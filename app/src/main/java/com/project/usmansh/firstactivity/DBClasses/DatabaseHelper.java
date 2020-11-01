package com.project.usmansh.firstactivity.DBClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {



    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private Dao<StudentItemTable, Long> studentrItemDBS;
    private Dao<TeacherItemTable,Long> teacherItemDao;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, StudentItemTable.class);
            TableUtils.createTable(connectionSource, TeacherItemTable.class);

        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            if(checkTableExist(database,"student_table")) {
                TableUtils.dropTable(connectionSource, StudentItemTable.class, false);
            }
            if(checkTableExist(database,"teacher_table")) {
                TableUtils.dropTable(connectionSource, TeacherItemTable.class, false);
            }

            onCreate(database,connectionSource);


        }catch (SQLException e){
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean checkTableExist(SQLiteDatabase database, String tableName){
        Cursor c = null;
        boolean tableExist = false;
        try {
            c = database.query(tableName, null,null,null,null,null,null);
            tableExist = true;
        }catch (Exception e){

        }
        return tableExist;
    }



    public Dao<StudentItemTable, Long> getStudentItemDao() throws SQLException{
        if(studentrItemDBS == null){
            try {
                studentrItemDBS = getDao(StudentItemTable.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return studentrItemDBS;
    }


    public Dao<TeacherItemTable, Long> getTeacherItemDao() throws SQLException{
        if(teacherItemDao == null){
            try {
                teacherItemDao = getDao(TeacherItemTable.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return  teacherItemDao;
    }




    @Override
    public void close() {
        studentrItemDBS = null;
        teacherItemDao = null;
        super.close();
    }

    public void clearTable() throws SQLException{
        try {

            TableUtils.clearTable(getConnectionSource(), TeacherItemTable.class);

            TableUtils.clearTable(getConnectionSource(), StudentItemTable.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }










    //=============================================================================

//    private static final String DATABASE_NAME = "VehMaintDB";
//    private static final int DATABASE_VERSION = 1;
//
//    private Dao<VehItemDB, Long> userItemDBS;
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME,null,DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
//
//        try {
//            TableUtils.createTable(connectionSource, VehItemDB.class);
//        }catch (SQLException | java.sql.SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        try {
//            if(checkTableExist(database,"item_user"))
//                TableUtils.dropTable(connectionSource, VehItemDB.class,false);
//
//            onCreate(database,connectionSource);
//        }catch (SQLException e){
//            e.printStackTrace();
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private boolean checkTableExist(SQLiteDatabase database, String tableName){
//        Cursor c = null;
//        boolean tableExist = false;
//        try {
//            c = database.query(tableName, null,null,null,null,null,null);
//            tableExist = true;
//        }catch (Exception e){
//
//        }
//        return tableExist;
//    }
//
//    public Dao<VehItemDB, Long> getUserItemDao() throws SQLException{
//        if(userItemDBS == null){
//            try {
//                userItemDBS = getDao(VehItemDB.class);
//            } catch (java.sql.SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return userItemDBS;
//    }
//
//    @Override
//    public void close() {
//        userItemDBS = null;
//        super.close();
//    }
//
//    public void clearTable() throws SQLException{
//        try {
//            TableUtils.clearTable(getConnectionSource(), VehItemDB.class);
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
//    }
}