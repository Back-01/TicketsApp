package com.example.ticketsapp.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.ticketsapp.ui.TicketsViewModel
import kotlinx.coroutines.launch
import java.io.File

// ML Kit imports
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTicketScreen(
    viewModel: TicketsViewModel,
    onBack: () -> Unit,
    onOpenCamera: () -> Unit
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var montoText by rememberSaveable { mutableStateOf("") }
    var fecha by rememberSaveable { mutableStateOf("") }

    val rutaImagen by viewModel.ultimaRutaImagen.collectAsState()
    val textoDetectado by viewModel.ultimoTextoDetectado.collectAsState()

    // Mostrar/ocultar texto detectado
    var mostrarTextoDetectado by rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Ticket") }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState) // permite desplazar todo
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = montoText,
                onValueChange = { montoText = it },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (ej. 25-11-18)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onOpenCamera,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tomar foto del ticket")
            }

            if (rutaImagen != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Foto lista: $rutaImagen",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Botón para OCR (monto + fecha)
                Button(
                    onClick = {
                        val path = rutaImagen
                        if (path == null) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Primero toma la foto del ticket."
                                )
                            }
                            return@Button
                        }

                        try {
                            val file = File(path)
                            val uri = Uri.fromFile(file)
                            val image = InputImage.fromFilePath(context, uri)

                            val recognizer = TextRecognition.getClient(
                                TextRecognizerOptions.DEFAULT_OPTIONS
                            )

                            recognizer.process(image)
                                .addOnSuccessListener { result ->
                                    val text = result.text
                                    viewModel.setUltimoTextoDetectado(text)

                                    // Sugerir monto automáticamente
                                    val sugeridoMonto = sugerirMontoDesdeTexto(text)

                                    // Sugerir fecha automáticamente (año 2 dígitos)
                                    val sugeridaFecha = sugerirFechaDesdeTexto(text)

                                    val mensajes = mutableListOf<String>()

                                    if (sugeridoMonto != null) {
                                        montoText = sugeridoMonto
                                        mensajes.add("Monto: $sugeridoMonto")
                                    }
                                    if (sugeridaFecha != null) {
                                        fecha = sugeridaFecha
                                        mensajes.add("Fecha: $sugeridaFecha")
                                    }

                                    scope.launch {
                                        if (mensajes.isNotEmpty()) {
                                            snackbarHostState.showSnackbar(
                                                message = "Sugerido desde el ticket → " +
                                                        mensajes.joinToString(" | ")
                                            )
                                        } else {
                                            snackbarHostState.showSnackbar(
                                                message = "No se encontró un monto o fecha claros en el texto."
                                            )
                                        }
                                    }
                                }
                                .addOnFailureListener { e ->
                                    e.printStackTrace()
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "No se pudo leer el texto del ticket."
                                        )
                                    }
                                }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Error al preparar la imagen para OCR."
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Leer texto del ticket")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 🔴 Botón para cancelar foto: elimina archivo y limpia estados
                OutlinedButton(
                    onClick = {
                        val path = rutaImagen
                        if (path != null) {
                            try {
                                val file = File(path)
                                if (file.exists()) {
                                    file.delete()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        viewModel.setUltimaRutaImagen(null)
                        viewModel.setUltimoTextoDetectado(null)
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Foto cancelada."
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar foto")
                }
            }

            if (!textoDetectado.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))

                // Botón para mostrar/ocultar el texto detectado
                TextButton(
                    onClick = { mostrarTextoDetectado = !mostrarTextoDetectado }
                ) {
                    Text(
                        if (mostrarTextoDetectado)
                            "Ocultar texto del ticket"
                        else
                            "Mostrar texto del ticket"
                    )
                }

                if (mostrarTextoDetectado) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Texto detectado:",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = textoDetectado ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val montoDouble = montoText.toDoubleOrNull()

                    if (titulo.isBlank() || fecha.isBlank() || montoDouble == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Completa título, monto válido y fecha."
                            )
                        }
                        return@Button
                    }

                    viewModel.addTicket(
                        titulo = titulo,
                        monto = montoDouble,
                        fecha = fecha,
                        rutaImagen = rutaImagen
                    )
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Guardar")
            }
        }
    }
}

/**
 * Intenta extraer un monto razonable del texto OCR.
 * Prioriza líneas con 'TOTAL', si no, toma el número más grande.
 */
private fun sugerirMontoDesdeTexto(texto: String): String? {
    val lines = texto.lines()
    val regex = Regex("""\d{1,3}(?:[.,]\d{3})*(?:[.,]\d{2})""")

    val candidatosTotal = lines
        .filter { it.contains("TOTAL", ignoreCase = true) }
        .flatMap { line -> regex.findAll(line).map { it.value } }

    val candidatos = if (candidatosTotal.isNotEmpty()) {
        candidatosTotal
    } else {
        regex.findAll(texto).map { it.value }.toList()
    }

    if (candidatos.isEmpty()) return null

    var mejorValor: Double? = null
    var mejorTexto: String? = null

    for (raw in candidatos) {
        val normalizado = normalizarMonto(raw) ?: continue
        val valor = normalizado.toDoubleOrNull() ?: continue

        if (mejorValor == null || valor > mejorValor!!) {
            mejorValor = valor
            mejorTexto = normalizado
        }
    }

    return mejorTexto
}

private fun normalizarMonto(raw: String): String? {
    var s = raw
        .replace(" ", "")
        .replace("$", "")

    val tienePunto = s.contains('.')
    val tieneComa = s.contains(',')

    if (tienePunto && tieneComa) {
        s = s.replace(".", "")
        s = s.replace(",", ".")
    } else if (!tienePunto && tieneComa) {
        s = s.replace(",", ".")
    } else {
        s = s.replace(",", "")
    }

    return s
}


