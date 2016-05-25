
public class BinarySearchTree <T extends Comparable<T>>
{
    private Node<T> head;

    public BinarySearchTree () {}
    public BinarySearchTree (T[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
    }
    public static void main (String[] args)
    {
        System.out.print("\n#------ BinarySearchTree ------#\n\nInput Array: [ ");

        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        for (int i = 0; i < args.length; i++) {
            Integer val = Integer.parseInt(args[i]);
            if (!tree.insert(val)) {
                continue;
            }

            System.out.print(val);
            if (i+1 != args.length) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]\n\n");

        Integer [] doesItContain = new Integer[] { 4, 7, 0, 8 };
        for (int i = 0; i < doesItContain.length; i++) {
            System.out.println("Contains " + doesItContain[i] + " :  " + tree.contains(doesItContain[i]));
        }

        System.out.println();
        System.out.println("Min Value  :  " + tree.min());
        System.out.println("Max Value  :  " + tree.max());
        System.out.println();

        System.out.println("Ordered      :  " + tree);
        // tree.print_ordered();
        System.out.print("\n");

        System.out.println("Size: " + tree.size());
        Integer[] removed = new Integer[]{ 6, 4, 1, 8 };
        for (int i = 0; i < removed.length; i++) {
            System.out.println("Remove " + removed[i] + "    :  " + tree.remove(removed[i]) + " ... " + tree);
        }
        System.out.println("Size: " + tree.size());
    }
    public boolean is_empty ()
    {
        return head == null;
    }
    public int size()
    {
        return size(head);
    }
    public T min ()
    {
        if (is_empty()) { return null; }
        return farthest_left(head).value();
    }
    public T max ()
    {
        if (is_empty()) { return null; }
        return farthest_right(head).value();
    }
    public boolean contains (T value)
    {
        return Node.exists(find(value, head));
    }
    public boolean insert (T value)
    {
        Node<T> node = new Node<T>(value);
        if (is_empty()) {
            head = node;
            return true;
        }
        return insert (head, node);
    }
    public String toString ()
    {
        return to_string(head, max(), "[ ") + " ]";
    }
    public boolean remove (T value)
    {
        Node<T> popped = find(value, head);
        if (!Node.exists(popped)) { return false; }
        if (popped.is_leaf()) {
            System.out.println("IM A LEAF");
            if (popped.equals(head)) {
                head = null;
                return true;
            }
            Node<T> parent = parent_of(value);
            if (parent.value().compareTo(value) < 0) {
                parent.right(null);
            } else {
                parent.left(null);
            }
            return true;
        }
        Node<T> left = popped.left();
        if (Node.exists(left)) {
            if (left.is_leaf()) {
                popped.left(null);
                popped.value(left.value());
                return true;
            }
            Node<T> curr = left;
            while (curr.right() != null) {
                curr = curr.right(); 
            }
            popped.value(curr.value());
            if (left.equals(curr)) {
                popped.left(curr.left());
            } else {
                left.right(curr.left());
            }
        } else {
            Node<T> right = popped.right();
            
            System.out.println("HERE I AM" + popped.value());
            if (right.is_leaf()) {
                popped.right(null);
                popped.value(right.value());
                return true;
            }
            Node<T> curr = right;
            while (curr.left() != null) {
                curr = curr.left();
            }
            popped.value(curr.value());
            if (right.equals(curr)) {
                popped.right(curr.right());
            } else {
                right.left(curr.right());
            }
        }
        return true;
    }

    /*
        Private Methods 
    */

    private Node<T> parent_of (T value)
    {
        if (is_empty()) { return null; }
        Node<T> curr = head;
        while (!curr.is_leaf()) {
            if (curr.value().compareTo(value) < 0) {
                Node<T> right = curr.right();
                if (right == null) {
                    return null;
                }
                if (right.equals(value)) {
                    return curr;
                }
                curr = right;
            } else if (curr.value().compareTo(value) > 0) {
                Node<T> left = curr.left();
                if (left == null) {
                    return null;
                }
                if (left.equals(value)) {
                    return curr;
                }
                curr = left;
            } else {
                return null;  // Will only happen if value is head's value
            } 
        }
        return null;
    }
    private int size (Node<T> branch)
    {
        if (branch == null) {
            return 0;
        }
        return size(branch.left()) + size(branch.right()) + 1;
    }
    private String to_string (Node<T> node, T max, String output)
    {
        if (!Node.exists(node)) {
            return output;
        }
        output = to_string(node.left(), max, output);
        
        output += node.value();

        if (!node.equals(max)) {
            output += ", ";
        }
        return to_string(node.right(), max, output);
    }
    private Node<T> farthest_left (Node<T> node)
    {
        if (is_empty()) { return null; }
        while (node.left() != null) {
            node = node.left();
        } 
        return node;
    }
    private Node<T> farthest_right (Node<T> node)
    {
        if (is_empty()) { return null; }
        while (node.right() != null) {
            node = node.right();
        }
        return node;
    }
    private Node<T> find (T value, Node<T> branch)
    {
        if (branch == null) {
            return null;
        }
        if (branch.value().compareTo(value) < 0) {
            return find(value, branch.right());
        }
        if (branch.value().compareTo(value) > 0) {
            return find(value, branch.left());
        }
        return branch; // Equals value;
    }
    private boolean insert (Node<T> branch, Node<T> node)
    {
        if (node.compareTo(branch) < 0) {
            Node<T> left = branch.left();
            if (left == null) {
                branch.left(node);
                return true;
            }
            return insert(left, node);
        }
        if (node.compareTo(branch) > 0) {
            Node<T> right = branch.right();
            if (right == null){
                branch.right(node);
                return true;
            }
            return insert(right, node);
        }
        return false;  // Is Equal To Branch
    }

    private class Node<T extends Comparable<T>> extends ComparableNode<T>
    {
        private Node<T> left;
        private Node<T> right;

        public Node (T value)
        {
            super(value);
        }
        
        public boolean is_leaf () { return !(Node.exists(left) || Node.exists(right)); }

        public Node<T> left () { return left; }
        public Node<T> right () { return right; }

        public void left (Node<T> node) { left = node; }
        public void right (Node<T> node) { right = node; }
    }
}