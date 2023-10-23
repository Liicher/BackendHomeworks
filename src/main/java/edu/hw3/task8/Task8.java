package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Реализуйте Iterator, который принимает коллекцию, но при этом двигается "назад".
 * То есть new BackwardIterator<>(List.of(1,2,3)) должен сначала вернуть 3, потом 2, а потом 1.
 */
public class Task8 {
    // Написал метод возвращающий "обратный" список с помощью BackwardIterator
    public <T> List<T> backwardIterator(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        Iterator<T> iterator = new BackwardIterator<>(list);
        List<T> outputList = new ArrayList<>();
        while (iterator.hasNext()) {
            outputList.add(iterator.next());
        }
        return outputList;
    }
}


