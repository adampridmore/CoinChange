package io.github.leedscodedojo;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.*;
import static java.util.Collections.singletonList;

public class CoinSet {
    private final List<Integer> coins;

    public CoinSet(Collection<Integer> coins) {
        this.coins = sortAndCopyCoins(coins);
    }

    private List<Integer> sortAndCopyCoins(Collection<Integer> coins) {
        return coins.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> coinsForAmount(int amount) {
        List<Result> results = new ArrayList<>(singletonList(Result.zero));

        IntStream.rangeClosed(1, amount).boxed()
                .map(value -> calculateResultForValue(value, results))
                .forEach(results::add);

        return toCoinResult(results);
    }

    private List<Integer> toCoinResult(List<Result> results) {
//        String text = results
//                .stream()
//                .map(Result::toString)
//                .collect(Collectors.joining(System.lineSeparator()));
//
//        System.out.println(text);

        List<Integer> coinsForSolution = new ArrayList<>();

        Result remainderResult = last(results);
        while (true) {
            int lastCoin = remainderResult.getLastCoin();
            coinsForSolution.add(lastCoin);
            remainderResult = results.get(remainderResult.getValue() - lastCoin);

            if (remainderResult == Result.zero) {
                return coinsForSolution.stream()
                        .sorted(Comparator.<Integer>reverseOrder())
                        .collect(Collectors.toList());
            }
        }
    }

    private Result last(List<Result> results) {
        return results.get(results.size() - 1);
    }

    private Result calculateResultForValue(int value, List<Result> results) {
        Optional<Result> bestPreviousResultOpt = coins.stream()
                .map(coin -> value - coin)
                .filter(v -> v >= 0)
                .map(v -> results.get(v))
                .min((o1, o2) -> compare(o1.getNumberOfCoins(), o2.getNumberOfCoins()))
                ;

        if (!bestPreviousResultOpt.isPresent()) {
            throw new RuntimeException("Bang");
        }

        Result bestPreviousResult = bestPreviousResultOpt.get();

        int lastCoin = value - bestPreviousResult.getValue();
        return new Result(value,
                bestPreviousResult.getNumberOfCoins() + 1,
                lastCoin);
    }
}
