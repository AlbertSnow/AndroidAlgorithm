package com.albert.snow.algorithm;

import org.junit.Test;


public class FindNumberCount {

    @Test
    public void doTest() {
        int[] nums1 = new int[]{
            1, 2, 2, 3, 3, 4
        };
        int num = 4;
        double expect = 1;


        double result = count(num, nums1);
        assert expect == result;
    }

    final int INVALID_INDEX = -1;


    private double count(int num, int[] numArray) {
        if (numArray == null || numArray.length == 0) {
            return 0;
        }

        //tag1
        int firstIndex = 0;
        int lastIndex = numArray.length - 1;
        int index = findIndex(firstIndex, lastIndex, numArray, num);

        if (index < 0) {
            return 0;
        }

        int leftIndex = index;
        while (leftIndex - 1 >= 0 && numArray[leftIndex - 1] == num) {
            leftIndex--;
        }

        int rightIndex = index;
        while (rightIndex + 1 < numArray.length && numArray[rightIndex + 1] == num) {
            rightIndex++;
        }

        return rightIndex - leftIndex + 1;
    }

    private int findIndex(int firstIndex, int lastIndex, int[] numArray, int num) {
        int mediumIndex = (lastIndex - firstIndex) / 2 + firstIndex;

        if (num == numArray[mediumIndex]) {
            return mediumIndex;
        } else if (num == numArray[lastIndex]) {
            return lastIndex;
        } else if (firstIndex >= lastIndex) {
            return INVALID_INDEX;
        }

        if (num < numArray[mediumIndex]) {
            lastIndex = mediumIndex;
        } else if (num > numArray[mediumIndex]) {
            firstIndex = mediumIndex;
        }
        return findIndex(firstIndex, lastIndex, numArray, num);
    }


}
