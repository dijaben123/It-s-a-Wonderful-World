package iww;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * This class contains methods to generate an excel
 */
public class Excel {

    /**
     * Constructs a new instance of the Excel class.
     * This class appears to handle Excel-related operations, possibly writing player data to an Excel file.
     * The constructor, as presented, has no explicit initialization or actions.
     */
    public Excel() {}

    /**
     * Writes player information to an Excel file.
     *
     * @param joueurs     Array of players whose information will be written to the file.
     * @param nomFichier  Name of the Excel file.
     */
    public static void EcrireFichier(Joueur[] joueurs, String nomFichier) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(nomFichier)) {
            Sheet sheet = workbook.createSheet("Joueurs");

            // En-têtes de colonnes
            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("Nom");
            headerRow.createCell(1).setCellValue("Niveau");
            headerRow.createCell(2).setCellValue("nbr de parties gagnees");
            headerRow.createCell(3).setCellValue("Score Total");
            headerRow.createCell(4).setCellValue("Points de Batiments");
            headerRow.createCell(5).setCellValue("Points de Jetons");
            headerRow.createCell(6).setCellValue("Points d'Empire");
            headerRow.createCell(7).setCellValue("Cartes Recyclees");
            headerRow.createCell(8).setCellValue("Cartes Defaussees");
            headerRow.createCell(9).setCellValue("Cartes Construites");
            headerRow.createCell(10).setCellValue("Cartes Structure Terminees");
            headerRow.createCell(11).setCellValue("Cartes Vehicule Terminees");
            headerRow.createCell(12).setCellValue("Cartes Recherche Terminees");
            headerRow.createCell(13).setCellValue("Cartes Projet Terminees");
            headerRow.createCell(14).setCellValue("Batiments Decouverte Terminees");
            headerRow.createCell(15).setCellValue("Cubes Materiaux Recoltes");
            headerRow.createCell(16).setCellValue("Cubes Energie Recoltes");
            headerRow.createCell(17).setCellValue("Cubes Science Recoltes");
            headerRow.createCell(18).setCellValue("Cubes Or Recoltes");
            headerRow.createCell(19).setCellValue("Cubes Exploration Recoltes");
            headerRow.createCell(20).setCellValue("Cubes Krystallium Recoltes");
            headerRow.createCell(21).setCellValue("Jetons Financier Recoltes");
            headerRow.createCell(22).setCellValue("Jetons General Recoltes");


            // Écrire les informations de chaque joueur dans des colonnes distinctes
            int rowIndex = 1;
            for (Joueur joueur : joueurs) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(joueur.getNom());
                row.createCell(1).setCellValue(joueur.getNiveau());
                row.createCell(2).setCellValue(joueur.getStatistiques().getNbrPartiesGagnees());
                row.createCell(3).setCellValue(joueur.getStatistiques().getResultatPartie());
                row.createCell(4).setCellValue(joueur.getStatistiques().getPtsBatiments());
                row.createCell(5).setCellValue(joueur.getStatistiques().getPtsJetons());
                row.createCell(6).setCellValue(joueur.getStatistiques().getPtsEmpire());
                row.createCell(7).setCellValue(joueur.getStatistiques().getCarteRecycles());
                row.createCell(8).setCellValue(joueur.getStatistiques().getCarteDefaussees());
                row.createCell(9).setCellValue(joueur.getStatistiques().getBatimentsConstruits());
                row.createCell(10).setCellValue(joueur.getStatistiques().getBatimentsMateriauTermines());
                row.createCell(11).setCellValue(joueur.getStatistiques().getBatimentsEnergieTermines());
                row.createCell(12).setCellValue(joueur.getStatistiques().getBatimentsScienceTermines());
                row.createCell(13).setCellValue(joueur.getStatistiques().getBatimentsOrTermines());
                row.createCell(14).setCellValue(joueur.getStatistiques().getBatimentsExploitationTermines());
                row.createCell(15).setCellValue(joueur.getStatistiques().getCubesMateriauRecoltes());
                row.createCell(16).setCellValue(joueur.getStatistiques().getCubesEnergieRecoltes());
                row.createCell(17).setCellValue(joueur.getStatistiques().getCubesScienceRecoltes());
                row.createCell(18).setCellValue(joueur.getStatistiques().getCubesOrRecoltes());
                row.createCell(19).setCellValue(joueur.getStatistiques().getCubesExploitationRecoltes());
                row.createCell(20).setCellValue(joueur.getStatistiques().getCubesKrystalliumRecoltes());
                row.createCell(21).setCellValue(joueur.getStatistiques().getJetonsFinanceRecoltes());
                row.createCell(22).setCellValue(joueur.getStatistiques().getJetonsGenerauxRecoltes());
            }

            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
