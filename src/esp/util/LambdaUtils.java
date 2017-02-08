package esp.util;

import java.util.function.Predicate;

public final class LambdaUtils {
    private LambdaUtils() {
    }

    public static <T> Predicate<T> as(Predicate<T> predicate) {
        return predicate;
    }

}