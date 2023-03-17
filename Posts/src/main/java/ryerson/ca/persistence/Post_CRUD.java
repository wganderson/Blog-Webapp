/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.persistence;

import ryerson.ca.helper.PostInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author student
 */
public class Post_CRUD {
    private static Connection getCon() {
        Connection con = null;
        try {
            System.out.println("Attempting Connection.");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BWB?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static ArrayList<PostInfo> readFeed(String username){
        PostInfo bean = null;
        ArrayList<PostInfo> feed = new ArrayList<PostInfo>();
        
        
        try{
            Connection con = getCon();
            
            String q = "SELECT title, datecreated, Post.type, body, image, Post.id FROM Creator INNER JOIN CreatorWritesPost ON Creator.id = CreatorWritesPost.creatorid INNER JOIN Post ON CreatorWritesPost.postid = Post.id WHERE Creator.username =\"" + username + "\" ORDER BY datecreated DESC";//select posts from creator with 'username'
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String title = rs.getString("title");
                String date = rs.getDate("datecreated").toString();
                String type = rs.getString("type");
                int postid = rs.getInt("Post.id");
                if(type.equals("image")){
                    String content = rs.getString("image");
                    bean = new PostInfo(title, date, type, content, postid);
                }
                else{
                    String body = rs.getString("body");
                    bean = new PostInfo(title, date, type, body, postid);
                }
                feed.add(bean);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return feed;
    }
    
    public static PostInfo readPost(int postid){
        PostInfo bean = null;
        try{
            Connection con = getCon();
            String temp = Integer.toString(postid);
            String q = "SELECT title, datecreated, Post.type, body, image FROM Post WHERE Post.id = " + temp;//select posts from creator with 'username'
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
                String title = rs.getString("title");
                String date = rs.getDate("datecreated").toString();
                String type = rs.getString("type");
                if(type.equals("image")){
                    String content = rs.getString("image");
                    bean = new PostInfo(title, date, type, content, postid);
                }
                else{
                    String body = rs.getString("body");
                    bean = new PostInfo(title, date, type, body, postid);
                }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return bean;
    }
    
    public static void subscribe(String sub, int id){
        System.out.println("POST_CRUD SUBSCRIBE");
        try{
            Connection con = getCon();

            String q = "UPDATE Creator SET subscribercount = subscribercount + 1";
            PreparedStatement ps = con.prepareStatement(q);
            ps.executeUpdate(q);

            q = "INSERT INTO Subscriptions (subusername, creatorid) VALUES (\"" + sub + "\", " + id + ")";
            ps = con.prepareStatement(q);
            ps.executeUpdate(q);
        }catch(Exception e){
            System.out.println(e);
        }
    }
        public static void unsubscribe(String sub, int id) {
            System.out.println("POST_CRUD UNSUBSCRIBE");
                try {
            Connection con = getCon();

            String q = "UPDATE Creator SET subscribercount = subscribercount - 1";
            PreparedStatement ps = con.prepareStatement(q);
            ps.executeUpdate(q);//decrement subcount
            
            q = "DELETE FROM Subscriptions WHERE subusername = \"" + sub + "\" AND creatorid = " + id;
            ps = con.prepareStatement(q);
            ps.executeUpdate(q);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
