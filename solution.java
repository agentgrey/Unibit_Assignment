import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        int[][] firstCombinations = twoSum(nums, target);
        for (int[] combination : firstCombinations) {
            System.out.println(Arrays.toString(combination));
        }

        int[] newArr = convertToSortedArray(firstCombinations);

        Set<List<Integer>> combinations = multiSum(newArr, target*2);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    public static int[][] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();

        for (int x: nums) {
            int check = target - x;
            if (map.containsKey(check))
                for (int num : map.get(check))
                    list.add(new int[]{num, x});
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(x);
        }

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++)
            leftArray[i] = arr[left + i];


        for (int j = 0; j < n2; j++)
            rightArray[j] = arr[mid + 1 + j];


        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static int[] convertToSortedArray(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[] array1D = new int[rows * cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array1D[k++] = arr[i][j];
            }
        }
        mergeSort(array1D, 0, array1D.length - 1);
        return array1D;
    }

    public static Set<List<Integer>> multiSum(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        bt(nums, target, new ArrayList<>(), 0, set);
        return set;
    }

    public static void bt(int[] nums, int target, List<Integer> list, int idx, Set<List<Integer>> res) {
        if(target==0) {
            res.add(new ArrayList<>(list));
        } else if (target >0) {
            for(int i=idx; i<nums.length; i++) {
                if(nums[i]>target) break;
                list.add(nums[i]);
                bt(nums, target-nums[i], list, i+1, res);
                list.remove(list.size()-1);
            }
        }
    }
}
 
