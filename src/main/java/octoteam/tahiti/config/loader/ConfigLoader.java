package octoteam.tahiti.config.loader;

import java.io.InputStream;

/**
 * 配置加载器的抽象类, 定义了通用接口
 */
public abstract class ConfigLoader {

    /**
     * 从输入流加载配置并映射到一个 Java Bean
     *
     * @param streamIn 输入流
     * @param clazz    Java Bean 的类
     * @param <T>      任意 Java Bean
     * @return Bean
     */
    public abstract <T> T loadToBean(InputStream streamIn, Class<T> clazz);

}
