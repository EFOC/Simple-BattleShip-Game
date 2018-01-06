package Battle_Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
/*	This class sets up the game. Creates players, game, 
 * 
 * 
 * 
 * 
 */

public class DotComBust {
//	Arrays
//	private ArrayList<DotCom> dotComList 	= new ArrayList();
	private String[] botNames				= {"askMe","Pets","go2","Lobo"};
	private DotCom[] dotComCollection;
//	Non-array variables
	private int numOfGuess 					= 0;
	private Configurations config 			= new Configurations();
	
	DotComBust(){
		mainMenu();
	}
	
	public void mainMenu(){
		int selection = 0;
		while(selection != -1){
			System.out.println("Welcome to BattleShip");
			System.out.println("1: Start game");
			System.out.println("2: Configure settings");
			System.out.println("3: Exit game");
			Scanner sc = new Scanner(System.in);
			selection = sc.nextInt();
			if(selection == 1){
				setUpGame();
				startPlaying();
			}else if(selection == 2){
				config.settingsMenu();
			}else if(selection == 3){
				System.out.println("Exiting...");
				System.exit(0);
			}
		}
	}
	
//	Sets up the game by reading the file and creating the objects
	public void setUpGame(){
		System.out.println("Starting game...");
		dotComCollection = new DotCom[config.getNumOfBots()];
		PositionGenerator generatePosition = new PositionGenerator();
		for(int i = 0; i < config.getNumOfBots(); i++){
			dotComCollection[i] = new DotCom(botNames[i]);
			dotComCollection[i].setLocation(generatePosition.generatePosition());
			if(config.getShowInfoOfBots().equals("Yes")){
				dotComCollection[i].printLocation();
			}//End of if
		}//End of for loop
	}//End of setUpGame()
	
//	Main gameplay
	public void startPlaying(){
		System.out.println("Press 0 to end game and return to main menu");
		while(!allDead()){
			System.out.println("Pick a letter followed by a number");
			Scanner sc = new Scanner(System.in);
			String userInput = sc.nextLine();
			numOfGuess++;
			checkUserGuess(userInput);
			if(userInput.equals("0")){
				mainMenu();
			}
		}//end while
		finishGame();
	}//end startPlaying()
	
	//Function checks if all opposing forces are dead to end the game
	public boolean allDead(){
		for(DotCom tempDotCom : dotComCollection){
			if(tempDotCom.getIsAlive()){ return false; }
		}
		return true;
	}
	
	public void checkUserGuess(String userInput){
		boolean userHit = false;
		String hitOrMiss;
		for(DotCom tempDotCom : dotComCollection){
			if(tempDotCom.checkingHit(userInput)) { userHit = true; }
		}
		hitOrMiss = userHit ? "Hit" : "Miss";
		System.out.println("You " + hitOrMiss);
	}//end checkUserGuess()
	
	public void finishGame(){
		System.out.println("Game Over\nYou took " + numOfGuess + " guesses to win");	
		System.out.println("1: Restart");
		System.out.println("2: Main Menu");
		int selection = 0;
		Scanner sc = new Scanner(System.in);
		selection = sc.nextInt();
		if(selection == 1){
			setUpGame();
			startPlaying();
		}else if(selection == 2){
			mainMenu();
		}else{
			finishGame();
		}
	}//end finishGame()
	
}
