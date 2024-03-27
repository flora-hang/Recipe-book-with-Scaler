package ui;

import model.Ingredients;
import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MyFrame extends JFrame implements ActionListener {

    private JButton b1;// view recipe
    private JButton b2;// add recipe
    private JButton b3;// scale recipe
    private JButton b4;// save recipe
    private JButton b5;// load recipe
    private ViewPanel viewPanel;
    private AddRecipePanel addRecipePanel;

    private RecipeBook book;
    private Recipe recipe;
    private Ingredients ingredients;

    private JPanel panel2;

    public MyFrame() {

        init();
        book = new RecipeBook("My RecipeBook");

        this.setLayout(new FlowLayout());
        this.setTitle("Recipe Collection");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500,500);

        this.getContentPane().setBackground(new Color(200,190,255));

        //this.setContentPane(frame);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            viewPanel = new ViewPanel(book, panel2, this);
            //add(viewPanel);
            this.setContentPane(viewPanel);
        } else if (e.getSource() == b2) {
            addRecipePanel = new AddRecipePanel(book, panel2, this);
            this.setContentPane(addRecipePanel);


        } else {
            ScalePanel scalePanel = new ScalePanel(book);
            this.setContentPane(scalePanel);
            System.out.println("add recipie");
        }
    }

    public void init() {
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();

        b1.setText("view recipes");
        b2.setText("add recipe");
        b3.setText("scale recipe");

        b1.setPreferredSize(new Dimension(300,150));
        b2.setPreferredSize(new Dimension(300,150));
        b3.setPreferredSize(new Dimension(300,150));

        ImageIcon scale = new ImageIcon(getClass().getResource("scale.png"));
        ImageIcon add = new ImageIcon(getClass().getResource("add.png"));
        ImageIcon book = new ImageIcon(getClass().getResource("book.jpeg"));
//        ImageIcon image = new ImageIcon("images.jpeg");
//
        b3.setIcon(scale);
        b3.setIconTextGap(10);
        b2.setIcon(add);
        b1.setIcon(book);

//        this.add(b1);
//        this.add(b2);
//        this.add(b3);


        panel2 = new JPanel();
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b2);
        this.add(panel2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        this.setVisible(true);
    }


}
