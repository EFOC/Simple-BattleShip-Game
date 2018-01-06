package Battle_Ship;

import java.util.ArrayList;

public class ExistingPositions {
	
	ArrayList<String> existingPositions = new ArrayList<>();
	
	
	
	public ExistingPositions(){}
	
	public void insertPosition(String position){
		existingPositions.add(position);
	}
	
	public boolean doesPositionExist(String newPosition){
		if(existingPositions.isEmpty()){ return false; }
		for(String temp: existingPositions){
			if(newPosition.equals(temp)) return true;
		}
		return false;
	}
}
