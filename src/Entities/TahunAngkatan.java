/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Tools.KoneksiDB;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import  java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NISSA
 */
public class TahunAngkatan {
    
    public int id_ta;
    public int tahun_angkatan;
    
    Connection _Cnn;
    KoneksiDB getCnn = new KoneksiDB();
    String query;
    public boolean isUpdate;
    PreparedStatement stat;
    ResultSet res;
    public DefaultTableModel tblthangkatan = new DefaultTableModel();
    public List<Object> list;
    
    public void select(){
        tblthangkatan.setColumnIdentifiers(new Object[] {"ID. TA ", "Tahun Angkatan"});
        try {
            _Cnn = getCnn.getConnection();
            query = "select * from tbthangkatan";
            stat = _Cnn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while (res.next()) {
                id_ta = res.getInt("id_ta");
                tahun_angkatan = res.getInt("tahun_angkatan");
                
                list.add(new Object[]{id_ta, tahun_angkatan} );
                
            }
            stat.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error method select() :"+ex);
        }
    }
    
    public void insert_update(){
        try {
            _Cnn = getCnn.getConnection();
            if(isUpdate == false) {
                query = "insert into tbthangkatan values (?,?) "; 
            }else{
                query = "update tbthangkatan set id_ta=?, tahun_angkatan=? "
                        + "where id_ta='"+id_ta+"' ";
            }
            stat = _Cnn.prepareStatement(query);
            stat.setInt(1, id_ta);
            stat.setInt(2, tahun_angkatan);
            stat.executeUpdate();
            stat.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error method insert_update() : "+ex);
        }
    }
    
    public void delete(int id_ta){
        try {
            _Cnn = getCnn.getConnection();
            query = "delete from tbthangkatan where id_ta='"+id_ta+"' ";
            stat = _Cnn.prepareStatement(query);
            stat.executeQuery();
            stat.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void createAutoID(){
        try {
            _Cnn = getCnn.getConnection();
            query = "select max(id_ta) from tbthangkatan";
            stat = _Cnn.prepareStatement(query);
            res = stat.executeQuery(query);
            if(res.first()){
                id_ta = res.getInt(1)+1;
            }else{
                id_ta = 1;
            }
            stat.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error method createAutoID() : "
                + ex, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
}
