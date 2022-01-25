import java.util.ArrayList;


public class Ataxx {
    private ArrayList<Joueur> joueurs = new ArrayList<>(); //Arrey liste des joueurs
    private Plateau p;                                     //Pleateau contenant les cases
    private int[] ijSelectionCourant = new int[2];         //Tableau pour stocker les coordonnées de selection jourantes : {i,j}
    private int[] ijDeplacementCourant = new int[2];       //Tableau pour stocker les coordonnées de la case de destination courante : {i,j}
    private int joueurCourant;                             //Indice du joueur courant dans le tableau des joueurs

    public Ataxx(){
        this.p = new Plateau();
        this.joueurs.add(new Joueur("j1", Couleur.ROUGE_BCK));
        this.joueurs.add(new Joueur("j2", Couleur.BLEU_BCK));
        this.joueurCourant = 0;
    }

    /**
     * Demande au joueur de saisir les coordonnées d'une case à selectionner tout en vérifiant si cette selection est correcte
     * @return void
     */
    public void demanderSelection(){
        int i, j;
        int s ;
        do{
            s = joueurs.get(joueurCourant).saisirSelection();
            j = s%10; 
            i = (s-j)/10;
        }while(!verifieSelection(i-1,j-1));

        //Mettre à jour le tableau des coordonnées de selection courantes 
        this.ijSelectionCourant[0] = i-1; 
        this.ijSelectionCourant[1] = j-1;
    }
    
    /**
     * Vérifie si les coordonnées selectionnées par le joueur respectent les conditions suivantes :
     *                                          Les coordonnées sont dans le plateau
     *                                          Le pion selectionné correspond au joueur courant
     *                                          Le pion selectionné n'est pas bloqué
     * @param i numéro de la ligne de la case selectionnée
     * @param j numéro de la colonne de la case selectionnée
     * @return True si la selection est correcte, False sinon
     */
    public boolean verifieSelection(int i, int j){
        String message = "Les coordonnées saisis dépassent les dimansions du plateau";

        if(p.verifieCordonnee(i, j)){
            message = "La case selectionnée n'est pas un pion correspondant au joueur";
           if( p.donnerCase(i,j).getCouleur() == donnerJoueurCourant().getCouleur()){
            message = "Le pion est bloqué et ne peut pas se deplacer";
                if(!p.verifieSiBloquee((Pion) p.donnerCase(i,j))){
                    return true;
                }
           }
        }
        System.out.println("*** Error *** : " + message );
        return false;
    }

    /**
     * Demande au joueur de saisir les coordonnées du déplacment tout en vérifiant si le déplacement peut s'effectuer
     * @return void
     */
    public void demanderDeplacement(){
        do{
            decoderDeplacement(joueurs.get(joueurCourant).saisirDeplacement());
        }while(!verifieDeplacement(ijDeplacementCourant[0],ijDeplacementCourant[1]));
    }

     /**
     * Vérifie si un déplacement paut s'effectuer s'il respecte les conditions suivantes : 
     *                                      la case de destination est dans le plateau
     *                                      la case de destination est dans un carré 5*5 du pion selectioné
     *                                      la case de destination est vide
     * @param i numéro de la ligne de la case de destination dans le plateau
     * @param j numéro de la colonne de la case de destination dans le plateau
     * @return True si le déplacement est possible, False sinon
     */
    public boolean verifieDeplacement(int i, int j){
        String message = "Les coordonnées saisis dépassent les dimansions du plateau";

        if(p.verifieCordonnee(i, j)){
            message = "Le deplacement depasse la limite possible ";
            if((i>=ijSelectionCourant[0]-2 
                && i<= ijSelectionCourant[0] + 2) 
                && (j>=ijSelectionCourant[1]-2 
                && j<= ijSelectionCourant[1] + 2)){
                message = "La case de destination est occupée";
                if(p.caseEstVide(i,j)){
                    return true;
                }
            }
        }
        System.out.println("*** Error *** : " + message );
        return false;
    }

     /**
     * Traduit le code du déplacement en coordonnées i et j de la case de déstination
     * @param d code du déplacement 
     * @return void
     */
    public void decoderDeplacement(int d){
       int deplacement =d%10; //Vriable pour stocker un seul déplacement, elle est initialisé avec le premier déplacement du code
       int dTmp = (int) d/10; // Vriable pour stocker le reste du code du déplacement à parcourir

       int[] selectionTmp = new int[2]; // Tableau pour stocker temporairement les coordonnées de selection courantes
       selectionTmp[0] = this.ijSelectionCourant[0]; 
       selectionTmp[1] = this.ijSelectionCourant[1]; 

       //Vraibles pour stocker temporairement les coordonnées de la case de destination
       int i = 0;  
       int j = 0;
       int n = 2; // indice du déplacement dans le code du déplacement, elle est initalisé avec la valeur maximum 2
       do{
           switch(deplacement){
               case 1:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] - 1;
                    deplacement = dTmp%10; // récuprer le prochain déplacement à effectuer 
                    break;
                case 2:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    break;
                case 3:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
                case 4:
                    i = selectionTmp[0];
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;
                    break;
                case 5:
                    if(n == 0){ //arreter le decodage s'il s'agit d'un 5 dans un code de 3 valeurs
                        break;
                    }
                    deplacement = d%10; //refaire le premier déplacement car il s'agit d'un 5 dans un code de 2 valeurs
                    break;
                case 6:
                    i = selectionTmp[0];
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
                case 7:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;
                    break;
                case 8:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    break;
                case 9:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
            }
            selectionTmp[0] =i; 
            selectionTmp[1] = j;
            dTmp = (int) dTmp/10;
            n--;
       }while(n>=0);

