package com.danilov.filemanager;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FileManagerTest {

    private String path = "I:/TEST";
    private File file = new File(path);

    //не пойму как проверить на ексепшн?
//    private String pathException = "I:/TE";
//    private File fileException = new File(pathException);

    @Test
    public void calculateFilesTest() {
        int cont = file.listFiles().length;
            if(file.isDirectory()){
                for (File path : file.listFiles()) {
                 path.getAbsolutePath();
                 if(path.isFile()){
                     cont++;
                 }
             }
        }
        assertEquals(6, cont);
        assertFalse(file.length() == 4);
    }

    @Test
    public void calculateDirsTest() {
        int count = file.getAbsolutePath().length();
        for (File path : file.listFiles()) {
            if(path.isDirectory()){
                count++;
            }
        }
        assertEquals(9, count);
        assertFalse(file.length() == 4);
    }
}
