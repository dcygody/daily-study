package sort;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @author: dcy
 * @create: 2023-07-25 19:10
 */
public class BubbleSort {

    public void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = {9,1,5,8,3,7,4,6,2};
        bubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}


