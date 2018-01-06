package Battle_Ship;

import java.util.ArrayList;
import java.util.Random;

//This class creates the objects that the user will try to 'destroy' in the game

public class DotCom {
	private boolean isAlive = true; //When the object gets created, it becomes alive
	private ArrayList<String> positionLocation = new ArrayList<>(); // ArrayList to hold the location positions of the object
	private String name; // The name of the object

	//SETTERS AND GETTERS
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	public boolean getIsAlive(){
		return this.isAlive;
	}
	
	public void setLocation(ArrayList<String> _position){
		if(_position.isEmpty()){
			System.out.println("No Room for the object");
		}else{
			positionLocation = new ArrayList<>(_position);
		}
	}
	
//	CONSTRUCTORS
	public DotCom(String name){
		this.name = name;
	}
	
//	OTHER METHODS
	public void checkingStatus(){
		this.isAlive = positionLocation.isEmpty() ? false : true;
		if(!this.isAlive){
			System.out.println("You have took down " + this.name);
		}
	}
	public boolean checkingHit(String userGuess){
		for(String position : positionLocation){
			if(userGuess.equals(position)){
				positionLocation.remove(position);
				checkingStatus();
				return true;
			}
		}
		return false;
	}
	
	public void printLocation(){
		System.out.println("Name: " + this.name);
		System.out.println("Location: " + this.positionLocation);
	}
	//ArrayList methods
	public ArrayList<String> getLocation(){
		return positionLocation;
	}
}
