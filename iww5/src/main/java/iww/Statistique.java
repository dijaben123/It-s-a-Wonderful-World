package iww;

import java.util.ArrayList;

/**
 * The {@code Statistique} class represents the statistics for a game.
 * It tracks various aspects of player performance and game outcomes.
 */
public class Statistique {

    private int resultatPartie;
    private int ptsBatiments;
    private int ptsJetons;
    private int ptsEmpire;
    private int carteRecycles;
    private int carteDefaussees;
    private int batimentsConstruits;
    private int batimentsMateriauTermines;
    private int batimentsEnergieTermines;
    private int batimentsScienceTermines;
    private int batimentsOrTermines;
    private int batimentsExploitationTermines;
    private int cubesMateriauRecoltes;
    private int cubesEnergieRecoltes;
    private int cubesScienceRecoltes;
    private int cubesOrRecoltes;
    private int cubesExploitationRecoltes;
    private int cubesKrystalliumRecoltes;
    private int jetonsGenerauxRecoltes;
    private int jetonsFinanceRecoltes;
    private int nbrPartiesGagnees;

    /**
     * Gets the result of the current game.
     *
     * @return The result of the current game.
     */
    public int getResultatPartie() {
        return resultatPartie;
    }

    /**
     * Gets the total points from constructed buildings.
     *
     * @return The total points from constructed buildings.
     */
    public int getPtsBatiments() {
        return ptsBatiments;
    }

    /**
     * Gets the total points from collected tokens.
     *
     * @return The total points from collected tokens.
     */
    public int getPtsJetons() {
        return ptsJetons;
    }

    /**
     * Gets the total points from the empire.
     *
     * @return The total points from the empire.
     */
    public int getPtsEmpire() {
        return ptsEmpire;
    }

    /**
     * Gets the number of recycled cards.
     *
     * @return The number of recycled cards.
     */
    public int getCarteRecycles() {
        return carteRecycles;
    }

    /**
     * Gets the number of discarded cards.
     *
     * @return The number of discarded cards.
     */
    public int getCarteDefaussees() {
        return carteDefaussees;
    }

    /**
     * Gets the total number of constructed buildings.
     *
     * @return The total number of constructed buildings.
     */
    public int getBatimentsConstruits() {
        return batimentsConstruits;
    }

    /**
     * Gets the total number of buildings with material completed.
     *
     * @return The total number of buildings with material completed.
     */
    public int getBatimentsMateriauTermines() {
        return batimentsMateriauTermines;
    }

    /**
     * Gets the total number of buildings with energy completed.
     *
     * @return The total number of buildings with energy completed.
     */
    public int getBatimentsEnergieTermines() {
        return batimentsEnergieTermines;
    }

    /**
     * Gets the total number of buildings with science completed.
     *
     * @return The total number of buildings with science completed.
     */
    public int getBatimentsScienceTermines() {
        return batimentsScienceTermines;
    }

    /**
     * Gets the total number of buildings with gold completed.
     *
     * @return The total number of buildings with gold completed.
     */
    public int getBatimentsOrTermines() {
        return batimentsOrTermines;
    }

    /**
     * Gets the total number of buildings with exploitation completed.
     *
     * @return The total number of buildings with exploitation completed.
     */
    public int getBatimentsExploitationTermines() {
        return batimentsExploitationTermines;
    }

    /**
     * Gets the total number of harvested material cubes.
     *
     * @return The total number of harvested material cubes.
     */
    public int getCubesMateriauRecoltes() {
        return cubesMateriauRecoltes;
    }

    /**
     * Gets the total number of harvested energy cubes.
     *
     * @return The total number of harvested energy cubes.
     */
    public int getCubesEnergieRecoltes() {
        return cubesEnergieRecoltes;
    }

    /**
     * Gets the total number of harvested science cubes.
     *
     * @return The total number of harvested science cubes.
     */
    public int getCubesScienceRecoltes() {
        return cubesScienceRecoltes;
    }

    /**
     * Gets the total number of harvested gold cubes.
     *
     * @return The total number of harvested gold cubes.
     */
    public int getCubesOrRecoltes() {
        return cubesOrRecoltes;
    }

    /**
     * Gets the total number of harvested exploitation cubes.
     *
     * @return The total number of harvested exploitation cubes.
     */
    public int getCubesExploitationRecoltes() {
        return cubesExploitationRecoltes;
    }

    /**
     * Gets the total number of harvested Krystallium cubes.
     *
     * @return The total number of harvested Krystallium cubes.
     */
    public int getCubesKrystalliumRecoltes() {
        return cubesKrystalliumRecoltes;
    }

