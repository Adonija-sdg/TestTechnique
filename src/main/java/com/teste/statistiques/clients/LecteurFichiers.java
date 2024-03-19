package com.teste.statistiques.clients;

import ch.qos.logback.core.net.server.Client;

import java.io.File;
import java.io.IOException;
import java.util.List;
public abstract class LecteurFichiers {
    protected File fichier;

    public LecteurFichiers(File fichier) {
        this.fichier = fichier;
    }

    public abstract List<Client> lireDonnees() throws IOException;

    public abstract List<Client> lecteurDonnee() throws IOException;
}
