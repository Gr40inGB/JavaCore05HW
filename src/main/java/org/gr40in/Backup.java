package org.gr40in;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Backup {
    public static void main(String[] args) throws IOException {
        makeBackup(Path.of("."));
    }

    public static void makeBackup(Path path) throws IOException {
        DirectoryStream directoryStream = Files.newDirectoryStream(path);
        for (var f : directoryStream) {
            System.out.println(f);
        }
    }

}
