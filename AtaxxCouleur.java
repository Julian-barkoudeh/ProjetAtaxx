/*
  Voici une Ã©numaration permettant de colorer votre terminal de type GNU-Linux.
  Je rappelle que l'environnement de programmation pour l'Ã©valuation de votre
  projet sera de type GNU-Linux.

  Vous pourrez personnaliser cette Ã©numaration en lui ajoutant d'autres mÃ©thodes
  si besoin (ce qui Ã©tait le cas de mon implÃ©mentation personnelle).

  Enfin, comme le sujet vous le montre, le "gameplay" fait apparaÃ®tre un
  affichage sur-mesure pour aider les joueurs Ã  visualiser les choix possibles
  qui s'offrent Ã  eux. Pour faire cela facilement (i.e. lisiblement au niveau de
  votre code source), je vous suggÃ¨re de dÃ©finir une autre Ã©numeration
  permettant de dÃ©finir un statut/marque/Ã©tat pour vos diffÃ©rents Ã©lÃ©ments au
  cours du temps (le / indiquant une liste de choix de vocabulaire selon vos
  attentes/besoins).
*/
enum Couleur {
    // FOR : foreground (couleur des caractÃ¨res).
    // BCK : background (couleur du fond).
    BLEU_FOR, BLEU_BCK,
    ROUGE_FOR, ROUGE_BCK,
    VERT_FOR, VERT_BCK,
    JAUNE_FOR, JAUNE_BCK,
    MAGENTA_FOR, MAGENTA_BCK,
    GRIS_FOR, GRIS_BCK,
    NORMALE_FOR, NORMALE_BCK;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() {
	String affichage = "";
        switch (this) {
	case BLEU_FOR :
	    affichage = "\u001B[94m";
	    break;
	case BLEU_BCK :
	    affichage = "\u001B[104m";
	    break;
	case ROUGE_FOR :
	    affichage = "\u001B[91m";
	    break;
	case ROUGE_BCK :
	    affichage = "\u001B[101m";
	    break;
	case VERT_FOR :
	    affichage = "\u001B[32m";
	    break;
	case VERT_BCK :
	    affichage = "\u001B[42m";
	    break;
	case JAUNE_FOR :
	    affichage = "\u001B[93m";
	    break;
	case JAUNE_BCK :
	    affichage = "\u001B[103m";
	    break;
	case MAGENTA_FOR :
	    affichage = "\u001B[95m";
	    break;
	case MAGENTA_BCK :
	    affichage = "\u001B[105m";
	    break;
	case GRIS_FOR :
	    affichage = "\u001B[37m";
	    break;
	case GRIS_BCK :
	    affichage = "\u001B[47m";
	    break;
	case NORMALE_FOR :
	    affichage = "\u001B[37m";
	    break;
	case NORMALE_BCK :
	    affichage = "\u001B[40m";
	    break;
	}
	return affichage;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void main(String args[]) {
	// Exemple (pas hyper joli du reste).
	System.out.println(Couleur.BLEU_FOR + "La " +
			   Couleur.NORMALE_FOR + " " + 
			   Couleur.ROUGE_FOR + "couleur, " +
			   " " + 
			   Couleur.VERT_BCK + "c'est " +
			   Couleur.NORMALE_BCK +
			   Couleur.JAUNE_FOR + "bien" +
			   Couleur.GRIS_FOR + "..." +
			   Couleur.NORMALE_FOR + "pratique !");
    }
}