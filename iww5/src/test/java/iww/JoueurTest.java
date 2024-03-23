package iww;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

public class JoueurTest {
    private Joueur unJoueur;

    /**
     * Sets up the test fixture. Called before every test method.
     */
    @Before
    public void setUp() {
        unJoueur = new Joueur("test", 1,"");
    }

    /**
     * Tests the Joueur constructor and the getters.
     */
    @Test
    public void testJoueur() {
        assertEquals(1, unJoueur.getNiveau());
        assertEquals("test", unJoueur.getNom());
        assertEquals(0, unJoueur.getPioche().size());
        assertEquals(0, unJoueur.getMainJ().size());
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        unJoueur.setPioche(new ArrayList<Carte>(Arrays.asList(new Carte("test","1",1,coutProd,bonusConst,bonusRec,production,pVictoire), new Carte("test","1",1,coutProd,bonusConst,bonusRec,production,pVictoire), new Carte("test","1",1,coutProd,bonusConst,bonusRec,production,pVictoire))));
        assertEquals(3, unJoueur.getPioche().size() );
        assertEquals(0, unJoueur.getMainJ().size() );
        unJoueur.ajoutCarteMain(new Carte("test","1",1,coutProd,bonusConst,bonusRec,production,pVictoire));
    }

    @Test
    public void testAjoutCarteEnConstruction() {
        Carte carte = new Carte("TestCarte", "Type",1, new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>());
        unJoueur.ajouteEnConstruction(carte);
        ArrayList<Carte> cartesEnConstruction = unJoueur.getEnConstruction();
        assertTrue(cartesEnConstruction.contains(carte));
    }


    @Test
    public void testStockRessource() {
        // Appelez la méthode stockRessource pour augmenter le compteur de transformation
        unJoueur.stockRessource();

        // Vérifiez que le compteur a été augmenté
        assertEquals(1, unJoueur.getTransformation());

        // Appelez la méthode stockRessource plusieurs fois pour atteindre 5
        for (int i = 0; i < 4; i++) {
            unJoueur.stockRessource();
        }

        // Vérifiez que le Krystallium a été augmenté
        assertEquals(1, unJoueur.getKrystallium());
        // Vérifiez que le compteur de transformation a été réinitialisé
        assertEquals(0, unJoueur.getTransformation());
    }

}
