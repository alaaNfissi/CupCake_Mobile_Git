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

  
}
