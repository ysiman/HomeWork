package hm08ClassLoaderr.hw1;

/**
 * Created by HOME on 14.05.2017.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PluginClassLoader extends ClassLoader {
    private String pathtobin;

    public PluginClassLoader(String pathtobin, ClassLoader parent) {
        super(parent);
        this.pathtobin = pathtobin;

    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass className="+className);
        System.out.println("findClass  " + this.pathtobin + className);
        try {
            byte[] ex = this.fetchClassFromFS(this.pathtobin + className + ".class");
            return this.defineClass(className, ex, 0, ex.length);
        }
        catch (Exception ex) {
            System.out.println("findClass fetchClassFromFS Exception="+className);
            return super.loadClass(className);
        }

    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        FileInputStream is = new FileInputStream(path);
        System.out.println("fetchClassFromFS Run :"+path);

        byte[] bytes = new byte[(int)is.available()];
        try{

            is.read(bytes, 0, bytes.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return bytes;

    }
}
