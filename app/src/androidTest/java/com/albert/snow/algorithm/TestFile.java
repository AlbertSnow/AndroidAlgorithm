package com.albert.snow.algorithm;

import org.junit.Test;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class TestFile {

    @Test
    public void testFile() {
        myBFS(new File("/sdcard"));
    }

    public ArrayList<String> myBFS(File checkFile) {
        ArrayList<String> myList = new ArrayList<>();
        ArrayDeque<File> queue = new ArrayDeque<>();
        queue.offer(checkFile);

        while (!queue.isEmpty()) {
            checkFile = queue.poll();

            if (checkFile != null) {
                File[] files = checkFile.listFiles();

                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (file == null) {
                            continue;
                        }
                        if (file.isDirectory()) {
                            queue.offer(file);//是文件夹入queue作为新的需要检索文件夹的路径
                        } else {
//                            myList.add(file.getAbsolutePath());//是文件入list集合
                            System.out.println("File name: " + file.getAbsolutePath());
                        }
                    }
                }

            }
        }
        return myList;
    }

}
