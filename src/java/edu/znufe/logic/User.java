/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.znufe.logic;

import edu.znufe.utilities.DbUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yanma
 */
public class User {
    private String userId;
    private String username;
    private String gender;
    private String location;
    private String university;
    
    ArrayList<Topic> topics = new ArrayList<>();
    ArrayList<Weibo> weiboList = new ArrayList<Weibo>();
    
    public User(String userId){
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT * FROM weibo_topics.user WHERE u_id = '" + userId + "' ;";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.userId = rs.getString("u_id");
                this.username = rs.getString("u_nick");
                this.gender = rs.getString("u_gender");
                this.location = rs.getString("u_location");
                this.university = rs.getString("u_university");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
    
    
    
    /**
     * search topic by query
     * @param searchContent
     * @return topic list
     * topics contain query
     */
    public ArrayList<Topic> findTopics(String searchContent){
        DbUtilities db = new DbUtilities();
        topics = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM weibo_topics.topic WHERE t_name LIKE '%"+searchContent+"%'";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String topicId = rs.getString("t_id");
                Topic newTopic = new Topic(topicId);
                topics.add(newTopic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return topics;
        
    }
    
    /**
     * find topics by type
     * @param typeId
     * @return topic list
     */
    public ArrayList<Topic> findTopics(int typeId){
        DbUtilities db = new DbUtilities();
        topics = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM weibo_topics.topic WHERE t_typeid  = "+typeId+" ;";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String topicId = rs.getString("t_id");
                Topic newTopic = new Topic(topicId);
                topics.add(newTopic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return topics;
        
    }
    
    /**
     * find all topics
     * @return 
     */
    public ArrayList<Topic> findTopics(){
        DbUtilities db = new DbUtilities();
        topics = new ArrayList<Topic>();
        try {
            
            String sql = "SELECT * FROM weibo_topics.topic";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String topicId = rs.getString("t_id");
                Topic newTopic = new Topic(topicId);
                topics.add(newTopic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return topics;
        
    }
    
    /**
     * get user's all weibo
     * @param userId
     * @return weibo list
     */
    public ArrayList<Weibo> getAllWeiboByUserid(String userId){
        DbUtilities db = new DbUtilities();
        weiboList = new ArrayList<Weibo>();
        try {
            
            String sql = "SELECT * FROM weibo_topics.weibo WHERE w_uid='"+userId+"' ;";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String weiboId = rs.getString("w_id");
                Weibo newWeibo = new Weibo(weiboId);
                weiboList.add(newWeibo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Weibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return weiboList;
    }
    
    
    public ArrayList<Weibo> getAllWeiboByTopic(String topicId){
        DbUtilities db = new DbUtilities();
        weiboList = new ArrayList<Weibo>();
        try {
            
            String sql = "SELECT * FROM weibo_topics.weibo WHERE w_tid='"+topicId+"' ;";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String weiboId = rs.getString("w_id");
                Weibo newWeibo = new Weibo(weiboId);
                weiboList.add(newWeibo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Weibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return weiboList;
    }
    
    /**
     * test whether user participated in the topic and publish a weibo comment
     * @param topicId
     * @param userId
     * @return true if user participated
     * return false if user didn't participate
     */
    public boolean testTopic(String topicId, String userId){
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT * FROM weibo_topics.weibo WHERE w_tid='"+topicId+"' AND w_uid='"+userId+"' ;";
            ResultSet rs = db.getResultSet(sql);
            if(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Weibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return false;
    }
    
    
    
    /**
     * whether user commented same type topics
     * by type and user ID, for recommend
     * @param typeId
     * @param userID
     * @return true or false
     */
    public boolean isCommented(int typeId, String userID){
        DbUtilities db = new DbUtilities();
        topics = new ArrayList<>();
        boolean flag = false;
        try {
            ArrayList<Topic> topicList = findTopics(typeId);
                for(Topic topic: topicList){
                    String sql = "SELECT * FROM weibo_topics.weibo WHERE w_tid  = '"+topic.getTopicId()+"' AND w_id='"+userID+"';";
                    ResultSet rs = db.getResultSet(sql);
                    if(rs.next()){
                        flag = true;
                        return true;
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return flag;
        
    }
    
    
    
    /**
     * is user follow topic user
     * @param userID
     * @param topicUserID
     * @return 
     */
    public boolean isFollow(String userID, String topicUserID){
        DbUtilities db = new DbUtilities();
        boolean follow = false;
        try {
            String sql = "SELECT follow FROM weibo_topics.follow WHERE u_id='"+userID+"' AND t_uid='"+topicUserID+"';";
            ResultSet rs = db.getResultSet(sql);
            if(rs.next()){
                if(rs.getInt("follow")==1){
                    follow = true;
                }
                return follow;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return follow;
    }
    
    
    
    
    /**
     * is user followed by topic user
     * @param userID
     * @param topicUserID
     * @return 
     */
    public boolean isFollowed(String userID, String topicUserID){
        DbUtilities db = new DbUtilities();
        boolean followed =false;
        try {
            String sql = "SELECT followed FROM weibo_topics.follow WHERE u_id='"+userID+"' AND t_uid='"+topicUserID+"';";
            ResultSet rs = db.getResultSet(sql);
            if(rs.next()){
                if(rs.getInt("followed")==1){
                    followed=true;
                }
                return followed;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return followed;
    }
    
}
