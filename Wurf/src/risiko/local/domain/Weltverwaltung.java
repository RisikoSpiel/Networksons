package risiko.local.domain;

import risiko.local.objects.Land;

public class Weltverwaltung {
	Land[] Lands = new Land[42];

	String[][] laendernamen = {
			{
			"Alaska",
			"Nordwest-Territorium",
			"Grönland",
			"Alberta",
			"Ontario",
			"Quebec",
			"Weststaaten",
			"Oststaaten",
			"Mittelamerika",

			"Venezuela",
			"Peru",
			"Brasilien",
			"Argentinien",

			"Nordwestafrika",
			"Ägypten",
			"Ostafrika",
			"Kongo",
			"Südafrika",
			"Madagaskar",

			"Island",
			"Skandinavien",
			"Ukraine",
			"Großbritannien",
			"Mitteleuropa",
			"Westeuropa",
			"Südeuropa",
			
			"Ural",
			"Sibirien",
			"Jakutien",
			"Irkutsk",
			"Kamtschatka",
			"Mongolei",
			"Japan",
			"Afghanistan",
			"China",
			"Mittlerer Osten",
			"Indien",
			"Siam",

			"Indonesien",
			"Neu-Guinea",
			"West-Australien",
			"Ost-Australien" 
			}
			,
			{
				"Al",
				"NT",
				"Groe",
				"Alb",
				"Ont",
				"Que",
				"Wests",
				"Osts",
				"Ma",

				"Ven",
				"Peru",
				"Bras",
				"Arg",

				"Norda",
				"Ägy",
				"Ostaf",
				"Kon",
				"Süda",
				"Mad",

				"Is",
				"Skan",
				"Ukr",
				"Großb",
				"Mittele",
				"Weste",
				"Süde",
				
				"Ural",
				"Sib",
				"Jak",
				"Irk",
				"Kamt",
				"Mong",
				"Jap",
				"Afg",
				"China",
				"Mittelo",
				"Ind",
				"Siam",

				"Indo",
				"Neug",
				"Westa",
				"Osta"}};
	
	public void Erstellekarte(String[] Namen){
		for (int i = 0; i < 42; i++){
			Lands[i] = new Land();
			Lands[i].setlandname(laendernamen[0][i]);
			Lands[i].setkuerzel(laendernamen[1][i]);
			Lands[i].IDf(i); // ID = Position in Lands Array
		}
	}
	
	public void armeeaufland(int einheiten, int id){
		Lands[id].einheitendazu(einheiten);
	}
	
	public int getarmee(int landid){
		return Lands[landid].getEinheiten();
		
	}
	
	public String[] karte(int land){
	String[] landdaten = new String[5];
		landdaten[0] = Lands[land].getlandname();
		landdaten[1] = Lands[land].getkuerzel();
		landdaten[2] = Lands[land].getbesitzer();
		landdaten[3] = Lands[land].getEinheitenText();
		landdaten[4] = Lands[land].getID();
		return landdaten;
	}
	
	public boolean genugeinheiten(String kuerzel){
		if(Lands[kuerzeltoid(kuerzel)].getEinheiten() > 1){
			return true;
		}
		else{
			return false;
			
		}
	}
	
	public boolean checkgrenze(int IDa, int IDb){
		int[] check = grenztan(IDa);
		
			for(int i = 0; i < check.length;i++){
				if(check[i] == IDb){
					return true;
				}
				
			}
			return false;
	}
	
