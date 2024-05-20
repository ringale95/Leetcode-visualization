package com.edu.arrayIndexing.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public double findAverage() {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum = sum + arr[i];
        }
        double avg = sum / n;
        return avg;
    }

    public int[] findDuplicate() throws Exception {
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] == arr[j])
                    return new int[] { i, j };
        throw new Exception("Duplicate not Found");
    }

    // this is not suitable since it return copyArr's indices and not original arr
    // indices
    public boolean findDuplicateFaster() throws Exception {
        int[] copyArr = arr.clone();
        Arrays.sort(copyArr);
        for (int i = 0; i < copyArr.length - 1; i++) {
            if (copyArr[i] == copyArr[i + 1])
                return true;
        }
        return false;
    }

    public int[] findDuplicatesFasterHash() throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            if (map.get(arr[i]) != null)
                return new int[] { (map.get(arr[i])), i };
            else
                map.put(arr[i], i);
        throw new Exception("Duplicate Not Found!");
    }

    public int[] findIndexesForTarget(int target) throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) { // traversal
            // Below is slow condition for loop to check
            // for (int j = i + 1; j < arr.length; j++) {
            // if (arr[i] + arr[j] == target)
            // return new int[] { i, j };
            // }

            // Use hash-map for faster retrieval
            Integer temp = map.get(arr[i]);
            // condition
            if (temp != null)
                return new int[] { temp, i };
            else
                map.put(target - arr[i], i);
        }
        throw new Exception("No match found!");
    }

}
