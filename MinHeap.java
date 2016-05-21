public class MinHeap
{
    private int capacity;
    private int size = 0;
    private int[] heap;

    public static void main (String[] args)
    {
        int[] arr = new int[] { 5, 3, 13, 4, 14, 7, 11, 6, 12, 2, 15, 1, 16, 9, 0, 10, 8 };
        MinHeap heap = new MinHeap(arr, 24);
        heap.print();
        System.out.println("Popped off the top: " + heap.pop());
        heap.print();
    }
    public MinHeap ()
    {
        capacity = 24;
        heap = new int[capacity];
    }
    public MinHeap (int capacity)
    {
        this.capacity = capacity;
        heap = new int[capacity];
    }
    public MinHeap (int[] arr, int capacity)
    {
        this.capacity = capacity;
        heap = new int[capacity];
        size = arr.length;
        System.out.print("PreHeap: [ ");
        for (int i = 0; i < arr.length && i < capacity-1; i++) {
            heap[i+1] = arr[i];
            System.out.print(arr[i]);
            if (i+1 != arr.length) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]\n");
        heapify();
    }
    public boolean insert (int value)
    {
        if (size == capacity) {
            return false;
        }
        heap[++size] = value;
        for (int pos = size; pos > 0; pos /= 2) {
            if (heap[pos] < heap[pos/2]) {
                break;
            }
            swap(pos, pos/2);
        }
        return true;
    }
    public int pop ()
    {
        if (size == 0) {
            return 0;
        }
        int value = heap[1];
        heap[1] = heap[size--];
        trickle_down(1);
        return value;
    }
    public int height ()
    {
        int curr = size;
        int height = 0;
        while (curr > 0) {
            curr /= 2;
            height++;
        }
        return height;
    }
    public void print ()
    {
        print_heap_array();
        int height = height();
        System.out.println("Height: " + height);

        int pos = 1, row = 0;
        int offset, spacing, last;

        while (pos <= size) {
            offset = (int)Math.pow(2, height - row - 1) - 1;
            spacing = (int)Math.pow(2, height - row) - 1;
            print_row(pos, (int)Math.pow(2, row), offset, spacing);
            pos = pos * 2;
            row++;
        }
        System.out.println();
    }
    private void print_heap_array ()
    {
        System.out.print("\nMinHeap: [ ");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i]);
            if (i+1 <= size) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]\n");
    }
    private void print_row (int pos, int end, int offset, int spacing)
    {
        System.out.print(spaces(offset));

        String space = spaces(spacing);
        for (int i = 0; i < end; i++) {
            if (pos == 1 || pos + i > size) {
                break;
            }
            char arrow = ((pos + i) % 2 == 0) ? '/' : '\\';
            System.out.print(arrow + space);
        }
        System.out.print("\n");

        System.out.print(spaces(offset));
        for (int i = 0; i < end; i++) {
            if (pos + i > size) {
                break;
            }
            int digits = new Integer(heap[pos + i]).toString().length() - 1;
            System.out.print(heap[pos + i] + space.substring(0, space.length()-digits));
        }
        System.out.print("\n");
    }
    private String spaces (int num)
    {
        String output = "";
        for (int x = 0; x < num; x++) {
            output += " ";
        }
        return output;
    }
    private void heapify ()
    {
        for (int pos = size / 2; pos > 0; pos--) {
            trickle_down(pos);
        }
    }
    private void trickle_down (int pos)
    {
        if (is_leaf(pos) || is_less_than_children(pos)) {
            return;
        }
        int left = left_child(pos);
        int right = right_child(pos);
        int branch = (right > size || heap[left] < heap[right]) ? left : right;
        swap(branch, pos);
        trickle_down(branch);
    }
    private void swap (int a, int b)
    {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    private boolean is_leaf (int pos) {
        return (pos * 2 > size);
    }
    private boolean is_less_than_children (int pos) {
        if (is_leaf(pos)) {
            return true;
        }
        if (heap[left_child(pos)] < heap[pos]) {
            return false;
        }
        if (right_child(pos) <= size && heap[right_child(pos)] < heap[pos]) {
            return false;
        }
        return true;
    }
    private int left_child(int position) {
        return position * 2;
    }
    private int right_child(int position) {
        return left_child(position) + 1;
    }
}