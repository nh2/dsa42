package stack;

public interface IntegerStack
{

    void push(int i);

    // Wirft IllegalStateException wenn Stack leer.
    int pop();

    // Wirft IllegalStateException wenn Stack leer.
    int peek();

    // Wirft IllegalStateException wenn Stack leer.
    int min();

    // Wirft IllegalStateException wenn Stack leer.
    int max();

    int size();

    boolean isEmpty();

}
