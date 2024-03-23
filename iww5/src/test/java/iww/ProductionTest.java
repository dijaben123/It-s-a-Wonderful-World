package iww;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class ProductionTest {

    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur[] joueurs;

    @Before
    public void setUp() {
        joueur1 = new Joueur("Joueur 1", 1,"");
        joueur2 = new Joueur("Joueur 2", 1,"");
        joueurs = new Joueur[]{joueur1, joueur2};
    }

    @Test
    public void testStartProduction() {

        ArrayList<Carte> pioche = GenerateurCarte.genPioche();

        joueur1.setEmpire(new Empire("a", new LinkedHashMap<>(), new LinkedHashMap<>()));
        joueur2.setEmpire(new Empire("a", new LinkedHashMap<>(), new LinkedHashMap<>()));

        for(Carte c : pioche){
            if(c.getNom().equals("Eoliennes")) joueur1.getCartesConstruites().add(c);
            if(c.getNom().equals("Complexe Industriel")) joueur2.getCartesConstruites().add(c);
        }
        Production production = new Production(joueurs);

        production.startProduction(4);

        assertEquals(7, joueur1.getStatistiques().getCubesEnergieRecoltes());
        assertEquals(6, joueur2.getStatistiques().getCubesMateriauRecoltes());
    }

}

