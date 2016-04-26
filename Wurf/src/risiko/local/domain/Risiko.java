package risiko.local.domain;

	public class Risiko {
		public Spielerverwaltung spielerv = new Spielerverwaltung();
		public Weltverwaltung welt = new Weltverwaltung();
		public Gamelogic game = new Gamelogic();
	
	
		public void erstelleSpiel(String[] namen){ // Erstlle instanzen mit Namenspaket
			spielerv.erstellespieler(namen);
			welt.Erstellekarte(namen);
			game.spieler(namen.length);
			verteilelaender(namen.length);
			
		}
		
		public String[] karte(int landID){ // Hole String Paket vom Land mit der ID
			return welt.karte(landID);	
		}
		

		
		
		public int spieleramzug(){ // Ermittel welche SpielerID am Zug ist
			return game.spieleramzug;
			
		}
		
		public String spielername(int spielerid){ // Hole Name von Spieler mithilfe der ID(bzw. ArrayPosition)
			return spielerv.getspielernamen(spielerid);
		}
		
		public int armeenzuverteilen(int spielerid){ // Hole Einheiten zu verteilen mithilfe der ID(bzw. ArrayPosition)
			return spielerv.armeezuverteilen(spielerid);
		}
		
		
		
		
		public void verteilelaender(int Spielera){
			for (int i = 0; i < 42; i++){ // Führe für jedes Land einmal aus
				
				
				int random = 0;
	
				while(random != 10 ){ // verbleibe solange in der While Schleife bis land erfolgreich zugewiesen wurde
					random = (int)(Math.random() * Spielera);
					if(Spielera <= 3 && spielerv.spieler[random].giblaender() < 42/Spielera){
						spielerv.spieler[random].einlanddazu();
						spielerv.spieler[random].bekommeland(i);
						welt.Lands[i].neuerbesitzer(spielerv.spieler[random].Name);
						random = 10;
					}
					if(Spielera > 3 && spielerv.spieler[random].giblaender() < (float) 42/Spielera){
						if(Spielera == 4 && spielerv.spieler[random].giblaender() < 11 ){
							spielerv.spieler[random].einlanddazu();
							spielerv.spieler[random].bekommeland(i);
							welt.Lands[i].neuerbesitzer(spielerv.spieler[random].Name);
							random = 10;
						}
						if(Spielera == 5 && spielerv.spieler[random].giblaender() < 8 ){	
							spielerv.spieler[random].einlanddazu();
							spielerv.spieler[random].bekommeland(i);
							welt.Lands[i].neuerbesitzer(spielerv.spieler[random].Name);
							random = 10;
						}
						if(Spielera == 6 && spielerv.spieler[random].giblaender() < 7 ){	
							spielerv.spieler[random].einlanddazu();
							spielerv.spieler[random].bekommeland(i);
							welt.Lands[i].neuerbesitzer(spielerv.spieler[random].Name);
							random = 10;
						}
						
						
						

					}
					
					
					
				}
		
			
			}
			for(int i = 0; i < Spielera;i++){
				System.out.println(spielerv.spieler[i].laender);
				}
		
		}
		
		public boolean checklandinbesitz(String kuerzel){ // Überprüfe Kürzel(Land) in besitz des Spielers der am Zug ist
			if(spielerv.spieler[game.spieleramzug].laenderinbesitz.contains(welt.kuerzeltoid(kuerzel))){
				return true;
			}
			else{
				return false;
				
			}
		}
		
		public int getarmee(String land){  // Hole anzahl einheiten des Landes mit der ID (Kürzel wird in ID abgewandelt)
			return welt.getarmee(welt.kuerzeltoid(land));
		}
		
		public void verschiebe(int einheiten,String vonland, String zuland){ // Anzahl Einheiten von LandIDv abziehen und zu LandIDz hinzufügen
			int idv = welt.kuerzeltoid(vonland);
			int idz = welt.kuerzeltoid(zuland);
			
			welt.Lands[idv].einheitendazu(-einheiten);
			welt.Lands[idz].einheitendazu(einheiten);
		}
		
		public boolean angriff(String angreifer,String verteidiger ){
			int ida = welt.kuerzeltoid(angreifer);
			int idv = welt.kuerzeltoid(verteidiger);
			
			int[] neu = game.kampf(welt.Lands[ida].getEinheiten(), welt.Lands[idv].getEinheiten());
			int neueeinheitenA = neu[0];
			int neueeinheitenV = neu[1];
			welt.Lands[idv].seteinheiten(neueeinheitenV);
			welt.Lands[ida].seteinheiten(neueeinheitenA);
				// ANGRIFFSSCRIPT
			if(welt.Lands[idv].getEinheiten() == 0){
				spielerv.verliereland(spielerv.nametoid(welt.Lands[idv].getbesitzer()), idv);
				welt.Lands[idv].neuerbesitzer(welt.Lands[ida].getbesitzer());
				spielerv.gewinneland(spielerv.nametoid(welt.Lands[ida].getbesitzer()), idv);
				return true; // Angriff erfolgreich
			}
			else{
				return false; // Angriff Fehlgeschlagen
						
			}
		}
		
		
		public void armeeaufland(int einheiten, int landid){	// CHECK
			welt.armeeaufland(einheiten, landid);
			spielerv.spieler[game.spieleramzug].setarmee(spielerv.spieler[game.spieleramzug].getarmee()-einheiten);
		}
		
		public void turn(){ // Phase beenden falls Erste Phase, dann Armeen zu verteilen Errechnen und ggf. Bonus für Kontinent addieren.
			int bonus = 0;
			game.turn();
			if(game.phase1() && game.spielrunde > 1){
				bonus = welt.checkkontinents(spielerv.getspielernamen(game.spieleramzug));
				
			}
			spielerv.spielerbekommtarmeen(game.spieleramzug, bonus);
			
		}
		

}
