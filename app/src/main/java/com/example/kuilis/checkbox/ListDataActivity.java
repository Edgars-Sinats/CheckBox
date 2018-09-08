package com.example.kuilis.checkbox;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
//        editText = (EditText) findViewById(R.id.editText);
//        btnAdd = (Button) findViewById(R.id.btnAdd);
//        btnViewData = (Button) findViewById(R.id.btnView);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }
        private void populateListView() {
            Log.d(TAG, "populateListView: Displaying data in the ListView.");

            //get the data and append to a list
            Cursor data = mDatabaseHelper.getData();
            ArrayList<String> listData = new ArrayList<>();
            while(data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(1));
            }
            //create the list adapter and set the adapter
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

            //set an onItemClickListener to the ListView
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = adapterView.getItemAtPosition(i).toString();
                    Log.d(TAG, "onItemClick: You Clicked on " + name);

                    Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                    }
                    if(itemID > -1){
                        Log.d(TAG, "onItemClick: The ID is: " + itemID);
                        Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                        editScreenIntent.putExtra("id",itemID);
                        editScreenIntent.putExtra("name",name);
                        startActivity(editScreenIntent);
                    }
                    else{
                        toastMessage("No ID associated with that name");
                    }
                }
            });
        }
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newEntry = editText.getText().toString();
//                if (editText.length() != 0) {
//                    AddData(newEntry);
//                    editText.setText("");
//                } else {
//                    toastMessage("You must put something in the text field!");
//                }
//
//            }
//        });
//
//        btnViewData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
//                startActivity(intent);
//            }
//        });



    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



}
