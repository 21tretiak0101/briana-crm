package by.ttre16.briana.assertion;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Configurable recursive field by field assertion
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

public class RecursiveAssert {
    public static <T> void assertMatch(
            T expected,
            T actual,
            String ...ignoring) {
        RecursiveComparisonConfiguration recursiveComparisonConfiguration =
                new RecursiveComparisonConfiguration();
        recursiveComparisonConfiguration.ignoreCollectionOrder(true);
        recursiveComparisonConfiguration.ignoreFields(ignoring);
        assertThat(actual)
                .usingRecursiveComparison(recursiveComparisonConfiguration)
                .isEqualTo(expected);
    }
}
