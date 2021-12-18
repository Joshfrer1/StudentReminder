package com.example.studentreminder.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studentreminder.models.ToDoItem;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ToDoItemDao_Impl implements ToDoItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ToDoItem> __insertionAdapterOfToDoItem;

  private final EntityDeletionOrUpdateAdapter<ToDoItem> __deletionAdapterOfToDoItem;

  private final EntityDeletionOrUpdateAdapter<ToDoItem> __updateAdapterOfToDoItem;

  public ToDoItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfToDoItem = new EntityInsertionAdapter<ToDoItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ToDoItem` (`id`,`title`,`is_completed`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ToDoItem value) {
        stmt.bindLong(1, value.id);
        if (value.title == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.title);
        }
        final int _tmp;
        _tmp = value.isCompleted ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__deletionAdapterOfToDoItem = new EntityDeletionOrUpdateAdapter<ToDoItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ToDoItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ToDoItem value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfToDoItem = new EntityDeletionOrUpdateAdapter<ToDoItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ToDoItem` SET `id` = ?,`title` = ?,`is_completed` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ToDoItem value) {
        stmt.bindLong(1, value.id);
        if (value.title == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.title);
        }
        final int _tmp;
        _tmp = value.isCompleted ? 1 : 0;
        stmt.bindLong(3, _tmp);
        stmt.bindLong(4, value.id);
      }
    };
  }

  @Override
  public long insert(final ToDoItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfToDoItem.insertAndReturnId(item);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteItem(final ToDoItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfToDoItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ToDoItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfToDoItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ToDoItem> getAll() {
    final String _sql = "SELECT * FROM ToDoItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
      final List<ToDoItem> _result = new ArrayList<ToDoItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ToDoItem _item;
        _item = new ToDoItem();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _item.title = null;
        } else {
          _item.title = _cursor.getString(_cursorIndexOfTitle);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
        _item.isCompleted = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ToDoItem getOne(final long id) {
    final String _sql = "SELECT * FROM ToDoItem WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
      final ToDoItem _result;
      if(_cursor.moveToFirst()) {
        _result = new ToDoItem();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _result.title = null;
        } else {
          _result.title = _cursor.getString(_cursorIndexOfTitle);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
        _result.isCompleted = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
