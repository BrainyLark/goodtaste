package com.erdenebileg.testapi.utils;

import com.erdenebileg.testapi.Entity.Contribution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utilizable {
    public static boolean insertRowData(Contribution cont) {
        try {
            Files.write(Paths.get("ratings_small.csv"), cont.toString().getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
