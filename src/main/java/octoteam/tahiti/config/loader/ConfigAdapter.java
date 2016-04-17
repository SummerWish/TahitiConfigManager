package octoteam.tahiti.config.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 配置加载器的抽象类, 定义了通用接口
 */
public abstract class ConfigAdapter {

    /**
     * 从输入流加载配置并映射到一个 Java Bean
     *
     * @param streamIn 输入流
     * @param clazz    Java Bean 的类
     * @param <T>      任意 Java Bean
     * @return Bean
     */
    public abstract <T> T loadToBean(InputStream streamIn, Class<T> clazz);

    /**
     * 将一个配置写入到输出流
     *
     * @param bean      配置对象
     * @param streamOut 输出流
     * @throws IOException
     */
    public abstract void writeToStream(Object bean, OutputStream streamOut) throws IOException;

}
