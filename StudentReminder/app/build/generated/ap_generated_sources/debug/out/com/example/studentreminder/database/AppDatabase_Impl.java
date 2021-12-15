package com.example.studentreminder.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ToDoItemDao _toDoItemDao;

  private volatile CategoryItemDao _categoryItemDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ToDoItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `due_date` INTEGER NOT NULL, `remind_date` INTEGER NOT NULL, `reoccur` TEXT, `category_id` INTEGER NOT NULL, `is_canvas_item` INTEGER NOT NULL, `is_completed` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CategoryItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category` TEXT, `colour` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2b7502da97543f49aeefe6d77d46aa00')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ToDoItem`");
        _db.execSQL("DROP TABLE IF EXISTS `CategoryItem`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsToDoItem = new HashMap<String, TableInfo.Column>(8);
        _columnsToDoItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("due_date", new TableInfo.Column("due_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("remind_date", new TableInfo.Column("remind_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("reoccur", new TableInfo.Column("reoccur", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("category_id", new TableInfo.Column("category_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("is_canvas_item", new TableInfo.Column("is_canvas_item", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToDoItem.put("is_completed", new TableInfo.Column("is_completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysToDoItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesToDoItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoToDoItem = new TableInfo("ToDoItem", _columnsToDoItem, _foreignKeysToDoItem, _indicesToDoItem);
        final TableInfo _existingToDoItem = TableInfo.read(_db, "ToDoItem");
        if (! _infoToDoItem.equals(_existingToDoItem)) {
          return new RoomOpenHelper.ValidationResult(false, "ToDoItem(com.example.studentreminder.models.ToDoItem).\n"
                  + " Expected:\n" + _infoToDoItem + "\n"
                  + " Found:\n" + _existingToDoItem);
        }
        final HashMap<String, TableInfo.Column> _columnsCategoryItem = new HashMap<String, TableInfo.Column>(3);
        _columnsCategoryItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItem.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItem.put("colour", new TableInfo.Column("colour", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategoryItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategoryItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategoryItem = new TableInfo("CategoryItem", _columnsCategoryItem, _foreignKeysCategoryItem, _indicesCategoryItem);
        final TableInfo _existingCategoryItem = TableInfo.read(_db, "CategoryItem");
        if (! _infoCategoryItem.equals(_existingCategoryItem)) {
          return new RoomOpenHelper.ValidationResult(false, "CategoryItem(com.example.studentreminder.models.CategoryItem).\n"
                  + " Expected:\n" + _infoCategoryItem + "\n"
                  + " Found:\n" + _existingCategoryItem);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2b7502da97543f49aeefe6d77d46aa00", "68141ec7153a64cbbe8b982c5478a87e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ToDoItem","CategoryItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ToDoItem`");
      _db.execSQL("DELETE FROM `CategoryItem`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ToDoItemDao.class, ToDoItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CategoryItemDao.class, CategoryItemDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public ToDoItemDao getToDoItemDao() {
    if (_toDoItemDao != null) {
      return _toDoItemDao;
    } else {
      synchronized(this) {
        if(_toDoItemDao == null) {
          _toDoItemDao = new ToDoItemDao_Impl(this);
        }
        return _toDoItemDao;
      }
    }
  }

  @Override
  public CategoryItemDao getCategoryItemDao() {
    if (_categoryItemDao != null) {
      return _categoryItemDao;
    } else {
      synchronized(this) {
        if(_categoryItemDao == null) {
          _categoryItemDao = new CategoryItemDao_Impl(this);
        }
        return _categoryItemDao;
      }
    }
  }
}
