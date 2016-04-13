package octoteam.tahiti.config.loader;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonAdapterTest {

    @Test
    public void testLoad() {
        InputStream streamIn = new ByteArrayInputStream("{\"foo\":{\"bar\":\"qux\"}}".getBytes());
        ConfigBean b = new JsonAdapter().loadToBean(streamIn, ConfigBean.class);
        assertNotNull(b.getFoo());
        assertEquals("qux", b.getFoo().getBar());
    }

    @Test
    public void testWrite() throws IOException {
        OutputStream streamOut = new ByteArrayOutputStream();
        ConfigBean b = new ConfigBean(new FooBean("qux"));
        new JsonAdapter().writeToStream(b, streamOut);
        assertEquals("{\"foo\":{\"bar\":\"qux\"}}", streamOut.toString());
    }

}