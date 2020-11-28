package com.project.usmansh.firstactivity.DBClasses;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.List;

public class TeacherDBManeger  {


    private final String TAG = TeacherDBManeger.this.getClass().getSimpleName();
    private final Context mContext;
    private static TeacherDBManeger INSTANCE;
    private DatabaseHelper databaseHelper;

    private Dao<TeacherItemTable, Long> teacherItemDao;
    private static String INDEX = "index";
    private static String ID = "id";
    private static String NAME = "name";
    private static String PHONE = "phone";
    private static String ADDRESS = "address";

    public TeacherDBManeger(Context mContext) {
        Log.i(TAG, "TeacherDBManeger");
        this.mContext = mContext;
        databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);

        try {
            teacherItemDao = databaseHelper.getTeacherItemDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static TeacherDBManeger getInstance(Context context){
        if(INSTANCE == null) INSTANCE = new TeacherDBManeger(context);
        return INSTANCE;
    }




    public void releaseDB(){
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
        }
    }



    public int clearAllData(){
        try {
            if (databaseHelper == null) return -1;
            databaseHelper.clearTable();
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }



    public boolean isUserExisting(String index){
        QueryBuilder queryBuilder = teacherItemDao.queryBuilder();
        boolean flag = false;
        try {
            if(queryBuilder.where().eq(INDEX,index).countOf()>0){
                flag = true;
            }else {
                flag = false;
            }
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
        }
        return flag;
    }


    public int insertTeacherItem(TeacherItemTable tecItemDB, boolean isEdit){
        int count = 0;
        try {
            UpdateBuilder updateBuilder = teacherItemDao.updateBuilder();

            String index = tecItemDB.getIndex() != null ? tecItemDB.getIndex() : "";
            String id = tecItemDB.getId() != null ? tecItemDB.getId().toString() : "";
            String name = tecItemDB.getName() != null ? tecItemDB.getName() : "";
            String phone = tecItemDB.getPhone() != null ? tecItemDB.getPhone() : "";
            String address = tecItemDB.getAddress() != null ? tecItemDB.getAddress() : "";

            if(teacherItemDao == null) return -1;

            if(isUserExisting(index)){
                Log.i(TAG,"this user exist");
                count = 1;
                if(isEdit){
                    deleteUser(index);
                    teacherItemDao.create(tecItemDB);
                }
            }else {
                count = 0;
                teacherItemDao.create(tecItemDB);
            }
            return count;

        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }



    public int deleteUser(String index){
        try {
            if(teacherItemDao == null) return -1;
            DeleteBuilder deleteBuilder = teacherItemDao.deleteBuilder();
            if(index != null || !index.isEmpty())
                deleteBuilder.where().eq(INDEX,index);
                deleteBuilder.delete();
            Log.i(TAG,"user deleted");
            return 0;
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public List<TeacherItemTable> getAllTeacher(){
        try {
            if (teacherItemDao == null)return null;
            return teacherItemDao.queryForAll();
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return null;
        }
    }



}
