package sample.ui;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.components.ButtonCustomeTP;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;




public class FrmPlatillos extends Stage {
    private TextField txtPlatillo, txtPrecio;
    private ComboBox<TipoPlatilloDAO> cbxTipo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private PlatillosDAO objPlatillo;
    private TableView<PlatillosDAO> tbvPlatillos;

    public FrmPlatillos(TableView<PlatillosDAO> tbvPlatillos,PlatillosDAO objPlatillo) {

        if(objPlatillo!=null){
            this.objPlatillo=objPlatillo;
        }else{ this.objPlatillo=new PlatillosDAO(); }

        CrearGUI();
        this.setTitle("gestion de platillos");
        this.setScene(escena);
        this.show();

        this.tbvPlatillos= tbvPlatillos;
    }

    private void CrearGUI() {
        txtPlatillo = new TextField();
        txtPlatillo.setText(objPlatillo.getNombre_platillo());
        txtPrecio = new TextField();
        txtPrecio.setText(String.valueOf(objPlatillo.getPrecio()));
        cbxTipo = new ComboBox<>();
        cbxTipo.setItems(new TipoPlatilloDAO().getAllTipo());
        btnGuardar = new Button("Guardar platillo");
        btnGuardar.setOnAction(event -> { Guardar();});

        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(txtPlatillo, txtPrecio, cbxTipo, btnGuardar);
        escena=new Scene(vBox,265,250);
        escena.getStylesheets().add("sample/assets/CSS´s/FrmPlatillos.css");
    }

    private void Guardar(){

        objPlatillo.setNombre_platillo(txtPlatillo.getText());
        objPlatillo.setPrecio(Float.parseFloat(txtPrecio.getText()));
        objPlatillo.setId_tipo(1);//valor fijo temporalmente para poder hacer la insercion

        if(objPlatillo==null) {//PROCESO PLATILLO NUEVO
            objPlatillo.insPlatillo();
        }else{                  //PROCESO PARA ACTUALIZAR EL PLATILLO
            objPlatillo.updPlatillo();
        }

        tbvPlatillos.setItems(objPlatillo.getAllPlatillo());
        tbvPlatillos.refresh();
        this.close();
    }
}
