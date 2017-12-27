package lt.line.splitter.util;

public class CommonUtils {
    public CommonUtils() {
    }

    public static boolean isStringEmpty(final String str) {
        return (str == null) || str.isEmpty();
    }

    public static String getTrimmedString(final String str) {
        return str == null ? null : str.trim();
    }
}
