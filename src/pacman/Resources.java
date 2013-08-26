package pacman;


import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Melvic
 */
public class Resources {
    protected static ResourceBundle resources;
    static{
        try{
            resources = ResourceBundle.getBundle("pacman.res.PacmanProperties",Locale.getDefault());
        }catch(Exception e){
            System.out.println("Pacman properties not found");
            System.exit(1);
        }
    }
    public String getResourceString(String key){
        String str;
        try{
            str = resources.getString(key);
        }catch(Exception e){
            str = null;
        }
        return str;
    }
    protected URL getResource(String key){
        String name = getResourceString(key);
        if(name != null){
            URL url = this.getClass().getResource(name);
            return url;
        }
        return null;
    }
}
