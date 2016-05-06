package io.github.leedscodedojo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.*;
import static java.text.MessageFormat.*;
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

    public List<Integer> coinsForAmount(int totalAmount) {
        List<Result> results = new ArrayList<>(singletonList(Result.zero));

        IntStream.rangeClosed(1, totalAmount).boxed()
                .map(amount -> calculateResultForAmount(amount, results))
                .forEach(results::add);

        return toCoinResult(results);
    }

    private Result calculateResultForAmount(int targetAmount, List<Result> results) {
        Optional<Result> bestPreviousResultOpt = coins.stream()
                .map(coin -> targetAmount - coin)
                .filter(x -> x >= 0)
                .map(results::get)
                .min((o1, o2) -> compare(o1.getNumberOfCoins(), o2.getNumberOfCoins()))
                ;

        if (!bestPreviousResultOpt.isPresent()) {
            throw new RuntimeException(format("No best result found for: {0}", Integer.toString(targetAmount)));
        }

        Result bestPreviousResult = bestPreviousResultOpt.get();

        int lastCoin = targetAmount - bestPreviousResult.getValue();
        return new Result(targetAmount,
                bestPreviousResult.getNumberOfCoins() + 1,
                lastCoin);
    }

    private List<Integer> toCoinResult(List<Result> results) {
        //printResults(results);

        List<Integer> coinsForSolution = new ArrayList<>();

        Result remainder = last(results);
        while (true) {
            int lastCoin = remainder.getLastCoin();
            coinsForSolution.add(lastCoin);
            remainder = results.get(remainder.getValue() - lastCoin);

            if (remainder == Result.zero) {
                return coinsForSolution.stream()
                        .sorted(Comparator.<Integer>reverseOrder())
                        .collect(Collectors.toList());
            }
        }
    }

    private void printResults(List<Result> results) {
        String text = results
                .stream()
                .map(Result::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(text);
    }

    private Result last(List<Result> results) {
        return results.get(results.size() - 1);
    }
}
