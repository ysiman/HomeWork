package hm08ClassLoaderr.hv2;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by HOME on 14.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        File dir = new File("C:\\temp");
        String[] modules = dir.list();
        Path path = dir.toPath();
        System.out.println("path = " + path.toString());

    }
}
