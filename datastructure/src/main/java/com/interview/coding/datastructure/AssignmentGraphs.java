package com.interview.coding.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AssignmentGraphs {

	 public Node cloneGraph(Node node) {
	        Map<Node, Node>visited = new HashMap<>();
	        return dfsClone(node, visited);
	    }
	     
	    private Node dfsClone(Node root, Map<Node, Node>visited){
	        if(root == null){
	            return root;
	        }
	       if(visited.containsKey(root)){
	           return visited.get(root);
	       } 
	        
	        Node cloneNode = new Node(root.val, new ArrayList<Node>());
	        visited.put(root, cloneNode);
	        
	        for(Node neighbor : root.neighbors){
	            cloneNode.neighbors.add(dfsClone(neighbor, visited));
	        }
	        
	        return cloneNode;
	        
	    }

	    public int maxAreaOfIsland(int[][] grid) {
	        int maxArea = 0;
	       
	        for(int row = 0; row<grid.length; row++){
	            for(int col = 0; col<grid[0].length; col++){
	                if(grid[row][col]==1){
	                    int currArea = dfsMaxArea(row, col, grid);
	                    maxArea = Math.max(maxArea, currArea);
	                }
	            }
	        }
	        return maxArea;
	        
	    }
	    
	    private int dfsMaxArea(int row, int col, int[][]grid){
	        if(row==grid.length || col==grid[0].length || row<0 || col<0){
	            return 0;
	        }
	        if(grid[row][col]==0 || grid[row][col]==2){
	            return 0;
	        }
	         
	        grid[row][col]=2;
	        
	        int currAreaRight = dfsMaxArea(row, col+1, grid);
	        int currAreaLeft = dfsMaxArea(row, col-1, grid);
	        int currAreaDown = dfsMaxArea(row+1, col, grid);
	        int currAreaUp = dfsMaxArea(row-1, col, grid);
	        
	        return currAreaRight+currAreaLeft+currAreaDown+currAreaUp+1;
	    }

	    public int findJudge(int N, int[][] trust) {
	        
	        if (trust.length < N - 1) {
	            return -1;
	        }
	        
	        int[] indegrees = new int[N + 1];
	        int[] outdegrees = new int[N + 1];

	        for (int[] relation : trust) {
	            outdegrees[relation[0]]++;
	            indegrees[relation[1]]++; 
	        }

	        for (int i = 1; i <= N; i++) {
	            if (indegrees[i] == N - 1 && outdegrees[i] == 0) {
	                return i;
	            }
	        }
	        return -1;
	    }

	    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
	        
	        double [] result = new double[queries.size()];
	        Map<String, List<Pair<String,Double>>> graph = new HashMap<>();
	        for(int i=0; i<equations.size();  i++){
	          addConnection(graph, equations.get(i).get(0), equations.get(i).get(1), values[i]) ;
	          
	          addConnection(graph, equations.get(i).get(1), equations.get(i).get(0), 1/values[i]) ;
	  
	        }
	        for(int i =0; i<queries.size();i++){
	             Set<String> set = new HashSet<>();
	         result [i] = dfs(graph, queries.get(i).get(0), queries.get(i).get(1), 1, set);
	        }
	        return result;
	    }
	    
	    private void addConnection(Map<String, List<Pair<String,Double>>> graph, String a, String b, double value){
	         List<Pair<String,Double>> list = null;
	         if(graph.get(a)!=null){
	               list = graph.get(a);
	           }else{
	               list = new ArrayList<>();
	           }
	            Pair<String,Double> pair = new Pair(b, value);
	            list.add(pair);
	            graph.put(a, list);
	        
	        
	    }
	    
	    private double dfsDivision(Map<String, List<Pair<String,Double>>> graph, String curr, String target, double value, Set<String> set){
	    
	        double valueCurr = -1.0;
	        if(!graph.containsKey(curr) || !graph.containsKey(target)){
	            return -1.0;
	        }
	        
	        if(curr.equalsIgnoreCase(target)){
	            return value;
	        }
	        set.add(curr);
	        List<Pair<String, Double>> listCurr = graph.get(curr);
	        for(int i=0; i <listCurr.size(); i++){
	            if(!set.contains(listCurr.get(i).getKey())){
	        double valTemp =   dfs(graph, listCurr.get(i).getKey(), target , listCurr.get(i).getValue()*value, set) ;     valueCurr = Math.max(valTemp, valueCurr);  
	            }
	        }
	       return valueCurr;   
	     
	    }

	    public int orangesRotting(int[][] grid) {
	    	   if(grid==null || grid.length==0){
	    	       return -1;
	    	   }
	    	     int countFresh = 0;
	    	        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
	    	        int row = grid.length;
	    	        int col = grid[0].length;
	    	        for(int r = 0; r<row; r++){
	    	            for(int c = 0; c<col; c++){
	    	                if(grid[r][c]==2){
	    	                    queue.add(new Pair(r,c));
	    	                }else if(grid[r][c]==1){
	    	                    countFresh++;
	    	                }
	    	            }
	    	        }
	    	        if(countFresh==0){
	    	            return 0;
	    	        }
	    	        int countMinutes =0;
	    	        int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
	    	        while(!queue.isEmpty()){
	    	            countMinutes++;
	    	            
	    	            int size =queue.size();
	    	            for(int i=0; i<size; i++){
	    	                Pair<Integer, Integer> pair = queue.poll();
	    	                
	    	                for(int[] dir : dirs){
	    	                   int r = pair.getKey()+dir[0];
	    	                int c = pair.getValue()+dir[1]; 
	    	                    if(r<0 || r>=row || c<0 || c>=col || grid[r][c]==0 || grid[r][c]==2){
	    	                        continue;
	    	                    }
	    	                    grid[r][c]=2;
	    	                    queue.add(new Pair(r,c));
	    	                    countFresh--;
	    	                }
	    	            }
	    	            
	    	        }
	    	        return countFresh==0?countMinutes-1:-1;
	    	    }

	    public int findCircleNum(int[][] isConnected) {
	        int[] visited = new int[isConnected.length];
	        int count = 0;
	        for (int i = 0; i < isConnected.length; i++) {
	            if (visited[i] == 0) {
	            	dfsCircleNum(isConnected, visited, i);
	                count++;
	            }
	        }
	        return count;
	    }
	    public void dfsCircleNum(int[][] M, int[] visited, int i) {
	        for (int j = 0; j < M.length; j++) {
	            if (M[i][j] == 1 && visited[j] == 0) {
	                visited[j] = 1;
	                dfsCircleNum(M, visited, j);
	            }
	        }
	    }

	    public boolean isBipartite(int[][] graph) {
	        int n = graph.length;
	        int[] color = new int[n];
	        Arrays.fill(color, -1);

	        for (int start = 0; start < n; ++start) {
	            if (color[start] == -1) {
	                Stack<Integer> stack = new Stack();
	                stack.push(start);
	                color[start] = 0;

	                while (!stack.empty()) {
	                    Integer node = stack.pop();
	                    for (int nei: graph[node]) {
	                        if (color[nei] == -1) {
	                            stack.push(nei);
	                            color[nei] = color[node] ^ 1;
	                        } else if (color[nei] == color[node]) {
	                            return false;
	                        }
	                    }
	                }
	            }
	        }

	        return true;
	    }

	    public boolean canFinish(int numCourses, int[][] prerequisites) {
	    	   Map<Integer, List<Integer>> graph = new HashMap<>();
	    	        int[] inDegree = new int[numCourses];
	    	        Queue<Integer> visited = new LinkedList();
	    	        List<Integer> list = null;
	    	        for(int [] preq : prerequisites){
	    	            list = graph.containsKey(preq[1])?graph.get(preq[1]):new ArrayList<>();
	    	            list.add(preq[0]);
	    	            graph.put(preq[1], list);
	    	            inDegree[preq[0]]++;
	    	        }
	    	        
	    	        int count=0;
	    	        
	    	        for(int i=0; i<numCourses; i++){
	    	            if(inDegree[i]==0){
	    	                visited.add(i);
	    	            }
	    	        }
	    	        
	    	        while(!visited.isEmpty()){
	    	            int num = visited.poll();
	    	            count++;
	    	            if(graph.containsKey(num)){
	    	             for(int nei : graph.get(num)){
	    	                 inDegree[nei]--;
	    	                 if(inDegree[nei]==0){
	    	                     visited.add(nei);
	    	                 }
	    	             }   
	    	            }
	    	        }
	    	        return count==numCourses;
	    	        
	    	}

	    public int[] findOrder(int numCourses, int[][] prerequisites ) {
	        Map<Integer, List<Integer>> graph = new HashMap<>();
	        int [] colors = new int[numCourses]; 
	         int [] result = new int[numCourses];
	        Stack<Integer> stack = new Stack<Integer>();
	        List<Integer> list = null;
	        for(int [] courses : prerequisites){
	            list = graph.containsKey(courses[1])?graph.get(courses[1]):new ArrayList<>();
	            list.add(courses[0]); 
	            graph.put(courses[1], list); 
	        }
	        for(int i = 0; i<numCourses; i++){ 
	            colors [i] =1;
	        }
	        for(int i=0; i<numCourses; i++){
	           if(!dfs(graph, i, stack, colors))
	             return new int [0];
	        }
	        for(int i =0; i<numCourses;i++){
	            result[i]=stack.pop();
	        }
	      return result;  
	    }
	    
	    private boolean dfsFindOrder(Map<Integer, List<Integer>> graph, int value, Stack<Integer> stack, int[]colors){
	      
	        if(colors[value]==3){
	            return true;
	        }
	        if(colors[value] == 2){
	            return false;
	        }
	        colors[value]=2;
	        
	        if(graph.containsKey(value)){ 
	            
	            List<Integer> list = graph.get(value); 
	            for(int i : list){
	          if(!dfs (graph, i, stack, colors)) 
	              return false;
	                         
	            }
	           
	        }
	        colors[value]=3;
	        stack.push(value);
	      return true;  
	        
	    }

	    
	    Set<Integer> seen = new HashSet();
	    int MAX_EDGE_VAL = 1000;

	    public int[] findRedundantConnection(int[][] edges) {
	        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
	        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
	            graph[i] = new ArrayList();
	        }

	        for (int[] edge: edges) {
	            seen.clear();
	            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
	                    dfs(graph, edge[0], edge[1])) {
	                return edge;
	            }
	            graph[edge[0]].add(edge[1]);
	            graph[edge[1]].add(edge[0]);
	        }
	        throw new AssertionError();
	    }
	    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
	        if (!seen.contains(source)) {
	            seen.add(source);
	            if (source == target) return true;
	            for (int nei: graph[source]) {
	                if (dfs(graph, nei, target)) return true;
	            }
	        }
	        return false;
	    }

}
