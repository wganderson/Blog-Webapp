/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.business;

import ryerson.ca.helper.PostInfo;
import ryerson.ca.persistence.Post_CRUD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author student
 */
public class PostService {
    
    public static ArrayList<PostInfo> getFeed(String username) {
        return Post_CRUD.readFeed(username);
    }
}
