package octoteam.tahiti.config;

import octoteam.tahiti.config.loader.ConfigAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DirectAdapter extends ConfigAdapter {

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

    public void writeToStream(Object data, OutputStream streamOut) throws IOException {
        streamOut.write(data.toString().getBytes());
    }

}