       //Mettre à jour les coordonnées de la case destination courante
       this.ijDeplacementCourant[0] = i;
       this.ijDeplacementCourant[1] = j;

    }

    /**
     * Change de joueur courant avec le flag "joueurCourant"
     * @return void
     */
    public void changerTour(){
        
        if(joueurCourant == 0){
            joueurCourant = 1;
        }
        else{
            joueurCourant = 0;
        }
    }

    /**
     * Retourne un élément de type Joueur qui correspond au joueur courant 
     * @return le joueur courant 
     */
    public Joueur donnerJoueurCourant(){
        return joueurs.get(joueurCourant);
    }

    /**
     * Retourne si le joueur peut jouer en verifiant si tous ces pions ne sont pas bloqués
     * @param joueur le joueur de référence
     * @return True si le joueur peut jouer, False sinon
     */
    public boolean verifieJoeurPeutJouer(Joueur joueur){
        int cptPions = 0; // Compteur de pions correspondant au joueur de référence 
        int cptPionsBLoqués = 0; // Compteur de pions bloqués correspondant au joueur de référence 

        for(int i = 0; i<p.dimension; i++){
            for(int j = 0; j<p.dimension; j++){
                if(p.donnerCase(i,j).getCouleur() == joueur.getCouleur()){
                    cptPions++;
                    if(p.verifieSiBloquee((Pion) p.donnerCase(i,j))){
                        cptPionsBLoqués++;
                    }
                }
            }
        }

        if(cptPions == cptPionsBLoqués){ //Tous les pions du joueur sont bloqués
            return false;
        }
        return true;
    }

    /**
     * Rtourne si le jeu est terminé en vérifiant les conditions suivantes : 
     *                              Toutes les cases du plateau son occupées
     *                              Le joueur courant n'a plus de pions sur le plateau
     * @return True si le jeu est terminé, False sinon
     */
    public boolean jeuEstTermine(){
        int cptCasesVides = 0; //Compteur des cases vides dans le plateau
        int cptPionsJoueurCourant = 0; //Compteur des pions du joueur courant dans le plateau
        for(int i = 0; i<p.dimension; i++){
            for(int j = 0; j<p.dimension; j++){
                //if(p.verifieCordonnee(i, j)){
                    if(p.caseEstVide(i, j)){
                        cptCasesVides++;
                    }
                //}
                if(p.donnerCase(i,j).getCouleur() == donnerJoueurCourant().getCouleur()){
                    cptPionsJoueurCourant++;
                }
            }
        }
        //Toutes les cases sont vides, ou aucun pion du joueur n'est présent sur le plateau
        if(cptCasesVides == 0 || cptPionsJoueurCourant == 0){ 
            return true;
        }
        return false;
    }
    /**
     * Contient la logique du jeu
     * @return le joueur courant 
     */
    public void gaimePlay(){

        System.out.println(Couleur.NORMALE_BCK + "\n********************************\n");
        p.affichePlateau();
        System.out.println("******** Tour du joueur : " + donnerJoueurCourant().toString());
        demanderSelection();
        p.selectionnerCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
        p.affichePlateau();
        demanderDeplacement();
        p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
        p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
        p.affichePlateau();
        changerTour();

        while(!jeuEstTermine()){
            System.out.println("\n********************************\n");
            if(verifieJoeurPeutJouer(donnerJoueurCourant())){
                System.out.println("******** Tour du joueur : " + donnerJoueurCourant().toString());
                demanderSelection();
                p.selectionnerCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
                p.affichePlateau();
                demanderDeplacement();
                p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
                p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
                p.affichePlateau();
            }
            else{
                System.out.println("******** Joueur doit passer son tour : " + donnerJoueurCourant().toString());
            }
            changerTour();
        }
        System.out.println("*********** Jeu Terminé !! ***********\n");

        int max  = donnerJoueurCourant().getScore(); // Variable pour stocker le score le plus grand entre les deux joueurs
        //Vérifier quel joueur a le score le plus grand
        if(joueurs.get(1-joueurCourant).getScore() > max){
            System.out.println("Le joueur  " + joueurs.get(1-joueurCourant).toString() + " a gagné");
        }
        else{
            System.out.println("Le joueur  " + donnerJoueurCourant().toString() + " a gagné");
        }

    }
    public static void main(String [] args){
        Ataxx a = new Ataxx();
        a.gaimePlay();
    }



}
