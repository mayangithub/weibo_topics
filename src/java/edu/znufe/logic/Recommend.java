/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.znufe.logic;

import java.util.ArrayList;

/**
 *
 * @author yanma
 */
public class Recommend {
    
    public Recommend(User user, int typeID){
        
    }
    
    public ArrayList<Topic> recommendTopics(User user, int typeID){
        
        ArrayList<Topic> typeTopicList = new ArrayList<Topic>();
        ArrayList<Topic> recommendTopics = new ArrayList<Topic>();
        typeTopicList = user.findTopics(typeID);
        for(Topic topic: typeTopicList){
            if(topic.isRecommend(user)){
                recommendTopics.add(topic);
            }
        }
        return recommendTopics;
    }
    
    
    
    
    
    
}
