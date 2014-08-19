/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.znufe.logic;

import edu.znufe.utilities.DbUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yanma
 */
public class Security {
    
    
    /**
     * login method 
     * test whether the username exists
     * @param nickName
     * @return the authenticated user
     * or return null
     */
    public User verifyUser(String userID){
        
        DbUtilities db = new DbUtilities();
        try {
            
            String sql = "SELECT u_id FROM weibo_topics.user WHERE u_id = '" + userID + "' ;";
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                String userId = rs.getString("u_id");
                User authenticatedUser = new User(userId);
                return authenticatedUser;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        return null;
    
    }
}
