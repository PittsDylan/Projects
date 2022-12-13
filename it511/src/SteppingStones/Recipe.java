package SteppingStones;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class representing recipes.
 *
 * @author Dylan Pitts
 */
public class Recipe {

    private String recipeName;              //name of recipe
    private int servings;                   //how many people the recipe can feed
    private double totalRecipeCalories;     //total calories for the recipe

    //list of recipe names
    private ArrayList<Ingredient> recipeIngredients;

    //Getters and Setters
    //--------------------------------------------------------------------------
    /**
     * get recipe name from recipe class.
     *
     * @return String value representing recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * set recipe name of the recipe class.
     *
     * @param recipeName changes the name of a recipe in the recipe class
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * get amount of servings from recipe class.
     *
     * @return integer value representing recipe serving size
     */
    public int getServings() {
        return servings;
    }

    /**
     * set the serving size of the recipe class
     *
     * @param servings changes the serving size of a recipe in the recipe class
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * get total amount of calories for a recipe in a recipe class.
     *
     * @return double value representing total calories for a recipe in a recipe
     * class
     */
    public double getTotalRecipeCalories() {
        return totalRecipeCalories;
    }

    /**
     * set the total calories for a recipe in a recipe class.
     *
     * @param totalRecipeCalories changes the total calories for a recipe in a
     * recipe class
     */
    public void setTotalRecipeCalories(double totalRecipeCalories) {
        this.totalRecipeCalories = totalRecipeCalories;
    }

    /**
     * get a list of strings representing recipes names in the recipe class.
     *
     * @return list of string values representing recipe names in the recipe
     * class
     */
    public ArrayList<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * set names in a list of recipes in the recipe class.
     *
     * @param recipeIngredients changes the names in a list of recipes in the
     * recipe class
     */
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
    //--------------------------------------------------------------------------

    /**
     * default constructor for recipe class.
     *
     * initializes values to default values.
     */
    public Recipe() {
        this.recipeName = "";
        this.servings = 0;
        this.recipeIngredients = new ArrayList<>();
        this.totalRecipeCalories = 0;
    }

    /**
     * Constructor for recipe class.
     *
     * @param recipeName string value representing recipe names
     * @param servings integer value representing serving size
     * @param recipeIngredients list representing collection of recipe names
     * @param totalRecipeCalories double value representing total recipe
     * calories
     */
    public Recipe(String recipeName, int servings,
            ArrayList<Ingredient> recipeIngredients, double totalRecipeCalories) {
        this.recipeName = recipeName;
        this.servings = servings;
        this.recipeIngredients = recipeIngredients;
        this.totalRecipeCalories = totalRecipeCalories;
    }

    /**
     * prints recipes and ingredients. Calculates calories per serving for re
     * and prints it.
     *
     * @see Recipe
     * @see Recipe#totalRecipeCalories
     * @see Recipe#servings
     * @see Recipe#recipeName
     * @see Recipe#recipeIngredients
     */
    public void printRecipe() {

        //output recipe name, serving size, and ingredient names
        System.out.println("\n----------------------");
        System.out.println("Recipe: " + recipeName);
        System.out.println("Recipe Calories: " + totalRecipeCalories);
        System.out.println("Serves: " + servings);
        System.out.println("Ingredients:");

        for (int i = 0; i < recipeIngredients.size(); i++) {

            //outputs info for each ingredient in a recipe
            System.out.println(recipeIngredients.get(i).toString());
        }
    }

