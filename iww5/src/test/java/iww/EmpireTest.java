package iww;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedHashMap;

public class EmpireTest {
    private Empire unEmpire;

    @Before
    public void setUp() {
        
        LinkedHashMap<String, Integer> ptsVictoire = new LinkedHashMap<>();
        ptsVictoire.put("financier", 10);
        ptsVictoire.put("general", 20);
        
        LinkedHashMap<String, Integer> productions = new LinkedHashMap<>();
        productions.put("energie", 1);

        unEmpire = new Empire("Mon Empire", ptsVictoire, productions);
    }

    @Test
    public void testGetNom() {
        assertEquals("Mon Empire", unEmpire.getNom());
    }

    @Test
    public void testGetPtsVictoire() {

        assertEquals(10, (int) unEmpire.getPtsVictoire().get("financier"));
        assertEquals(20, (int) unEmpire.getPtsVictoire().get("general"));
    }

    @Test
    public void testGetProductions() {
      
        assertEquals(1, (int) unEmpire.getProductions().get("energie"));
    }
}
