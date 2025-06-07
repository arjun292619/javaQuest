package nestedClasses.burger;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private class Item {

        private String name;
        private String type;
        private double price;

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        private Item(String name, String type) {
            this(name, type, type.equalsIgnoreCase("burger") ? base : 0);
        }

        @Override
        public String toString() {
            return "%10s %15s $%.2f".formatted(type, name, getprice(price, conversionRate));
        }

        private static double getprice(double price, double rate) {
            return price * rate;
        }
    }

    private final double base = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        this.burger = new Burger("regular");
        this.drink = new Item("coke", "drink", 1.5);
//        System.out.println(drink.name);
        this.side = new Item("fries", "side", 2.0);
    }

    public double getTotal() {
        double total = burger.getPrice() + drink.price + side.price;
        return Item.getprice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%27s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
    }

    public void addToppings(String... selectedToppings) {
        burger.addToppings(selectedToppings);
    }

    private class Burger extends Item {

        private enum Extra {
            AVACADO, BACON, CHEESE, KETCHUP, MAYO, PICKLES;

            private double getPrice() {
                return switch (this) {
                    case AVACADO -> 1.0;
                    case BACON, CHEESE -> 1.50;
                    default -> 0;
                };
            }
        }

        private List<Item> toppings = new ArrayList<>();

        Burger(String name) {
            super(name, "burger", 5.0);
        }

        public double getPrice() {
            double sum = super.price;
            for (Item t : toppings) {
                sum += t.price;
            }
            return sum;
        }

        private void addToppings(String... selectedToppings) {
            for (String t : selectedToppings) {
                try {
                    Extra topping = Extra.valueOf(t.toUpperCase());
                    toppings.add(new Item(topping.name(), "TOPPING",
                            topping.getPrice()));
                } catch (IllegalArgumentException ie) {
                    System.out.println("No Topping found for " + t);
                    System.out.println("-".repeat(50));
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sbItem = new StringBuilder(super.toString());
            for (Item t : toppings) {
                sbItem.append("\n");
                sbItem.append(t.toString());
            }
            return sbItem.toString();
        }
    }
}
