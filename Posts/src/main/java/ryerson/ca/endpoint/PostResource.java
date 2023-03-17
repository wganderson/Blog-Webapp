/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.endpoint;

import java.io.StringWriter;
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
import ryerson.ca.persistence.Post_CRUD;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("Post/{postid}")
public class PostResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.PostResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("postid") int postid) {
        //TODO return proper representation object
        PostInfo post;
        post = Post_CRUD.readPost(postid);
        if (post == null) {
            return ("");
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PostInfo.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(post, sw);
            System.out.println(sw.toString());
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
 