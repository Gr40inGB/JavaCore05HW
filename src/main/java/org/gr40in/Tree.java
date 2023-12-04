package org.gr40in;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO: Доработать метод print, необходимо распечатывать директории и файлы
 */
public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();

        if (files == null)
            return;

        List<File> filesListAll = new ArrayList<>(Arrays.asList(files));

        // sorted for lift folder up, and files down
        filesListAll.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() & (!o2.isDirectory())) return -1;
                else if ((!o1.isDirectory()) & o2.isDirectory()) return 1;
                else return o1.getName().compareTo(o2.getName());
            }
        });

        // im not sure that is correct method for count
        int subDirTotal = (int) filesListAll.stream().filter(File::isDirectory).count();
        int subFilesTotal = (int) filesListAll.stream().filter(File::isFile).count();

        int subDirCounter = 0;
        int subFilesCounter = 0;
        for (int i = 0; i < filesListAll.size(); i++) {
            if (filesListAll.get(i).isDirectory()) {
                print(filesListAll.get(i), indent, subFilesTotal == subFilesCounter
                        & subDirTotal == ++subDirCounter);
            } else print(filesListAll.get(i), indent, subFilesTotal == ++subFilesCounter
                    && subDirTotal == subDirCounter);
        }
    }

}
