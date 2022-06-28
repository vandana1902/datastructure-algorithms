package com.interview.coding.datastructure;

public class TrieNodeop {
	
	private TrieNodeop[] links;

    private final int R = 26;

    private boolean isEnd;

    public TrieNodeop() {
        links = new TrieNodeop[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNodeop get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNodeop node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
