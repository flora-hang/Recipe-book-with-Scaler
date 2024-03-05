package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// representing an ingredient, with a name, the amount, and in what unit
public class Ingredients implements Writable {

    String name;
    double amount;
    String unit;

    //EFFECT: constructs an ingredient with a name, an amount, and a unit
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

    //MODIFIES: this
    //EFFECTS: change ingredient name to given name
    public void changeName(String newName) {
        this.name = newName;
    }

    //MODIFIES: this
    //EFFECTS: change ingredient amount to given amount
    public void changeAmount(double newAmount) {
        this.amount = newAmount;
    }

    //MODIFIES: this
    //EFFECTS: change ingredient unit to given unit
    public void changeUnit(String newUnit) {
        this.unit = newUnit;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        json.put("unit", unit);
        return json;
    }

}
