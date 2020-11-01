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

public class DatabaseManager {

    private final String TAG = DatabaseManager.this.getClass().getSimpleName();
    private final Context mContext;
    private static DatabaseManager INSTANCE;
    private DatabaseHelper databaseHelper;

    private Dao<StudentItemTable, Long> studentItemDao;
    private static String INDEX = "index";
    private static String ID = "id";
    private static String NAME = "name";
    private static String PHONE = "phone";
    private static String ADDRESS = "address";


    public DatabaseManager(Context mContext) {
        Log.i(TAG, "DatabaseManager");
        this.mContext = mContext;
        databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);

        try {
            studentItemDao = databaseHelper.getStudentItemDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(Context context){
        if(INSTANCE == null) INSTANCE = new DatabaseManager(context);
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
        QueryBuilder queryBuilder = studentItemDao.queryBuilder();
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


    public int insertStudentItem(StudentItemTable stuItemDB, boolean isEdit){
        int count = 0;
        try {
            UpdateBuilder updateBuilder = studentItemDao.updateBuilder();
            String index = stuItemDB.getIndex() != null ? stuItemDB.getIndex() : "";
            String id = stuItemDB.getId() != null ? stuItemDB.getId().toString() : "";
            String name = stuItemDB.getName() != null ? stuItemDB.getName() : "";
            String phone = stuItemDB.getPhone() != null ? stuItemDB.getPhone() : "";
            String address = stuItemDB.getAddress() != null ? stuItemDB.getAddress() : "";

            if(studentItemDao == null) return -1;

            if(isUserExisting(index)){
                Log.i(TAG,"this user exist");
                count = 1;
                if(isEdit){
                    deleteUser(index);
                    studentItemDao.create(stuItemDB);
                }
            }else {
                count = 0;
                studentItemDao.create(stuItemDB);
            }
            return count;

        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public int deleteUser(String index){
        try {
            if(studentItemDao == null) return -1;
            DeleteBuilder deleteBuilder = studentItemDao.deleteBuilder();
            if(index != null || !index.isEmpty()) deleteBuilder.where().eq(INDEX,index);
            deleteBuilder.delete();
            Log.i(TAG,"user deleted");
            return 0;
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public List<StudentItemTable> getAllStudent(){
        try {
            if (studentItemDao == null)return null;
            return studentItemDao.queryForAll();
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}