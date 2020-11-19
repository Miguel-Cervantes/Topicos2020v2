package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
    private TableView<TipoPlatilloDAO> tbvTipoPlatillo;
    int opc;


    public FrmTipoPlatillo(TableView<TipoPlatilloDAO> tbvTipoPlatillos, TipoPlatilloDAO objTp) {

        if(objTp!=null){
            this.objTipoPlatillo=objTp; opc=1;
        }else{
            this.objTipoPlatillo=new TipoPlatilloDAO(); opc=2;
        }

        CrearGUI();
        this.setTitle("gestion de Tipo Platillos");
        this.setScene(escena);
        this.show();

        this.tbvTipoPlatillo=tbvTipoPlatillos;
    }

    private void CrearGUI() {
        txtNameTipoPlatillo=new TextField();
        txtNameTipoPlatillo.setText(objTipoPlatillo.getDsc_tipo());

        btnGuardar=new Button("Guardar Tipo Platillo");
        btnGuardar.setOnAction(event -> {Guardar();});

        vBox=new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(txtNameTipoPlatillo,btnGuardar);
        escena=new Scene(vBox,265,250);
        escena.getStylesheets().add("sample/assets/CSS´s/FrmTipoPlatillos.css");

    }

    private void Guardar() {

        objTipoPlatillo.setDsc_tipo(txtNameTipoPlatillo.getText());
        objTipoPlatillo.setId_tipo(1);

        if(opc!=1){
            objTipoPlatillo.insTipo();
        }else{
            objTipoPlatillo.upTipo();
        }

        tbvTipoPlatillo.setItems(objTipoPlatillo.getAllTipo());
        tbvTipoPlatillo.refresh();
        this.close();
    }
}