package octoteam.tahiti.config.loader;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;

/**
 * TODO
 */
public class JsonLoader extends ConfigLoader {

    /**
     * TODO
     *
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * {@inheritDoc}
     */
    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        String content;
        content = convertStreamToString(streamIn);
        return JSON.parseObject(content, clazz);
    }

}
