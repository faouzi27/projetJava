/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Entity;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Base64;


public class fichier {

	private String nom;
	private String extension;
	private String data;
	
	
	
	public fichier() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public fichier(String nom, String extension, String data) {
		super();
		this.nom = nom;
		this.extension = extension;
		this.data = data;
	}

	public byte[] base64_to_byte(String Data) {
		byte[] databyte = null;
		
		
		databyte=Base64.getDecoder().decode(Data);
		
		
		
		return databyte;
	}

	public String byte_to_base64(byte[] Data) {

		 
		String database64 = Base64.getEncoder().encodeToString(Data);
		 
		return database64;
	}
	
public void Operationfichier(fichier nf) throws FileAlreadyExistsException  {
	
		
		
		String path=".\\"+nf.getNom()+"."+nf.getExtension();
		System.out.println(nf.getNom()+"."+path+"*******"+nf.getExtension()+"//"+nf.getData());

		try (FileOutputStream nfileoutp = new FileOutputStream(path)) {
			nfileoutp.write(nf.base64_to_byte(nf.getData()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	
}

