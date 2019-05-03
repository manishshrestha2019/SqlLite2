package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.sqllite2.Word;

import java.util.ArrayList;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {

    private static final String databaseName="Dictionary DB";
    private static final int dbVersion=1;

    private static final String tblWord="tblWord";
    private static final String WordId="WordId";
    private static final String Word="Word";
    private static final String Meaning="Meaning";

    public MyHelper(Context context){
        super(context,databaseName,null,dbVersion);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+tblWord+" (" +WordId +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+Word+" TEXT, "+Meaning+" Text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long InsertData(String word,String meaning,SQLiteDatabase db){
        long id;
        ContentValues contentValues=new ContentValues();
        contentValues.put(Word,word);
        contentValues.put(Meaning,meaning);
        id=db.insert(tblWord,null,contentValues);
        return id;

    }

    public List<com.e.sqllite2.Word> GetAllWords(SQLiteDatabase db){
        List<Word> dictionaryList=new ArrayList<>();
        String[] columns={WordId,Word,Meaning};

        Cursor cursor =db.query(tblWord,columns,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                dictionaryList.add(new Word(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));

            }
        }
        return dictionaryList;

    }
    public List<Word> GetWordByName(String word, SQLiteDatabase db){
        List<Word> dictionaryList=new ArrayList<>();
        Cursor cursor =db.rawQuery("select * from tblWord where Word=?",new String[]{word});
        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                dictionaryList.add(new Word(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }
        }
        return dictionaryList;
    }
}

