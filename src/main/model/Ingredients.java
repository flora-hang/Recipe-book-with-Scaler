package model;


public class Ingredients {

    String name;
    int amount;
    String unit;

    public Ingredients(String name, int amount,String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    private void unitConvertor(String targetUnit) {

        if (targetUnit.equals("cups and spoon")) {
            //stub
        } else if (targetUnit.equals("grams")) {
            //stub
        } else {
            //stub
        }

    }

    private String getName() {
        return this.name;
    }

    private int getAmount() {
        return 0;
    }

    private String getUnit() {
        return this.unit;
    }


}
