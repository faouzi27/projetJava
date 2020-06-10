/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

/**
 *
 * @author Hamza
 */
public class Affecter {
    private int id;

   private Clase cl;
   private User us;

    public Affecter() {
    }

    public Affecter(int id, Clase cl, User us) {
        this.id = id;
        this.cl = cl;
        this.us = us;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clase getCl() {
        return cl;
    }

    public void setCl(Clase cl) {
        this.cl = cl;
    }

    public User getUs() {
        return us;
    }

    public void setUs(User us) {
        this.us = us;
    }
   
   
   
   
   
   


}
