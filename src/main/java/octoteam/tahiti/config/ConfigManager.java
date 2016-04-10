package octoteam.tahiti.config;

import octoteam.tahiti.config.loader.ConfigLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * define the manager to get the configurationfile and loaded to be java bean
 */
public class ConfigManager {

    /**
     * the inputstream of the file
     */
    private InputStream streamIn;

    /**
     * the abstract class stated in the folder ConfigLoader
     */
    private ConfigLoader loader;

    /**
     * set the inputstream and the loader written in the file routed by pathCandidates
     *
     * @param loader
     * @param pathCandidates
     */
    public ConfigManager(ConfigLoader loader, String... pathCandidates) throws IOException {
        InputStream in = null;
        for (String path : pathCandidates) {
            try {
                in = new FileInputStream(path);
                break;
            } catch (IOException ignore) {
            }
        }
        if (in == null) {
            throw new IOException("None of the specified config is found");
        }
        this.streamIn = in;
        this.loader = loader;
    }

    /**
     * any kind type of configuration file loaded to be javabean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T loadToBean(Class<T> clazz) {
        return loader.loadToBean(this.streamIn, clazz);
    }

}
