package octoteam.tahiti.config.loader;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * loader of the configuration file --yaml
 */
public class YamlLoader extends ConfigLoader {

    /**
     * using the loadas method in yaml to return java bean (a class type of T)
     * {@inheritDoc}
     */
    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        return new Yaml().loadAs(streamIn, clazz);
    }

}
