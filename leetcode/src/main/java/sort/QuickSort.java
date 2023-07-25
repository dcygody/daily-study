package sort;

import java.util.Arrays;

/**
 * @description: 快排
 * @author: dcy
 * @create: 2023-07-25 19:55
 */
public class QuickSort {

    public void quickSort(int[] arr, int low, int high) {

        int i = low;
        int j = high;
        int pivot = arr[low];
        do {
            while (arr[i] < pivot && i < high) {
                i++;
            }
            while (arr[j] > pivot && j > low) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }while (i <= j);

        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }

    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {9,1,5,8,3,7,4,6,2};
        quickSort.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}


