package com.teste.statistiques.clients.dao;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileReader {


    private static final String DELIMITER = ",";

    public List<Client> lireDonnees(File fichier) throws IOException {
        List<Client> clients = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] parties = ligne.split(DELIMITER);
                if (parties.length < 5) {
                    // La ligne n'a pas le bon format, passer à la suivante
                    continue;
                }

                // Ajouter une instruction de journalisation pour vérifier les données lues
                System.out.println("Données lues depuis le fichier CSV : " + ligne);

                // Vérifier et valider les données avant de créer un Client
                String nom = parties[0].trim();
                String prenom = parties[1].trim();
                int age;
                try {
                    age = Integer.parseInt(parties[2].trim());
                } catch (NumberFormatException e) {
                    // Si l'âge n'est pas un nombre valide, passer à la ligne suivante
                    System.err.println("Erreur de format pour l'âge : " + parties[2]);
                    continue;
                }
                String profession = parties[3].trim();
                double salaire;
                try {
                    salaire = Double.parseDouble(parties[4].trim());
                } catch (NumberFormatException e) {
                    // Si le salaire n'est pas un nombre valide, passer à la ligne suivante
                    System.err.println("Erreur de format pour le salaire : " + parties[4]);
                    continue;
                }
                // Créer un objet Client à partir des parties extraites de la ligne
                Client client = new Client(nom, prenom, age, profession, (int) salaire);
                clients.add(client);
            }
        } catch (IOException e) {
            // Gérer les erreurs de lecture de fichier
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            throw e; // Rethrow pour signaler l'erreur à l'appelant
        }

        return clients;
    }
}
