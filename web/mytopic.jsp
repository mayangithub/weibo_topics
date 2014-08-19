<%-- 
    Document   : mytopic
    Created on : May 10, 2014, 12:32:19 AM
    Author     : yanma
--%>

<%@page import="edu.znufe.logic.Weibo"%>
<%@page import="edu.znufe.logic.Topic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.znufe.logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userID = "";
    String username = "";
    User user = null;
    ArrayList<Weibo> weiboList = new ArrayList<Weibo>();
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }else{
        user = (User) session.getAttribute("user");
        userID = user.getUserId();
        username = user.getUsername();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>我的活动</title>
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
                                <li><a href="topicindex.jsp">全部活动</a></li>
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
                       
                            
                                
                                    
                                    
                                
                                <%
                                  weiboList = user.getAllWeiboByUserid(userID);
                                  System.out.println(userID);
                                  System.out.println(user.getAllWeiboByUserid(userID));
                                  if(weiboList!=null){
                                      out.println("<div class='result' align='center'>");
                                      out.println("<table class='table table-striped table-hover '>");
                                      out.println("<tr>");
                                      out.println("<th>话题</th>");
                                      out.println("<th>评论内容</th>");
                                      out.println("</tr>");
                                    for(Weibo weibo: weiboList){
                                        out.println("<tr>");
                                        Topic topic = new Topic(weibo.getTopicId());
                                        out.println("<td>#"+topic.getTopicName()+"#</td>");
                                        out.println("<td>"+weibo.getWeiboContent()+"</td>");
                                        out.println("</tr>");
                                    }
                                    out.println("</table>");
                                  }else{
                                      out.println("<h3>还没有参与话题哟~~</h3>");
                                  }
                                  
                                %>
                                
                            
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
