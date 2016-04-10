package octoteam.tahiti.config.loader;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * YAML 配置加载器
 */
public class YamlLoader extends ConfigLoader {

    /**
     * {@inheritDoc}
     */
    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        return new Yaml().loadAs(streamIn, clazz);
    }

}
