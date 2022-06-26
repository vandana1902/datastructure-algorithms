package com.interview.coding.datastructure;

import java.util.LinkedList;

public class RecentCounter {
	
	 LinkedList<Integer> slideWindow;
	
	public RecentCounter() {
        this.slideWindow = new LinkedList<Integer>();
    }

    public int ping(int t) {
       
        this.slideWindow.addLast(t);

       
        while (this.slideWindow.getFirst() < t - 3000)
            this.slideWindow.removeFirst();

        return this.slideWindow.size();
    }

}
