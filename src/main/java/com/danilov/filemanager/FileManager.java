package com.danilov.filemanager;

import java.io.*;
import java.nio.file.InvalidPathException;

public class FileManager {

    public static void main(String[] args) throws IOException {
        System.out.println(calculateFiles("I:/TEST"));
        System.out.println(calculateDirs("I:/TEST"));
        copy("I:/TEST", "I:/TEST23");
        move("I:/TEST23", "H:/TEST");
    }

    public static int calculateFiles(String path) {


        File filesPath = new File(path);
        int count = 0;

        if (!filesPath.exists()) {
            throw new InvalidPathException(path, "No such path to file");
        }

        for (File file : filesPath.listFiles()) {
            if (file.isFile()) {
                count++;
            } else if (file.isDirectory()) {
                count += calculateFiles(file.getAbsolutePath());
            }
        }
        return count;
    }

    //количество папок в папке и всех подпапках по пути
    public static int calculateDirs(String path) {
        int count = 0;
        File pathFile = new File(path);

        if (!pathFile.exists()) {
            throw new InvalidPathException(path, "No such path to file");
        }
        for (File file : pathFile.listFiles()) {
            if (file.isDirectory()) {
                count++;
                count += calculateDirs(file.getAbsolutePath());
            }
        }
        return count;
    }

    //копирование файлов и папок (откуда куда)
    public static void copy(String from, String to) throws IOException {
        File pathFrom = new File(from);
        File pathTo = new File(to);

        if (!pathFrom.exists()) {
            throw new InvalidPathException(from, "the path is not exist");
        }
        if (pathFrom.isDirectory()) {
            if (!pathTo.exists()) {
                pathTo.mkdirs();
            }
            for (String file : pathFrom.list()) {
                copy(new File(from, file).getPath(), new File(to, file).getPath());
            }
        } else {

            try (InputStream in = new FileInputStream(from);
                 OutputStream out = new FileOutputStream(to)) {
                byte[] buffer = new byte[2048];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
        }
    }

    //перемещение файлов и папок (откуда куда)
    public static void move(String from, String to) throws IOException {
        File directory = new File(from);
        File newFile = new File(to);
        if (!newFile.exists()) {
            copy(from, to);
        }
        for (File path : directory.listFiles()) {
            if (path.isDirectory()) {
                move(path.getAbsolutePath(), to);
            }
            path.delete();
        }
        directory.delete();
    }
}