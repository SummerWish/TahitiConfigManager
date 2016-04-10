package octoteam.tahiti.config.loader;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonLoaderTest {

    @Test
    public void testJsonLoader() {
        InputStream streamIn = new ByteArrayInputStream("{\"foo\":{\"bar\":\"qux\"}}".getBytes());
        ConfigBean b = new JsonLoader().loadToBean(streamIn, ConfigBean.class);
        assertNotNull(b.getFoo());
        assertEquals("qux", b.getFoo().getBar());
    }

}