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
public class Topic {
    private String topicId;
    private String topicName;
    private int typeId;
    private String topicUserId;
    private String content;
    private int total;
    private int number;
    private double rate;
    ArrayList<Topic> topics = new ArrayList<>();
    
    public Topic(String topicId){
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT * FROM weibo_topics.topic WHERE t_id = '"+topicId+"';";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.topicId = rs.getString("t_id");
                this.content = rs.getString("t_content");
                this.number = rs.getInt("t_number");
                this.rate = rs.getDouble("t_rate");
                this.topicName = rs.getString("t_name");
                this.topicUserId = rs.getString("t_uid");
                this.total = rs.getInt("t_total");
                this.typeId = rs.getInt("t_typeid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
    }
    
    
    public void updateRate(String topicId, int score){
        DbUtilities db = new DbUtilities();
        String sql = "UPDATE weibo_topics.topic SET t_total=t_total+"+score+", t_number = t_number+1, t_rate = t_total/t_number WHERE t_id = '"+topicId+"';";
        db.executeQuery(sql);
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(String topicUserId) {
        this.topicUserId = topicUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }
    
    
    
    public boolean isRecommend(User user){
        boolean isMember = false;
        boolean sameType = false;
        String gender;
        boolean isFollow;
        boolean isFollowed;
        String location;
        System.out.println("userID: "+user.getUserId()+" University: "+user.getUniversity());
        if(user.getUniversity().equals("ZNUFE")){
            isMember = true;
        }
        if(user.isCommented(this.getTypeId(), user.getUserId())){
            sameType = true;
        }
        gender = user.getGender();
        isFollow = user.isFollow(user.getUserId(), this.getTopicUserId());
        isFollowed = user.isFollowed(user.getUserId(), this.getTopicUserId());
        location = user.getLocation();
        
        if(isMember==false){
            if(sameType==false){
                if(isFollow==false){
                    if(location.equals("Hubei Wuhan")){
                        if(gender.equals("female")){
                            return false;
                        }else{
                            return false;
                        }//gender ends
                    }//location ends
                    else{
                        if(gender.equals("female")){
                            return false;
                        }else{
                            return false;
                        }//gender ends
                    }//location ends
                }//follow false ends
                else{
                    if(gender.equals("male")){
                        if(isFollowed==true){
                            return false;
                        }else{
                            return false;
                        }//follow ends
                    }//gender male ends
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return false;
                        }//location ends
                    }//gender female ends
                }//follow true ends
            }//same type false ends
            else{
                if(gender.equals("male")){
                    if(isFollow==false){
                        if(isFollowed==false){
                            return false;
                        }else{
                            return false;
                        }//is followed ends
                    }//is follow false ends
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return false;
                        }//location ends
                    }//is follow true ends
                }//gender male ends
                else{
                    if(isFollow==false){
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return false;
                        }//location ends
                    }//is follow false ends
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return true;
                        }//location ends
                    }//is follow true ends
                }//gender female ends
            }//same type true ends
        }//member false ends
        else{
            if(sameType==false){
                if(isFollowed==false){
                    if(gender.equals("male")){
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return false;
                        }//location ends
                    }//gender male ends
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return true;
                        }//location ends
                    }//gender female ends
                }//is followed false
                else{
                    if(isFollow==false){
                        if(gender.equals("male")){
                            return true;
                        }else{
                            return true;
                        }//gender ends
                    }//is follow false
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return true;
                        }//location ends
                    }//is follow true
                }//is followed true
            }//same type false ends
            else{
                if(gender.equals("male")){
                    if(isFollow==false){
                        if(isFollowed==false){
                            return false;
                        }else{
                            return false;
                        }//is followed ends
                    }//follow false ends
                    else{
                        if(location.equals("Hubei Wuhan")){
                            return false;
                        }else{
                            return false;
                        }//location ends
                    }//follow true ends
                }//gender male ends
                else{
                    if(location.equals("Hubei Wuhan")){
                        if(isFollow==false){
                            return false;
                        }else{
                            return false;
                        }//is follow ends
                    }//location equal ends
                    else{
                        if(isFollow==false){
                            return true;
                        }else{
                            return false;
                        }//is follow ends
                    }//location not equal ends
                }//gender female ends
            }//same type true ends
        }//member true ends
        
    }
    
    
    
}
