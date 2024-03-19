package com.teste.statistiques.clients.dao;

import lombok.Data;

@Data
public class Client {
    private String nom;
    private String prenom;
    private int age;
    private final String profession;
    private int salaire;

    public Client(String nom, String prenom, int age, String profession, int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.profession = profession;
        this.salaire = salaire;
    }
}
