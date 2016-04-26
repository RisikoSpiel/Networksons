

public class wurfeln {



	public void wuerfelnf(int angreifer , int verteidiger){
		int[]ergebnisA = new int[3]; //Ergebnis alle 3 Würfeln/Angreifer
		int[]ergebnisV = new int[2]; //Ergebnis alle 2 Würfeln/Verteidiger
		int[]ergebnisAneu = new int[2];//zwei höchsten Zahlen vom Angreifer 
		int angreiferNeu = angreifer;//Anzahl der neuen Einheiten
		int verteidigerNeu = verteidiger;//Anzahl der Einheiten von Verteidiger
		int cache = 0; //Zahl zum Zwischenspeichern
		
		//WAS GEHT
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
		System.out.println("Angreifer Wurf 1 = " + ergebnisA[0]);
		System.out.println("Angreifer Wurf 2 = " + ergebnisA[1]);
		System.out.println("Angreifer Wurf 3 = " + ergebnisA[2]);
		
		
		if (verteidiger == 1){
			ergebnisV[0]=(int)(Math.random() * 6) +1;	
		}
		if(verteidiger > 1){
			ergebnisV[0]=(int)(Math.random() * 6) +1;
			ergebnisV[1]=(int)(Math.random() * 6) +1;
		}
		System.out.println("Verteidiger Wurf 1: " + ergebnisV[0]);
		System.out.println("Verteidiger Wurf 2: " + ergebnisV[1]);
		
		//Die zwei höchsten Ergbnisse werden in Aneu übernommen
		ergebnisAneu[0] = ergebnisA[0];
		ergebnisAneu[1] = ergebnisA[1];
		
		if(ergebnisAneu[0] < ergebnisA[2]){
			ergebnisAneu[0] = ergebnisA[2];
			if(ergebnisAneu[1] < ergebnisA[0]){
				ergebnisAneu[1] = ergebnisA[0];
			}
			
			
		}
		
		System.out.println("Neu 1: " + ergebnisAneu[0]);
		System.out.println("Neu 2: " + ergebnisAneu[1]);
		
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
		
		System.out.println("Neu 1: " + ergebnisAneu[0]);
		System.out.println("Neu 2: " + ergebnisAneu[1]);
		
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
		System.out.println("Angreifer übrige Einheiten: " +angreiferNeu);
		System.out.println("Verteidiger übrige Einheiten: " +verteidigerNeu);
	}
	
	
	public static void main(String[]args){
		wurfeln wurf = new wurfeln();
		wurf.wuerfelnf(12, 6);
	
		
		
	}
	
}