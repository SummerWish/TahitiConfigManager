package octoteam.tahiti.config;

import octoteam.tahiti.config.loader.ConfigLoader;

import java.io.InputStream;

class DirectReadConfigLoader extends ConfigLoader {

    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public <T> T loadToBean(InputStream streamIn, Class<T> clazz) {
        if (clazz != String.class) {
            throw new IllegalArgumentException();
        }
        return (T) convertStreamToString(streamIn);
    }

}
