package ui;

import model.Ingredients;

import javax.swing.*;
import java.awt.*;

public class IngredientPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel unitLabel;
    private JTextField nameField;
    private JTextField amountField;
    private JTextField unitField;
    private GridBagConstraints constraints;
    private GridBagLayout grid;

    public IngredientPanel() {
        setLayout(new GridBagLayout());

        grid = new GridBagLayout();
        constraints = new GridBagConstraints();
        this.setBackground(new Color(240,190,255));
        nameLabel = new JLabel("name of Ingredient: ");
        amountLabel = new JLabel("amount (i.e. 20): ");
        unitLabel = new JLabel("unit (i.e. g)");
        nameField = new JTextField();
        amountField = new JTextField();
        unitField = new JTextField();

        setConstraints();

        add(nameLabel);
        add(nameField);
        add(amountLabel);
        add(amountField);
        add(unitLabel);
        add(unitField);
        this.setVisible(true);
    }

    public Ingredients getIngredient() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String unit = unitField.getText();
        return new Ingredients(name, amount, unit);
    }

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
