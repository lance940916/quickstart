package com.snailwu.start;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author WuQinglong
 * @date 2021/2/23 12:45
 */
public class Main {

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,77,81,94};
//        System.out.println(binarySearch(arr, 77));
//        int[] twoSum = twoSum(arr, 8);
//        for (int i : twoSum) {
//            System.out.print(i + ", ");
//        }

//        String s = "aabdffdefhii";
//        System.out.println(lengthOfLongestSubstring(s));

//        int[] arr = {1,2,3,3,4,5,6,6};
//        System.out.println(removeDuplicates(arr));

        System.out.println(2);

    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }

    public static int lengthOfLongestSubstring(String s) {
        // 记录 字符:数量
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        // 记录结果
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char leftC = s.charAt(left);
                left++;
                window.put(leftC, window.get(leftC) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static int removeDuplicates(int[] nums) {
        int left = 0;
        int right = left + 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                // right 和下一个元素交换 并 right++
                left++;
                nums[left] = nums[right];
            }
            right++;
        }
        int maxIndex = left;
        for (int i = 0; i < maxIndex; i++) {
            System.out.print(nums[i] + ",");
        }

        return left + 1;
    }

}
