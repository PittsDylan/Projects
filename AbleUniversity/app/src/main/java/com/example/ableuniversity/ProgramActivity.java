package com.example.ableuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.*;

import java.util.*;

/**
 * Last Update: 7/10/22
 * Description: Program Activity for the application.
 * Purpose: Control how UI elements are handled for the program Activity.
 */
public class ProgramActivity extends AppCompatActivity {

    //Initialize items.
    List<String> groupList;                        //Will indicate 1st level items on expanded menu
    List<String> childList;                        //Will indicate 2nd level items on expanded menu
    List<String> descChildList;                    //Will indicate description of 2nd level items
    Map<String, List<String>> programMenu;         //Will map child items to group items.
    Map<String, List<String>> parallelMap;         //Will map description of child items to group items.
    ExpandableListView expandableListView;         //Expandable list item.
    ExpandableListAdapter expandableListAdapter;   //Expandable list adapter.

    /**
     * Description: Built in method that runs on activity start up.
     * Purpose: Starts ProgramActivity.
     * @param savedInstanceState Preserves and restores activities UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        //Initialize UI components
        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        final Button programButton = findViewById(R.id.programButton);
        final Button homeButton = findViewById(R.id.home);

        //Run displayList method with empty string.
        displayList("");

        //When Search view is selected.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Description: Controls what happens when a user selects submit on the search view.
             * Purpose: Query user results and return expected results.
             * @param s Users intended query.
             * @return Accepts the change.
             */
            @Override
            public boolean onQueryTextSubmit(String s) {

                //Displays only items that match user query.
                displayList(s);

                //Expand all Group items. Used to show which child items match query.
                expandAll();

                //Close search view after query complete.
                searchView.clearFocus();

                //Allow Change.
                return true;
            }

