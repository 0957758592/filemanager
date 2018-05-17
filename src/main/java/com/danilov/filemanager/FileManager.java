package com.danilov.filemanager;

import java.io.*;
import java.nio.file.InvalidPathException;

public class FileManager {

    public FileManager() {

    }

    public int calculateFiles(String path) {


        File filesPath = new File(path);
        int count = 0;

        if (filesPath.exists()) {
            throw new InvalidPathException(path, "No such path to file");
        }

        for (File file : filesPath.listFiles()) {
            if (file.isFile()) {
                count++;
            }
            if (file.isDirectory()) {
                count += calculateFiles(file.getAbsolutePath());
            }
        }
        return count;
    }

    //количество папок в папке и всех подпапках по пути
    public int calculateDirs(String path) {
        int count = 0;
        File pathFile = new File(path);

        if (pathFile.exists()) {
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
    public void copy(String from, String to) throws IOException {
        File pathFrom = new File(from);
        File pathTo = new File(to);

        if (pathFrom.exists()) {
            throw new InvalidPathException(from, "the path is not exist");
        }
            if (pathFrom.isDirectory()) {
                if (!pathTo.exists()) {
                    pathTo.mkdir();
                }
                for (String file : pathFrom.list()) {
                    copy(new File(from, file).toString(), new File(to, file).toString());
                }
            } else {
                InputStream in = null;
                OutputStream out = null;

                try {
                    in = new FileInputStream(from);
                    out = new FileOutputStream(to);
                    byte[] buffer = new byte[2048];

                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                } finally {
                    in.close();
                    out.close();
                }
            }
    }

    //перемещение файлов и папок (откуда куда)
    public void move(String from, String to) throws IOException {
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