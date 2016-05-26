package dataStructures;

import java.util.Arrays;
import java.util.Timer;

public class Sort
{
    public static <T extends Comparable<T>> void main (String[] args)
    {
        Sort.speed_test();
    }
    public static <T extends Comparable<T>> void bubble (T[] arr)
    {
        for (int end = arr.length - 1; end >= 0; end--) {
            for (int runner = 0; runner < end; runner++) {
                if (arr[runner].compareTo(arr[runner+1]) > 0) {
                    swap(arr, runner, runner+1);
                }
            }
        }
    }
    public static <T extends Comparable<T>> void selection (T[] arr)
    {
        for (int start = 0; start < arr.length; start++) {
            int minIndex = start;
            for (int runner = start + 1; runner < arr.length; runner++) {
                if (arr[minIndex].compareTo(arr[runner]) > 0) {
                    minIndex = runner;
                }
            }
            if (minIndex == start) {
                continue;
            }
            swap(arr, minIndex, start);
        }
    }
    public static <T extends Comparable<T>> void insertion (T[] arr)
    {
        for (int index = 1; index < arr.length; index++) {
            T pulled = arr[index];
            int insertionIndex = 0;
            for (int runner = index - 1; runner >= 0; runner--) {
                if (pulled.compareTo(arr[runner]) > 0) {
                    insertionIndex = runner + 1;
                    break;
                }
                arr[runner+1] = arr[runner];
            }
            arr[insertionIndex] = pulled;
        }
    }
    public static <T extends Comparable<T>> void quick (T[] arr)
    {
        Sort.quick(arr, 0, arr.length-1);
    }
    public static <T extends Comparable<T>> void quick (T[] arr, int start, int end)
    {
        int pivot = Sort.partition(arr, start, start, end);
        if (pivot > start) {
            Sort.quick(arr, start, pivot);
        }
        if (pivot < end) {
            Sort.quick(arr, pivot+1, end);
        }
    }
    public static <T> void swap (T[] arr, int a, int b)
    {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static <T extends Comparable<T>> int partition (T[] arr)
    { 
        return Sort.partition(arr, 0, 0, arr.length-1);
    }
    public static <T extends Comparable<T>> int partition (T[] arr, int pivot, int start, int end)
    {
        if (pivot != start) {
            Sort.swap(arr, start, pivot);
        }
        pivot = start;
        int last = pivot+1;
        for (int i = last; i <= end; i++) {
            if (arr[i].compareTo(arr[pivot]) >= 0) {
                continue;
            }
            Sort.swap(arr, last, i);
            last++; 
        }
        Sort.swap(arr, pivot, last-1);
        return last-1;
    }
    private static void speed_test()
    {
        int size = 60 * 1000;
        double time_start = System.nanoTime();
        System.out.print("Create Array of " + size + " Random Integers: ");
        Integer[] base_array = new Integer[size];
        for (int i = 0; i < size; i++) {
            Integer num = (int)(Math.random() * size);
            base_array[i] = num;
        }
        double time_end = System.nanoTime();
        Sort.print_duration(time_start, time_end);

        Integer[] arr = new Integer[size];

        System.arraycopy(base_array, 0, arr, 0, size);
        time_start = System.nanoTime();
        System.out.print("Bubble Sort: ");
        Sort.bubble(arr);
        time_end = System.nanoTime();
        Sort.print_duration(time_start, time_end);

        System.arraycopy(base_array, 0, arr, 0, size);
        time_start = System.nanoTime();
        System.out.print("Selection Sort: ");
        Sort.selection(arr);
        time_end = System.nanoTime();
        Sort.print_duration(time_start, time_end);

        System.arraycopy(base_array, 0, arr, 0, size);
        time_start = System.nanoTime();
        System.out.print("Insertion Sort: ");
        Sort.insertion(arr);
        time_end = System.nanoTime();
        Sort.print_duration(time_start, time_end);

        System.arraycopy(base_array, 0, arr, 0, size);
        time_start = System.nanoTime();
        System.out.print("Quick Sort: ");
        Sort.quick(arr);
        time_end = System.nanoTime();
        Sort.print_duration(time_start, time_end);
    }
    private static void print_duration (double start, double end)
    {
        double duration = (end - start) / 1000000000.0;
        System.out.print(duration + " seconds\n");
    }
}