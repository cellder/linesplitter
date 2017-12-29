package lt.line.splitter.impl;

import static org.junit.Assert.*;

import lt.line.splitter.model.Text;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@RunWith(value = Parameterized.class)
public class LineSplitterImplTest {

    @Parameter(value = 0)
    public String sourcePath;
    @Parameter(value = 1)
    public int lineLength;
    @Parameter(value = 2)
    public int expectedLineCount;

    private LineSplitterImpl lineSplitter;

    @Before
    public void init() {
        lineSplitter = new LineSplitterImpl();
    }

    @Parameters(name = "{index}: splitAndWriteToFile({0}, {1}) expectedLineCount={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {LineSplitterImplTest.class.getResource("/data/testFile.txt").getFile(), 10, 8294},
                {LineSplitterImplTest.class.getResource("/data/testFile.txt").getFile(), 20, 3767},
                {LineSplitterImplTest.class.getResource("/data/testFile.txt").getFile(), 30, 2420},
                {LineSplitterImplTest.class.getResource("/data/testFile.txt").getFile(), 40, 1784},
                {LineSplitterImplTest.class.getResource("/data/testFile.txt").getFile(), 50, 1408},
                {LineSplitterImplTest.class.getResource("/data/testFile2.txt").getFile(), 13, 2},
                {LineSplitterImplTest.class.getResource("/data/testFile3.txt").getFile(), 7, 4}
        });
    }

    @Test
    public void splitAndWriteToFile() throws IOException {
        final File sourceFile = new File(sourcePath);
        final Text splittedText = lineSplitter.splitAndWriteToFile(sourceFile, lineLength, null);
        final String text = splittedText.getText();
        final String[] lines = text.split(System.lineSeparator());
        assertEquals("Line count should be equals to:" + expectedLineCount, expectedLineCount, lines.length);

        Arrays.stream(lines).forEach((line) -> assertTrue("Line length shouldn't be longer than:" + lineLength,
                line.length() <= lineLength));
    }

}