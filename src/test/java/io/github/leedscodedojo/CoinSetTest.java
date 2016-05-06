package io.github.leedscodedojo;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.List;

import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CoinSetTest {
    @Test
    public void coins_for_2() {
        CoinSet coinSet = new CoinSet(asList(1, 2));
        List<Integer> result = coinSet.coinsForAmount(2);

        assertThat(result, is(singletonList(2)));
    }

    @Test
    public void coins_for_3() {
        CoinSet coinSet = new CoinSet(asList(1, 2));
        List<Integer> result = coinSet.coinsForAmount(3);

        assertThat(result, is(asList(2,1)));
    }

//    @Test
//    public void coins_for_3_when_impossible() {
//        CoinSet coinSet = new CoinSet(asList(2));
//        List<Integer> result = coinSet.coinsForAmount(3);
//
//        assertThat(result, is(asList(2,1)));
//    }

    @Test
    public void coins_for_8() {
        CoinSet coinSet = new CoinSet(asList(1, 2, 5, 10));
        List<Integer> result = coinSet.coinsForAmount(8);

        assertThat(result, is(asList(5,2,1)));
    }

    @Test
    public void coins_for_87() {
        CoinSet coinSet = new CoinSet(asList(1,2,5,10,20,50));
        List<Integer> result = coinSet.coinsForAmount(87);

        assertThat(result, is(asList(50,20,10,5,2)));
    }
    @Test
    public void Lilliputian_coins() {
        CoinSet coinSet = new CoinSet(asList(1,4,15,20,50));
        List<Integer> result = coinSet.coinsForAmount(32);

        assertThat(result, is(asList(15,15,1,1)));
    }

    @Test
    public void lots_of_coins_for_large_value() {
        CoinSet coinSet = new CoinSet(asList(1,5,50,123,2000,20000));
        System.out.println(format("{0}", coinSet.coinsForAmount(104729)));
    }
}