import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppAgenda {

    public static void main(String[] args) {
        String nomContacte;
        String telefonContacte;
        boolean numeroValid;
        BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));
        char opcio;
        Agenda laMevaAgenda = new Agenda();
        
        try {
            opcio  = '1';
            while ((opcio == '1') || (opcio == '2') || (opcio == '3') || (opcio == '4') || (opcio == '5')
                    || (opcio == '6')) {
                System.out.println("AGENDA");
                System.out.println("1 - Nou contacte");
                System.out.println("2 - Buscar contacte");
                System.out.println("3 - Modificar contacte");
                System.out.println("4 - Eliminar contacte");
                System.out.println("5 - Llistar contactes");
                System.out.println("6 - Buidar agenda");
                System.out.println("0 - Sortir");
                
                System.out.print("Entra una opció: ");
                opcio = teclat.readLine().charAt(0);

                switch (opcio) {
                    case '1':
                        System.out.print("Entra el nom: ");
                        nomContacte = teclat.readLine();
                        System.out.print("Entra el telèfon: ");
                        telefonContacte = teclat.readLine();
                        numeroValid = esCorrecte(telefonContacte);
                        if (numeroValid) {
                            int telefonoEnter = Integer.parseInt(telefonContacte);
                            laMevaAgenda.consultar(nomContacte, telefonoEnter);
                            laMevaAgenda.afegir(nomContacte, telefonoEnter);
                        } else {
                            System.out.println("ERROR! El format de telèfon és incorrecte!.");
                        }
                        break;

                    case '2':
                        System.out.print("Nom a buscar: ");
                        nomContacte = teclat.readLine().toUpperCase();
                        laMevaAgenda.buscar(nomContacte);
                        break;

                    case '3':
                        laMevaAgenda.modificar();
                        break;

                    case '4':
                        laMevaAgenda.esborrar();
                        break;

                    case '5':
                        laMevaAgenda.mostrar();
                        break;

                    case '6':
                        laMevaAgenda.buidar();
                        break;

                    case '0':
                        System.out.println("Fins la propera!");
                        break;

                    default:
                        System.out.println("\n\tOpció incorrecta ...\n\tTorna a intentar-ho!\n");
                        opcio = '1';
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(AppAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean esCorrecte(String telefonCadena) {
        for (char caracter : telefonCadena.toCharArray()) {
            if (!Character.isDigit(caracter))
                return false;
        }
        return true;
    }
}