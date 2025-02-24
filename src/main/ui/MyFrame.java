package ui;

import model.Event;
import model.EventLog;
import model.Ingredients;
import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;


// create a home page

public class MyFrame extends JFrame implements ActionListener {

    private JButton b1;// view recipe
    private JButton b2;// add recipe
    private JButton b3;// scale recipe
    private JButton b4;// save recipe
    private JButton b5;// load recipe
    private ViewPanel viewPanel;
    private AddRecipePanel addRecipePanel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/workroom.json";
    private ScalePanel scalePanel;
    private RecipeBook book;
    private EventLog events;

    private JPanel panel2;

    // MODIFIES: this
    // EFFECTS: initiate the home page
    public MyFrame() {

        init();
        book = new RecipeBook("My RecipeBook");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        events = events.getInstance();
        this.setLayout(new FlowLayout());
        this.setTitle("Recipe Collection");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event e : events) {
                    System.out.println(e.toString() + "\n");
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });

        this.setSize(700,700);

        this.getContentPane().setBackground(new Color(200,190,255));

        //this.setContentPane(frame);
    }

    // MODIFIES: this
    // EFFECTS: b1 leads to the viewPanel where recipes are displayed; b2 leads to the add ingredient panel;
    // b3 leads to the scale panel to scale recipes; b4 is load recipe; b5 is save recipe
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            viewPanel = new ViewPanel(book, panel2, this);
            //add(viewPanel);
            this.setContentPane(viewPanel);
        } else if (e.getSource() == b2) {
            addRecipePanel = new AddRecipePanel(book, panel2, this);
            this.setContentPane(addRecipePanel);


        } else if (e.getSource() == b3) {
            scalePanel = new ScalePanel(book, panel2, this);
            this.setContentPane(scalePanel);
            System.out.println("add recipie");
        } else if (e.getSource() == b4) {
            read();
        } else {
            write();
        }
    }

    //MODIFIES: this
    //EFFECTS: read the json file and add the recipes to the recipe bok
    public void read() {
        try {
            book = jsonReader.read();
            System.out.println("Loaded " + book.getName() + " from " + JSON_STORE);
        } catch (IOException f) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: save the recipes in the current recipe book to json file
    public void write() {
        try {
            jsonWriter.open();
            jsonWriter.write(book);
            jsonWriter.close();
            System.out.println("Saved " + book.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException f) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: add imageIcon to all buttons
    public void init() {

        setUpButtons();

        ImageIcon scale = new ImageIcon(getClass().getResource("scale.png"));
        ImageIcon add = new ImageIcon(getClass().getResource("add.png"));
        ImageIcon book = new ImageIcon(getClass().getResource("book.jpeg"));
        ImageIcon save = new ImageIcon(getClass().getResource("load.jpeg"));
        ImageIcon load = new ImageIcon(getClass().getResource("loadReal.jpeg"));

        b5.setIcon(save);
        b4.setIcon(load);
        b3.setIcon(scale);
        b2.setIcon(add);
        b1.setIcon(book);

        addThingstoPanel();
    }

    // MODIFIES: this
    // EFFECTS: set up the buttons
    public void setUpButtons() {
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();

        b1.setText("view recipes");
        b2.setText("add recipe");
        b3.setText("scale recipe");
        b4.setText("load recipe");
        b5.setText("save recipe");

        b1.setPreferredSize(new Dimension(300,120));
        b2.setPreferredSize(new Dimension(300,120));
        b3.setPreferredSize(new Dimension(300,120));
        b4.setPreferredSize(new Dimension(300,120));
        b5.setPreferredSize(new Dimension(300,120));

    }

    // MODIFIES: this
    // EFFECTS: add buttons to the panel, and a event listener to all buttons
    public void addThingstoPanel() {
        panel2 = new JPanel();
        panel2.setBackground(new Color(170,230,10));
        panel2.setPreferredSize(new Dimension(590, 640));
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        panel2.add(b4);
        panel2.add(b5);
        this.add(panel2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        this.setVisible(true);
    }
}
