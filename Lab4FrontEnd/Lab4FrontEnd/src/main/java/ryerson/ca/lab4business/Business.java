/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab4business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import ryerson.ca.lab4helper.PostInfo;
import ryerson.ca.lab4helper.PostXML;
import ryerson.ca.lab4helper.UserInfo;
import ryerson.ca.lab4helper.UserXML;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String password) throws IOException {
        UserInfo u = getUser(username);
        if (u != null) {
            System.out.println("username: " + u.getUsername() + " = " + username);
            System.out.println("password: " + u.getPassword() + " = " + password);
        } else {
            System.out.println("username " + username + " not found");
        }
        if (u != null && u.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public static UserXML getSubscriptions(String username) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/Users/webresources/Subs");
        System.out.println("Username is " + username);
        InputStream is = searchwebTarget.path(username).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        UserXML feed = userxmltoObjects(xml);
        return feed;
    }

    public static UserInfo getUser(String username) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/Users/webresources/User");
        InputStream is = searchwebTarget.path(username).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        UserInfo user = userInfoxmltoObjects(xml);
        return user;
    }

    public static PostXML getFeed(String username) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/Posts/webresources/Feed");
        InputStream is = searchwebTarget.path(username).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        PostXML feed = postxmltoObjects(xml);
        return feed;
    }

    public static void getSub(String sub, String creator) {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/Users/webresources/Subto/");
        searchwebTarget.path("sub").path(sub).path(creator).request().get(InputStream.class);
    }

    public static void getUnsub(String sub, String creator) {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/Users/webresources/Subto/");
        searchwebTarget.path("unsub").path(sub).path(creator).request().get(InputStream.class);
    }

    private static PostXML postxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PostXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PostXML books = (PostXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return books;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static UserXML userxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(UserXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            UserXML list = (UserXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return list;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PostInfo postInfoxmltoObjects(String xml) {
        if (xml.isEmpty()) {
            return null;
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PostInfo.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PostInfo post = (PostInfo) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return post;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static UserInfo userInfoxmltoObjects(String xml) {
        if (xml.isEmpty()) {
            return null;
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(UserInfo.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            UserInfo user = (UserInfo) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return user;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
