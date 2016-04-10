package octoteam.tahiti.config.loader;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YamlLoaderTest {

    @Test
    public void testYamlLoader() {
        InputStream streamIn = new ByteArrayInputStream("foo:\n  bar: qux".getBytes());
        ConfigBean b = new YamlLoader().loadToBean(streamIn, ConfigBean.class);
        assertNotNull(b.getFoo());
        assertEquals("qux", b.getFoo().getBar());
    }

}