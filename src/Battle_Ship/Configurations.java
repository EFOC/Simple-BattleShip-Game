package Battle_Ship;

/**
 * This class configures the settings of the game
 * Consists of menus for the games and reading and writing into the xml file
 * The player can choose the number of bots to play against
 * Note that setting the difficulty does not change the game play in any way
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

public class Configurations {
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private String defaultFileName = "settings.xml";
	private String insertedFileName = null;
	private Document document;
	private String[] difficultyModes = {"Easy","Medium","Hard"};
	
	//Constants values
	private static final int ONLY_CHILD_NODE = 0;
	private static final int MENU_ADDITION = -1;
	private static final String defaultDifficulty = "Medium";
	private static final String defaultNumOfBots = "2";
	
	public Configurations(){
		try {
			DocumentBuilder build =  factory.newDocumentBuilder();
			String currentFileName = insertedFileName == null ? defaultFileName : insertedFileName;
			this.document = build.parse(currentFileName);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void settingsMenu(){

		System.out.println("Settings Menu:\n"
				+ "Please Select the Following Options\n"
				+ "1: Choose difficulty\n"
				+ "2: Config bots\n"
				+ "3: Save settings\n"
				+ "4: Restore default\n"
				+ "5: Back to main menu");
		Scanner sc = new Scanner(System.in);//This gets the user input to select what they want to change
		Integer userInput = sc.nextInt();
		if(userInput.equals(1)){
			difficultyMenu();
		}else if(userInput.equals(2)){
			botSettings();
		}else if(userInput.equals(3)){
			saveFile();
			System.out.println("Saved\n");
			settingsMenu();
		}else if(userInput.equals(4)){
			restoreDefault();
			System.out.println("Restored Settings");
		}else if(userInput.equals(5)){
			//Exists object
		}else{
			System.out.println("Error: Please select from the following");
		}
	}
	
	public void restoreDefault(){
		setNumOfBots(defaultNumOfBots);
		setDifficulty(defaultDifficulty);
	}
	
	public void difficultyMenu(){
		
		System.out.println("Select Difficulty\n"
				+ "1: Easy\n"
				+ "2: Medium\n"
				+ "3: Hard\n"
				+ "4: Return to settings\n"
				+ "Current difficulty: " + getDifficulty());
		int selection = 0;
		{
			Scanner sc = new Scanner(System.in);
			String userInput = sc.next();
			selection = Integer.valueOf(userInput);//Gets the user input from 1-4
			
			if(selection <= 3 && selection >= 1){
				setDifficulty(difficultyModes[selection + MENU_ADDITION]);//If the input is 1-3 than it will select that part of the area and return the setting
				difficultyMenu();
			}else if(selection == 4){
				settingsMenu();
			}
		}while(selection < 1 || selection > 3);
	}

	public void botSettings(){
		System.out.println("Select the number of bots to play against:\n"
				+ "Min 1 Max 4\n"
				+ "5: Show bot's name and location : " + getShowInfoOfBots() + "\n"
				+ "6: Return to settings");
		System.out.println("Current bots: " + getNumOfBots());
		String numOfBotsWanted = null;
		int userInput = 0;
		{
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextInt();

			if(userInput <= 4 && userInput >= 1){
				numOfBotsWanted = String.valueOf(userInput);
				setNumOfBots(numOfBotsWanted);
				botSettings();
			}else if(userInput == 5){
				setShowInfoOfBots();
			}else if(userInput == 6){
				settingsMenu();
			}
		}while(userInput < 1 || userInput > 6);
	}
	
	public void setShowInfoOfBots(){
		NodeList settingList = this.document.getElementsByTagName("showBotInfo");
		Node botInfo = settingList.item(ONLY_CHILD_NODE);
		if(getShowInfoOfBots().equals("Yes")){
			botInfo.setTextContent("No");	
		}else if(getShowInfoOfBots().equals("No")){
			botInfo.setTextContent("Yes");
		}
		botSettings();
	}
	
	public String getShowInfoOfBots(){
		NodeList settingList = this.document.getElementsByTagName("showBotInfo");
		Node botInfo = settingList.item(ONLY_CHILD_NODE);
		return botInfo.getTextContent();
	}
	
	public int getNumOfBots(){
		NodeList settingList = this.document.getElementsByTagName("bots");
		Node bots = settingList.item(ONLY_CHILD_NODE);
		return Integer.valueOf(bots.getTextContent());
	}
	
	public void setNumOfBots(String numOfBots){
		NodeList settingList = this.document.getElementsByTagName("bots");
		Node bots = settingList.item(ONLY_CHILD_NODE);
		bots.setTextContent(numOfBots);
	}

	public String getDifficulty(){
		NodeList settingList = this.document.getElementsByTagName("difficulty");
		Node difficulty = settingList.item(ONLY_CHILD_NODE);
		return difficulty.getTextContent();
	}

	public void setDifficulty(String mode){
		NodeList settingList = this.document.getElementsByTagName("difficulty");
		Node difficulty = settingList.item(ONLY_CHILD_NODE);
		difficulty.setTextContent(mode);
	}
	
	public void setFileName(String fileName){
		this.insertedFileName = fileName;
	}

	public void saveFile(){//Saving file method
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(defaultFileName)));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			saveFileError();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			saveFileError();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			saveFileError();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			saveFileError();
		}	
	}
	
//	This is to show the user that an error occurred during saving
	public void saveFileError(){
		System.err.println("Error: File not saved sucessfully");
	}
}