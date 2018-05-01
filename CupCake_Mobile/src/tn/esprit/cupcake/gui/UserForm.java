/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package tn.esprit.cupcake.gui; 
 
import com.codename1.components.ScaleImageLabel; 
import com.codename1.facebook.FaceBookAccess; 
import com.codename1.facebook.User; 
import com.codename1.io.Storage; 
import com.codename1.l10n.ParseException;
//import com.codename1.l10n.SimpleDateFormat;
import com.codename1.social.FacebookConnect; 
import com.codename1.social.Login; 
import com.codename1.social.LoginCallback; 
import com.codename1.ui.Button; 
import com.codename1.ui.EncodedImage; 
import com.codename1.ui.Image; 
import com.codename1.ui.Label; 
import com.codename1.ui.URLImage; 
import com.codename1.ui.events.ActionEvent; 
import com.codename1.ui.events.ActionListener; 
import com.codename1.ui.geom.Dimension; 
import com.codename1.ui.layouts.BoxLayout; 
import java.io.IOException;
import java.text.SimpleDateFormat;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.Patisserie;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;
 
/** 
 * GUI builder created Form 
 * 
 * @author Tiburcio 
 */ 
public class UserForm extends com.codename1.ui.Form { 
 
    public UserForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources()); 
		System.out.println("test");
    } 
     
    public UserForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        showFormElements(); 
    } 
 
	private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
	private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();
	
	    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }
			if(sourceComponent == gui_Button_1) {
				//System.out.println("test");
                onButton_1ActionEvent(ev);
            }
            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
			if(sourceComponent == gui_Button_4) {
                onButton_3ActionEvent(ev);
            }
			
        }

        public void dataChanged(int type, int index) {
        }
    }

	
	  private void guiBuilderBindComponentListeners() {
        UserForm.EventCallbackClass callback = new UserForm.EventCallbackClass();
        gui_Button_2.addActionListener(callback);
		gui_Button_1.addActionListener(callback);
		gui_Button_4.addActionListener(callback);
    }
//-- DON'T EDIT BELOW THIS LINE!!! 
 
 
// <editor-fold defaultstate="collapsed" desc="Generated Code">                           
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        //setLayout(new com.codename1.ui.layouts.LayeredLayout()); 
        setInlineStylesTheme(resourceObjectInstance);
        //setInlineStylesTheme(resourceObjectInstance);
        //setTitle("UserForm"); 
        //setName("UserForm");
		/*-------------------------------------------------*/
		ScaleImageLabel myPic = new ScaleImageLabel();
            Image img=resourceObjectInstance.getImage("profile_image.png");
			img.scaled(108, 94);
		try {
			img = Image.createImage("/anonimo.jpg");
			
		} catch (IOException ex) {
			ex.printStackTrace();
			//Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
		}
            myPic.setIcon(img);
			Dimension d = new Dimension(50, 50);
            myPic.setPreferredSize(d);
		guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(myPic);
		gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setHint("Username/Email");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("Password");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
		gui_Container_1.addComponent(gui_Button_4);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
		gui_Label_1.setText("");
        myPic.setName("Label_1");
		myPic.setUIID("CenterLabel");
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
        gui_Button_2.setName("Button_2");
		gui_Button_4.setText("Sign In With Facebook");
        gui_Button_4.setName("Button_4");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
    }// </editor-fold> 
 
