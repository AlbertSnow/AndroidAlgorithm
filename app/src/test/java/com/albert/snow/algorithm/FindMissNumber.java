package com.albert.snow.algorithm;

import org.junit.Test;


public class FindMissNumber {

    @Test
    public void doTest() {
        int[] nums1 = new int[]{
            0, 1, 2, 4, 5, 6, 7
        };
        assert 0 == find(0, nums1.length - 1, new int[]{ 1, 2, 3, 4, 5, 6, 7});
        assert 1 == find(0, nums1.length - 1, new int[]{ 0, 2, 3, 4, 5, 6, 7});
        assert 2 == find(0, nums1.length - 1, new int[]{ 0, 1, 3, 4, 5, 6, 7});
        assert 3 == find(0, nums1.length - 1, new int[]{ 0, 1, 2, 4, 5, 6, 7});
        assert 4 == find(0, nums1.length - 1, new int[]{ 0, 1, 2, 3, 5, 6, 7});
        assert 5 == find(0, nums1.length - 1, new int[]{ 0, 1, 2, 3, 4, 6, 7});
        assert 6 == find(0, nums1.length - 1, new int[]{ 0, 1, 2, 3, 4, 5, 7});
        assert 7 == find(0, nums1.length - 1, new int[]{ 0, 1, 2, 3, 4, 5, 6});
    }

    private int find(int start, int end, int[] nums1) {
        if (nums1 == null || nums1.length == 0 || start > end) {
            return - 1;
        }

        int middleIndex = (start + end) / 2;

        if (start == end) {
            if (middleIndex == nums1[middleIndex]) {
                return middleIndex + 1;
            } else if (middleIndex < nums1[middleIndex]) {
                return middleIndex;
            }
        } else if (middleIndex == nums1[middleIndex]) {
            start = middleIndex + 1;
        } else if (middleIndex < nums1[middleIndex]) {
            end = middleIndex - 1;
        }

        return find(start, end, nums1);
    }



}
