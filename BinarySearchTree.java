

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
    public int get_min ()
    {
        if (head == null) {
            return 0;
        }
        Node curr = head;
        while (curr.get_left() != null) {
            curr = curr.get_left();
        }
        return curr.get_value();
    }
    public int get_max ()
    {
        if (head == null) {
            return 0;
        }
        Node curr = head;
        while (curr.get_right() != null) {
            curr = curr.get_right();
        }
        return curr.get_value();
    }
    public boolean contains (int value)
    {
        return contains(value, head);
    }
    public boolean insert (int value)
    {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return true;
        }
        return insert (head, node);
    }
    private boolean contains (int value, Node branch)
    {
        if (branch == null) {
            return false;
        }
        if (branch.less_than(value)) {
            return contains(value, branch.get_right());
        }
        if (branch.greater_than(value)) {
            return contains(value, branch.get_left());
        }
        return true; // Equals value;
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

        public boolean is_leaf () { return left == null && right == null; }
        public Node get_left () { return left; }
        public Node get_right () { return right; }

        public void set_left (Node node) { left = node; }
        public void set_right (Node node) { right = node; }

        public boolean less_than (Node node) { return value < node.get_value(); }
        public boolean less_than (int val) { return value < val; }
        public boolean greater_than (Node node) { return value > node.get_value(); }
        public boolean greater_than (int val) { return value > val; }
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
        System.out.println("Contains 4? ... " + tree.contains(4));
        System.out.println("Contains 7? ... " + tree.contains(7));
        System.out.println("Contains 0? ... " + tree.contains(0));
        System.out.println();
        System.out.println("Min Value? ... " + tree.get_min());
        System.out.println("Max Value? ... " + tree.get_max());
        System.out.println();
    }
}