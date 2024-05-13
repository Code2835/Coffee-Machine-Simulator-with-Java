package machine;

import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {

    static int water = 0;
    static int milk = 0;
    static int coffeeBeans = 0;
    static int disposableCups = 0;
    static int money = 0;

    static CoffeeType coffeeType;


    static void machineSetup(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        CoffeeMachine.water = water;
        CoffeeMachine.milk = milk;
        CoffeeMachine.coffeeBeans = coffeeBeans;
        CoffeeMachine.disposableCups = disposableCups;
        CoffeeMachine.money = money;
    }


    static void machineStatus() {
        System.out.println("\n--------------------------------------");

        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """, CoffeeMachine.water,
                CoffeeMachine.milk,
                CoffeeMachine.coffeeBeans,
                CoffeeMachine.disposableCups,
                CoffeeMachine.money);
    }


    static void calculateIngredients(CoffeeType type) {
        int[] ingredientsQuantity = {type.waterPerCup, type.milkPerCup, type.beansPerCup, type.pricePerCup};
        int[] machineCapacity = {water, milk, coffeeBeans, money};
        String[] ingredientNames = {"water", "milk", "beans", "money"};

        for (int i = 0; i <= 3; i++) {
            if (machineCapacity[i] - ingredientsQuantity[i] < 0) {
                System.out.printf("Sorry, not enough %s!", ingredientNames[i]);
                return; // stops executing code in this method
            }
        }

        water -= type.waterPerCup;
        milk -= type.milkPerCup;
        coffeeBeans -= type.beansPerCup;
        money += type.pricePerCup;

        disposableCups -= 1;
    }


    private static int getCapacity(CoffeeType type) {
        int capacityWater = water / type.waterPerCup;
        int capacityMilk = milk / type.milkPerCup;
        int capacityCoffeeBeans = coffeeBeans / type.beansPerCup;
        int capacityPrice = money / type.pricePerCup;

        int[] findCapacity = {capacityWater, capacityMilk,
                capacityCoffeeBeans, capacityPrice};

        int capacity = Integer.MAX_VALUE;

        for (int i : findCapacity) {
            capacity = Math.min(capacity, i);
        }

        System.out.println("\n--------------------------------------");

//        if (cups > capacity) {
//            System.out.printf("No, I can make only %d cup(s) of coffee%n", capacity);
//        } else if (cups < capacity) {
//            System.out.printf("Yes, I can make that amount of coffee " +
//                    "(and even %d more than that)%n", capacity - cups);
//        } else {
//            System.out.println("Yes, I can make that amount of coffee");
//        }


        return capacity;
    }


    static void menu() {
        System.out.println("\n--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String action = null;

        while (!Objects.equals(action, "exit")) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");

            action = scanner.next();

            switch (action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    machineStatus();
                    break;
                default:
                    break;
            }
        }
    }


    static void buy() {
        System.out.println("\n--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String choose;

        do {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            choose = scanner.next();

            switch (choose) {
                case "1":
                    coffeeType = CoffeeType.ESPRESSO;
                    break;
                case "2":
                    coffeeType = CoffeeType.LATTE;
                    break;
                case "3":
                    coffeeType = CoffeeType.CAPPUCCINO;
                    break;
                case "back":
                    return;
//                    break;
                default:
                    System.out.println("wrong coffee");
                    break;
            }
        } while (!choose.equals("1") && !choose.equals("2") && !choose.equals("3") && !choose.equals("back"));


        calculateIngredients(coffeeType);
    }


    static void fill() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--------------------------------------");

        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += scanner.nextInt();
    }


    static void take() {
        System.out.printf("%nI gave you $%d", money);
        money = 0;
    }


    public static void main(String[] args) {
        machineSetup(400, 540, 120, 9, 550);
        menu();
    }
}
