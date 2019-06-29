package nz.ac.arastudent.eyeballmazeassignment2.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;

import nz.ac.arastudent.eyeballmazeassignment2.R;

public class Model implements IGame {	

	public Model()
	{
	
	}
	
	public String[][] GameMap =
		{
			{"    ", "    ", "TR G", "    "},
			{"PB  ", "TY  ", "DY  ", "PG  "},
			{"TG  ", "SR  ", "SG  ", "DY  "},
			{"TR  ", "TB  ", "SR  ", "TG  "},
			{"SB  ", "DR  ", "TB  ", "DB  "},
			{"    ", "DBU ", "    ", "    "}
		};
		
	public int moveCounter = 0;
	public int movesLeft = 10;
	public int goalCounter = 0;

	CoOrds player = new CoOrds(0, 0, PlayerDirection.Up);
	
	Shapes currentShape = Shapes.Diamond;
	Colours currentColour = Colours.Blue;

	public void setMazeCharacter(int x, int y, String item){
		GameMap[y][x] = item;
	}

	public String[][] getGameMap(){
		return this.GameMap;
	}

	public void updateMaze(){
		int tempGoalCounter = 0;
		for (int y = 0; y < GameMap.length; ++y){
			for (int x = 0; x < GameMap[y].length; ++x){
				String pos = GameMap[y][x];
				
				//Update the current player position in the process
				String[] item = GameMap[y][x].split("");
				StringBuilder sb = new StringBuilder(GameMap[y][x]);
				
				if (Goal.get(item[4])== Goal.Open) {
					tempGoalCounter++;
				}
				if (sb.charAt(2) != ' '){
					player.x = x;
					player.y = y;
					player.looking = PlayerDirection.get(item[3]);
					this.currentShape = Shapes.get(item[1]);
					this.currentColour = Colours.get(item[2]);
				}
			}
		}
		this.goalCounter = tempGoalCounter;
	}
	
	public Integer getRowCount(){
		return GameMap.length;	
	}

	public Integer getcolumnCount(){
		int previousRowLength = 0;
		
		for (String[] row : GameMap)
		{
			if (row.length >= previousRowLength) { previousRowLength = row.length; }
		}
		return previousRowLength;
	}
	
    public void restartMaze(){

    }

    public void setGoalCount(String goalCount) { this.goalCounter = Integer.parseInt(goalCount); }
    public String getGoalCount(){
       return Integer.toString(goalCounter);
    }

	public void setMoveCount(String moveCount) { this.moveCounter = Integer.parseInt(moveCount); }
	public String getMoveCount(){
		return Integer.toString(moveCounter);
	}

	public void setMovesLeft(String movesLeft) { this.movesLeft= Integer.parseInt(movesLeft); }
	public Integer getMovesLeft(){
		return movesLeft;
	}

    public Integer[] getPlayerLocation(){
		Integer[] coords;
		coords = new Integer[2];

		coords[0] = player.x;
        coords[1] = player.y;

        return coords;
	}

	public String getPlayerDirection(){
		return player.looking.getAbbreviation();
	}

	public String getItem(int x, int y){
		return GameMap[y][x];
	}
    
