package com.aa.algo;

import com.aa.EDBase;

public class RandomizedVersion extends EDBase {
	
	
	@Override
	public int editDistance(String word1, String word2) {
		if(word1.length()>=word2.length()){
			return (int) (Math.random()* word1.length()-word2.length()+1)+word2.length();
		}
		else{
			return (int) (Math.random()* word2.length()-word1.length()+1)+word1.length();
		}
	}

}