    /**
     * Gets the total number of collected General tokens.
     *
     * @return The total number of collected General tokens.
     */
    public int getJetonsGenerauxRecoltes() {
        return jetonsGenerauxRecoltes;
    }

    /**
     * Gets the total number of collected Finance tokens.
     *
     * @return The total number of collected Finance tokens.
     */
    public int getJetonsFinanceRecoltes() {
        return jetonsFinanceRecoltes;
    }

    /**
     * Adds the result of a game to the total result.
     *
     * @param resultatPartie The result of the game to be added.
     */
    public void addResultatPartie(int resultatPartie) {
        this.resultatPartie += resultatPartie;
    }

    /**
     * Adds the specified points from constructed buildings to the total.
     *
     * @param ptsBatiments The points from constructed buildings to be added.
     */
    public void addPtsBatiments(int ptsBatiments) {
        this.ptsBatiments += ptsBatiments;
    }

    /**
     * Adds the specified points from collected tokens to the total.
     *
     * @param ptsJetons The points from collected tokens to be added.
     */
    public void addPtsJetons(int ptsJetons) {
        this.ptsJetons += ptsJetons;
    }

    /**
     * Adds the specified points from the empire to the total.
     *
     * @param ptsEmpire The points from the empire to be added.
     */
    public void addPtsEmpire(int ptsEmpire) {
        this.ptsEmpire += ptsEmpire;
    }

    /**
     * Adds the specified number of recycled cards to the total.
     *
     * @param carteRecycles The number of recycled cards to be added.
     */
    public void addCarteRecycles(int carteRecycles) {
        this.carteRecycles += carteRecycles;
    }

    /**
     * Adds the specified number of discarded cards to the total.
     *
     * @param carteDefaussees The number of discarded cards to be added.
     */
    public void addCarteDefaussees(int carteDefaussees) {
        this.carteDefaussees += carteDefaussees;
    }

    /**
     * Adds the specified number of harvested Krystallium cubes to the total.
     *
     * @param cubesKrystalliumRecoltes The number of harvested Krystallium cubes to
     *                                 be added.
     */
    public void addCubesKrystalliumRecoltes(int cubesKrystalliumRecoltes) {
        this.cubesKrystalliumRecoltes += cubesKrystalliumRecoltes;
    }

    /**
     * Adds the specified number of collected General tokens to the total.
     *
     * @param jetonsGenerauxRecoltes The number of collected General tokens to be
     *                               added.
     */
    public void addJetonsGenerauxRecoltes(int jetonsGenerauxRecoltes) {
        this.jetonsGenerauxRecoltes += jetonsGenerauxRecoltes;
    }

    /**
     * Adds the specified number of collected Finance tokens to the total.
     *
     * @param jetonsFinanceRecoltes The number of collected Finance tokens to be
     *                              added.
     */
    public void addJetonsFinanceRecoltes(int jetonsFinanceRecoltes) {
        this.jetonsFinanceRecoltes += jetonsFinanceRecoltes;
    }

    /**
     * Constructs a new instance of the {@code Statistique} class with initial
     * values set to zero.
     * This constructor initializes all statistical variables representing game
     * outcomes and player performance.
     */
    public Statistique() {
        this.resultatPartie = 0;
        this.ptsBatiments = 0;
        this.ptsJetons = 0;
        this.ptsEmpire = 0;
        this.carteRecycles = 0;
        this.carteDefaussees = 0;
        this.batimentsConstruits = 0;
        this.batimentsMateriauTermines = 0;
        this.batimentsEnergieTermines = 0;
        this.batimentsScienceTermines = 0;
        this.batimentsOrTermines = 0;
        this.batimentsExploitationTermines = 0;
        this.cubesMateriauRecoltes = 0;
        this.cubesEnergieRecoltes = 0;
        this.cubesScienceRecoltes = 0;
        this.cubesOrRecoltes = 0;
        this.cubesExploitationRecoltes = 0;
        this.cubesKrystalliumRecoltes = 0;
        this.jetonsGenerauxRecoltes = 0;
        this.jetonsFinanceRecoltes = 0;
        this.nbrPartiesGagnees = 0;
    }

