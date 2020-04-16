package com.snapshotprojects.Bingofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCode {

	public static void main(String[] args) {
		//CREATING THE ARRAYLIST OF VALUES
		ArrayList<Integer> kgValue = new ArrayList<>();
		ArrayList<String> colourValue = new ArrayList<>();
		for(int i = 0 ; i < 10 ; i++) {
			kgValue.add(i);
		}
		colourValue.add("red");
		colourValue.add("orange");
		System.out.println("ArrayList of kgValues is : "+kgValue);
		System.out.println("ArrayList of colourValue is : "+colourValue);
		//////////////////////////////////////////////////
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("KG", kgValue);
		map.put("COLOUR",colourValue);
		System.out.println("map is : "+map);
		///////////////////////////////////////////////
		
		list.add(map);
		System.out.println("Final List of Map is : "+list);

	}

}
