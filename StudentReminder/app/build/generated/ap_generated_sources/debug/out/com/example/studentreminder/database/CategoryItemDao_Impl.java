package com.example.studentreminder.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studentreminder.models.CategoryItem;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CategoryItemDao_Impl implements CategoryItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CategoryItem> __insertionAdapterOfCategoryItem;

  private final EntityDeletionOrUpdateAdapter<CategoryItem> __deletionAdapterOfCategoryItem;

  private final EntityDeletionOrUpdateAdapter<CategoryItem> __updateAdapterOfCategoryItem;

  public CategoryItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategoryItem = new EntityInsertionAdapter<CategoryItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CategoryItem` (`id`,`category`,`colour`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CategoryItem value) {
        stmt.bindLong(1, value.id);
        if (value.category == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.category);
        }
        if (value.colour == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.colour);
        }
      }
    };
    this.__deletionAdapterOfCategoryItem = new EntityDeletionOrUpdateAdapter<CategoryItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CategoryItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CategoryItem value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfCategoryItem = new EntityDeletionOrUpdateAdapter<CategoryItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CategoryItem` SET `id` = ?,`category` = ?,`colour` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CategoryItem value) {
        stmt.bindLong(1, value.id);
        if (value.category == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.category);
        }
        if (value.colour == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.colour);
        }
        stmt.bindLong(4, value.id);
      }
    };
  }

  @Override
  public long insert(final CategoryItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCategoryItem.insertAndReturnId(item);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CategoryItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCategoryItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CategoryItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCategoryItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CategoryItem> getCategories() {
    final String _sql = "select * from categoryitem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfColour = CursorUtil.getColumnIndexOrThrow(_cursor, "colour");
      final List<CategoryItem> _result = new ArrayList<CategoryItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CategoryItem _item;
        _item = new CategoryItem();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _item.category = null;
        } else {
          _item.category = _cursor.getString(_cursorIndexOfCategory);
        }
        if (_cursor.isNull(_cursorIndexOfColour)) {
          _item.colour = null;
        } else {
          _item.colour = _cursor.getString(_cursorIndexOfColour);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CategoryItem findById(final long id) {
    final String _sql = "select * from categoryitem where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfColour = CursorUtil.getColumnIndexOrThrow(_cursor, "colour");
      final CategoryItem _result;
      if(_cursor.moveToFirst()) {
        _result = new CategoryItem();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _result.category = null;
        } else {
          _result.category = _cursor.getString(_cursorIndexOfCategory);
        }
        if (_cursor.isNull(_cursorIndexOfColour)) {
          _result.colour = null;
        } else {
          _result.colour = _cursor.getString(_cursorIndexOfColour);
        }
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
