package iww;

import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The Defausse class manages the discarding of cards under construction by players.
 */
public class Defausse {

    private final static Logger LOGGER = Logger.getLogger("");
    private static Random random = new Random();

    /**
     * Constructs a new instance of the Defausse class.
     * This class seems to be related to discarding cards in the game.
     * The constructor, as presented, has no explicit initialization or actions.
     */
    public Defausse() {}

    /**
     * Determines if the player needs to discard cards based on the total production requirements.
     * @param j The player.
     * @return 1 if discarding is needed, 0 otherwise.
     */
    private static int besoinRessourcesJusquAQuatre(Joueur j) {
        int total = 0;
        for (Carte carte : j.getEnConstruction()) {

            for (Map.Entry<String, Integer> entry : carte.getCoutProd().entrySet()) {
                total += entry.getValue();
            }
            if (total > 5) {
                return 1;
            } else {
                total = 0;
            }
        }

        return 0;
    }

        private static int besoinRessourcedef(Joueur j) {
        int total = 0;
        int i=j.getEnConstruction().size()+1;
        int x=0;
        for (Carte carte : j.getEnConstruction()) {
            x++;
            for (Map.Entry<String, Integer> entry : carte.getCoutProd().entrySet()) {
                total += entry.getValue();
            }
            if (total > 5) {
                return x;
            } else {
                total = 0;
            }
        }

        return i;
    }

    /**
     * Discards cards under construction based on player choices and production requirements.
     * @param joueur The player.
     * @param y The round number.
     */
    public static void defausseEnConstruction(Joueur joueur, int y) {
        System.out.println(joueur.getColor());
        System.out.println("        Voulez-vous defaussez des cartes en construction ? [1]oui [2]non");
        LOGGER.info("        Voulez-vous defaussez des cartes en construction ? [1]oui [2]non");
        int rand = 0;
        if (joueur.getNiveau() == 1 || joueur.getNiveau() == 3) {
            rand = random.nextInt(1, 3);
        }
        if (y == 4 && joueur.getNiveau() == 2) {
            rand = besoinRessourcesJusquAQuatre(joueur);
        }

        LOGGER.info("        " + rand + "\n");
        System.out.println(rand);
        int choixCarte = -1;
        if (rand == 1) {
            while (choixCarte <= joueur.getEnConstruction().size() && joueur.getEnConstruction().size() > 0) {
                int index = 1;
                for (int i = 0; i < joueur.getEnConstruction().size(); i++) {
                    System.out.println("[" + index + "] " + joueur.getEnConstruction().get(i).getNom());
                    LOGGER.info("        [" + index + "] " + joueur.getEnConstruction().get(i).getNom() + "\n");
                    index++;
                }
                System.out.println("        Quelle carte voulez vous defausser ? [>" + joueur.getEnConstruction().size()
                        + "] pour annuler");
                LOGGER.info("        Quelle carte voulez vous defausser ? [>" + joueur.getEnConstruction().size()
                        + "] pour annuler");
                if (joueur.getNiveau() == 1) {
                    choixCarte = random.nextInt(1, joueur.getEnConstruction().size() * 2);
                }
                else{
                    choixCarte=besoinRessourcedef(joueur);
                }

                LOGGER.info("        " + choixCarte + "\n");
                System.out.println(choixCarte);
                if (choixCarte - 1 <= joueur.getEnConstruction().size() - 1) {
                    joueur.RecyclCarte(joueur.getEnConstruction().remove(choixCarte - 1), false);
                }

            }
        }

    }
}
