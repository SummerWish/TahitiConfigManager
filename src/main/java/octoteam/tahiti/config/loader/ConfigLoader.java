package octoteam.tahiti.config.loader;

import java.io.InputStream;

/**
 * define package loader
 */
public abstract class ConfigLoader {

    /**
     * state an abstract class to be implemented
     *
     * @param streamIn
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T> T loadToBean(InputStream streamIn, Class<T> clazz);

}
