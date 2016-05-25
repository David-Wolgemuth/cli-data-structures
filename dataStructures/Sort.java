package dataStructures;

import java.util.Arrays;

public class Sort
{
    public static void main (String[] args)
    {
        Integer[] arr = new Integer[] { 8, 3, 4, 1, 0, 7, 5, 6, 9 };
        System.out.print(Arrays.toString(arr));
        Sort.insertion(arr);
        System.out.println("Insertion Sorted: " + Arrays.toString(arr));
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
    public static <T> void swap (T[] arr, int a, int b)
    {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}