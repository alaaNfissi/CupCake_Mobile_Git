/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package tn.esprit.cupcake.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
	private EncodedImage encoded;
	private URLImage uRLImage;
	private ImageViewer imgv;
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addComponentToSideMenu(inbox);
        
        //getToolbar().addCommandToSideMenu("WalkthruForm", statsImage, e -> new WalkthruForm(res).show());
        getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Map", null, e -> {});
		System.out.println(UtilisateurService.user);
		if(UtilisateurService.user.getRoles().equals("ROLE_PATISSIER"))
		{
			getToolbar().addCommandToSideMenu("My Space",null,e->new MySpaceForm(res).show());
		}
            getToolbar().addCommandToSideMenu("Trending", trendingImage, e -> new TrendingForm(res).show());
            getToolbar().addCommandToSideMenu("Settings", null, e -> {});
		    getToolbar().addCommandToSideMenu("Sign out", null, e -> {
			UtilisateurService.user=null;
			PatisserieService.patisserie=null;
			new SignInForm(res).show();
		});

        
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
		/*encoded = EncodedImage.createFromImage(res.getImage(""), false);
		uRLImage =URLImage.createToStorage(encoded,"test", UtilisateurService.user.getImage());
		imgv= new ImageViewer(uRLImage);*/
		/*EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true); 
                        URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg",UtilisateurService.user.getImage() );*/
		EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true); 
                        URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg",UtilisateurService.user.getImage()); 
                        background.fetch(); 
                        ScaleImageLabel myPic = new ScaleImageLabel(); 
                        myPic.setIcon(background); 
						ImageViewer img = new ImageViewer(background);
                         
                        
		getToolbar().addComponentToSideMenu(new Label(img.getImage(), "Container"));
        getToolbar().addComponentToSideMenu(new Label(UtilisateurService.user.getNom()+" "+UtilisateurService.user.getPrenom(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(UtilisateurService.user.getUsername()+" "+UtilisateurService.user.getDate_naissance(), "SideCommandSmall"));
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
