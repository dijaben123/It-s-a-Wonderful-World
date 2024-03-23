package iww;

import org.junit.Test;

public class ExcelTest {
    @Test
    public void testEcrireFichier() {
        // Sample data for testing
        Joueur[] joueurs = {
                new Joueur("Joueur1",12,""),
                new Joueur("Joueur2", 80,""),
                 new Joueur("Joueur3", 90,"")
        };

        // Call the method to write the Excel file
        Excel.EcrireFichier(joueurs, "testJoueurs.xlsx");
    }
}
