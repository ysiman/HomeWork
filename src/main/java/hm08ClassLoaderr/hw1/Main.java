package hm08ClassLoaderr.hw1;

/**
 * Created by HOME on 14.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("============1");
        PluginManager pm = new PluginManager("C:");
        System.out.println("============2");
        //Plugin plugin = pm.load("temp","PluginWorker");
        Plugin plugin = pm.load("temp","PluginWorker");
        System.out.println("============3");
        plugin.doUsefull();
        System.out.println("============4");
    }
}
