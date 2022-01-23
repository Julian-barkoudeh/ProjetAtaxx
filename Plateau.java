import java.util.ArrayList;

public class Plateau {
    ArrayList<ArrayList<Case>> board = new ArrayList<>();
    int dimension = 7;
    
    public Plateau(){
        initPlateau();
        initElements();
    }
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
    public void initElements(){
        board.get(0).set(0,new Pion(Couleur.ROUGE_BCK, 0, 0));
        board.get(0).set(dimension-1,new Pion(Couleur.BLEU_BCK, 0, dimension-1));
        board.get(dimension-1).set(dimension-1,new Pion(Couleur.ROUGE_BCK, dimension-1, dimension-1));
        board.get(dimension-1).set(0,new Pion(Couleur.BLEU_BCK, dimension-1, 0));

        board.get(2).set(3, new Obstacle(2, 3));
        board.get(3).set(2, new Obstacle(3, 2));
        board.get(3).set(4, new Obstacle(3, 4));
        board.get(4).set(3, new Obstacle(4, 3));

    }

    public Case donnerCase(int ord, int abs){
        return board.get(ord).get(abs);
    }
    public void selectionnerCase(int ord, int abs){
        board.get(ord).get(abs).setCouleurSelection(Couleur.JAUNE_FOR);
        for(int i = ord-2; i<=ord+2; i++){
            for(int j = abs-2; j<=abs+2; j++){
                if(verifieCordonnee(i,j)){
                    if(!caseEstObstacle(j, i)) {
                        if(!(i == ord && j == abs)){
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
    public boolean verifieCordonnee(int i, int j){
        if((i>=0 && i<dimension) && (j>=0 && j<dimension)){
            return true;
        }
        //System.out.println("*** Error ***  : La case selectionnee ne respecte pas les dimensions");
        return false;
    }

    public boolean verifieSiBloquee(Pion p){
        int cptCases = 0;
        int cptCasesVides = 0;
        for(int i = p.getOrd()-2; i<=p.getOrd()+2; i++){
            for(int j = p.getAbs()-2; j<=p.getAbs()+2; j++){
                if(verifieCordonnee(i,j)){
                    cptCases++;
                    if(!caseEstVide(i,j)){
                        cptCasesVides++;
                    }
                }
            }
        }
        if(cptCasesVides == cptCases){
            //System.out.println("***** Le pion est bloqué et ne peut pas se déplacer *****");
            return true;
        }
        return false;
    }

    public boolean caseEstObstacle(int ord, int abs){
        if(donnerCase(ord, abs).getClass().getName() == "Obstacle"){
            return true;
        }
        return false;
    }
    public boolean caseEstVide(int ord, int abs){
        if(donnerCase(ord, abs).getClass().getName() == "CaseVide"){
            return true;
        }
        return false;
    }
    public void selecteCasesProximite(Case caseSelectione,Case caseProximite){
       
    }
    public boolean caseEstProche(Case caseSelectione,Case caseProximite){ 
        if((caseProximite.getAbs() >= caseSelectione.getAbs() -1) && (caseProximite.getAbs() <= caseSelectione.getAbs() +1)){
            if((caseProximite.getOrd() >= caseSelectione.getOrd() -1 ) && (caseProximite.getOrd() <= caseSelectione.getOrd() +1)){
                return true;
            }
        }
        return false;
    }
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
    
    public void deplacer(Pion p, int ord, int abs){
        enleverSelection(p.getOrd(),  p.getAbs());
        board.get(ord).set(abs,new Pion(p.getCouleur(), ord, abs));
        if(caseEstDistante(p, donnerCase(ord, abs))){
            board.get(p.getOrd()).set(p.getAbs(), new CaseVide(p.getOrd(), p.getAbs()));
        }
    }
    public void infecter(Pion p, Joueur joueur){
        for(int i = p.getOrd()-1; i<=p.getOrd()+1; i++){
            for(int j = p.getAbs()-1; j<=p.getAbs()+1; j++){
                if(verifieCordonnee(i,j)){
                    if((donnerCase(i,j).getClass().getName() == "Pion") && (donnerCase(i,j).getCouleur() != p.getCouleur())){
                       donnerCase(i, j).setCouleur(p.getCouleur());
                       joueur.incrementerScore();
                    }
                }
            }
        }
    }
    public void enleverSelection(int ord, int abs){
        for(int i = ord-2; i<=ord+2; i++){
            for(int j = abs-2; j<=abs+2; j++){
                if(verifieCordonnee(i,j)){
                    donnerCase(i, j).setCouleurSelection(Couleur.GRIS_FOR);
                }
            }
        }
    }

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
