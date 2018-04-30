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
 
//-- DON'T EDIT BELOW THIS LINE!!! 
 
 
// <editor-fold defaultstate="collapsed" desc="Generated Code">                           
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout()); 
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("UserForm"); 
        setName("UserForm"); 
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
		   //new TrendingForm().show();
        } 
    } 
 
    private void showIfNotLoggedIn(UserForm form) {
        try { 
            form.getContentPane().removeAll();
            Storage.getInstance().writeObject("token", ""); 
             
            ScaleImageLabel myPic = new ScaleImageLabel();
            Image img = Image.createImage("/anonimo.jpg");
            myPic.setIcon(img);
            Dimension d = new Dimension(50, 50);
            myPic.setPreferredSize(d);
             
            form.add(myPic);
             
            form.add(new Label("User not connected"));
             
            Button buttonLogin = new Button("Login");
            buttonLogin.addActionListener((e) -> {
                facebookLogin(form);
            }); 
            form.add(buttonLogin);
             
            form.revalidate();
            //form.show(); 
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex); 
        } 
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
} 
