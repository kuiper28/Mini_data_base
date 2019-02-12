import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author kuiper
 * Clasa in care se creeaza o instanta
 * Atributele si valorile lor sunt salvate
 * intr-un linkedhashmap de forma (Key:Atribut,valKey:ValAtribut)
 */
public class creeazaInstanta extends creeazaEntitate {
    int numarAtribute;
    LinkedHashMap<String, String>  instante = new LinkedHashMap<>();
    LinkedHashMap<String, String>  hash = new LinkedHashMap<>();
    
    // Initializez o instanta.
    public creeazaInstanta (String nume,int nr,int numarAtr) {
        super(nume,nr,numarAtr);

    }
    
    // Adaug o pereche:Atribut-ValAtribut in instante. 
    public void adaugaInstanta(String key,String instanta) {
          instante.put(key,instanta);
    }
    
    /* In hash-ul din aceasa clasa copiez hash-ul din clasa
       creeazaEntitate,pentru a stii Atrributul si tipul atributului.
    */
    public void copiaza (LinkedHashMap<String, String>  hash) {
        this.hash = hash;
    }
    
    // Getter pentru a obtine primaryKey-ul unei entitati.
    public String getPrimaryKey () {
        return instante.get(instante.keySet().toArray()[0]);
    }
    
    /* Metoda care realizeaza afisarea unei instante (adica
       a atributelor si valorilor acestor atribute).
    */ 
    public void afis(BufferedWriter wr) throws IOException {
        wr.write(super.getNume() + " ");
        int p = 0;
        for (String key : instante.keySet()) {
          p++;
          wr.write(key + ":" + instante.get(key)+ "");
          if (p < super.getRf())
              wr.write(" ");
          
        }
        wr.newLine();
    }
    
    // Getter pentru factorul de replicare al unei instante.
    public int getRf() {
        return super.getRf();
    }
}
