/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


/**
 *
 * @author Hamza
 */
public class Matiere {
   private int id;
   private String nom_matiere;
   private int coefficient;

    public Matiere(int id) {
        this.id = id;
    }

   
   
    public Matiere(int id, String nom_matiere, int coefficient) {
        this.id = id;
        this.nom_matiere = nom_matiere;
        this.coefficient = coefficient;
    }

   
   
   
    public Matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }
  
    public Matiere(String nom_matiere, int coefficient) {
        this.nom_matiere = nom_matiere;
        this.coefficient = coefficient;
    }

    public Matiere() {
    }

    public int getCoefficient() {
        return coefficient;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
     return nom_matiere;      
//  return "Matiere{" + "id=" + id + ", nom_matiere=" + nom_matiere + ", coefficient=" + coefficient + '}';
    }
}

