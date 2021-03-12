package com.snailwu.start;

/**
 * @author WuQinglong
 * @date 2021/3/7 11:48
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 8, 6, 3};
//        bubbleSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        quickSort(arr, 0, arr.length - 1);
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    /**
     * 假设前面N个元素已经是排好序的，从后往前依次对比，类似冒泡
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    /**
     * 每次从未排序的序列中找到最小值，然后放到已排好序的序列末尾
     */
    private static void selectSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i; j < len; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (arr[min] != i) {
                int t = arr[i];
                arr[i] = arr[min];
                arr[min] = t;
            }
        }
    }

    /**
     * 选出一个基数 key，让比 key 小的在左边，比 key 大的在右边
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right, key = arr[left];
        while (l < r) {
            while (l < r && arr[r] > key) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
                l++;
            }
            while (l < r && arr[l] < key) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
                r--;
            }
        }
        arr[l] = key; // 或 arr[r] = key;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    /**
     * 递归按照顺序合并两个数组
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            mergeArray(arr, left, middle, right);
        }
    }

    private static void mergeArray(int[] arr, int left, int middle, int right) {
        int l1 = left;
        int l2 = middle + 1;
        int k = 0;
        int[] temp = new int[arr.length];
        while (l1 <= middle && l2 <= right) {
            if (arr[l1] == arr[l2]) {
                temp[k++] = arr[l1++];
                temp[k++] = arr[l2++];
            } else if (arr[l1] < arr[l2]) {
                temp[k++] = arr[l1++];
            } else if (arr[l1] > arr[l2]) {
                temp[k++] = arr[l2++];
            }
        }
        while (l1 <= middle) {
            temp[k++] = arr[l1++];
        }
        while (l2 <= right) {
            temp[k++] = arr[l2++];
        }
        System.arraycopy(temp, 0, arr, left, k);
    }

    /**
     * 堆排序，分为最大堆和最小堆
     * 是一个完全二叉树
     */
    private static void heapSort() {
    }

}
