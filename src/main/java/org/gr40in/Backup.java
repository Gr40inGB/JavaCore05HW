package org.gr40in;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Backup {

    public static void main(String[] args) throws IOException {

        // go up and create backup folder. And into him <backup + Date>
        Path backupFolder = Path.of("..//backup//backup" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyyy-MM-dd-hh-mm")));
        makeBackup(Path.of("."), backupFolder);
    }

    public static void makeBackup(Path source, Path destination) throws IOException {

        DirectoryStream<Path> directoryStream =
                Files.newDirectoryStream(source);  //"[!back]*"); glob ignored Backup.java =(

        for (var f : directoryStream) {
            Path newDestination = Path.of(destination.toString() + f.toString());
            if (Files.isDirectory(f)) {
                Files.createDirectories(newDestination);
                makeBackup(f, destination);
            } else //System.out.println(f);
                copyFile(f, destination);
        }
    }

    public static void copyFile(Path file, Path destination) throws IOException {
        FileInputStream inputStream = new FileInputStream(file.toFile());
        FileOutputStream fileOutputStream = new FileOutputStream(Path.of(destination.toString() + file).toFile());
        int count;
        byte[] buffer = new byte[1024];
        while ((count = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, count);
        }
    }

}
