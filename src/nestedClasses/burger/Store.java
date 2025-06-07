package nestedClasses.burger;

public class Store {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        Meal regularmeal = new Meal();
        regularmeal.addToppings("Ketchup", "Mayo", "bacon", "cheddar", "cheese");
//        regularmeal.addToppings("Ketchup", "Mayo", "bacon");
        System.out.println(regularmeal);
        System.out.println("-".repeat(50));
        Meal mealInUSD = new Meal(0.68);
        System.out.println(mealInUSD);
        System.out.println("-".repeat(50));
    }
}
