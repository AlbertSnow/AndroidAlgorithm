package com.albert.snow.algorithm

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.albert.snow.algorithm", appContext.packageName)
    }



    @Test
    public fun testFile() {
        traverseFile(File("/sdcard"))
    }


    private fun traverseFile(rootFile: File)  {
        var checkFile: File? = rootFile
        val queue = ArrayDeque<File?>()
        queue.offer(checkFile)
        var fileCount = 1

        while (!queue.isEmpty()) {

            checkFile = queue.poll()
            if (checkFile != null) {
                val files = checkFile.listFiles()
                if (files?.isNotEmpty() == true) {
                    for (file in files) {

                        if (file != null) {
                            if (file.isDirectory) {
                                queue.offer(file) //是文件夹入queue作为新的需要检索文件夹的路径
                            } else {
                                fileCount++
//                                println("File name is ${file.absolutePath}" )
                            }
                        }
                    }
                }
            }
        }

        println("Hanlde file $fileCount")

    }

}