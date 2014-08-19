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
public class Type {
    private int typeId;
    private String typeName;
    
    public Type(int typeId){
        DbUtilities db = new DbUtilities();
        try {
            String sql = "SELECT * FROM weibo_topics.type WHERE ty_id = "+typeId;
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.typeId = rs.getInt("ty_id");
                this.typeName = rs.getString("ty_name");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeDbConnection();
        }
        
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
 
    
    
    
    
    
}
