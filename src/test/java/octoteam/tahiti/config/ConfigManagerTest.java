package octoteam.tahiti.config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConfigManagerTest {

    @Test
    public void testLoadSingleConfig() throws IOException {
        File config = File.createTempFile("temp", Long.toString(System.nanoTime()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(config));
        bw.write("foo");
        bw.close();

        ConfigManager manager = new ConfigManager(
                new DirectAdapter(),
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
                new DirectAdapter(),
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
                new DirectAdapter(),
                config1.getPath(),
                config2.getPath()
        );
        assertEquals("foo", manager.loadToBean(String.class));

        config1.delete();
        config2.delete();
    }

    @Test(expected = IOException.class)
    public void testLoadException() throws IOException {
        ConfigManager manager = new ConfigManager(
                new DirectAdapter(),
                "/a/file/that/does/not/exist"
        );
        manager.loadToBean(String.class); // throw
    }

    @Test
    public void testWriteSingleConfig() throws IOException {
        String tempFile = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), Double.toString(System.nanoTime()) + ".txt");

        ConfigManager manager = new ConfigManager(
                new DirectAdapter(),
                tempFile
        );
        manager.writeToFirstCandidate("foo");

        File file = new File(tempFile);
        assertEquals("foo", FileUtils.readFileToString(file));

        file.delete();
    }

    @Test
    public void testWriteFallbackConfig() throws IOException {
        String tempFile1 = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), Double.toString(System.nanoTime()) + "1.txt");
        String tempFile2 = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), Double.toString(System.nanoTime()) + "2.txt");

        ConfigManager manager = new ConfigManager(
                new DirectAdapter(),
                "/dev/urandom/a/place/not/exist",
                tempFile1,
                tempFile2
        );
        manager.writeToFirstCandidate("foo");

        File file1 = new File(tempFile1);
        assertEquals("foo", FileUtils.readFileToString(file1));

        File file2 = new File(tempFile2);
        assertFalse(file2.exists());

        file1.delete();
    }

    @Test(expected = IOException.class)
    public void testWriteException() throws IOException {
        ConfigManager manager = new ConfigManager(
                new DirectAdapter(),
                "/dev/urandom/a/place/not/exist"
        );
        manager.writeToFirstCandidate("foo"); // throw
    }


}