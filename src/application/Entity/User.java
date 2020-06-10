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



//import java.sql.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class User {
	
    private String username,tel,prenom,cin,sexe,email,password,roles,classe,loisir,username_canonical,email_canonical,confirmation_token,salt;
    private int niveau,parent,id,matiere,affecter;
    private Clase clase;

    private int nombreAbsence;
    private Absence absence;
    private LocalDate dtn;
    private LocalDateTime last_login,password_requested_at;
    private boolean enabled;

	public User(){
            this.nombreAbsence=0;
}

    public User(String username, String prenom, String cin, String sexe, String email, String password, String roles, String classe, String loisir, String username_canonical, String email_canonical, String confirmation_token, String salt, String tel, int niveau, int parent, int id, int matiere, int affecter, Clase clase, int nombreAbsence, Absence absence, LocalDate dtn, LocalDateTime last_login, LocalDateTime password_requested_at, boolean enabled) {
        this.username = username;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.classe = classe;
        this.loisir = loisir;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.confirmation_token = confirmation_token;
        this.salt = salt;
        this.tel = tel;
        this.niveau = niveau;
        this.parent = parent;
        this.id = id;
        this.matiere = matiere;
        this.affecter = affecter;
        this.clase = clase;
        this.nombreAbsence = nombreAbsence;
        this.absence = absence;
        this.dtn = dtn;
        this.last_login = last_login;
        this.password_requested_at = password_requested_at;
        this.enabled = enabled;
    }
        
        

    public int getNombreAbsence() {
        return nombreAbsence;
    }

    public Absence getAbsence() {
        return absence;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }

    public void setNombreAbsence(int nombreAbsence) {
        this.nombreAbsence = nombreAbsence;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDateTime getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(LocalDateTime password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public User(String username, String prenom, String cin, String sexe, String email, String password, String roles, String classe, String loisir, String username_canonical, String email_canonical, String tel, int niveau, int parent, int id, int matiere, int affecter, Clase clase, int nombreAbsence, Absence absence, LocalDate dtn, LocalDateTime last_login, boolean enabled) {
        this.username = username;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.classe = classe;
        this.loisir = loisir;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.tel = tel;
        this.niveau = niveau;
        this.parent = parent;
        this.id = id;
        this.matiere = matiere;
        this.affecter = affecter;
        this.clase = clase;
        this.nombreAbsence = nombreAbsence;
        this.absence = absence;
        this.dtn = dtn;
        this.last_login = last_login;
        this.enabled = enabled;
    }

    
    public User(String username, String prenom, String cin, String sexe, String email, String password, String roles, String loisir, String username_canonical, String email_canonical, String tel, int niveau, int parent, int id, int matiere, int affecter, Clase clase, int nombreAbsence, Absence absence, LocalDate dtn, boolean enabled) {
        this.username = username;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.classe = classe;
        this.loisir = loisir;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.tel = tel;
        this.niveau = niveau;
        this.parent = parent;
        this.id = id;
        this.matiere = matiere;
        this.affecter = affecter;
        this.clase = clase;
        this.nombreAbsence = nombreAbsence;
        this.absence = absence;
        this.dtn = dtn;
        this.enabled = enabled;
    }

    
    public User(String username, String prenom, String cin, String sexe, String email, String password, String roles, Clase clase, String loisir, String tel, int niveau, int parent, int id, int affecter, LocalDate dtn) {
        this.username = username;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.clase = clase;
        this.loisir = loisir;
        this.tel = tel;
        this.niveau = niveau;
        this.parent = parent;
        this.id = id;
        this.affecter = affecter;
        this.dtn = dtn;
    }

    public User(int id) {
        this.id = id;
    }
    

    public User(String username, String prenom, LocalDate dtn) {
        this.username = username;
        this.prenom = prenom;
        this.dtn = dtn;
    }

    public User(String username, String prenom, int id) {
        this.username = username;
        this.prenom = prenom;
        this.id = id;
    }
        
        
        
	
public void setusername(String n) {
	username=n;
}
public String getusername() {
	return username;
}
public void setprenom(String n) {
	prenom=n;
}
public String getprenom() {
	return prenom;
}
public void setcin(String n) {
	cin=n;
}
public String getcin() {
	return cin;
}
public void setsexe(String n) {
	sexe=n;
}
public String getsexe() {
	return sexe;
}
public void setdtn(LocalDate n) {
	dtn=n;
}
public LocalDate getdtn() {
	return dtn;
}
public void settel(String n) {
	tel=n;
}
public String gettel() {
	return tel;
}
public void setemail(String n) {
	email=n;
}
public String getemail() {
	return email;
}

public void setpassword(String n) {
	password=n;
}
public String getpassword() {
	return password;
}
public void setroles(String n) {
	roles=n;
}
public String getroles() {
	return roles;
}
public void setclase(Clase n) {
	clase=n;
}
public Clase getclasse() {
	return clase;
}
public void setaffecter(int n) {
	affecter=n;
}
public int getaffecter() {
	return affecter;
}
public void setniveau(int n) {
	niveau=n;
}
public int getniveau() {
	return niveau;
}
public void setloisir(String n) {
	loisir=n;
}
public String getloisir() {
	return loisir;
}
public void setparent(int n) {
	parent=n;
}
public int getparent() {
	return parent;
}


    @Override
    public String toString() {
      
 return username;
//return "User{" + "nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", sexe=" + sexe + ", email=" + email + ", login=" + login + ", password=" + password + ", role=" + role + ", classe=" + classe + ", loisir=" + loisir + ", tel=" + tel + ", niveau=" + niveau + ", parent=" + parent + ", id=" + id + ", affecter=" + affecter + ", dtn=" + dtn + '}';
    }

   
    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
	
    public int getMatiere() {
		return matiere;
	}
	public void setMatiere(int matiere) {
		this.matiere = matiere;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String Username) {
		this.username = Username;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getLoisir() {
		return loisir;
	}
	public void setLoisir(String loisir) {
		this.loisir = loisir;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int isAffecter() {
		return affecter;
	}
	public void setAffecter(int affecter) {
		this.affecter = affecter;
	}
	public LocalDate getDtn() {
		return dtn;
	}
	public void setDtn(LocalDate dtn) {
		this.dtn = dtn;
	}

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    public int getAffecter() {
        return affecter;
    }
    
    


}

