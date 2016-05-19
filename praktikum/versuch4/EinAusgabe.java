package praktikum.versuch4;

import java.io.*;
import java.util.*;

public class EinAusgabe implements Serializable{
  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
      int sets;
      
      Scanner sc = new Scanner(new FileReader("praktikum/versuch4/daten.txt"));
      Mensch m1 = new Mensch();
      
      sets = sc.nextInt();
      System.out.println(sets);
      
      Mensch[] MList = new Mensch[sets];     
      for(int i = 0 ; i < sets; i++){
        MList[i] = new Mensch(sc.next(),sc.nextDouble(),sc.nextInt());
      }
    
      for(int i = 0; i < sets; i++){
        System.out.println(MList[i].print());
      }     
      ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("daten2(serialized)"));
      oos.writeObject(MList);
      oos.close();    //Strom schlie�en
    
      ObjectInputStream ois = new ObjectInputStream (new FileInputStream("daten2(serialized)"));
    Mensch[] MList2 = new Mensch[sets];
    MList2 = (Mensch[]) ois.readObject();
    ois.close();    //Strom schlie�en
    
    System.out.println("Set 2:");
    
    for(int i = 0; i < sets; i++){
      System.out.println(MList2[i].print());
      }
  }
}
