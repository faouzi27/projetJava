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
 */


/**
 *
 * @author Hamza
 */
public class Absence {
    
    private int id;
    private int nb_absence;
    private Clase c;
    private User u ;

    public Absence() {
    }

    public Absence(int id, int nb_absence) {
        this.id = id;
        this.nb_absence = nb_absence;
    }
    

    public Absence(int id, int nb_absence, Clase c, User u) {
        this.id = id;
        this.nb_absence = nb_absence;
        this.c = c;
        this.u = u;
    }

    public Absence(int nb_absence, Clase c, User u) {
        this.nb_absence = nb_absence;
        this.c = c;
        this.u = u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_absence() {
        return nb_absence;
    }

    public void setNb_absence(int nb_absence) {
        this.nb_absence = nb_absence;
    }

    public Clase getC() {
        return c;
    }

    public void setC(Clase c) {
        this.c = c;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String toString() {
     return nb_absence+"";
// return "Absence{" + "id=" + id + ", nb_absence=" + nb_absence + ", c=" + c + ", u=" + u + '}';
    }

    

}
