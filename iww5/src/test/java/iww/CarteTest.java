package iww;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CarteTest {

    private Carte uneCarte;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();

        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);


        uneCarte = new Carte("Agence d'Espionnage", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire);
        // ArrayList<Carte> pioches = GenerateurCarte.genPioche();
        // uneCarte = pioches.get(0);
    }

    @Test
    public void testGetIndex() {
        int index = uneCarte.getIndex();
        assertEquals(1, index);
    }

    /**
     * Test case for the getNom() method of the Carte class.
     * Verifies that the method returns the correct name of the card.
     */
    @Test
    public void testGetNom() {
        String nom = uneCarte.getNom();
        assertEquals("Agence d'Espionnage", nom);
    }

    @Test
    public void testGetType() {
        String type = uneCarte.getType();
        assertEquals("Projet", type);
    }

    /**
     * Test case for the getCoutProd() method of the Carte class.
     * Verifies that the method returns the correct production cost of the card.
     */
    @Test
    public void testGetCoutProd() {
        Map<String, Integer> coutProd = uneCarte.getCoutProd();
        assertTrue(coutProd.containsKey("energie"));
        assertEquals(2, coutProd.get("energie").intValue());
    }

    // @Test
    // public void testProduction() {
    //     Map<String, String> bonusProd = uneCarte.getProduction();
    //     assertTrue(bonusProd.containsKey("exploration"));
    //     assertEquals(2, bonusProd.get("exploration")());
    // }


    /**
     * Test case for the getBonusRec() method of the Carte class.
     * Verifies that the method returns the correct resource bonus of the card.
     */
    @Test
    public void testGetBonusRec() {
        Map<String, Integer> bonusRec = uneCarte.getBonusRec();
        assertTrue(bonusRec.containsKey("exploration"));
        assertEquals(1, bonusRec.get("exploration").intValue());
    }

    /**
     * Test case for the getProduction() method of the Carte class.
     * Verifies that the method returns the correct production bonus of the card.
     */
    @Test
    public void testGetbonusConst() {
        Map<String, Integer> bonusConst = uneCarte.getBonusConst();
        assertEquals(new LinkedHashMap<>(), bonusConst);
    }

    @Test
    public void testGetPointVictoire(){
        Map<String, Integer> pVictoire = uneCarte.getpVictoire();
        assertTrue(pVictoire.containsKey("."));
        assertEquals(1, pVictoire.get(".").intValue());
    }

}