/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;

import java.util.Objects;

/**
 *
 * @author Rzouga
 */
public class CourMonLivre {
    
    private int id , IdMatier ;
    private String description,nomCour,video ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatier() {
        return IdMatier;
    }

    public void setIdMatier(int IdMatier) {
        this.IdMatier = IdMatier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomCour() {
        return nomCour;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.IdMatier;
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.nomCour);
        hash = 83 * hash + Objects.hashCode(this.video);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourMonLivre other = (CourMonLivre) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.IdMatier != other.IdMatier) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.nomCour, other.nomCour)) {
            return false;
        }
        if (!Objects.equals(this.video, other.video)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourMonLivre{" + "id=" + id + ", IdMatier=" + IdMatier + ", description=" + description + ", nomCour=" + nomCour + ", video=" + video + '}';
    }

    public CourMonLivre(int id, int IdMatier, String description, String nomCour, String video) {
        this.id = id;
        this.IdMatier = IdMatier;
        this.description = description;
        this.nomCour = nomCour;
        this.video = video;
    }

    public CourMonLivre(int IdMatier, String description, String nomCour, String video) {
        this.IdMatier = IdMatier;
        this.description = description;
        this.nomCour = nomCour;
        this.video = video;
    }
    
        public CourMonLivre(int IdMatier, String description, String nomCour , int id ) {
        this.id = id ;
        this.IdMatier = IdMatier;
        this.description = description;
        this.nomCour = nomCour;
    }
    
    
    
    
}
