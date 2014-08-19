<%-- 
    Document   : topicindex
    Created on : May 10, 2014, 12:40:58 AM
    Author     : yanma
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="edu.znufe.logic.Topic"%>
<%@page import="edu.znufe.logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userID = "";
    String username = "";
    User user = null;
    ArrayList<Topic> topics = new ArrayList<Topic>();
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }else{
        user = (User) session.getAttribute("user");
        userID = user.getUserId();
        user = new User(userID);
        username = user.getUsername();
        System.out.println(username);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>微博活动首页</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="bodywrap">
            <div class="main">
                <div class="header">
                    <div class='logo'></div>
                    
                </div>
                <div class="topicindex_mainbody">
                    <!--   left navigation bar    -->
                    <div class='left'>
                        <div class="part0">
                            <ul>
                                <li>ID: <%=userID%></li>
                                <li><%=username%></li>
                            </ul>
                        </div>
                        <div class='part1'>
                            <ul class="part1list">
                                <li><a href="">全部活动</a></li>
                                <li><a href="mytopic.jsp?userID=<%=userID%>">我的活动</a></li>
                                <li><button>发布新活动</button></li>
                            </ul>
                        </div>
                        <div class="part2">
                            <h3>活动分类</h3>
                            <ul style="padding-left: 0px;">
                                <li><a href="topiclist.jsp?type=1">晚会</a></li>
                                <li><a href="topiclist.jsp?type=2">体育</a></li>
                                <li><a href="topiclist.jsp?type=3">讲座</a></li>
                                <li><a href="topiclist.jsp?type=4">会议</a></li>
                                <li><a href="topiclist.jsp?type=5">新闻</a></li>
                                <li><a href="topiclist.jsp?type=6">竞赛</a></li>
                                <li><a href="topiclist.jsp?type=7">其他</a></li>
                            </ul>
                        </div>
                        <div class='part3'>
                            <h3><a href="topicrecommend.jsp">推荐活动</a></h3>
                            <ul >
                                <li></li>
                                <li></li>
                            </ul>
                        </div>
                        <div class="part4">
                        <a href="index.jsp"><button>Log out</button></a>
                        </div>
                    </div>
                    <!--  / left navigation bar    -->
                    <!--    main topics list   -->
                    <div class='center'>
                        <div class='search'>
                            <form action='topicsearch.jsp' method="get">
                            <label>搜索活动关键字: 
                            <input type='text'  required name='search' id='search' size="30"/></label>
                            <button type="submit" hidden></button>
                            </form>
                        </div>
                        
                        
                        <div class="result" align="center">
                            <table class="table table-striped table-hover ">
                                <tr>
                                    <th>活动名称</th>
                                    <th>简介</th>
                                </tr>
                                <%
                                        topics = user.findTopics();
                                        if(topics!=null){
                                            for(Topic topic: topics){
                                                String topicID = topic.getTopicId();
                                                out.println("<tr>");
                                                out.println("<td><a href='topicdetail.jsp?topicid="+topicID+"'>"+topic.getTopicName()+"</a></td>");
                                                out.println("<td>"+topic.getContent()+"</td>");
                                                out.println("</tr>");
                                            }
                                        }else{
                                            out.println("<h3>没有活动哦</h3>");
                                        
                                    }
                                %>
                            </table>
                        </div>
                        
                    </div>
                    
                    
                    <!-- / main topics list  -->
                </div>
            </div>
        </div>
        <div class="footerwrap">
            <div class="footer">
                
            </div>
        </div>
        
    </body>
</html>
