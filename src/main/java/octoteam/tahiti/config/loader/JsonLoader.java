package octoteam.tahiti.config.loader;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;

/**
 * JSON 配置加载器
 */
public class JsonLoader extends ConfigLoader {

    /**
     * 从输入流读取所有内容
     *
     * @param is 输入流
     * @return 输入流中所有内容
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
