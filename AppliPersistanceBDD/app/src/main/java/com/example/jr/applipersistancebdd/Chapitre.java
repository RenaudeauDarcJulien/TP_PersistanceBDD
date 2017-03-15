package com.example.jr.applipersistancebdd;

/**
 * Created by JR on 15/03/2017.
 */

public class Chapitre {

    private int id;
    private String name;
    private String description;


    public Chapitre()
    {

    }
    public Chapitre(int id,String name,String desc)
    {
        this.id=id;
        this.name=name;
        this.description=desc;
    }

    public Chapitre(String name,String desc)
    {
        this.name=name;
        this.description=desc;
    }
//Vos getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Surcharger la méthode toString qui vous permet d’afficher les membres de l’instance Chapitre
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom du Chapitre = " + name + "\n" +  "Description du chapitre = " + description);
        return sb.toString();
    }
}
