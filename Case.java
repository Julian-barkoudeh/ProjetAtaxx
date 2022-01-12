public class Case {

    private String couleur;
    private String couleurSelection;
    private int ord;
    private int abs;
    
    public Case(int ord, int abs ){
        couleur = "blanc";
        this.couleurSelection = "black";
        this.ord = ord;
        this.abs = abs;
    }
    public Case(String couleur, int ord, int abs ){
        this.couleur = couleur;
        this.couleurSelection = "black";
        this.ord = ord;
        this.abs = abs;
    }

    public void setCouleur(String c){
        this.couleur  = c;
    }
    public String getCouleur(){
        return couleur;
    }
    public String getCouleurSelection(){
        return couleurSelection;
    }
    public void setCouleurSelection(String couleurSelection) {
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

}
