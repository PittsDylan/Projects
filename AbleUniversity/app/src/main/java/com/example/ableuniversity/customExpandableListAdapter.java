package com.example.ableuniversity;

import android.content.*;

import android.graphics.Typeface;

import android.view.*;

import android.widget.*;

import java.util.*;

/**
 * Last Update: 7/9/2022
 * Description: Custom expandable list adapter.
 * Purpose: Controls what happens when an item on the expandable list is selected.
 */
public class customExpandableListAdapter extends BaseExpandableListAdapter {

    //Items passed to expandable list adapter
    private Context context;                        //interface to global information about application environment.
    private Map<String, List<String>> programMenu;  //Hash map of child items in the expandable list.
    private Map<String, List<String>> parallelMap;  //Hash map of descriptions of child items.
    private List<String> groupList;                 //Group items

    /**
     * Description: Constructor for custom expandable list adapter.
     * Purpose: Initialize the custom expandable list adapter.
     * @param context Allows access to application-specific resources and classes.
     * @param groupList Contains the first level items on the expandable list.
     * @param programMenu Contains the second level items on the expandable list.
     * @param parallelMap Contains description of second level items on the expandable list.
     */
    public customExpandableListAdapter(Context context, List<String> groupList, Map<String,
            List<String>> programMenu, Map<String, List<String>> parallelMap) {

        //Initialize items
        this.context = context;
        this.programMenu = programMenu;
        this.groupList = groupList;
        this.parallelMap = parallelMap;
    }

    /**
     * Description: Get size of programMenu.
     * Purpose: Get the number of groups within programMenu.
     * @return The number of key value pairs in programMenu.
     */
    @Override
    public int getGroupCount() {
        return programMenu.size();
    }

    /**
     * Description: Get the number of children in a group.
     * Purpose: Find out how many children are in a group.
     * @param i Index position within the group item.
     * @return Return how many children are within the selected group.
     */
    @Override
    public int getChildrenCount(int i) {
        return programMenu.get(groupList.get(i)).size();
    }

    /**
     * Description: Gets data associated with the given group.
     * Purpose: To find a group and return all associated children.
     * @param i Index position within the group item.
     * @return Return data within the selected group.
     */
    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    /**
     * Description: Get child item within a group.
     * Purpose: To find a child item in a group and return its data.
     * @param i Index position within the group item.
     * @param i1 Index position of child item within the group item.
     * @return Return child item.
     */
    @Override
    public Object getChild(int i, int i1) {
        return programMenu.get(groupList.get(i)).get(i1);
    }

    /**
     * Description: Gets ID for the group item at the given position.
     * Purpose: Find the index number of a group item.
     * @param i Index position within the group item.
     * @return Index position of a group item.
     */
    @Override
    public long getGroupId(int i) {
        return i;
    }

    /**
     * Description: Gets ID for the child item within the given group item.
     * Purpose: Find the index number of a child item.
     * @param i Index position within the group item.
     * @param i1 Index position of child item within the group item.
     * @return Index position of a child item.
     */
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    /**
     * Description: Indicates whether the child and group IDs are stable across changes to underlying data.
     * Purpose: Checks that IDs are always referring to the same object.
     * @return Returns true to indicate that data refers to the same object.
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Description: Gets a View that displays the given group.
     * Purpose: Displaying group items on UI.
     * @param i Index position within the group item.
     * @param b Is the expandable menu expanded true or false.
     * @param view Contains old view.
     * @param viewGroup The parent view that will be attached.
     * @return Return the view corresponding to the group item position.
     */
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        //Get the group items name that was selected from UI by the user as a string.
        String programName = getGroup(i).toString();

        //if the old view is not null.
        if (view == null) {
            //Build view objects from XML layout group_item.
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_item, null);
        }
        //Get the group_text UI component and assign it to Textview item.
        TextView item = view.findViewById(R.id.group_text);

        //Apply the bold typeface to UI component.
        item.setTypeface(null, Typeface.BOLD);

        //Set UI component text to the group item name.
        item.setText(programName);

        //Return view with changes.
        return view;
    }

    /**
     * Description: Gets A View that display the data for the given child item within the given group item.
     * Purpose: Display child items for a group item when group item is selected.
     * @param i Index position within the group item.
     * @param i1 Index position of child item within the group item.
     * @param b Is the child item the last child within a group true or false.
     * @param view Contains old view.
     * @param viewGroup The parent view that will be attached.
     * @return Return the view corresponding to the child item position.
     */
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        //Get item child items name that was selected from UI by the user as a string.
        String model = getChild(i, i1).toString();

        //if the old view is not null.
        if (view == null) {
            //Build view objects from XML layout child_text.
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        //Get the child_text UI component and assign it to Textview item.
        TextView item = view.findViewById(R.id.child_text);

        //Set UI component text to the child item name.
        item.setText(model);

        //Set variable equal to child items name.
        final String itemText = item.getText().toString();

        //Set variable equal to child items description.
        final String itemDesc = parallelMap.get(groupList.get(i)).get(i1);

        //When child item is clicked
        item.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Set what happens when a child item is clicked.
             * Purpose: When a child item is clicked start new activity and pass child items name and
             * description as variables to the new activity.
             * @param view The view withing the adapter that was clicked.
             */
            @Override
            public void onClick(View view) {

                 //Initialize ActivityProgramDepartment Class as an Activity.
                 Intent intent = new Intent(context, ActivityProgramDepartment.class);

                 //Pass items as variables to the ActivityProgramDepartment Activity.
                 intent.putExtra("key" + " 1", itemText);           //Child Items name.
                 intent.putExtra("key" + " 2", itemDesc);           //Child Items Description.

                 //Start ActivityProgramDepartment Activity.
                 context.startActivity(intent);
            }
        });
        //Return view with changes.
        return view;
    }

    /**
     * Description: Indicates whether a child item at an position is selectable.
     * Purpose: If a child item is selected by a user an action will occur.
     * @param i Index position within the group item.
     * @param i1 Index position of child item within the group item.
     * @return Return true if selectable.
     */
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
