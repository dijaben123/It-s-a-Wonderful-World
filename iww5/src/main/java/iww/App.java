package iww;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The App class contains the main method to launch the game.
 * It prompts the user to input the number of players and their levels, and the number of games to be played.
 * It then creates a new instance of the Partie class and launches the game for the specified number of games.
 */
public class App {

    private final static Logger LOGGER = Logger.getLogger("");

    /**
     * Default constructor for the App class.
     * This constructor seems to be the default or no-argument constructor for the App class.
     * No specific initialization or configuration logic is present in the provided code.
     */
    public App() {}

    /**
     * The main class of the game. It allows the user to input the number of players and their levels, as well as the number of games to be played.
     * It then creates a new game with the specified number of players and launches it for the specified number of games.
     * @param args the command line arguments, where the first argument is the number of players and the second argument is the number of games to be played.
     */
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Scanner sc = new Scanner(System.in);
        int a = 0;
        String[] couleurStrings1 = { "\033[38;2;255;0;0m", "\033[38;2;0;0;255m", "\033[38;2;0;255;0m", "\033[38;2;255;255;0m", "\033[38;2;255;0;255m" };
        String[] couleurStrings2 = { "\033[38;2;3;90;125m", "\033[38;2;170;212;208m", "\033[38;2;150;188;92m", "\033[38;2;224;183;108m", "\033[38;2;247;226;139m" };
        String[] couleurStrings3 = { "\u001B[37m", "\u001B[37m", "\u001B[37m", "\u001B[37m", "\u001B[37m" };

        String[] couleurString = couleurStrings1;

        int nbParties = -1;
        // Vérification des arguments de ligne de commande pour le nombre de joueurs.
        if (args.length > 0) {
            if (args[0].matches("\\d+")) {
                a = Integer.parseInt(args[0]);
                if (a < 1 || a > 5) {
                    System.out.println("Le nombre de joueurs doit etre entre 1 et 5");
                }
            } else {
                System.out.println("L'argument n'est pas un entier.");
            }
            if (args.length == 2) {
                if (args[1].matches("\\d+")) {
                    nbParties = Integer.parseInt(args[1]);
                    if (nbParties == -1) {
                        System.out.println("le nombre de partie doit être positif");
                    }
                } else {
                    System.out.println("Le nombre de partie doit etre un nombre positif");
                }
            }

        }
        int c = 0;
        //choix de la palette de couleur
        while (c < 1 || c > 3) {
            try {
                System.out.print("Quelle palette voulez vous utiliser?\n");
                System.out.print("1 : "+couleurStrings1[0]+"Bot1 "+couleurStrings1[1]+"Bot2 "+couleurStrings1[2]
                +"Bot3 "+couleurStrings1[3]+"Bot4 "+couleurStrings1[4]+"Bot5"+"\u001B[37m"+"\n");
                System.out.print("2 : "+couleurStrings2[0]+"Bot1 "+couleurStrings2[1]+"Bot2 "+couleurStrings2[2]
                +"Bot3 "+couleurStrings2[3]+"Bot4 "+couleurStrings2[4]+"Bot5"+"\u001B[37m"+"\n");
                System.out.println("3 : "+couleurStrings3[0]+"Bot1 "+couleurStrings3[1]+"Bot2 "+couleurStrings3[2]
                +"Bot3 "+couleurStrings3[3]+"Bot4 "+couleurStrings3[4]+"Bot5"+"\u001B[37m"+"\n");
                c = sc.nextInt();
                if (c < 1 || c > 3) {
                    System.out.print("Cette palette n'existe pas.\n");
                }
            } catch (Exception e) {
                System.out.print("Erreur de saisie\n");
                sc.next();
            }
        }
        if (c == 1) {
            couleurString = couleurStrings1;
        } else {
            if (c ==2){
                couleurString = couleurStrings2;
            }else {
                couleurString = couleurStrings3;
            }
        }
        // a=0;
        // Saisie du nombre de joueurs jusqu'à ce qu'un nombre valide soit entré.
        while (a < 1 || a > 5) {
            try {
                System.out.print("Nombre de joueur (1-5) ? ");
                a = sc.nextInt();
                if (a < 1 || a > 5) {
                    System.out.print("Nombre de joueurs incorrecte.\n");
                }
            } catch (Exception e) {
                System.out.print("Erreur de saisie\n");
                sc.next();
            }
        }
        System.out.print("nb joueur : " + a + "\n");
        Joueur[] tableauJoueur = new Joueur[a];
        for (int i = 1; i <= a; i++) {
            int lvl;
            while (true) {
                System.out.print("Quel est le niveau du bot " + i + "? (1 = Random, 2 = Moyen, 3 = Expert) ");
                String input = sc.next();
                if (input.equals("1") || input.equals("2") || input.equals("3")) {
                    lvl = Integer.parseInt(input);
                    break; // Sortir de la boucle si lvl est 1 ou 2
                } else {
                    System.out.println("Veuillez saisir une valeur valide (1 , 2 ou 3).");
                }
            }
            tableauJoueur[i - 1] = new Joueur("bot" + i, lvl, couleurString[i-1]);
        }
        // Affichage des informations sur les joueurs.
        for (Joueur joueur : tableauJoueur) {
            System.out.print("Le nom " + joueur.getNom() + " Le niveau " + joueur.getNiveau() + "\n");
        }
        // Saisie du nombre de parties jusqu'à ce qu'un nombre positif soit entré.
        while (nbParties < 0) {
            try {
                System.out.print("Nombre de parties ? ");
                nbParties = sc.nextInt();
                if (nbParties < 0) {
                    System.out.print("Nombre de incorrecte.\n");
                }
            } catch (Exception e) {
                System.out.print("Erreur de saisie\n");
                sc.next();
            }
        }
        int multiThreads = -1;
        if(nbParties > 1){
            while (multiThreads < 0 || multiThreads >1) {
                try {
                    System.out.print("Lancer les parties a la suite ou en paralleles ? ");
                    System.out.print("[0] a la suite [1] en paralleles");
                    multiThreads = sc.nextInt();
                } catch (Exception e) {
                    System.out.print("Erreur de saisie\n");
                    sc.next();
                }
            }
        }
        if(nbParties==1 || multiThreads==0 ) lancementPartiesALaSuite(tableauJoueur, nbParties);
        if(multiThreads == 1) lancementPartiesParalleles(tableauJoueur,nbParties,couleurString,a);
        sc.close();

