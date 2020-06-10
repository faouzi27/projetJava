/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

/**
 *
 * @author acer
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hamza
 */
public class Clase
{
    private int id;
    private int niveau;
    private String type;
 
    
    public Clase() {
    }

    public Clase(int id, int niveau, String type) {
        this.id = id;
        this.niveau = niveau;
        this.type = type;
     
    }

    public Clase(int niveau, String type) {
        this.niveau = niveau;
        this.type = type;
    }

    public Clase(int id, int niveau) {
        this.id = id;
        this.niveau = niveau;
    }

    public Clase(int id) {
        this.id = id;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.getNiveau()+this.getType()+"";
    }



    
    
}

