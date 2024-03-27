package ui;

import model.Ingredients;
import model.Recipe;
import model.RecipeBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ViewPanel extends JPanel implements ActionListener {

    private JPanel viewRecipeFrame;
    private JTable table;
    private DefaultTableModel model;
    private RecipeBook book;
    private JButton home;
    private MyFrame previous;
    private JPanel prevPanel;

    public ViewPanel(RecipeBook book, JPanel prevPanel, MyFrame previous) {

        this.prevPanel = prevPanel;
        this.previous = previous;
        home = new JButton("Home");
        home.addActionListener(this);
        add(home);
        this.book = book;

//        Recipe recipe1 = new Recipe("Pizza", 9, 50, "prepare dough");
//        Recipe recipe2 = new Recipe("Pasta", 9, 50, "prepare dough");
//        recipe1.addIngredient(new Ingredients("flour", 700, "g"));
//        recipe1.addIngredient(new Ingredients("water", 300, "g"));
//        recipe2.addIngredient(new Ingredients("flour", 700, "g"));
//        recipe2.addIngredient(new Ingredients("egg", 300, "g"));
//        book.addRecipe(recipe1);
//        book.addRecipe(recipe2);
        this.setLayout(new FlowLayout());

        this.setSize(500,500);

        this.setBackground(new Color(200,190,255));

        // Create table model with columns
        String[] columnNames = {"Name", "Portion", "Prep Time", "Ingredients", "Instructions"};
        model = new DefaultTableModel(columnNames, 0);


        // Populate table with recipe data
        for (Recipe recipe : book.getRecipes()) {
            model.addRow(new Object[]{recipe.getRecipeName(), recipe.getPortion(), recipe.getPrepTime(),
                    printIngredients(recipe.getIngredientList()), recipe.getInstruction()});
        }

        // Create JTable with the model
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        table.setRowHeight(200); //not changing row height
        //updateRowHeights();
        table.setSize(300, 400);
//        this.add(table);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(150); // Ingredients
        columnModel.getColumn(3).setCellRenderer(new TableCellRenderer() {
            JTextArea textArea = new JTextArea();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                textArea.setText((String) value);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setEditable(false); // if you don't want it to be editable
                textArea.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5)); // Some padding

                if (isSelected) {
                    textArea.setBackground(table.getSelectionBackground());
                    textArea.setForeground(table.getSelectionForeground());
                } else {
                    textArea.setBackground(table.getBackground());
                    textArea.setForeground(table.getForeground());
                }

                return textArea;
            }
        });
        table.setVisible(true);
        this.setVisible(true);
    }

    //EFFECTS: print out ingredient list for table
    public String printIngredients(List<Ingredients> ingredientsList) {
        String allIngredients = "";
        for (Ingredients i : ingredientsList) {
            allIngredients = allIngredients + i.getName() + " " + i.getAmount() + " " + i.getUnit() + "\n";
        }
        return allIngredients;
    }

//    // EFFECTS: find max height for every row by going through the columns,
//    // and set the row height to the max value
//    public void updateRowHeights() {
//        for (int row = 0; row < table.getRowCount(); row++) {
//            int rowHeight = table.getRowHeight();
//
//            for (int column = 0; column < table.getColumnCount(); column++) {
//                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
//                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
//            }
//
//            table.setRowHeight(row, rowHeight);
//        }
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            previous.setContentPane(prevPanel);
        }
    }
}
