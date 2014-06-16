package com.tools.tommydev.videofinder.DataBaseHelper;

/**
 * Created by TomMy on 8/8/13.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DBClass_favor extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION =4;

    // Database Name
    private static final String DATABASE_NAME = "youtubedownloader_x";

    // Table Name
    private static final String TABLE_MEMBER = "favor_x";

    public DBClass_favor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // Create Table Name
        db.execSQL("CREATE TABLE " + TABLE_MEMBER +
                "(MemberID TEXT PRIMARY KEY," +
                " Name TEXT(255)," +
                " Filename TEXT(255)," +
                " status TEXT(255)," +
                " Filesize TEXT(255)," +
                " datex TEXT(255)," +
                " img TEXT(255)" +
                ");");

        Log.d("CREATE TABLE","Create Table Successfully.");
    }

    // Insert Data
    public long InsertData(String strName, String Filename, String status, String filesize, String MemberID, String datax,String imgUrl) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "INSERT INTO " + TABLE_MEMBER
             + "(MemberID,Name) VALUES (?,?)";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);
             insertCmd.bindString(2, strName);
             return insertCmd.executeInsert();
             */

            ContentValues Val = new ContentValues();
            Val.put("MemberID", MemberID);
            Val.put("Name", strName);
            Val.put("Filename", Filename);
            Val.put("status", status);
            Val.put("filesize", filesize);
            Val.put("datex", datax);
            Val.put("img", imgUrl);
            long rows = db.insert(TABLE_MEMBER, null, Val);


        	Log.e("MemberID",MemberID);
        	Log.e("Name",strName);
        	Log.e("Filename",Filename);
        	Log.e("status",status);
        	Log.e("filesize",filesize);
        	Log.e("datex",datax);
        	Log.e("img",imgUrl);
      
            
            
            
            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return DeleteData(MemberID);
        }

    }


    // Select Data
    public String[] SelectData(String strMemberID) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" },
                    "MemberID=?",
                    new String[] { String.valueOf(strMemberID) }, null, null, null, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    /***
                     *  0 = MemberID
                     *  1 = Name
                     *  2 = Tel
                     */
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }


    // Select All Data
    public class sMembers {
        String _MemberID;
        String _Name;
        String _Tel;
        String _Status;
        String _Filesize;
        String _datex;
        String _img;
        public String get_datex() {
            return _datex;
        }

        public void set_datex(String _datex) {
            this._datex = _datex;
        }

        public String get_Filesize() {
            return _Filesize;
        }

        public void set_Filesize(String _Filesize) {
            this._Filesize = _Filesize;
        }

        public String get_Filename() {
            return _Filename;
        }

        public void set_Filename(String _Filename) {
            this._Filename = _Filename;
        }

        String _Filename;

        // Set Value
        public void sMemberID(String vMemberID){
            this._MemberID = vMemberID;
        }
        public void sName(String vName){
            this._Name = vName;
        }


        // Get Value
        public String gMemberID(){
            return _MemberID;
        }
        public String gName(){
            return _Name;
        }

        public String get_Status() {
            return _Status;
        }

        public void set_Status(String _Status) {
            this._Status = _Status;
        }

        public String get_img() {
            return _img;
        }

        public void set_img(String _img) {
            this._img = _img;
        }
    }

    public List<sMembers> SelectAllData() {
        // TODO Auto-generated method stub

        try {
            List<sMembers> MemberList = new ArrayList<sMembers>();

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_MEMBER+" ORDER BY datex DESC";
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        sMembers cMember = new sMembers();
                        cMember.sMemberID(cursor.getString(0));
                        cMember.sName(cursor.getString(1));
                        cMember.set_Filename(cursor.getString(2));
                        cMember.set_Status(cursor.getString(3));
                        cMember.set_Filesize(cursor.getString(4));
                        cMember.set_datex(cursor.getString(5));
                        cMember.set_img(cursor.getString(6));
                        MemberList.add(cMember);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MemberList;

        } catch (Exception e) {
            return null;
        }

    }

//    // Update Data
    public long UpdateDownloadData(String filename,String status) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "UPDATE " + TABLE_MEMBER
             + " SET Name = ? "
             + " , Tel = ? "
             + " WHERE MemberID = ? ";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strName);
             insertCmd.bindString(2, strTel);
             insertCmd.bindString(3, strMemberID);

             return insertCmd.executeUpdateDelete();
             *
             */
            ContentValues Val = new ContentValues();
            Val.put("status", status);

            long rows = db.update(TABLE_MEMBER, Val, " Filename = ?",
                    new String[] { String.valueOf(filename) });

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }

    // Delete Data
    public long DeleteData(String strMemberID) {
        // TODO Auto-generated method stub
        Log.e("DeleteData",strMemberID);
        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             * for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "DELETE FROM " + TABLE_MEMBER
             + " WHERE MemberID = ? ";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);

             return insertCmd.executeUpdateDelete();
             *
             */

            long rows =  db.delete(TABLE_MEMBER, "MemberID = ?",
                    new String[] { String.valueOf(strMemberID) });

            db.close();
            return rows; // return rows delete.

        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);

        // Re Create on method  onCreate
        onCreate(db);
    }



    public boolean SelectDataWithName(String Filename) {
        // TODO Auto-generated method stub
    boolean hasFile=false;
      //  Log.e("R2P",Filename);



        String path = Environment.getExternalStorageDirectory().toString()+"/download/"+Filename;
        File f = new File(path);
        hasFile=f.exists();
        if(hasFile){
          //  Log.e("R2P","HaveFile");
//            try {
//                SQLiteDatabase db;
//                db = this.getReadableDatabase(); // Read Data
//                Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" },"Filename=?", new String[] { String.valueOf(Filename) }, null, null, null, null);
//                if(cursor != null)
//                {
//                    if (cursor.moveToFirst()) {
//                        Log.e("R2P>>>>>","["+cursor.getString(3)+"]");
//                        if(cursor.getString(3).equals("downloaded")){
//                            hasFile=true;
//                        }else{
//                            hasFile=false;
//                        }
//                    }}
//                cursor.close();
//                db.close();
//            } catch (Exception e) {
//                Log.e("R2P","Test",e);
//            }

            hasFile=true;
        }else{
         //   Log.e("R2P","notFile");
            hasFile=false;
        }



    return  hasFile;

    }

    public boolean SelectDataWithNameArray(String id) {
        // TODO Auto-generated method stub
    //    Log.e("SelectDataWithNameArray",id);
        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" },
                    "MemberID=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    /***
                     *  0 = MemberID
                     *  1 = Name
                     *  2 = Tel
                     */
                    arrData[0] = cursor.getString(0);
                    if(arrData[0].equals(id)){
                        return  true;
                    }else{
                        return  false;
                    }
//                    arrData[1] = cursor.getString(1);
//                    arrData[2] = cursor.getString(2);
//                    arrData[3] = cursor.getString(3);
//                    arrData[4] = cursor.getString(4);
//                    return true;
                }}
            cursor.close();
            db.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

}