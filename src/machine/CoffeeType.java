package machine;

public enum CoffeeType {
    ESPRESSO(250,
            0,
            16,
            4),

    LATTE(350,
            75,
            20,
            7),

    CAPPUCCINO(200,
            100,
            12,
            6);


    final int waterPerCup;
    final int milkPerCup;
    final int beansPerCup;
    final int pricePerCup;


    CoffeeType(int waterPerCup, int milkPerCup, int beansPerCup, int pricePerCup) {
        this.waterPerCup = waterPerCup;
        this.milkPerCup = milkPerCup;
        this.beansPerCup = beansPerCup;
        this.pricePerCup = pricePerCup;
    }
}