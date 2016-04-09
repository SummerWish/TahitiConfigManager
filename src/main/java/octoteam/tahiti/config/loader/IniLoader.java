package octoteam.tahiti.config.loader;

import org.ini4j.Ini;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 */
public class IniLoader extends ConfigLoader {

    /**
     * {@inheritDoc}
     */
    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        try {
            return new Ini(streamIn).as(clazz);
        } catch (IOException ignore) {
            return null;
        }
    }

}
