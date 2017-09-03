package component;

import listener.TaskCompleteListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctFilter extends Tool {

    public DistinctFilter() { };

    public DistinctFilter(TaskCompleteListener callback) {
        super(callback);
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {
        this.data = input.stream();
        this.output = data.map(CaseInsensitiveString::new)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .map(CaseInsensitiveString::getOriginalString)
                .collect(Collectors.toList());
        this.callback(this.output);
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
