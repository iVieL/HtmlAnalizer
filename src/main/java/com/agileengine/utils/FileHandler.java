package com.agileengine.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class FileHandler {

    public static File openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if(!file.exists()) throw new FileNotFoundException("The file "+fileName+" doesn't exists.");
        return file;
    }
}
