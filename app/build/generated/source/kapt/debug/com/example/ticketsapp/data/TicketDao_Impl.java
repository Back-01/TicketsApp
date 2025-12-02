package com.example.ticketsapp.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TicketDao_Impl implements TicketDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Ticket> __insertionAdapterOfTicket;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTicket;

  public TicketDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTicket = new EntityInsertionAdapter<Ticket>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tickets` (`id`,`titulo`,`monto`,`fecha`,`rutaImagen`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Ticket entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitulo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitulo());
        }
        statement.bindDouble(3, entity.getMonto());
        if (entity.getFecha() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFecha());
        }
        if (entity.getRutaImagen() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRutaImagen());
        }
      }
    };
    this.__preparedStmtOfDeleteTicket = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tickets WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTicket(final Ticket ticket, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTicket.insert(ticket);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTicket(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTicket.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteTicket.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Ticket>> getAllTickets() {
    final String _sql = "SELECT * FROM tickets ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tickets"}, new Callable<List<Ticket>>() {
      @Override
      @NonNull
      public List<Ticket> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfRutaImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "rutaImagen");
          final List<Ticket> _result = new ArrayList<Ticket>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Ticket _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitulo;
            if (_cursor.isNull(_cursorIndexOfTitulo)) {
              _tmpTitulo = null;
            } else {
              _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            }
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final String _tmpRutaImagen;
            if (_cursor.isNull(_cursorIndexOfRutaImagen)) {
              _tmpRutaImagen = null;
            } else {
              _tmpRutaImagen = _cursor.getString(_cursorIndexOfRutaImagen);
            }
            _item = new Ticket(_tmpId,_tmpTitulo,_tmpMonto,_tmpFecha,_tmpRutaImagen);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTicketById(final long id, final Continuation<? super Ticket> $completion) {
    final String _sql = "SELECT * FROM tickets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Ticket>() {
      @Override
      @Nullable
      public Ticket call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfRutaImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "rutaImagen");
          final Ticket _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitulo;
            if (_cursor.isNull(_cursorIndexOfTitulo)) {
              _tmpTitulo = null;
            } else {
              _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            }
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final String _tmpRutaImagen;
            if (_cursor.isNull(_cursorIndexOfRutaImagen)) {
              _tmpRutaImagen = null;
            } else {
              _tmpRutaImagen = _cursor.getString(_cursorIndexOfRutaImagen);
            }
            _result = new Ticket(_tmpId,_tmpTitulo,_tmpMonto,_tmpFecha,_tmpRutaImagen);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
