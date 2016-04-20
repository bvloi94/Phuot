package com.loibv.t1p.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "T1P.db";

    // Table names
    public static final String TABLE_TRIP = "Trip";
    public static final String TABLE_TRIPPLACE = "TripPlace";
    public static final String TABLE_PLACE = "Place";
    public static final String TABLE_DISTRICT = "District";
    public static final String TABLE_CITY = "City";
    public static final String TABLE_TRIPPREPARATION = "TripPreparation";
    public static final String TABLE_PREPARATION = "Preparation";
    private static final String TABLE_ACCOUNT = "Account";
    private static final String TABLE_NOTIFICATION = "Notification";
    private static final String TABLE_FUND = "Fund";
    private static final String TABLE_TRANSACTION = "Transaction";
    private static final String TABLE_FUNDTRANSACTION = "FundTransaction";


    // Common column names
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_TYPE = "Type";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_LOCATION = "Location";

    // Table Trip columns
    public static final String KEY_CREATETIME = "CreateTime";
    public static final String KEY_STARTTIME = "StartTime";
    public static final String KEY_ENDTIME = "EndTime";
    public static final String KEY_GATHERINGPLACE = "GatheringPlace";
    public static final String KEY_STATUS = "Status";
    public static final String KEY_LEADERID = "LeaderId";
    public static final String KEY_CITYID = "CityId";
    public static final String KEY_PREPARATIONID = "PreparationId";
    // Table TripPlace columns
    public static final String KEY_TRIPID = "TripId";
    public static final String KEY_PLACEID = "PlaceId";
    public static final String KEY_NEXTTRIPID = "NextTripId";
    public static final String KEY_VEHICLE = "Vehicle";
    // Table Place columns
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_IMAGE_PATH = "ImagePath";
    public static final String KEY_LATITUDE = "Latitude";
    public static final String KEY_LONGITUDE = "Longitude";
    // Table Preparation columns
    public static final String KEY_MEMBERID = "MemberId";
    public static final String KEY_OBJECT = "Object";
    // Table Account columns
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_AVATAR = "Avatar";
    public static final String KEY_FULLNAME = "Fullname";
    public static final String KEY_ROLEID = "RoleId";
    public static final String KEY_ISACTIVE = "IsActive";
    // Table Notification columns
    public static final String KEY_RECEIVERID = "ReceiverId";
    public static final String KEY_SENDERID = "SenderId";
    public static final String KEY_MESSAGE = "Message";
    public static final String KEY_ISREADED = "IsReaded";
    // Table Fund columns
    public static final String KEY_TOTAL = "Total";
    public static final String KEY_MANAGERID = "ManagerId";
    // Table Transaction columns
    public static final String KEY_AMOUNT = "Amount";
    // Table FundTransaction columns
    public static final String KEY_FUNDID = "FundId";
    public static final String KEY_TRANSACTIONID = "TransactionId";

    // Trip table create statement
    private static final String CREATE_TABLE_TRIP =
            "CREATE TABLE " + TABLE_TRIP + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT, "
                    + KEY_DESCRIPTION + " TEXT, "
                    + KEY_STARTTIME + " TEXT, "
                    + KEY_ENDTIME + " TEXT, "
                    + KEY_GATHERINGPLACE + " TEXT, "
                    + KEY_STATUS + " INT, "
                    + KEY_LEADERID + " INTEGER"
                    + ")";

    // TripPlace table create statement
    private static final String CREATE_TABLE_TRIPPLACE =
            "CREATE TABLE " + TABLE_TRIPPLACE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_TRIPID + " INTEGER, "
                    + KEY_PLACEID + " INTEGER"
                    + ")";

    // Place table create statement
    private static final String CREATE_TABLE_PLACE =
            "CREATE TABLE " + TABLE_PLACE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT, "
                    + KEY_ADDRESS + " TEXT, "
                    + KEY_IMAGE_PATH + " INTEGER, "
                    + KEY_DESCRIPTION + " TEXT, "
                    + KEY_LATITUDE + " REAL, "
                    + KEY_LONGITUDE + " REAL"
                    + ")";

    // District table create statement
    private static final String CREATE_TABLE_DISTRICT =
            "CREATE TABLE " + TABLE_DISTRICT + "("
                    + KEY_ID + " INTEGER PRIMARY KEY, "
                    + KEY_NAME + " TEXT, "
                    + KEY_CITYID + " INTEGER"
                    + ")";

    // City table create statement
    private static final String CREATE_TABLE_CITY =
            "CREATE TABLE " + TABLE_CITY + "("
                    + KEY_ID + " INTEGER PRIMARY KEY, "
                    + KEY_NAME + " TEXT"
                    + ")";

    // TripPreparation table create statement
    private static final String CREATE_TABLE_TRIPPREPARATION =
            "CREATE TABLE " + TABLE_TRIPPREPARATION + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_TRIPID + " INTEGER, "
                    + KEY_PREPARATIONID + " INTEGER, "
                    + KEY_DESCRIPTION + " TEXT"
                    + ")";

    // Preparation table create statement
    private static final String CREATE_TABLE_PREPARATION =
            "CREATE TABLE " + TABLE_PREPARATION + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_MEMBERID + " INTEGER, "
                    + KEY_OBJECT + " TEXT"
                    + ")";

    // Account table create statement
    private static final String CREATE_TABLE_ACCOUNT =
            "CREATE TABLE " + TABLE_ACCOUNT + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_EMAIL + " TEXT, "
                    + KEY_PASSWORD + " TEXT, "
                    + KEY_PHONE + " TEXT, "
                    + KEY_AVATAR + " TEXT, "
                    + KEY_FULLNAME + " TEXT, "
                    + KEY_ROLEID + " INTEGER, "
                    + KEY_ISACTIVE + " INTEGER "
                    + ")";

    // Notification table create statement
    private static final String CREATE_TABLE_NOTIFICATION =
            "CREATE TABLE " + TABLE_NOTIFICATION + "( "
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_RECEIVERID + " INTEGER, "
                    + KEY_SENDERID + " INTEGER, "
                    + KEY_TYPE + " INTEGER, "
                    + KEY_MESSAGE + " TEXT, "
                    + KEY_ISREADED + " INTEGER, "
                    + ")";

    // Fund table create statement
    private static final String CREATE_TABLE_FUND =
            "CREATE TABLE " + TABLE_FUND + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_TOTAL + " INTEGER, "
                    + KEY_MANAGERID + " INTEGER, "
                    + KEY_TRIPID + " INTEGER"
                    + ")";

    // Transaction table create statement
    private static final String CREATE_TABLE_TRANSACTION =
            "CREATE TABLE " + TABLE_TRANSACTION + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_AMOUNT + " INTEGER, "
                    + KEY_TYPE + " INTEGER, "
                    + KEY_DESCRIPTION + " TEXT"
                    + ")";

    // FundTransaction table create statement
    private static final String CREATE_TABLE_FUNDTRANSACTION =
            "CREATE TABLE " + TABLE_FUNDTRANSACTION + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_FUNDID + " INTEGER, "
                    + KEY_TRANSACTIONID + " INTEGER"
                    + ")";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_TRIP);
        db.execSQL(CREATE_TABLE_TRIPPLACE);
        db.execSQL(CREATE_TABLE_PLACE);
        db.execSQL(CREATE_TABLE_DISTRICT);
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_TRIPPREPARATION);
        db.execSQL(CREATE_TABLE_PREPARATION);
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
        db.execSQL(CREATE_TABLE_FUND);
        db.execSQL(CREATE_TABLE_TRANSACTION);
        db.execSQL(CREATE_TABLE_FUNDTRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPPLACE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPPREPARATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREPARATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUNDTRANSACTION);

        // create new tables
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}

