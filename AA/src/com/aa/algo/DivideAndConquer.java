package com.aa.algo;

import com.aa.EDBase;

public class DivideAndConquer extends EDBase {
	static int[][] matrix;

	@Override
	public int editDistance(String word1, String word2) {
		matrix = new int[word1.length() + 1][word2.length() + 1];
		int sol1, sol2, sol3;
		int i, j;

		for (i = 0; i <= word1.length(); i++)
			matrix[i][0] = i;
		for (j = 0; j <= word2.length(); j++)
			matrix[0][j] = j;
		/*
		 * ------------------------ Divide step ------------------------
		 */
		for (i = 1; i <= word1.length(); i++){
			for (j = 1; j <= word2.length(); j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					sol1 = matrix[i - 1][j - 1];
					matrix[i][j] = sol1;
				} else {
					// Step : DIVIDE
					sol1 = matrix[i - 1][j]; // delete step as last
					sol2 = matrix[i][j - 1]; // insert step as last
					sol3 = matrix[i - 1][j - 1]; // replace step as last

					// Step : CONQUER (solvimg original problem using solution
					// from smaller-sub problems)
					sol1 = sol1 + deleteCost;
					sol2 = sol2 + insertCost;
					sol3 = sol3 + replaceCost;
					// deciding which solution has minimum cost ***************
					if (sol1 <= sol2 && sol1 <= sol3)
						matrix[i][j] = sol1;

					if (sol2 <= sol1 && sol2 <= sol3)
						matrix[i][j] = sol2;

					if (sol3 <= sol1 && sol3 <= sol2)
						matrix[i][j] = sol3;
				}
			}
		}
		//printTheMatrix(word1, word2);
		return (matrix[word1.length()][word2.length()]);
	}

	public void printTheMatrix(String word1, String word2){
		System.out.print("     ");
		for (int j = 0; j < word2.length()+1; j++)
	          System.out.print("  " + j);
	       System.out.println();
	       System.out.println("==================================");

	       for (int i = 0; i < word1.length()+1; i++){
	          if ( i < 10 )
	             System.out.print(" ");
	          System.out.print(" " + i + "  ");

	          for (int j = 0; j < word2.length()+1; j++)
	             System.out.print("  " + matrix[i][j]);
	          System.out.println();
	       }
	}
}
