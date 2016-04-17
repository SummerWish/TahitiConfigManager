package octoteam.tahiti.config.loader;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

public class PropertyAdapter extends ConfigAdapter {

    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        Properties properties = new Properties();
        try {
            properties.load(streamIn);
        } catch (IOException ignored) {
        }
        try {
            T bean = clazz.newInstance();
            BeanUtils.populate(bean, properties);
            return bean;
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public void writeToStream(Object bean, OutputStream streamOut) throws IOException {
        Properties properties = new Properties();
        try {
            Map beanProp = BeanUtils.describe(bean);
            properties.putAll(beanProp);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        properties.store(streamOut, "");
    }

}
