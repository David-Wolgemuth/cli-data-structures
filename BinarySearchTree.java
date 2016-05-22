

public class BinarySearchTree
{
    private Node head;

    public BinarySearchTree () {}
    public BinarySearchTree (int[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
    }
    public static void main (String[] args)
    {
        System.out.print("\n#------ BinarySearchTree ------#\n\nInput Array: [ ");
        BinarySearchTree tree = new BinarySearchTree();

        for (int i = 0; i < args.length; i++) {
            int val = Integer.parseInt(args[i]);
            if (!tree.insert(val)) {
                continue;
            }

            System.out.print(val);
            if (i+1 != args.length) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]\n\n");

        int [] doesItContain = new int[] { 4, 7, 0, 8 };
        for (int i = 0; i < doesItContain.length; i++) {
            System.out.println("Contains " + doesItContain[i] + " :  " + tree.contains(doesItContain[i]));
        }

        System.out.println();
        System.out.println("Min Value  :  " + tree.get_min());
        System.out.println("Max Value  :  " + tree.get_max());
        System.out.println();

        System.out.print("Ordered      :  ");
        tree.print_ordered();
        System.out.print("\n");

        int[] removed = new int[]{ 6, 4, 1, 8 };
        for (int i = 0; i < removed.length; i++) {
            System.out.print("Remove " + removed[i] + "    :  " + tree.remove(removed[i]) + " ... ");
            tree.print_ordered();
        }
    }
    public boolean is_empty ()
    {
        return head == null;
    }
    public int get_min ()
    {
        if (is_empty()) { return 0; }
        return farthest_left(head).get_value();
    }
    public int get_max ()
    {
        if (is_empty()) { return 0; }
        return farthest_right(head).get_value();
    }
    public boolean contains (int value)
    {
        return find(value, head) != null;
    }
    public boolean insert (int value)
    {
        Node node = new Node(value);
        if (is_empty()) {
            head = node;
            return true;
        }
        return insert (head, node);
    }
    public void print_ordered ()
    {
        System.out.print("[ ");
        print_ordered(head, get_max());
        System.out.print(" ]\n");
    }
    public boolean remove (int value)
    {
        Node popped = find(value, head);
        if (popped == null) { return false; }
        if (popped.is_leaf()) {
            if (popped.equals(head)) {
                head = null;
                return true;
            }
            Node parent = parent_of(value);
            if (parent.less_than(value)) {
                parent.set_right(null);
            } else {
                parent.set_left(null);
            }
            return true;
        }
        Node left = popped.get_left();
        if (left != null) {
            if (left.is_leaf()) {
                popped.set_left(null);
                popped.set_value(left.get_value());
                return true;
            }
            Node curr = left;
            while (curr.get_right() != null) {
                curr = curr.get_right(); 
            }
            popped.set_value(curr.get_value());
            if (left.equals(curr)) {
                popped.set_left(curr.get_left());
            } else {
                left.set_right(curr.get_left());
            }
        } else {
            Node right = popped.get_right();
            if (right.is_leaf()) {
                popped.set_right(null);
                popped.set_value(right.get_value());
                return true;
            }
            Node curr = right;
            while (curr.get_left() != null) {
                curr = curr.get_left();
            }
            popped.set_value(curr.get_value());
            if (right.equals(curr)) {
                popped.set_right(curr.get_right());
            } else {
                right.set_left(curr.get_right());
            }
        }
        return true;
    }

    /*
        Private Methods 
    */

    private Node parent_of (int value)
    {
        if (is_empty()) { return null; }
        Node curr = head;
        while (!curr.is_leaf()) {
            if (curr.less_than(value)) {
                Node right = curr.get_right();
                if (right == null) {
                    return null;
                }
                if (right.equals(value)) {
                    return curr;
                }
                curr = right;
            } else if (curr.greater_than(value)) {
                Node left = curr.get_left();
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
    private void print_ordered (Node node, int max)
    {
        if (node == null) {
            return;
        }
        print_ordered(node.get_left(), max);
        int left = 0, right = 0;
        if (node.get_left() != null) { left = node.get_left().get_value(); }
        if (node.get_right() != null) { right = node.get_right().get_value(); }
        System.out.print(node.get_value());
        if (node.get_value() != max) {
            System.out.print(", ");
        }
        print_ordered(node.get_right(), max);
    }
    private Node farthest_left (Node node)
    {
        if (is_empty()) { return null; }
        while (node.get_left() != null) {
            node = node.get_left();
        } 
        return node;
    }
    private Node farthest_right (Node node)
    {
        if (is_empty()) { return null; }
        while (node.get_right() != null) {
            node = node.get_right();
        }
        return node;
    }
    private Node find (int value, Node branch)
    {
        if (branch == null) {
            return null;
        }
        if (branch.less_than(value)) {
            return find(value, branch.get_right());
        }
        if (branch.greater_than(value)) {
            return find(value, branch.get_left());
        }
        return branch; // Equals value;
    }
    private boolean insert (Node branch, Node node)
    {
        if (node.less_than(branch)) {
            Node left = branch.get_left();
            if (left == null) {
                branch.set_left(node);
                return true;
            }
            return insert(left, node);
        }
        if (node.greater_than(branch)) {
            Node right = branch.get_right();
            if (right == null){
                branch.set_right(node);
                return true;
            }
            return insert(right, node);
        }
        return false;  // Is Equal To Branch
    }

    private class Node
    {
        private int value;
        private Node left;
        private Node right;
        
        public Node (int val)
        {
            value = val;
        }
        public int get_value () { return value; }
        public void set_value (int value) { this.value = value; }

        public boolean is_leaf () { return left == null && right == null; }
        public Node get_left () { return left; }
        public Node get_right () { return right; }

        public void set_left (Node node) { left = node; }
        public void set_right (Node node) { right = node; }

        public boolean less_than (Node node) { return value < node.get_value(); }
        public boolean less_than (int val) { return value < val; }
        public boolean greater_than (Node node) { return value > node.get_value(); }
        public boolean greater_than (int val) { return value > val; }
        public boolean equals (int val) { return value == val; }
        public boolean equals (Node node) { return value == node.get_value(); }
    }

}