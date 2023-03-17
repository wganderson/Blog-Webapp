<%-- 
    Document   : LoginFailed
    Created on : Feb 9, 2022, 1:59:17 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>

    <style>
        body {
            background-color: whitesmoke;
        }
        
        .center {
            margin: auto;
            width: 50%;
            border-width: 2px;
            border-style: solid;
            border-color: black;
            padding: 10px;
            text-align: center;
            border-radius: 15px;
            background-color: black;
            color: white;
        }
        
        .Login {
            text-align: left;
            padding-left: 20px;
        }
        
        input[type=text] {
            width: 70%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        input[type=submit] {
            width: 20%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        
        input[type=submit]:hover {
            background-color: #45a049;
        }
        
        #errorMessage {
            color: red;
        }
    </style>
</head>

<body>

    <!--center div-->
    <div class="center">
        <h1>Welcome to our Blog Site</h1>
        <h2>💻 Log-in </h2>

        <div class="loginForm">
            <form action="FrontEnd" method="post">
                <div class="Login">
                    <label for="username">Enter Username:</label>
                    <input type="text" id="username" name="username" size="30">
                </div>

                <div class="Login">
                    <label for="password">Enter Password:</label>
                    <input type="text" id="password" name="password" size="30">
                </div>

                <input type="submit" value="Login">
            </form>
            
            <div id="errorMessage">
                <h4> You entered the wrong information. Try again </h4>
            </div>
        </div>
    </div>
</body>

</html>