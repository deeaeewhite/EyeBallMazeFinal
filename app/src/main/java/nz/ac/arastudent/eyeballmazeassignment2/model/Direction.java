package nz.ac.arastudent.eyeballmazeassignment2.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
	Up("W", 2),
	Down("S", -2),
	Left("A", 1),
	Right("D", -1);
	
	public final String abbreviation;
	public final int number;
	
	private static final Map<String, Direction> lookup = new HashMap<String, Direction>();
	private static final Map<Integer, Direction> lookupInt = new HashMap<Integer, Direction>();
	
	static {
        for (Direction d : Direction.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }
	
	static {
        for (Direction d : Direction.values()) {
            lookupInt.put(d.getNumber(), d);
        }
    }
	
	Direction(String abbreviation, int number){
		this.abbreviation = abbreviation;
		this.number = number;
	}
	
	public String getAbbreviation() {
        return abbreviation;
    }
	
	public int getNumber() {
        return number;
    }

    public static Direction get(String abbreviation) {
        return lookup.get(abbreviation);
    }
    
    public static Direction getInt(int number) {
        return lookupInt.get(number);
    }
}
