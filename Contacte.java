public class Contacte {
    // Atributs
    private String nom;
    private int telefon;

    // Constructor sense paràmetres
    public Contacte() {
        this.nom = null;
        this.telefon = 0;
    }
    // Constructor amb dos paràmetres
    public Contacte(String _nom, int _telefon) {
        this.nom = _nom;
        this.telefon = _telefon;
    }
    // Setters
    public void setNom(String _nom){        
        this.nom=_nom.toUpperCase();
    }
    public void setTelefon(int _tel){
        this.telefon=_tel;
    }
    // Getters
    public String getNom() {
        return this.nom;
    }
    public int getTelefon() {
        return this.telefon;
    }
}