//-- DON'T EDIT ABOVE THIS LINE!!! 
 
    private void showFormElements() { 
        this.setScrollable(false);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        showData(this);
    } 
 
    private void showData(UserForm form) {
        String token = (String) Storage.getInstance().readObject("token");
        if(token == null || token.equals("")){
            showIfNotLoggedIn(form);
        } else { 
            showIfLoggedIn(form);
        } 
    } 
 
    private void showIfNotLoggedIn(UserForm form) {
        //try { 
            form.getContentPane().removeAll();
            Storage.getInstance().writeObject("token", ""); 
             
            /*ScaleImageLabel myPic = new ScaleImageLabel();
            Image img = Image.createImage("/anonimo.jpg");
            myPic.setIcon(img);
            Dimension d = new Dimension(50, 50);
            myPic.setPreferredSize(d);*/
             
            //form.add(myPic);
             
            //form.add(new Label("User not connected"));
			gui_Label_1.setText("User not connected");
             
            //Button buttonLogin = new Button("Login");
            gui_Button_4.addActionListener((e) -> {
                facebookLogin(form);
            }); 
            form.add(gui_Container_1);
             
            form.revalidate();
            //form.show(); 
       /* } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex); 
        } */
    } 
 
    private void showIfLoggedIn(UserForm form) {
        String token = (String) Storage.getInstance().readObject("token");
        FaceBookAccess.setToken(token);
            final User me = new User();
			Client c = new Client();
            try {
				String fields="name,first_name,last_name,age_range,birthday,email,gender,address,location";
                FaceBookAccess.getInstance().getUser("me?fields="+fields, me, new ActionListener() {
                    @Override 
                    public void actionPerformed(ActionEvent evt) {
                        String miNombre = me.getName(); 
						c.setUsername(me.getName());
						c.setAdresse(me.getLocation().getName());
						c.setNom(me.getLast_name());
						c.setPrenom(me.getFirst_name());
						c.setEmail(me.getEmail());
						try {
							java.util.Date dtJ= new SimpleDateFormat("MM/DD/YYYY").parse(me.getBirthday());
							c.setDate_naissance(dtJ);
						}catch (java.text.ParseException ex) {
							ex.printStackTrace();
							//Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
						}
						//Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
						if(me.getGender().equals("male"))
						{
							c.setSexe("homme");
						}
						else
						{
							c.setSexe("femme");
						}
						c.setId(Long.parseLong(me.getId()));
						c.setNum_tel(0);
						c.setImage("https://graph.facebook.com/v2.11/me/picture?access_token=" + token);
						c.setRoles("ROLE_CLIENT");
						c.setPassword(me.getFirst_name()+"-"+me.getLast_name());
						System.out.println(c);
					    System.out.println("************************************");
						UtilisateurService us=new UtilisateurService();
						us.user=c;
						System.out.println(us.LoggedUser(c.getUsername(), c.getPassword()));
						if (us.LoggedUser(c.getUsername(), c.getPassword()).getUsername()==null) {
							us.addUser(c);
						}
						//System.out.println(me.getAbout().toString());
						//System.out.println(me.getBirthday().toString());
						//System.out.println(me.getEmail().toString());
						//System.out.println(me.getFirst_name().toString());
						//System.out.println(me.getId());
                         
                        form.getContentPane().removeAll(); 
                         
                        form.add(new Label(miNombre)); 
                         
                        Button buttonLogout = new Button("Logout"); 
                        buttonLogout.addActionListener((e) -> { 
                            facebookLogout(form); 
                            showIfNotLoggedIn(form); 
                        }); 
 
                        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true); 
                        URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg", 
                                "https://graph.facebook.com/v2.11/me/picture?access_token=" + token); 
                        background.fetch(); 
                        ScaleImageLabel myPic = new ScaleImageLabel(); 
                        myPic.setIcon(background); 
                         
                        form.add(myPic); 
                        form.add(buttonLogout); 
                         
                        form.revalidate();
						//new TrendingForm().show();
                        //form.show(); 
                    } 
 
                     
                }); 
            } catch (IOException ex) {
                ex.printStackTrace();
                showIfNotLoggedIn(form);
            } 
    } 
 
    private void facebookLogout(UserForm form) {
        String clientId = "563976137303485";
        String redirectURI = "http://localhost/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "42e3ac786b4dfd94b4599aa2e33d61dc";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
 
        //trigger the login if not already logged in 
        fb.doLogout();
		UtilisateurService.user=null;
		PatisserieService.patisserie=null;
        Storage.getInstance().writeObject("token", ""); 
        showIfNotLoggedIn(form);
    } 
     
    private void facebookLogin(UserForm form) {
        //use your own facebook app identifiers here    
        //These are used for the Oauth2 web login process on the Simulator. 
        String clientId = "563976137303485";
        String redirectURI = "http://localhost/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "42e3ac786b4dfd94b4599aa2e33d61dc";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        //Sets a LoginCallback listener 
        fb.setCallback(new LoginCallback() {
            @Override 
            public void loginFailed(String errorMessage) {
                System.out.println("Falló el login");
                Storage.getInstance().writeObject("token", ""); 
                showIfNotLoggedIn(form);
            } 
 
            @Override 
            public void loginSuccessful() { 
                System.out.println("Funcionó el login");
                String token = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token);
                showIfLoggedIn(form);
				//new TrendingForm().show();
            } 
             
        }); 
        //trigger the login if not already logged in 
        if(!fb.isUserLoggedIn()){
            fb.doLogin();
        }else{ 
            //get the token and now you can query the facebook API 
            String token = fb.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
            showIfLoggedIn(form);
        } 
    }
	
	//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
		UtilisateurService us=new UtilisateurService();
			us.LoggedUser(gui_Text_Field_2.getText(), gui_Text_Field_1.getText());
        //new InboxForm().show();
		if (UtilisateurService.user != null) {
				//Form FL=new Form(UtilisateurService.user.getUsername());
				//FL.show();
				new TrendingForm().show();
			}
    }
	
	public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new SignUpForm().show();
    }
	
	public void onButton_3ActionEvent(com.codename1.ui.events.ActionEvent ev) {
		System.out.println("test 1");
        new UserForm().show();
    }
} 
