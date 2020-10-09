package com.albert.snow.algorithm;

import org.junit.Test;


public class FindMedian {

    @Test
    public void doTest() {
        int[] nums1 = new int[]{
            1, 2
        };
        int[] nums2 = new int[]{
            2, 18, 2
        };
        double expect = 2;


        double result = findMedianSortedArrays(nums1, nums2);
        assert expect == result;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArray = mergeArray(nums1, nums2);

        if (mergeArray.length == 0) {
            return 0;
        } else if (mergeArray.length == 1) {
            return mergeArray[0];
        } else if (mergeArray.length % 2 != 0) {
            return mergeArray[mergeArray.length / 2];
        } else {
            int left = mergeArray[mergeArray.length / 2 - 1];
            int right = mergeArray[mergeArray.length / 2];
            return (left + right) / 2.0f;
        }
    }

    private int[] mergeArray(int[] nums1, int[] nums2) {
        nums1 = nums1 == null ? new int[0] : nums1;
        nums2 = nums2 == null ? new int[0] : nums2;

        int resultIndex = nums1.length + nums2.length - 1;

        int[] result = new int[nums1.length + nums2.length];
        int cache = 0;
        int index1 = nums1.length - 1;
        int index2 = nums2.length - 1;
        boolean useNums1;

        while (resultIndex >= 0) {

            if (index1 >= 0 && index2 >= 0) {
                useNums1 = nums1[index1] > nums2[index2];
            } else {
                useNums1 = index1 >= 0;
            }

            if (useNums1) {
                cache = nums1[index1];
                index1--;
            } else {
                cache = nums2[index2];
                index2--;
            }

            result[resultIndex] = cache;
            resultIndex--;
        }
        return result;
    }

}
