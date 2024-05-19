package com.edu.arrayIndexing.service;

import org.springframework.stereotype.Service;

@Service
public class ArrayService {

    private int[] arr;

    public void createArray(int n) {
        arr = new int[n];
    }

    public String searchFromArray(int arrayIndex) throws Exception {
        if (arrayIndex > arr.length - 1 && arrayIndex < 0)
            throw new Exception();
        return arr[arrayIndex] + " ";
    }

    public int[] evenIndexArray() {
        for (int i = 0; i < arr.length; i++)
            if (i % 2 == 0)
                arr[i] = arr[i] * 2;
        return arr;
    }

    public void updateArray(int[] newArr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = newArr[i];
    }

    public int findIndexSmallest() {
        int smallest = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[smallest] > arr[i])
                smallest = i;
        }
        return smallest;
    }

    public int findLargestIndex() {
        int largest = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[largest] < arr[i])
                largest = i;
        }
        return largest;
    }
}