    /**
     * Calculates a recipes information by serving size and displays it to the
     * user
     *
     * @see recipeName
     * @see servings
     * @see totalRecipeCalories
     * @see recipeIngredients
     * @see Ingredient
     * @see Ingredient#getIngredientAmount()
     * @see Ingredient#getUnitMeasurement()
     * @see Ingredient#getNameOfIngredient()
     * @see Ingredient#getNumberCaloriesPerType()
     */
    public void printServings() {
        //print recipe name
        System.out.println("\n" + recipeName + " Servings:");

        String print;  //holds ingredient information

        //holds a list of print variables
        ArrayList<String> printArray = new ArrayList();

        //for every ingredient
        for (int i = 0; i < recipeIngredients.size(); i++) {

            //for every serving
            for (int j = 0; j < servings; j++) {

                //get the amount of ingredients for a serving
                //round it to the second decimal
                double perUnit = Math.round(recipeIngredients.get(i).getIngredientAmount()
                        / servings * (servings - j) * 100.0) / 100.0;

                /**
                * save the amount of ingredients per serving
                * and the ingredients unit of Measurement
                * and calculate the amount of calories per serving for an ingredient
                * rounded to the second decimal
                * in the string variable print.
                */
                print = perUnit + " "
                        + recipeIngredients.get(i).getUnitMeasurement()
                        + " of " + recipeIngredients.get(i).getNameOfIngredient()
                        + " and " + Math.round(perUnit
                                * recipeIngredients.get(i).getNumberCaloriesPerType()
                                * 100.0) / 100.0 + " Calories";

                //save print to the printArray list
                printArray.add(print);
            }
        }
        //if the amount of ingredients is greater than 1
        if (recipeIngredients.size() > 1) {

            //for every serving
            //i correspondes to individual the ingredients
            for (int i = 0; i < servings; i++) {

                //print the serving number and how much calories a recipe has 
                //for that serving rounded to the second decimal
                System.out.print((servings - i) + " servings " + "has "
                        + Math.round(totalRecipeCalories / servings * (servings - i)
                                * 100.0) / 100.0
                        + " Total Calories \n");

                //for every ingredient
                //j corresponds to all the ingredients in a serving
                for (int j = 0; j < recipeIngredients.size(); j++) {

                    //print recipe information by ingredients per serving
                    System.out.print("with " + printArray.get(j * servings + i) + "\n");
                }
                System.out.println();
            }
            System.out.println();
        } //if the amount of ingredients is less than 1
        else {
            //for every element in the printArray list
            for (int i = 0; i < printArray.size(); i++) {

                //print the ingredient per serving along with its calories per serving
                //rounded to the second decimal
                System.out.println((i + 1) + " servings " + "has "
                        + Math.round(totalRecipeCalories / (i + 1) * 100.0) / 100.0
                        + " total calories \n" + "with " + printArray.get(i) + "\n");
            }
        }
    }

    /**
     * calculates the total recipe calories
     *
     * @see totalRecipeCalories
     */
    public void recalcTotalCalories() {

        //reset total recipe calories to prepare for recalculation
        totalRecipeCalories = 0;

        //for all ingredients in a recipe
        for (int i = 0; i < recipeIngredients.size(); i++) {

            //add the total calories of an ingredient and add it to the 
            //total calories in a recipes rounded to the second decimal
            totalRecipeCalories
                    += Math.round(recipeIngredients.get(i).getTotalCalories()
                            * 100.0) / 100.0;
        }
    }

    /**
     * Creates a new recipe. Gets ingredients for the new recipe. Calculates
     * total calories for the recipe.
     *
     * @see Recipe#recipeIngredients
     * @see Recipe#recipeName
     * @see Recipe#servings
     * @see Recipe#totalRecipeCalories
     * @see Ingredient
     * @see Ingredient#addIngredient(java.lang.String)
     * @see Ingredient#totalCalories
     * @return an object representing the recipe
     */
    public Recipe createNewRecipe() {

        boolean addMoreIngredients = true;   //validates if a user wants to quit

        Scanner scnr = new Scanner(System.in);

        //gets recipe name from user
        System.out.print("\nPlease enter the recipe name: ");
        recipeName = scnr.nextLine();

        //gets number of servings for a recipe from user
        System.out.print("Please enter the number of servings: ");
        servings = scnr.nextInt();

        //consume newline character
        scnr.nextLine();
        
        //gets ingredients and there calories.
        //calculates total recipe calories.
        do {
            //gets ingredient name from user or allows them to quit
            System.out.print("\nPlease enter the ingredient name"
                    + " or end if you are finished: ");
            String ingredientName = scnr.nextLine();

            //if user enters end exit loop
            if (ingredientName.toLowerCase().equals("end")) {
                addMoreIngredients = false; //false will exit loop
            } //else user entered an ingredient name.
            //get ingredient amount and calories.
            else {
                //create new ingredient
                Ingredient tmpIngredient = new Ingredient().addIngredient(ingredientName);

                //add recipe name to recipe list             
                recipeIngredients.add(tmpIngredient);

                //calculates total recipe calories by multiplying ingredient calories
                //by ingredient amount and adding that to the previouse value.                     
                totalRecipeCalories += tmpIngredient.getTotalCalories();
            }
        } while (addMoreIngredients);  //if false end loop

        //create new recipe object recipe1 using user input.
        Recipe recipe1 = new Recipe(recipeName,
                servings, recipeIngredients, totalRecipeCalories);

        return recipe1;  //return recipe1 object
    }
}