private fun sugerirFechaDesdeTexto(texto: String): String? {
    val lines = texto.lines()

    // Formato YYYY-MM-DD o YYYY/MM/DD
    val regexYmd = Regex("""\b(20\d{2})[-/](0[1-9]|1[0-2])[-/](0[1-9]|[12]\d|3[01])\b""")

    // Formato DD-MM-YYYY o DD/MM/YYYY
    val regexDmy = Regex("""\b(0[1-9]|[12]\d|3[01])[-/](0[1-9]|1[0-2])[-/](20\d{2})\b""")

    // Formato DD/MM/YY o DD-MM-YY
    val regexDmyShortYear = Regex("""\b(0[1-9]|[12]\d|3[01])[-/](0[1-9]|1[0-2])[-/](\d{2})\b""")

    // Formato con mes en texto (ENE, FEB, MAR, ABR, MAY, JUN, JUL, AGO, SEP, OCT, NOV, DIC)
    // Ej: 15 MAR 2025, 15-mar-25, 03/ene/24
    val regexTextMonth = Regex(
        """\b(0[1-9]|[12]\d|3[01])[\s./-]*(ENE|FEB|MAR|ABR|MAY|JUN|JUL|AGO|SEP|OCT|NOV|DIC)[\s./-]*((20)?\d{2})\b""",
        RegexOption.IGNORE_CASE
    )

    // 1) Buscar primero en líneas con 'FECHA'
    val lineasFecha = lines.filter { it.contains("FECHA", ignoreCase = true) }

    val candidatosPrioritarios = mutableListOf<String>()

    for (line in lineasFecha) {
        regexYmd.find(line)?.let { candidatosPrioritarios.add(it.value) }
        regexDmy.find(line)?.let { candidatosPrioritarios.add(it.value) }
        regexDmyShortYear.find(line)?.let { candidatosPrioritarios.add(it.value) }
        regexTextMonth.find(line)?.let { candidatosPrioritarios.add(it.value) }
    }

    val candidato = when {
        candidatosPrioritarios.isNotEmpty() -> candidatosPrioritarios.first()
        else -> {
            // 2) Si no hubo 'FECHA', buscar en todo el texto (en orden de "confiabilidad")
            regexYmd.find(texto)?.value
                ?: regexDmy.find(texto)?.value
                ?: regexDmyShortYear.find(texto)?.value
                ?: regexTextMonth.find(texto)?.value
        }
    }

    if (candidato == null) return null

    return normalizarFecha(candidato)
}

/**
 * Normaliza fechas a formato YY/MM/DD (solo últimos 2 dígitos del año)
 *  - "2025-11-18" → "25/11/18"
 *  - "18/11/2025" → "25/11/18"
 *  - "18/11/25"   → "25/11/18" (asumiendo 20xx)
 *  - "15 MAR 2025" → "25/03/15"
 */
private fun normalizarFecha(raw: String): String? {
    val s = raw.trim().uppercase()

    // Mapa de meses en texto a número
    val meses = mapOf(
        "ENE" to "01",
        "FEB" to "02",
        "MAR" to "03",
        "ABR" to "04",
        "MAY" to "05",
        "JUN" to "06",
        "JUL" to "07",
        "AGO" to "08",
        "SEP" to "09",
        "OCT" to "10",
        "NOV" to "11",
        "DIC" to "12"
    )

    // YYYY-MM-DD o YYYY/MM/DD
    val regexYmd = Regex("""^(20\d{2})[-/](0[1-9]|1[0-2])[-/](0[1-9]|[12]\d|3[01])$""")
    regexYmd.matchEntire(s)?.let { m ->
        val (y, mth, d) = m.destructured
        val yy = y.takeLast(2)   // solo últimos 2 dígitos del año
        return "$yy/$mth/$d"
    }

    // DD-MM-YYYY o DD/MM/YYYY
    val regexDmy = Regex("""^(0[1-9]|[12]\d|3[01])[-/](0[1-9]|1[0-2])[-/](20\d{2})$""")
    regexDmy.matchEntire(s)?.let { m ->
        val (d, mth, y) = m.destructured
        val yy = y.takeLast(2)
        return "$yy/$mth/$d"
    }

    // DD-MM-YY o DD/MM/YY
    val regexDmyShortYear = Regex("""^(0[1-9]|[12]\d|3[01])[-/](0[1-9]|1[0-2])[-/](\d{2})$""")
    regexDmyShortYear.matchEntire(s)?.let { m ->
        val (d, mth, yy) = m.destructured
        // asumimos 20xx
        return "$yy/$mth/$d"
    }

    // Formatos con mes en texto: DD MES YYYY o DD MES YY
    val regexTextMonth = Regex(
        """^(0[1-9]|[12]\d|3[01])[\s./-]*(ENE|FEB|MAR|ABR|MAY|JUN|JUL|AGO|SEP|OCT|NOV|DIC)[\s./-]*((20)?\d{2})$"""
    )
    regexTextMonth.matchEntire(s)?.let { m ->
        // grupos: día, mesTxt, añoCompleto (o parcial), prefijo20?
        val d = m.groupValues[1]
        val mesTxt = m.groupValues[2]
        val anyoRaw = m.groupValues[3]  // puede ser "2025" o "25"
        val mesNum = meses[mesTxt] ?: return null

        val yy = if (anyoRaw.length == 4) {
            anyoRaw.takeLast(2)
        } else {
            anyoRaw
        }

        return "$yy/$mesNum/$d"
    }

    return null
}


