package hm08ClassLoaderr.hv2;

import hm08ClassLoaderr.hw1.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EncryptedClassLoader extends ClassLoader {
    private String pathtobin;
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.pathtobin = pathtobin;
        this.key = key;
        this.dir = dir;

    }
    public void encryptAll()
    {
        String[] modules = dir.list();
        List<Class<?>> list = new ArrayList<>();
        for (String module: modules) {

            String moduleName = module.split(".class")[0];
            System.out.println("moduleName="+moduleName);

            try {
                // Class clazz = pcl.loadClass(pluginClassName);
                Class clazz = findClass(moduleName);
                Plugin customPlagin = (Plugin) clazz.newInstance();
                customPlagin.doUsefull();;


            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Class clazz = findClass(moduleName);
                list.add(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass  " + dir.toPath().toString() + className);

        try {
            byte[] ex = this.fetchClassFromFS(this.pathtobin + className + ".class");
            return this.defineClass(className, ex, 0, ex.length);
        }
        catch (Exception ex) {
            return super.loadClass(className);
        }

    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        FileInputStream is = new FileInputStream(path);
        System.out.println("fetchClassFromFS Run :"+path);

        byte[] bytes = new byte[(int)is.available()];
        byte[] b = key.getBytes();
        int keyInt = 0;
        for(int i = 0; i<b.length; i++ )
        {
            keyInt =  keyInt + b[i];
        }
        try{

            is.read(bytes, 0, bytes.length);
            for(int i = 0; i < bytes.length; i++){
                bytes[i] = (byte)(bytes[i] - keyInt);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return bytes;

    }


}
