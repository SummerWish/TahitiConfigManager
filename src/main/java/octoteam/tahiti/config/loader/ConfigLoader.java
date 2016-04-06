package octoteam.tahiti.config.loader;

import java.io.InputStream;

/**
 * TODO
 */
public interface ConfigLoader {

    /**
     * @param streamIn
     * @param <T>
     * @return
     */
    public <T> T loadToBean(InputStream streamIn);

}
