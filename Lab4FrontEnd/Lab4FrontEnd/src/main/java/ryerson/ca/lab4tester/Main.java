package ryerson.ca.lab4tester;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import ryerson.ca.lab4frontend.FrontEndIn;
import ryerson.ca.lab4helper.UserInfo;
import ryerson.ca.lab4helper.UserXML;

/**
 *
 * @author student
 */
public class Main {
    public static void main(String[] args){
        
        UserXML result = FrontEndIn.retreiveSubscriptionsFromBackendTest("");
        //UserXML subs = (UserXML) result;
        //ArrayList<UserInfo> subscribed = (ArrayList) subs.getFeed();
        /*
        if(subscribed.equals(null)){
            System.out.println("null");
        }
        else{
            System.out.println("not null");
        }
        */
    }
}
