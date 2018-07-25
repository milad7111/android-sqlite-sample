package com.sample.foo.sqliteexample;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import room.AppDatabase;
import room.classes.Person;
import room.dao.PersonDAO;

public class MainActivity extends AppCompatActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    private ListView listView;
    private ArrayAdapter mPersonsArrayAdapter;
    // ExampleDBHelper dbHelper;
    AppDatabase database;
    public static PersonDAO mPersonDAO;
    List<Person> dbPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.addNew);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateOrEditActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, -1);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(this);
        mPersonDAO = database.getPersonDAO();
        dbPersons = mPersonDAO.getPersons();
        ArrayList<String> mArrayList_Names = new ArrayList<>();

        for (int i = 0; i < dbPersons.size(); i++) {
            mArrayList_Names.add(dbPersons.get(i).getName());
        }

        mPersonsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mArrayList_Names);

//        dbHelper = new ExampleDBHelper(this);

//        final Cursor cursor = dbHelper.getAllPersons();
//        String[] columns = new String[]{
//                ExampleDBHelper.PERSON_COLUMN_ID,
//                ExampleDBHelper.PERSON_COLUMN_NAME
//        };
//        int[] widgets = new int[]{
//                R.id.personID,
//                R.id.personName
//        };

//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.person_info,
//                cursor, columns, widgets, 0);
        listView = findViewById(R.id.listView1);
        listView.setAdapter(mPersonsArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
//                Cursor itemCursor = (Cursor) MainActivity.this.listView.getItemAtPosition(position);
//                int personID = itemCursor.getInt(itemCursor.getColumnIndex(ExampleDBHelper.PERSON_COLUMN_ID));
                Intent intent = new Intent(getApplicationContext(), CreateOrEditActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, position);
                startActivity(intent);
            }
        });
    }
}