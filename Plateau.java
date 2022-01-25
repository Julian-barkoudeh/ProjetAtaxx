import java.util.ArrayList;

public class Plateau {
    ArrayList<ArrayList<Case>> board = new ArrayList<>(); //ArreyListe de 2 dimensions
    int dimension = 7;                                    //Taille du plateau
    
    public Plateau(){
        initPlateau();
        initElements();
    }

    /**
     * Initialise le plateau avec des cases vides
     * @return void
     */
    public void initPlateau(){
        for(int i=0; i < dimension; i++) {
            board.add(new ArrayList<Case>());
        }

        for(int i = 0; i<dimension; i++){
            for(int j = 0; j<dimension; j++){
                board.get(i).add(j,new CaseVide(i, j));
            }
        }
    }
    
    /**
     * Initialise les éléments présents en début du jeu : les obstacles + les 4 pions
     * @return void
     */
    public void initElements(){
        //Initialisation des 4 pions 
        board.get(0).set(0,new Pion(Couleur.ROUGE_BCK, 0, 0));
        board.get(0).set(dimension-1,new Pion(Couleur.BLEU_BCK, 0, dimension-1));
        board.get(dimension-1).set(dimension-1,new Pion(Couleur.ROUGE_BCK, dimension-1, dimension-1));
        board.get(dimension-1).set(0,new Pion(Couleur.BLEU_BCK, dimension-1, 0));

        //Initialisation des obstacles 
        board.get(2).set(3, new Obstacle(2, 3));
        board.get(3).set(2, new Obstacle(3, 2));
        board.get(3).set(4, new Obstacle(3, 4));
        board.get(4).set(3, new Obstacle(4, 3));
    }

    /**
     * Permet de retourner une case spécifique du plateau
     * @param ord numéro de la ligne de la case dans le plateau
     * @param abs numéro de la colonne de la case dans le plateau
     * @return retourne un élément de type Case
     */

    public Case donnerCase(int ord, int abs){
        return board.get(ord).get(abs);
    }

