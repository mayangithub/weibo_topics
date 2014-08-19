/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.znufe.logic;

import edu.znufe.utilities.DbUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yanma
 */
public class Weibo {
    private String userId;
    private String topicId;
    private Date weiboTime;
    private String weiboId;
    private String weiboContent;
    ArrayList<Weibo> weiboList = new ArrayList<Weibo>();
    
    public Weibo(String weiboId){
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT * FROM weibo_topics.weibo WHERE w_id = '" +weiboId+"';";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.weiboId = rs.getString("w_id");
                this.topicId = rs.getString("w_tid");
                this.userId = rs.getString("w_uid");
                this.weiboTime = rs.getTimestamp("w_time");
                this.weiboContent = rs.getString("w_content");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Weibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
    }
    
    public Weibo(String userId, String topicId, Date weiboTime, String weiboId){
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT * FROM weibo_topics.weibo WHERE w_tid='"+topicId+"' AND w_uid = '"+userId + "' AND w_time="+weiboTime+" AND w_id = '" +weiboId+"';";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.weiboId = rs.getString("w_id");
                this.topicId = rs.getString("w_tid");
                this.userId = rs.getString("w_uid");
                this.weiboTime = rs.getDate("w_time"); 
                this.weiboContent = rs.getString("w_content");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Weibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
    }
    
    
    /**
     * publish new comment weibo
     * @param userId
     * @param topicId
     * @param content 
     * time current date and time
     */
    public Weibo(String userId, String topicId, String content){
        DbUtilities db = new DbUtilities();
        String weiboID = UUID.randomUUID().toString();
        String sql = "INSERT INTO weibo_topics.weibo (w_id, w_uid, w_tid, w_time, w_content) VALUES ('"+weiboID+"', '"+userId+"', '"+topicId+"', NOW(), '"+content+"');";
        db.executeQuery(sql);
    }
    
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Date getWeiboTime() {
        return weiboTime;
    }

    public void setWeiboTime(Date weiboTime) {
        this.weiboTime = weiboTime;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    
    
    
    
    
    
}
