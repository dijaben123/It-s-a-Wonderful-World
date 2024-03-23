package iww;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Carte class represents a card in the game.
 */
public class Carte {

    private String nom;
    private String type;
    private int index;
    private Map<String, Integer> coutProd;
    private Map<String, Integer> bonusConst;
    private Map<String, Integer> bonusRec;
    private Map<String, String> production;
    private Map<String, Integer> pVictoire;

    /**
     * Constructs a new Carte object with the given parameters.
     *
     * @param nom        the name of the card
     * @param type       the type of the card
     * @param index      the index of the card
     * @param coutProd   the production cost of the card
     * @param bonusConst the construction bonus of the card
     * @param bonusRec   the recycling bonus of the card
     * @param production the production bonus of the card
     * @param pVictoire  the victory points bonus of the card
     */
    public Carte(String nom, String type, int index, LinkedHashMap<String, Integer> coutProd,
            LinkedHashMap<String, Integer> bonusConst, LinkedHashMap<String, Integer> bonusRec,
            LinkedHashMap<String, String> production, LinkedHashMap<String, Integer> pVictoire) {
        this.nom = nom;
        this.type = type;
        this.index = index;
        this.coutProd = coutProd;
        this.bonusConst = bonusConst;
        this.bonusRec = bonusRec;
        this.production = production;
        this.pVictoire = pVictoire;
    }

    /**
     * Returns the name of the card.
     *
     * @return the name of the card
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Returns the type of this card.
     *
     * @return the type of this card
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the map of bonuses received by the card.
     *
     * @return the map of bonuses received by the card
     */
    public Map<String, Integer> getBonusRec() {
        return this.bonusRec;
    }

    /**
     * Returns the map of constant bonuses associated with this card.
     *
     * @return the map of constant bonuses associated with this card
     */
    public Map<String, Integer> getBonusConst() {
        return this.bonusConst;
    }

    /**
     * Returns the map of production costs for this card.
     *
     * @return the map of production costs for this card
     */
    public Map<String, Integer> getCoutProd() {
        return coutProd;
    }

    /**
     * Sets the bonus received for the card.
     *
     * @param bonusRec a map containing the bonus received for the card
     */
    public void setBonusRec(Map<String, Integer> bonusRec) {
        this.bonusRec = bonusRec;
    }

    /**
     * Returns the index of the card.
     *
     * @return the index of the card
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the production map of the card.
     *
     * @return the production map of the card
     */
    public Map<String, String> getProduction() {
        return production;
    }

    /**
     * Gets the map representing the victory points of players, where the keys are player names
     * and the values are their respective victory points.
     *
     * @return The map of player victory points.
     */
    public Map<String, Integer> getpVictoire() {
        return pVictoire;
    }

}
