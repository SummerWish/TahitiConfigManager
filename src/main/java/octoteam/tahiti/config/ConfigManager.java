package octoteam.tahiti.config;

import octoteam.tahiti.config.loader.ConfigLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 */
public class ConfigManager {

    /**
     * TODO
     */
    private InputStream streamIn;

    /**
     * TODO
     */
    private ConfigLoader loader;

    /**
     * TODO
     *
     * @param loader
     * @param pathCandidates
     */
    public ConfigManager(ConfigLoader loader, String... pathCandidates) throws IOException {
        InputStream in = null;
        for (String path : pathCandidates) {
            try {
                in = new FileInputStream(path);
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
     * TODO
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T loadToBean(Class<T> clazz) {
        return loader.loadToBean(this.streamIn, clazz);
    }

}
