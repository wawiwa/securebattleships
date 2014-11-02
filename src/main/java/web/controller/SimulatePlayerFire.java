package web.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ejb.domain.OutgoingRounds;
import ejb.domain.ShipLayout;

public class SimulatePlayerFire {
	
	//incoming string
//	[
//	  {
//	    "RoundType": "outgoing",
//	    "From": "player1",
//	    "To": "player2",
//	    "Rounds": [
//	      {
//	        "isHit": null,
//	        "coords": "C5"
//	      },
//	      {
//	        "isHit": null,
//	        "coords": "D4"
//	      },
//	      {
//	        "isHit": null,
//	        "coords": "E5"
//	      },
//	      {
//	        "isHit": null,
//	        "coords": "F5"
//	      },
//	      {
//	        "isHit": null,
//	        "coords": "G5"
//	      }
//	    ]
//	  }
//	]	
	
	
	
	// result that will be returned
//	{
//		  "RoundType": "outgoing",
//		  "From": "player1",
//		  "To": "player2",
//		  "Rounds": [
//		    {
//		      "isHit": true,
//		      "coords": "C2"
//		    },
//		    {
//		      "isHit": true,
//		      "coords": "D4"
//		    },
//		    {
//		      "isHit": false,
//		      "coords": "E5"
//		    },
//		    {
//		      "isHit": false,
//		      "coords": "J5"
//		    },
//		    {
//		      "isHit": false,
//		      "coords": "G5"
//		    }
//		  ]
//	}	
	
	
	public static void main(String args[]) throws IOException {
		
		Gson gson = new Gson();
		
		BufferedReader br1 = new BufferedReader(new FileReader("/Users/JackYoung/Desktop/securebattleships/tempJSON/player1_ShipPlacement.json"));
		BufferedReader br2 = new BufferedReader(new FileReader("/Users/JackYoung/Desktop/securebattleships/tempJSON/player2_ShipPlacement.json"));
		BufferedReader br3 = new BufferedReader(new FileReader("/Users/JackYoung/Desktop/securebattleships/tempJSON/player1_outgoingRound.json"));

		String player1Ships_str = br1.readLine();
		String player2Ships_str = br2.readLine();
		String player1outgoingRoung_str = br3.readLine();
		
		// Outputting the JSON data that was read in
		System.out.println(player1Ships_str);
		System.out.println(player2Ships_str);
		System.out.println(player1outgoingRoung_str);
		
		@SuppressWarnings("unchecked")
		Type collectionType1 = new TypeToken<List<ShipLayout>>() {}.getType();
		Type collectionType2 = new TypeToken<List<OutgoingRounds>>() {}.getType();
		
		// Converting the JSON strings into objects
		List<ShipLayout> player1_ships = gson.fromJson(player1Ships_str, collectionType1);
		List<ShipLayout> player2_ships = gson.fromJson(player1Ships_str, collectionType1);
		List<OutgoingRounds> outgoingRounds = gson.fromJson(player1outgoingRoung_str, collectionType2);
		
		
		OutgoingRounds checkedResults = compareOutgoingRoundsToOpponentsShips(outgoingRounds.get(0), player2_ships);
		
		System.out.println("\n");
		System.out.println(gson.toJson(checkedResults));
		// return gson.toJson(checkedResults);
		
	}
	
	public static OutgoingRounds compareOutgoingRoundsToOpponentsShips(OutgoingRounds outgoingRounds, List<ShipLayout> opponent) {
		
		// Creating an aggregated list of coords for opponents ships
		List<String> shipCoords = new ArrayList<String>();
		for (int i = 0; i < opponent.size(); i++) {
			int tempLength = opponent.get(i).shipCords.length;
			for (int j = 0; j < tempLength; j++) {
				shipCoords.add(opponent.get(i).getShipCords()[j]);
			}
		}
		
		// Checking outgoing rounds against opponent ship coords
		for (int i = 0; i < outgoingRounds.getRounds().length; i++) {
			if (shipCoords.contains(outgoingRounds.getRounds()[i].getCoords())) {
				outgoingRounds.getRounds()[i].setHit(true);
			} else {
				outgoingRounds.getRounds()[i].setHit(false);
			}
		}
		
		return outgoingRounds;
		
	}
	
	

}
