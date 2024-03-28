package ui;

import model.Ingredients;
import model.Recipe;
import model.RecipeBook;
import model.RecipeConvertor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScalePanel extends JPanel implements ActionListener {

    private JTextField recipeNameField;
    private JTextField recipeNameField2;
    private JTextField portionField;
    private JTextField ingredientField;
    private JTextField amountField;
    private JPanel prevPanel;
    private JFrame mainFrame;
    private RecipeBook book;
    private Recipe recipe1;
    private Recipe recipe2;
    private JButton submit1;
    private JButton submit2;
    private JButton home;
    private String recipeName;
    private String recipeName1;
    private Double multiplyBy;
    private Double amount;
    private String ingredientName;
    private Ingredients ingredient;
    private RecipeConvertor converter;
    private GridBagConstraints constraints;
    private GridBagConstraints end;
    private GridBagLayout grid;

    public ScalePanel(RecipeBook book, JPanel prevPanel, JFrame mainFrame) {
        this.prevPanel = prevPanel;
        this.mainFrame = mainFrame;
        this.book = book;
        this.setLayout(new FlowLayout());
        this.setSize(700,700);
        this.setBackground(new Color(190,200,250));
        //adding panel for first scale option
        JPanel option1 = new JPanel();

        ;
        option1.setSize(400, 200);
        option1.setLayout(grid = new GridBagLayout());

        end = new GridBagConstraints();
        constraints = new GridBagConstraints();
        JLabel optionLabel1 = new JLabel();
        optionLabel1.setText("1. scale by number");

        option1.add(optionLabel1);
        submit1 = new JButton("submit");
        option1.add(submit1);
        submit2 = new JButton("submit");
        submit1.addActionListener(this);
        submit2.addActionListener(this);

        JLabel recipeNameLabel = new JLabel("Recipe Name:");
        recipeNameField = new JTextField();
        JLabel portionLabel = new JLabel("multiply by (i.e. 2) :");
        portionField = new JTextField();
        constraints.gridwidth = GridBagConstraints.BOTH;
        end.gridwidth = GridBagConstraints.REMAINDER;

        grid.setConstraints(submit1, end);
        grid.setConstraints(submit2, end);
        grid.setConstraints(recipeNameLabel, constraints);
        grid.setConstraints(recipeNameField, end);
        grid.setConstraints(portionLabel, constraints);
        grid.setConstraints(portionField, end);
        option1.add(recipeNameLabel);
        option1.add(recipeNameField);
        option1.add(portionLabel);
        option1.add(portionField);

        //adding panel for second scaling option
        JLabel recipeNameLabel2 = new JLabel("Recipe Name:");
        recipeNameField2 = new JTextField();
        JPanel option2 = new JPanel();
        option2.setLayout(grid);
        option2.setSize(400, 200);
        option2.add(submit2);
        JLabel optionLabel2 = new JLabel();
        optionLabel2.setText("2. scale by ingredient ");
        option2.add(optionLabel2);
        //text box for inputs needed to scale
        JLabel ingredientLabel = new JLabel("ingredient name: ");
        ingredientField = new JTextField();
        JLabel amountLabel = new JLabel("ingredient amount: ");
        amountField = new JTextField();
        grid.setConstraints(recipeNameLabel2, constraints);
        grid.setConstraints(recipeNameField2, end);
        grid.setConstraints(ingredientLabel, constraints);
        grid.setConstraints(ingredientField, end);
        grid.setConstraints(amountLabel, constraints);
        grid.setConstraints(amountField, end);

        option2.add(optionLabel2);
        option2.add(recipeNameLabel2);
        option2.add(recipeNameField2);

        option2.add(ingredientLabel);
        option2.add(ingredientField);
        option2.add(amountLabel);
        option2.add(amountField);

//
//
        home = new JButton("Home");
        home.addActionListener(this);
        this.add(option1, BorderLayout.CENTER);
        this.add(option2, BorderLayout.CENTER);
        add(home);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit1) {
            System.out.println("recipe scaled by option 1");
            recipeName = recipeNameField.getText();
            recipe1 = book.findRecipe(recipeName);
            multiplyBy = Double.parseDouble(portionField.getText());
            converter = new RecipeConvertor(recipe1);
            book.addRecipe(converter.scaleBasedOnNum(recipe1,multiplyBy));

        } else if (e.getSource() == submit2) {
            System.out.println("recipe scaled by option 2");
            recipeName1 = recipeNameField2.getText();
            recipe2 = book.findRecipe(recipeName1);
            amount = Double.parseDouble(amountField.getText());
            ingredientName = ingredientField.getText();
            ingredient = recipe2.findIngredient(ingredientName);
            converter = new RecipeConvertor(recipe2);
            book.addRecipe(converter.scaleBasedOnIngredient(recipe2, ingredient, amount));
            System.out.println("scaled by option 2");
        } else {
            mainFrame.setContentPane(prevPanel);
        }
    }


}
