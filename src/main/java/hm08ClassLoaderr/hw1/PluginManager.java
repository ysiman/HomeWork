package hm08ClassLoaderr.hw1;

import hm08ClassLoaderr.hw1.Plugin;
import hm08ClassLoaderr.hw1.PluginClassLoader;

/**
 * Created by HOME on 23.04.2017.
 */
public class PluginManager {
    private final String pluginRootDirectory;
    String pathToPlagin ;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName)   {
        System.out.println("pluginRootDirectory = " + pluginRootDirectory);
        System.out.println("pluginName = " + pluginName);
        pathToPlagin = pluginRootDirectory + "\\" + pluginName+ "\\";
        System.out.println("pathToPlagin = " + pathToPlagin);
        System.out.println("pluginClassName = " + pluginClassName);
        PluginClassLoader pcl = new PluginClassLoader(pathToPlagin,ClassLoader.getSystemClassLoader());

        try {
            Class clazz = pcl.findClass(pluginClassName);
            Plugin customPlagin = (Plugin) clazz.newInstance();
            return customPlagin;


        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
