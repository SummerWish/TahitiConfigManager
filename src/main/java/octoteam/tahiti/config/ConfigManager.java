package octoteam.tahiti.config;

import octoteam.tahiti.config.loader.ConfigLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 配置管理
 */
public class ConfigManager {

    /**
     * 有效的输入流
     */
    private InputStream streamIn;

    /**
     * 配置加载器
     */
    private ConfigLoader loader;

    /**
     * 给定配置加载器, 并给定配置的一个或多个候选路径
     *
     * @param loader         配置加载器
     * @param pathCandidates 候选路径
     * @throws IOException 若所有候选路径都无法获得配置, 则抛出此异常
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
     * 从候选路径中加载配置到 Java Bean
     *
     * @param clazz Java Bean 的类
     * @param <T>   任意 Java Bean
     * @return Bean
     */
    public <T> T loadToBean(Class<T> clazz) {
        return loader.loadToBean(this.streamIn, clazz);
    }

}
