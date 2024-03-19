package com.teste.statistiques.clients.service;

import com.teste.statistiques.clients.dao.Client;
import com.teste.statistiques.clients.dao.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class LecteurFichierCsvService implements LecteurFichierService {
    @Autowired
    private CsvFileReader csvFileReader;

    @Override
    public List<Client> lireClients(File fichier) throws IOException {
        return csvFileReader.lireDonnees(fichier);
    }

    @Override
    public List<Client> lireFichier() {
        // Spécifiez le répertoire contenant les fichiers CSV
        File repertoire = new File("src/main/resources/uploads/");
        File[] fichiers = repertoire.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (fichiers != null && fichiers.length > 0) {
            // Si le répertoire contient des fichiers CSV, nous lisons le premier fichier trouvé
            File fichier = fichiers[0];
            try {
                List<Client> clients = csvFileReader.lireDonnees(fichier);
                // Ajouter une instruction de journalisation pour vérifier les données lues
                System.out.println("Clients lus depuis le fichier CSV : " + clients);
                return clients;
            } catch (IOException e) {
                // Gérer les erreurs de lecture de fichier
                e.printStackTrace();
                return Collections.emptyList();
            }
        } else {
            // Si aucun fichier CSV n'est trouvé dans le répertoire, retourner une liste vide
            return Collections.emptyList();
        }
    }
}