    /**
     * Generates statistics related to the buildings constructed based on the
     * provided list of cards.
     *
     * @param cartesConstruites The list of cards representing constructed
     *                          buildings.
     */
    public void genereStatsBatiments(ArrayList<Carte> cartesConstruites) {
        // Increment the total number of constructed buildings.
        this.batimentsConstruits += cartesConstruites.size();

        // Variables to count different types of buildings.
        int batMateriaux = 0;
        int batEnergies = 0;
        int batSciences = 0;
        int batOr = 0;
        int batExplo = 0;

        // Iterate through the list of constructed buildings and count each type.
        for (Carte c : cartesConstruites) {
            switch (c.getType()) {
                case "Structure":
                    batMateriaux += 1;
                    break;
                case "Vehicule":
                    batEnergies += 1;
                    break;
                case "Recherche":
                    batSciences += 1;
                    break;
                case "Projet":
                    batOr += 1;
                    break;
                case "Decouverte":
                    batExplo += 1;
                    break;
                default:
                    break;
            }
        }

        // Update statistics based on the counts of different types of buildings.
        this.batimentsMateriauTermines += batMateriaux;
        this.batimentsEnergieTermines += batEnergies;
        this.batimentsScienceTermines += batSciences;
        this.batimentsOrTermines += batOr;
        this.batimentsExploitationTermines += batExplo;
    }

    /**
     * Generates statistics related to the harvested cubes based on the specified
     * resource type.
     *
     * @param ressource The type of resource for which statistics are generated.
     */
    public void genereStatCubes(String ressource) {
        // Increment the count of harvested cubes based on the specified resource type.
        switch (ressource) {
            case "materiau":
                this.cubesMateriauRecoltes += 1;
                break;
            case "energie":
                this.cubesEnergieRecoltes += 1;
                break;
            case "science":
                this.cubesScienceRecoltes += 1;
                break;
            case "or":
                this.cubesOrRecoltes += 1;
                break;
            case "exploration":
                this.cubesExploitationRecoltes += 1;
                break;
            default:
                break;
        }
    }

    /**
     * Gets the total number of games won.
     *
     * @return The total number of games won.
     */
    public int getNbrPartiesGagnees() {
        return nbrPartiesGagnees;
    }

    /**
     * Adds the specified number of won games to the total.
     *
     * @param nbrPartiesGagnees The number of won games to be added.
     */
    public void addNbrPartiesGagnees(int nbrPartiesGagnees) {
        this.nbrPartiesGagnees += nbrPartiesGagnees;
    }

    /**
     * Adds the specified number of constructed buildings to the total count.
     *
     * @param b The number of buildings constructed to be added.
     */
    public void addBatimentsConstruits(int b) {
        this.batimentsConstruits += b;
    }

    /**
     * Adds the specified number of buildings with material completed to the total
     * count.
     *
     * @param b The number of buildings with material completed to be added.
     */
    public void addBatimentsMateriauTermines(int b) {
        this.batimentsMateriauTermines += b;
    }

    /**
     * Adds the specified number of buildings with energy completed to the total
     * count.
     *
     * @param b The number of buildings with energy completed to be added.
     */
    public void addBatimentsEnergieTermines(int b) {
        this.batimentsEnergieTermines += b;
    }

    /**
     * Adds the specified number of buildings with science completed to the total
     * count.
     *
     * @param b The number of buildings with science completed to be added.
     */
    public void addBatimentsScienceTermines(int b) {
        this.batimentsScienceTermines += b;
    }

    /**
     * Adds the specified number of buildings with gold completed to the total
     * count.
     *
     * @param b The number of buildings with gold completed to be added.
     */
    public void addBatimentsOrTermines(int b) {
        this.batimentsOrTermines += b;
    }

    /**
     * Adds the specified number of buildings with exploitation completed to the
     * total count.
     *
     * @param b The number of buildings with exploitation completed to be added.
     */
    public void addBatimentsExploitationTermines(int b) {
        this.batimentsExploitationTermines += b;
    }

    /**
     * Adds the specified number of harvested material cubes to the total count.
     *
     * @param c The number of harvested material cubes to be added.
     */
    public void addCubesMateriauRecoltes(int c) {
        this.cubesMateriauRecoltes += c;
    }

    /**
     * Adds the specified number of harvested energy cubes to the total count.
     *
     * @param c The number of harvested energy cubes to be added.
     */
    public void addCubesEnergieRecoltes(int c) {
        this.cubesEnergieRecoltes += c;
    }

    /**
     * Adds the specified number of harvested science cubes to the total count.
     *
     * @param c The number of harvested science cubes to be added.
     */
    public void addCubesScienceRecoltes(int c) {
        this.cubesScienceRecoltes += c;
    }

    /**
     * Adds the specified number of harvested gold cubes to the total count.
     *
     * @param c The number of harvested gold cubes to be added.
     */
    public void addCubesOrRecoltes(int c) {
        this.cubesOrRecoltes += c;
    }

    /**
     * Adds the specified number of harvested exploitation cubes to the total count.
     *
     * @param c The number of harvested exploitation cubes to be added.
     */
    public void addCubesExploitationRecoltes(int c) {
        this.cubesExploitationRecoltes += c;
    }

}
