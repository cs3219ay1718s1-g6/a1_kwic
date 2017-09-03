package filters;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IgnoreFilterTest {

    @Test
    public void testWithIgnoreKeywords() {
        String[] testString = new String[] { "is", "the", "of", "and", "as", "a", "after" };
        String[] expected = new String[] { };
        String[] actual = new IgnoreFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWithIgnoreKeywordsSentence() {
        String[] testString = new String[] { "is the", "the ok", "yes as" };
        String[] expected = new String[] { "yes as" };
        String[] actual = new IgnoreFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWithoutIgnoreKeywords() {
        String[] testString = new String[] { "test", "hello", "ok" };
        String[] expected = new String[] { "test", "hello", "ok" };
        String[] actual = new IgnoreFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWithoutIgnoreKeywordsSentence() {
        String[] testString = new String[] { "how are", "you don't", "yes as" };
        String[] expected = new String[] { "how are", "you don't", "yes as" };
        String[] actual = new IgnoreFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}
