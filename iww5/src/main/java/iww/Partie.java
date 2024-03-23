package iww;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The Partie class represents a game session. It contains the players, the deck of cards, and the methods to launch the game.
 */
public class Partie {

	private final static Logger LOGGER = Logger.getLogger("");

    private Joueur[] joueurs;
    private ArrayList<Carte> pioche;
    private ArrayList<Empire> piocheEmpire;


    /**
     * Constructor for the Partie class.
     *
     * @param joueurs an array of Joueur objects representing the players in the
     *                game
     */
    public Partie(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Returns the array of players in the game.
     *
     * @return The array of players.
     */
    public Joueur[] getJoueurs() {
        return this.joueurs;
    }

    /**
     * This method launches the game by generating a deck of cards, starting the
     * drafting phase, planning phase, and production phase for each of the four
     * turns, and printing the result at the end.
     */
    public void lancerPartie() {
        LOGGER.setLevel(Level.INFO);

        this.pioche = GenerateurCarte.genPioche();
        this.piocheEmpire = GenerateurCarte.genCartesEmpire();
        ArrayList<List<Carte>> paquets =  new ArrayList<List<Carte>>();
        if (joueurs.length == 1){
            paquets = GenerateurCarte.creationPaquets(pioche);
        }
        for (Joueur joueur : joueurs) {
            joueur.getEnConstruction().clear();
            joueur.getCartesConstruites().clear();
            joueur.resetInventaire();
            joueur.setEmpire(this.piocheEmpire.remove(0));
            System.out.println(joueur.getNom() + " : " + joueur.getEmpire().getNom());
        }
        LOGGER.info("Debut du jeu\n");
        System.out.println("\u001B[37m"+"Debut du jeu");

        System.out.println( "\u001B[37m"+"------------------------------" );
        for (int i = 1 ; i < 5 ; i++) {
            System.out.println( "\u001B[37m"+"Debut tour "+i );
            LOGGER.info("Debut tour "+i+"\n");

            System.out.println( "\u001B[37m"+"-----------------------------------------------------"+"phase de draft "+i+"-----------------------------------------------------");
            LOGGER.info("-----------------------------------------------------"+"phase de draft "+i+"-----------------------------------------------------\n");

            System.out.println(joueurs.length + " joueur(s)");
            Draft draft = new Draft(i,joueurs,pioche);
            if (joueurs.length == 2){
                draft.lancementDraft2J();
            } else if (joueurs.length == 1) {
                System.out.println("pas de draft");
            } else {
                draft.lancementDraft();
            }
            System.out.println("\u001B[37m"+"fin phase de draft " + i);
            LOGGER.info("fin phase de draft "+i+"\n");
            System.out.println("\u001B[37m"+"-----------------------------------------------------" + "phase de planification " + i
                    + "-----------------------------------------------------");
            LOGGER.info("-----------------------------------------------------"+"phase de planification "+i+"-----------------------------------------------------\n");

            Planification planif = new Planification(joueurs);
            if (joueurs.length==1) {
                planif.startPlanificationUnJoueur(paquets, pioche,i);
            } else {
                planif.startPlanification(i);
            }
            System.out.println("\u001B[37m"+"fin phase de planification " + i);
            LOGGER.info("fin phase de planification "+i+"\n");

            System.out.println("\u001B[37m"+"-----------------------------------------------------" + "phase de production " + i
                    + "------------------------------------------------------");
            LOGGER.info("-----------------------------------------------------"+"phase de production "+i+"-----------------------------------------------------\n");

            Production production = new Production(joueurs);
            production.startProduction(i);
            System.out.println("\u001B[37m"+"fin phase de production " + i);
            LOGGER.info("fin phase de production "+i+"\n");

        }
        System.out.println("\u001B[37m"+"fin du jeu");
        LOGGER.info("fin du jeu\n");

        System.out.println("\u001B[37m"+"Resultat");
    }

    /**
     * Calculates and prints the scores and statistics for each player in the game.
     */
    public void resultat() {

        for (Joueur joueur : joueurs) {
            int cptCarteStructure = 0;
            int cptCarteVehicule = 0;
            int cptCarteRecherche = 0;
            int cptCarteProjet = 0;
            int cptCarteDecouverte = 0;

            int cptMultiplicateurStructure = 0;
            int cptMultiplicateurVehicule = 0;
            int cptMultiplicateurRecherche = 0;
            int cptMultiplicateurProjet = 0;
            int cptMultiplicateurDecouverte = 0;
            int cptMultiplicateurFinancier = 0;
            int cptMultiplicateurGeneral = 0;

            int score = 0;

            ArrayList<Carte> joueurCartesConstruites = joueur.getCartesConstruites();
            for (Carte CarteConstruites : joueurCartesConstruites) {
                switch (CarteConstruites.getType()) {
                    case "Structure":
                        cptCarteStructure++;
                        break;
                    case "Vehicule":
                        cptCarteVehicule++;
                        break;
                    case "Recherche":
                        cptCarteRecherche++;
                            break;
                    case "Projet":
                        cptCarteProjet++;
                            break;
                    case "Decouverte":
                        cptCarteDecouverte++;
                            break;
                    default:
                        break;
                }
                Map<String, Integer> pVictoireCarteConstruites = CarteConstruites.getpVictoire();
                if (!pVictoireCarteConstruites.isEmpty()) {
                    for (Map.Entry<String, Integer> pVictoire : pVictoireCarteConstruites.entrySet()){
                        switch (pVictoire.getKey()) {
                            case "Structure":
                                cptMultiplicateurStructure = cptMultiplicateurStructure + pVictoire.getValue();
                                break;
                            case "Vehicule":
                                cptMultiplicateurVehicule = cptMultiplicateurVehicule + pVictoire.getValue();
                                break;
                            case "Recherche":
                                cptMultiplicateurRecherche = cptMultiplicateurRecherche + pVictoire.getValue();
                                break;
                            case "Projet":
                                cptMultiplicateurProjet = cptMultiplicateurProjet + pVictoire.getValue();
                                break;
                            case "Decouverte":
                                cptMultiplicateurDecouverte = cptMultiplicateurDecouverte + pVictoire.getValue();
                                break;
                            case "financier":
                                cptMultiplicateurFinancier = cptMultiplicateurFinancier + pVictoire.getValue();
                                break;
                            case "general":
                                cptMultiplicateurGeneral = cptMultiplicateurGeneral + pVictoire.getValue();
                                break;
                            case ".":
                                score = score + pVictoire.getValue();
                            default:
                                break;
                        }
                    }
                }
            }
            int ptsBatiments = score + (cptCarteStructure * cptMultiplicateurStructure) +
                    (cptCarteVehicule * cptMultiplicateurVehicule) +
                    (cptCarteRecherche * cptMultiplicateurRecherche) +
                    (cptCarteProjet * cptMultiplicateurProjet) +
                    (cptCarteDecouverte * cptMultiplicateurDecouverte) +
                    (cptMultiplicateurFinancier * joueur.getFinancier() ) +
                    (cptMultiplicateurGeneral * joueur.getGeneral());

            for (Map.Entry<String, Integer> pVictoireEmpire : joueur.getEmpire().getPtsVictoire().entrySet()) {
                switch (pVictoireEmpire.getKey()) {
                    case "Recherche":
                        cptMultiplicateurRecherche = cptMultiplicateurRecherche + pVictoireEmpire.getValue();
                        break;
                    case "Projet":
                        cptMultiplicateurProjet = cptMultiplicateurProjet + pVictoireEmpire.getValue();
                        break;
                    case "Decouverte":
                        cptMultiplicateurDecouverte = cptMultiplicateurDecouverte + pVictoireEmpire.getValue();
                        break;
                    case "financier":
                        cptMultiplicateurFinancier = cptMultiplicateurFinancier + pVictoireEmpire.getValue();
                        break;
                    case "general":
                        cptMultiplicateurGeneral = cptMultiplicateurGeneral + pVictoireEmpire.getValue();
                        break;
                    default:
                        break;
                }
            }
            score = score + (cptCarteStructure * cptMultiplicateurStructure) +
                    (cptCarteVehicule * cptMultiplicateurVehicule) +
                    (cptCarteRecherche * cptMultiplicateurRecherche) +
                    (cptCarteProjet * cptMultiplicateurProjet) +
                    (cptCarteDecouverte * cptMultiplicateurDecouverte) +
                    (cptMultiplicateurFinancier * joueur.getFinancier() ) +
                    (cptMultiplicateurGeneral * joueur.getGeneral());
            System.out.println(ptsBatiments);
            System.out.println(score);
            System.out.println(joueur.getEmpire().getNom());
            int ptsEmpire = score - ptsBatiments;
            joueur.getStatistiques().addPtsBatiments(ptsBatiments);
            joueur.getStatistiques().addPtsJetons(joueur.getFinancier()+joueur.getGeneral());
            joueur.getStatistiques().addPtsEmpire(ptsEmpire);
            joueur.getStatistiques().genereStatsBatiments(joueur.getCartesConstruites());

            score = score + (1 * joueur.getFinancier() ) + (1 * joueur.getGeneral());
            joueur.getStatistiques().addResultatPartie(score);
            joueur.setScore(score);

            System.out.println("Joueur : "+joueur.getNom()+" score : "+ joueur.getScore());

        }

        int maxScore = 0;
        for(Joueur joueur : joueurs){
            if(joueur.getScore()>maxScore){maxScore = joueur.getScore();}
        }
        for(Joueur joueur : joueurs){
            if(joueur.getScore()==maxScore){joueur.getStatistiques().addNbrPartiesGagnees(1);}
        }
        System.out.println();

    }
}

