package org.jboss.gravel.data.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.gravel.Filter;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 * A filter that filters based on a comma-separated list of numbers and numeric ranges.
 */
public final class RangeListFilter implements Filter {

    private static final class Range {
        private long low;
        private long high;

        public Range(final long low, final long high) {
            this.low = low;
            this.high = high;
        }
    }

    private static final Pattern rangePattern = Pattern.compile(
        "\\G\\s*(,\\s*)?(?:\\s*(\\d+)\\s*(-\\s*(\\d*))?)"
    );

    private final List<Range> ranges = new ArrayList<Range>();

    public RangeListFilter(String filterString) {
        boolean first = true;

        final Matcher matcher = rangePattern.matcher(filterString);
        int lastMatched = 0;
        while (matcher.find()) {
            if ((matcher.group(1) == null) != first) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid range list given", null));
            }
            lastMatched = matcher.end();
            first = false;
            final String lowString = matcher.group(2);
            final boolean hasHigh = matcher.group(3) != null;
            final String highString = matcher.group(4);
            long low = Long.parseLong(lowString);
            long high = hasHigh ? highString.length() == 0 ? Long.MAX_VALUE : Long.parseLong(highString) : low;
            if (low <= high) {
                ranges.add(new Range(low, high));
            }
        }
        if (lastMatched < filterString.length()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid range list given", null));
        }
    }

    public boolean testEntry(Object object) {
        final long value;
        if (object == null) {
            return false;
        } else if (object instanceof Number) {
            value = ((Number) object).longValue();
        } else if (object instanceof String) {
            value = Long.parseLong((String) object);
        } else {
            value = Long.parseLong(object.toString());
        }
        if (ranges.isEmpty()) {
            return true;
        }
        for (Range range : ranges) {
            if (range.low <= value && value <= range.high) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder(ranges.size() * 5);
        boolean first = true;
        for (Range range : ranges) {
            if (first) {
                first = false;
            } else {
                builder.append(',');
            }
            builder.append(range.low);
            if (range.low != range.high) {
                builder.append('-').append(range.high);
            }
        }
        return builder.toString();
    }
}
