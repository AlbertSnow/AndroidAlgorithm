package com.albert.snow.algorithm;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class ZOrderString {

    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    @Test
    public void doTest() {
        int n = 1;
        String s = "LEETCODEISHIRING";
        System.out.println(convert(s, n));
    }

    public String convert(String s, int n) {

        if (n <= 0 || s == null || s.length() <= 0) {
            return "";
        }

        if (n == 1 || n >= s.length()) {
            return s;
        }

        List<TreeNode> roots = buildTree(n, s);
        return middleOrderIterator(s, roots);
    }

    private String middleOrderIterator(String s, List<TreeNode> roots) {
        StringBuilder builder = new StringBuilder(s.length());
        Queue<TreeNode> queue = new ArrayDeque<>(roots);

        while(!queue.isEmpty()) {
            TreeNode item = queue.poll();
            if (item == null) {
                break;
            }

            if (item.val >= 0) {
                builder.append(s.charAt(item.val));
            }

            if (item.left != null) {
                queue.offer(item.left);
            }
            if (item.right != null) {
                queue.offer(item.right);
            }
        }

        return builder.toString();
    }

    private List<TreeNode> buildTree(int n, String s) {
        List<TreeNode> roots = new ArrayList<>();
        TreeNode preItem = new TreeNode(0);
        roots.add(preItem);

        int i;
        int index = 1;
        boolean add = true;


        for (i =1; i<s.length(); i++) {
            TreeNode item = new TreeNode(i);

            if (index == 0) {
                roots.add(item);
            }

            if (add) {
                preItem.right = item;

                index++;
                if (index == n) {
                    index -= 2;
                    add = false;
                }
            } else {
                if (index < n -2) {
                    item.left = preItem;
                }

                index--;
                if (index < 0) {
                    index += 2;
                    add = true;
                }
            }

            preItem = item;
        }

        while (index >= 0 && index != (n-1)  && !add) {
            TreeNode item = new TreeNode(-1);
            if (index == 0) {
                roots.add(item);
            }

            item.left = preItem;
            index--;
        }


        return roots;
    }



}
