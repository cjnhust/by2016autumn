package sq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/10/3.
 */

public class SqSite extends SQLiteOpenHelper {

//    建立Province表
    public static final String PROVINCE="create table Province("
            + "id integer primary key autoincrement,"
            + "province_name text,"
            + "province_code text)";

    //    建立City表
    public static final String CITY="create table Province("
            + "id integer primary key autoincrement,"
            + "city_name text,"
            + "city_code text,"
            + "province_id integer)";

    //    建立County表
    public static final String COUNTY="create table Province("
            + "id integer primary key autoincrement,"
            + "county_name text,"
            + "county_code text"
            + "city_id integer)";
    public SqSite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sq) {
        sq.execSQL(PROVINCE);    //创建省份表
        sq.execSQL(CITY);    //创建城市表
        sq.execSQL(COUNTY);    //创建县城表
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
