package iww;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class PartieTest {
    private Joueur[] joueurs;
    private Partie partie;

    /**
     * Sets up the test fixture. Called before every test method.
     */
    @Before
    public void setUp() {
        joueurs = new Joueur[] {
            new Joueur("Joueur1", 1,""),
            new Joueur("Joueur2", 1,""),
            new Joueur("Joueur3", 1,"")
        };
        partie = new Partie(joueurs);
    }


    /**
     * Tests the Partie constructor and the getters.
     */

    @Test
    public void testNbJoueur() {
        assertEquals(3, joueurs.length);
    }   

}
