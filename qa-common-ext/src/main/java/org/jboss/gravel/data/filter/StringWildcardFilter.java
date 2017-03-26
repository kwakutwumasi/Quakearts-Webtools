package org.jboss.gravel.data.filter;

import java.util.regex.Pattern;
import org.jboss.gravel.Filter;

/**
 * A filter that filters based on a string wildcard expression.  Valid wildcard characters
 * are {@code '*'} to match any number of characters and {@code '?'} to match exactly one character.
 */
public final class StringWildcardFilter implements Filter {
    private final String originalString;
    private final Pattern pattern;

    public StringWildcardFilter(String patternString) {
        originalString = patternString;
        if (patternString == null || "".equals(patternString)) {
            pattern = Pattern.compile(".*");
            return;
        }
        final int patternStringLength = patternString.length();
        final StringBuilder builder = new StringBuilder(patternStringLength * 2);
        builder.append('^');
        for (int i = 0; i < patternStringLength; i++) {
            final char c = patternString.charAt(i);
            switch (c) {
                case '*': builder.append(".*"); break;
                case '?': builder.append('.'); break;
                default:
                    if (! Character.isLetterOrDigit(c)) {
                        builder.append('\\');
                    }
                    builder.append(c);
            }
        }
        builder.append('$');
        pattern = Pattern.compile(builder.toString());
    }

    public boolean testEntry(Object object) {
        if (object == null) {
            object = "";
        }
        return pattern.matcher(object.toString()).matches();
    }

    public String toString() {
        return originalString;
    }
}
