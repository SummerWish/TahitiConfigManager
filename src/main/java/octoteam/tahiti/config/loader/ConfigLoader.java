package octoteam.tahiti.config.loader;

import java.io.InputStream;

/**
 * TODO
 */
public abstract class ConfigLoader {

    /**
     * TODO
     *
     * @param streamIn
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T> T loadToBean(InputStream streamIn, Class<T> clazz);

}
