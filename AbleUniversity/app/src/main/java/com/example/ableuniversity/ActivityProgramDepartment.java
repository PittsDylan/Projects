package com.example.ableuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.*;

/**
 * Last Update: 7/9/2022
 * Description: Activity that runs when child item from expandable list in the Program Activity is selected.
 * Purpose: Display child item and description of child item that was selected from the group item.
 */
public class ActivityProgramDepartment extends AppCompatActivity {

    /**
     * Description: onCreate method for activity.
     * Purpose: start the activity.
     * @param savedInstanceState Preserves and restores activities UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_department);

        //UI Components
        TextView tv = (TextView) findViewById(R.id.textView);           //Child item text area
        TextView tv2 = (TextView) findViewById(R.id.textView2);         //Child item description text area
        final Button programButton = findViewById(R.id.programButton);  //Program button
        final Button homeButton = findViewById(R.id.home);              //Home button

        //Get extra values set within the customExpandableListAdapter
        Bundle extras = getIntent().getExtras();

        //if extra values exist
        if (extras != null) {

            //get extra values
            String value = extras.getString("key" + " 1");      //Child item
            String value2 = extras.getString("key" + " 2");     //Child item description

            //set extra values
            tv.setText(value);                                      //Apply to child item text area
            tv2.setText(value2);                                    //Apply to child item description text area
        }

        //if program button clicked
        programButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Built in on click method for setOnClickListener.
             * Purpose: When program button selected start Program Activity.
             * @param view Item that was clicked.
             */
            @Override
            public void onClick(View view) {
                //Start the Program Activity
                startActivity(new Intent(ActivityProgramDepartment.this, ProgramActivity.class));
            }
        });

        //if home button clicked
        homeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Built in on click method for setOnClickListener.
             * Purpose: When home button selected start Main Activity.
             * @param view Item that was clicked.
             */
            @Override
            public void onClick(View view) {
                //start the Main Activity
                startActivity(new Intent(ActivityProgramDepartment.this, MainActivity.class));
            }
        });
    }
}