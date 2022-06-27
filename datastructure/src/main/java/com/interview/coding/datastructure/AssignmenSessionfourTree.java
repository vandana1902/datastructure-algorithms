package com.interview.coding.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class AssignmenSessionfourTree {
	
	public List<Integer> rightSideView(TreeNode root) {
	      List<Integer> resultList = new ArrayList<>();
	        if(root==null)
	            return resultList;
	       dfs(root, resultList, 0);
	        return resultList;
	           
	    }
	    
	    private void dfs(TreeNode node, List<Integer>list,int level){
	        if (level == list.size()) 
	            list.add(node.val);
	        
	        if (node.right != null) 
	            dfs(node.right, list, level + 1);  
	        if (node.left != null) 
	            dfs(node.left, list, level + 1);
	            
	         
	    }


	    public List<List<Integer>> levelOrder(TreeNode root) {
	        List<List<Integer>> resultList = new ArrayList<>();
	        if(root==null){
	            return resultList;
	        }
	    Queue<Pair<TreeNode, Integer>> treeQueue = new ArrayDeque<>();
	       treeQueue.add(new Pair(root,1)); 
	        
	         int currLevel =  0;    
	        while(!treeQueue.isEmpty()){
	         Pair<TreeNode, Integer> pairNode = treeQueue.poll();
	            TreeNode node = pairNode.getKey(); 
	            int level = pairNode.getValue();
	         if(level>currLevel) {
	             List<Integer> list = new ArrayList<>();
	             resultList.add(list);
	             currLevel ++;
	         } 
	           resultList.get(resultList.size()-1).add(node.val);
	            if(node.left != null)
	           treeQueue.add(new Pair(node.left,level+1)); 
	            if(node.right!=null)
	            treeQueue.add(new Pair(node.right,level+1));
	        }
	       return resultList;     
	        
	    }

	    public TreeNode invertTree(TreeNode root) {
	        return dfs(root);
	    }
	    
	    private TreeNode dfs(TreeNode node){
	       if (node==null){
	            return node;
	        }
	        TreeNode nodeRight = dfs(node.right);  
	        TreeNode nodeLeft = dfs(node.left);  
	        node.left = nodeRight;
	        node.right = nodeLeft;
	        return node;
	        
	        
	    }

	    
	    public int maxDepth(TreeNode root) {
	        if(root==null)
	            return 0;
	        
	        return dfsDepth(root);
	    }
	    
	    private int dfsDepth(TreeNode node){
	        int maxHeight = 0;
	        if(node==null){
	            return 0;
	        }
	        int rightHeight = dfsDepth(node.right);
	        int leftHeight = dfsDepth(node.left);
	        maxHeight = Math.max(rightHeight, leftHeight)+1;
	        return maxHeight;
	    }


	    public boolean isBalanced(TreeNode root) {
	        
	        return dfsBalanced(root) != -1;
	         
	     }
	     
	    private int dfsBalanced(TreeNode node){ 
	         int height = 0;
	         if(node==null){
	             return height;
	         }
	        int heightLeft = dfsBalanced(node.left); 
	         int heightRight = dfsBalanced(node.right); 
	         if(heightLeft <0 || heightRight<0){
	             return -1;
	         }
	         if(Math.abs(heightRight-heightLeft) > 1){
	            return -1; 
	         }
	        
	         height = Math.max(heightLeft, heightRight)+1;
	         return height;
	     }
	     
	     int maxSum = Integer.MIN_VALUE;
	     public int maxPathSum(TreeNode root) {
	         if(root == null)
	             return 0;
	         
	         dfsPathSum(root);
	         
	       return maxSum;  
	     }
	     
	     private int dfsPathSum(TreeNode node){
	         
	         if(node==null){
	             return 0;
	         }
	         int leftMaxSum = Math.max(dfsPathSum(node.left),0);
	         int rightMaxSum = Math.max(dfsPathSum(node.right),0);
	         int currSum = node.val+leftMaxSum+rightMaxSum;
	         maxSum = Math.max(maxSum,currSum);
	         return node.val+Math.max(leftMaxSum, rightMaxSum);
	           
	         
	     }

	     TreeNode ans= null;
	     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		        this.dfsLowestCommonAncestor(root, p, q);
		         return this.ans;
		     }
	     private boolean dfsLowestCommonAncestor(TreeNode currentNode, TreeNode p, TreeNode q) {

	         if (currentNode == null) {
	             return false;
	         }

	         int left = this.dfsLowestCommonAncestor(currentNode.left, p, q) ? 1 : 0;

	         int right = this.dfsLowestCommonAncestor(currentNode.right, p, q) ? 1 : 0;

	         int mid = (currentNode == p || currentNode == q) ? 1 : 0;

	         if (mid + left + right >= 2) {
	             this.ans = currentNode;
	         }

	         return (mid + left + right > 0);
	     }
 
	     
	     int minCameraNum;
	     Set<TreeNode> covered;
	     public int minCameraCover(TreeNode root) {
	    	 minCameraNum = 0;
	         covered = new HashSet();
	         covered.add(null);

	         dfs(root, null);
	         return minCameraNum;
	     }

	     public void dfs(TreeNode node, TreeNode par) {
	         if (node != null) {
	             dfs(node.left, node);
	             dfs(node.right, node);

	             if (par == null && !covered.contains(node) ||
	                     !covered.contains(node.left) ||
	                     !covered.contains(node.right)) {
	            	 minCameraNum++;
	                 covered.add(node);
	                 covered.add(par);
	                 covered.add(node.left);
	                 covered.add(node.right);
	             }
	         }
	     }
}
