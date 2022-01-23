public class Case {

    private Couleur couleur;
    private Couleur couleurSelection;
    private int ord;
    private int abs;
    
    public Case(int ord, int abs ){
        couleur = Couleur.NORMALE_BCK;
        this.couleurSelection = Couleur.GRIS_FOR;
        this.ord = ord;
        this.abs = abs;
    }
    public Case(Couleur couleur, int ord, int abs ){
        this.couleur = couleur;
        this.couleurSelection = Couleur.GRIS_FOR;
        this.ord = ord;
        this.abs = abs;
    }

    public void setCouleur(Couleur c){
        this.couleur  = c;
    }
    public Couleur getCouleur(){
        return couleur;
    }
    public Couleur getCouleurSelection(){
        return couleurSelection;
    }
    public void setCouleurSelection(Couleur couleurSelection) {
        this.couleurSelection = couleurSelection;
    }
    public void setOrdAbs(int ord, int abs){
        this.ord = ord;
        this.abs = abs;
    }
    public void setOrdAbs(Case c){
        this.ord = c.getOrd();
        this.abs = c.getAbs();
    }
    public int getOrd(){
        return ord;
    }
    public int getAbs(){
        return abs;
    }
    public String toString(){
        return couleurSelection + "[" + couleur + "   " + Couleur.NORMALE_BCK + "]    " + Couleur.NORMALE_FOR;
    }

}
