package com.interview.coding.datastructure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {
	
	 public String serialize(TreeNode root) {
		    return rserialize(root, "");
		  }
	 
	 private String rserialize(TreeNode root, String str) {
		    
		    if (root == null) {
		      str += "null,";
		    } else {
		      str += str.valueOf(root.val) + ",";
		      str = rserialize(root.left, str);
		      str = rserialize(root.right, str);
		    }
		    return str;
		  }
	 
	 public TreeNode deserialize(String data) {
		    String[] data_array = data.split(",");
		    List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
		    return rdeserialize(data_list);
		  }
	 
	 private TreeNode rdeserialize(List<String> l) {
		   
		    if (l.get(0).equals("null")) {
		      l.remove(0);
		      return null;
		    }

		    TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
		    l.remove(0);
		    root.left = rdeserialize(l);
		    root.right = rdeserialize(l);

		    return root;
		  }

}
