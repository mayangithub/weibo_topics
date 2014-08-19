<%-- 
    Document   : topicdetail
    Created on : May 10, 2014, 12:34:46 AM
    Author     : yanma
--%>

<%@page import="edu.znufe.logic.Type"%>
<%@page import="edu.znufe.logic.Weibo"%>
<%@page import="edu.znufe.logic.Topic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.znufe.logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userID = "";
    String username = "";
    User user = null;
    String topicID = "";
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
        <title>活动详情</title>
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
                            <ul style="padding-left: 0px;">
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
                                if(request.getParameter("topicid")!=null){
                                    topicID = request.getParameter("topicid");
                                    Topic topic = new Topic(topicID);
                                    session.setAttribute("topic", topic);
                                    out.println("<div class='detail' align='left'>");
                                    out.println("<ul>");
                                    out.println("<li>活动名称： #"+topic.getTopicName()+"#</li>");
                                    out.println("<li>平均分： "+topic.getRate()+"</li>");
                                    out.println("<li>参与人数： "+topic.getNumber()+"</li>");
                                    out.println("<li>活动类型： "+(new Type(topic.getTypeId())).getTypeName()+"</li>");
                                    out.println("<li>活动简介： "+topic.getContent()+"</li>");
                                    out.println("</ul>");
                                    System.out.println(topicID);
                                    System.out.println(user.getUserId());
                                    if(user.testTopic(topicID, userID)){
                                        out.println("<div>");
                                        out.println("<form method='post' action='/Weibo_Topic/RateServlet' >");
                                        out.println("<label>评分： </label>");
                                        out.println("<input type='radio' name='rate' value='1' required/> 1分 ");
                                        out.println("<input type='radio' name='rate' value='2' required/> 2分 ");
                                        out.println("<input type='radio' name='rate' value='3' required/> 3分 ");
                                        out.println("<input type='radio' name='rate' value='4' required/> 4分 ");
                                        out.println("<input type='radio' name='rate' value='5' required/> 5分 ");
                                        out.println("<input type='submit' value='投上一票' class='btn-default'/>");
                                        out.println("</form>");
                                        out.println("</div><br><br>");//rate form ends
                                    }else{
                                        out.println("<h6>还没有参与这个活动讨论，所以不能参与评分哦~~</h6><br>");
                                    }
                                    
                                    out.println("</div>");//topic detail ends
                                    %>
                                    <%
                                        out.println("<div class='publishcomment' align='center'>");
                                        out.println("<form method='post' action='/Weibo_Topic/CommentServlet'>");
                                        out.println("<textarea rows='4' cols='50' name='comment' required></textarea><br><br>");
                                        out.println("<input type='submit' value='Publish' class='btn-default'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                                        out.println("<input type='reset' value='Clear' class='btn-default'/>");
                                        out.println("</form>");
                                        out.println("</div>");
                                        
                                    %>
                                    <%
                                    weiboList = user.getAllWeiboByTopic(topicID);
                                    if(weiboList!=null){
                                        out.println("<div class='comment'>");
                                        out.println("<table class='table table-striped table-hover'>");
                                        out.println("<tr><th colspan='2'>评论详情： </th></tr>");
                                        for(Weibo weibo:weiboList){
                                            out.println("<tr>");
                                            out.println("<td>"+(new User(weibo.getUserId())).getUsername()+"</td>");
                                            out.println("<td>"+weibo.getWeiboTime()+"</td>");
                                            out.println("</tr><tr>");
                                            out.println("<td colspan='2'>"+weibo.getWeiboContent()+"</td></tr>");
                                            
                                        }
                                        out.println("</table>");
                                        out.println("</div>");//comment ends
                                    }else{
                                        out.println("<h3>---还没有评论哦---</h3>");
                                    }
                                }else{
                                    out.println("<h3>---活动不存在---</h3>");
                                }
                            %>
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