            /**
             * Description: Controls what happens when a user types in search view.
             * Purpose: Control what happens when user deletes all results in search view.
             * Resets the expanded list showing all group and child items.
             */
            @Override
            public boolean onQueryTextChange(String s) {

                //if the user has no characters in the search view.
                if(s.equals("")){
                    //run the onQueryTextSubmit method with empty string as a result.
                    this.onQueryTextSubmit("");
                }
                //Accept change.
                return true;
            }
        });
        //When user selects on a child item within a group item from the expandable list.
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            /**
             * Description: What happens when a child item within the group item list is selected.
             * Purpose: Change what happens depending on the child item selected.
             */
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i, i1).toString();
                return true;
            }
        });
        //When the ProgramButton is clicked.
        programButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Control what happens programButton is clicked.
             * Purpose: Start the ProgramActivity.
             * @param view The view withing the adapter that was clicked.
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgramActivity.this, ProgramActivity.class));
            }
        });
        //When homeButton is clicked.
        homeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Description: Control what happens when homeButton is clicked.
             * Purpose: Return to the start up screen.
             * @param view The view withing the adapter that was clicked.
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgramActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * Description: Makes groupList, ChildList, and DescChildList.
     * Purpose: Control which item from the groupList, ChildList, and DescChildList is displayed
     * on the expandable list and passed the expandable list adapter.
     * @param query Users intended query.
     */
    private void makeList(String query) {

        //Initialize UI component expanded_menu.
        expandableListView = findViewById(R.id.expanded_menu);

        //Initialize groupList
        groupList = new ArrayList<>();

        //add group items to groupList.
        groupList.add("Math");
        groupList.add("Computer Science");
        groupList.add("English");
        groupList.add("Biology");

        //Initialize and set values for child items that will go into the expanded menu.
        String [] csList = {"General Computer Science", "Cybersecurity", "Software Engineering"};
        String [] mathList = {"Applied Mathematics", "Pure Mathematics", "Statistics"};
        String [] englishList = {"General English", "Creative Writing & Literature", "Linguistics"};
        String [] biologyList = {"General Biology", "Molecular & Cellular Biology", "Neuroscience"};

        //Initialize and set values for the descriptions of child items from the expanded menu.
        String csDesc1 = "AS(Associate of Science) in Computer Science\n\n" +
                "The concentration in Computer Science is designed to teach students skills and " +
                "ideas they will use immediately and in the future. Because information technology " +
                "affects every aspect of society, graduates with computer science degrees have open " +
                "to them an enormous variety of careers—engineering, teaching, medicine, law, basic " +
                "science, entertainment, management, and countless others.";

        String csDesc2 = "BS(Bachelor of Science) in Computer Science\n\n" +
                "Students enrolled in the Bachelors of Science program in Cybersecurity will " +
                "build a strong foundation in security, privacy, and cryptography. They will " +
                "acquire knowledge and skills to plan, manage, and maintain the security of an " +
                "organization’s cloud infrastructure and applications.";

        String csDesc3 = "MS(Master of Science) in Computer Science\n\n" +
                "Students enrolled in the Master of Science program in Software Engineering " +
                "will learn to apply systematic engineering principles to the design, construction, " +
                "and maintenance of large software applications.";

        String mathDesc1 = "AS(Associate of Science) in Mathematics\n\n" +
                "Applied mathematics is the mathematical study of general scientific concepts, " +
                "principles, and phenomena that, because of their widespread occurrence and " +
                "application, relate or unify various disciplines. The core of the program at " +
                "Able University concerns the following principles and their mathematical " +
                "formulations: propagation, equilibrium, stability, optimization, computation, " +
                "statistics, and random processes.";

        String mathDesc2 = "BS(Bachelor of Science) in Mathematics\n\n" +
                "Pure mathematics is the study of the basic concepts and structures that " +
                "underlie mathematics. Its purpose is to search for a deeper understanding and " +
                "an expanded knowledge of mathematics itself. Traditionally, pure mathematics" +
                " has been classified into three general fields: analysis, which deals with " +
                " continuous aspects of mathematics; algebra, which deals with discrete aspects; " +
                "and geometry. The undergraduate program is designed so that students become " +
                "familiar with each of these areas. Students may also wish to explore other " +
                "topics such as logic, number theory, complex analysis, " +
                "and subjects within applied mathematics.";

        String mathDesc3 = "MS(Master of Science) in Mathematics\n\n" +
                "Statistics is a relatively young discipline, organized around the rapidly growing " +
                "body of knowledge about principled methods for data collection and data analysis, " +
                "the making of rational decisions under uncertainty, and the modeling of randomness " +
                "in any quantitative inquiries, including the social, natural, and medical sciences. " +
                "A basic goal of the concentration in Statistics is to help students acquire the " +
                "conceptual, computational, and mathematical tools for quantifying uncertainty and" +
                "making sense of complex data arising from many applications – " +
                "including statistically sound ways of collecting such data.";

        String englishDesc1 = "AA(Associate of Arts) in English\n\n" +
                "Humans use stories to cope and thrive, from prehistoric cave paintings to " +
                "distilling experience in novels, screenplays, and hip hop rhymes. By studying " +
                "English literature, students learn how to analyze and appreciate the language of " +
                "the past and to contribute to the narrative of the future. Concentrators will " +
                "develop expertise in interpreting others’ rhetoric and learn to communicate " +
                "meaningfully.";

        String englishDesc2 = "AB(Bachelors of Arts) in English\n\n" +
                "Students enrolled in the Bachelors of Arts program in Creative Writing & " +
                "Literature will develop skills in creative writing and literary analysis through " +
                "literature courses and writing workshops in fiction, screenwriting, poetry, and " +
                "nonfiction. Through online group courses and one-on-one tutorials, as well as a " +
                "week on campus, students hone their craft and find their voice.";

        String englishDesc3 = "MA(Master of Arts) in English\n\n" +
                "The concentration in Linguistics, the scientific study of language, " +
                "emphasizes linguistic theory, historical linguistics, and the relationship " +
                "between language and cognition, and cuts across the humanities, social sciences, " +
                "cognitive sciences, physical sciences, and biological sciences.";

        String biologyDesc1 = "AS(Associate of Science) in Biology\n\n" +
                "Students enrolled in the Associates of Science program in Biology will learn " +
                "how to apply critical thinking to real-world scenarios in the life sciences while " +
                "exploring cutting-edge research and theory. Students gain deeper insight into a " +
                "range of biological fields, such as molecular biology, genetics, genomics, cell " +
                "biology, neurobiology and behavior, and ecology.";

        String biologyDesc2 = "BS(Bachelor of Science) in Biology\n\n" +
                "The Molecular and Cellular Biology concentration emphasizes the intersection " +
                "of modern research in cellular biology with medicine and society. MCB is ideally " +
                "suited for students who wish to study molecular and cellular processes at the " +
                "heart of both normal physiology and disease. MCB concentrators will acquire an " +
                "understanding of scientific logic and approaches as they explore a wide range " +
                "of contemporary subjects.";

        String biologyDesc3 = "MS(Master of Science) in Biology\n\n" +
                "Neuroscience, the study of the nervous system, is a field that investigates the " +
                "biological mechanisms that underlie behavior and how brains process information. " +
                "The study of neuroscience provides both a broad scientific training and a deep " +
                "understanding of the biology of the nervous system. Given the diversity of " +
                "interests in this field, the only prerequisite for students entering this " +
                "concentration is an intense curiosity about the brain.";

        //Parallel List of child item, described each child item.
        String [] csDescList = {csDesc1, csDesc2, csDesc3};
        String [] mathDescList = {mathDesc1, mathDesc2, mathDesc3};
        String [] englishDescList = {englishDesc1, englishDesc2, englishDesc3};
        String [] biologyDescList = {biologyDesc1, biologyDesc2, biologyDesc3};

        //Initialize child item and description of child items for expanded menu adapter.
        programMenu = new HashMap<String, List<String>>();
        parallelMap = new HashMap<String, List<String>>();

        //if the user didn't query anything
        if (query.isEmpty()) {
            //For each group item in the group list.
            for (String group : groupList) {

                //If that group item is equal to the string Math.
                if (group.equals("Math")) {
                    //Add Child item and its associated description to parallel Lists
                    loadAllChildren(mathList, mathDescList);

                //If that group item is equal to the string Computer Science
                } else if (group.equals("Computer Science")) {
                    //Add Child item and its associated description to parallel Lists
                    loadAllChildren(csList, csDescList);

                //If that group item is equal to the string English
                } else if (group.equals("English")) {
                    //Add Child item and its associated description to parallel Lists
                    loadAllChildren(englishList, englishDescList);

                //If that group item is equal to the string Biology
                } else if (group.equals("Biology")) {
                    //Add Child item and its associated description to parallel Lists
                    loadAllChildren(biologyList, biologyDescList);
                }
                //Add items to expanded menu adapter.
                programMenu.put(group, childList);
                parallelMap.put(group, descChildList);
            }
        //If the user passed a query through the search view.
        } else {
            //Make the query is lower case.
            query = query.toLowerCase();

            //For every group item
            for (String group : groupList) {
                //If the group item is the math item.
                if (group.equals("Math")) {
                    //Run a query over the math group item looking
                    //for matches among child items or their descriptions.
                    queryLists(mathList, mathDescList, query);

                //If the group item is the Computer Science item.
                } else if (group.equals("Computer Science")) {
                    //Run a query over the Computer Science group item looking
                    //for matches among child items or their descriptions.
                    queryLists(csList, csDescList, query);

                //If the group item is the English item.
                } else if (group.equals("English")) {
                    //Run a query over the English group item looking
                    //for matches among child items or their descriptions.
                    queryLists(englishList, englishDescList, query);

                //If the group item is the Biology item.
                } else if (group.equals("Biology")) {
                    //Run a query over the Biology group item looking
                    //for matches among child items or their descriptions.
                    queryLists(biologyList, biologyDescList, query);
                }
                //Add items to expanded menu adapter.
                programMenu.put(group, childList);
                parallelMap.put(group, descChildList);
            }
        }
    }

    /**
     * Description: Runs a query over child item and associated description.
     * Purpose: Find child items that match users query and only display matched items.
     * @param queryList1 Child item
     * @param queryList2 Description of Child item.
     * @param query User submitted filter.
     */
    private void queryLists(String [] queryList1, String [] queryList2, String query) {
        //Initialize tmp lists
        ArrayList <String> tmpList = new ArrayList<String>();
        ArrayList <String> tmpList2 = new ArrayList<String>();

        int i = 0;      //position of child item within loop.
        //For every child item
        for (String item : queryList1) {
            //If the child item or its description matches the users query.
            if (item.toLowerCase().contains(query) || queryList2[i].toLowerCase().contains(query)) {
                //Add child item and description to lists
                tmpList.add(item);
                tmpList2.add(queryList2[i]);
            }
            i++;    //next child item.
        }
        //Initialize new array with size equal to the amount of child items.
        String [] tmpArray = new String[tmpList.size()];
        //Convert child item list to array.
        tmpArray = tmpList.toArray(tmpArray);
        //Add child items to expanded list adapter(Display child items in programActivity).
        loadChild(tmpArray);
        //Convert Child item description list to array.
        tmpArray = tmpList2.toArray(tmpArray);
        //Add child item descriptions to expanded list adapter
        loadDescChild(tmpArray);
    }
    /**
     * Description: Display expanded list.
     * Purpose: Create and display expanded list.
     * @param query Users selected query may be empty if user hasn't sent a query.
     */
    private void displayList(String query){

        //Filter and make groupList, childList, and descChildList.
        makeList(query);

        //Add groupList, ChildList, descChildList to custom expandable list adapter and set it.
        //Allows UI to display elements from list.
        expandableListAdapter =
                new customExpandableListAdapter(this, groupList, programMenu, parallelMap);
        expandableListView.setAdapter(expandableListAdapter);
    }

    /**
     * Description: Expand all group items.
     * Purpose: Displays all the child items of every group item.
     */
    private void expandAll() {
        //Get the number of key value pairs within the expandable list adapter
        int count = expandableListAdapter.getGroupCount();

        //for every key value pair.
        for (int i = 0; i < count; i++) {
            //expand the key value pair.
            expandableListView.expandGroup(i);
        }
    }
    /**
     * Description: Add Items to associated list.
     * Purpose: Add Items to expanded list adapter.
     * @param childList 2nd level items in the expanded list adapter.
     * @param descChildList Description of 2nd level items from the expanded list adapter.
     */
    private void loadAllChildren(String [] childList, String[] descChildList) {
        //Add 2nd level items to expanded list adapter.
        loadChild(childList);
        //Add description of items to expanded list adapter.
        loadDescChild(descChildList);
    }
    /**
     * Description: Adds items to ChildList
     * Purpose: Add items to expanded list adapter.
     * @param childModels Names of 2nd level items from expanded menu.
     */
    private void loadChild(String[] childModels) {
        childList = new ArrayList<>();
        for (String model: childModels) {
            childList.add(model);
        }
    }
    /**
     * Description: Adds items to descChildList
     * Purpose: Add items to expanded list adapter.
     * @param childModels Description of 2nd level items from expanded menu.
     */
    private void loadDescChild(String[] childModels) {
        descChildList = new ArrayList<>();
        for (String model: childModels) {
            descChildList.add(model);
        }
    }
}