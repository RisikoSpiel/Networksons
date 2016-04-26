package risiko.local.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import risiko.local.domain.Risiko;



public class CUI {
	Risiko Spiel1 = new Risiko();

	
	private BufferedReader in;
	
	public CUI(String datei) throws IOException {

		in = new BufferedReader(new InputStreamReader(System.in));
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des MenÃ¼s.
	 */
	private void gibMenueAus() {
		System.out.print("Befehle: \n  Spiel starten 'Start'");
		System.out.println("         \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Einlesen von Benutzereingaben.
	 */
	private String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Verarbeitung von Eingaben
	 * und Ausgabe von Ergebnissen.
	 */
	private void verarbeiteEingabe(String line) throws IOException {
		
		int spieleranzahl;
		
		
		// Eingabe bearbeiten:
		switch (line) {
		case "Start":
			if(10 < 10.5){
				System.out.println("true");
			}
			do {
				System.out.println("Wieviele Spieler spielen mit? (2-3)");
				System.out.print("> "); // Prompt
				spieleranzahl = Integer.parseInt(liesEingabe());
			} while (spieleranzahl < 2 || spieleranzahl > 6); //{

			String[] Spielernamen = new String[spieleranzahl];
					for(int i = 0; i < spieleranzahl;i++){
						System.out.println("Gib den Namen von Spieler "+ (i+1)+" ein");
						System.out.print("> "); // Prompt
						Spielernamen[i] = liesEingabe();
					}
					Spiel1.erstelleSpiel(Spielernamen);
					System.out.println("");
					System.out.println("________________________");
					System.out.println("Spiel startet!");
					System.out.println("________________________");
					System.out.println("");
					ermittelphase();
					

					
			break;
				
			case "Help":

						System.out.println("Hilfe anzeigen: 'Help' ");
						System.out.println("Zeige gesamte Karte: 'Map'");
						System.out.println("Zeige nur meine Länder: 'MyMap'");
						System.out.println(("Wer ist am Zug?: 'wer'"));
						System.out.println(("Nächste Phase: 'turn'"));
						
						
						
					
			break;
			
			case "Map":
				zeigekarte();
				
				break;
			case "MyMap":
				zeigemeinelaender();
				
				break;
				
			case "wer":
				System.out.println(Spiel1.spielername(Spiel1.spieleramzug())+" ist am Zug");
				
				break;
			case "turn":
				Spiel1.turn();
				ermittelphase();
				
				break;
					
			case "verteile": // Verteielen CUI
					do{
						System.out.println("Auf welches Land möchtest du deine "+Spiel1.armeenzuverteilen(Spiel1.spieleramzug())+" Einheiten verteilen? (Kürzel)");
						String Eingabe = liesEingabe();
						if(Spiel1.checklandinbesitz(Eingabe)){
						int anzahl;
							do{
								System.out.println("Wieviele Einheiten möchtest du verteilen?");
								try{
									anzahl = Integer.parseInt(liesEingabe());	
									  // is an integer!
									} catch (NumberFormatException e) {
										System.out.println("Bitte gib eine gültige Zahl ein!");
									  // not an integer!
										anzahl = -1;
									}
							
							}
							while(anzahl < 0);
								if(anzahl > 0 && anzahl <= Spiel1.armeenzuverteilen(Spiel1.spieleramzug())){
									Spiel1.armeeaufland(anzahl, Spiel1.welt.kuerzeltoid(Eingabe));
									System.out.println(anzahl + " Einheiten auf " + Eingabe +" verteilt.");
								}
								else{
									System.out.println("Falsche Eingabe,"+ Spiel1.armeenzuverteilen(Spiel1.spieleramzug())+" Einheiten noch zu verteilen");
								}
						}
						else{
						System.out.println("Nicht im Besitz");
						}
					} while(Spiel1.armeenzuverteilen(Spiel1.spieleramzug()) > 0 );
					System.out.println("Verteilungsphase beendet");
					System.out.println("");
					Spiel1.turn();
					ermittelphase();
					
					
				
				break;
			case "angriff": // Angriffs CUI
				zeigemeinelaender();
				System.out.println("Von welchem Land aus möchtest du angreifen? [Kürzel]");
				System.out.println("Angriff Abbrechen: 'Abb'");
				String Eingabe;
				do{
				Eingabe = liesEingabe();
				String Eingabe2;
				if(Spiel1.checklandinbesitz(Eingabe) && Spiel1.welt.genugeinheiten(Eingabe)){
					do{
					zeigegrenzlaender(Spiel1.welt.kuerzeltoid(Eingabe));
					System.out.println("Welches Land möchtest du Angreifen? [Kürzel]");
					System.out.println("Angriff Abbrechen: 'Abb'");
						Eingabe2 = liesEingabe();
						if(Spiel1.welt.checkgrenze(Spiel1.welt.kuerzeltoid(Eingabe), Spiel1.welt.kuerzeltoid(Eingabe2)) && !Spiel1.welt.getbesitzer(Spiel1.welt.kuerzeltoid(Eingabe2)).equals(Spiel1.spielerv.getspielernamen(Spiel1.spieleramzug()))){
							do{
							System.out.println("Grenzt an, Angriff!");
							System.out.print("Deine Einheiten: " );
							for(int i = 0; i < Spiel1.getarmee(Eingabe); i++){
								System.out.print("|" );
							}
							System.out.println("");
							System.out.print("Gegnererische Einheiten: ");
							for(int i = 0; i < Spiel1.getarmee(Eingabe2); i++){
								System.out.print("|" );
							}
							System.out.println("");
							System.out.println("");
							if(Spiel1.angriff(Eingabe, Eingabe2)){
							
								System.out.println("Angriff Erfolgreich!");
								System.out.println("Wieviel Truppen auf das Eroberte Land bewegen?");
								System.out.println("Gesamt: "+ Spiel1.getarmee(Eingabe)+" Maximal: "+ (Spiel1.getarmee(Eingabe)-1)+ " Mindestens: 1");
								int Menge;
								Menge = Integer.parseInt(liesEingabe());

									while(Menge > Spiel1.getarmee(Eingabe) || Menge <= 0){
										System.out.println("Mindestens: 1 und Maximal: "+ (Spiel1.getarmee(Eingabe)-1) );
										Menge = Integer.parseInt(liesEingabe());
									}
									
										Spiel1.verschiebe(Menge, Eingabe, Eingabe2);
										System.out.println("Angriff fortsetzen? [yes/no]");
										String Eingabe3 = liesEingabe();

										if(Eingabe3.equals("yes")){
											line = "no";
											
					
											
										}
										else{
											line = "no";
											Eingabe2 = "Abb";
											Eingabe = "Abb";
											
											
										}
										
										
							}
							else{
								System.out.println("Angriff Fehlgeschlagen!");
								System.out.println("Erneuter Angriff? [yes/no]");
								line = liesEingabe();
								
							}
							} while(line.equals("yes"));
							Eingabe2 = "Abb";
							Eingabe = "Abb";
							
							
						}
						else{
							System.out.println("Grenzt nicht an,Land gehört dir oder Abbruch");
							Eingabe = "Abb";
						}
					} while(!Eingabe2.equals("Abb"));
					
				
				}
				else if (!Eingabe.equals("Abb")){
					System.out.println("Nicht im Besitz");
					
				}
				}
				while(!Eingabe.equals("Abb"));
				ermittelphase();
					
				
				
				
				break;
			case("bewegen"):
				zeigemeinelaender();
				do{
					System.out.println("Von welchem Land aus möchtest du Einheiten verteilen? [Kürzel]");
					System.out.println("Verteilen Abbrechen: 'Abb'");
					Eingabe = liesEingabe();
					if(Spiel1.checklandinbesitz(Eingabe) && Spiel1.welt.genugeinheiten(Eingabe)){
						zeigegrenzlaenderVerteilen(Spiel1.welt.kuerzeltoid(Eingabe));
						System.out.println("Zu welchem Land möchtest du verschieben? [Kürzel]");
							String Eingabe2 = liesEingabe();
							if(Spiel1.welt.checkgrenze(Spiel1.welt.kuerzeltoid(Eingabe), Spiel1.welt.kuerzeltoid(Eingabe2)) && Spiel1.welt.getbesitzer(Spiel1.welt.kuerzeltoid(Eingabe2)).equals(Spiel1.spielerv.getspielernamen(Spiel1.spieleramzug()))){
								System.out.println("Wieviel Einheiten möchtest du Verschieben?");
								System.out.println("Gesamt: "+ Spiel1.getarmee(Eingabe)+" Maximal: "+ (Spiel1.getarmee(Eingabe)-1));
								int Menge;
									do{
										System.out.println("Mindestens: 1 und Maximal: "+ (Spiel1.getarmee(Eingabe)-1) );
										Menge = Integer.parseInt(liesEingabe());
										
									}while(Menge > Spiel1.getarmee(Eingabe) && Menge <= 0);
									Spiel1.verschiebe(Menge, Eingabe, Eingabe2);
									System.out.println("verteilen beendet");
									Eingabe = "Abb";
									Spiel1.turn();
									ermittelphase();
							}		
					
					}
					else{
						System.out.println("Falsche Eingabe oder Abbruch"); // Land nicht im Besitz oder falsche Eingabe
						ermittelphase();
					}
				}
				while(!Eingabe.equals("Abb"));
				
				break;
			
		}
	}


	public void run() {
		String input = ""; 
		gibMenueAus();
	
		// Hauptschleife der Benutzungsschnittstelle
		do {
			
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}
	

	
	
	public void ermittelphase() throws IOException{
		
		int phase = Spiel1.game.phasenid; // Holt sich von der Gamelogic aktuelle Phase
			if(phase == 0){ // Spieler beginnt seine Züge
				System.out.println(Spiel1.spielername(Spiel1.spieleramzug())+" ist am Zug");
				verarbeiteEingabe("Help");
			}
			if(phase == 1){ // Verteilungsphase
				zeigemeinelaender();
				System.out.println("Vertstärkungsphase");
				System.out.println("Einheiten zu verteilen: "+Spiel1.armeenzuverteilen(Spiel1.spieleramzug()));
				verarbeiteEingabe("verteile");	
				
			}
			if(phase == 2){ // Angriffsphase
				System.out.println("Angriffsphase");
				System.out.println("Ein Land angreifen: 'angriff'");
				System.out.println("Angriffsphase überspringen: 'turn'");
				System.out.println("Hilfe : 'Help'");
				
			}
			if(phase == 3){ // Verteilung von Land zu Land
				
				System.out.println("Truppenbewegung");
				System.out.println("Einheiten bewegen: 'bewegen'");
				System.out.println("Verteilungsphase überspringen: 'turn'");
			}
			
			
	}
	
	
	public void zeigemeinelaender(){
		ArrayList<Integer> laender = Spiel1.spielerv.spieler[Spiel1.game.spieleramzug].laenderinbesitz; // Hole Länderliste vom aktuellenspieler
		String[] land = new String[5];
		
		System.out.println("Länder im Besitz:");
		System.out.println("_____________");
		for(int i = 0; i < laender.size(); i++){
			System.out.println(laender.get(i));
			land = Spiel1.karte(laender.get(i));
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		
		}
	}
	
	public void zeigekarte(){
		
		String[] land = new String[5];
		
		
		System.out.println("Nordamerika:");
		System.out.println("_____________");
		for(int i = 0; i <= 8; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		System.out.println("Südamerika:");
		System.out.println("_____________");
		for(int i = 9; i <= 12; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		System.out.println("Afrika:");
		System.out.println("_____________");
		for(int i = 13; i <= 18; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		System.out.println("Europa:");
		System.out.println("_____________");
		for(int i = 19; i <= 25; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		System.out.println("Asien:");
		System.out.println("_____________");
		for(int i = 26; i <= 37; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		System.out.println("Australien:");
		System.out.println("_____________");
		for(int i = 38; i <= 41; i++){
			land = Spiel1.karte(i);
			for(int b = 0; b < 5;b++){
				if(b == 0) System.out.print("Name: ");
				if(b == 1) System.out.print("Kürzel: ");
				if(b == 2) System.out.print("Besitzer: ");
				if(b == 3) System.out.print("Einheiten: ");
				if(b == 4) System.out.print("ID: ");
				System.out.println(land[b]);
			}
			System.out.println("_____________");
		}
		

		
	}
	
	public void zeigegrenzlaender(int id){
		// Stellt alle angrenzenen feindlichen Länder da.
		String[] land = new String[5];
		int[] angrenzungen = Spiel1.welt.grenztan(id);
			for(int i = 0; i < angrenzungen.length; i++){
				land = Spiel1.karte(angrenzungen[i]);
				if(!land[2].equals(Spiel1.spielername(Spiel1.spieleramzug()))){
				for(int b = 0; b < 5;b++){
					if(b == 0) System.out.print("Name: ");
					if(b == 1) System.out.print("Kürzel: ");
					if(b == 2) System.out.print("Besitzer: ");
					if(b == 3) System.out.print("Einheiten: ");
					if(b == 4) System.out.print("ID: ");
					System.out.println(land[b]);
				}
				System.out.println("_____________");
			}}
		
		
	}
	
	public void zeigegrenzlaenderVerteilen(int id){
		// Stellt alle angrenzenen eigene Länder da.
		String[] land = new String[5];
		int[] angrenzungen = Spiel1.welt.grenztan(id);
			for(int i = 0; i < angrenzungen.length; i++){
				land = Spiel1.karte(angrenzungen[i]);
				if(land[2].equals(Spiel1.spielername(Spiel1.spieleramzug()))){
				for(int b = 0; b < 5;b++){
					if(b == 0) System.out.print("Name: ");
					if(b == 1) System.out.print("Kürzel: ");
					if(b == 2) System.out.print("Besitzer: ");
					if(b == 3) System.out.print("Einheiten: ");
					if(b == 4) System.out.print("ID: ");
					System.out.println(land[b]);
				}
				System.out.println("_____________");
			}}
		
		
	}

	
	/**
	 * Die main-Methode...
	 */
	public static void main(String[] args) {
		CUI cui;
		try {
			cui = new CUI("BIB");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
