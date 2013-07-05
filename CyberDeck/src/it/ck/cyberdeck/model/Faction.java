package it.ck.cyberdeck.model;

public enum Faction {
  CRIMINAL("Criminal"), SHAPER("Shaper"), ANARCH("Anarch"), NEUTRAL("Neutral"), HAAS_BIOROID("Haas-Bioroid"), JINTEKI("Jinteki"), NBN("NBN"), WEYLAND_CONSORTIUM("The Weyland Consortium");
  
  private String text;

  Faction(String txt){
	  this.text = txt;
  }
  
  public static Faction fromString(String value){
	  if (value != null) {
	      for (Faction b : Faction.values()) {
	        if (value.equalsIgnoreCase(b.text)) {
	          return b;
	        }
	      }
	    }
	  throw new IllegalArgumentException("No constant with text " + value + " found");
  }
}
