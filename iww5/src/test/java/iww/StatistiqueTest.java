package iww;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class StatistiqueTest {

    Statistique statistique = new Statistique();

    @Test
    public void testAddCarteDefaussees() {

        statistique.addCarteDefaussees(2);
        assertEquals(2, statistique.getCarteDefaussees());
    }

    @Test
    public void testAddCarteRecycles() {
        statistique.addCarteRecycles(3);
        assertEquals(3, statistique.getCarteRecycles());
    }

    @Test
    public void testAddCubesKrystalliumRecoltes() {
        statistique.addCubesKrystalliumRecoltes(5);
        assertEquals(5, statistique.getCubesKrystalliumRecoltes());
    }

    @Test
    public void testAddJetonsFinanceRecoltes() {
        statistique.addJetonsFinanceRecoltes(8);
        assertEquals(8, statistique.getJetonsFinanceRecoltes());
    }

    @Test
    public void testAddJetonsGenerauxRecoltes() {
        statistique.addJetonsGenerauxRecoltes(8);
        assertEquals(8, statistique.getJetonsGenerauxRecoltes());
    }

    @Test
    public void testAddPtsBatiments() {
        statistique.addPtsBatiments(8);
        assertEquals(8, statistique.getPtsBatiments());
    }

    @Test
    public void testAddPtsEmpire() {
        statistique.addPtsEmpire(8);
        assertEquals(8, statistique.getPtsEmpire());
    }

    @Test
    public void testAddPtsJetons() {
        statistique.addPtsJetons(8);
        assertEquals(8, statistique.getPtsJetons());
    }

    @Test
    public void testAddResultatPartie() {
        statistique.addResultatPartie(8);
        assertEquals(8, statistique.getResultatPartie());
    }

    @Test
    public void testGenereStatCubes() {
        statistique.genereStatCubes("materiau");
        statistique.genereStatCubes("energie");
        statistique.genereStatCubes("science");

        assertEquals(1, statistique.getCubesMateriauRecoltes());
        assertEquals(1, statistique.getCubesEnergieRecoltes());
        assertEquals(1, statistique.getCubesScienceRecoltes());
    }

    @Test
    public void testGenereStatsBatiments() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);



        cartesConstruites.add(new Carte("a", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("b", "Structure",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("c", "Vehicule",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("e", "Recherche",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("f", "Decouverte",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(5, statistique.getBatimentsConstruits());
        assertEquals(1, statistique.getBatimentsMateriauTermines());
        assertEquals(1, statistique.getBatimentsEnergieTermines());
        assertEquals(1, statistique.getBatimentsScienceTermines());
        assertEquals(1, statistique.getBatimentsOrTermines());
        assertEquals(1, statistique.getBatimentsExploitationTermines());
    }

    @Test
    public void testGetBatimentsConstruits() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("a", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("b", "Structure",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("c", "Vehicule",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("e", "Recherche",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("f", "Decouverte",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(5, statistique.getBatimentsConstruits());
    }

    @Test
    public void testGetBatimentsEnergieTermines() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("c", "Vehicule",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(1, statistique.getBatimentsEnergieTermines());
    }

    @Test
    public void testGetBatimentsExploitationTermines() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("f", "Decouverte",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(1, statistique.getBatimentsExploitationTermines());
    }

    @Test
    public void testGetBatimentsMateriauTermines() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("a", "Structure",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(1, statistique.getBatimentsMateriauTermines());
    }

    @Test
    public void testGetBatimentsOrTermines() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("a", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(1, statistique.getBatimentsOrTermines());
    }

    @Test
    public void testGetBatimentsScienceTermines() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("e", "Recherche",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(1, statistique.getBatimentsScienceTermines());
    }

    @Test
    public void testGetCarteDefaussees() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("a", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("b", "Structure",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("c", "Vehicule",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("e", "Recherche",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("f", "Decouverte",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(5, statistique.getBatimentsConstruits());
        Joueur j = new Joueur("a", 1,"");
        j.setStatistique(statistique);
        j.RecyclCarte(cartesConstruites.get(0), false);
        j.RecyclCarte(cartesConstruites.get(1), false);
        j.RecyclCarte(cartesConstruites.get(3), false);
        assertEquals(3, statistique.getCarteDefaussees());
        assertEquals(0, statistique.getCarteRecycles());

    }

    @Test
    public void testGetCarteRecycles() {
        ArrayList<Carte> cartesConstruites = new ArrayList<>();
        LinkedHashMap<String, Integer> coutProd = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusConst = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> bonusRec = new LinkedHashMap<>();
        LinkedHashMap<String, String> production = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pVictoire = new LinkedHashMap<>();
        coutProd.put("energie", 2);
        bonusRec.put("exploration", 1);
        production.put("exploration", "2");
        pVictoire.put(".", 1);

        cartesConstruites.add(new Carte("a", "Projet",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("b", "Structure",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("c", "Vehicule",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("e", "Recherche",1, coutProd, bonusConst, bonusRec, production, pVictoire));
        cartesConstruites.add(new Carte("f", "Decouverte",1, coutProd, bonusConst, bonusRec, production, pVictoire));

        statistique.genereStatsBatiments(cartesConstruites);
        assertEquals(5, statistique.getBatimentsConstruits());
        Joueur j = new Joueur("a", 1,"");
        j.setStatistique(statistique);
        j.RecyclCarte(cartesConstruites.get(2), true);
        j.RecyclCarte(cartesConstruites.get(1), true);
        j.RecyclCarte(cartesConstruites.get(3), true);
        assertEquals(3, statistique.getCarteRecycles());
    }

    @Test
    public void testGetCubesEnergieRecoltes() {
        statistique.genereStatCubes("energie");

        assertEquals(1, statistique.getCubesEnergieRecoltes());
    }

    @Test
    public void testGetCubesExploitationRecoltes() {
        statistique.genereStatCubes("exploration");

        assertEquals(1, statistique.getCubesExploitationRecoltes());
    }

    @Test
    public void testGetCubesKrystalliumRecoltes() {
        Joueur j = new Joueur("a",1,"");
        for(int i=0; i<5; i++) j.stockRessource();
        assertEquals(1, j.getStatistiques().getCubesKrystalliumRecoltes());
    }

    @Test
    public void testGetCubesMateriauRecoltes() {
        statistique.genereStatCubes("materiau");

        assertEquals(1, statistique.getCubesMateriauRecoltes());
    }

    @Test
    public void testGetCubesOrRecoltes() {
        statistique.genereStatCubes("or");

        assertEquals(1, statistique.getCubesOrRecoltes());
    }

    @Test
    public void testGetCubesScienceRecoltes() {
        statistique.genereStatCubes("science");

        assertEquals(1, statistique.getCubesScienceRecoltes());
    }

    @Test
    public void testGetJetonsFinanceRecoltes() {
        statistique.addJetonsFinanceRecoltes(5);

        assertEquals(5, statistique.getJetonsFinanceRecoltes());
    }

    @Test
    public void testGetJetonsGenerauxRecoltes() {
        statistique.addJetonsGenerauxRecoltes(5);

        assertEquals(5, statistique.getJetonsGenerauxRecoltes());
    }

    @Test
    public void testGetPtsBatiments() {
        statistique.addPtsBatiments(4);

        assertEquals(4, statistique.getPtsBatiments());
    }

    @Test
    public void testGetPtsEmpire() {
        statistique.addPtsEmpire(6);;

        assertEquals(6, statistique.getPtsEmpire());
    }

    @Test
    public void testGetPtsJetons() {
        statistique.addPtsJetons(2);;

        assertEquals(2, statistique.getPtsJetons());
    }

    @Test
    public void testGetResultatPartie() {
        statistique.addResultatPartie(55);

        assertEquals(55, statistique.getResultatPartie());
    }

    @Test
    public void testGetNbrPartiesGagnees() {
        statistique.addNbrPartiesGagnees(55);

        assertEquals(55, statistique.getNbrPartiesGagnees());
    }

}
