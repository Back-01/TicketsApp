package com.example.ticketsapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010\tJ\u0010\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00100\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006%"}, d2 = {"Lcom/example/ticketsapp/ui/TicketsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/ticketsapp/data/TicketRepository;", "(Lcom/example/ticketsapp/data/TicketRepository;)V", "_currentTicket", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/ticketsapp/data/Ticket;", "_ultimaRutaImagen", "", "_ultimoTextoDetectado", "currentTicket", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentTicket", "()Lkotlinx/coroutines/flow/StateFlow;", "tickets", "", "getTickets", "ultimaRutaImagen", "getUltimaRutaImagen", "ultimoTextoDetectado", "getUltimoTextoDetectado", "addTicket", "", "titulo", "monto", "", "fecha", "rutaImagen", "deleteTicket", "id", "", "loadTicket", "setUltimaRutaImagen", "path", "setUltimoTextoDetectado", "text", "app_debug"})
public final class TicketsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.ticketsapp.data.TicketRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.ticketsapp.data.Ticket>> tickets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.ticketsapp.data.Ticket> _currentTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.ticketsapp.data.Ticket> currentTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _ultimaRutaImagen = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> ultimaRutaImagen = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _ultimoTextoDetectado = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> ultimoTextoDetectado = null;
    
    public TicketsViewModel(@org.jetbrains.annotations.NotNull()
    com.example.ticketsapp.data.TicketRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.ticketsapp.data.Ticket>> getTickets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.ticketsapp.data.Ticket> getCurrentTicket() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getUltimaRutaImagen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getUltimoTextoDetectado() {
        return null;
    }
    
    public final void loadTicket(long id) {
    }
    
    public final void addTicket(@org.jetbrains.annotations.NotNull()
    java.lang.String titulo, double monto, @org.jetbrains.annotations.NotNull()
    java.lang.String fecha, @org.jetbrains.annotations.Nullable()
    java.lang.String rutaImagen) {
    }
    
    public final void deleteTicket(long id) {
    }
    
    public final void setUltimaRutaImagen(@org.jetbrains.annotations.Nullable()
    java.lang.String path) {
    }
    
    public final void setUltimoTextoDetectado(@org.jetbrains.annotations.Nullable()
    java.lang.String text) {
    }
}