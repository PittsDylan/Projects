package SteppingStones;

import java.util.Scanner;

/**
 * Class representing ingredients.
 *
 * @author Dylan Pitts
 */
public class Ingredient {

    private String nameOfIngredient;        //Name of the ingredient
    private String unitMeasurement;         //Type of measurment used
    private double ingredientAmount;        //Amount of the type used
    private int numberCaloriesPerType;      //Amount of calories per type used
    private double totalCalories;           //Total calories for an ingredient

    /**
     * Constructor initializing parameters.
     *
     * @param nameOfIngredient Name of the ingredient
     * @param unitMeasurement Type of measurement used
     * @param ingredientAmount Amount of the type used
     * @param numberCaloriesPerType Amount of calories per type
     * @param totalCalories Total calories for an ingredient
     */
    public Ingredient(String nameOfIngredient,
            String unitMeasurement, double ingredientAmount,
            int numberCaloriesPerType, double totalCalories) {
        this.nameOfIngredient = nameOfIngredient;
        this.unitMeasurement = unitMeasurement;
        this.ingredientAmount = ingredientAmount;
        this.numberCaloriesPerType = numberCaloriesPerType;
        this.totalCalories = totalCalories;
    }

    /**
     * default constructor initializes fields to 0 or blank space.
     */
    public Ingredient() {
        this.nameOfIngredient = " ";   //initilize name of ingredient to " "
        this.unitMeasurement = " ";   //initilize unit of measurment to " "
        this.ingredientAmount = 0;     //initilize ingredient amount to 0
        this.numberCaloriesPerType = 0;     //initilize calories per unit to 0
        this.totalCalories = 0.0;   //initilize total caloreis to 0.0
    }

    /**
     * return name of the ingredient.
     *
     * @return String value representing the name of the ingredient.
     */
    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    /**
     * return measurement type of the ingredient.
     *
     * @return String value representing the type of the ingredient.
     */
    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    /**
     * return measurement amount of the ingredient.
     *
     * @return double value representing the measurement amount for the
     * ingredient.
     */
    public double getIngredientAmount() {
        return ingredientAmount;
    }

    /**
     * return number of calories per the type for the ingredient.
     *
     * @return int value representing the number of calories per type for the
     * ingredient.
     * @see Ingredient#unitMeasurement
     */
    public int getNumberCaloriesPerType() {
        return numberCaloriesPerType;
    }

    /**
     * return total calories for the ingredient.
     *
     * @return double value representing the total calories of the ingredient.
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    /**
     * set the this.nameOfIngredient field to nameOfIngredient.
     *
     * @param nameOfIngredient name of Ingredient
     */
    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    /**
     * set the this.unitMeasurement field to unitMeasurement.
     *
     * @param unitMeasurement measurement Type
     */
    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    /**
     * set the this.ingredientAmount field to ingredientAmount.
     *
     * @param ingredientAmount measurement Amount
     */
    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    /**
     * set the this.numberCaloriesPerType field to numberCaloriesPerType.
     *
     * @param numberCaloriesPerType number of Calories Per Type
     * @see Ingredient#unitMeasurement
     */
    public void setNumberCaloriesPerType(int numberCaloriesPerType) {
        this.numberCaloriesPerType = numberCaloriesPerType;
    }

    /**
     * set the this.totalCalories field to totalCalories.
     *
     * @param totalCalories total Calories
     */
    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    /**
     * go through the steps of creating a new ingredient object will asking user
     * for all relevant fields.
     *
     * @return ingredient object
     * @see Ingredient
     * @see Ingredient#nameOfIngredient
     * @see Ingredient#unitMeasurement
     * @see Ingredient#ingredientAmount
     * @see Ingredient#numberCaloriesPerType
     * @see Ingredient#totalCalories
     * @param tempNameOfIngredient gets the name of ingredient from user
     */
    public Ingredient addIngredient(String tempNameOfIngredient) {

        String tempUnitMeasurement = " ";  //unit of measurment for ingredient
        double tempIngredientAmount = 0.0;  //amount for ingredient
        int tempNumberCaloriesPerType = 0;    //number of calories per measurement
        double tempTotalCalories = 0.0;  //total caloresi for ingredient
        boolean validating = true; //used to prevent loops from ending

        Scanner scnr = new Scanner(System.in);

        //user intruction
        System.out.println("\nFor " + tempNameOfIngredient + " Enter The");
        System.out.println("-----------------");

        //get type of measurement and assign it to tempUnitMeasurement variable
        //check for input error if caught display error and repeat.
        do {
            System.out.print("Unit of Measurement: ");
            tempUnitMeasurement = scnr.nextLine();

            //if user input wasnt blank            
            if (!tempUnitMeasurement.isBlank()) {
                validating = false;  //end loop
            } //if user input is blank            
            else {
                System.out.println("\n[Error: input cant be blank]\n");
            }
        } while (validating); //loop until end of validation

        validating = true;   //reset loop check state

        //get measurement type amount and pass it to tempIngredientAmount variable
        //check for input error if caught display error and repeat.
        do {
            System.out.print("number of " + tempUnitMeasurement + ": ");

            //get user input make sure its double
            if (scnr.hasNextDouble()) {
                tempIngredientAmount = scnr.nextDouble();
                validating = false;  //end loop
            } //if user input is not double print error and repeat loop
            else {
                System.out.println("\n[ERROR: " + tempUnitMeasurement
                        + " must be a number]\n");
            }
            scnr.nextLine();  //consume newline character 
        } while (validating); //loop until end of validation

        validating = true;   //reset loop check state

        //get calories per the type and pass it to tempNumberCaloriesPerType varaible 
        //check for input error if caught display error and repeat.       
        do {
            System.out.print("calories per " + tempUnitMeasurement + " (Whole numbers): ");

            //get user input make sure its int
            if (scnr.hasNextInt()) {
                tempNumberCaloriesPerType = scnr.nextInt();
                validating = false;  //end loop
            } //if user input is not int print error repeat loop
            else {
                System.out.println("\n[ERROR: Calories most be in whole numbers]\n");
            }
            scnr.nextLine();  //consume newline character
        } while (validating); //loop until end of validation

        //calulates the total calories for an ingredient.
        tempTotalCalories = tempIngredientAmount * tempNumberCaloriesPerType;

        //assign fields based on user input to the new ingredient object.
        Ingredient tempNewIngredient
                = new Ingredient(tempNameOfIngredient,
                        tempUnitMeasurement, tempIngredientAmount,
                        tempNumberCaloriesPerType,
                        tempTotalCalories);
        return tempNewIngredient;
    }

    /**
     * Print Ingredient.
     *
     * @return String concatenation of nameOfIngredient, unitMeasurement,
     * ingredientAmount, numberCaloriesPerType and totalCalories in a formatted
     * manner.
     * @see Ingredient#nameOfIngredient
     * @see Ingredient#unitMeasurement
     * @see Ingredient#ingredientAmount
     * @see Ingredient#numberCaloriesPerType
     * @see Ingredient#totalCalories
     */
    @Override
    public String toString() {
        return "\nIngredient: " + nameOfIngredient
                + "\n" + unitMeasurement + ": " + ingredientAmount
                + "\nCalories Per " + unitMeasurement + ": "
                + numberCaloriesPerType
                + "\ntotal Calories: " + totalCalories;
    }
}
