package ui;


import model.Ingredients;
import model.Recipe;
import model.RecipeBook;
import model.RecipeConvertor;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//representing a Recipe App
public class RecipeApp {

    private RecipeBook book;
    private Recipe recipe;
    private Scanner input;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    String unit;
    String name;
    double amount;
    String recipeName;
    String inputDone;
    double portion;
    int time;
    private static final String JSON_STORE = "./data/workroom.json";


    // EFFECTS: constructor that runs the teller application
    public RecipeApp() {
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRecipe() {
        boolean keepGoing = true;
        int command;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadWorkRoom();

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 6) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command, 1 is view recipe collection, 2 is add recipe to collection,
    // 3 is scale recipe, and 4 is to quit
    private void processCommand(int command) {
        if (command == (1)) {
            viewRecipes();
        } else if (command == 2) {
            addRecipeToBook();
        } else if (command == 3) {
            scaleRecipe();
        } else if (command == 4) {
            saveWorkRoom();
        } else if (command == 5) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECT: take user input to scale the recipe
    private void scaleRecipe() {

        scanner = new Scanner(System.in);

        System.out.println("please enter name of recipe: ");
        String recipeName = scanner.nextLine();

        Recipe recipe = book.findRecipe(recipeName);

        RecipeConvertor convertor;
        convertor = new RecipeConvertor(recipe);
        System.out.println("Would you like to scale by limited ingredient(1) or by number(2)? ");
        int method = scanner.nextInt();

        if (method == 1) {

            scanner = new Scanner(System.in);
            System.out.println("Please enter ingredient name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter ingredient amount: ");
            double amount = scanner.nextInt();
            book.addRecipe(convertor.scaleBasedOnIngredient(book.findRecipe(recipeName),
                    recipe.findIngredient(name), amount));


        } else {
            System.out.println("Please enter the number you want to scale your recipe by: ");
            int n = scanner.nextInt();
            book.addRecipe(convertor.scaleBasedOnNum(book.findRecipe(recipeName), n));
        }

    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        book = new RecipeBook("first Collection");
        recipe = new Recipe("Best Croissant", 10, 90, "instructions..");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 ->  view recipes");
        System.out.println("\t2 -> add recipe to book");
        System.out.println("\t3 -> scale recipe");
        System.out.println("\t4 -> save work room to file");
        System.out.println("\t5 -> load work room from file");
        System.out.println("\t6 -> quit");
    }

    //MODIFIES: this
    //EFFECT: display a list of recipes with their name
    private void viewRecipes() {

        System.out.println("CATALOGUE: \n");
        for (Recipe recipe : book.getRecipes()) {

            System.out.println(recipe.getRecipeName());

        }

        viewIndividualRecipe();

    }


    //EFFECTS: try to find individual recipe and print it out,
    // if not found print a message to let the user know the recipe does not exist
    private void viewIndividualRecipe() {
        scanner = new Scanner(System.in);
        System.out.println("please enter name of the recipe to view individual recipes (not case sensitive): ");
        String name = scanner.nextLine();
        recipe = book.findRecipe(name);
        if (recipe == null) {
            System.out.println("The recipe you searched for does not exist, add it yourself! ");

        } else {
            System.out.println(recipe.getRecipeName());
            System.out.println("Portions: " + recipe.getPortion());
            System.out.println("Time Needed: " + recipe.getPrepTime());
            System.out.println("Ingredients needed: \n");
            printIngredients(recipe);
            System.out.println();
            System.out.println(recipe.getInstruction());
        }
    }

    //MODIFIES: this
    //EFFECT: print out ingredient list
    private void printIngredients(Recipe recipe) {

        ArrayList<Ingredients> list = recipe.getIngredientList();
        for (Ingredients ingredient : list) {
            System.out.println(ingredient.getName() + ": " + ingredient.getAmount() + ingredient.getUnit());
        }

    }

    //MODIFIES: RecipeBook
    //EFFECT: take inputs for recipe specification and add recipe to recipe book
    private void addRecipeToBook() {
        scanner = new Scanner(System.in);

        System.out.println("Please enter name of recipe: ");
        recipeName = scanner.nextLine();

        System.out.println("Please enter portion of recipe (in number): ");
        portion = scanner.nextDouble();

        System.out.println("Please enter prep time of recipe (in minutes): ");
        time = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter instruction: ");
        String text = scanner.nextLine();

        inputDone = text;
        recipe = new Recipe(recipeName, portion, time, text);

        addToIngredientList();

        book.addRecipe(recipe);

        System.out.println("Recipe has been added!");
    }

    //REQUIRES: the user finishes inputting an ingredient before typing done,
    // there is at least one ingredient in the recipe
    //MODIFIES: Recipe
    //EFFECT: accept inputs for ingredients, if done is typed, then stop.
    // If consistent == no, then as the unit question every time, otherwise, don't as unit question repeatedly
    @SuppressWarnings("methodlength")
    private void addToIngredientList() {

        boolean isDone = false;
        boolean notWarn = true;
        boolean consistent = false;
        while (!isDone) {

            if ((!consistent) && !(inputDone.equals("done"))) {

                scanner = new Scanner(System.in);
                System.out.println("Please enter name of one ingredient: ");
                name = scanner.nextLine();

                if (name.equals("done")) {
                    break;
                }
                System.out.println("Please enter amount of ingredient: ");
                amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Please enter unit of ingredient: ");

                unit = scanner.nextLine();

                System.out.println("Is this unit consistent throughout the recipe (yes or no) ? ");
                String consist = scanner.nextLine();

                if (consist.equals("yes")) {
                    consistent = true;
                }
                if (consist.equals("done")) {
                    recipe.addIngredient(new Ingredients(name, amount, this.unit));
                    break;
                }
                if (notWarn) {
                    System.out.println("if you are done with inputting, "
                            + "type done as answer to this question, otherwise, "
                            + "please press enter and answer as usual");
                    notWarn = false;
                    inputDone = scanner.nextLine();
                }

                if (inputDone.equals("done")) {
                    isDone = true;
                }

                recipe.addIngredient(new Ingredients(name, amount, this.unit));

            } else if (!inputDone.equals("done")) {
                scanner = new Scanner(System.in);
                System.out.println("Please enter name of one ingredient: ");
                name = scanner.nextLine();
                String conti = name;

                if (conti.equals("done")) {
                    break;
                }
                System.out.println("Please enter amount of ingredient: ");
                amount = scanner.nextDouble();

                recipe.addIngredient(new Ingredients(name, amount, unit));
                //after second ingredient input the two questions appear together

            }

        }

    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(book);
            jsonWriter.close();
            System.out.println("Saved " + book.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            book = jsonReader.read();
            System.out.println("Loaded " + book.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
