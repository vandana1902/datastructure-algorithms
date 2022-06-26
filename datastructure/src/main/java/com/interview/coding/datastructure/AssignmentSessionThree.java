package com.interview.coding.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AssignmentSessionThree {
	
	public boolean isValid(String s) {
        Map <Character, Character> map = new HashMap<Character, Character>();
        map.put(')' , '(');
        map.put('}' , '{');
        map.put(']' , '[');
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if(topElement != map.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

	
	public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int [] result = new int[length];
        Stack<Integer> stack = new Stack<Integer>();
        for(int currDay = 0; currDay<length; currDay++ ){
            int temprature = temperatures[currDay];
            while( ! stack.isEmpty() && temperatures[stack.peek()] < temprature){
                int previousDay = stack.pop();
                result[previousDay] = currDay - previousDay;
            }
            stack.push(currDay);
        }
       return result;  
    }
	
	
	public String simplifyPath(String path) {
        String [] pathArray = path.split("/");
        Stack<String>stack = new Stack<String>();
        for(String pathStr : pathArray){
            if(pathStr.equals(".") || pathStr.isEmpty()){
                continue;
            }else if(pathStr.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(pathStr);
            }
        }
        StringBuilder result = new StringBuilder();
        for(String str : stack){
            result.append("/");
            result.append(str);
        }
      return result.length()>0 ? result.toString() : "/";  
    }

	
	public boolean isAnagram(String s, String t) {
	        if(s.length()!=t.length()){
	            return false;
	        }
	        Map<Character,Integer> map = new HashMap<Character,Integer>();
	        for(int i=0; i<s.length(); i++){
	           map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
	            map.put(t.charAt(i), map.getOrDefault(t.charAt(i),0)-1);
	        }
	        
	        for(char c: map.keySet()){
	            if(map.get(c) !=0){
	                return false;
	            }
	        }
	        return true;
	    }
	
	
	public int longestValidParentheses(String s) {
	       int result = 0;
	        Stack<Integer> stack = new Stack<Integer>();
	        stack.push(-1);
	        for(int i = 0; i<s.length(); i++){
	            if(s.charAt(i)=='('){
	                stack.push(i);
	            }else{
	                stack.pop();
	                if(stack.isEmpty()){
	                    stack.push(i);
	                }else{
	                    result = Math.max(result, i-stack.peek());
	                }
	            }
	        }
	        return result;
	    }

	
	public int lengthOfLongestSubstring(String s) {
	        int n = s.length(), ans = 0;
	        Map<Character, Integer> map = new HashMap<>(); 
	        
	        for (int j = 0, i = 0; j < n; j++) {
	            if (map.containsKey(s.charAt(j))) {
	                i = Math.max(map.get(s.charAt(j)), i);
	            }
	            ans = Math.max(ans, j - i + 1);
	            map.put(s.charAt(j), j + 1);
	        }
	        return ans;
	    }

	 
	public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

	
	public int[] maxSlidingWindow(int[] nums, int k) {
	    int n = nums.length;
	    if (n * k == 0) return new int[0];
	    if (k == 1) return nums;

	    int [] left = new int[n];
	    left[0] = nums[0];
	    int [] right = new int[n];
	    right[n - 1] = nums[n - 1];
	    for (int i = 1; i < n; i++) {
	      
	      if (i % k == 0) left[i] = nums[i];  
	      else left[i] = Math.max(left[i - 1], nums[i]);

	      
	      int j = n - i - 1;
	      if ((j + 1) % k == 0) right[j] = nums[j];  
	      else right[j] = Math.max(right[j + 1], nums[j]);
	    }

	    int [] output = new int[n - k + 1];
	    for (int i = 0; i < n - k + 1; i++)
	      output[i] = Math.max(left[i + k - 1], right[i]);

	    return output;
	  }


	
}
