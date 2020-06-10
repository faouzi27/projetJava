/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class cour {
	 private int id; 
	 private String file  ;
	 private int matiere ; 
	 private int classe ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getMatiere() {
		return matiere;
	}
	public void setMatiere(int matiere) {
		this.matiere = matiere;
	}
	public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}
	public cour(int id, String file, int matiere, int classe) {
		super();
		this.id = id;
		this.file = file;
		this.matiere = matiere;
		this.classe = classe;
	}
	
}

