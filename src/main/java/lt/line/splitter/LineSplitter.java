package lt.line.splitter;

import lt.line.splitter.impl.LineSplitterImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LineSplitter {

    private final LineSplitterImpl lineSplitterImpl;

    public LineSplitter() {
        lineSplitterImpl = new LineSplitterImpl();
    }

    public void run() throws IOException {
        final URL url = getClass().getResource("/testFile.txt");
        if (url != null) {
            final File textFile = new File(url.getFile());
            System.out.println(textFile);
            lineSplitterImpl.splitAndWriteToFile(textFile, 10);
        }
        else {
            System.out.println("File not found!");
        }
    }
}