	public String getbesitzer(int ID){
		return Lands[ID].getbesitzer();
	}
	
	
	public int[] grenztan(int ID){ // Gib Nachbarländer(ID) als Array zurück. 
	
		
		// Nordamerika
		if(ID == 0){
			int[] a = {1,3,30};
			return a;
		}
		if(ID == 1){
			int[] a = {1,2,3,4};
			return a;
		}
		if(ID == 2){
			int[] a = {1,5,4,19};
			return a;
		}
		if(ID == 3){
			int[] a = {0,1,4,6};
			return a;
		}
		if(ID == 4){
			int[] a = {1,2,3,6,7,5};
			return a;
		}
		if(ID == 5){
			int[] a = {2,4,7};
			return a;
		}
		if(ID == 6){
			int[] a = {3,4,7,8};
			return a;
		}
		if(ID == 7){
			int[] a = {4,5,6,8};
			return a;
		}
		if(ID == 8){
			int[] a = {6,7,9};
			return a;
		}
		// Südamerika
		if(ID == 9){
			int[] a = {8,10,11};
			return a;
		}
		if(ID == 10){
			int[] a = {9,11,12};
			return a;
		}
		if(ID == 11){
			int[] a = {9,10,12,13};
			return a;
		}
		if(ID == 12){
			int[] a = {10,11};
			return a;
		}
		if(ID == 13){
			int[] a = {11,14,15,16,24,25};
			return a;
		}
		if(ID == 14){
			int[] a = {13,15,25,35};
			return a;
		}
		if(ID == 15){
			int[] a = {13,14,16,17,18};
			return a;
		}
		if(ID == 16){
			int[] a = {13,15,17,18};
			return a;
		}
		if(ID == 17){
			int[] a = {16,15,18};
			return a;
		}
		if(ID == 18){
			int[] a = {17,15};
			return a;
		}
		// Europa
		if(ID == 19){
			int[] a = {20,22};
			return a;
		}
		if(ID == 20){
			int[] a = {19,22,23,21};
			return a;
		}
		if(ID == 21){
			int[] a = {20,23,25,35,33,26};
			return a;
		}
		if(ID == 22){
			int[] a = {19,20,23,24};
			return a;
		}
		if(ID == 23){
			int[] a = {20,21,25,24,22};
			return a;
		}
		if(ID == 24){
			int[] a = {22,23,25,13};
			return a;
		}
		if(ID == 25){
			int[] a = {23,24,13,14,35,21};
			return a;
		}
		// Asien
		if(ID == 26){
			int[] a = {27,33,34,21}; // 21 genauer Prüfen
			return a;
		}
		if(ID == 27){
			int[] a = {26,34,31,29,28};
			return a;
		}
		if(ID == 28){
			int[] a = {27,29,30};
			return a;
		}
		if(ID == 29){
			int[] a = {27,28,30,31};
			return a;
		}
		if(ID == 30){
			int[] a = {28,29,31,32,0};
			return a;
		}
		if(ID == 31){
			int[] a = {29,28,30,34,32};
			return a;
		}
		if(ID == 32){
			int[] a = {30,31};
			return a;
		}
		if(ID == 33){
			int[] a = {21,35,36,34,26};
			return a;
		}
		if(ID == 34){
			int[] a = {31,27,26,33,36,37};
			return a;
		}
		if(ID == 35){
			int[] a = {14,15,25,21,33,36};
			return a;
		}
		if(ID == 36){
			int[] a = {35,33,34,37};
			return a;
		}
		if(ID == 37){
			int[] a = {36,34,38};
			return a;
		}
		// Australien
		if(ID == 38){
			int[] a = {37,39,40};
			return a;
		}
		if(ID == 39){
			int[] a = {38,40,41};
			return a;
		}
		if(ID == 40){
			int[] a = {38,39,41};
			return a;
		}
		if(ID == 41){
			int[] a = {40,39};
			return a;
		}
			
		int[] a = {1,2,3,4,5};
		return a;
	}
	
	public int kuerzeltoid(String kuerzel){ // String Kürzel wird in ID des Landes gewandelt.
		
		for (int i = 0; i < 42; i++){
				if(Lands[i].getkuerzel().equals(kuerzel)){
					return i;
					
				}
				
		}
		return 43;
	}
	public int checkkontinents(String spieler){
		int bonus = 0;
		int count = 0;
		//Bonus Check für Nordamerika
		for(int i = 0; i <= 8; i++){
			
				if(Lands[i].getbesitzer().equals(spieler)){
					count++;
				}
				if(count == 9){
					bonus = bonus +5;
				}
		}
		//Bonus Check für Südamerika
		count = 0;
		for(int i = 9; i <= 12; i++){
			
				if(Lands[i].getbesitzer().equals(spieler)){
					count++;
				}
				if(count == 4){
					bonus = bonus +2;
				}
		}
		//Bonus Check für Afrika
		count = 0;
		for(int i = 13; i <= 18; i++){
			
				if(Lands[i].getbesitzer().equals(spieler)){
					count++;
				}
				if(count == 6){
					bonus = bonus +3;
				}
		}
		//Bonus Check für Europa
		count = 0;
		for(int i = 19; i <= 25; i++){
			
				if(Lands[i].getbesitzer().equals(spieler)){
					count++;
				}
				if(count == 7){
					bonus = bonus +3;
				}
		}
		//Bonus Check für Asien
		count = 0;
		for(int i = 26; i <= 37; i++){
			
			if(Lands[i].getbesitzer().equals(spieler)){
				count++;
			}
			if(count == 12){
				bonus = bonus +7;
			}
			
		}
		//Bonus Check für Australien
		count = 0;
		for(int i = 38; i <= 41; i++){
			
			if(Lands[i].getbesitzer().equals(spieler)){
				count++;
				System.out.println(count);
			}
			if(count == 4){
				System.out.println("Bonus");
				bonus = bonus +2;
			}
			
		}
		
		return bonus;
	}
	
}
