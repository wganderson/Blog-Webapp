/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab4helper;

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
//import com.mycompany.lab2exc2.Persistence.User_CRUD;
/**
 *
 * @author student
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserInfo {
    /*to be completed
    For now, we just add book info to make the example work. This class must be completed in future
    */
    
    String username;
    String password;
    int subCount, postCount;
    
    

    public UserInfo(String username, String password, int subCount, int postCount) {     
        this.username = username;
        this.password = password;
        this.subCount = subCount;
        this.postCount = postCount;
    }
    
    public UserInfo(){
    
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
