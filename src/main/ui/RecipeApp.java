package ui;


import model.Ingredients;
import model.Recipe;
import model.RecipeBook;
import model.RecipeConvertor;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

//representing a Recipe App
public class RecipeApp {

    private RecipeBook book;
    private Recipe recipe;
    private Scanner input;
    private RecipeConvertor convertor;
    private Scanner scanner;

    // EFFECTS: runs the teller application
    public RecipeApp() {
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRecipe() {
        boolean keepGoing = true;
        int command = 0;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 4) {
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECT: take user input to scale the recipe
    private void scaleRecipe() {

        scanner = new Scanner(System.in);

        System.out.println("please enter name of recipe: ");
        String recipeName = scanner.nextLine();

        convertor = new RecipeConvertor(book.findRecipe(recipeName));
        System.out.println("Would you like to scale by limited ingredient(1) or by number(2)? ");
        int method = scanner.nextInt();

        if (method == 1) {
            System.out.println("Please enter ingredient name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter ingredient amount: ");
            double amount = scanner.nextInt();
            convertor.scaleBasedOnIngredient(recipe.findIngredient(name), amount);

        } else {
            System.out.println("Please enter the number you want to scale your recipe by: ");
            int n = scanner.nextInt();
            convertor.scaleBasedOnNum(n);
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        book = new RecipeBook("first Collection");
        recipe = new Recipe("Best Croissant", 10, 90);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 ->  view recipes");
        System.out.println("\t2 -> add recipe to book");
        System.out.println("\t3 -> scale recipe");
        System.out.println("\t4 -> quit");
    }

    //EFFECT: display a list of recipes with their name
    private void viewRecipes() {

        System.out.println("CATALOGUE: \n");
        for (Recipe recipe : book.getRecipes()) {

            System.out.println(recipe.getRecipeName());

        }

        viewIndividualRecipe();

    }

    private void viewIndividualRecipe() {
        scanner = new Scanner(System.in);
        System.out.println("please enter name of the recipe to view individual recipes (not case sensitive): ");
        String name = scanner.nextLine();
        recipe = book.findRecipe(name);
        if (recipe == null) {
            System.out.println("The recipe you searched for does not exist, add it yourself! ");
            runRecipe();
        }
        System.out.println(recipe.getRecipeName());
        System.out.println("Portions: " + recipe.getPortion());
        System.out.println("Time Needed: " + recipe.getPrepTime());
        System.out.println("Ingredients needed: \n");
        printIngredients(recipe);


    }

    //EFFECT: print out ingredient list
    private void printIngredients(Recipe recipe) {

        ArrayList<Ingredients> list = recipe.getIngredientList();
        for (Ingredients ingredient : list) {
            System.out.println(ingredient.getName() + "  "
                    + ingredient.getAmount() + ingredient.getUnit());
        }
    }

    //MODIFIES: book
    //EFFECT: take inputs for recipe specification and add recipe to recipe book
    private void addRecipeToBook() {
        scanner = new Scanner(System.in);

        System.out.println("Please enter name of recipe: ");
        String recipeName = scanner.nextLine();

        System.out.println("Please enter portion of recipe (in number): ");
        int portion = scanner.nextInt();

        System.out.println("Please enter prep time of recipe (in minutes): ");
        int time = scanner.nextInt();

        System.out.println("Please enter instruction: ");
        String text = scanner.nextLine();
        recipe.addInstruction(text);

        addToIngredientList();
        book.addRecipe(recipeName, portion, time);

    }

    //EFFECT: accept inputs for ingredients, if done is typed, then stop.
    // If consistent == no, then as the unit question every time, otherwise, don't as unit question repeatedly
    private void addToIngredientList() {

        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        boolean consistent = false;
        String unit;
        while (!isDone) {
            if ((scanner.nextLine() == "done")) {
                isDone = true;
            } else if (consistent == false) {

                System.out.println("Please enter name of one ingredient: ");
                String name = scanner.nextLine();

                System.out.println("Please enter amount of ingredient: ");
                double amount = scanner.nextInt();

                System.out.println("Please enter unit of ingredient: ");
                String unit2 = scanner.nextLine();

                System.out.println("Is this unit consistent throughout the recipe (yes or no) ? ");
                String consist = scanner.nextLine();
                if (consist.equals("yes")) {
                    consistent = true;
                }
                System.out.println("if you are done with inputting, "
                        + "type done as answer to the next question, otherwise, please answer as usual");

                recipe.addIngredient(new Ingredients(name, amount, unit2));
                unit = unit2;

            } else {
                System.out.println("Please enter name of one ingredient: ");
                String name1 = scanner.nextLine();

                System.out.println("Please enter amount of ingredient: ");
                double amount1 = scanner.nextInt();

                recipe.addIngredient(new Ingredients(name1, amount1, unit));

            }

        }

    }
}
