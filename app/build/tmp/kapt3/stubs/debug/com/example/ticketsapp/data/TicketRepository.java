package com.example.ticketsapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00100\u000fJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/ticketsapp/data/TicketRepository;", "", "dao", "Lcom/example/ticketsapp/data/TicketDao;", "(Lcom/example/ticketsapp/data/TicketDao;)V", "addTicket", "", "ticket", "Lcom/example/ticketsapp/data/Ticket;", "(Lcom/example/ticketsapp/data/Ticket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTicket", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTickets", "Lkotlinx/coroutines/flow/Flow;", "", "getTicket", "app_debug"})
public final class TicketRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.ticketsapp.data.TicketDao dao = null;
    
    public TicketRepository(@org.jetbrains.annotations.NotNull()
    com.example.ticketsapp.data.TicketDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.ticketsapp.data.Ticket>> getAllTickets() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addTicket(@org.jetbrains.annotations.NotNull()
    com.example.ticketsapp.data.Ticket ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTicket(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.ticketsapp.data.Ticket> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTicket(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}