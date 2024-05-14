import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agenda {

    Contacte[] llistaDeContactes = new Contacte[99];
    // Comptador dels contactes creats.
    // Variable molt important!.
    private int quantitatDeContactes = 0;

    public void consultar(String _nom, int _telefon) {
        for (int i = 0; i < this.quantitatDeContactes; i++) {
            if (_nom.equals(this.llistaDeContactes[i].getNom())) {
                System.out.println("Ja existeix un contacte amb aquest nom!");
            }
        }

    }

    public void afegir(String _nom, int _telefon) {
        if (quantitatDeContactes < 99) {
            this.llistaDeContactes[quantitatDeContactes] = new Contacte();
            this.llistaDeContactes[quantitatDeContactes].setNom(_nom);
            this.llistaDeContactes[quantitatDeContactes].setTelefon(_telefon);
            this.quantitatDeContactes++;
            ordenar();
        } else {
            System.out.println("L'agenda està plena!");
        }

    }

    public void buscar(String _nom) {
        boolean trobat = false;

        for (int i = 0; i < quantitatDeContactes; i++) {
            if (_nom.equals(this.llistaDeContactes[i].getNom())) {
                System.out.println(this.llistaDeContactes[i].getNom() + "-" +
                    "Tel.: " + this.llistaDeContactes[i].getTelefon());
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("El contacte no existeix!");
        }
    }

    public void ordenar() {
        //Aquest mètode ordenarà el vector de contactes
        // en funció del nom fent servir el Mètode Bombolla
        int midaVector = this.quantitatDeContactes;
        String nom1;
        String nom2;
        // Optimitzat per quan es tingui més de dos elements com a mínim.
        if (quantitatDeContactes >= 2) {
            for (int i = 1; i <= midaVector - 1; i++) {
                for (int j = 1; j <= midaVector - i; j++) {
                    nom1 = this.llistaDeContactes[j - 1].getNom();
                    nom2 = this.llistaDeContactes[j].getNom();
                    if (nom1.charAt(0) > nom2.charAt(0)) {
                        Contacte tmp = this.llistaDeContactes[j - 1];
                        this.llistaDeContactes[j - 1] = this.llistaDeContactes[j];
                        this.llistaDeContactes[j] = tmp;
                    }
                }
            }
        }
    }

    public void mostrar() {
        if (this.quantitatDeContactes == 0) {
            System.out.println("No hi ha contactes!");
        } else {
            for (int t = 0; t < this.quantitatDeContactes; t++) {
                // És necessari per precaució fer servir el this per al mètode,
                // ja que si s'executa moltes vegades les referències a memòria poden fallar.
                System.out.println(this.llistaDeContactes[t].getNom() + "-" +
                     "Tel: " + Integer.toString(this.llistaDeContactes[t].getTelefon()));
            }
        }
    }

    public void buidar() {
        try {
            System.out.println("S'eliminaran tots els contactes");
            System.out.println("Estas segur (S/N)?");
            String resposta;
            BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));
            resposta = teclat.readLine();
            resposta = resposta.toUpperCase();
            if (resposta.equals("S")) {
                // Fet per mera formalitat perquè el java s'encarrega d'alliberar
                // els objectes no referenciats creats. El garbage collector
                for (int i = 0; i < this.quantitatDeContactes; i++) {
                    this.llistaDeContactes[i].setNom("");
                    this.llistaDeContactes[i].setTelefon(0);
                }
                quantitatDeContactes = 0;
                System.out.println("Agenda buidada correctament!");
            } else {
                System.out.println("Acció cancel·lada");
            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void esborrar() {
        try {
            boolean trobat = false;
            BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nom de contacte a eliminar: ");
            String eliminar = teclat.readLine().toUpperCase();
            if (quantitatDeContactes == 0) {
                System.out.println("No hi ha contactes");
            } else {
                for (int i = 0; i < quantitatDeContactes; i++) {

                    if (eliminar.equals(this.llistaDeContactes[i].getNom())) {
                        System.out.println(i + 1 + ". " + 
                            this.llistaDeContactes[i].getNom() + "-" + 
                            "Tel:" + this.llistaDeContactes[i].getTelefon());
                        trobat = true;
                    }
                }
                if (trobat) {
                    System.out.println("Quin contacte vols eliminar, introdueix el número associat?");
                    int eliminar_numero = Integer.parseInt(teclat.readLine());
                    eliminar_numero--;
                    System.out.println("Estàs segur (S/N)?");
                    String resposta;
                    resposta = teclat.readLine();
                    resposta = resposta.toUpperCase();
                    if (resposta.equals("S")) {
                        Contacte[] temporal = new Contacte[99];
                        int ii = 0;
                        boolean trobat2=false;
                        for (int i = 0; i < this.quantitatDeContactes; i++) {

                            if (i != eliminar_numero) {
                                // Es crea l'objecte temporal per esborrar-lo
                                if (!trobat2) {
                                  temporal[ii] = this.llistaDeContactes[ii];
                                  ii++;
                                }
                                else {
                                    if (ii<this.quantitatDeContactes){ 
                                        temporal[ii] = this.llistaDeContactes[ii+1];
                                     ii++;
                                    }
                                }

                            } else {
                                temporal[ii] = this.llistaDeContactes[ii + 1];
                                ii++;
                                trobat2=true;
                            }
                        }
                        this.quantitatDeContactes--;
                        System.out.println("Contacte eliminat correctament");
                        for (int j = 0; j < this.quantitatDeContactes; j++) {
                            this.llistaDeContactes[j] = temporal[j];
                        }
                    }

                } else {
                    System.out.println("Em sap greu, no s'ha trobat el nom!");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        try {
            boolean trobat = false;
            BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nom de contacte a modificar:");
            String eliminar = teclat.readLine().toUpperCase();
            if (quantitatDeContactes == 0) {
                System.out.print("No hi ha contactes!");
            } else {
                for (int i = 0; i < this.quantitatDeContactes; i++) {
                    if (eliminar.equals(this.llistaDeContactes[i].getNom())) {
                        System.out.println(i + 1 + ". " + this.llistaDeContactes[i].getNom() + "-" + 
                            "Tel:" + this.llistaDeContactes[i].getTelefon());
                        trobat = true;
                    }
                }
                if (trobat) {
                    System.out.print("Quin contacte vols modificar?, entra el número: ");
                    int modificar_numero = Integer.parseInt(teclat.readLine());

                    System.out.print("Entra el nom: ");
                    String nouNom = teclat.readLine();
                    System.out.print("Introdueix telèfon, format numèric: ");
                    int nouTelefon = Integer.parseInt(teclat.readLine());

                    this.llistaDeContactes[modificar_numero - 1].setNom(nouNom);
                    this.llistaDeContactes[modificar_numero - 1].setTelefon(nouTelefon);
                    ordenar();
                } else {
                    System.out.println("No hi ha contactes amb aquest nom");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}