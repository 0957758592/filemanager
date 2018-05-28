package com.danilov.filemanager;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.Assert.*;

public class FileManagerIntegrationTest {

  private static FileManager fm = new FileManager();
  private static final int INITIAL_CAPACITY = 5;
  private static File dir;
  private static File file;
  private File testPath;
  private File testPathException;
  private static String from;
  private static String pathToCopy;
  private static String pathToMove;

  @BeforeClass
  public static void beforeClass() throws IOException {

    for (int i = 0; i < INITIAL_CAPACITY; i++) {
      dir = new File("TEST//test" + i + "//test");
      file = new File("TEST//test" + i + "//test" + i + ".txt");

      if (!dir.exists()) {
        dir.mkdirs();
      }

      if (!file.exists()) {
        file.createNewFile();
      }
    }
  }

  @Before
  public void before() {
    testPathException = new File("TEST_2");
    testPath = new File("TEST");
  }

  @Test
  public void calculateFilesTest() {
    assertEquals(5, fm.calculateFiles(testPath.getPath()));
    assertFalse(fm.calculateFiles(testPath.getPath()) == 4);
  }

  @Test
  public void calculateDirsTest() {
    assertEquals(10, fm.calculateDirs(testPath.getPath()));
    assertFalse(fm.calculateDirs(testPath.getPath()) == 4);
  }

  @Test(expected = InvalidPathException.class)
  public void getPathException() {
    fm.calculateFiles(testPathException.getPath());
  }

  @After
  public void after() throws IOException {
    testPathException = new File("TEST");
    testPath = new File("TEST_2");

    from = "TEST";
    pathToCopy = "TEST_2";
    pathToMove = "TEST_3";
  }

  @AfterClass
  public static void afterClass() throws IOException {
    fm.copy(from, pathToCopy);
    fm.move(pathToCopy, pathToMove);
  }

  @Test
  public void copyTest() {
    assertTrue(testPath.exists());
  }

//  @Test
//  public void moveTest() {
//    assertTrue(movePath.exists());
//  }

}
