package iww;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class DefausseTest {

    @Test
    public void testDefausseEnConstruction() {
        ArrayList<Carte> pioche = GenerateurCarte.genPioche();
        int tailleAvantDefause = pioche.size();
        Joueur j = new Joueur("a", 1,"");
        for(int i=0; i<10; i++){
            j.getEnConstruction().add(pioche.get(i));
        }
        for(int i=0; i<20; i++){
            Defausse.defausseEnConstruction(j,4);
        }
        assertNotEquals(tailleAvantDefause, j.getEnConstruction().size());

    }
}
