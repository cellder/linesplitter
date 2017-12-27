package lt.line.splitter.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

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
}
