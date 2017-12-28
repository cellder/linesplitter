package lt.line.splitter.impl;

import lt.line.splitter.model.Text;
import lt.line.splitter.util.CommonUtils;
import lt.line.splitter.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class LineSplitterImpl {

    public Text splitAndWriteToFile(final File file, final int lineLength, final String destination) throws IOException {
        return splitAndWriteToFile(FileSystemUtils.readTextFromFile(file), lineLength, destination);
    }

    public Text splitAndWriteToFile(final String text, final int lineLength, final String destination) throws IOException {
        Text splittedText = null;
        if (text != null && !text.isEmpty() && lineLength > 0) {
            final String oneLineText = text.replaceAll(System.lineSeparator(), " ");

            splittedText = getSplittedText(oneLineText, lineLength);

            if (!CommonUtils.isStringEmpty(destination)) {
                FileSystemUtils.writeToFile(splittedText.getText(),
                        new StringBuilder().append(destination).append("/resultFile.txt").toString());
            }
        }
        return splittedText;
    }

    private Text getSplittedText(final String oneLineText, final int lineLength) {
        final Text splittedText = new Text(lineLength);
        final StringTokenizer st = new StringTokenizer(oneLineText, " ,.:?!", true);

        while (st.hasMoreElements()) {
            String token = st.nextToken();
            if ((token = CommonUtils.getTrimmedString(token)) != null && !token.isEmpty()) {
                splittedText.addToken(token);
            }
        }
        return splittedText;
    }
}
