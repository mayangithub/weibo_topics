<%-- 
    Document   : index
    Created on : May 11, 2014, 3:22:09 AM
    Author     : yanma
--%>

<%@page import="edu.znufe.logic.User"%>
<%@page import="edu.znufe.logic.Security"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String userID = "";
    session.setAttribute("user", null);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN " "http://www.w3.org/TR/html4/loose.dtd "> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录界面</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="bodywrap">
            <div class="main">
                <div class="header">
                    <div class='logo'></div>
                    <div><h1 style='color: white;' class="loginh1">Sina Weibo Login Page</h1></div>
                </div>
                <div class="mainbody">
                    <div id="login_form">
                    <form action="" method="get" >
                        <label>User ID: &nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input type="text" value="" name="userid" required class='form-control'/><br><br><br>
                        <input type="submit" value="Login" class="btn-default"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="Clear" class="btn-default"/>
                    </form>
                    </div>
                    
                    <%
                        String userid = "";
                        session.setAttribute("user", null);
                        session.setAttribute("userID", null);
                        if(request.getParameter("userid")!=null){
                            userid = request.getParameter("userid");
                            System.out.println(userid);
                            Security security = new Security();
                            User user = security.verifyUser(userid);
                            if(user!=null){
                                session.setAttribute("user", user);
                                userID = user.getUserId();
                                session.setAttribute("userID", userID);
                                out.println("<script language='javascript'>alert('Login successfully~')</script>");
                                response.sendRedirect("topicindex.jsp?userID="+userID);
                            }else{
                                out.println("<script language='javascript'>alert('Login failed.')</script>");
                            }
                        }
                    %>
                </div>
            </div>

        </div>
        <div class="footerwrap">
            <div class="footer">
                
            </div>
        </div>
        
    </body>
</html>


