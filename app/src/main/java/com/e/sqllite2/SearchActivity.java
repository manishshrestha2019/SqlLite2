package com.e.sqllite2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;

public class SearchActivity extends AppCompatActivity {
    private Button btnSearch;
    private EditText etSearch;
    private ListView listViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);
        listViewSearch = findViewById(R.id.listViewSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListWord();
            }
        });


    }
        private void ListWord(){
        final MyHelper myHelper=new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase=myHelper.getWritableDatabase();

        String word=etSearch.getText().toString();
        List<Word> wordList=new ArrayList<>();
        wordList=myHelper.GetWordByName(word,sqLiteDatabase);

        HashMap<String,String > hashMap=new HashMap<>();
        for(int i=0; i<wordList.size();i++){
            hashMap.put(wordList.get(i).getWord(),wordList.get(i).getMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,new ArrayList<String>(hashMap.keySet())
        );
        listViewSearch.setAdapter(stringArrayAdapter);
    }
}
