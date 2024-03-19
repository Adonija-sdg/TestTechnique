package com.teste.statistiques.clients.service;

import com.teste.statistiques.clients.dao.Client;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;
public interface LecteurFichierService {
    List<Client> lireClients(File fichier) throws IOException, JAXBException;

    List<Client> lireFichier();
}
