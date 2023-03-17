/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.endpoint;

import java.io.StringWriter;
import java.util.ArrayList;
import ryerson.ca.helper.PostInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.helper.PostXML;
import ryerson.ca.persistence.Post_CRUD;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("Feed/{creator}")
public class FeedResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public FeedResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.PostResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("creator") String creator) {
        //TODO return proper representation object
        System.out.println("getXML FEED ACCESSED");
        ArrayList<PostInfo> feed;
        feed = Post_CRUD.readFeed(creator);
        
        
        if (feed == null) {
            return ("");
        }
        PostXML pxml = new PostXML();
        pxml.setPost(feed);
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PostXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(pxml, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(PostResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    /**
     * PUT method for updating or creating an instance of PostResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
 