package iww;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;

/**
 * The Production class represents the production of resources for all players
 * in the game.
 * It contains an array of players and an array of resources, and provides a
 * method to start the production of resources.
 */
public class Production {

    private Joueur[] joueurs;
    private String[] ressources;
    private final static Logger LOGGER = Logger.getLogger("");

    /**
     * Constructs a Production object with the given array of players and
     * initializes the resources array.
     *
     * @param joueurs the array of players to be assigned to the Production object
     */
    public Production(Joueur[] joueurs) {
        this.joueurs = joueurs;
        this.ressources = new String[] { "materiau", "energie", "science", "or", "exploration" };
    }

    /**
     * Starts the production of resources for all players in the game.
     * Loops through each resource and each player, calculates the total amount of
     * the resource
     * produced by the player's constructed cards and empire, and distributes it to
     * the player's
     * under-construction cards. Also distributes Krystallium to players who have
     * it.
     *
     * @param y The current game turn.
     */
    public void startProduction(int y) {
        int[] tabQuantiteRecue = new int[joueurs.length];
        for (String ressource : ressources) {
            int index = 0;
            for (Joueur joueur : joueurs) {
                if (joueur.getCartesConstruites().size() > 0)
                    Defausse.defausseEnConstruction(joueur, y);
                System.out.println(joueur.getColor());
                int numberRessource = 0;
                ArrayList<Carte> cartesProductionJoueur = joueur.getCartesConstruites();
                for (Carte carteProductionJoueur : cartesProductionJoueur) {
                    Map<String, String> productions = carteProductionJoueur.getProduction();
                    for (Map.Entry<String, String> production : productions.entrySet()) {
                        String nomRessource = production.getKey();
                        String quantite = production.getValue();
                        String regex = "\\d+"; // \\d signifie un chiffre, + signifie une ou plusieurs occurrences
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(quantite);
                        int quantiteRecue = 0;
                        if (matcher.matches()) {
                            quantiteRecue = Integer.parseInt(quantite);
                        } else {
                            for (Carte c : joueur.getCartesConstruites()) {
                                if (c.getType().equals(quantite))
                                    quantiteRecue++;
                            }
                        }
                        if (ressource.equals(nomRessource)) {
                            numberRessource += quantiteRecue;
                            System.out.println("Le joueur " + joueur.getNom() + " a produit " + quantiteRecue + " "
                                    + ressource + " via " + carteProductionJoueur.getNom());
                            LOGGER.info("Le joueur " + joueur.getNom() + " a produit " + quantiteRecue + " " + ressource
                                    + " via " + carteProductionJoueur.getNom());
                        }
                    }
                }
                Empire EmpireJoueur = joueur.getEmpire();
                LinkedHashMap<String, Integer> ProductionEmpire = EmpireJoueur.getProductions();
                for (Map.Entry<String, Integer> production : ProductionEmpire.entrySet()) {
                    String nomRessource = production.getKey();
                    if (ressource.equals(nomRessource)) {
                        int quantite = production.getValue();
                        numberRessource += quantite;
                        System.out.println("Le joueur " + joueur.getNom() + " a produit " + quantite + " " + ressource
                                + " via son empire");
                        LOGGER.info("Le joueur " + joueur.getNom() + " a produit " + quantite + " " + ressource
                                + " via son empire");
                    }
                }
                tabQuantiteRecue[index] = numberRessource;
                System.out.println("Le joueur " + joueur.getNom() + " a produit " + numberRessource + " " + ressource
                        + " au total");
                LOGGER.info("Le joueur " + joueur.getNom() + " a produit " + numberRessource + " " + ressource
                        + " au total");
                System.out.println("Le joueur " + joueur.getNom() + " possede " + numberRessource + " " + ressource);
                LOGGER.info("Le joueur " + joueur.getNom() + " possede " + numberRessource + " " + ressource);
                if ((ressource.equals("materiau") || ressource.equals("or")) && numberRessource >= 3) {
                    joueur.setFinancier(joueur.getFinancier() + 1);
                    joueur.getStatistiques().addJetonsFinanceRecoltes(1);
                    System.out.println("Le joueur " + joueur.getNom() + "gagne 1 financier");
                    LOGGER.info("Le joueur " + joueur.getNom() + "gagne 1 financier");
                    System.out.println("Nombre de financiers " + joueur.getFinancier());
                    LOGGER.info("Nombre de financiers " + joueur.getFinancier());
                } else if ((ressource.equals("energie") || ressource.equals("exploration")) && numberRessource >= 3) {
                    joueur.setGeneral(joueur.getGeneral() + 1);
                    joueur.getStatistiques().addJetonsGenerauxRecoltes(1);
                    System.out.println("Le joueur " + joueur.getNom() + " gagne 1 general");
                    LOGGER.info("Le joueur " + joueur.getNom() + " gagne 1general");
                    System.out.println("Nombre de generaux " + joueur.getGeneral());
                    LOGGER.info("Nombre de generaux " + joueur.getGeneral());
                } else if (ressource.equals("science") && numberRessource >= 3) {
                    Random random = new Random();
                    int rand = random.nextInt(0, 2);
                    if (rand == 0) {
                        joueur.setFinancier(joueur.getFinancier() + 1);
                        joueur.getStatistiques().addJetonsFinanceRecoltes(1);
                        System.out.println("Le joueur " + joueur.getNom() + " gagne 1 financier");
                        LOGGER.info("Le joueur " + joueur.getNom() + " gagne 1 financier");
                        System.out.println("Nombre de financier " + joueur.getFinancier());
                        LOGGER.info("Nombre de financier " + joueur.getFinancier());
                    } else if (rand == 1) {
                        joueur.setGeneral(joueur.getGeneral() + 1);
                        joueur.getStatistiques().addJetonsGenerauxRecoltes(1);
                        System.out.println("Le joueur " + joueur.getNom() + " gagne 1 general");
                        LOGGER.info("Le joueur " + joueur.getNom() + " gagne 1 general");
                        System.out.println("Nombre de general " + joueur.getGeneral());
                        LOGGER.info("Nombre de general " + joueur.getGeneral());
                    }
                }
                if (numberRessource > 0) {
                    for (int i = 0; i < numberRessource; i++) {
                        joueur.getStatistiques().genereStatCubes(ressource);
                    }
                    Construction.distribution(joueur.getEnConstruction(), ressource, numberRessource, joueur);
                }
                if (joueur.getKrystallium() > 0) {
                    Construction.distributionKrystallium(joueur);
                }
                index++;
            }
            if (joueurs.length >= 2) {
                int maxQuantite = Integer.MIN_VALUE;
                int indexMaxQuantite = -1;
                for (int i = 0; i < tabQuantiteRecue.length; i++) {
                    if (tabQuantiteRecue[i] > maxQuantite) {
                        maxQuantite = tabQuantiteRecue[i];
                        indexMaxQuantite = i;
                    }
                }
                boolean multipleMaxQuantities = false;
                for (int i = 0; i < tabQuantiteRecue.length; i++) {
                    if (i != indexMaxQuantite && tabQuantiteRecue[i] == maxQuantite) {
                        multipleMaxQuantities = true;
                        break;
                    }
                }
                if (!multipleMaxQuantities) {
                    Joueur joueurBonus = joueurs[indexMaxQuantite];
                    System.out.println(joueurBonus.getColor());
                    if (ressource.equals("materiau") || ressource.equals("or")) {
                        joueurBonus.setFinancier(joueurBonus.getFinancier() + 1);
                        joueurBonus.getStatistiques().addJetonsFinanceRecoltes(1);
                        System.out.println("Le joueur " + joueurBonus.getNom() + "gagne 1 financier");
                        LOGGER.info("Le joueur " + joueurBonus.getNom() + "gagne 1 financier");
                        System.out.println("Nombre de financiers " + joueurBonus.getFinancier());
                        LOGGER.info("Nombre de financiers " + joueurBonus.getFinancier());
                    } else if (ressource.equals("energie") || ressource.equals("exploration")) {
                        joueurBonus.setGeneral(joueurBonus.getGeneral() + 1);
                        joueurBonus.getStatistiques().addJetonsGenerauxRecoltes(1);
                        System.out.println("Le joueur " + joueurBonus.getNom() + " gagne 1 general");
                        LOGGER.info("Le joueur " + joueurBonus.getNom() + " gagne 1 general");
                        System.out.println("Nombre de generaux " + joueurBonus.getGeneral());
                        LOGGER.info("Nombre de generaux " + joueurBonus.getGeneral());
                    } else if (ressource.equals("science")) {
                        Random random = new Random();
                        int rand = random.nextInt(0, 2);
                        if (rand == 0) {
                            joueurBonus.setFinancier(joueurBonus.getFinancier() + 1);
                            joueurBonus.getStatistiques().addJetonsFinanceRecoltes(1);
                            System.out.println("Le joueur " + joueurBonus.getNom() + " gagne 1 financier");
                            LOGGER.info("Le joueur " + joueurBonus.getNom() + " gagne 1 financier");
                            System.out.println("Nombre de financier " + joueurBonus.getFinancier());
                            LOGGER.info("Nombre de financier " + joueurBonus.getFinancier());
                        } else if (rand == 1) {
                            joueurBonus.setGeneral(joueurBonus.getGeneral() + 1);
                            joueurBonus.getStatistiques().addJetonsGenerauxRecoltes(1);
                            System.out.println("Le joueur " + joueurBonus.getNom() + " gagne 1 general");
                            LOGGER.info("Le joueur " + joueurBonus.getNom() + " gagne 1 general");
                            System.out.println("Nombre de general " + joueurBonus.getGeneral());
                            LOGGER.info("Nombre de general " + joueurBonus.getGeneral());
                        }
                    }
                }
            }
        }
    }
}
