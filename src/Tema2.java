import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;

/**
 *
 * @author kuiper
 * Clasa principala in care realizez
 * parsarea fisierelor si apelarea 
 * operatiilor.
 */
public class Tema2 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
        FileInputStream fstream = new FileInputStream(args[0]);
            try (DataInputStream in = new DataInputStream(fstream)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[0] + "_out"));
                String strLine;
                strLine = br.readLine();
                String [] tokens; /* String-ul in care se vor afla elementele de
                		     pe o linie */ 
                
                tokens = strLine.split(" ");
                int nrNoduri = Integer.parseInt(tokens[2]);
                int capacitateMaxima = Integer.parseInt(tokens[3]);
                creeazaDB bazaDate = new creeazaDB(tokens[1],nrNoduri,capacitateMaxima); 
                bazaDate.initializeazaDB(); // Initializez baza de date cu numarul maxim de noduri.
                
                // arr este arraylist-ul in care vor fi salvate entitatile.
                ArrayList<creeazaEntitate> arr = new ArrayList<creeazaEntitate>();
                
                ArrayList<Nod> nods = new ArrayList<Nod>();
                while ((strLine = br.readLine()) != null) {
                   String [] tok;
                   tok = strLine.split(" ");
                   
                   // Cazul in care avem operatia "CREATE".
                   if (tok[0].equals("CREATE")) {
                       
                       // Creez o noua entitate.
                       creeazaEntitate entitate = new  creeazaEntitate (tok[1],
                       Integer.parseInt(tok[2]),Integer.parseInt(tok[3]));
                       int number = 4 + Integer.parseInt(tok[3])*2;
                       
                       // Adaug atributele
                       for (int j = 4; j < number; j += 2) {
                           entitate.adauga(tok[j],tok[j+1]);                        
                       }
                       arr.add(entitate); // Adaug entitatea in arr.
                   }
                   
                   // Cazul in care avem operatia "INSERT".
                   if (tok[0].equals("INSERT")) {
                       int integer = 0;
                       String string = "0";
                       float fl = 0;
                       int count = 0;
                       
                       /* parcurg arr si vad cand numele unei instante
                        corespunde cu numele unei entitati. */
                       for (int i = 0; i < arr.size(); i++) {
                           if (tok[1].equals(arr.get(i).getNume())) {
                               
                                // Creez o noua instanta.
                                creeazaInstanta ins = new creeazaInstanta(tok[1],
                                arr.get(i).getAtribute(),arr.get(i).getRf());
                                
                                /* In noua instanta voi pune atributele
                                    corespunzatoare entitatii din care provine.
                                */
                                ins.copiaza(arr.get(i).getHash());
                                
                                /* Adaug valorile atributelor corespunzatoare in
                                 instanta */
                               for (String key : arr.get(i).hash.keySet()) {
                                      if (arr.get(i).hash.get(key).equals("Integer")){
                                        ins.adaugaInstanta(key,tok[2+count]);
                                      }
                                    else
                                      if (arr.get(i).hash.get(key).equals("String")) {
                                          ins.adaugaInstanta(key,tok[2+count]);
                                      }
                                    else  
                                      if (arr.get(i).hash.get(key).equals("Float")){

					/* Tratez cazul in care valoarea atributului
					   instantei corespunzatoare unei entitati care
		                           la acelasi atribut are tipul float,este,in
					   acest caz ,de fapt, o valoare intreaga.(Ex:
				           490.0).
				        */    
                                          int x = 0;
                                          float f = Float.parseFloat(tok[2+count]);
                                             int iPart = (int) f;
                                             double fPart = f;
                                             if(fPart - iPart == 0) {
                                                 x = (int)f;
                                                ins.adaugaInstanta(key,Integer.toString(x));
                                             } else
                                                 ins.adaugaInstanta(key,tok[2+count]);
                                    
                                      }
                                    count++;
                                }
                                  bazaDate.adaugaElement(ins,arr.get(i).getRf());
                           }
                       }
                   }
                   
                   // Cazul in care comanda este "SNAPSHOTDB".
                   if (tok[0].equals("SNAPSHOTDB")) {
                   bazaDate.printBazaDate(writer);
                   }
                   
                   // Cazul in care comanda este "DELETE".
                   if (tok[0].equals("DELETE")) {
                   bazaDate.Delete(writer,tok[2]);
                   }
                   
                   // Cazul in care comanda este "GET".
                   if (tok[0].equals("GET")) {
                   bazaDate.Get(writer,tok[1],tok[2]);
                   }
                   
                   // Cazul in care comanda este "UPDATE".
                    if (tok[0].equals("UPDATE")) {
                       int fl = 0;
                   for (int i = 0; i < tok[4].length(); i++) {
                       if(tok[4].charAt(i) == '.') {
                           fl = 1;
                       }           
                   }
                   if (fl == 1) {
                        int x = 0;
                        float f = Float.parseFloat(tok[4]);
                        int iPart = (int) f;
                        double fPart = f;
                        if(fPart - iPart == 0) {
                        x = (int)f;
                        bazaDate.Update (writer,tok[2], tok[3], Integer.toString(x));
                   } else
                        bazaDate.Update (writer,tok[2], tok[3], tok[4]);
                   }else
                     bazaDate.Update (writer,tok[2], tok[3], tok[4]);

                }

                }
                writer.close();
    } 
    
} catch (IOException e){
         System.err.println("Error: " + e.getMessage());
       }
}
}
