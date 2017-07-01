package hm08ClassLoaderr.hv2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class EncriptClass {
    public static void main(String[] args) {
        try(
                //FileInputStream fin=new FileInputStream("C:\\temp\\PluginWorkerrr.class");
                //FileOutputStream fos=new FileOutputStream("C:\\temp\\PluginWorkerEnc.class"))
                FileInputStream fin=new FileInputStream("C:\\tempEncrypt\\PluginWorker.class");
                FileOutputStream fos=new FileOutputStream("C:\\tempEncrypt\\PluginWorkerENCR.class"))
        {
            byte[] buffer = new byte[fin.available()];
            String key = "JavaSchool";
            byte[] b = key.getBytes();
            int keyInt = 0;
            for(int i = 0; i<b.length; i++ )
            {
                keyInt =  keyInt + b[i];
            }
            // считываем буфер
            fin.read(buffer, 0, buffer.length);
            for(int i = 0; i < buffer.length; i++){
                buffer[i] = (byte)(buffer[i] + keyInt);
            }
            // записываем из буфера в файл
            fos.write(buffer, 0, buffer.length);


        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
