package com.danilov.filemanager;

import org.junit.Test;

import java.io.File;
import java.nio.file.InvalidPathException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FileManagerTest {

    private File file = new File("I:/TEST");
    private File fileException = new File("I:/TE");
    private FileManager fm = new FileManager();

    @Test
    public void calculateFilesTest() {
        assertEquals(6, fm.calculateFiles(file.getPath()));
        assertFalse(fm.calculateFiles(file.getPath()) == 4);
    }

    @Test
    public void calculateDirsTest() {
        assertEquals(9, fm.calculateDirs(file.getPath()));
        assertFalse(fm.calculateDirs(file.getPath()) == 4);
    }

    @Test(expected = InvalidPathException.class)
    public void getPathException(){
        fm.calculateFiles(fileException.getPath());
    }

//    @Test
//    public void calculateFilesTest() {
//        int cont = file.listFiles().length;
//            if(file.isDirectory()){
//                for (File path : file.listFiles()) {
//                 path.getAbsolutePath();
//                 if(path.isFile()){
//                     cont++;
//                 }
//             }
//        }
//        assertEquals(6, cont);
//        assertFalse(file.length() == 4);
//    }
//
//    @Test
//    public void calculateDirsTest() {
//        int count = file.getAbsolutePath().length();
//        for (File path : file.listFiles()) {
//            if(path.isDirectory()){
//                count++;
//            }
//        }
//        assertEquals(9, count);
//        assertFalse(file.length() == 4);
//    }
}
