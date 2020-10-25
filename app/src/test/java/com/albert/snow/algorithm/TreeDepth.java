package com.albert.snow.algorithm;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;


public class TreeDepth {

    static class Tree {
        Tree left, right;
        int value;

        public Tree() {}

        public Tree(Tree left, Tree right) {this.left = left; this.right = right;}

    }

    static class EndLayer extends Tree {}


    @Test
    public void doTest() {
        Tree l33 = new Tree();

        Tree l21 = new Tree();
        Tree r21 = new Tree();
        Tree l22 = new Tree(null, l33);
        Tree r22 = new Tree();

        Tree l11 = new Tree(l21, r21);
        Tree r11 = new Tree(l22, r22);

        Tree rootTree = new Tree(l11, r11);

        System.out.println("first depth: " + findDepthByIterator(rootTree));
        System.out.println("second depth: " + findDepthByLoop(rootTree));

    }

    private int findDepthByIterator(Tree tree) {
        if (tree == null) {
            return 0;
        }

        int leftDepth = findDepthByIterator(tree.left);
        int rightDepth = findDepthByIterator(tree.right);

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }


    private int findDepthByLoop(Tree tree) {
        if (tree == null) {
            return 0;
        }

        int depth = 0;
        Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(tree);
        queue.offer(new EndLayer());

        while (queue.size() > 0) {
            Tree cacheTree = queue.poll();
            if (cacheTree != null) {

                if (cacheTree instanceof EndLayer) {
                    depth++;
                    if (queue.size() > 0) {
                        queue.offer(new EndLayer());
                    }
                }


                if (cacheTree.left != null) {
                    queue.offer(cacheTree.left);
                }

                if (cacheTree.right != null) {
                    queue.offer(cacheTree.right);
                }

            }
        }

        return depth;
    }


}
