package lt.line.splitter.model;

import java.util.ArrayList;
import java.util.List;

public class Text {

    private final int lineLength;
    private final List<Line> lines;

    public Text(final int lineLength) {
        this.lineLength = lineLength;
        lines = new ArrayList<>();
    }

    public void addToken(final String token) {
        final Line incompleteLine = getLastIncompleteLine();
        if (token != null) {

        }
    }

    private Line getLastIncompleteLine() {
        Line lastLine = null;
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
        }
        return lastLine;
    }

    public int getLineLength() {
        return lineLength;
    }

    private class Line {
        private final int lineLength;
        private boolean complete;
        private StringBuilder sb = new StringBuilder();

        private Line(final int lineLength) {
            this.lineLength = lineLength;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public boolean isComplete() {
            return complete;
        }

        public boolean isTokenApplicable(final String token, final boolean splittable) {
            boolean applicable = false;

            if (token != null) {
                if (token.length() > lineLength) {

                }
            }

            return applicable;
        }
    }
}
