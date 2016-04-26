package risiko.local.objects;

public class Land {
	int ID = 0; // ID des Landes
	String Name = ""; // Name des Landes
	String kuerzel = ""; // Abkürzung des Landes
	String besitzer =""; // Name Besitzer
	int einheiten = 1; // Anzahl einheiten auf dem Land
	

	
	public void setlandname(String name){
		Name = name;	
	}
	
	public void setkuerzel(String name){
		kuerzel = name;		
	}
	
	public void seteinheiten(int menge){
		einheiten = menge;		
	}
	
	public void neuerbesitzer(String name){
		besitzer = name;
	}
	
	public String getlandname(){
		return Name;
	}
	
	public String getkuerzel(){
		return kuerzel;
	}
	
	public String getbesitzer(){
		return besitzer;
	}
	
	public String getID(){
		return Integer.toString(ID);
	}
	
	public String getEinheitenText(){
		return Integer.toString(einheiten);
	
	}
	
	public void einheitendazu(int anzahl){
		einheiten = einheiten + anzahl;
	}
	
	public int getEinheiten(){
		return einheiten;
		
	}
	public void IDf(int id){
		ID = id;
	}
	
	
	
	}


