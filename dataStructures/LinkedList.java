package dataStructures;

public class LinkedList<T extends Comparable<T>>
{
    private Node<T> head;

    public LinkedList () {}
    public LinkedList (T[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            add_back(arr[i]);
        }
    }

    public static void main (String[] args)
    {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < args.length; i++) {
            list.add_back((String)args[i]);
        }
        System.out.println(list);
        list.add_back("penultimate", 1);
        list.add_front("2nd", 1);
        System.out.println(list);
        System.out.println("Back: " + list.back() + ", Front: " + list.front());
        System.out.println();
        System.out.println("Value at pos 1: " + list.value_at(1));
        System.out.println("Value at pos 1 from back: " + list.value_from_back(1));
        System.out.println();
        System.out.println("Removing \"b\" ... " + list.remove("b"));
        System.out.println("Removing \"e\" ... " + list.remove("e"));
        System.out.println("Removing \"a\" ... " + list.remove("a"));
        System.out.println("\n" + list);
    }

    public boolean is_empty () { return !Node.exists(head); }
    public int length ()
    {
        int count = 0; 
        for (Node<T> curr = head; Node.exists(curr); curr = curr.next()) {
            count++;
        }
        return count;
    }
    public String toString ()
    {
        String str = "";
        for (Node<T> curr = head; Node.exists(curr); curr = curr.next()) {
            str += curr.value();
            if (!curr.is_last()) {
                str += " -> ";
            }
        }
        return str + "\n";
    }
    public T front () { return value_at(0); }
    public T back () { return value_from_back(0); }

    public boolean remove (T value)
    {
        if (is_empty()) {
            return false;
        }
        if (head.value().equals(value)) {
            head = head.next();
            return true;
        }
        Node<T> curr = head;
        while (!curr.is_last()) {
            T val = curr.next().value();
            if (curr.next().value().equals(value)) {
                curr.next(curr.next().next());
                return true;
            }
            curr = curr.next();
        }
        return false;
    }

    public T value_at (int pos)
    {
        if (is_empty()) { return null; }

        Node<T> node = head;
        int current_position = 0;

        while (Node.exists(node) && current_position < pos) {
            current_position++;
            node = node.next();
        }
        if (current_position != pos) {
            return null;
        }

        return node.value();
    }
    public T value_from_back (int pos)
    {
        if (is_empty()) { return null; }

        int length = length();
        Node<T> node = head;
        int current_position = 1;
        while (current_position < length - pos  && Node.exists(node)) {
            node = node.next();
            current_position++;
        }
        if (length - pos != current_position) {
            return null;
        }
        return node.value();
    }
    public void add_front (T value) { add_front(value, 0); }
    public void add_front (T value, int pos)
    {
        if (is_empty()) {
            head = new Node<T>(value);
            return;
        }
        if (pos == 0) {
            Node<T> next = head;
            head = new Node<T>(value);
            head.next(next);
        }
        Node<T> curr = head;
        int index = 1;
        while (!curr.is_last() && index < pos) {
            curr = curr.next();
            index++;
        }
        insert_after(curr, value);
    }
    public void add_back (T value) { add_back(value, 0); }
    public void add_back (T value, int pos)
    {
        if (is_empty()) {
            head = new Node<T>(value);
            return;
        }
        Node<T> curr = head;
        Node<T> runner = head;
        int index = 0;
        while (!runner.is_last()) {
            if (index >= pos) {
                curr = curr.next();
            }
            index++;
            runner = runner.next();
        }
        insert_after(curr, value);
    }
    private void insert_after (Node<T> node, T value)
    {
        Node<T> next = node.next();
        node.next(new Node<T>(value));
        node.next().next(next);
    }

    private class Node<T extends Comparable<T>> extends ComparableNode<T>
    {
        private T value;
        private Node<T> next;
        public Node (T value)
        {
            super(value);
        }

        public Node<T> next () { return next; }
        public void next (Node<T> next) { this.next = next; }

        public boolean is_last () { return !Node.exists(this.next); }
    }
}