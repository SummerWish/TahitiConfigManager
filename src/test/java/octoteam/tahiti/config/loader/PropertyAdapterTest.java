package octoteam.tahiti.config.loader;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class PropertyAdapterTest {

    @Test
    public void testLoad() {
        InputStream streamIn = new ByteArrayInputStream("bar=qux".getBytes());
        FooBean b = new PropertyAdapter().loadToBean(streamIn, FooBean.class);
        assertEquals("qux", b.getBar());
    }

    @Test
    public void testWrite() throws IOException {
        OutputStream streamOut = new ByteArrayOutputStream();
        FooBean b = new FooBean("qux");
        new PropertyAdapter().writeToStream(b, streamOut);
        assertTrue(streamOut.toString().contains("bar=qux"));
    }

}