package collectionmanager.commons;

@FunctionalInterface
public interface Transformer<A, B> {

    B transform(A input);

}
