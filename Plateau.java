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
                board.get(i).add(j,new Case(" ", i, j));
            }
        }
    }
    public void initElements(){
        board.get(0).set(0,new Pion("R", 0, 0,0));
        board.get(0).set(dimension-1,new Pion("B", 0, dimension-1,1));
        board.get(dimension-1).set(dimension-1,new Pion("R", dimension-1, dimension-1,0));
        board.get(dimension-1).set(0,new Pion("B", dimension-1, 0,1));

        board.get(2).set(3, new Obstacle(2, 3));
        board.get(3).set(2, new Obstacle(3, 2));
        board.get(3).set(4, new Obstacle(3, 4));
        board.get(4).set(3, new Obstacle(4, 3));

    }

    public Case donnerCase(int ord, int abs){
        return board.get(ord).get(abs);
    }
    public void slecteCase(int ord, int abs){
        board.get(ord).get(abs).setCouleurSelection("Jaune");
        for(int i = ord-2; i<=ord+2; i++){
            for(int j = abs-2; j<=abs+2; j++){
                if((i >=0 && i<dimension) && (j>= 0 && j< dimension)){
                    if(!estObstacle(j, i) || (i != ord && j != abs)){
                        if(caseEstProche(donnerCase(ord, abs), donnerCase(i, j))){
                            donnerCase(i, j).setCouleurSelection("vert");
                        }
                        else if(caseEstDistante(donnerCase(ord, abs), donnerCase(i, j))){
                            donnerCase(i, j).setCouleurSelection("magenta");
                        }
                    }
                }
            }
        }
    }
    public boolean estObstacle(int ord, int abs){
        if(donnerCase(ord, abs).getClass().getName() == "Obstacle"){
            return true;
        }
        return false;
    }
    public void selecteCasesProximite(Case caseSelectione,Case caseProximite){
       
    }
    public boolean caseEstProche(Case caseSelectione,Case caseProximite){
        if(caseProximite.getAbs() <= caseSelectione.getAbs() -1 && caseProximite.getAbs() >= caseSelectione.getAbs() +1){
            if(caseProximite.getOrd() <= caseSelectione.getOrd() -1 && caseProximite.getOrd() >= caseSelectione.getOrd() +1){
                return false;
            }
        }
        return false;
    }
    public boolean caseEstDistante(Case caseSelectione,Case caseProximite){
        if(caseProximite.getAbs() <= caseSelectione.getAbs() -2 && caseProximite.getAbs() >= caseSelectione.getAbs() +2){
            if(caseProximite.getOrd() <= caseSelectione.getOrd() -2 && caseProximite.getOrd() >= caseSelectione.getOrd() +2){
                return false;
            }
        }
        return false; 
    }
    
    public void deplacer(Pion p, int ord, int abs){
        board.get(ord).set(abs,new Pion(p.getCouleur(), ord, abs, p.getIdJoueur()));
        if(caseEstDistante(p, donnerCase(ord, abs))){
            board.get(p.getOrd()).set(p.getAbs(), new Case(ord, abs));
        }
    }
    public void infecter(Pion p){
        for(int i = p.getOrd()-1; i<=p.getOrd()+1; i++){
            for(int j = p.getAbs()-1; j<=p.getAbs()+1; j++){
                if(((i >=0 && i<dimension) && (j>= 0 && j< dimension))||(i != p.getOrd() && j != p.getAbs())){
                    if(!estObstacle(j, i) && (donnerCase(i,j).getClass().getName() == "Pion")){
                       donnerCase(i, j).setCouleur(p.getCouleur());
                    }
                }
            }
        }
    }

    public void affichePlateau(){
        for(int i = 0; i<dimension; i++){
            for(int j = 0; j<dimension; j++){
                System.out.print("[ "  + donnerCase(i, j).getCouleur() + "]   ");
            }
            System.out.print("\n");
        } 
    }
    public  static void main (String [] args){
        Plateau p = new Plateau();
        p.affichePlateau();
        p.deplacer((Pion) p.donnerCase(0,0), 1, 2);
        p.affichePlateau();
    }
}
