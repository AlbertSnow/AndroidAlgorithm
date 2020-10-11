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

        assert 1 == count(1, nums1);
        assert 2 == count(2, nums1);
        assert 2 == count(3, nums1);
        assert 1 == count(4, nums1);
    }

    final int INVALID_INDEX = -1;


    private double count(int num, int[] numArray) {
        if (numArray == null || numArray.length == 0) {
            return 0;
        }

        //tag1
        int firstIndex = 0;
        int lastIndex = numArray.length - 1;
        int leftIndex = getFirstNumber(firstIndex, lastIndex, numArray, num);

        if (leftIndex < 0) {
            return 0;
        }
        int rightIndex = getEndNumber(firstIndex, lastIndex, numArray, num);

        return rightIndex - leftIndex + 1;
    }

    private int getFirstNumber(int firstIndex, int lastIndex, int[] numArray, int num) {
        int mediumIndex = (lastIndex - firstIndex) / 2 + firstIndex;

        if (num == numArray[mediumIndex]) {

            if (mediumIndex == 0 || numArray[mediumIndex - 1] != num) {
                return mediumIndex;
            } else {
                lastIndex = mediumIndex - 1;
            }

        } else if (firstIndex >= lastIndex) {
            return INVALID_INDEX;
        } else if (num < numArray[mediumIndex]) {
            lastIndex = mediumIndex - 1;
        } else if (num > numArray[mediumIndex]) {
            firstIndex = mediumIndex + 1;
        }
        return getFirstNumber(firstIndex, lastIndex, numArray, num);
    }

    private int getEndNumber(int firstIndex, int lastIndex, int[] numArray, int num) {
        int mediumIndex = (lastIndex - firstIndex) / 2 + firstIndex;

        if (num == numArray[mediumIndex]) {

            if (mediumIndex == numArray.length - 1 || numArray[mediumIndex + 1] != num) {
                return mediumIndex;
            } else {
                firstIndex = mediumIndex + 1;
            }
        } else if (firstIndex >= lastIndex) {
            return INVALID_INDEX;
        } else if (num < numArray[mediumIndex]) {
            lastIndex = mediumIndex - 1;
        } else if (num > numArray[mediumIndex]) {
            firstIndex = mediumIndex + 1;
        }
        return getEndNumber(firstIndex, lastIndex, numArray, num);
    }


}
