package lt.line.splitter.impl;

import lt.line.splitter.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LineSplitterImpl {

    public void splitAndWriteToFile(final File file, final int lineLength) throws IOException {
        splitAndWriteToFile(FileSystemUtils.readTextFromFile(file), lineLength);
    }

    public void splitAndWriteToFile(final String text, final int lineLength) {
        if (text != null && !text.isEmpty() && lineLength > 0) {
            final String oneLineText = text.replaceAll(System.lineSeparator(), " ");
            System.out.println(oneLineText);

            final StringTokenizer st = new StringTokenizer(oneLineText, " ,.", true);
            final StringBuilder sb = new StringBuilder();
            final List<String> lineTokens = new ArrayList<>();

            while (st.hasMoreElements()) {
                final String token = st.nextToken();
                System.out.println(token + "  :" + token.length());
            }
        }
    }

    private boolean makeLine(final List<String> lineTokens, final String token, final int lineLength) {
        boolean lineComplete = false;

        if (token == null) {
            return lineComplete;
        }

        if (token.length() < lineLength) {

        }

        return lineComplete;
    }
}
