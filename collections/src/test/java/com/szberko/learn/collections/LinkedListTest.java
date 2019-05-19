package com.szberko.learn.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    List emptyCollection = Collections.EMPTY_LIST;
    Set emptySet = Collections.EMPTY_SET;
    Map emptyMap = Collections.EMPTY_MAP;


    @Test
    void givenLinkedList_whenAddMultipleSameElement_ThenIShouldSeeTheSameElementMultipleTimes(){
        LinkedList<String> linkedList = new LinkedList<>();

        String elemFirst = "first";
        linkedList.add(elemFirst);
        linkedList.add(elemFirst);

        assertEquals(2, linkedList.size());
        assertEquals(elemFirst, linkedList.getFirst());
        assertEquals(elemFirst, linkedList.getLast());
    }

}