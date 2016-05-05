package io.github.leedscodedojo;

import org.junit.Test;

import java.util.List;

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
}