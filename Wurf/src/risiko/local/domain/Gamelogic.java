package risiko.local.domain;

public class Gamelogic {
	public int spieleramzug = 0;
	public int anzahlspieler;
	public int phasenid = 0;
	public int spielrunde = 1;
	

	public void spieler(int anzahlspieler){
		this.anzahlspieler = anzahlspieler;
	}
	
	public boolean phase1(){
		if(phasenid == 1){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void turn(){
		phasenid++;
		if(phasenid > 3){
			phasenid = 0;
			spieleramzug++;
				if(spieleramzug == anzahlspieler){
					spieleramzug = 0;
					spielrunde++;
				}
		}
		
		
	}
	
	public int[] kampf(int angreifer , int verteidiger){
		int[]ergebnisA = new int[3]; //Ergebnis alle 3 Würfeln/Angreifer
		int[]ergebnisV = new int[2]; //Ergebnis alle 2 Würfeln/Verteidiger
		int[]ergebnisAneu = new int[2];//zwei höchsten Zahlen vom Angreifer 
		int angreiferNeu = angreifer;//Anzahl der neuen Einheiten
		int verteidigerNeu = verteidiger;//Anzahl der Einheiten von Verteidiger
		int cache = 0; //Zahl zum Zwischenspeichern
		
		//Anzahl der Würfeln in Abhängigkeit von der Anzahl der Einheiten
		if (angreifer == 2){
			ergebnisA[0]=(int)(Math.random() * 6) +1;
		}
		if (angreifer == 3){
			ergebnisA[0]=(int)(Math.random() * 6) +1;
			ergebnisA[1]=(int)(Math.random() * 6) +1;
		}
		if (angreifer > 3){
			ergebnisA[0]=(int)(Math.random() * 6) +1;
			ergebnisA[1]=(int)(Math.random() * 6) +1;
			ergebnisA[2]=(int)(Math.random() * 6) +1;
		}
		
		
		if (verteidiger == 1){
			ergebnisV[0]=(int)(Math.random() * 6) +1;	
		}
		if(verteidiger > 1){
			ergebnisV[0]=(int)(Math.random() * 6) +1;
			ergebnisV[1]=(int)(Math.random() * 6) +1;
		}

		
		//Die zwei höchsten Ergbnisse werden in Aneu übernommen
		ergebnisAneu[0] = ergebnisA[0];
		ergebnisAneu[1] = ergebnisA[1];
		
		if(ergebnisAneu[0] < ergebnisA[2]){
			ergebnisAneu[0] = ergebnisA[2];
			if(ergebnisAneu[1] < ergebnisA[0]){
				ergebnisAneu[1] = ergebnisA[0];
			}
			
			
		}
		
		
		// Sortieren des Neuen Angreifer Wurf
		if(ergebnisAneu[0] < ergebnisAneu[1]){
			cache = ergebnisAneu[0];
			ergebnisAneu[0] = ergebnisAneu[1];
			ergebnisAneu[1] = cache;
		}
		
		// Sortieren des Verteidiger Wurf
		if(ergebnisV[0] < ergebnisV[1]){
			cache = ergebnisV[0];
			ergebnisV[0] = ergebnisV[1];
			ergebnisV[1] = cache;
		}
		
		
		if (ergebnisAneu[0]> ergebnisV[0]){
			verteidigerNeu--;
		}
		else{
			angreiferNeu--;
		}
		if (ergebnisAneu[1]> ergebnisV[1] && ergebnisV[1]> 0){
			verteidigerNeu--;
		}
		else if(ergebnisAneu[1] > 0 && ergebnisV[1]> 0){
			angreiferNeu--;
		}
		
		int[] einheiten = new int[2];
		einheiten[0] = angreiferNeu;
		einheiten[1] = verteidigerNeu;
		return einheiten;
	}

	// Karte ziehen, Auftrag erfüllt oder Welt erobert.
}
