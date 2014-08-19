/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.znufe.logic;

/**
 *
 * @author yanma
 */
public class Follow {
    String userID;
    String topicUserID;
    int follow;
    int followed;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTopicUserID() {
        return topicUserID;
    }

    public void setTopicUserID(String topicUserID) {
        this.topicUserID = topicUserID;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }
    
    
    
    
    
}
