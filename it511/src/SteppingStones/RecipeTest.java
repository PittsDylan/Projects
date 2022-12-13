package SteppingStones;

import java.util.ArrayList;

/**
 * Test Case for program
 * 
 * @author Dylan Pitts
 */
public class RecipeTest {

    /**
     * create two recipes with ingredients add those recipes to a recipe list
     * print recipe names, details, and servings.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Start of test case");
        
        //create a new list of recipes
        RecipeBox myRecipeBox = new RecipeBox();
        
        //Create recipe objects 
        Recipe myFirstRecipe = new Recipe();

        //Create list of ingredients
        ArrayList<Ingredient> recipeIngredientsTwo = new ArrayList();

        //Initilize ingreident
        String ingredientNameTwo = "Noodles";
        Ingredient tempIngredientFive
                = new Ingredient(ingredientNameTwo, "cups", 1, 100, 100);

        //Add ingredient
        recipeIngredientsTwo.add(tempIngredientFive);

        //Initilize recipe
        myFirstRecipe.setRecipeName("Ramen");
        myFirstRecipe.setServings(1);
        myFirstRecipe.setRecipeIngredients(recipeIngredientsTwo);
        myFirstRecipe.setTotalRecipeCalories(150);

        //recalculate total recipe calories
        myFirstRecipe.recalcTotalCalories();
        
        //add recipe to recipe list
        myRecipeBox.addRecipe(myFirstRecipe);

        //Create list of ingredients
        ArrayList<Ingredient> recipeIngredients = new ArrayList();

        //Create and initilize recipe
        Recipe mySecondRecipe = new Recipe("Pizza", 5, recipeIngredients, 300);

        //Create ingredients add them to recipe
        Ingredient tempIngredientTwo
                = new Ingredient("Anchovies", "pounds", 2.25, 12, 27);
        recipeIngredients.add(tempIngredientTwo);

        Ingredient tempIngredientThree
                = new Ingredient("Cheese", "pounds", 12, 120, 1440);
        recipeIngredients.add(tempIngredientThree);

        Ingredient tempIngredientFour
                = new Ingredient("Tomato sauce", "ounces", 2, 50, 100);
        recipeIngredients.add(tempIngredientFour);

        //recalculate total recipe calories
        mySecondRecipe.recalcTotalCalories();
        
        //add recipe to recipe list
        myRecipeBox.addRecipe(mySecondRecipe);
        
        //print recipe names
        System.out.println("[Recipe Names]\n");
        myRecipeBox.printAllRecipeNames();
        
        //print recipe details
        System.out.println("[Recipe Details]");
        myRecipeBox.printAllRecipeDetails(myFirstRecipe.getRecipeName());
        
        //print recipe servings
        System.out.println("\n[Recipe Servings]");
        myFirstRecipe.printServings();
        
        //print recipe details
        System.out.println("[Recipe Details]");
        myRecipeBox.printAllRecipeDetails(myFirstRecipe.getRecipeName());
        
        //print recipe servings
        System.out.println("\n[Recipe Servings]");
        mySecondRecipe.printServings();
        
        System.out.println("End of test case");             
    }
}
