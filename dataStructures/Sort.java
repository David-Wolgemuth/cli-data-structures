package dataStructures;

import java.util.Arrays;

public class Sort
{
    public static <T extends Comparable<T>> void main (String[] args)
    {
        Integer[][] arrs = {
            new Integer[] { 4, 3, 8, 1, 0, 7, 5, 6, 9 },
            new Integer[] { 4, },
            new Integer[] { 1, 3, 8, 1, 0, 7, 5, 6, 9 },
            new Integer[] { 8, 3, 8, 1, 0, 7, 5, 6, 9 }
        };
        System.out.println("Partitioning Arrays:\n");
        for (int i = 0; i < arrs.length; i++) {
            System.out.println("Before: " + Arrays.toString(arrs[i]));
            Sort.partition(arrs[i], 0, 0, arrs[i].length-1);
            System.out.println("Partitioned: " + Arrays.toString(arrs[i]));
            System.out.println("\n<-- ## -->\n");
        }
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
    public static <T extends Comparable<T>> void partition (T[] arr, int pivot, int start, int end)
    {
        if (start == end) {
            return;
        }
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
    }
}