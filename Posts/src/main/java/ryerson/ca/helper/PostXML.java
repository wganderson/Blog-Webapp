/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXML {

    @XmlElement(name = "post")
    private ArrayList<PostInfo> feed;

    public ArrayList<PostInfo> getFeed() {
        return feed;

    }

    public PostXML() {

    }

    public void setPost(ArrayList<PostInfo> bs) {
        feed = bs;

    }

}
