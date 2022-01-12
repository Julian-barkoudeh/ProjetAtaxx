public class Pion extends Case {
    private int idJoueur;
    public Pion(String couleur, int ord, int abs, int idJoueur ){
        super(couleur,ord, abs);
        this.idJoueur = idJoueur;
    }
    public int getIdJoueur(){
        return idJoueur;
    }
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
    
}
