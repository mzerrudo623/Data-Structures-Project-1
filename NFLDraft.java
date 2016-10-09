/* Mishael Zerrudo
   CSC 311 Project #1
   A program that simulates an NFL draft
   September 11, 2015 */

import java.util.*;
import java.io.*;
import java.text.*;						//used for DecimalFormat class
public class NFLDraft
{
	private String[] teams;				//holds team names
	private double[] winPercent;		//holds team's winning percentage
	/*** teams and winPercent are parallel arrays ***/
	
	private String[][] playerNames;		//holds names of players
	
	//constructor
	public NFLDraft()
	{
		teams = new String[8];				//8 teams
		winPercent = new double[8];			//win percentage of the 8 teams
		playerNames = new String[4][8];		//4 rounds. 8 players that can be chosen per round.
	}
	
	//get team names from a file and put them into String array teams
	public void getTeams() throws IOException
	{
		File inputFile = new File("TeamNames.txt");
		if (!inputFile.exists())
		{
			System.out.println("File Not Found");
			System.exit(0);
		}
		Scanner input = new Scanner(inputFile);
		for (int i = 0; i < 8; i++)
			teams[i] = input.nextLine();
		input.close();
	}
	
	//gets random doubles and assigns them into the double array winPercent
	//gives random win percentage each time program runs
	public void getPercentage()
	{
		Random random = new Random();
		DecimalFormat formatter = new DecimalFormat("0.00");	//Ex: Turns 12.3456 to 12.34 when printed
		for (int i = 0; i < 8; i++)
			winPercent[i] = random.nextDouble() * 100; //Ex: turns 0.112 to 11.2
		
		//prints out team's win percentage
		for (int i = 0; i < 8; i++)
			System.out.println(teams[i] + " won " + formatter.format(winPercent[i]) + " of their games last season!");
		System.out.println("");
	}
	
	//sorts winPercent in increasing order
	//sorts teams to match winPercentage
	/*** teams and winPercent are parallel arrays ***/
	public void sortArray()
	{
		double dTemp;			//holds temporary value for swapping
		String sTemp;			//holds temporary value for swapping
		for (int i = 1; i < 9; i++)
		{
			for (int j = 0; j < winPercent.length - i; j++)
			{
				if (winPercent[j] > winPercent[j + 1])
				{
					//swap the two values of winPercent
					dTemp = winPercent[j];
					winPercent[j] = winPercent[j + 1];
					winPercent[j + 1] = dTemp;
					
					//swap the corresponding values of teams
					sTemp = teams[j];
					teams[j] = teams[j + 1];
					teams[j + 1] = sTemp;
				}
			}
		}
		
		//prints team's rank
		for (int i = 0; i < 8; i++)
			System.out.println(teams[i] + " is ranked " + (i + 1));
		System.out.println("");
	}
	
	//get player names from a file and assigns the round they can be chosen for draft
	public void getPlayerNames() throws IOException
	{
		Random random = new Random();
		int randomNum;
		int firstCount = 0;			//tells how many player has been chosen for first round
		int secondCount = 0;		//tells how many player has been chosen for second round
		int thirdCount = 0;			//tells how many player has been chosen for third round
		int fourthCount = 0;		//tells how many player has been chosen for fourth round
		
		File inputFile = new File("PlayerNames.txt");
		if (!inputFile.exists())
		{
			System.out.println("File Not Found");
			System.exit(0);
		}
		Scanner input = new Scanner(inputFile);
		while (input.hasNext())
		{
			randomNum = 0;
			while (randomNum == 0)		//used to loop back if array chosen by randomNum is full
			{
				randomNum = random.nextInt(4) + 1;
				if (randomNum == 1)
				{
					if (firstCount < 8)
					{
						playerNames[0][firstCount] = input.nextLine();
						System.out.println(playerNames[0][firstCount] + " has been selected to be drafted in Round 1");
						firstCount++;
					}
					else		//there are already 8 players that can be drafted in first round
						randomNum = 0;		//causes program to loop back to beginning of inner while loop to choose a different number
				}
				else if (randomNum == 2)
				{
					if (secondCount < 8)
					{
						playerNames[1][secondCount] = input.nextLine();
						System.out.println(playerNames[1][secondCount] + " has been selected to be drafted in Round 2");
						secondCount++;
					}
					else		//there are already 8 players that can be drafted in second round
						randomNum = 0;		//causes program to loop back to beginning of inner while loop to choose a different number
				}
				else if (randomNum == 3)
				{
					if (thirdCount < 8)
					{
						playerNames[2][thirdCount] = input.nextLine();
						System.out.println(playerNames[2][thirdCount] + " has been selected to be drafted in Round 3");
						thirdCount++;
					}
					else		//there are already 8 players that can be drafted in third round
						randomNum = 0;		//causes program to loop back to beginning of inner while loop to choose a different number
				}
				else
				{
					if (fourthCount < 8)
					{
						playerNames[3][fourthCount] = input.nextLine();
						System.out.println(playerNames[3][fourthCount] + " has been selected to be drafted in Round 4");
						fourthCount++;
					}
					else		//there are already 8 players that can be drafted in fourth round
						randomNum = 0;		//causes program to loop back to beginning of inner while loop to choose a different number
				}
			}
		}
		System.out.println("");
	}
	
	//each team chooses a player in each round
	public void choosePlayer()
	{
		Random random = new Random();
		int randomNum = 0;
		for (int i = 0; i < 4; i++)			//there are 4 rounds
		{
			for (int j = 0; j < 8; j++)		//there are 8 teams that needs to choose a player each round
			{
				randomNum = random.nextInt(8);
				while (playerNames[i][randomNum] == null)		//while player has already been chosen
					randomNum = random.nextInt(8);			//choose a different number
				System.out.println("Round " + (i + 1) + ": Team: " + teams[j] + " selected: " + playerNames[i][randomNum]);
				playerNames[i][randomNum] = null;		//prevents this player from being chosen again
			}
			System.out.println("");
		}
	}
	
	public static void main (String[] args) throws IOException
	{
		NFLDraft draft = new NFLDraft();
		draft.getTeams();
		draft.getPercentage();
		draft.sortArray();
		draft.getPlayerNames();
		draft.choosePlayer();
	}
}