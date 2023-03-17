/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab4helper;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostInfo {
    String title, date, type, content;
    
    public PostInfo(String title, String date, String type, String content){
        this.title = title;
        this.date = date;
        this.type = type;
        this.content = content;   
    }
   
    public PostInfo(){
    
    }
    /*
    public static ArrayList<PostInfo> getFeed(String username){
        return Post_CRUD.readFeed(username);
    }
    */
    
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
