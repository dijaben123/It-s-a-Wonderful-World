package iww;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represents a player in the game. It contains the player's name,
 * level, deck of cards, hand of cards, resources, cards under construction,
 * built cards, and various resource counts.
 */
public class Joueur {

    private final static Logger LOGGER = Logger.getLogger("");

    private String nom;
    private int niveau;
    private ArrayList<Carte> pioche;
    private ArrayList<Carte> mainJoueur;
    private Map<String, Integer> ressources;
    private ArrayList<Carte> cartesEnConstruction;
    private ArrayList<Carte> cartesConstruites;
    private int krystallium;
    private int general;
    private int financier;
    private int transformation;
    private Empire empire;
    private int score;
    private Statistique statistiques;
    private String couleur;

    /**
     * Constructs a new Joueur object with the given name and level.
     * Initializes the player's pioche, mainJoueur, ressources,
     * cartesEnConstruction, and cartesConstruites.
     * Sets the player's krystallium, general, financier, and transformation to 0.
     *
     * @param nom    the name of the player
     * @param niveau the level of the player
     * @param couleur  the couleur of the player
     */
    public Joueur(String nom, int niveau, String couleur) {
        this.nom = nom;
        this.niveau = niveau;
        this.pioche = new ArrayList<Carte>();
        this.mainJoueur = new ArrayList<Carte>();
        this.ressources = genereRessources();
        this.cartesEnConstruction = new ArrayList<Carte>();
        this.cartesConstruites = new ArrayList<Carte>();
        this.krystallium = 0;
        this.general = 0;
        this.financier = 0;
        this.transformation = 0;
        this.empire = null;
        this.score = 0;
        this.statistiques = new Statistique();
        this.couleur = couleur;
    }

    /**
     * Returns the amount of Krystallium the player has.
     *
     * @return the amount of Krystallium the player has
     */
    public int getKrystallium() {
        return krystallium;
    }

    /**
     * Returns the color the player has.
     *
     * @return the color the player has
     */
    public String getColor() {
        return couleur;
    }

    /**
     * Returns the value of the general attribute for this player.
     *
     * @return the value of the general attribute for this player
     */
    public int getGeneral() {
        return general;
    }

    /**
     * Returns the value of the 'financier' attribute of the player.
     *
     * @return the value of the 'financier' attribute of the player.
     */
    public int getFinancier() {
        return financier;
    }

