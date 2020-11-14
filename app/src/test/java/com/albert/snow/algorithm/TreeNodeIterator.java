package com.albert.snow.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class TreeNodeIterator {

    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode() {}

        public TreeNode(TreeNode left, TreeNode right) {this.left = left; this.right = right;}

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    static class EndLayer extends TreeNode {}


    @Test
    public void doTest() {
        TreeNode l33 = new TreeNode(330);

        TreeNode l21 = new TreeNode(210);
        TreeNode r21 = new TreeNode(211);
        TreeNode l22 = new TreeNode(220,null, l33);
        TreeNode r22 = new TreeNode(221);

        TreeNode l11 = new TreeNode(110, l21, r21);
        TreeNode r11 = new TreeNode(111, l22, r22);

        TreeNode rootTreeNode = new TreeNode(0, l11, r11);

        List<Integer> result = inorderTraversal(rootTreeNode);

        for (Integer i: result) {
            System.out.print(i + "-->");
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doMiddleByLoop(root, result);
        return result;
    }

    private void doMiddleIterator(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        doMiddleIterator(root.left, result);
        result.add(root.val);
        doMiddleIterator(root.right, result);
    }

    private void doMiddleByLoop(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }


    }


}
