package risiko.local.domain;

import risiko.local.objects.Spieler;

public class Spielerverwaltung {
	public Spieler[] spieler;
	public void erstellespieler(String[] namen){
		
		int spielera = namen.length;
		spieler = new Spieler[spielera];
		
		
		for (int i = 0; i < spielera; i++){
			spieler[i] = new Spieler();
			spieler[i].setzenamen(namen[i]);
			
		}
		
	}
	
	public void spielerbekommtarmeen(int spieler, int bonus){
		int armee = this.spieler[spieler].laender;
		armee = armee /3;
			if(armee <3 ){
				armee = 3;
			}
		armee = armee + bonus;
		this.spieler[spieler].setarmee(armee);
	}
	
	public int nametoid(String name){
		for (int i = 0; i < spieler.length; i++){
			if(spieler[i].Name.equals(name)){
				return i;
			}
		}
		return 0;
	}
	
	public String getspielernamen(int spielerid){ // Hole Public Variable ohne Funktion
		return spieler[spielerid].Name;
	}
	
	public int armeezuverteilen(int spielerid){ // Hole Public Variable ohne Funktion
		return spieler[spielerid].armeenzuverteilen;
	}
	
	public void verliereland(int spieler, int land){
		this.spieler[spieler].landabziehen();
		this.spieler[spieler].verliereland(land);
		
	}
	
	public void gewinneland(int spieler, int land){
		this.spieler[spieler].einlanddazu();
		this.spieler[spieler].bekommeland(land);;
		
	}

}
