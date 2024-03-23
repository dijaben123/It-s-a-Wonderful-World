package iww;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.ArrayList;

public class GenerateurCarteTest {

    @Test
    public void testGenPioche() {
        ArrayList<Carte> pioche = GenerateurCarte.genPioche();
        assertNotNull(pioche);

        assertEquals(150, pioche.size());

    }

    @Test
    public void testGenCartesEmpire() {
        ArrayList<Empire> piocheEmpire = GenerateurCarte.genCartesEmpire();
        assertNotNull(piocheEmpire);

        assertEquals(5, piocheEmpire.size());

    }
}
