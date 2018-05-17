package com.danilov.filemanager;

import java.io.*;
import java.nio.file.InvalidPathException;
public class FileManager {

    public static void main(String[] args) throws IOException {

        String path = "I:/TEST";
        String from = "I:/TEST";
        String to = "I:/TEST2";
        String to2 = "H:/TEST23";


        System.out.println("files: " + calculateFiles(path));

        System.out.println("dirs: " + calculateDirs(path));

        copy(from, to);

        move(to, to2);
    }


    public static int calculateFiles(String path) {


        File filesPath = new File(path);
        int count = 0;
        if (filesPath.exists()) {
            for (File file : filesPath.listFiles()) {
                if (file.isFile()) {
                    count++;
                }
                if (file.isDirectory()) {
                    count += calculateFiles(file.getAbsolutePath());
                }
            }
        } else {
            throw new InvalidPathException(path, "No such path to file");
        }
        return count;
    }

    //количество папок в папке и всех подпапках по пути
    public static int calculateDirs(String path) {
        int count = 0;
        File pathFile = new File(path);
        if (pathFile.exists()) {
            for (File file : pathFile.listFiles()) {
            if (file.isDirectory()) {
                count ++;
                count += calculateDirs(file.getAbsolutePath());
            }
           }
        } else {
            throw new InvalidPathException(path, "No such path to file");
        }
        return count;
    }

    //копирование файлов и папок (откуда куда)
    public static void copy(String from, String to) throws IOException {
        File pathFrom = new File(from);
        File pathTo = new File(to);

        if (pathFrom.exists()) {
            if (pathFrom.isDirectory()) {
                if (!pathTo.exists()) {
                    pathTo.mkdir();
                }
                for (String file : pathFrom.list()) {
                    copy(new File(from, file).toString(), new File(to, file).toString());
                }
            } else {
                InputStream in = new FileInputStream(from);
                OutputStream out = new FileOutputStream(to);

                byte[] buffer = new byte[2048];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                    out.flush();
                }
                in.close();
                out.close();
            }
        } else {
            throw new InvalidPathException(from, "the path is not exist");
        }
    }
    //перемещение файлов и папок (откуда куда)
    public static void move(String from, String to) throws IOException {
        File oldFile = new File(from);
        File newFile = new File(to);
        if (!newFile.exists()) {
            copy(from, to);
        }
        for (File file : oldFile.listFiles()) {
            if (file.isDirectory()) {
                move(file.getAbsolutePath(), to);
            }
            file.delete();
        }
        oldFile.delete();
    }
}