        for(Joueur joueur : tableauJoueur){
            System.out.println("Nombre de victoires du joueur "+ joueur.getNom()+" niveau "+ joueur.getNiveau()+" : "+joueur.getStatistiques().getNbrPartiesGagnees());
        }

        System.out.println("\nPlus de statistiques dans le fichier excel : Resultats, se trouvant dans le dossier iww5\n");
        Excel.EcrireFichier(tableauJoueur, "Resultat.xlsx");

    }

    private static void lancementPartiesALaSuite(Joueur[] tableauJoueur, int nbParties){
        Partie partie = new Partie(tableauJoueur);
        Handler[] handlers = LOGGER.getHandlers();
        System.out.println();
        PrintStream originalOut = System.out;
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);
        }
        int nbpartie =0;
        for (int i = 0; i < nbParties; i++) {
            nbpartie = i+1;
            if(i==nbParties-1) ConfigLog.setup();
            if(nbParties <101 && nbParties >1){
                    System.setOut(originalOut);
                    System.out.println("lancement partie : "+ nbpartie);
                    System.setOut(new PrintStream(new NullOutputStream()));
            }
            if(nbParties >100){
                System.setOut(originalOut);
                if(nbpartie == 1){
                    System.out.println("lancement partie : "+ nbpartie);
                }
                if(nbParties == nbpartie){
                    System.out.println("lancement partie : "+ nbpartie);
                }
                System.setOut(new PrintStream(new NullOutputStream()));
            }

            LOGGER.setLevel(Level.INFO);
            // Lancement des parties.
            LOGGER.info("Partie n°"+nbpartie+"\n");
            partie.lancerPartie();
            partie.resultat();
        }

        System.setOut(originalOut);
    }

    private static void lancementPartiesParalleles(Joueur[] tableauJoueur, int nbParties, String[] couleurString, int a){
        //Partie partie = new Partie(tableauJoueur);
        Handler[] handlers = LOGGER.getHandlers();
        System.out.println();
        PrintStream originalOut = System.out;
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);
        }
        final int nbPartiesPourThread = nbParties;
        final String[] couleurPourThread = couleurString;
        final int  aThread= a;
        final ThreadLocal<Joueur[]> threadLocalTableauJoueur = ThreadLocal.withInitial(() -> new Joueur[aThread]);
        Thread[] gameThreads = new Thread[nbParties];
        for (int i = 0; i < nbParties; i++) {
            // Lancement des parties.
            int nbpartie = i+1;
            LOGGER.info("Partie n°"+nbpartie+"\n");
            Thread gameThread = new Thread(() -> {
                //if(nbpartie == nbParties-1) ConfigLog.setup();
                LOGGER.setLevel(Level.INFO);
                Joueur[] tableauJoueurThread = threadLocalTableauJoueur.get();

                for (int j = 1; j <= aThread; j++) {
                    tableauJoueurThread[j - 1] = new Joueur("bot" + j, tableauJoueur[j-1].getNiveau(), couleurPourThread[j-1]);
                }
                Partie partie = new Partie(tableauJoueurThread);

                if(nbParties <101 && nbParties >1){
                        System.setOut(originalOut);
                        System.out.println("------- Game Thread " + nbpartie + " is running on Thread ID: " + Thread.currentThread().getId());
                        System.setOut(new PrintStream(new NullOutputStream()));
                }
                if(nbParties >100){
                    System.setOut(originalOut);
                    if(nbpartie == 1){
                        System.out.println("------- Game Thread " + nbpartie + " is running on Thread ID: " + Thread.currentThread().getId());
                    }
                    if(nbParties == nbpartie){
                        System.out.println("------- Game Thread " + nbpartie + " is running on Thread ID: " + Thread.currentThread().getId());
                    }
                    System.setOut(new PrintStream(new NullOutputStream()));
                }
                partie.lancerPartie();
                partie.resultat();
                for(int k = 0; k < tableauJoueur.length; k++){
                    tableauJoueur[k].getStatistiques().addNbrPartiesGagnees(tableauJoueurThread[k].getStatistiques().getNbrPartiesGagnees());
                    tableauJoueur[k].getStatistiques().addResultatPartie(tableauJoueurThread[k].getStatistiques().getResultatPartie());
                    tableauJoueur[k].getStatistiques().addPtsBatiments(tableauJoueurThread[k].getStatistiques().getPtsBatiments());
                    tableauJoueur[k].getStatistiques().addPtsJetons(tableauJoueurThread[k].getStatistiques().getPtsJetons());
                    tableauJoueur[k].getStatistiques().addPtsEmpire(tableauJoueurThread[k].getStatistiques().getPtsEmpire());
                    tableauJoueur[k].getStatistiques().addCarteRecycles(tableauJoueurThread[k].getStatistiques().getCarteRecycles());
                    tableauJoueur[k].getStatistiques().addCarteDefaussees(tableauJoueurThread[k].getStatistiques().getCarteDefaussees());
                    tableauJoueur[k].getStatistiques().addBatimentsConstruits(tableauJoueurThread[k].getStatistiques().getBatimentsConstruits());
                    tableauJoueur[k].getStatistiques().addBatimentsMateriauTermines(tableauJoueurThread[k].getStatistiques().getBatimentsMateriauTermines());
                    tableauJoueur[k].getStatistiques().addBatimentsEnergieTermines(tableauJoueurThread[k].getStatistiques().getBatimentsEnergieTermines());
                    tableauJoueur[k].getStatistiques().addBatimentsScienceTermines(tableauJoueurThread[k].getStatistiques().getBatimentsScienceTermines());
                    tableauJoueur[k].getStatistiques().addBatimentsOrTermines(tableauJoueurThread[k].getStatistiques().getBatimentsOrTermines());
                    tableauJoueur[k].getStatistiques().addBatimentsExploitationTermines(tableauJoueurThread[k].getStatistiques().getBatimentsExploitationTermines());
                    tableauJoueur[k].getStatistiques().addCubesMateriauRecoltes(tableauJoueurThread[k].getStatistiques().getCubesMateriauRecoltes());
                    tableauJoueur[k].getStatistiques().addCubesEnergieRecoltes(tableauJoueurThread[k].getStatistiques().getCubesEnergieRecoltes());
                    tableauJoueur[k].getStatistiques().addCubesScienceRecoltes(tableauJoueurThread[k].getStatistiques().getCubesScienceRecoltes());
                    tableauJoueur[k].getStatistiques().addCubesOrRecoltes(tableauJoueurThread[k].getStatistiques().getCubesOrRecoltes());
                    tableauJoueur[k].getStatistiques().addCubesExploitationRecoltes(tableauJoueurThread[k].getStatistiques().getCubesExploitationRecoltes());
                    tableauJoueur[k].getStatistiques().addCubesKrystalliumRecoltes(tableauJoueurThread[k].getStatistiques().getCubesKrystalliumRecoltes());
                    tableauJoueur[k].getStatistiques().addJetonsFinanceRecoltes(tableauJoueurThread[k].getStatistiques().getJetonsFinanceRecoltes());
                    tableauJoueur[k].getStatistiques().addJetonsGenerauxRecoltes(tableauJoueurThread[k].getStatistiques().getJetonsGenerauxRecoltes());
               }
            });

            gameThreads[i] = gameThread;

        }

        // Start all threads outside the loop
        for (Thread gameThread : gameThreads) {
            gameThread.start();
        }
        // Wait for all threads to finish
        for (Thread gameThread : gameThreads) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.setOut(originalOut);
    }
}
