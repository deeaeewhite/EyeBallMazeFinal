package nz.ac.arastudent.eyeballmazeassignment2.model;

import java.util.HashMap;
import java.util.Map;

public enum Shapes {
	Triangle("T"),
	Diamond("D"),
	Plus("P"),
	Star("S");
	
	public final String abbreviation;
	
	private static final Map<String, Shapes> lookup = new HashMap<String, Shapes>();
	
	static {
        for (Shapes d : Shapes.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }
	
	Shapes(String abbreviation){
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
        return abbreviation;
    }

    public static Shapes get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
