package com.interview.coding.datastructure;

public class AssignmentSecondClass {
	
	public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode previous = dummyHead;
        ListNode currentNode = head;
        while(currentNode != null){
          if(currentNode.val == val)  {
            previous.next = currentNode.next;
           }else{
              previous = currentNode;
              }
            currentNode =  currentNode.next;
        }
        return dummyHead.next;
        
    }

	 public int fib(int n) {
	        if(n<=1){
	            return n;
	        }
	        return fib(n-1)+(n-2);
	    }
	 
	 public int search(int[] nums, int target, int low, int high) {
		 int middleIndex = (low+high)/2; 
         
    if(target==nums[middleIndex]){
       
        return middleIndex;
    } else if(target < nums[middleIndex]){
        high=middleIndex; 
        return search( nums, target, low,  high);
    }else {
        low = middleIndex+1; 
        return search( nums, target, low,  high);

     }
	 }

	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode secondHead = dummyHead; 
        ListNode firstHead = dummyHead;
        
        for(int i = 1; i<=n ; i++){
            if(firstHead.next !=null)
            firstHead = firstHead.next;
        }
        System.out.print(firstHead.val);
        while (firstHead.next!=null){
            firstHead = firstHead.next; 
            secondHead =  secondHead.next;
        }
        secondHead.next = secondHead.next.next;
        return dummyHead.next;
        
    }
        
	public ListNode reverseList(ListNode head) {
         if(head ==null){
            return head;
        }
         ListNode previousNode = null;
        ListNode currentNode =  head; 
                
      while(currentNode!=null){ 
       ListNode  nextNode =  currentNode.next; 
         currentNode.next = previousNode; 
          previousNode = currentNode;
          currentNode = nextNode;
           
      }
        return previousNode;
    }

	public int searchInsert(int[] nums, int target) {
        int high = nums.length;
        int low = 0;
        while(low<high){
          int middle = (high+low)/2;
            if(target==nums[middle]){
                return middle;
            }else if(target>nums[middle]){
                low = middle+1;
            }else{
                high = middle;
            }
                
        }
       return low; 
    }

	public char nextGreatestLetter(char[] letters, char target) {
	      
        for(int i =0; i<letters.length; i++){
            if(letters[i]>target){
                return letters[i];
            }
        }
        return letters[0];
    }

	public int findPeakElement(int[] nums) {
		int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;  
    }

	public boolean searchMatrix(int[][] matrix, int target) {
		 int row = matrix.length-1;
	        int col = 0;

	        while (row >= 0 && col < matrix[0].length) {
	            if (matrix[row][col] > target) {
	                row--;
	            } else if (matrix[row][col] < target) {
	                col++;
	            } else { 
	                return true;
	            }
	        }

	        return false;  
    }

	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode dummyHead =  new ListNode(0);
	        ListNode result = dummyHead;
	        int carry = 0;
	        while (l1 != null || l2 != null){
	            int x = (l1!=null)?l1.val:0;
	            int y = (l2!=null)?l2.val:0;
	            int tempSum = x + y + carry;
	            carry = tempSum/10;
	            result.next = new ListNode(tempSum%10);
	            result =  result.next;
	            if(l1!=null)
	                l1=l1.next;
	            if(l2!=null)
	                l2=l2.next;
	           
	        }
	        if(carry>0){
	            result.next = new ListNode(carry);
	        }
	       return dummyHead.next; 
	    }

	 public int search(int[] nums, int target) {
	        int low = 0;
	        int high = nums.length;
	        
	         while(low<high){
	            
	             int middle = (low+high)/2;
	            
	             if(target==nums[middle]){
	                 return middle;
	             }else if(nums[middle]>=nums[low]){
	                if(target<nums[middle] && target>=nums[low]){
	                    high = middle;
	                }else{
	                    low=  middle+1;
	                }
	             }else{
	                 if(target>nums[middle] && target<=nums[high]){
	                    low= middle+1;
	                 }else{
	                    high = middle; 
	                 }
	             }
	                     
	                
	             }
	        return -1;
	         }

	 public void reorderList(ListNode head) {
		 ListNode slow = head, fast = head;
		    while (fast != null && fast.next != null) {
		      slow = slow.next;
		      fast = fast.next.next;
		    }

		    ListNode prev = null, curr = slow, tmp;
		    while (curr != null) {
		      tmp = curr.next;

		      curr.next = prev;
		      prev = curr;
		      curr = tmp;
		    }

		   
		    ListNode first = head, second = prev;
		    while (second.next != null) {
		      tmp = first.next;
		      first.next = second;
		      first = tmp;

		      tmp = second.next;
		      second.next = first;
		      second = tmp;
		    }  
	    }
}
