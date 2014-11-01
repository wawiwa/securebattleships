package org.sooo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;

import ejb.domain.ShipLayout;

public class GsonTest {

	private Gson gson = new Gson();
	private Gson gsonSerializingNulls = new GsonBuilder().serializeNulls()
			.create();

	
	@Test
	public void boat() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("/Users/wward/Documents/GMU/SWE 681/gson-example-master/boatBoard.json"));

		String st = br.readLine();

		@SuppressWarnings("unchecked")
		Type collectionType = new TypeToken<List<ShipLayout>>() {}.getType();
		List<ShipLayout> sl = gson.fromJson(st, collectionType);

		// JSON string that is read in from the file
		System.out.println("Original String: " + st);
		
		// number of objects in the json string array
		System.out.println("Number of Objects: " + sl.size());
		
		// tostring of the json converted into the ship object
		System.out.println("ToString of Json Object: "+ sl);

		// reversing object to json
		String arrayListToJson = gson.toJson(sl);
		System.out.println("Converted Back: "  + arrayListToJson);
		
	}

}
