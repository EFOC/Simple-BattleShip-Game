package Battle_Ship;
import java.util.Scanner;


public class UserSelection {

	private int selectedOption = 0;
	private boolean menuRestart = false;
	private UserSelection[] user = new UserSelection[5];
	
	
	public UserSelection(){
		MainMenu();
	}
	
	public void MainMenu(){
System.out.println("Welcome to Dot Com Game !");
		
		System.out.println("Select Your Options\n"
				+ "1: Start Game\n"
				+ "2: Settings\n"
				+ "3: Exit");
		
		Scanner sc = new Scanner(System.in);
		selectedOption = sc.nextInt();
		
		if(selectedOption > 3 || selectedOption < 1){
			System.out.println("Please Choose from the following");
			MainMenu();
		}else if(selectedOption == 1){
			System.out.println("Start Game Placeholder");
		}else if(selectedOption == 2){
			OptionsMenu();
		}else if(selectedOption == 3){
			System.out.println("Exiting...");
			System.exit(0);
		}
	}
	
	
	public void OptionsMenu(){
		System.out.println("Options Menu");
		System.out.println("1: Sound On");
		System.out.println("2: Sound Off");
		System.out.println("3: Exit Options");
		
		Scanner sc = new Scanner(System.in);
		selectedOption = sc.nextInt();
		
		if(selectedOption > 3 || selectedOption < 1){
			System.out.println("Please Choose from the following");
			OptionsMenu();
		}else if(selectedOption == 1){
			
		}else if(selectedOption == 2){
			
		}else if(selectedOption == 3){
			UserSelection userSelection = new UserSelection();
		}
	}
	
	
	
}
