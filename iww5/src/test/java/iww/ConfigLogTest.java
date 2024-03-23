package iww;

import java.util.logging.Logger;

import org.junit.Test;

public class ConfigLogTest {
    @Test
    public void testSetup() {
        // Appeler la méthode setup de ConfigLog pour configurer les journaux
        ConfigLog.setupTest();

        // Utiliser le logger par défaut pour enregistrer quelques messages
        Logger logger = Logger.getLogger("");
        logger.info("Ceci est un message d'information.");
        logger.warning("Ceci est un avertissement.");
        logger.severe("Ceci est une erreur grave.");
    }
}
