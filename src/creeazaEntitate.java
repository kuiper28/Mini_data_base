import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author kuiper
 * Clasa in care se creeaza o entitate.
 * Atributele si tipurile atributelor fiind salvate
 * intr-un linkedhashmap de tipul (Key:Atribut,valKey:tipAtribut).
 */
public class creeazaEntitate {
    private String nume;
    private int    RF;
    private int    numarAtribute;
    LinkedHashMap<String, String>  hash = new LinkedHashMap<>();
    
    // Intitializez o entitate
    public creeazaEntitate(String nume, int rf,int nrAtribute) {
        this.nume          = nume;
        this.RF            = rf;
        this.numarAtribute = nrAtribute;
    }
    
    // Adaug o pereche:Atribut-tipAtribut in hash. 
    public void adauga(String atr1, String atr2) {
        hash.put(atr1,atr2);
    }
    
    // Getter pentru factorul de replicare al unei entitati. 
    public int getRf () {
        return RF;
    }
    
    // Getter pentru numarul de atribute.
    public int getAtribute () {
        return this.numarAtribute;
    }
    
    // Geter pentru nume.
    public String getNume () {
        return this.nume;
    }
    
    // Getter pentru hash.
    public LinkedHashMap<String, String> getHash() {
        return this.hash;
    }
}
