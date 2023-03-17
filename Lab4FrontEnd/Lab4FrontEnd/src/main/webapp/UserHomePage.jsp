<%-- 
    Document   : UserHomePage
    Created on : Feb 9, 2022, 7:56:51 PM
    Author     : student
--%>
<%@page import="ryerson.ca.lab4helper.PostInfo"%>
<%@page import="ryerson.ca.lab4frontend.FrontEndIn"%>
<%@page import="ryerson.ca.lab4helper.PostXML"%>
<%@page import="ryerson.ca.lab4helper.UserInfo"%>
<%@page import="ryerson.ca.lab4helper.UserXML"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>

<%
UserXML uxml = (UserXML) request.getAttribute("subscriptions");
ArrayList<UserInfo> subscribed = uxml.getFeed();

int subcount;
try{
    subcount = subscribed.size();
}catch(Exception e){
    subcount = -1;
}
%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getSession().getAttribute("username")%> Home</title>

        <style>

            body {
                background-color: whitesmoke;
            }

            .center {
                margin: auto;
                width: 80%;
                border-width: 2px;
                border-style: solid;
                border-color: grey;
                padding: 10px;
                text-align: center;
                border-radius: 15px;
                background-color: black;
                color: white;
            }

            .button {
                width: 20%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin: auto;
            }

            li {
                list-style-type: none;
                /* Remove bullets */
                padding: 5px;
                /* Remove padding */
                margin: 0;
                /* Remove margins */
                display: inline;
            }
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }
        </style>
    </head>
    <body>

    <body>
        <div class="center">
            <h1>Welcome, <%=request.getAttribute("username")%> to Your Home Page</h1>
            <div id="stats">
                <ul>

                    <li>Subscriptions: <%=subcount%> </li>
                    <li>Posts:[Length of Arraylist]</li>
                    <li>Collaborators:[Length of Arraylist]</li>
                </ul>
            </div>

        </div>
        <div class="center">
            <table>
                <caption>Your Subscriptions</caption>
                <tr>
                    <th>Blog Name</th>
                    <th>Browse Content</th>
                </tr>
                <%
                    if (subcount <= 0) {//if user has no subscriptions
                %>
                <form action = "Browse" method = "post">
                    <tr>
                        <td>Find more blogs</td>
                        <td><input type="submit" value="Browse New Content"></td>
                    </tr>
                </form>
                <%
                } else {
                    for (int j = 0; j < 5; j++) {
                        if (subscribed.size() > j) {
                %>
                <form action = "BrowseUser" method = "post">
                    <tr>
                        <td><%=subscribed.get(j).getUsername()%></td>
                    <input type="hidden" name="browseTo" value ="<%=subscribed.get(j).getUsername()%>">                        
                    <td><input type="submit" value="Browse <%=subscribed.get(j).getUsername()%>"></td>
                    </tr>
                </form>
                <%} else {
                %>
                <form action = "Browse" method = "post">
                    <tr>
                        <td>Find more blogs</td>
                        <td><input type="submit" value="Browse New Content"></td>
                    </tr>
                </form>
                <%}
                    }
                    if (subscribed.size() >= 5) {%>
                <form action = "BrowseSubs" method = "post">
                    <tr>
                        <td>More Subscriptions</td>
                        <td><input type="submit" value="Browse Subscriptions"></td>
                    </tr>
                </form>
                <%
                        }
                    }%>

            </table>    
        </div>
    </body>
</html>
