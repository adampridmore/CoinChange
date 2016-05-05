package io.github.leedscodedojo;

public class Result {
    static public final Result zero = new Result(0,0,-1);

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                ", numberOfCoins=" + numberOfCoins +
                ", lastCoin=" + lastCoin +
                '}';
    }

    private int value;
    private int numberOfCoins;
    private int lastCoin;

    public Result(int value, int numberOfCoins, int lastCoin) {
        this.value = value;
        this.numberOfCoins = numberOfCoins;
        this.lastCoin = lastCoin;
    }

    public int getValue() {
        return value;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public int getLastCoin() {
        return lastCoin;
    }
}
