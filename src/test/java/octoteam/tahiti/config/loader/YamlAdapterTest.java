package octoteam.tahiti.config.loader;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YamlAdapterTest {

    @Test
    public void testLoad() {
        InputStream streamIn = new ByteArrayInputStream("foo:\n  bar: qux".getBytes());
        ConfigBean b = new YamlAdapter().loadToBean(streamIn, ConfigBean.class);
        assertNotNull(b.getFoo());
        assertEquals("qux", b.getFoo().getBar());
    }

    @Test
    public void testWrite() throws IOException {
        OutputStream streamOut = new ByteArrayOutputStream();
        ConfigBean b = new ConfigBean(new FooBean("qux"));
        new YamlAdapter().writeToStream(b, streamOut);
        assertEquals("foo:\n  bar: qux", streamOut.toString().trim());
    }

}