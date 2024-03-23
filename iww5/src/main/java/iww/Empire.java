package iww;

import java.util.LinkedHashMap;

/**
 * The Empire class represents an empire in the game. It contains information
 * about the empire's name, victory points,
 * and production values.
 */
public class Empire {

    private String nom;
    private LinkedHashMap<String, Integer> ptsVictoire;
    private LinkedHashMap<String, Integer> productions;

    /**
     * Constructs a new Empire object with the given name, victory points, and
     * production values.
     *
     * @param nom         the name of the empire
     * @param ptsVictoire a LinkedHashMap containing the victory points for the empire
     * @param productions a LinkedHashMap containing the production values for the empire
     */
    public Empire(String nom, LinkedHashMap<String, Integer> ptsVictoire, LinkedHashMap<String, Integer> productions) {
        this.nom = nom;
        this.ptsVictoire = ptsVictoire;
        this.productions = productions;
    }

    /**
     * Returns the name of the empire.
     *
     * @return the name of the empire
     */
    public String getNom() {
        return nom;
    }

    /**
     * Returns a LinkedHashMap containing the victory points for the empire.
     *
     * @return a LinkedHashMap containing the victory points for the empire
     */
    public LinkedHashMap<String, Integer> getPtsVictoire() {
        return ptsVictoire;
    }

    /**
     * Returns a LinkedHashMap containing the production values for the empire.
     *
     * @return a LinkedHashMap containing the production values for the empire
     */
    public LinkedHashMap<String, Integer> getProductions() {
        return productions;
    }

}
