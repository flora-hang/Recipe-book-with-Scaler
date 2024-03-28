package ui;

import model.Ingredients;
import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRecipePanel extends JPanel implements ActionListener {
    private RecipeBook book;
    private JButton addIngredientButton;
    private GridBagLayout grid;
    private GridBagConstraints constraints;
    private JPanel panel1;
    private JTextField nameField;
    private JTextField portionField;
    private JTextField prepTimeField;
    private JTextField instructionsField;
    private JLabel nameLabel;
    private JLabel portionLabel;
    private JLabel prepTimeLabel;
    private JLabel instructionsLabel;
    private List<IngredientPanel> ingredientPanels;
    private JButton home;

    private JPanel prevPanel;
    private MyFrame previousFrame;

    // creates a panel to get all the info needed for a recipe to be added
    public AddRecipePanel(RecipeBook book, JPanel prevPanel,  MyFrame previousFrame) {

        init();
        this.prevPanel = prevPanel;
        this.previousFrame = previousFrame;
        //this.previousFrame.setVisible(false);
        this.book = book;
        ingredientPanels = new ArrayList<>();
        // Add Ingredients label
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        panel1.add(ingredientsLabel);

        // add ingredient button for adding more than one ingredients
        addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(this);
        grid.setConstraints(addIngredientButton, constraints);
        panel1.add(addIngredientButton);

        // Add initial ingredient panel
        addIngredientPanel();

        add(panel1);
        panel1.setVisible(true);
        this.setVisible(true);
    }

    public void init() {

        home = new JButton("Home");
        home.addActionListener(this);
        add(home);

        panel1 = new JPanel();
        grid = new GridBagLayout();
        panel1.setLayout(grid);
        this.setLayout(new FlowLayout());
        this.setSize(700, 700);
        // Create labels and text fields for each input
        setUpLabel();

        makeGrid();

        // Create a button to submit the form
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255,0,0));
        submitButton.addActionListener(this);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(submitButton, constraints);
        panel1.add(submitButton);
        addThingsToPanel1();
        this.setBackground(new Color(200,190,255));
        this.add(panel1);
        this.setVisible(true);
    }

    public void addThingsToPanel1() {

        panel1.add(nameLabel);
        panel1.add(nameField);
        panel1.add(portionLabel);
        panel1.add(portionField);
        panel1.add(prepTimeLabel);
        panel1.add(prepTimeField);
        panel1.add(instructionsLabel);
        panel1.add(instructionsField);
    }

    public void setUpLabel() {
        nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        portionLabel = new JLabel("Portion:");
        portionField = new JTextField();
        prepTimeLabel = new JLabel("Preparation Time (min):");
        prepTimeField = new JTextField();
        instructionsLabel = new JLabel("Instructions:");
        instructionsField = new JTextField();
    }

    public void makeGrid() {


        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.0;
        grid.setConstraints(nameLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(nameField, constraints);
        constraints.fill = GridBagConstraints.BOTH;
        grid.setConstraints(portionLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(portionField, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        grid.setConstraints(prepTimeLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(prepTimeField, constraints);

        constraints.gridwidth = GridBagConstraints.BOTH;
        grid.setConstraints(instructionsLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(instructionsField, constraints);



    }

    //EFFECTS: Method to add a new ingredient panel
    public void addIngredientPanel() {
        IngredientPanel ingredientPanel = new IngredientPanel();
        ingredientPanels.add(ingredientPanel);
        grid.setConstraints(ingredientPanel, constraints);
        panel1.add(ingredientPanel);
        revalidate();
    }

    // Method to create Recipe object from user input
    public void createRecipe() {
        String name = nameField.getText();
        Double portion = Double.parseDouble(portionField.getText());
        int prepTime = Integer.parseInt(prepTimeField.getText());
        String instructions = instructionsField.getText();
//        IngredientPanel ingredientPanel = new IngredientPanel();
//        ingredientPanel.getIngredient();
        List<Ingredients> ingredients = new ArrayList<>();
        for (IngredientPanel panel : ingredientPanels) {
            ingredients.add(panel.getIngredient());
        }

        Recipe recipe = new Recipe(name, portion, prepTime, instructions);
        for (Ingredients i : ingredients) {
            recipe.addIngredient(i);
        }
        // adding recipe to recipeBook
        book.addRecipe(recipe);

        System.out.println("recipe added");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            //previousFrame.setVisible(true);

            previousFrame.setContentPane(prevPanel);

        } else if (e.getSource() == addIngredientButton) {
            addIngredientPanel();

        } else {
            createRecipe();
            System.out.println("ran create recipe");
        }
    }
}
