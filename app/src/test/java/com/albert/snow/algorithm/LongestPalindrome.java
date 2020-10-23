package com.albert.snow.algorithm;

import org.junit.Test;


public class LongestPalindrome {

    @Test
    public void doTest() {
//        float centerIndex = getLongestIndex("123456899");
//        System.out.println("MaxIndex: " + centerIndex);
        String longest = getLongestIndex("bb");
        System.out.println("MaxIndex: " + longest);

    }

    public String getLongestIndex(String array) {

        if (array == null || array.length() <= 0) {
            return "";
        }

        int size = array.length();
        int maxResult = 0;
        float maxIndex = 0;
        int maxLeft =0;
        int maxRight = 0;

        int centerIndex = 0;
        int iteratorResult = 0;

        if (size == 1) {
            return array;
        }

        while (centerIndex >= 0 && centerIndex < size) {
            int left = centerIndex - 1;
            int right = centerIndex + 1;

            iteratorResult = 1;
            while (left >= 0 && right < size && (array.charAt(left) == array.charAt(right))) {
                left--;
                right++;
                iteratorResult += 2;
            }

            if (iteratorResult > maxResult) {
                maxResult = iteratorResult;
                maxIndex = centerIndex;
                maxLeft = ++left;
                maxRight = --right;
            }

            left = centerIndex;
            right = centerIndex + 1;
            iteratorResult = 0;
            while (left >= 0 && right < size && (array.charAt(left) == array.charAt(right))) {
                left--;
                right++;
                iteratorResult += 2;
            }

            if (iteratorResult > maxResult) {
                maxResult = iteratorResult;
                maxIndex = centerIndex + 0.5f;
                maxLeft = ++left;
                maxRight = --right;
            }


            centerIndex++;
        }


        String result = array.substring(maxLeft, Math.min(maxRight + 1, size));
        System.out.println("MaxResult: " + maxIndex);
        return result;
    }


}
