package model;

// representing an ingredient, with a name, the amount, and in what unit
public class Ingredients {

    String name;
    double amount;
    String unit;

    //EFFECT: constructs an ingredient
    public Ingredients(String name, double amount,String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }


    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeAmount(double newAmount) {
        this.amount = newAmount;
    }

    public void changeUnit(String newUnit) {
        this.unit = newUnit;
    }

}
