<%-- 
    Document   : Blog
    Created on : Feb 9, 2022, 7:56:51 PM
    Author     : student
--%>


<%@page import="ryerson.ca.lab4helper.UserXML"%>
<%@page import="ryerson.ca.lab4business.Business"%>
<%@page import="ryerson.ca.lab4helper.UserInfo"%>
<%@page import="ryerson.ca.lab4helper.PostXML"%>
<%@page import="ryerson.ca.lab4helper.PostInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getSession().getAttribute("browseTo")%></title>

        <style>

            body {
                background-color: #EDF2F4;
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
                background-color: #2B2D42;
                color: #EDF2F4;
            }

            .post {
                margin: auto;
                width: 40%;
                border-width: 1px;
                border-color: black;
                padding: 10px;
                border-radius: 15px;
                background-color: #2B2D42;
                margin-top: 4px;
                text-align: center;
                color: #EDF2F4;
            }

            .button {
                width: 20%;
                background-color: #8D99AE;
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

            .textPost {
                text-align: center;
                padding: 5px 5px;
            }

            .imagePost > img {
                border-radius: 8px;
                left: 50%;
            }

            .imagePost > .textPost{
                text-align: center;
            }

            #images {
                width: 100%; 
            }
        </style>
    </head>
    <body>
        <%
            UserXML uxml = (UserXML) request.getSession().getAttribute("subscriptions");
            ArrayList<UserInfo> subscribed = uxml.getFeed();
            PostXML pxml = (PostXML) request.getSession().getAttribute("feed");
            ArrayList<PostInfo> feed = pxml.getFeed();
            UserInfo u = Business.getUser((String) request.getSession().getAttribute("username"));
        %>
    <body>
        <div class="center">
            <h1>Welcome to <%=request.getSession().getAttribute("browseTo")%> Blog Page</h1>
            <form action = "Subscribe" method = "post">
                <tr>
                <input type="hidden" name="SubTog" value ="<%=(Boolean) request.getSession().getAttribute("subbed")%>">                        
                <td><input type="submit" value="<%if((Boolean) request.getSession().getAttribute("subbed")){%>subscribed<%}else{%>subscribe<%}%>"></td>
                </tr>
            </form>
            <div id="stats">
                <ul>
                    <li>Subscribers:[Subscriber Count] </li>
                    <li>Posts:[Length of Arraylist]</li>
                    <li>Collaborators:[Length of Arraylist]</li>
                </ul>
            </div>
        </div>
        <%
        if(feed != null){
            for (int i = 0; i < feed.size(); i++) {
                PostInfo p = feed.get(i);
        %>
        <div class="post">
            <div class="imagePost">
                <h2><%=p.getTitle()%></h2>
                <%
                    if (p.getType().equals("image")) {
                %>
                <img src="<%=p.getContent()%>" alt="sus image" id="images">
                <%--TODO ADD MORE INFO TO IMAGE POST--%>
                <div class="Date">
                    Date: <%=p.getDate()%>
                </div>
            </div>
            <%
            } else {
            %>
            <%--TODO TEXT POST FORMATTING--%>
            <div class="textPost">
                <%=p.getContent()%>
            </div>
            <div class="Date">
                Date: <%=p.getDate()%>
            </div>
            <%}%>
        </div>
        <%}}
        %>

    </body>
</html>
