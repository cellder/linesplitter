package lt.line.splitter.model;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private static String SPACE = " ";
    private final int lineLength;
    private final List<Line> lines;
    private final String splitRegex;
    private final static List<String> charsWithoutSpaceAhead = new ArrayList<>();
    static {
        charsWithoutSpaceAhead.add(",");
        charsWithoutSpaceAhead.add(".");
        charsWithoutSpaceAhead.add(":");
        charsWithoutSpaceAhead.add("?");
        charsWithoutSpaceAhead.add("!");
    }

    public Text(final int lineLength) {
        this.lineLength = lineLength;
        lines = new ArrayList<>();
        splitRegex = String.format("(?<=\\G.{%s})", lineLength);
    }

    public void addToken(final String token) {
        if (token.length() >= getLineLength()) {
            Line emptyLine = getLastEmptyLine();
            final String[] splittedTokens = token.split(splitRegex);
            for (final String tkn : splittedTokens) {
                emptyLine.addToken(tkn);
                emptyLine = getLastEmptyLine();
            }
        }
        else {
            Line incompleteLine = getLastIncompleteLine(token);
            incompleteLine.addToken(token);
        }
    }

    private Line getLastEmptyLine() {
        Line emptyLine;
        if (lines.isEmpty()) {
            emptyLine = new Line(getLineLength());
            lines.add(emptyLine);
        }
        else {
            emptyLine = lines.get(lines.size() - 1);
            if (!emptyLine.isLineEmpty()) {
                emptyLine.markComplete();
                emptyLine = new Line(getLineLength());
                lines.add(emptyLine);
            }
        }
        return emptyLine;
    }

    private Line getLastIncompleteLine(final String token) {
        Line lastLine;
        if (lines.isEmpty()) {
            lastLine = new Line(getLineLength());
            lines.add(lastLine);
        }
        else {
            lastLine = lines.get(lines.size() - 1);
            if (lastLine.isComplete()) {
                lastLine = new Line(getLineLength());
                lines.add(lastLine);
            }
            else if (!lastLine.isTokenApplicable(token)) {
                lastLine.markComplete();
                lastLine = new Line(getLineLength());
                lines.add(lastLine);
            }
        }

        return lastLine;
    }

    public int getLineLength() {
        return lineLength;
    }

    public String getText() {
        final List<String> stringLines = new ArrayList<>();
        lines.stream().forEach((l) -> stringLines.add(l.getLineText()));
        return String.join(System.lineSeparator(), stringLines);
    }

    private class Line {

        private final int lineLength;
        private boolean complete;
        private StringBuilder sb = new StringBuilder();

        private Line(final int lineLength) {
            this.lineLength = lineLength;
        }

        public String getLineText() {
            return sb.toString();
        }

        public boolean isComplete() {
            return complete;
        }

        public void markComplete() {
            complete = true;
        }

        public boolean isLineEmpty() {
            return sb.toString().isEmpty();
        }

        public boolean isTokenApplicable(final String token) {
            boolean applicable = false;

            if (isLineEmpty()) {
                applicable = true;
            }
            else if ((token.length() == 1) && charsWithoutSpaceAhead.contains(token) &&
                    (sb.toString().length() + token.length()) <= lineLength) {
                applicable = true;
            }
            else if ((sb.toString().length() + token.length()) < lineLength) {
                applicable = true;
            }

            return applicable;
        }

        public void addToken(final String token) {
            if (isLineEmpty() || (token.length() == 1) && charsWithoutSpaceAhead.contains(token)) {
                sb.append(token);
            }
            else {
                sb.append(SPACE).append(token);
            }
        }

        @Override
        public String toString() {
            return "Line{" +
                    "lineLength=" + lineLength +
                    ", complete=" + complete +
                    ", lineText=" + sb.toString() +
                    '}';
        }
    }
}
