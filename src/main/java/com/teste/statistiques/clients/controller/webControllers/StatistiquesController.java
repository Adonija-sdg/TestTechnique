package com.teste.statistiques.clients.controller.webControllers;

import com.teste.statistiques.clients.service.LecteurFichierCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StatistiquesController {
    private final LecteurFichierCsvService lectureFichierCsvService;
    @Autowired
    public StatistiquesController(LecteurFichierCsvService lectureFichierCsvService) {
        this.lectureFichierCsvService = lectureFichierCsvService;
    }

}
