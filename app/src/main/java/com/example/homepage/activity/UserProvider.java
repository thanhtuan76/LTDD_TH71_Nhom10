package com.example.homepage.activity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class UserProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.homepage.UserProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/users";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";

    private static HashMap<String, String> USERS_PROJECTION_MAP;

    static final int USERS = 1;
    static final int USER_ID = 2;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", USERS);
        uriMatcher.addURI(PROVIDER_NAME, "users/#", USER_ID);
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "TuaLuaShop";
    static final String USERS_TABLE_NAME = "Users";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + USERS_TABLE_NAME +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "password TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
            onCreate(db);
        }
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        return db == null ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(USERS_TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case USERS:
                queryBuilder.setProjectionMap(USERS_PROJECTION_MAP);
                break;
            case USER_ID:
                queryBuilder.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                break;
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = USERNAME;
        }
        Cursor c = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USERS:
                return "vnd.android.cursor.dir/vnd.example.users";
            case USER_ID:
                return "vnd.android.cursor.item/vnd.example.users";
            default:
                throw new IllegalArgumentException("Wrong statement URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = db.insert(USERS_TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return uri;
        }
        throw new SQLException("Failed to insert " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USERS:
                count = db.delete(USERS_TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(USERS_TABLE_NAME, _ID + "=" + id + (!TextUtils.isEmpty(selection)? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Wrong statement URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USERS:
                count = db.update(USERS_TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(USERS_TABLE_NAME, values, _ID + "=" + id + (!TextUtils.isEmpty(selection)? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Wrong statement URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
