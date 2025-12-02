package com.example.ticketsapp.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u001a\u0012\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u001a\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u001a\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a8\u0006\u000e"}, d2 = {"AddTicketScreen", "", "viewModel", "Lcom/example/ticketsapp/ui/TicketsViewModel;", "onBack", "Lkotlin/Function0;", "onOpenCamera", "normalizarFecha", "", "raw", "normalizarMonto", "sugerirFechaDesdeTexto", "texto", "sugerirMontoDesdeTexto", "app_debug"})
public final class AddTicketScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AddTicketScreen(@org.jetbrains.annotations.NotNull()
    com.example.ticketsapp.ui.TicketsViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenCamera) {
    }
    
    /**
     * Intenta extraer un monto razonable del texto OCR.
     * Prioriza líneas con 'TOTAL', si no, toma el número más grande.
     */
    private static final java.lang.String sugerirMontoDesdeTexto(java.lang.String texto) {
        return null;
    }
    
    private static final java.lang.String normalizarMonto(java.lang.String raw) {
        return null;
    }
    
    private static final java.lang.String sugerirFechaDesdeTexto(java.lang.String texto) {
        return null;
    }
    
    /**
     * Normaliza fechas a formato YY/MM/DD (solo últimos 2 dígitos del año)
     * - "2025-11-18" → "25/11/18"
     * - "18/11/2025" → "25/11/18"
     * - "18/11/25"   → "25/11/18" (asumiendo 20xx)
     * - "15 MAR 2025" → "25/03/15"
     */
    private static final java.lang.String normalizarFecha(java.lang.String raw) {
        return null;
    }
}