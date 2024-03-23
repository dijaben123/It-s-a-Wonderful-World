package iww;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConstructionTest {
    private Joueur joueur;
    private ArrayList<Carte> cartes;

    @Before
    public void setUp() {

        joueur = new Joueur("TestPlayer", 1,"");
        cartes = new ArrayList<Carte>();

        LinkedHashMap<String, Integer> coutProd1 = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst1 = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec1 = new LinkedHashMap<>();
        LinkedHashMap<String, String> production1 = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire1 = new LinkedHashMap<>();

        coutProd1.put("krystallium", 1);
        bonusConst1.put("general", 1);
        bonusRec1.put("energie", 2);
        production1.put("or", "materiau");
        pVictoire1.put("exploration", 5);

        Carte carte1 = new Carte("Carte1", "Type1", 1, coutProd1, bonusConst1, bonusRec1, production1, pVictoire1);
        Carte carte2 = new Carte("Carte2", "Type2", 2, coutProd1, bonusConst1, bonusRec1, production1, pVictoire1);
        cartes.add(carte1);
        cartes.add(carte2);
        Map<String, Integer> resources = joueur.getRessources();
        resources.put("materiau", 0);
        resources.put("energie", 0);
    }

    @Test
    public void testDistributionKrystallium() {
        for(int i = 0; i <5;i++) {joueur.stockRessource();}
        joueur.ajouteEnConstruction(cartes.get(0));
        assertEquals(1, joueur.getKrystallium());
        if(Construction.distributionKrystallium(joueur)) assertEquals(0, joueur.getKrystallium());
        else assertEquals(1, joueur.getKrystallium());
    }

    @Test
    public void testDistribution() {
        Construction.distribution(cartes, "materiau", 2, joueur);
        assertEquals(1, joueur.getRessources().get("materiau").intValue());
        assertEquals(0, joueur.getEnConstruction().size());
    }
}
