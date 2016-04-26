package risiko.local.objects;
import java.util.*;

public class Spieler {
	public String Name = "Mustermann";
	public int laender = 0;
	public int armeenzuverteilen = 0;
	public ArrayList<Integer> laenderinbesitz = new ArrayList<Integer>();
	
	public int giblaender(){
		return laender;
	}
	
	public void einlanddazu(){
		laender = laender+1;
		
	}
	public void setzenamen(String name){
		Name= name;
		
	}
	public void bekommeland(int id){
		laenderinbesitz.add(id);
	}
	
	public void verliereland(int id){
		laenderinbesitz.remove(laenderinbesitz.indexOf(id)); //Bekomme Position des Landes in der ArrayList und lösche
	}
	
	public void landabziehen(){
		laender--;
	}
	
	public void setarmee(int set){
		armeenzuverteilen = set;
	}
	
	public int getarmee(){
		return armeenzuverteilen;
	}
	

	

}
