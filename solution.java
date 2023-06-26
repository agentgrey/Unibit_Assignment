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

        Arrays.sort(nums);

        Set<List<Integer>> combinations = multiSum(nums, target*2);
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
 
