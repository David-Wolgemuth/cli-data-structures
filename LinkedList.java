
public class LinkedList<T>
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
        list.print();
        list.add_back("penultimate", 1);
        list.add_front("2nd", 1);
        list.print();
        System.out.println("Back: " + list.back() + ", Front: " + list.front());
        System.out.println();
        System.out.println("Value at pos 1: " + list.value_at(1));
        System.out.println("Value at pos 1 from back: " + list.value_from_back(1));
        System.out.println();
        System.out.println("Removing \"b\" ... " + list.remove("b"));
        System.out.println("Removing \"e\" ... " + list.remove("e"));
        System.out.println("Removing \"a\" ... " + list.remove("a"));
        System.out.println();
        list.print();
    }

    public boolean is_empty () { return !Node.exists(head); }
    public int length ()
    {
        int count = 0; 
        for (Node<T> curr = head; Node.exists(curr); curr = curr.get_next()) {
            count++;
        }
        return count;
    }
    public void print ()
    {
        for (Node<T> curr = head; Node.exists(curr); curr = curr.get_next()) {
            System.out.print(curr.value());
            if (!curr.is_last()) {
                System.out.print(" -> ");
            }
        }
        System.out.print("\n");
    }
    public T front () { return value_at(0); }
    public T back () { return value_from_back(0); }

    public boolean remove (T value)
    {
        if (is_empty()) {
            return false;
        }
        if (head.has_value(value)) {
            head = head.get_next();
            return true;
        }
        Node<T> curr = head;
        while (!curr.is_last()) {
            T val = curr.get_next().value();
            if (curr.get_next().has_value(value)) {
                curr.set_next(curr.get_next().get_next());
                return true;
            }
            curr = curr.get_next();
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
            node = node.get_next();
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
            node = node.get_next();
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
            head.set_next(next);
        }
        Node<T> curr = head;
        int index = 1;
        while (!curr.is_last() && index < pos) {
            curr = curr.get_next();
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
                curr = curr.get_next();
            }
            index++;
            runner = runner.get_next();
        }
        insert_after(curr, value);
    }
    private void insert_after (Node<T> node, T value)
    {
        Node<T> next = node.get_next();
        node.set_next(new Node<T>(value));
        node.get_next().set_next(next);
    }

    private static class Node<T>
    {
        private T value;
        private Node<T> next;
        public Node (T value)
        {
            value(value);
        }
        public static boolean exists (Node node) { return node != null; }

        public void value (T value) { this.value = value; }
        public T value () { return value; }

        public boolean has_value(T value) { return this.value.equals(value); }

        public void set_next (Node<T> next) { this.next = next; }
        public Node<T> get_next () { return next; }

        public boolean is_last () { return this.next == null; }
}
}