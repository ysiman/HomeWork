package hm08ClassLoaderr.hw1;

import hm08ClassLoaderr.hw1.Plugin;

/**
 * Created by HOME on 14.05.2017.
 */
public class PluginWorker implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Internal plugin " + this.getClass() + " doing useful work!");
    }
}
