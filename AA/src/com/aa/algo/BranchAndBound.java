package com.aa.algo;

import com.aa.EDBase;

/**
 * Branch & Bound
 * 
 * It realizes that it already has a better optimal solution that the pre-solution leads to so it abandons that pre-solution.
 * It completely searches the state space tree to get optimal solution.
 * we cut some branches of the tree to reduce the search space of the solutions(try not to compute again already found an optimal sol for the substring fo words)
 */
public class BranchAndBound extends EDBase {
	int myarr[][] = null;
	
	@Override
	public int editDistance(String word1, String word2) {

		// init array with -1 cost, which means not calculated yet. not found
		// any optimal solution yet.
		if (myarr == null) {
			myarr = new int[word1.length() + 1][word2.length() + 1];
			for (int i = 0; i < myarr.length; i++)
				for (int j = 0; j < myarr[i].length; j++)
					myarr[i][j] = -1;
		}
		if (myarr[word1.length()][word2.length()] != -1)
			return myarr[word1.length()][word2.length()];

		if (word1.isEmpty())
			return word2.length();
		else if (word2.isEmpty())
			return word1.length();

			int insert = editDistance(word1, word2.substring(0, word2.length() - 1)) + insertCost;
		
			int delete = editDistance(word1.substring(0, word1.length() - 1), word2) + deleteCost;
	
			int replace = editDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1))
					+ (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1) ? 0 : replaceCost);
	
			int cost = Math.min(Math.min(insert, delete), replace);
			myarr[word1.length()][word2.length()] = cost;
		return cost;
	}

}
