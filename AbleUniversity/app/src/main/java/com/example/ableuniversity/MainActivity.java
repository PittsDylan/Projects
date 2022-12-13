package com.example.ableuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * Creator: Pitts, Dylan
 * Last Update: 7/9/22
 * Description: Main Activity of the Able University Application.
 * Purpose: Main menu of the application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Description: onCreate method for application.
     * Purpose: Start MainActivity.
     * @param savedInstanceState Preserves and restores activities UI state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get UI components
        final ListView listView = findViewById(R.id.listview);          //Main Menu
        final Button programButton = findViewById(R.id.programButton);  //Program Button
        final Button homeButton = findViewById(R.id.home);              //Home Button

        //Create new list for the main menu component
        List<String> list = new ArrayList<>();                          //Main Menu Items

        //add String text to the list
        list.add("Program");
        list.add("Classes");
        list.add("Register");
        list.add("Books");
        list.add("Student ID");
        list.add("Parking");
        list.add("Faculty");
        list.add("Jobs");
        list.add("About");

        //Add List to the UI Component (Displays Main Menu)
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        //When an Item from the List is clicked by the user
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Description: Built in onItemClick method for setOnItemClickListener.
             * Purpose: When an item is clicked start associated activity.
             * @param adapterView Determines which item from main menu was selected.
             * @param view The view withing the adapter that was clicked.
             * @param i Index position of selected view in adapter.
             * @param l Row ID of selected view.
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if the index position selected was zero (programs item from list)
                if (i == 0) {
                    //start the Program Activity
                    startActivity(new Intent(MainActivity.this, ProgramActivity.class));//
                } else {
                    //else if item selected wasn't program item do nothing
                    //[Other Items on the list are not implemented: Beyond Scope of Project].
                }
            }
        });

        //If program button was clicked
        programButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Built in on click method for setOnClickListener.
             * Purpose: When program button selected start Program Activity.
             * @param view Item that was clicked.
             */
            @Override
            public void onClick(View view) {
                //Start the Program Activity
                startActivity(new Intent(MainActivity.this, ProgramActivity.class));
            }
        });

        //if home button was clicked
        homeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Built in on click method for setOnClickListener.
             * Purpose: When home button selected start Main Activity.
             * @param view Item that was clicked.
             */
            @Override
            public void onClick(View view) {
                //start the Main Activity
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
