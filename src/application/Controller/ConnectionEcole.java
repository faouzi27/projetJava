/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;

/**
 *
 * @author acer
 */

import application.Entity.Clase;
import application.Entity.Note;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

import com.mysql.jdbc.Driver;

import application.Entity.User;
import application.Entity.cour;
import application.Entity.fichier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.LocalDateStringConverter;
public class ConnectionEcole {
	
	private String sql;
	private static Connection con=null;
	public int r=0;
	public LinkedList<User> alaf = new LinkedList<User>();

	public ObservableList alaf1;
	Alert alert = new Alert(null);
	public ConnectionEcole()  {
		super();		

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/ecole-1", "root","");
                
                System.out.println("NO CONNECTION");
            } catch (SQLException ex) {
             
            }
			
			
		
	}


	public LinkedList<User> affichier(String s) {
		sql=s;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
			    User us = new User();
			    us.setUsername_canonical(rs.getString("username_canonical"));
			    us.setPrenom(rs.getString("prenom"));
			    us.setRoles(rs.getString("roles"));
			    us.setPassword(rs.getString("password"));
			    us.setAffecter(rs.getInt("affecter"));
			    us.setCin(rs.getString("cin"));
			    us.setClasse(rs.getString("classe"));
			    us.setLoisir(rs.getString("loisir"));
			    us.setNiveau(rs.getInt("niveau"));
			    us.setId(rs.getInt("id"));
			    us.setMatiere(rs.getInt("matiere"));
			  //  us.setdtn(LocalDate.of(rs.getDate("date_naissance").getYear(),rs.getDate("date_naissance").getMonth(),rs.getDate("date_naissance").getDay()));
			    us.setParent(rs.getInt("parent"));
			    us.setTel(rs.getString("tel"));
			    us.setSexe(rs.getString("sexe"));
			    us.setEmail(rs.getString("email"));
				this.alaf.add(us);
				


		
			}
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return alaf;
	}
	
	
	public int ajouterpa(String s,User e) {
		sql=s;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, e.getUsername());
			st.setString(2, e.getPrenom());
			st.setString(3, e.getCin());
			st.setString(4, e.getSexe());
			
			Date  daten =  Date.valueOf(e.getDtn());
			st.setDate(9, daten);
			st.setString(5, e.getTel());
			st.setString(6, e.getEmail());
			st.setString(7, e.getPassword());
			st.setString(8, e.getRoles());
                        st.setString(10, e.getUsername_canonical());
                        st.setString(11, e.getEmail_canonical());
                        st.setBoolean(12, e.isEnabled());

		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
	}

        public int ajouteen(String s,User e) {
		sql=s;
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, e.getUsername());
			st.setString(2, e.getPrenom());
			st.setString(3, e.getCin());
			st.setString(4, e.getSexe());
			st.setString(5, e.getTel());
			st.setString(6, e.getEmail());
			st.setString(7, e.getPassword());
			st.setString(8, e.getRoles());      
		        st.setInt(9, e.getMatiere());
                        st.setString(10, e.getUsername_canonical());
                        st.setString(11, e.getEmail_canonical());
                        st.setBoolean(12, e.isEnabled());
                        
			Date  daten =  Date.valueOf(e.getDtn());
			st.setDate(13, daten);
			
		 r =	st.executeUpdate();
		} catch (SQLException e1) {

try {
	System.out.println("erreur sql-1");
	PreparedStatement st = con.prepareStatement(sql);
	System.out.println(sql);
	st.setString(1, e.getUsername());
	st.setString(2, e.getPrenom());
	st.setString(3, e.getCin());
	st.setString(4, e.getSexe());
	st.setString(5, e.getTel());
	st.setString(6, e.getEmail());
	st.setString(7, e.getPassword());
	st.setString(8, e.getRoles());
        st.setString(9, e.getUsername_canonical());
        st.setString(10, e.getEmail_canonical());
        st.setBoolean(11, e.isEnabled());
        
			Date  daten =  Date.valueOf(e.getDtn());
			st.setDate(12, daten);

	
	 r =	st.executeUpdate();
} catch (Exception e2) {
	System.out.println("erreur sql-2");
	e2.printStackTrace();
	}
		
		
		}
		return r;
	}
	
	
	
	
	public int ajoutermatiere(String s,String nommat,int coef) {
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nommat);
			st.setInt(2, coef);
			
			
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			alert.setAlertType(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setTitle("Warning");
			alert.setContentText("matiere deja exsit");
			alert.showAndWait();		}
		return r;
	}
	
	
	public int ajouterappre(String s,User e) {
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, e.getUsername());
			st.setString(2, e.getPrenom());
			st.setString(3, e.getCin());
			st.setString(4, e.getSexe());
			st.setString(5, e.getTel());
			st.setString(6, e.getEmail());
			st.setString(7, e.getRoles());
			Date  daten =  Date.valueOf(e.getDtn());
			st.setDate(8, daten);
			st.setInt(9, e.getNiveau());
			st.setString(10, e.getLoisir());
			st.setInt(11, e.getParent());
                        st.setString(12, e.getUsername_canonical());
                        st.setString(13, e.getEmail_canonical());
                        st.setBoolean(14, e.isEnabled());
                        st.setString(15, e.getPassword());

			
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
	}
	
	
	public int getparent(String s,String username,String cin) {
		User uss=new User();
		sql=s;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("username").equals(username) && rs.getString("cin").equals(cin)) {	
					uss.setId(rs.getInt("id"));
			break;
				}
			}
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		return uss.getId();
	}
	
	public User user(int id) {
		User us=new User();
		sql="select * from user where id ="+id;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				
				    us.setId(rs.getInt("id"));
			            us.setUsername(rs.getString("username"));
				    us.setPrenom(rs.getString("prenom"));
				    us.setRoles(rs.getString("roles"));
				    us.setPassword(rs.getString("password"));
				    us.setAffecter(rs.getInt("affecter"));
				    us.setCin(rs.getString("cin"));
				    us.setClasse(rs.getString("classe"));
				    us.setLoisir(rs.getString("loisir"));
				    us.setNiveau(rs.getInt("niveau"));
				    us.setId(rs.getInt("id"));
				  //  us.setdtn(LocalDate.of(rs.getDate("date_naissance").getYear(),rs.getDate("date_naissance").getMonth(),rs.getDate("date_naissance").getDay()));
				    us.setParent(rs.getInt("parent"));
				    us.setTel(rs.getString("tel"));
				    us.setSexe(rs.getString("sexe"));
				    us.setEmail(rs.getString("email"));
				
					
					
					
			break;
				}
			
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		return us;
	}
	
	
	
	public ObservableList selectmatier() {
		ObservableList ObservableList = FXCollections.observableArrayList();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from matiere");
			while (rs.next()) {
				ObservableList.add(rs.getString("nom_matiere"));
			

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ObservableList;
	}
	
	
	   public List<Clase> getALL() throws SQLException{
    String req2="select * from classe ";
      Statement  ste=DBConnect.getConnection().createStatement();
        ResultSet rs=ste.executeQuery(req2);
        List<Clase> classes =new ArrayList<>();
        while(rs.next()){
            Clase classe= new Clase();
            classe.setId(rs.getInt("id"));
            classe.setNiveau(rs.getInt("niveau"));
            classe.setType(rs.getString("type"));
          classes.add(classe);
        
        }
       
        return classes;

    }
	
	

	public ObservableList selectclass() {
		ObservableList ObservableList = FXCollections.observableArrayList();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from classe");
			while (rs.next()) {
                            
                        
                                
                                ObservableList.add(rs.getString("type"));
			
			}
			
		} catch (Exception e) {
		
		}
		return ObservableList;
	}
	
	
	public ObservableList selectmatbox() {
		ObservableList ObservableList = FXCollections.observableArrayList();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from matiere");
			while (rs.next()) {
				ObservableList.add(rs.getString("nom_matiere"));
			
			}
			
		} catch (Exception e) {
		
		}
		return ObservableList;
	}
	
	
	
	public ObservableList affappcom(String ff) {
		ObservableList ObservableList = FXCollections.observableArrayList();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user where parent="+ff);
			while (rs.next()) {
		        ObservableList.add(rs.getString("cin"));
		        System.out.println("this sys out form sql "+ff+"////"+rs.getString("cin"));
			}
			
		} catch (Exception e) {
		
		}
		return ObservableList;
	}
	
	
	
	
	
	
	
	public int uploadcour(String s,fichier f,int classe,int Matiere) {
		
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			
			
			st.setString(1, f.getNom());
			st.setString(2, f.getExtension());
			st.setInt(3, Matiere);
			st.setInt(4, classe);
			st.setString(5, f.getData());
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
		
	}
	
	
	
	public int uploademploit(String s,fichier f,int classe) {
		
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getNom());
			st.setString(2, f.getExtension());
			st.setInt(3, classe);
			st.setString(4, f.getData());
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
		
	}
	
	
	
	public int getclasse(String classe) {
			int classeid = 0;
		
		String		sql="select * from classe";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				if(classe.equals(rs.getString("type"))) {
					classeid=rs.getInt("id");
					
				}
			}
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		return classeid;
	}
	
	
	public int getmatiere(String matiere) {
		int matiereid = -1;
				
				String	sql="select * from matiere";
				try {
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					
					
					while(rs.next()){
						System.out.println(rs.getString("nom_matiere"));
						if(matiere.equals(rs.getString("nom_matiere"))) {
							matiereid=rs.getInt("id");
							System.out.println(matiereid);

						}
					
					}
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				return matiereid;
			}	
	
	
	
	
	
	
	public int suppcour(int id) {
		String sql="delete from cour where id="+id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
		int	r1 =	st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}
	public int suppuser(String id) {
		String sql="delete from user where id="+id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
		int	r1 =	st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}
	
	
	public int suppmat(int id) {
		String sql="delete from matiere where id="+id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
		int	r1 =	st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}
	

	
	
	public ObservableList selectbox() {
		ObservableList ObservableList = FXCollections.observableArrayList();
		
				ObservableList.add("parent");
				ObservableList.add("apprenant");

		
		return ObservableList;
	}
	

	public ObservableList selectbox1() {
		ObservableList ObservableList = FXCollections.observableArrayList();
		
				ObservableList.add("employe");
				ObservableList.add("enseignant");

		
		return ObservableList;
	}
	

	
	public ObservableList selectuser(String sf,String rolee) {
		ObservableList ObservableList = FXCollections.observableArrayList();
		try {
			Statement st=con.createStatement();
			
			if(rolee.equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}")) {
				ResultSet rs=st.executeQuery("select * from user where roles="+rolee);
				while (rs.next()) {
					ObservableList.add(rs.getString("nom_matiere"));
				

				}
			}else if(rolee.equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}")) {
				ResultSet rs=st.executeQuery("select * from user"+rolee);
				while (rs.next()) {
					ObservableList.add(rs.getString("nom_matiere"));
				

				}
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ObservableList;
	}
	
	
	
	
	

	public int matiere(int id) {
		String sql="delete from matiere where id="+id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
		int	r1 =	st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}
	
	
	
	
	public int up_ap_parent(int id ,String s) {
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			
			
		
		
			
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
	}
	
	
	public int modifmat(String s) {
		sql=s;
		try {

			PreparedStatement st = con.prepareStatement(sql);
			
		 r =	st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return r;
	}
	
	 public boolean UpdateUserLastLogin(User u) {

    String sql = ("UPDATE user SET last_login = '"+u.getLast_login()+"'WHERE id = '"+u.getId()+"'");
   try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

    }

}