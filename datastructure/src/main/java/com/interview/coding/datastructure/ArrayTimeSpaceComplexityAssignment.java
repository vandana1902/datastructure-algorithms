package com.interview.coding.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayTimeSpaceComplexityAssignment {
	
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer>numsMap = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++){
           int remains = target - nums[i];
            if(numsMap.containsKey(remains)){
                return new int [] {numsMap.get(remains), i};
            }else{
                numsMap.put(nums[i], i);
            }
        }
        return null;
       
    }
	
	public void moveZeroes(int[] nums) {
		int writer = 0;
		for(int reader = 0; reader<nums.length; reader++) {
			if(nums[reader]!=0) {
				nums[writer]=nums[reader];
				nums[reader]=0;
				writer++;
			}
		}
	}
	
	public int maxProfit(int[] prices) {
		int tempPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0; i<prices.length; i++){
            if(tempPrice>prices[i]){
              tempPrice =  prices[i];
            }else {
                maxProfit = Math.max(maxProfit, prices[i]-tempPrice);
            }
            
        }
        return maxProfit;  
    }
	
	public int maxSubArray(int[] nums) {
        int currentSubArray = 0;
        int maxSubArray = 0;
        for(int i=1; i<nums.length; i++){
           currentSubArray = Math.max(nums[i], currentSubArray+nums[i]);
            maxSubArray = Math.max(currentSubArray, maxSubArray);
        }
        return maxSubArray;
        
    }
	
	public int[][] merge(int[][] intervals) {
		 List<int[]> listIntervals = new ArrayList<>();
		 for(int []  interval : intervals) {
			if(listIntervals.size()>0 && listIntervals.get(listIntervals.size()-1)[1]>=interval[0]) {
				listIntervals.get(listIntervals.size()-1)[1] = Math.max(listIntervals.get(listIntervals.size()-1)[1], interval[1]);
			}
			else {
				listIntervals.add(interval);
			}
		 }
		 
		return listIntervals.toArray(new int[listIntervals.size()][]);
	        
	    }
	 
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
           for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
          for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
           
            if (up != down) {
                
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            
            if (left != right) {
               
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
	    }
	 	
	public void gameOfLife(int[][] board) {
	         
	     }
	 	 
	public String reverseWords(String s) {
			s = s.trim();
			List<String> listStr =  Arrays.asList(s.split(" "));
			Collections.reverse(listStr);
			return String.join(" ", listStr);
	    }
	 	
	public int[] productExceptSelf(int[] nums) {
		 int tempProduct = 1;
	        int [] result = new int[nums.length];
	        for(int i = 0; i<nums.length; i++){
	            tempProduct = tempProduct*nums[i];
	        }
	        for(int i = 0; i<nums.length; i++){
	            result[i] = tempProduct/nums[i];
	        }
	        return result;
	    }
	 	
	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            if (duplicates.add(nums[i])) {
                for (int j = i + 1; j < nums.length; j++) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(result);
	 	}
	        
}
