package lt.line.splitter.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSystemUtils {

    public FileSystemUtils() {
    }

    public static String readTextFromFile(final File sourceFile) throws IOException {
        String resultText = null;

        if (sourceFile != null) {
            try (FileInputStream fis = new FileInputStream(sourceFile)) {
                resultText = IOUtils.toString(fis, Charset.defaultCharset());
            }
        }

        return resultText;
    }

    public static void writeToFile(final String text, final String destination) throws IOException {
        final Path path = Paths.get(destination);
        System.out.println(path.toString());
        Files.write(path, text.getBytes());
    }
}
