package tech.ninecraft.map.aggregator;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;

public class IntArrayAggregator implements ArgumentsAggregator {
    @Override
    public @Nullable Object aggregateArguments(final ArgumentsAccessor accessor, final ParameterContext context)
        throws ArgumentConversionException {
        final int[] result = new int[accessor.size() - 1];

        for (int i = 0; i < accessor.size() - 1; ++i) {
            System.out.println(i);
            result[i] = Integer.parseInt(accessor.getString(i + 1));
        }

        return result;
    }
}
