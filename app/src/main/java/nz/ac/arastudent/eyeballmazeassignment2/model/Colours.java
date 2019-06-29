package nz.ac.arastudent.eyeballmazeassignment2.model;

import java.util.HashMap;
import java.util.Map;

public enum Colours {
	Green("G"),  //G
	Blue("B"),   //B
	Yellow("Y"), //Y
	Red("R");    //R
	
	public final String abbreviation;
	
	private static final Map<String, Colours> lookup = new HashMap<String, Colours>();
	
	static {
        for (Colours d : Colours.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }
	
	Colours(String abbreviation){
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
        return abbreviation;
    }

    public static Colours get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
