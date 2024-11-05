
package kelas;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Coldp
 */
public class user {
    String user_name,user_email,user_password,user_fullname;
    int user_status;
    
    private Connection konek;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    
    public user() throws SQLException{
        koneksi koneksi = new koneksi();
        konek = koneksi.konekDB();
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
    
    public void tambahUser(){
        query = "INSERT INTO user VALUES (?,?,MD5(?),?,?)";
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, user_name);
            ps.setString(2, user_email);
            ps.setString(3, user_password);
            ps.setString(4, user_fullname);
            ps.setInt(5, user_status);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "user berhasil ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "user gagal ditambahkan");
        }
    }
    public ResultSet tampilUser(){
        query = "SELECT * FROM user";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "data gagal ditampilkan");
        }
        
        return rs;
    }
    
    public void hapusUser(){
       query = "DELETE FROM user WHERE user_name = ?";
        try {

            ps = konek.prepareStatement(query);

            ps.setString(1, user_name);

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "User Berhasil Di Hapus");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "User Gagal Di Hapus");
        }
    }
    
    public void ubahUser(){
       if (user_password.equals("")) {

            query = "UPDATE user SET user_email = ?,"
                    + " user_fullname = ?,"
                    + " user_status = ?"
                    + " WHERE user_name = ?";
            try {

                ps = konek.prepareStatement(query);

                ps.setString(1, user_email);
                ps.setString(2, user_fullname);
                ps.setInt(3, user_status);
                ps.setString(4, user_name);

                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "User Berhasil Di Ubah");

            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "User Gagal Di Ubah");
            }

        } else {
             query = "UPDATE user SET user_email = ?,"
                    + " user_fullname = ?,"
                    + " user_status = ?,"
                    + " user_password = MD5(?)"
                    + " WHERE user_name = ?";
            try {

                ps = konek.prepareStatement(query);

                ps.setString(1, user_email);
                ps.setString(2, user_fullname);
                ps.setInt(3, user_status);
                ps.setString(4, user_password);
                ps.setString(5, user_name);

                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "User Berhasil Di Ubah");

            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "User Gagal Di Ubah");
            }
        }

    }
 
   public void login (){
       
   }
}
    