    /**
     * Effectue la selection d'une case spécifique dans le plateau. La méthode parcours un carré 5*5 autour de la case et change la couleur des crochets
     * @param ord numéro de la colonne de la case à selectionner dans le plateau
     * @param abs numéro de la ligne de la case à selectionner dans le plateau
     * @return void
     */
    public void selectionnerCase(int ord, int abs){
        board.get(ord).get(abs).setCouleurSelection(Couleur.JAUNE_FOR);//La case à selectionner 

        for(int i = ord-2; i<=ord+2; i++){
            for(int j = abs-2; j<=abs+2; j++){
                if(verifieCordonnee(i,j)){ //Vérifie si l'élément parcouru est dans le plateau
                    if(!caseEstObstacle(j, i)) { 
                        if(!(i == ord && j == abs)){// Evite de parcourir la case à selectionner 
                            if(caseEstProche(donnerCase(ord, abs), donnerCase(i, j))){
                                donnerCase(i, j).setCouleurSelection(Couleur.VERT_FOR);
                            }
                            else if(caseEstDistante(donnerCase(ord, abs), donnerCase(i, j))){
                                donnerCase(i, j).setCouleurSelection(Couleur.MAGENTA_FOR);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Vérifie si les coordonnées passées en paramètre sont dans le plateau
     * @param i numéro de la colonne de la case à selectionner dans le plateau
     * @param j numéro de la ligne de la case à selectionner dans le plateau
     * @return True si la case est dans le plateau, False sinon
     */
    public boolean verifieCordonnee(int i, int j){
        if((i>=0 && i<dimension) && (j>=0 && j<dimension)){
            return true;
        }
        return false;
    }

    /**
     * Vérifie si un Pion est bloqué et ne peut pas effectuer un déplacement dans un carré de 5*5
     * @param Pion case de type Pion 
     * @return True si le pion est bloqué, False sinon 
     */
    public boolean verifieSiBloquee(Pion p){
        int cptCases = 0; // nombre de cases dans le plateau autour du pion dans un carré 5*5
        int cptCasesOcuppees= 0; // nombre de cases occupées dans le plateau autour du pion dans un carré 5*5

        for(int i = p.getOrd()-2; i<=p.getOrd()+2; i++){
            for(int j = p.getAbs()-2; j<=p.getAbs()+2; j++){
                if(verifieCordonnee(i,j)){
                    cptCases++; 
                    if(!caseEstVide(i,j)){
                        cptCasesOcuppees++;
                    }
                }
            }
        }
        if(cptCasesOcuppees == cptCases){ //Toutes les cases autour du pion sont occupées
            return true;
        }
        return false;
    }

    /**
     * Vérifie si une case est un obstacle 
     * @param ord numéro de la colonne de la case dans le plateau
     * @param abs numéro de la ligne de la case dans le plateau
     * @return True si la case est un obstacle, False sinon  
     */
    public boolean caseEstObstacle(int ord, int abs){
        if(donnerCase(ord, abs).getClass().getName() == "Obstacle"){
            return true;
        }
        return false;
    }

    /**
     * Vérifie si une case est vide
     * @param ord numéro de la ligne de la case dans le plateau
     * @param abs numéro de la colonne de la case dans le plateau
     * @return True si la case est un vide, False sinon  
     */
    public boolean caseEstVide(int ord, int abs){
        if(donnerCase(ord, abs).getClass().getName() == "CaseVide"){
            return true;
        }
        return false;
    }

   /**
     * Vérifie si une case est à une distance de 1 d'une autre case
     * @param caseSelectione la case de référence 
     * @param caseProximite la case à vérifier par rapport la case de référence
     * @return True si la "caseProximite" est proche de "caseSelectione" , False sinon
     */
    public boolean caseEstProche(Case caseSelectione,Case caseProximite){ 
        if((caseProximite.getAbs() >= caseSelectione.getAbs() -1) && (caseProximite.getAbs() <= caseSelectione.getAbs() +1)){
            if((caseProximite.getOrd() >= caseSelectione.getOrd() -1 ) && (caseProximite.getOrd() <= caseSelectione.getOrd() +1)){
                return true;
            }
        }
        return false;
    }
     /**
     * Vérifie si une case est de distance 2 d'une autre case
     * @param caseSelectione la case de référence 
     * @param caseProximite la case à vérifier par rapport la case de référence
     * @return True si la "caseProximite" est distante de "caseSelectione" , False sinon
     */
    public boolean caseEstDistante(Case caseSelectione,Case caseProximite){
        if(!caseEstProche(caseSelectione, caseProximite)){
            if((caseProximite.getAbs() >= caseSelectione.getAbs() -2 )&& (caseProximite.getAbs() <= caseSelectione.getAbs() +2)){
                if((caseProximite.getOrd() >= caseSelectione.getOrd() -2) && (caseProximite.getOrd() <= caseSelectione.getOrd() +2 )){
                    return true;
                }
            }
        }
        return false; 
    }

     /**
     * Effectue le deplacement (proche et distant) d'un pion dans le plateau
     * @param p le pion à deplacer
     * @param ord numéro de la ligne où il faut deplacer le pion
     * @param abs numéro de la colonne où il faut deplacer le pion
     * @return void
     */
    public void deplacer(Pion p, int ord, int abs){
        enleverSelection(p.getOrd(),  p.getAbs()); 
        board.get(ord).set(abs,new Pion(p.getCouleur(), ord, abs)); // Créer un pion dans les nouvelles coordonnées

        //Création d'une case vide dans les anciennes coordonnées si le déplacement est distant
        if(caseEstDistante(p, donnerCase(ord, abs))){
            board.get(p.getOrd()).set(p.getAbs(), new CaseVide(p.getOrd(), p.getAbs()));
        }
    }

    /**
     * Effectue l'infection des cases autour d'un pion passé en paramètre et met à jour le score du joueur
     * @param p le pion de référence
     * @param joueur le joueur qui possède le pion
     * @return void
     */
    public void infecter(Pion p, Joueur joueur){
        for(int i = p.getOrd()-1; i<=p.getOrd()+1; i++){
            for(int j = p.getAbs()-1; j<=p.getAbs()+1; j++){
                if(verifieCordonnee(i,j)){
                    //Infecter seulement les cases de type pion et qui n'appartiennent pas au joueur 
                    if((donnerCase(i,j).getClass().getName() == "Pion") && (donnerCase(i,j).getCouleur() != p.getCouleur())){
                       donnerCase(i, j).setCouleur(p.getCouleur());
                       joueur.incrementerScore();
                    }
                }
            }
        }
    }

     /**
     * Permet d'enlever les couleurs de selection autour une case de référence dans un carré de 5*5
     * @param ord numéro de la ligne de la case dans le plateau
     * @param abs numéro de la colonne de la case dans le plateau
     * @return void
     */
    public void enleverSelection(int ord, int abs){
        for(int i = ord-2; i<=ord+2; i++){
            for(int j = abs-2; j<=abs+2; j++){
                if(verifieCordonnee(i,j)){
                    donnerCase(i, j).setCouleurSelection(Couleur.GRIS_FOR);
                }
            }
        }
    }

    /**
     * Affiche le plateau dans le terminal
     * @return void
     */
    public void affichePlateau(){
        System.out.println("    1        2        3        4        5        6        7");
        for(int i = 0; i<dimension; i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j<dimension; j++){
                System.out.print(Couleur.NORMALE_BCK);
                System.out.print(donnerCase(i, j).toString());
               
            }
            System.out.print("\n\n");
        } 
        System.out.print("\n\n");
    }
    public  static void main (String [] args){
        Plateau p = new Plateau();
        CaseVide c1 = new CaseVide(5, 6);
        Pion pio = new Pion(Couleur.NORMALE_BCK, 6, 8);
        Joueur j = new Joueur("bleu", Couleur.BLEU_BCK);
        System.out.println(pio.getClass().getName() == c1.getClass().getName());
        p.affichePlateau();
        p.deplacer((Pion) p.donnerCase(0,p.dimension-1), 2, 5);
        p.selectionnerCase(2,5);
        p.affichePlateau();
        p.enleverSelection(2,5);
        p.affichePlateau();
        p.deplacer((Pion) p.donnerCase(2, 5), 4, 5);
        p.affichePlateau();
        p.deplacer((Pion) p.donnerCase(4, 5), 5, 5);
        p.affichePlateau();
        p.infecter((Pion) p.donnerCase(5, 5), j);
        p.affichePlateau();
    }
}
