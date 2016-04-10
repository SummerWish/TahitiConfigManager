package octoteam.tahiti.config;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ConfigManagerTest {

    @Test
    public void testLoadSingleConfig() throws IOException {
        File config = File.createTempFile("temp", Long.toString(System.nanoTime()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(config));
        bw.write("foo");
        bw.close();

        ConfigManager manager = new ConfigManager(
                new DirectReadConfigLoader(),
                config.getPath()
        );
        assertEquals("foo", manager.loadToBean(String.class));

        config.delete();
    }

    @Test
    public void testFallbackConfig() throws IOException {
        File config = File.createTempFile("temp", Long.toString(System.nanoTime()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(config));
        bw.write("foo");
        bw.close();

        ConfigManager manager = new ConfigManager(
                new DirectReadConfigLoader(),
                "/a/file/that/does/not/exist",
                config.getPath()
        );
        assertEquals("foo", manager.loadToBean(String.class));

        config.delete();
    }

    @Test
    public void testLoadPriority() throws IOException {
        File config1 = File.createTempFile("temp1", Long.toString(System.nanoTime()));
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(config1));
            bw.write("foo");
            bw.close();
        }

        File config2 = File.createTempFile("temp2", Long.toString(System.nanoTime()));
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(config2));
            bw.write("bar");
            bw.close();
        }

        ConfigManager manager = new ConfigManager(
                new DirectReadConfigLoader(),
                config1.getPath(),
                config2.getPath()
        );
        assertEquals("foo", manager.loadToBean(String.class));

        config1.delete();
        config2.delete();
    }

    @Test(expected = IOException.class)
    public void testException() throws IOException {
        new ConfigManager(
                new DirectReadConfigLoader(),
                "/a/file/that/does/not/exist"
        );
    }

}