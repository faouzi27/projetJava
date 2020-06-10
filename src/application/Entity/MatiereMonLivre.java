/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

/**
 *
 * @author Rzouga
 */
public class MatiereMonLivre {
    
    int id , categorie , vote , nbrhour ;
    String matiere ,image ;
    double rate ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getNbrhour() {
        return nbrhour;
    }

    public void setNbrhour(int nbrhour) {
        this.nbrhour = nbrhour;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public MatiereMonLivre(int categorie, int vote, int nbrhour, String matiere, String image, double rate) {
        this.categorie = categorie;
        this.vote = vote;
        this.nbrhour = nbrhour;
        this.matiere = matiere;
        this.image = image;
        this.rate = rate;
    }
    public MatiereMonLivre(int categorie,  int nbrhour, String matiere, String image) {
        this.categorie = categorie;
        this.nbrhour = nbrhour;
        this.matiere = matiere;
        this.image = image;
    }
    public MatiereMonLivre(int id ,int categorie,  int nbrhour, String matiere) {
        this.id= id ;
        this.categorie = categorie;
        this.nbrhour = nbrhour;
        this.matiere = matiere;
    }

    public MatiereMonLivre(int id, int categorie, int vote, int nbrhour, String matiere, String image, double rate) {
        this.id = id;
        this.categorie = categorie;
        this.vote = vote;
        this.nbrhour = nbrhour;
        this.matiere = matiere;
        this.image = image;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MatiereMonLivre{" + "id=" + id + ", categorie=" + categorie + ", vote=" + vote + ", nbrhour=" + nbrhour + ", matiere=" + matiere + ", image=" + image + ", rate=" + rate + '}';
    }

    public MatiereMonLivre(int id) {
        this.id = id;
    }
    
    
    
}
