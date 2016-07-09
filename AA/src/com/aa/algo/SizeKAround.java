package com.aa.algo;

import com.aa.EDBase;

public class SizeKAround extends EDBase{
	private int  distance[][];
	private int k;
	
	
	@Override
	public int editDistance(String word1, String word2) {
		
		int n=word1.length()+1;
		int m=word2.length()+1;
		
		distance= new int[n+1][m+1];
		for(int i=0;i<n;i++){
			distance[i][0]=i;
		}
		for(int j=0;j<m;j++){
			distance[0][j]=j;
		}
		
		int delete =0;
		int insert =0;
		int subtitute = 0;
		char car1;
		char car2;
		int min=0;
		int tall=0;
		if(n>=m){
			tall=word1.length()-1;
			k = m-2;
		} else {
			tall=word2.length()-1;
			k = n-2;
		}
		System.out.println("K : " + k + " WORD1 SIZE : " + n + " WORD2 SIZE : " + m);
		if(k<=0){
			System.out.println("K must be higher than0");
		} else if(k>tall) {
			System.out.println("K can't be more than "+(tall));
		}else{
			for(int i=1; i<tall+1;i++){
				for(int j=i;j<k;j++){
					delete = distance[i-1][j]+1;
					insert = distance[i][j-1]+1;
					car1=word1.charAt(i-1);
					car2=word2.charAt(j-1);
					if(car1!=car2){
						subtitute = distance[i-1][j-1]+2;
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
		return 0;
	}
	
	public int[][] getDistance() {
		return distance;
	}
	public void setDistance(int[][] distance) {
		this.distance = distance;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
}
