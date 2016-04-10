package octoteam.tahiti.config.loader;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;

/**
 * loader of  the configration file --JSON
 */
public class JsonLoader extends ConfigLoader {

    /**
     * 
     *conver the input into string
     * @param is
     * @return s--string type of the file
     */
    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * using the parseObject method to return a class type of T--into java bean
     * {@inheritDoc}
     */
    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        String content;
        content = convertStreamToString(streamIn);
        return JSON.parseObject(content, clazz);
    }

}
