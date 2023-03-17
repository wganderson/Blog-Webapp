<%-- 
    Document   : UserHomePage
    Created on : Feb 9, 2022, 7:56:51 PM
    Author     : student
--%>

<%@page import="ryerson.ca.lab4helper.UserInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discover</title>

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
            <%
                UserInfo temp;
                ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
                for(int i = 1; i < 6; i++){
                    temp = new UserInfo("User" + i, "password" + i, 0, 0);
                    userList.add(temp);
                }
            %>
            
        </style>
    </head>
    <body>
    <body>
        <div class="center">
            <h1>Welcome, <%=request.getAttribute("username")%> to Your Discover Page</h1>
        </div>
        <div class ="center">
            <table>
                <caption>Available Blogs</caption>
                <tr>
                    <th>Blog Name</th>
                    <th>Browse Content</th>
                </tr>
                <%
                    int j = 0;
                    for (j = 0; j < userList.size(); j++) {
                %>
                <form action = "BrowseUser" method = "post">
                    <tr>
                        <td><%=userList.get(j).getUsername()%></td>
                    <input type="hidden" name="browseTo" value ="<%=userList.get(j).getUsername()%>">                        
                    <td><input type="submit" value="Browse <%=userList.get(j).getUsername()%>"></td>
                    </tr>
                </form>
                <%}
                %>

            </table>    
        </div>
    </body>
</html>
