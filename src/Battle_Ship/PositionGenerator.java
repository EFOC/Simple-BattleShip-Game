package Battle_Ship;

import java.util.ArrayList;
import java.util.Random;

public class PositionGenerator {

	private int 	minRow 			 = 1;
	private int 	maxRow 			 = 5;
	private byte 	minLetterDecimal = 97; //This is the decimal value for 'a'
	private byte 	maxLetterDecimal = 103; //This is the decimal value for 'g'
	private boolean vertical;
	private Random 	randomChar 		 = new Random();
	private int 	numOfPositions 	 = 3;
	private char 	column;
	private int 	row;
	
	private ArrayList<Integer> 	 rowLocation 	   = new ArrayList<>();
	private ArrayList<Character> columnLocation    = new ArrayList<>();
	private ArrayList<String> 	 positionLocation  = new ArrayList<>();
	private ExistingPositions    existingPosition  = new ExistingPositions();
	
	public PositionGenerator(){
		ExistingPositions xp = new ExistingPositions();
	}
	
//	This generates a position for the object. Example: 'a3'
	public ArrayList<String> generatePosition(){
		rowLocation 	 = new ArrayList<>();
		columnLocation   = new ArrayList<>();
		positionLocation = new ArrayList<>();
		vertical 		 =  randomChar.nextBoolean();//Randomly selects if it will be vertical, if false, it will be horizontal

//		Generating positions
		{			
			column   = (char)(randomChar.nextInt(maxLetterDecimal - minLetterDecimal) + minLetterDecimal); //Needs to be char to be able to increment
			row      = (randomChar.nextInt((maxRow - minRow) + 1) + minRow);			
		}while(existingPosition.doesPositionExist(String.valueOf(column)+String.valueOf(row)));//If it returns true, it means position already exists and will loop till it finds a position that has not been filled yet 

//		Generates the rest of the 'ships' positions
		for(int i = 0; i < numOfPositions; i++){
			if(vertical){ 
				positionLocation.add(String.valueOf(column++)+String.valueOf(row));
				existingPosition.insertPosition(String.valueOf(column)+String.valueOf(row));
			}else{
				positionLocation.add(String.valueOf(column)+String.valueOf(row++));
				existingPosition.insertPosition(String.valueOf(column)+String.valueOf(row));
			}
		}
		return positionLocation;
	}
}
