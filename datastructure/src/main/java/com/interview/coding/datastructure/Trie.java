package com.interview.coding.datastructure;

public class Trie {
	
	 private TrieNodeop root;

	    public Trie() {
	        root = new TrieNodeop();
	    }
	    
	    public void insert(String word) {
	        TrieNodeop node = root;
	        for (int i = 0; i < word.length(); i++) {
	            char currentChar = word.charAt(i);
	            if (!node.containsKey(currentChar)) {
	                node.put(currentChar, new TrieNodeop());
	            }
	            node = node.get(currentChar);
	        }
	        node.setEnd();
	    }
	
	public boolean search(String word) {
	       TrieNodeop node = searchPrefix(word);
	       return node != null && node.isEnd();
	    }
	
	private TrieNodeop searchPrefix(String word) {
        TrieNodeop node = root;
        for (int i = 0; i < word.length(); i++) {
           char curLetter = word.charAt(i);
           if (node.containsKey(curLetter)) {
               node = node.get(curLetter);
           } else {
               return null;
           }
        }
        return node;
    }
	
	public boolean startsWith(String prefix) {
        TrieNodeop node = searchPrefix(prefix);
        return node != null;
    }

}
