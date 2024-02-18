package tasks.polishNotation;

@FunctionalInterface
public interface Calculate<X, Y> {
    X calculate(X a, Y b);
}