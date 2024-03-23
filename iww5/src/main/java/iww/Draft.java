package iww;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;


/**
 * The Draft class represents a game draft with a given number of rounds, players, and deck of cards.
 * It contains methods to change the decks of the players in a draft round and to launch the draft.
 */
public class Draft {

    private final static Logger LOGGER = Logger.getLogger("");

    private int nbTour;
    private Joueur[] joueurs;
    private ArrayList<Carte> pioche;

    /**
     * Constructs a Draft object with the given number of rounds, players, and deck
     * of cards.
     *
     * @param nbTour  the number of rounds in the draft
     * @param joueurs an array of players participating in the draft
     * @param pioche  the deck of cards used in the draft
     */
    public Draft(int nbTour, Joueur[] joueurs, ArrayList<Carte> pioche) {
        this.nbTour = nbTour;
        this.joueurs = joueurs;
        this.pioche = pioche;
    }

    /**
     * Changes the decks of the players in a draft round.
     * @param joueurs An array of players.
     * @param tour The current draft round.
     */
    public void changement(Joueur[] joueurs, int tour) {
        int nbJoueurs = joueurs.length;
        ArrayList<ArrayList<Carte>> pioches = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            pioches.add(joueur.getPioche());
            joueur.setPioche(new ArrayList<Carte>());
        }
        for (int i = 0; i < nbJoueurs; i++) {
            int index1 = i;
            int index2;
            if (tour % 2 == 0) {
                index2 = (i + 1) % nbJoueurs;
            } else {
                index2 = (i - 1 + nbJoueurs) % nbJoueurs;
            }
            joueurs[index1].setPioche(pioches.get(index2));
        }
    }

    /**
     * Chooses the most productive card for the given player based on their empire's productions.
     *
     * @param joueur The player for whom to choose the most productive card.
     * @return The most productive card.
     */
    public Carte choisirCarteLaPlusProductive(Joueur joueur) {
        LinkedHashMap<String, Integer> productions = joueur.getEmpire().getProductions();
        ArrayList<Carte> piocheJoueur = joueur.getPioche();
        Carte carteLaPlusProductive = null;
        int productionMax = 0;

        // Parcourez les cartes dans la pioche du joueur pour trouver la plus productive.
        for (Carte carte : piocheJoueur) {
            int productionCarte = 0;

            // Calculez la production totale de la carte en parcourant les ressources de production.
            for (Map.Entry<String, Integer> entry : productions.entrySet()) {
                String ressource = entry.getKey();
                int quantite = entry.getValue();
                productionCarte += productions.get(ressource) * quantite;
            }

            // Si la production totale est supérieure à la production maximale précédente, mettez à jour la carte choisie.
            if (productionCarte > productionMax) {
                productionMax = productionCarte;
                carteLaPlusProductive = carte;
            }
        }

        return carteLaPlusProductive;
    }
    /**
     * Launches the draft game mode.
     * Distributes cards to each player and reveals them one by one.
     * Then, each player passes their remaining cards to the next player and draws a new card.
     * This process is repeated for 6 rounds, after which each player reveals their final hand.
     */
    public void lancementDraft() {
        for (int k = 0; k < joueurs.length; k++) {
            System.out.println(joueurs[k].getColor());
            ArrayList<Carte> piocheJoueurJ = new ArrayList<Carte>();
            for (int l = 0; l < 7; l++) {
                piocheJoueurJ.add(pioche.remove(0));
            }
            joueurs[k].setPioche(piocheJoueurJ);
            System.out.println(joueurs[k].getNom() + " tient ce paquet : " + joueurs[k].getNomPioche());
            LOGGER.info(joueurs[k].getNom() + " tient ce paquet : " + joueurs[k].getNomPioche());
            Random random = new Random();
            int l = random.nextInt(piocheJoueurJ.size());

            if (joueurs[k].getNiveau() == 1) {
                // Niveau 1 (Random): Le bot choisit une carte au hasard.
                joueurs[k].ajoutCarteMain(piocheJoueurJ.remove(l));
                System.out.println(joueurs[k].getNom() + " a pioché une carte au hasard.");
                LOGGER.info(joueurs[k].getNom() + " a pioché une carte au hasard.");
            } else if (joueurs[k].getNiveau() == 2) {
                // Niveau 2 (Expert): Le bot choisit la carte la plus productive.
                Carte carteLaPlusProductive = choisirCarteLaPlusProductive(joueurs[k]);
                joueurs[k].getPioche().remove(carteLaPlusProductive);
                joueurs[k].ajoutCarteMain(carteLaPlusProductive);

                System.out.println(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                LOGGER.info(joueurs[k].getNom() + " a choisi la carte la plus productive.");
            } else if (joueurs[k].getNiveau() == 3) {
                // Niveau 3 (nul) le bot choisis la premier carte
                joueurs[k].ajoutCarteMain(piocheJoueurJ.remove(0));
                System.out.println(joueurs[k].getNom() + " a choisi la carte la premiere carte.");
                LOGGER.info(joueurs[k].getNom() + " a choisi la carte la premiere carte");
            }
        }

        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getColor());
            System.out.println(joueur.getNom() + " revele sa carte : "
                    + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
            LOGGER.info(joueur.getNom() + " revele sa carte : "
                    + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
        }
        System.out.println("\n");

        for (int j = 1; j < 7; j++) {
            changement(joueurs, nbTour);
            for (int k = 0; k < joueurs.length; k++) {
                System.out.println(joueurs[k].getColor());
                Random random = new Random();
                int l;
                if (joueurs[k].getPioche().size() > 1) {
                    l = random.nextInt(joueurs[k].getPioche().size() - 1);
                } else
                    l = 0;
                if (joueurs[k].getNiveau() == 1) {
                // Niveau 1 (Random): Le bot choisit une carte au hasard.
                joueurs[k].ajoutCarteMain(joueurs[k].getPioche().remove(l));
                System.out.println(joueurs[k].getNom() + " a pioché une carte au hasard.");
                LOGGER.info(joueurs[k].getNom() + " a pioché une carte au hasard.");
                } else if (joueurs[k].getNiveau() == 2) {
                    // Niveau 2 (Expert): Le bot choisit la carte la plus productive.
                    Carte carteLaPlusProductive = choisirCarteLaPlusProductive(joueurs[k]);
                    joueurs[k].getPioche().remove(carteLaPlusProductive);
                    joueurs[k].ajoutCarteMain(carteLaPlusProductive);

                    System.out.println(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                    LOGGER.info(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                } else if (joueurs[k].getNiveau() == 3) {
                    joueurs[k].ajoutCarteMain(joueurs[k].getPioche().remove(0));
                    System.out.println(joueurs[k].getNom() + " a choisi la premiere carte.");
                    LOGGER.info(joueurs[k].getNom() + " a choisi la premiere carte.");
                }
            }
            for (Joueur joueur : joueurs) {
                System.out.println(joueur.getColor());
                System.out.println(joueur.getNom() + " revele sa carte : "
                        + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
                LOGGER.info(joueur.getNom() + " revele sa carte : "
                        + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
                if(joueur.getNomPioche().size() > 0) {
                    System.out.println(joueur.getNom() + " passe ce paquet au joueur suivant : " + joueur.getNomPioche());
                    LOGGER.info(joueur.getNom() + " passe ce paquet au joueur suivant : " + joueur.getNomPioche());
                }
            }
            System.out.println("\n");
        }

        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getColor());
            System.out.println(joueur.getNom() + " revele sa main finale : " + joueur.getNomCartes());
            LOGGER.info(joueur.getNom() + " revele sa main finale : " + joueur.getNomCartes());
        }
    }

    /**
     * Initiates a 2-player draft.
     */
    public void lancementDraft2J() {
        for (int k = 0; k < joueurs.length; k++) {
            System.out.println(joueurs[k].getColor());
            ArrayList<Carte> piocheJoueurJ = new ArrayList<Carte>();
            for (int l = 0; l < 10; l++) {
                piocheJoueurJ.add(pioche.remove(0));
            }
            joueurs[k].setPioche(piocheJoueurJ);
            System.out.println(joueurs[k].getNom() + " tient ce paquet : " + joueurs[k].getNomPioche());
            LOGGER.info(joueurs[k].getNom() + " tient ce paquet : " + joueurs[k].getNomPioche());
            Random random = new Random();
            int l = random.nextInt(piocheJoueurJ.size());

            if (joueurs[k].getNiveau() == 1) {
                // Niveau 1 (Random): Le bot choisit une carte au hasard.
                joueurs[k].ajoutCarteMain(piocheJoueurJ.remove(l));
                System.out.println(joueurs[k].getNom() + " a pioché une carte au hasard.");
                LOGGER.info(joueurs[k].getNom() + " a pioché une carte au hasard.");
            } else if (joueurs[k].getNiveau() == 2) {
                // Niveau 2 (Expert): Le bot choisit la carte la plus productive.
                Carte carteLaPlusProductive = choisirCarteLaPlusProductive(joueurs[k]);
                joueurs[k].getPioche().remove(carteLaPlusProductive);
                joueurs[k].ajoutCarteMain(carteLaPlusProductive);

                System.out.println(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                LOGGER.info(joueurs[k].getNom() + " a choisi la carte la plus productive.");
            } else if (joueurs[k].getNiveau() == 3) {
                // Niveau 3 (nul) le bot choisis la premier carte
                joueurs[k].ajoutCarteMain(piocheJoueurJ.remove(0));
                System.out.println(joueurs[k].getNom() + " a choisi la premiere carte.");
                LOGGER.info(joueurs[k].getNom() + " a choisi la premiere carte.");
            }
        }

        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getColor());
            System.out.println(joueur.getNom() + " revele sa carte : "
                    + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
            LOGGER.info(joueur.getNom() + " revele sa carte : "
                    + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
        }
        System.out.println("\n");

        for (int j = 1; j < 7; j++) {
            changement(joueurs, nbTour);
            for (int k = 0; k < joueurs.length; k++) {
                System.out.println(joueurs[k].getColor());
                Random random = new Random();
                int l;
                if (joueurs[k].getPioche().size() > 1) {
                    l = random.nextInt(joueurs[k].getPioche().size() - 1);
                } else
                    l = 0;
                if (joueurs[k].getNiveau() == 1) {
                    // Niveau 1 (Random): Le bot choisit une carte au hasard.
                    joueurs[k].ajoutCarteMain(joueurs[k].getPioche().remove(l));
                    System.out.println(joueurs[k].getNom() + " a pioché une carte au hasard.");
                    LOGGER.info(joueurs[k].getNom() + " a pioché une carte au hasard.");
                } else if (joueurs[k].getNiveau() == 2) {
                    // Niveau 2 (Expert): Le bot choisit la carte la plus productive.
                    Carte carteLaPlusProductive = choisirCarteLaPlusProductive(joueurs[k]);
                    joueurs[k].getPioche().remove(carteLaPlusProductive);
                    joueurs[k].ajoutCarteMain(carteLaPlusProductive);

                    System.out.println(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                    LOGGER.info(joueurs[k].getNom() + " a choisi la carte la plus productive.");
                } else if (joueurs[k].getNiveau() == 3) {
                    joueurs[k].ajoutCarteMain(joueurs[k].getPioche().remove(0));
                    System.out.println(joueurs[k].getNom() + " a choisi la premiere carte..");
                    LOGGER.info(joueurs[k].getNom() + " a choisi la premiere carte.");
                }
            }
            for (Joueur joueur : joueurs) {
                System.out.println(joueur.getColor());
                System.out.println(joueur.getNom() + " revele sa carte : "
                        + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
                LOGGER.info(joueur.getNom() + " revele sa carte : "
                        + joueur.getMainJ().get(joueur.getMainJ().size() - 1).getNom());
                if(joueur.getNomPioche().size() > 0) {
                    System.out.println(joueur.getNom() + " passe ce paquet au joueur suivant : " + joueur.getNomPioche());
                    LOGGER.info(joueur.getNom() + " passe ce paquet au joueur suivant : " + joueur.getNomPioche());
                }
            }
            System.out.println("\n");
        }

        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getColor());
            System.out.println(joueur.getNom() + " revele sa main finale : " + joueur.getNomCartes());
            LOGGER.info(joueur.getNom() + " revele sa main finale : " + joueur.getNomCartes());
        }
    }
}
