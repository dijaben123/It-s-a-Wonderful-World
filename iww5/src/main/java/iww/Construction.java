package iww;

import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents a construction in the game. It contains methods for verifying if a card can be constructed by a player based on the resources they have, displaying the choices available for a construction based on the required resources, allowing a player to choose between storing a resource or distributing it to a card under construction, and distributing Krystallium to a player.
 */
public class Construction {
    private static Random random = new Random();
    private final static Logger LOGGER = Logger.getLogger("");

    /**
     * Constructs a new instance of the Construction class.
     * This class appears to be related to the construction phase in the game.
     * The constructor, as presented, has no explicit initialization or actions.
     */
    public Construction() {}

    /**
     * Verifies if a card can be constructed by a player based on the resources they
     * have.
     *
     * @param carte  The card to be constructed.
     * @param joueur The player attempting to construct the card.
     * @return true if the card can be constructed, false otherwise.
     */
    private static boolean verifConstructible(Carte carte, Joueur joueur) {
        Set<String> clesCoutProd = carte.getCoutProd().keySet();
        int cpt = 0;
        for (String cleCoutProd : clesCoutProd) {
            if (carte.getCoutProd().get(cleCoutProd) <= 0) {
                cpt++;
            }
        }
        if (cpt == clesCoutProd.size()) {
            joueur.ajoutCarteConstruite(carte);
            return true;
        }
        return false;
    }

    /**
     * Displays the choices available for a construction based on the required
     * resources.
     *
     * @param carteNecessiteRessources an array of required resources for the
     *                                 construction
     * @return the number of choices available
     */
    private static int affichageChoix(Carte[] carteNecessiteRessources) {
        int choix = 1;
        for (int i = 0; i < carteNecessiteRessources.length; i++) {
            if (carteNecessiteRessources[i] != null) {
                System.out.print("      " + "[" + choix + "] " + carteNecessiteRessources[i].getNom() + " ");
                LOGGER.info("      " + "[" + choix + "] " + carteNecessiteRessources[i].getNom() + " ");
                choix++;
            }
        }
        System.out.print("      " + "[" + choix + "] aucune\n");
        LOGGER.info("      " + "[" + choix + "] aucune");
        return choix;
    }

    /**
     * This method allows a player to choose between storing a resource or
     * distributing it to a card under construction.
     * If the player chooses to distribute the resource, the method checks if the
     * card can be constructed with the current resources.
     * If the card can be constructed, it is removed from the player's list of cards
     * under construction.
     *
     * @param cartes                 The cards.
     * @return return victory points.
     */
    public static int ptvictoire(Carte[] cartes) {
        if (cartes == null || cartes.length == 0) {
            // Gérer le cas où le tableau de cartes est vide
            return 1;
        }

        String cle;
        int valeur = 0;
        int choix = 0;
        int pointsVictoireMax = 0;

        for (int i = 0; i < cartes.length-1; i++) {
            if (cartes[i] == null) {
                // Ignorer les éléments null dans le tableau
                continue;
            }

            int pointsVictoireCourants = 0;

            // Parcourez chaque entrée de la Map pVictoire de la carte actuelle
            for (Map.Entry<String, Integer> entry : cartes[i].getpVictoire().entrySet()) {
                cle = entry.getKey();
                valeur = entry.getValue();
            }

            pointsVictoireCourants = valeur;

            if (pointsVictoireCourants > pointsVictoireMax) {
                choix = i;
                pointsVictoireMax = pointsVictoireCourants;
            }
        }

        return choix + 1;
    }



    /**
     * Handles the player's choice for resource distribution based on the given parameters.
     *
     * @param joueur                    The player making the choice.
     * @param carteNecessiteRessources  The array of cards requiring resources.
     * @param cle                       The key representing the type of resource.
     * @param valeur                    The value indicating the quantity of the resource required.
    */
    private static void choix(Joueur joueur, Carte[] carteNecessiteRessources, String cle, int valeur) {
        int choix = affichageChoix(carteNecessiteRessources);
        int rand ;
        if (joueur.getNiveau()==1){
            rand= random.nextInt(1, choix + 1);
        }
        else if (joueur.getNiveau()==3){
            rand= 1;
        }
        else{
            rand= ptvictoire(carteNecessiteRessources);
        }

        if (rand == choix) {
            // Le joueur choisit de stocker la ressource.

            System.out.println("        Ressource stockée");
            LOGGER.info("        Ressource stockée");
            System.out.println("");
            joueur.stockRessource();
            joueur.getRessources().put(cle, valeur - 1);
        } else {
            System.out.println(
                    "        ressource distribuee à [" + rand + "] " + carteNecessiteRessources[rand - 1].getNom());
            LOGGER.info(
                    "        ressource distribuee à [" + rand + "] " + carteNecessiteRessources[rand - 1].getNom());

            System.out.println("");
            Carte carteConstruite = new Carte("nom", "type", -1, null, null, null, null, null);
            for (Carte carte : joueur.getEnConstruction()) {
                if (carte.getNom().equals(carteNecessiteRessources[rand - 1].getNom())
                        && carte.getIndex() == carteNecessiteRessources[rand - 1].getIndex()) {
                    carte.getCoutProd().put(cle, carte.getCoutProd().get(cle) - 1);
                    joueur.getRessources().put(cle, valeur - 1);
                    if (verifConstructible(carte, joueur)) {
                        carteConstruite = carte;
                    }
                }
            }
            if (carteConstruite.getNom().equals(carteNecessiteRessources[rand - 1].getNom())) {
                joueur.enleverCarteEnConstruction(carteConstruite);
            }
        }
    }

