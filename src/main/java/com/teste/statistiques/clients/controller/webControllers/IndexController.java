package com.teste.statistiques.clients.controller.webControllers;

import com.teste.statistiques.clients.dao.Client;
import com.teste.statistiques.clients.service.LecteurFichierCsvService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
    public class IndexController {
    private final LecteurFichierCsvService lectureFichierCsvService;

    public IndexController(LecteurFichierCsvService lectureFichierCsvService) {
        this.lectureFichierCsvService = lectureFichierCsvService;
    }

    @GetMapping({ "/", "/index" })
        public String index(Model model) {
            return "index";
        }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/statistiques")
    public String getStatistiques(Model model) {
        List<Client> clientsFromCsv = lectureFichierCsvService.lireFichier();

        // Afficher le contenu du fichier CSV dans la console
        System.out.println("Contenu du fichier CSV :");
        System.out.println(clientsFromCsv);
        for (Client client : clientsFromCsv) {
            System.out.println(client);
        }

        // Regrouper les clients par type de profession et calculer la moyenne des salaires
        Map<String, Double> moyenneSalairesParProfession = new HashMap<>();
        calculerMoyenneSalaires(clientsFromCsv, moyenneSalairesParProfession);

        model.addAttribute("moyenneSalaires", moyenneSalairesParProfession);
        model.addAttribute("clientsFromCsv", clientsFromCsv); // Ajouter les clients lus à partir du fichier CSV
        model.addAttribute("clientsManuels", new ArrayList<Client>() {{
            add(new Client("SAWADO", "Ado", 28, "informaticien", 5000));
            add(new Client("SAWA", "Abibi", 25, "comptable", 4500));
        }}); // Ajouter les clients manuels

        return "statistiques";
    }


    private void calculerMoyenneSalaires(List<Client> clients, Map<String, Double> moyenneSalairesParProfession) {
        for (Client client : clients) {
            String profession = client.getProfession();
            double salaire = client.getSalaire();
            if (!moyenneSalairesParProfession.containsKey(profession)) {
                moyenneSalairesParProfession.put(profession, salaire);
            } else {
                double sommeSalaires = moyenneSalairesParProfession.get(profession);
                //double nombreClients = moyenneSalairesParProfession.get(profession);
                double nouvelleSomme = sommeSalaires + salaire;
                moyenneSalairesParProfession.put(profession, nouvelleSomme);
            }
        }
        for (String profession : moyenneSalairesParProfession.keySet()) {
            double sommeSalaires = moyenneSalairesParProfession.get(profession);
            double nombreClients = clients.stream().filter(c -> c.getProfession().equals(profession)).count();
            double moyenne = sommeSalaires / nombreClients;
            moyenneSalairesParProfession.put(profession, moyenne);
        }
    }
}
