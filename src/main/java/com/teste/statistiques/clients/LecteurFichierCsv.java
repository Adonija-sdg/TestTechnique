package com.teste.statistiques.clients;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.net.server.Client;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class LecteurFichierCsv extends LecteurFichiers {
    public LecteurFichierCsv(File fichier) {
        super(fichier);
    }
    @Override
    public List<Client> lireDonnees() throws IOException {
        List<Client> clients = new ArrayList<>();

        try (CSVParser parser = CSVParser.parse(String.valueOf(fichier), CSVFormat.DEFAULT.withTrim())) {
            for (CSVRecord record : parser) {
                String nom = record.get(0);
                String prenom = record.get(1);
                int age = Integer.parseInt(record.get(2));
                String profession = record.get(3);
                int salaire = Integer.parseInt(record.get(4));
                clients.add(new Client() {
                    @Override
                    public void run() {
                    }
                    @Override
                    public void close() {

                    }
                });
            }
        }

        return clients;
    }

    @Override
    public List<Client> lecteurDonnee() throws IOException {
        return null;
    }
}
