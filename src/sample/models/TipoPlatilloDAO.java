package sample.models;

import java.sql.Statement;

public class TipoPlatilloDAO {
    private int id_tipo;
    private String dsc_tipo;

    public int getId_tipo() { return id_tipo; }
    public void setId_tipo(int id_tipo) { this.id_tipo = id_tipo; }
    public String getDsc_tipo() { return dsc_tipo; }
    public void setDsc_tipo(String dsc_tipo) { this.dsc_tipo = dsc_tipo; }

    public void insTipo(){
        try{
            String query="INSERT INTO tbl_tipoplatillo (dsc_tipo) VALUES('"+dsc_tipo+"')";
            Statement stms= Conexion.con.createStatement();
            stms.executeUpdate(query);

        }catch(Exception e){ e.printStackTrace();}
    }

    public void upTipo() {//Siempre un where para que sea un solo registro modoficado y no todos tambien el de delete
        try {
            String query = "UPDATE tbl_tipoplatillo SET dsc_tipo ='" + dsc_tipo + "' WHERE id_tipo= " + id_tipo;
            Statement stms= Conexion.con.createStatement();
            stms.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delTipo(){
        try{
            String query="DELETE FROM tbl_tipoplatillo WHERE id_tipo = "+id_tipo;
            Statement stms= Conexion.con.createStatement();
            stms.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }

    public void getAllTipo(){ }
    public void getTipo(){ }

}
