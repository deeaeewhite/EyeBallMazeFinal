package nz.ac.arastudent.eyeballmazeassignment2.model;

import java.util.HashMap;
import java.util.Map;

public enum Goal {
	Open("G"),
	NaG(" "),
	Done("X");
	
	public final String abbreviation;
	
	private static final Map<String, Goal> lookup = new HashMap<String, Goal>();
	
	static {
        for (Goal d : Goal.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }
	
	Goal(String abbreviation){
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
        return abbreviation;
    }

    public static Goal get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
