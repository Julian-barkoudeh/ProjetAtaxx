public class Case {

    private Couleur couleur;          //Couleur du fon de la case 
    private Couleur couleurSelection; //Couleur des crochets autour de la case
    private int ord;                  //Numéro de la ligne de la case dans le plateau 
    private int abs;                  //Numéro de la colonne de la case dans le plateau
    
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
   

    /**
     * Methode Getter 
     * @return la couluer du fond de la case
     */
    public Couleur getCouleur(){
        return couleur;
    }

    /**
     * Methode Getter 
     * @return couleur des crochets
     */
    public Couleur getCouleurSelection(){
        return couleurSelection;
    }

    /**
     * Methode Getter 
     * @return couleur le champ ord de la case 
     */
    public int getOrd(){
        return ord;
    }
    /**
     * Methode Getter 
     * @return couleur le champ abs de la case 
     */
    public int getAbs(){
        return abs;
    }

     /**
     * Methode Setter : permet de modifier la couleur de fond de la case
     * @param c la nouvelle couleur
     */
    public void setCouleur(Couleur c){
        this.couleur  = c;
    }

    /**
     * Methode Setter : permet de modifier la couleur des crochets autour de la case 
     * @param couleurSelection la nouvelle couleur des crochets
     */
    public void setCouleurSelection(Couleur couleurSelection) {
        this.couleurSelection = couleurSelection;
    }

    /**
     * Methode Setter : permet de modifier les coordonnées de la case dans le plateau
     * @param ord nouvelle cordonnées pour les lignes
     * @param abs nouvelle cordonnées pour les colonne
     * @return void
     */
    public void setOrdAbs(int ord, int abs){
        this.ord = ord;
        this.abs = abs;
    }

    /**
     * Methode Setter : permet de modifier les coordonnées de la case dans le plateau
     *@param c Case contenant les nouvelles coordonnées
     * @return void
     */
    public void setOrdAbs(Case c){
        this.ord = c.getOrd();
        this.abs = c.getAbs();
    }
    
    public String toString(){
        return couleurSelection + "[" + couleur + "   " + Couleur.NORMALE_BCK + "]    " + Couleur.NORMALE_FOR;
    }

}
