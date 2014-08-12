package it.ck.cyberdeck.model;

public enum CardSet {
	CORE("Core", "01"), 
	WHAT_LIES_AHEAD("What Lies Ahead", "02"),
	TRACE_AMOUNT("Trace Amount", "02"),
	CYBER_EXODUS("Cyber Exodus", "02"),
	A_STUDY_IN_STATIC("A Study in Static", "02"),
	HUMANITYS_SHADOW("Humanity's Shadow", "02"),
	FUTURE_PROOF("Future Proof", "02"),
	CREATION_AND_CONTROL("Creation and Control", "03"),
	OPENING_MOVES("Opening Moves", "04"),
	SECOND_THOUGHTS("Second Thoughts", "04"),
	MALA_TEMPORA("Mala Tempora", "04"),
	TRUE_COLORS("True Colors", "04"),
	FEAR_AND_LOATHING("Fear and Loathing", "04"),
	DOUBLE_TIME("Double Time", "04"),
	HONOR_AND_PROFIT("Honor and Profit", "05"),
	UPSTALK("Upstalk", "06");
	
	private String name;
	private String code;

	CardSet(String name, String code){
		this.name = name;
		this.code = code;
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getCode(){
		return code;
	}
	
	public static CardSet findByName(String setName){
		for(CardSet set : values()){
			if(set.name.equalsIgnoreCase(setName))
				return set;
		}
		
		throw new IllegalArgumentException("No CardSet with name: " + setName + " found!!!");
	}
}
