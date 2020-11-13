package sample.ui;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class FrmPlatillos extends Stage {
    private TextField txtPlatillo, txtPrecio;
    private ComboBox<TipoPlatilloDAO> cbxTipo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private PlatillosDAO objPlatillo;

    public FrmPlatillos() {
        CrearGUI();
        this.setTitle("gestion de platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        txtPlatillo = new TextField("Platillo");
        txtPrecio = new TextField("Precio");
        cbxTipo = new ComboBox<>();
        btnGuardar = new Button("Guardar platillo");
        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(txtPlatillo, txtPrecio, cbxTipo, btnGuardar);
        escena=new Scene(vBox,265,250);
        escena.getStylesheets().add("sample/assets/CSS´s/FrmPlatillos.css");
    }

    private void Guardar(){
        objPlatillo=new PlatillosDAO();
        objPlatillo.setNombre_platillo(txtPlatillo.getText());
        objPlatillo.setPrecio(Float.parseFloat(txtPrecio.getText()));
        objPlatillo.setId_tipo(1);//valor fijo temporalmente para poder hacer la insercion
        objPlatillo.insPlatillo();
    }
}
