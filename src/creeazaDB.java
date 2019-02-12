import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 *
 * @author kuiper
 * 
 * Clasa in care se creeaza baza de date , care este 
 * reprezentata ca un arraylist de noduri.
 */

public class creeazaDB {
    public String  nume;
    public int     numarNoduri;
    public int     capacitateMaxima;
    ArrayList<Nod> bazaDate = new ArrayList<Nod>(numarNoduri);
    
    
    public creeazaDB (String nume, int nrNoduri,int capacitateMax) {
        this.nume             = nume;
        this.numarNoduri      = nrNoduri;
        this.capacitateMaxima = capacitateMax;
    }

   /*  Initializarea bazei de date cu "numarNoduri" de noduri si
   `   initializarea cu capacitateaMaxima de instante si indicile 
       corespunzator
   */
   public void initializeazaDB () {
   	  for (int i = 0; i < numarNoduri; i++) {
                Nod retine = new Nod(i + 1, capacitateMaxima);
                bazaDate.add(retine);
    }
   }
   
   /* Adaugarea unui  unei instante  pe "Rf" noduri in 
      baza de date.  
   */
    public void adaugaElement (creeazaInstanta ins,int Rf) {
    int count = Rf;
    for (int i = 0; i < bazaDate.size();i++) {
     if (count > 0) {
      if (bazaDate.get(i).getSize() < capacitateMaxima) {
        bazaDate.get(i).adaugaInsNod(ins);
        count--;
      }
     }
    }    
  }
    
    // Printarea tuturor instantelor din ficare nod al bazei de date 
    public void printBazaDate (BufferedWriter wr) throws IOException{
    int count = 0;
    for (int i = 0; i < bazaDate.size(); i++) {
        if(bazaDate.get(i).getInstante() != 0) {
        wr.write("Nod" + bazaDate.get(i).getIdx());
        wr.newLine();
        count++;
        }
      if (bazaDate.get(i).getSize() > 0) {
        bazaDate.get(i).afiseaza(wr);
      } 
    }
    
   // Daca count este 0 baza de date este goala.
    if (count == 0) {
     wr.write("EMPTY DB");
     wr.newLine();
    }                  
    }
    
    // Eliminarea unei instante de pe toate nodurile bazi de date.
    public void Delete (BufferedWriter wr,String valAtribut) throws IOException {
        int [] gasit = new int [2];
        gasit[0] = 0;
        for (int i = 0; i < bazaDate.size(); i++) {
            bazaDate.get(i).deleteInstance(wr,valAtribut,gasit);
        }
        
        /* Instanta care se doreste a fi stearsa nu a fost gasita in baza de
           date.
        */
        if (gasit[0] == 0) {
            wr.write("NO INSTANCE TO DELETE");
            wr.newLine();
            
        }
    }
    
    /* Preluarea ,din baza de date, a tuturor nodurilor pe care se afla
       o anumita intanta, precum si a tuturor atributelor instantei 
       respective.
    */
    public void Get (BufferedWriter wr,String entitate,String valAtribut) throws IOException {
        int [] gasit = new int [2];
        gasit[0] = 0;
        creeazaInstanta [] retine = new creeazaInstanta[2];
        for (int i = 0; i < bazaDate.size(); i++) {
             if(bazaDate.get(i).getInstance (wr,entitate,valAtribut,retine) == true){
                 wr.write("Nod" + (i+1) + " ");
                 gasit[0] = 1;
             }            
        }
        
        // Tratez cazul in care instanta a fost gasita sau nu.
        if (gasit[0] == 1) {
            retine[0].afis(wr);
        } else 
        {
            wr.write("NO INSTANCE FOUND");
            wr.newLine();
        }
    }
    
    /* Actualizez atributele instantei cu primary-key "str1" pe toate nodurile
       in care aceasta a fost introdusa (exista o copie a ei).
    */
    
    public void Update (BufferedWriter wr,String str1, String str2,String newVal) throws IOException {
        creeazaInstanta [] ins = new creeazaInstanta[2];
        int [] gasit = new int [2];
        gasit[0] = 0;
        int [] val = new int [2];
        val [0] = 0;
        for (int i = 0; i < bazaDate.size(); i++) {
             bazaDate.get(i).updateInstance(str1,str2,newVal,ins,val);              
        }
         for (int j = 0 ; j < bazaDate.size(); j++) {
            bazaDate.get(j).deleteInstance(wr,str1,gasit);

        }
         adaugaElement(ins[0],val[0]);        
    } 
}
