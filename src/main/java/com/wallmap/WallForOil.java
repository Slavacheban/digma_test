package com.wallmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WallForOil {
    private Integer[] integers;

    public WallForOil(Integer[] integers) {
        this.integers = integers;
    }

    public Integer getTotalOil() {
        return getSumOil(initSortedListIndex(integers));
    }

    private  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private List<Integer> initSortedListIndex(Integer[] input) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            map.put(i, input[i]);
        }
        Map<Integer, Integer> sortedMap = sortByValue(map);
        LinkedList<Integer> listIndex = new LinkedList<>();
        for (Map.Entry<Integer, Integer> pair : sortedMap.entrySet()) {
            listIndex.addFirst(pair.getKey());
        }
        return listIndex;
    }

    private Integer getSumOil(List<Integer> list) {
        int sum = 0;
        LinkedList<Integer> leftList = new LinkedList<>();
        LinkedList<Integer> rightList = new LinkedList<>();

        leftList.add(list.get(0));
        rightList.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > rightList.getLast()) {
                rightList.add(list.get(i));
            } else if (list.get(i) < leftList.getLast()) {
                leftList.add(list.get(i));
            }
        }

        for (int i = 1; i < rightList.size(); i++) {
            sum += integers[rightList.get(i)] * (rightList.get(i) - rightList.get(i - 1) - 1);
        }
        for (int i = 1; i < leftList.size(); i++) {
            sum += integers[leftList.get(i)] * (leftList.get(i - 1) - leftList.get(i) - 1);
        }

        Integer outSum = getOutSumOil(list, leftList, rightList);
        return sum - outSum;
    }

    private Integer getOutSumOil(List<Integer> list, LinkedList<Integer> leftList, LinkedList<Integer> rightList) {
        Integer outSum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!(leftList.contains(list.get(i)) || rightList.contains(list.get(i)))) {
                outSum += integers[list.get(i)];
            }
        }
        return outSum;
    }

}