    /**
     * This method is used to distribute Krystallium to a player. It randomly chooses whether to use Krystallium or not.
     * If Krystallium is used, it selects a card from the player's construction list and randomly chooses a resource to use Krystallium on.
     * It then updates the card's production cost and checks if the card is still constructible. If it is, the card is removed from the player's construction list.
     * Finally, the player's Krystallium count is reduced by 1.
     * @param joueur The player to distribute Krystallium to.
     * @return true if Krystallium was used, false otherwise.
     */
    public static boolean distributionKrystallium(Joueur joueur) {
        System.out.println("Krystallium : " + joueur.getKrystallium());
        LOGGER.info("Krystallium : " + joueur.getKrystallium());
        System.out.println("Utilise Krystallium ? [0]oui [1]non");
        LOGGER.info("Utilise Krystallium ? [0]oui [1]non");
        int rand = random.nextInt(0, 2);
        System.out.println(rand);
        LOGGER.info(rand+"");
        if (rand == 0) {
            Carte[] carteEnConstruction = new Carte[joueur.getEnConstruction().size()];
            if (carteEnConstruction.length > 0) {
                int choix = affichageChoix(joueur.getEnConstruction().toArray(carteEnConstruction));
                int carteRand = random.nextInt(1, choix);
                System.out.println("");
                System.out.println("        Carte choisie " + carteEnConstruction[carteRand - 1].getNom());
                LOGGER.info("        Carte choisie " + carteEnConstruction[carteRand - 1].getNom());
                System.out.println("");
                Carte carteChoisie = carteEnConstruction[carteRand - 1];
                String[] ressources = new String[carteChoisie.getCoutProd().size()];
                int index = 1;
                for (Map.Entry<String, Integer> entry : carteChoisie.getCoutProd().entrySet()) {
                    String cle = entry.getKey();
                    int valeur = entry.getValue();
                    if (valeur > 0) {
                        System.out.println("    [" + index + "] Ressource : " + cle + ", Valeur : " + valeur);
                        LOGGER.info("    [" + index + "] Ressource : " + cle + ", Valeur : " + valeur);
                        ressources[index - 1] = cle;
                        index++;
                    }
                }
                System.out.println("Utilise le krystallium pour quelle ressource ?");
                LOGGER.info("Utilise le krystallium pour quelle ressource ?");
                System.out.println("");
                System.out.println("bot "+joueur.getNiveau());
                System.out.println("taiile ressouce "+ressources.length);
                System.out.println(ressources[0]);
                int ressourceRand = 0;
                if(ressources.length>1) random.nextInt(0, ressources.length);

                System.out.println(ressourceRand);
                LOGGER.info(ressourceRand+"");
                System.out.println("ressource choisie : " + ressources[ressourceRand]);
                LOGGER.info("ressource choisie : " + ressources[ressourceRand]);
                carteChoisie.getCoutProd().put(ressources[ressourceRand],
                        carteChoisie.getCoutProd().get(ressources[ressourceRand]) - 1);
                if (verifConstructible(carteChoisie, joueur))
                    joueur.enleverCarteEnConstruction(carteChoisie);
                joueur.enleveKrystallium();
                System.out.println("Krystallium : " + joueur.getKrystallium());
                LOGGER.info("Krystallium : " + joueur.getKrystallium());
            }
            return true;
        }
        return false;
    }

    /**
     * Distributes a resource to a card being constructed by a player.
     *
     * @param cartes The list of cards being constructed.
     * @param cle    The key of the resource being distributed.
     * @param valeur The value of the resource being distributed.
     * @param joueur The player who is constructing the card.
     */
    public static void distribution(ArrayList<Carte> cartes, String cle, int valeur, Joueur joueur) {
        Carte[] carteNecessiteRessources = new Carte[cartes.size()];
        int index = 0;
        // Vérifie quelles cartes en construction nécessitent la ressource.
        for (Carte c : cartes) {
            if (c.getCoutProd().containsKey(cle)) {
                if (c.getCoutProd().get(cle) > 0) {
                    carteNecessiteRessources[index] = c;
                    index++;
                }
            }
        }
        if (carteNecessiteRessources.length > 0) {
            if (carteNecessiteRessources[0] != null){
                System.out.println("          Cartes nécessitant la ressource:");
                LOGGER.info("          Cartes nécessitant la ressource:");
            }
            System.out.println("        À quelle carte voulez-vous distribuer la ressource (-1 sur la ressource):");
            LOGGER.info("        À quelle carte voulez-vous distribuer la ressource (-1 sur la ressource):");
            choix(joueur, carteNecessiteRessources, cle, valeur); // Permet au joueur de choisir la carte.
        }
            // return carteNecessiteRessources;
    }
}
