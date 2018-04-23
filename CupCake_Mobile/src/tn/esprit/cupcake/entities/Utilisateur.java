/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alaa
 */
public class Utilisateur {
	private long id ;
	private String username;
	private String username_canonical;
    private String email ;
	private String email_canonical;
	private String enabled;
	private String salt;
	private String password ;
	private Date last_login;
	private String confirmation_token;
	private Date password_requested_at;
	private String roles ;
	private int num_tel;
	private String nom ;
    private String prenom;
    private Date date_naissance ;
	
	//private static ArrayList<String> RoleSymfony=new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_CLIENT","ROLE_PATISSIER"));

	


    public Utilisateur() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername_canonical() {
		return username_canonical;
	}

	public void setUsername_canonical(String username_canonical) {
		this.username_canonical = username_canonical;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_canonical() {
		return email_canonical;
	}

	public void setEmail_canonical(String email_canonical) {
		this.email_canonical = email_canonical;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getConfirmation_token() {
		return confirmation_token;
	}

	public void setConfirmation_token(String confirmation_token) {
		this.confirmation_token = confirmation_token;
	}

	public Date getPassword_requested_at() {
		return password_requested_at;
	}

	public void setPassword_requested_at(Date password_requested_at) {
		this.password_requested_at = password_requested_at;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	@Override
	public String toString() {
		return "Utilisateur{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + '}';
	}

  
}
