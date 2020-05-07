package com.snapshotprojects.Bingofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class TestCode {

	public static void main(String[] args) {
		/*
		 * //CREATING THE ARRAYLIST OF VALUES ArrayList<Integer> kgValue = new
		 * ArrayList<>(); ArrayList<String> colourValue = new ArrayList<>(); for(int i =
		 * 0 ; i < 10 ; i++) { kgValue.add(i); } colourValue.add("red");
		 * colourValue.add("orange");
		 * System.out.println("ArrayList of kgValues is : "+kgValue);
		 * System.out.println("ArrayList of colourValue is : "+colourValue);
		 * //////////////////////////////////////////////////
		 * 
		 * List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * 
		 * map.put("KG", kgValue); map.put("COLOUR",colourValue);
		 * System.out.println("map is : "+map);
		 * ///////////////////////////////////////////////
		 * 
		 * list.add(map); System.out.println("Final List of Map is : "+list);
		 */

		List<String> listToBeUpdated = new ArrayList<String>();
		listToBeUpdated.add("Apple");
		listToBeUpdated.add("Orange");
		listToBeUpdated.add("Orange");
		System.out.println("First list ======> "+listToBeUpdated);
		ArrayList<String> listOfItemsToBeAdded = new ArrayList<String>();
		listOfItemsToBeAdded.add("Orange");
		listOfItemsToBeAdded.add("Lemon");
		System.out.println("Second List ======> "+listOfItemsToBeAdded);
		ListIterator<String> ltr1 = listToBeUpdated.listIterator();
		ListIterator<String> ltr2 = listOfItemsToBeAdded.listIterator();
		while(ltr1.hasNext())
		{
			if(ltr1.next() == ltr2.next()) 
			{
				
			}
		}
		System.out.println("First List after updation ===> "+listToBeUpdated);
		
	}

}











