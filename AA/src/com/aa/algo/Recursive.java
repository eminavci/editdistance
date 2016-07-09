package com.aa.algo;

import com.aa.EDBase;

/*
 * Insert : problem reduces to finding Cost ( i , j - 1 ) + INSERT_COST
                i.e we insert the mismatched character and add the cost of insertion.
    Delete : problem reduces to finding Cost ( i - 1 , j ) + DELETE_COST
    Replace : problem reduces to finding Cost ( i - 1 , j - 1 ) + REPLACE_COST
 * */
public class Recursive extends EDBase{

	@Override
	public int editDistance(String word1, String word2) {

		/* 	
		 * If first string is empty, the only option is to
	     * insert all characters of second string into first
	    */
		if (word1.isEmpty()) 
			return word2.length();
		
		/* 	
		 * If second string is empty, the only option is to
		 * remove all characters of first string
	    */
	    if (word2.isEmpty()) 
	    	return word1.length();

	    /* If last characters are not same, consider all three 
	     * operations on last character of first string, recursively
	     * compute minimum cost for all three operations and 
	     * take minimum of three values. 
	    */
	    int replace = editDistance(word1.substring(1), word2.substring(1)) + ((word1.charAt(0) == word2.charAt(0)) ? 0 : replaceCost);
	    int delete = editDistance(word1.substring(1), word2) + deleteCost;
	    int insert = editDistance(word1, word2.substring(1)) + insertCost;
	    
	    return Math.min(Math.min(replace, delete), insert);
	}
}
