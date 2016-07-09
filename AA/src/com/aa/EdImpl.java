package com.aa;

import java.util.Map;

import com.aa.algo.DivideAndConquer;
import com.aa.algo.SizeKAround;

public class EdImpl {
	
	public static void main(String[] args) {
		
		long start, end;
		
		String word1 = "abcertrew";
		String word2 = "xyzerwtttweert";
		
		EditDistance ed = new SizeKAround();
		
		start = System.currentTimeMillis();
		System.out.println("Min Operation for ED : " + ed.editDistance(word1, word2));
		end = System.currentTimeMillis();
		System.out.println("TIME : " + (end-start)/1000);
	}

}