    public Object[] whatsAt(int x, int y){
    	Object[] out = new Object[]{};
    	ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(out));
    	if(y >= GameMap.length || x > GameMap[0].length || y < 0 || x < 0){
    		temp.add(false);
    	} else {
	    	String[] item = GameMap[y][x].split("");
	    	Shapes shape = Shapes.get(item[1]);
	    	Colours colour = Colours.get(item[2]);
	    	Goal goal = Goal.get(item[4]);
	    	
	    	temp.add(shape);
	    	temp.add(colour);
	    	temp.add(goal);
    	}
		return temp.toArray();
    }
    
    public String makeMove(String move, int space){
    	//String[] theirInput = move.split("");
    	Direction direction = Direction.get(move);
		String out = "Can not move backwards";
		boolean validMove = true;
    	int spaces = space;
    	if (this.isNotMovingBackwards(direction)){

	    	if (direction == Direction.Down) { validMove = this.moveVertical(spaces, PlayerDirection.Down);}
	    	else if (direction == Direction.Up) { validMove = this.moveVertical(-spaces, PlayerDirection.Up);}
	    	
	    	else if (direction == Direction.Left) { validMove = this.moveHorizontal(-spaces, PlayerDirection.Left);}
	    	else if (direction == Direction.Right) { validMove = this.moveHorizontal(spaces, PlayerDirection.Right);}

	    	if (validMove == false){
	    		out = "You can only move to a square of same Shape or Colour!";
			} else {
				movesLeft -= 1;
				moveCounter++;
				out = "";
			}
		}

    	this.updateMaze();
    	return out;
    }
    
    private boolean isNotMovingBackwards(Direction direction){
    	Boolean out = true;
    	if(direction.getNumber() + this.player.looking.getNumber() == 0){
    		out = false;
    	}
    	return out;
    }
    
    private boolean moveHorizontal(int spaces, PlayerDirection playerDirection){
    	int movingTo = player.x + spaces;
    	boolean out = true;
    	Object[] newLocation = whatsAt(movingTo, player.y);
    	
    	if (newLocation.length < 2){
    		System.out.println("Invalid Move");
    	}
    	else {
	    	Shapes newShape = (Shapes) newLocation[0];
	    	Colours newColour = (Colours) newLocation[1];
	    	Goal goal = (Goal) newLocation[2];
	    	
	    	if(goal == Goal.Done){
	    		System.out.println("This goal has already been solved");
	    		
	    	} else if (goal == Goal.Open && (this.currentShape == newShape || this.currentColour == newColour)){
    			player.looking = playerDirection;
	    		
	    		StringBuilder sb = new StringBuilder(GameMap[player.y][player.x]);
	    		sb.deleteCharAt(2);
	    		sb.insert(2, " ");
	    		GameMap[player.y][player.x] = sb.toString();
	    		
	    		StringBuilder sb2 = new StringBuilder(GameMap[player.y][movingTo]);
	    		sb2.deleteCharAt(3);
	    		sb2.deleteCharAt(2);
	    		sb2.insert(2, player.looking.getAbbreviation());
	    		sb2.insert(3, Goal.Done.getAbbreviation());
	    		GameMap[player.y][movingTo] = sb2.toString();
	    		
	    	}else if (goal == Goal.NaG && (this.currentShape == newShape || this.currentColour == newColour)){ 
	    		player.looking = playerDirection;
	    		
	    		StringBuilder sb = new StringBuilder(GameMap[player.y][player.x]);
	    		sb.deleteCharAt(2);
	    		sb.insert(2, " ");
	    		GameMap[player.y][player.x] = sb.toString();
	    		
	    		StringBuilder sb2 = new StringBuilder(GameMap[player.y][movingTo]);
	    		sb2.deleteCharAt(2);
	    		sb2.insert(2, player.looking.getAbbreviation());
	    		GameMap[player.y][movingTo] = sb2.toString();
	    		
	    	} else { out = false;}
    	}
    	return out;
    }
    
    private boolean moveVertical(int spaces, PlayerDirection playerDirection){
    	int movingTo = player.y + spaces;
		boolean out = true;
    	Object[] newLocation = whatsAt(player.x, movingTo);

    	if (newLocation.length < 2){
    		System.out.println("Invalid Move");
    	}
    	else {
	    	Shapes newShape = (Shapes) newLocation[0];
	    	Colours newColour = (Colours) newLocation[1];
	    	Goal goal = (Goal) newLocation[2];

	    	if(goal == Goal.Done){
	    		System.out.println("This goal has already been solved");
	    		
	    	} else if (goal == Goal.Open && (this.currentShape == newShape || this.currentColour == newColour)){
    			player.looking = playerDirection;
	    		
	    		StringBuilder sb = new StringBuilder(GameMap[player.y][player.x]);
	    		sb.deleteCharAt(2);
	    		sb.insert(2, " ");
	    		GameMap[player.y][player.x] = sb.toString();
	    		
	    		StringBuilder sb2 = new StringBuilder(GameMap[movingTo][player.x]);
	    		sb2.deleteCharAt(3);
	    		sb2.deleteCharAt(2);
	    		sb2.insert(2, player.looking.getAbbreviation());
	    		sb2.insert(3, Goal.Done.getAbbreviation());
	    		GameMap[movingTo][player.x] = sb2.toString();

	    	}else if (goal == Goal.NaG && (this.currentShape == newShape || this.currentColour == newColour)){ 
	    		player.looking = playerDirection;
	    		
	    		StringBuilder sb = new StringBuilder(GameMap[player.y][player.x]);
	    		sb.deleteCharAt(2);
	    		sb.insert(2, " ");
	    		GameMap[player.y][player.x] = sb.toString();
	    		
	    		StringBuilder sb2 = new StringBuilder(GameMap[movingTo][player.x]);
	    		sb2.deleteCharAt(2);
	    		sb2.insert(2, player.looking.getAbbreviation());
	    		GameMap[movingTo][player.x] = sb2.toString();
	    		
	    	} else { out = false;}

    	}
		return out;
    }
    
    public void updateMove(){
    	
    }
    
    public void showNextMove(){
    	
    }
    
    public void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
    
    public boolean isComplete(){
    	boolean result = false;
    	if (this.goalCounter == 0 || this.movesLeft == 0){
    		//result = false;
    		result = true;
    	}
    	return result;
    }
    
   // public void start() {
    //	this.updateMaze();
    //	while (isComplete()){
    //		this.updateMaze();
    //
	  //  	Scanner in = new Scanner(System.in);
	    //	String s = in.nextLine();
	    //	this.makeMove(s);
	    //	System.out.println(this.moveCounter);
	    //	System.out.println(this.goalCounter);
	    //	System.out.println(this.goalsSolved);
	    	//this.clearConsole();
    	//}
   // }
}
