package filters;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DedupeFilter extends Filter<String, String> {
    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.map(CaseInsensitiveString::new)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .map(CaseInsensitiveString::getOriginalString);
    }

    private static class CaseInsensitiveString {

        private final String originalString;

        private CaseInsensitiveString(String string) {
            originalString = string;
        }

        private String getOriginalString() {
            return originalString;
        }

        @Override
        public int hashCode() {
            return this.getOriginalString().toLowerCase().hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof CaseInsensitiveString)) return false;
            CaseInsensitiveString string = (CaseInsensitiveString) o;
            return (getOriginalString().equalsIgnoreCase(string.getOriginalString()));
        }
    }
}
