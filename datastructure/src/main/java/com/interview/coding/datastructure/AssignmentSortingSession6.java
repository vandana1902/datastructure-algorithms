package com.interview.coding.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class AssignmentSortingSession6 {
	
	public void merge(int[] nums1, int m, int[] nums2, int n) {
	       
        int p1 = m - 1;
        int p2 = n - 1; 
        
       
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }

	 public int[] sortArray(int[] nums) {
	        int [] result = new int[nums.length];
	        PriorityQueue<Integer> heap = new PriorityQueue<>();
	        for(int i=0; i<nums.length; i++)
	        {
	            heap.add(nums[i]);
	        }
	    for(int i=0; i<nums.length; i++)
	        {
	            result[i] = heap.poll();
	        }
	        return result;
	    }

	 public String mergeAlternately(String word1, String word2) {
	        int l1 = word1.length();
	        int l2 = word2.length();
	        int length = Math.min(l1,l2);
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<length; i++){
	            sb.append(word1.charAt(i));
	            sb.append(word2.charAt(i));
	        }
	      return  (l1>l2)?sb.append(word1.substring(length)).toString(): sb.append(word2.substring(length)).toString();
	       
	    }

	 
	 public String largestMerge(String word1, String word2) {

			if(word1.length() == 0 && word2.length() == 0) return "";
		    if(word1.length() == 0) return word2;
		    if(word2.length() == 0) return word1;
		    
		    if(word1.compareTo(word2) >= 0){
		        return word1.substring(0, 1) + largestMerge(word1.substring(1), word2);
		    }else{
		        return word2.substring(0, 1) + largestMerge(word1, word2.substring(1));
		    }
		}

	 
	 public void sortColors(int[] nums) {
		   
		    int p0 = 0, curr = 0;
		    
		    int p2 = nums.length - 1;

		    int tmp;
		    while (curr <= p2) {
		      if (nums[curr] == 0) {
		       
		        tmp = nums[p0];
		        nums[p0++] = nums[curr];
		        nums[curr++] = tmp;
		      }
		      else if (nums[curr] == 2) {
		       
		        tmp = nums[curr];
		        nums[curr] = nums[p2];
		        nums[p2--] = tmp;
		      }
		      else curr++;
		    }
		  }

	 public boolean isPossible(int[] target) {
	       
	        if (target.length == 1) {
	            return target[0] == 1;
	        }
	        
	        int totalSum = Arrays.stream(target).sum();
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	        for (int num : target) {
	            pq.add(num);
	        }
	        
	        while (pq.element() > 1) {
	            int largest = pq.remove();
	            int x = largest - (totalSum - largest);
	            if (x < 1) return false;
	            pq.add(x);
	            totalSum = totalSum - largest + x;
	        }
	        
	        return true; 
	    }


	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		    ListNode h = new ListNode(0);
		    ListNode ans=h;
		    while (l1 != null && l2 != null) {
		        if (l1.val < l2.val) {
		            h.next = l1;
		            h = h.next;
		            l1 = l1.next;
		        } else {
		            h.next = l2;
		            h = h.next;
		            l2 = l2.next;
		        }
		    }
		    if(l1==null){
		        h.next=l2;
		    }
		    if(l2==null){
		        h.next=l1;
		    } 
		    return ans.next;
		}
	
	public ListNode mergeKLists(ListNode[] lists) {
		    if(lists.length==1){
		        return lists[0];
		    }
		    if(lists.length==0){
		        return null;
		    }
		    ListNode head = mergeTwoLists(lists[0],lists[1]);
		    for (int i = 2; i < lists.length; i++) {
		        head = mergeTwoLists(head,lists[i]);
		    }
		    return head;
		}

	 public int findKthLargest(int[] nums, int k) {
         PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
      
        for (int n: nums) {
          heap.add(n);
          if (heap.size() > k)
            heap.poll();
        }

       
        return heap.poll();        
  }

	 public int[] topKFrequent(int[] nums, int k) {
	       Map<Integer, Integer> count = new HashMap<>();
	       for(int i=0; i<nums.length; i++){
	           count.put(nums[i], count.getOrDefault(nums[i],0)+1);
	       }
	       
	       PriorityQueue<Integer>heap =new PriorityQueue<>((n1,n2) -> count.get(n1) -count.get(n2));
	       
	       for(int n: count.keySet()){
	           heap.add(n);
	           if(heap.size()>k){
	               heap.poll();
	           }
	       }
	       
	       int [] ans = new int[k];
	       for(int i=k-1;  i>=0; i--){
	           ans[i] = heap.poll();
	       }
	       
	       return ans;
	   }
}
