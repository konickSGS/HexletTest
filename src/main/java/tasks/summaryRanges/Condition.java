package tasks.summaryRanges;

@FunctionalInterface
public interface Condition<T> {
    boolean make(T a, T b);
}