import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kuiper
 * Creeaza un nod care este reprezentat ca un arraylist
 * de instante.
 */
public class Nod {
    int index;
    int capacitateMaxima;
    int areInstante = 0;
    
    public ArrayList<creeazaInstanta> noduri = new ArrayList<creeazaInstanta>();
    
    // Initializarea unui nod.
    public Nod (int index,int capacitateMax) {
        this.index = index;
        this.capacitateMaxima = capacitateMax;
    }
    
    // Adaugarea unei instante in  nod.
    public void adaugaInsNod(creeazaInstanta ins) {       
        noduri.add(0,ins); // Adaugarea se realizeaza intotdeauna la inceput.
        this.areInstante ++;
    }
    
    /* Metoda de afisare a unUI nod (adica se apeleaza metoda de
       afisare a unei instante pentru ficare instanta din nod).       
    */
    public void afiseaza(BufferedWriter writer) throws IOException {
        for(int i = 0; i < noduri.size(); i++) {
            noduri.get(i).afis(writer);
                    
        }
    }
    
    /* Stergerea unei instante din nod,adica
       instanta care are primaryKey-ul egal valAtr.       
    */
    public void deleteInstance (BufferedWriter writer,String valAtr,int [] gasit) throws IOException {
        for (int i = 0; i < noduri.size(); i++) {
            if (noduri.get(i).getPrimaryKey ().equals(valAtr)) {
                noduri.remove(i);
                gasit[0] = 1;
            }
        }
    }
    
    /* Obtinerea unei instante din nod, instanta care are 
       primaryKey-ul egal cu valAtr.
    */
    public boolean getInstance (BufferedWriter writer,String entitate,String valAtr,creeazaInstanta [] retine) {
         for (int i = 0; i < noduri.size(); i++) {
            if (noduri.get(i).getNume ().equals (entitate)) {
            if (noduri.get(i).getPrimaryKey ().equals(valAtr)) {
                retine[0] = noduri.get(i);
                return true;
            }
            }
    }
         return false;
    }
    
    /* Metoda care face actualizarea unei instante din nod,
       instanta care are primaryKey-ul egal str2.        
    */
    public void updateInstance (String str1,String str2,String newVal,creeazaInstanta[] ins,int [] val) {
         for (int i = 0; i < noduri.size(); i++) {
              for (String key : noduri.get(i).instante.keySet()) {
                  if (noduri.get(i).instante.get(key).equals(str1)){
                      for (String key1 : noduri.get(i).instante.keySet()) {
                          if (key1.equals(str2)){
                              
                              noduri.get(i).instante.put(key1, newVal);
                              ins[0] = noduri.get(i);
                              val[0] = noduri.get(i).getAtribute();
                          }
                            
                          }
                  }
              }
         }
    }
    
    // Getter pentru arraylist-ul de instante.
    public ArrayList getArray() {
        return this.noduri;
    }
    
    // Getter pentru numarul de instante dintr-un nod.
    public int getSize() {
        return this.noduri.size();
    }
    
    // Getter pentru indicele nodului.
    public int getIdx () {
        return this.index;
    }
    
    /* Getter pentru numarul areInstante, care ne
       spune numarul de instante din nod. 
    */
    public int getInstante () {
        return this.areInstante;
    }
}
