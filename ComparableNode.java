
public class ComparableNode<T extends Comparable<T>> implements Comparable<ComparableNode<T>>
{
    protected T value;

    public ComparableNode(T value)
    {
        value(value);
    }
    public int compareTo(final ComparableNode<T> node)
    {
        return value.compareTo(node.value());
    }
    public T value ()
    {
        return value;
    }
    public void value (T value)
    {
        this.value = value;
    }
    public static boolean exists (ComparableNode node)
    {
        return node != null;
    }
}
