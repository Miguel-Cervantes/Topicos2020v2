package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.TipoPlatilloDAO;



public class FrmTipoPlatillo extends Stage {
    private TextField txtNameTipoPlatillo;
    //Combobox?
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private TipoPlatilloDAO objTipoPlatillo;


    public FrmTipoPlatillo() {
        CrearGUI();
        this.setTitle("gestion de Tipo Platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        txtNameTipoPlatillo=new TextField();
        btnGuardar=new Button("Guardar Tipo Platillo");
        btnGuardar.setOnAction(event -> {Guardar();});
        vBox=new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(txtNameTipoPlatillo,btnGuardar);
        escena=new Scene(vBox,265,250);
        escena.getStylesheets().add("sample/assets/CSS´s/FrmTipoPlatillos.css");

    }

    private void Guardar() {
        objTipoPlatillo =new TipoPlatilloDAO();
        objTipoPlatillo.setDsc_tipo(txtNameTipoPlatillo.getText());
        objTipoPlatillo.setId_tipo(1);
        objTipoPlatillo.insTipo();


        txtNameTipoPlatillo.clear();
    }
}