package com.aa.algo;

import com.aa.EDBase;

public class ClassicalDynamic extends EDBase {
	private int  distance[][] = null;
	
	@Override
	public int editDistance(String word1, String word2) {
		int n=word1.length()+1;
		int m=word2.length()+1;
		
		// initializing  matrix keeping distances
		if(distance == null){
			distance= new int[word1.length()+1][word2.length()+1];	
			for(int i=0;i<n;i++)
				distance[i][0]=i;
			
			for(int j=0;j<m;j++)
				distance[0][j]=j;
		}
		
		int delete =0;
		int insert =0;
		int subtitute = 0;
		char car1;
		char car2;
		int min=0;
		
		for(int i=1; i<n;i++){
			for(int j=1;j<m;j++){
				delete = distance[i-1][j] + deleteCost;
				insert = distance[i][j-1] + insertCost;
				car1=word1.charAt(i-1);
				car2=word2.charAt(j-1);
				if(car1!=car2){
					subtitute = distance[i-1][j-1] + replaceCost;
				}
				else{
					subtitute= distance[i-1][j-1];
				}
				
				min=0;
				if(delete<insert){
					min=delete;
				}
				else{
					min=insert;
				}
				if(subtitute<min){
					min=subtitute;
				}
				
				distance[i][j]=min;
			}
		}
		int number = distance[n-1][m-1];
		return number;
	}

}
