package Tools;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author NISSA
 */
public class KoneksiDB {
    
    public Connection getConnection() throws SQLException{ // method koneksi DB
       Connection cnn;  //mendeklarasikan class connection
    try{
        String server = "jdbc:mysql://localhost/dbsiakadv1_161530037"; //nama server dimana menyimpan lokasi database
        String drever = "com.mysql.jdbc.Driver";    //nama driver koneksi database mysql
        Class.forName(drever);      //eksekusi dirver koneksi 
        cnn = DriverManager.getConnection(server, "root", "");  //inisialisasi / pemberian nilai cnn koneksi 
        return cnn;
        
    }catch(SQLException | ClassNotFoundException se){  //fungsi catch : menampilkan error pada syntak dalam try
        JOptionPane.showMessageDialog(null, "Error koneksi database : "+se);
        return null;
        
    }
    }
    
}
