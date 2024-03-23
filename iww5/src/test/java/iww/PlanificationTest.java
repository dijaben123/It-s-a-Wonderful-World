package iww;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanificationTest {

    private Planification planification;
    private Joueur joueur1;
    private Joueur joueur2;

    /**
     * Sets up the test fixture.
     *
     * This method is called before a test is executed.
     * It initializes the players and the planning for the test.
     */
    @Before
    public void setUp() {
        joueur1 = new Joueur("Joueur1", 1, "");
        joueur2 = new Joueur("Joueur2", 1, "");
        Joueur[] joueurs = { joueur1, joueur2 };
        planification = new Planification(joueurs);
    }

    /**
     * Tests the startPlanification method of the Planification class.
     * It verifies that the mainJ list of both players is empty after the
     * planification starts.
     */
    @Test
    public void testStartPlanification() {
        // Assurez-vous que la mainJ est vide au début
        assertEquals(0, joueur1.getMainJ().size());
        assertEquals(0, joueur2.getMainJ().size());

        // Exécutez la planification
        planification.startPlanification(4);

        // Assurez-vous que la mainJ est toujours vide après la planification
        assertEquals(0, joueur1.getMainJ().size());
        assertEquals(0, joueur2.getMainJ().size());

        // Assurez-vous que les cartes sont correctement ajoutées aux constructions en
        // cours
        // (vous devrez peut-être adapter cela en fonction de votre implémentation)
        assertEquals(0, joueur1.getEnConstruction().size());
        assertEquals(0, joueur2.getEnConstruction().size());

    }

    @Test
    public void testStartPlanificationUnJoueur() {
        ArrayList<Carte> pioche = GenerateurCarte.genPioche();
        ArrayList<List<Carte>> paquets = new ArrayList<List<Carte>>();
        paquets = GenerateurCarte.creationPaquets(pioche);
        planification.startPlanificationUnJoueur(paquets, pioche, 4);

        // Assurez-vous que la mainJ est toujours vide après la planification
        assertEquals(0, joueur1.getMainJ().size());

        // Assurez-vous que les cartes sont correctement ajoutées aux constructions en
        // cours
        // (vous devrez peut-être adapter cela en fonction de votre implémentation)
        assertNotEquals(0, joueur1.getEnConstruction().size());
    }

}
