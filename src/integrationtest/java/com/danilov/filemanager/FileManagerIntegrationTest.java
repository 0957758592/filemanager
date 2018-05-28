package com.danilov.filemanager;

import org.junit.Test;

import java.io.File;
import java.nio.file.InvalidPathException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FileManagerIntegrationTest {

    private File file = new File("I://TEST");
    private File fileException = new File("I://TE");
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

}
