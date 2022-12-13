package SteppingStones;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a collection of recipes. 
 * 
 * This class acts as the Driver class for the program and guides the user through
 * creating new recipes and adds them to a collection of recipes.
 *
 * @author Dylan Pitts
 */
public class RecipeBox {

    private ArrayList<Recipe> listOfRecipes;    //list of recipes

    //Getters and Seters
    //--------------------------------------------------------------------------
    /**
     * get a list representing recipes from a recipe collection class
     *
     * @return a list representing recipes from a recipe collection class
     */
    public ArrayList<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

    /**
     * set the list of recipes for a recipe collection class
     *
     * @param listOfRecipes list of recipes from a recipe collection class
     */
    public void setListOfRecipes(ArrayList<Recipe> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }
    //--------------------------------------------------------------------------

    /**
     * Default constructor for recipe collection class
     *
     * Initializes list of recipes to default value of empty
     */
    public RecipeBox() {
        this.listOfRecipes = new ArrayList<>();
    }

    /**
     * Constructor for recipe collection class
     *
     * @param listOfRecipes a value representing a list of recipes
     */
    public RecipeBox(ArrayList<Recipe> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }

    /**
     * Prints all details for a selected recipe Calls getRecipe to get a recipe
     * from list of recipes using a recipe name
     *
     * @param selectedRecipeName value representing a recipe name
     * @see RecipeBox#getRecipe
     */
    public void printAllRecipeDetails(String selectedRecipeName) {

        //gets a recipe from the list of recipes that matches the selected recipe name
        Recipe selectedRecipe = getRecipe(selectedRecipeName);

        //if the recipe either has no match or the list of recipes is empty
        if (selectedRecipe != null) {
            selectedRecipe.printRecipe();   //print recipe details
        }//if selected recipe name found a match
    }

    /**
     * get recipe object using recipe name from list of recipes
     *
     * @param selectedRecipeName value representing a recipe name
     * @see RecipeBox
     * @see RecipeBox#listOfRecipes
     * @return recipe object from list of recipes
     */
    public Recipe getRecipe(String selectedRecipeName) {

        //if list of recipes is empty 
        if (listOfRecipes.isEmpty()) {
            System.out.println("Recipe Box is empty\n");
            return null; //return back to method call with null indicating failure
        }

        //for each recipe in the list of recipes
        for (Recipe recipe : listOfRecipes) {

            //if the recipe matches the selected recipe
            if (selectedRecipeName.equals(recipe.getRecipeName())) {
                return recipe; //return back to call with desired recipe
            }
        }
        //if no match was found in list of recipes for selected recipe
        System.out.println("Recipe does not exist!\n");
        return null;    //return back to method with a null indicating failure
    }

    /**
     * Prints all recipes names in the list of recipes
     * 
     * @see RecipeBox
     * @see RecipeBox#listOfRecipes
     */
    public void printAllRecipeNames() {

        //if list of recipes is empty
        if (listOfRecipes.isEmpty()) {
            System.out.println("Recipe Box is empty\n");
            return; //return back to method call
        }
        int i = 1; //incrementer

        //for each recipe in the list of recipes
        for (Recipe recipe : listOfRecipes) {
            //print the name of the recipe in the list of recipes
            System.out.println(i++ + ": " + recipe.getRecipeName());
        }
        System.out.println();
    }

    /**
     * Create a new recipe and add to recipe list
     *
     * @param tmpRecipe value representing a recipe
     * @see RecipeBox
     * @see RecipeBox#listOfRecipes
     */
    public void newRecipe(Recipe tmpRecipe) {
        tmpRecipe.createNewRecipe();    //create new recipe
        listOfRecipes.add(tmpRecipe);   //add new recipe to list of recipes
    }

    /**
     * Add existing recipe to recipe list
     *
     * @param tmpRecipe value representing a recipe
     * @see RecipeBox
     * @see RecipeBox#listOfRecipes
     */
    public void addRecipe(Recipe tmpRecipe) {
        listOfRecipes.add(tmpRecipe);   //add new recipe to list of recipes
    }

    /**
     * Drives the program.
     *
     * gets user input and runs selected options.
     *
     * @param args command line values
     * @see RecipeBox
     * @see RecipeBox#newRecipe(SteppingStones.Recipe) 
     * @see RecipeBox#printAllRecipeDetails(java.lang.String) 
     * @see RecipeBox#printAllRecipeNames() 
     * @see RecipeBox#getRecipe(java.lang.String) 
     * @see Recipe#printServings() 
     */
    public static void main(String[] args) {

        // Create a Recipe Box
        RecipeBox myRecipeBox = new RecipeBox();
        
        //create scanner
        Scanner menuScnr = new Scanner(System.in);

        /**
         * Print a menu for the user to select one of the three options:
         *
         */
        System.out.print("\nMenu\n"
                + "0. To quit\n"
                + "1. Add Recipe\n"
                + "2. Print All Recipe Details\n"
                + "3. Print All Recipe Names\n"
                + "4. Print All Recipe Servings\n"
                + "\nPlease select a menu item: ");

        //while program has input
        while (menuScnr.hasNextInt() || menuScnr.hasNextLine()) {

            //get user input
            var userInput = menuScnr.next();
            menuScnr.nextLine();    //consume newline

            int input; //used to make sure input is an int

            //test wheather input is an int
            try {
                //convert userInput into int
                input = Integer.parseInt(userInput);

                //switch to input from user
                switch (input) {
                    case 0: //if user input is 0
                        menuScnr.close();   //close scanner
                        System.exit(0);     //close program   
                        
                    case 1: //if user input is 1
                        Recipe tmpRecipe = new Recipe();    //create new recipe
                        myRecipeBox.newRecipe(tmpRecipe);   //add new recipe to recipe list
                        break;  //break this loop iteration
                        
                    case 2: //if user input is 2
                        //get recipe name
                        System.out.println("\nWhich recipe?");
                        String selectedRecipeName = menuScnr.nextLine();
                        //print details for selected recipe
                        myRecipeBox.printAllRecipeDetails(selectedRecipeName);
                        break;  //break this loop iteration
                        
                    case 3: //if user input is 3
                        //print all recipe names in the recipe list
                        myRecipeBox.printAllRecipeNames();
                        break; //break this loop iteration   
                        
                    case 4: //if user input is 4                   
                        //get recipe name
                        System.out.println("\nWhich recipe?");
                        selectedRecipeName = menuScnr.nextLine();
                        //get recipe
                        Recipe selectedRecipe = myRecipeBox.getRecipe(selectedRecipeName);
                        //if recipe was found
                        if (selectedRecipe != null) {
                            //print recipe servings
                            selectedRecipe.printServings();
                        }
                        break;  //break this loop iteration
                        
                    default: //if user input is none of the above
                        System.out.println("Input Error: please enter a number"
                                + " only from 0 to 4");
                        break;  //break this loop iteration
                        
                }//end of case
            }//if user input wasnt a number
            catch (NumberFormatException ex) {
                System.out.println("Input Error: please enter a number");
            }
            //redisplay input
            System.out.print("\nMenu\n"
                    + "0. To quit\n"
                    + "1. Add Recipe\n"
                    + "2. Print All Recipe Details\n"
                    + "3. Print All Recipe Names\n"
                    + "4. Print All Recipe Servings\n"
                    + "\nPlease select a menu item: ");
        }//end of loop
    }//end of main
}//end of class

