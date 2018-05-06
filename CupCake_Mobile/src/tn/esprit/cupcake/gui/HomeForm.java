/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import tn.esprit.cupcake.entities.Panier;
import tn.esprit.cupcake.services.PanierService;

/**
 *
 * @author Boubaker Ibrahim
 */
public class HomeForm {

	Form f;
	//TextField tnom;
	//TextField tetat;
	Button btnajout, btnaff, btnstat, btntop,backBtn;

	public HomeForm() {
		f = new Form("Nos produits");
		//tnom = new TextField();
		//tetat = new TextField();
		btnajout = new Button("Prendre Des Notes");
		btnaff = new Button("Affichage Produits");
		btnstat = new Button("Statistiques");
		btntop = new Button("Top list");
		backBtn=new Button("Retour");
		backBtn.setUIID("CalendarHourSelected");
		//f.add(tnom);
		//f.add(tetat);
		f.add(btnajout);
		f.add(btnaff);
		f.add(btnstat);
		f.add(btntop);
		f.add(backBtn);
		btnajout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				note n = new note();
				n.start();
			}
		});
		btnaff.addActionListener((e) -> {
			Affichage a = new Affichage();
			a.getF().show();
		});

		btnstat.addActionListener((e) -> {
			Statistic s = new Statistic();
			s.createPieChartForm().show();
		});
		btntop.addActionListener((e) -> {
			Affichage2 a = new Affichage2();
			a.getF().show();

		});
		backBtn.addActionListener((e)->{
		new ListPatisseriesGui().show();
		});
	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}



}
