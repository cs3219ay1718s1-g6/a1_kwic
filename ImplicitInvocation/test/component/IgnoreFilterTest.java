package component;

import listener.TaskCompleteListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IgnoreFilterTest {

    private IgnoreFilter ignoreFilter;
    private TaskCompleteListener callback;

    @BeforeEach
    public void setUp() {
        this.ignoreFilter = new IgnoreFilter();
    }

    @Test
    public void testWithIgnoreKeywords() {

        String[] testStrings = new String[] { "is", "the", "of", "and", "as", "a", "after" };
        String[] expected = new String[] { };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.ignoreFilter.setTaskCompleteListener(callback);
        this.ignoreFilter.transform(Arrays.asList(testStrings));

    }

    @Test
    public void testWithIgnoreKeywordsSentence() {

        String[] testStrings = new String[] { "is the", "the ok", "yes as" };
        String[] expected = new String[] { "yes as" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.ignoreFilter.setTaskCompleteListener(callback);
        this.ignoreFilter.transform(Arrays.asList(testStrings));

    }

    @Test
    public void testWithoutIgnoreKeywords() {

        String[] testStrings = new String[] { "test", "hello", "ok" };
        String[] expected = new String[] { "test", "hello", "ok" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.ignoreFilter.setTaskCompleteListener(callback);
        this.ignoreFilter.transform(Arrays.asList(testStrings));

    }

    @Test
    public void testWithoutIgnoreKeywordsSentence() {

        String[] testStrings = new String[] { "how are", "you don't", "yes as" };
        String[] expected = new String[] { "how are", "you don't", "yes as" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.ignoreFilter.setTaskCompleteListener(callback);
        this.ignoreFilter.transform(Arrays.asList(testStrings));

    }

}
