package com.example.utils;

import java.io.File;

public class PathUtil {
    public static String getDocsRoot() {
        File currentDirectory = new File(".\\docs");
        String currentPath = currentDirectory.getAbsolutePath();
        System.out.println("当前目录：" + currentPath);
        return currentPath;
    }
    public static String getDefaultImg() {
        return PathUtil.getDocsRoot()+"\\default.png";
    }
}
