package hm08ClassLoaderr.hv2;

import java.io.File;

public class EncryptManager {
    public static void main(String[] args) {
        File dir = new File("C:\\tempEncrypt");
        EncryptedClassLoader loader = new EncryptedClassLoader("JavaSchool",dir,ClassLoader.getSystemClassLoader());
        loader.encryptAll();
    }
}
