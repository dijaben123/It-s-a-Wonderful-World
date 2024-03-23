package iww;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DraftTest {

    private Draft draft;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueur3;
    private ArrayList<Carte> pioche;

    @Before
    public void setUp() {
        joueur1 = new Joueur("Joueur 1", 1,"");
        joueur2 = new Joueur("Joueur 2", 1,"");
        joueur3 = new Joueur("Joueur 3", 1,"");

        pioche = GenerateurCarte.genPioche();

        draft = new Draft(6, new Joueur[]{joueur1, joueur2, joueur3}, pioche);
    }

    @Test
    public void testChangement() {
        ArrayList<Carte> mainJ1AvantChangement = joueur1.getMainJ();
        ArrayList<Carte> mainJ2AvantChangement = joueur2.getMainJ();
        ArrayList<Carte> mainJ3AvantChangement = joueur3.getMainJ();

        draft.changement(new Joueur[]{joueur1, joueur2,joueur3}, 1);

        assertEquals(mainJ3AvantChangement,joueur1.getMainJ());
        assertEquals(mainJ2AvantChangement,joueur3.getMainJ());
        assertEquals(mainJ1AvantChangement,joueur2.getMainJ());

    }

    @Test
    public void testChoisirCarteLaPlusProductive() {

        ArrayList<Empire> empires = GenerateurCarte.genCartesEmpire();
        joueur1.setEmpire(empires.get(0)); 
        ArrayList<Carte> pioche = GenerateurCarte.genPioche();
        joueur1.getPioche().add(pioche.get(0));
        joueur1.getPioche().add(new Carte("Carte2", "Type2", 2, new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>()));
        joueur1.getPioche().add(new Carte("Carte3", "Type3", 3, new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>()));

        Carte carteLaPlusProductive = draft.choisirCarteLaPlusProductive(joueur1);

        assertNotEquals("Carte2", carteLaPlusProductive.getNom());
        assertNotEquals("Cart3", carteLaPlusProductive.getNom());
    }

    @Test
    public void testLancementDraft() {
        draft.lancementDraft();

        assertEquals(7, joueur1.getMainJ().size());
        assertEquals(7, joueur2.getMainJ().size());
        assertEquals(7, joueur3.getMainJ().size());

    }
    @Test
    public void testLancementDraft2J() {
        draft.lancementDraft2J();
        assertEquals(7, joueur1.getMainJ().size());
        assertEquals(7, joueur2.getMainJ().size());
    }
}
