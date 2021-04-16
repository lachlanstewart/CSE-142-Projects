//Lachlan Stewart
//12/04/2020
//CSE 142 BD 
//Assessment 8 Critters Assignment

import java.util.*;
import java.awt.*;

//This class describes a custom Husky critter and controls its functionality
public class Husky extends Critter {
	
	
	//Field
	private int moveCount; // Counts the husky's moves

	//Constructor
	public Husky(){
		moveCount = -1;
	}

	//Movement: Moves counter-clockwise in a 5x5 square
	//Returns the direction to move in
	public Direction getMove() {
		moveCount++;
		//Moves in a counter-clockwise square
		if	(moveCount % 20 == 0 || moveCount % 20 == 1 || moveCount % 20 == 2 || 
			 moveCount % 20 == 3 || moveCount % 20 == 4){
			return Direction.SOUTH;
		} else if	(moveCount % 20 == 5 || moveCount % 20 == 6 || moveCount % 20 == 7 || 
			 		 moveCount % 20 == 8 || moveCount % 20 == 9){
			return Direction.EAST;
		} else if	(moveCount % 20 == 10 || moveCount % 20 == 11 || moveCount % 20 == 12 || 
			 		 moveCount % 20 == 13 || moveCount % 20 == 14){
			return Direction.NORTH;
		} else {
			return Direction.WEST;
		}
	}

	//Eating: Always eats (Huskies are young, growing animals!)
	//Returns a boolean (true --> eat)
	public boolean eat() {
		return true;
	}

	//Fighting behavior: Scratch birds or hippos that are full, Roar for everything else
	//Returns the attack decision
	public Attack fight(String opponent) {
		if(opponent.equals("V") || opponent.equals("^") ||
		   opponent.equals("<") || opponent.equals(">") || //Birds & Vultures
		   opponent.equals("0")){ // No-longer-hungry hippos
			return Attack.SCRATCH;
		} else { 
			return Attack.ROAR;
		}
	}

	//Color: Huskies are always light gray
	//Returns the color of the critter's string
	public Color getColor() {
		return Color.LIGHT_GRAY;
	}

	//Icon: Huskies look like dogs!
	//Returns the string to represent the critter
	public String toString() {
		return "#'.'#"; // Think "ear + eye + nose + eye + ear"
	}

}
