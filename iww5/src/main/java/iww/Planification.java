package iww;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The Planification class represents the planning phase of the game for each
 * player.
 * It prompts each player to choose an action for each card in their hand,
 * either construction or recycling. It then displays the cards under
 * construction for each player
 * and distributes resources to each player.
 */
public class Planification {

    private final static Logger LOGGER = Logger.getLogger("");

    private Joueur[] joueurs;
    private Random random;

    /**
     * Constructs a new Planification object with the given array of Joueur objects.
     *
     * @param joueurs the array of Joueur objects to be used in the planification
     */
    public Planification(Joueur[] joueurs) {
        this.joueurs = joueurs;
        this.random = new Random();
    }

    /**
     * Initiates the planning phase for all players in the game, allowing each player to make construction
     * or recycling decisions based on their current hand of cards.
     *
     * @param y The current game turn.
     */
    public void startPlanification(int y) {
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getColor());
            System.out.println("**C'est au tour de " + joueur.getNom() + " de jouer!**");
            LOGGER.info("**C'est au tour de " + joueur.getNom() + " de jouer!**");
            List<Carte> mainJoueur = joueur.getMainJ();


            for (Carte carte : mainJoueur) {
                boolean construction = choisirConstruction(joueur, carte);

                if (construction) {
                    System.out.println("        Vous avez choisi de construire " + carte.getNom());
                    LOGGER.info("        Vous avez choisi de construire " + carte.getNom());
                    joueur.ajouteEnConstruction(carte);
                } else {
                    System.out.println("        Vous avez choisi de recycler");
                    LOGGER.info("        Vous avez choisi de recycler");
                    joueur.RecyclCarte(carte, true);
                    System.out.println("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                    LOGGER.info("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                    System.out.println("        " + joueur.getRessources());
                    LOGGER.info("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                }
            }
            System.out.println("    Cartes en construction du joueur:");
            LOGGER.info("    Cartes en construction du joueur:");
            for (Carte c : joueur.getEnConstruction()) {
                System.out.print("          " + c.getNom() + "\n");
                LOGGER.info("          " + c.getNom() + "\n");
                System.out.println("");
            }
            joueur.effaceMain();
            if (joueur.getCartesConstruites().size() > 0)
                Defausse.defausseEnConstruction(joueur,y);
            System.out.println("    Distribution des ressources");
            LOGGER.info("    Distribution des ressources");
            distributionRessources(joueur);
            if (joueur.getKrystallium() > 0)
                Construction.distributionKrystallium(joueur);
        }
    }

    /**
     * Initiates the planning phase for a single player in the game, involving the selection of card packs,
     * potential discards, and construction or recycling decisions.
     *
     * @param paquetsDeCartes The list of card packs available for selection.
     * @param pioche The deck of cards.
     * @param tour The current game turn.
     */
    public void startPlanificationUnJoueur(ArrayList<List<Carte>> paquetsDeCartes,ArrayList<Carte> pioche,int tour ){
        for (Joueur joueur : joueurs) {
            for (int i = 0; i <= 1 ; i++) {
                List<Carte> paquet = paquetsDeCartes.get(0);
                paquetsDeCartes.remove(0);
                System.out.println(joueur.getColor());
                System.out.println("paquet : "+(i+1));
                LOGGER.info("paquet : "+(i+1));
                for (Carte carte : paquet) {
                    System.out.println("[" + paquet.indexOf(carte)+ "] Carte : " + carte.getNom());
                    LOGGER.info("[" + paquet.indexOf(carte)+ "] Carte : " + carte.getNom());
                }
                System.out.println("Le paquet vous convient-il ? (oui/non)");
                LOGGER.info("Le paquet vous convient-il ? (oui/non)");
                boolean paquetOk = random.nextBoolean();
                while (paquet.size() > 1 && paquetOk == false) {
                    System.out.println("Le paquet ne convient pas , choisissez 2 cartes a defausser");
                    LOGGER.info("Le paquet ne convient pas , choisissez 2 cartes a defausser");
                    for (int y = 0; y <= 1 ; y++) {
                        if (paquet.size() > 1) {
                            int choix =  random.nextInt(0,paquet.size()-1);
                            System.out.println("Vous avez choisis de defausser la carte : " + paquet.get(choix).getNom());
                            LOGGER.info("Vous avez choisis de defausser la carte : " + paquet.get(choix).getNom());
                            paquet.remove(choix);
                        }else{
                            int choix =  0;
                            System.out.println("Vous avez choisis de defausser la carte : " + paquet.get(choix).getNom());
                            LOGGER.info("Vous avez choisis de defausser la carte : " + paquet.get(choix).getNom());
                            paquet.remove(choix);
                        }

                    }
                    List<Carte> le5Temporaire = new ArrayList<>();
                    System.out.println("Voici 5 cartes temporaires. Choisissez une carte");
                    LOGGER.info("Voici 5 cartes temporaires. Choisissez une carte");
                    for (int j = 0; j < 5; j++) {
                        le5Temporaire.add(pioche.remove(0));
                        System.out.println("[" + le5Temporaire.indexOf(le5Temporaire.get(j))+ "] Carte Temporaire : " + le5Temporaire.get(j).getNom());
                        LOGGER.info("[" + le5Temporaire.indexOf(le5Temporaire.get(j))+ "] Carte Temporaire : " + le5Temporaire.get(j).getNom());
                    }
                    int choix =  random.nextInt(0,le5Temporaire.size()-1);
                    System.out.println("Vous avez choisis de prendre la carte : " + le5Temporaire.get(choix).getNom());
                    LOGGER.info("Vous avez choisis de prendre la carte : " + le5Temporaire.get(choix).getNom());
                    paquet.add(le5Temporaire.get(choix));
                    System.out.println("Voici le nouveau paquet : ");
                    LOGGER.info("Voici le nouveau paquet : ");
                    for (Carte carte : paquet) {
                        System.out.println("[" + paquet.indexOf(carte)+ "] Carte : " + carte.getNom());
                        LOGGER.info("[" + paquet.indexOf(carte)+ "] Carte : " + carte.getNom());
                    }
                    System.out.println("Le paquet vous convient-il ? (oui/non)");
                    LOGGER.info("Le paquet vous convient-il ? (oui/non)");
                    paquetOk = random.nextBoolean();

                }
                for (Carte carte : paquet) {
                boolean construction = choisirConstruction(joueur, carte);

                if (construction) {
                    System.out.println("        Vous avez choisi de construire " + carte.getNom());
                    LOGGER.info("        Vous avez choisi de construire " + carte.getNom());
                    joueur.ajouteEnConstruction(carte);
                } else {
                    System.out.println("        Vous avez choisi de recycler");
                    LOGGER.info("        Vous avez choisi de recycler");
                    joueur.RecyclCarte(carte, true);
                    System.out.println("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                    LOGGER.info("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                    System.out.println("        " + joueur.getRessources());
                    LOGGER.info("        " + joueur.getNom() + " a recyclé la carte " + carte.getNom());
                }
            }
            System.out.println("    Cartes en construction du joueur:");
            LOGGER.info("    Cartes en construction du joueur:");
            for (Carte c : joueur.getEnConstruction()) {
                System.out.print("          " + c.getNom() + "\n");
                LOGGER.info("          " + c.getNom() + "\n");
                System.out.println("");
            }
            joueur.effaceMain();
            if (joueur.getCartesConstruites().size() > 0)
                Defausse.defausseEnConstruction(joueur,tour);
            System.out.println("    Distribution des ressources");
            LOGGER.info("    Distribution des ressources");
            distributionRessources(joueur);
            if (joueur.getKrystallium() > 0)
                Construction.distributionKrystallium(joueur);
                //todo : faire la planification pour un joueur
            }
        }
    }

    /**
     * This method helps the player decide whether to construct or recycle a card.
     * It considers Points de Victoire, Coûts de Production, and Bonus de
     * Construction/Recyclage.
     *
     * @param joueur The player making the decision.
     * @param carte  The card to decide on.
     * @return True if the player decides to construct, false if recycling.
     */
    private boolean choisirConstruction(Joueur joueur, Carte carte) {
        int niveau = joueur.getNiveau();
        if (niveau == 1 || niveau == 3) {
            // Niveau 1 et 2(Random): Le joueur choisit aléatoirement.
            return random.nextBoolean();
        } else {
            // Niveau 2 (Simple): Le joueur choisit en fonction de la valeur de la carte.
            // Niveau supérieur (Intelligent): Utiliser des critères pour décider.
            int pointsVictoire = carte.getpVictoire().values().stream().mapToInt(Integer::intValue).sum();
            int coutProdTotal = carte.getCoutProd().values().stream().mapToInt(Integer::intValue).sum();
            int bonusConstruction = carte.getBonusConst().values().stream().mapToInt(Integer::intValue).sum();
            int bonusRecyclage = carte.getBonusRec().values().stream().mapToInt(Integer::intValue).sum();

            // Considérer les points de victoire, les coûts de production et les bonus.
            if (pointsVictoire > 5) {
                // Si la carte offre un grand nombre de points de victoire, elle est plus
                // attrayante à construire.
                return true;
            } else if (coutProdTotal > 8) {
                // Si les coûts de production sont élevés, envisagez de recycler, surtout si les
                // ressources sont difficiles à obtenir.
                return false;
            } else {
                // Considérer les bonus de construction et recyclage.
                return bonusConstruction > bonusRecyclage;
            }
        }
    }

    /**
     * This method distributes the resources of a player to their constructions.
     * It iterates over the resources of the player and for each resource, it calls
     * the distribution method of the Construction class
     * to distribute the resource to the constructions that
     *
     * @param joueur The player whose resources are being distributed.
     */
    private void distributionRessources(Joueur joueur) {
        for (Map.Entry<String, Integer> entry : joueur.getRessources().entrySet()) {
            String cle = entry.getKey();
            int valeur = entry.getValue();
            System.out.println("        Ressource : " + cle + ", Valeur : " + valeur);
            LOGGER.info("        Ressource : " + cle + ", Valeur : " + valeur);
            while (valeur > 0) {
                Construction.distribution(joueur.getEnConstruction(), cle, valeur, joueur);
                valeur--;
            }
            System.out.println("");
        }
    }

}
