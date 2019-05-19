package com.szberko.learn.collections;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    @Test
    void givenEmptyArrayList_whenAddTheContentOfAnotherList_ThenTheInitialArrayListWillAcceptIt(){
        List<Long> arrayList = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

        LongStream.range(4, 10).boxed()
                .collect(Collectors.collectingAndThen(toCollection(ArrayList::new), ls -> arrayList.addAll(0, ls)));

        assertThat(arrayList, equalTo(Arrays.asList(4L, 5L, 6L, 7L, 8L, 9L, 1L, 2L, 3L)));
    }

    @Test
    void givenPreLoadedArrayList_whenListIteratorPreviousCalled_ThenTraversingInReverseOrder(){
        List<Integer> listOfIntegers = new ArrayList<>(IntStream.range(0, 10).boxed().collect(toList()));
        ListIterator<Integer> listIterator = listOfIntegers.listIterator(listOfIntegers.size());

        List<Integer> result = new ArrayList<>();
        while (listIterator.hasPrevious()){
            result.add(listIterator.previous());
        }
        Collections.reverse(listOfIntegers);
        assertThat(result, equalTo(listOfIntegers));
    }

    @Test
    void givenPreLoadedArrayList_whenRemoveElementsByCertainCriteria_thenTheItemsWhichMatchedTheCriteriaWillBeRemoved(){
        List<Integer> listOfIntegers = IntStream.range(0, 10).boxed().collect(toList());

        listOfIntegers.removeIf(integer -> integer < 5);

        assertThat(listOfIntegers, equalTo(Arrays.asList(5, 6, 7, 8, 9)));
    }

    @Test
    void givenPreLoadedUnmodifiableList_whenAddingNewElement_thenThrowsUnsupportedOperation(){
        List<Double> listOfDoubles = DoubleStream.generate(Math::random).boxed().limit(5).collect(Collectors.toUnmodifiableList());

        assertThrows(UnsupportedOperationException.class, () -> listOfDoubles.add(0D));
    }
}
