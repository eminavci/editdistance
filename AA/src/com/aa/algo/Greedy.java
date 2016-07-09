package com.aa.algo;

import com.aa.EDBase;

public class Greedy extends EDBase{
	private int  distance[][] = null;
	
	public int[][] getDistance() {
		return distance;
	}
	public void setDistance(int[][] distance) {
		this.distance = distance;
	}
	@Override
	public int editDistance(String word1, String word2) {
		int k=0;
		if(word1.length()>=word2.length()){
			k=word1.length()- word2.length();
			for(int i=0;i<word2.length();i++){
				if(word1.charAt(i)!=word2.charAt(i)){
					k=k+1;
				}
			}
		}
		else{
			k=word2.length()- word1.length();
			for(int j=0;j< word1.length();j++){
				if(word1.charAt(j)!=word2.charAt(j)){
					k=k+1;
				}
			}
		}
		return k;
	}
	
	
}
