package com.travel.logic;

import com.travel.saveDB.BusServiceDB;

public class UserServiceRetrievedDataCheckForSplit {
	
	public static int totalStations;

	public static boolean main(String source, String destination) {

		String[] split = BusServiceDB.HaltStations.split(",");
		int sourceIndexValue = -1, destinationIndexValue = -1;
		totalStations = BusServiceDB.HaltStations.split(", ").length;
		System.out.println("Total stations between starting place and destination place : " + totalStations);
		String[] stationsArray = new String[totalStations];
		for (int i = 0; i < totalStations; i++) {
			stationsArray[i] = split[i];
			if (stationsArray[i].contains(source)) {
				System.out.println("source index : "+i);
				sourceIndexValue = i;
			}
		}

		for (int j = 0; j < totalStations; j++) {

			stationsArray[j] = split[j];
			if (stationsArray[j].contains(destination)) {
				System.out.println("destination index : "+j);
				destinationIndexValue = j;
			}
		}
		if (sourceIndexValue < destinationIndexValue) {
			System.out.println("proceed");
			return true;
		} else {
			System.out.println("not to proceed !!!");
			System.out.println("-----------------------------------");
		}
		return false;

	}
}