    /**
     * Returns the transformation level of the player.
     *
     * @return the transformation level of the player
     */
    public int getTransformation() {
        return transformation;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getNom() {
        return nom;
    }

    /**
     * Returns the level of the player.
     *
     * @return the level of the player
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Returns the list of constructed cards of the player.
     *
     * @return the list of constructed cards of the player
     */
    public ArrayList<Carte> getCartesConstruites() {
        return cartesConstruites;
    }

    /**
     * Returns the player's deck of cards.
     *
     * @return the player's deck of cards
     */
    public ArrayList<Carte> getPioche() {
        return pioche;
    }

    /**
     * Returns an ArrayList of the names of the cards in the player's deck.
     *
     * @return ArrayList of Strings containing the names of the cards in the
     *         player's deck.
     */
    public ArrayList<String> getNomPioche() {
        ArrayList<String> nomPioche = new ArrayList<String>();
        int c;
        for (c = 0; c < getPioche().size(); c++) {
            nomPioche.add(getPioche().get(c).getNom());
        }
        return nomPioche;
    }

    /**
     * Sets the player's deck of cards.
     *
     * @param pioche the deck of cards to set
     */
    public void setPioche(ArrayList<Carte> pioche) {
        this.pioche = pioche;
    }

    /**
     * Returns the list of cards in the player's hand.
     *
     * @return the list of cards in the player's hand
     */
    public ArrayList<Carte> getMainJ() {
        return mainJoueur;
    }

    /**
     * Returns an ArrayList of the names of the cards in the player's hand.
     *
     * @return ArrayList of String containing the names of the cards in the player's
     *         hand.
     */
    public ArrayList<String> getNomCartes() {
        ArrayList<String> nomCarte = new ArrayList<String>();
        int c;
        for (c = 0; c < getMainJ().size(); c++) {
            nomCarte.add(getMainJ().get(c).getNom());
        }
        return nomCarte;
    }

    /**
     * Ajoute une carte à la main du joueur.
     *
     * @param carte La carte à ajouter à la main du joueur.
     */
    public void ajoutCarteMain(Carte carte) {
        mainJoueur.add(carte);
    }

    /**
     * Removes all cards from the player's hand.
     */
    public void effaceMain() {
        this.mainJoueur.clear();
    }

    /**
     * Returns the list of cards currently being constructed by the player.
     *
     * @return the list of cards currently being constructed by the player
     */
    public ArrayList<Carte> getEnConstruction() {
        return this.cartesEnConstruction;
    }

    /**
     * Returns a map of the resources owned by the player.
     *
     * @return a map of the resources owned by the player
     */
    public Map<String, Integer> getRessources() {
        return this.ressources;
    }

    /**
     * Ajoute une carte en construction à la liste des cartes en construction du
     * joueur.
     *
     * @param carte la carte à ajouter à la liste des cartes en construction
     */
    public void ajouteEnConstruction(Carte carte) {
        this.cartesEnConstruction.add(carte);
    }

    /**
     * Generates a HashMap of resources with initial values set to 0.
     *
     * @return HashMapRessources A HashMap containing the resources and their
     *         initial values.
     */
    public LinkedHashMap genereRessources() {
        LinkedHashMap<String, Integer> HashMapRessources = new LinkedHashMap<String, Integer>();
        HashMapRessources.put("materiau", 0);
        HashMapRessources.put("energie", 0);
        HashMapRessources.put("science", 0);
        HashMapRessources.put("or", 0);
        HashMapRessources.put("exploration", 0);

        return HashMapRessources;
    }

    /**
     * Recycles a card and adds its bonus resources to the player's resources.
     *
     * @param carte The card to be recycled.
     * @param isRecycling If the card is recycled.
     */
    public void RecyclCarte(Carte carte, Boolean isRecycling) {
        Map bonusRec = carte.getBonusRec();
        Set<String> cles = bonusRec.keySet();
        int ressource = 0;
        for (String cle : cles) {
            ressource = ressources.get(cle) + 1;
            ressources.put(cle, ressource);
            statistiques.genereStatCubes(cle);
        }
        if(isRecycling)statistiques.addCarteRecycles(1);
        else statistiques.addCarteDefaussees(1);
    }

    /**
     * Increases the transformation counter and prints the amount of resources
     * stored.
     * If the transformation counter reaches 5, it converts the resources into
     * krystallium and resets the counter.
     * Prints the amount of krystallium and the new amount of resources stored.
     */
    public void stockRessource() {
        this.transformation++;
        System.out.println("        Ressource stockées " + this.transformation);
        LOGGER.info("        Ressource stockées " + this.transformation);
        if (transformation == 5) {
            this.krystallium = this.krystallium + 1;
            statistiques.addCubesKrystalliumRecoltes(1);
            this.transformation = 0;
            System.out.println("        --Ressource transformées en krystallium--");
            LOGGER.info("        --Ressource transformées en krystallium--");
            System.out.println("        Krystallium = " + this.krystallium);
            LOGGER.info("        --Ressource transformées en krystallium--");
            System.out.println("        Ressource stockées " + this.transformation);
            LOGGER.info("        Ressource stockées " + this.transformation);
        }
    }

    /**
     * Adds a constructed card to the player's list of constructed cards and updates
     * their resources based on the card's construction bonuses.
     *
     * @param carte The card will be builded.
     */
    public void ajoutCarteConstruite(Carte carte) {
        this.cartesConstruites.add(carte);
        System.out.println("carte construite " + carte.getNom());
        LOGGER.info("carte construite " + carte.getNom());
        Map<String, Integer> bonusConstruction = carte.getBonusConst();
        System.out.println("ressource = " + ressources);
        LOGGER.info("ressource = " + ressources);
        System.out.println("bonusConstruction = " + bonusConstruction);
        LOGGER.info("bonusConstruction = " + bonusConstruction);
        System.out.println("AVANT FINANCIER = " + this.financier + " GENERAL = " + this.general + " KRYSTALLIUM = "
                + this.krystallium);
        LOGGER.info("AVANT FINANCIER = " + this.financier + " GENERAL = " + this.general + " KRYSTALLIUM = "
                + this.krystallium);

        for (Map.Entry<String, Integer> entry : bonusConstruction.entrySet()) {
            String cle = entry.getKey();
            Integer valeur = entry.getValue();
            if (valeur != null) {
                if (cle.equals("krystallium")) {
                    this.krystallium = this.krystallium + valeur;
                    statistiques.addCubesKrystalliumRecoltes(valeur);
                } else if (cle.equals("general")) {
                    this.general = this.general + valeur;
                    statistiques.addJetonsGenerauxRecoltes(valeur);
                } else if (cle.equals("financier")) {
                    this.financier = this.financier + valeur;
                    statistiques.addJetonsFinanceRecoltes(valeur);
                }
            }
        }
        System.out.println("ressource apres construction = " + ressources);
        LOGGER.info("ressource apres construction = " + ressources);
    }

    /**
     * Removes a card from the player's list of cards under construction.
     * @param carte The card to remove.
     */
    public void enleverCarteEnConstruction(Carte carte) {
        int index = 0;
        int carteTrouve = -1;
        for (Carte c : cartesEnConstruction) {
            if (c.getNom().equals(carte.getNom())) {
                Set<String> clesCoutProd = carte.getCoutProd().keySet();
                int cpt = 0;
                for (String cleCoutProd : clesCoutProd) {
                    if (carte.getCoutProd().get(cleCoutProd) == 0) {
                        cpt++;
                    }
                }
                if (cpt == clesCoutProd.size() && c.getIndex() == carte.getIndex()) {
                    carteTrouve = index;
                }
            }
            index++;
        }
        if (carteTrouve >= 0) {
            cartesEnConstruction.remove(carteTrouve).getNom();
        }
    }

    /**
     * Decreases the amount of Krystallium owned by the player by 1.
     */
    public void enleveKrystallium() {
        krystallium--;
    }

    /**
     * Sets the empire of the player.
     *
     * @param empire the empire to set
     */
    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    /**
     * Returns the empire of the player.
     *
     * @return the empire of the player
     */
    public Empire getEmpire() {
        return this.empire;
    }

    /**
     * Sets the value of the "general" attribute for the player.
     *
     * @param general The new value for the "general" attribute.
     */
    public void setGeneral(int general) {
        this.general = general;
    }

    /**
     * Sets the value of the "financier" attribute for the player.
     *
     * @param financier The new value for the "financier" attribute.
     */
    public void setFinancier(int financier) {
        this.financier = financier;
    }


    /**
     * Returns the number of victory points of the player.
     *
     * @return the number of victory points of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the player.
     *
     * @param score The new score for the player.
    */
    public void setScore(int score) {
        this.score = score;
    }

    /**
    * Resets the player's inventory by setting all resources and transformations to zero.
    */
    public void resetInventaire(){
        this.krystallium = 0;
        this.financier = 0;
        this.general = 0;
        this.transformation = 0;
    }

    /**
     * Gets the statistics of the player.
     *
     * @return The statistics of the player.
     */
    public Statistique getStatistiques() {
        return statistiques;
    }

    /**
     * Sets the statistics of the player.
     *
     * @param statistique The new statistics for the player.
     */
    public void setStatistique(Statistique statistique) {
        this.statistiques = statistique;
    }


}
