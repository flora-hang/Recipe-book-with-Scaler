package ui;

import model.Ingredients;

import javax.swing.*;
import java.awt.*;

// create a sub panel for additional ingredients
public class IngredientPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel unitLabel;
    private JTextField nameField;
    private JTextField amountField;
    private JTextField unitField;
    private GridBagConstraints constraints;
    private GridBagLayout grid;

    // EFFECTS: initialize the new ingredients panel
    public IngredientPanel() {

        this.setSize(new Dimension(300, 150));
        grid = new GridBagLayout();
        setLayout(grid);
        constraints = new GridBagConstraints();
        this.setBackground(new Color(240,190,255));
        nameLabel = new JLabel("name of Ingredient: ");
        amountLabel = new JLabel("amount (i.e. 20): ");
        unitLabel = new JLabel("unit (i.e. g)");
        nameField = new JTextField(20);
        amountField = new JTextField(5);
        unitField = new JTextField(6);

        setConstraints();

        add(nameLabel);
        add(nameField);
        add(amountLabel);
        add(amountField);
        add(unitLabel);
        add(unitField);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: returns an Ingredients object from the fields
    public Ingredients getIngredient() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String unit = unitField.getText();
        return new Ingredients(name, amount, unit);
    }

    // MODIFIES: this
    // EFFECTS: set constraints for all the label and text fields
    public void setConstraints() {
        constraints.weightx = 1.0;
        constraints.gridwidth = GridBagConstraints.BOTH;
        grid.setConstraints(nameLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(nameField, constraints);
        constraints.gridwidth = GridBagConstraints.BOTH;
        grid.setConstraints(amountLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(amountField, constraints);
        constraints.gridwidth = GridBagConstraints.BOTH;
        grid.setConstraints(unitLabel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(unitField, constraints);
    }
}
