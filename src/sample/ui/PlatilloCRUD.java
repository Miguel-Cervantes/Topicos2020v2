package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class PlatilloCRUD extends Stage {

    private VBox vBox;
    private TableView<PlatillosDAO> tbvPlatillo;
    private Button btnNuevo;
    private Scene escena;
    private PlatillosDAO objPDAO;

    public PlatilloCRUD(){
        objPDAO=new PlatillosDAO();
        CrearGUI();
        this.setTitle("Administracion de Platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        tbvPlatillo=new TableView<>();
        CrearTabla();
        btnNuevo=new Button("Nuevo Platillo");
        //btnNuevo.setOnAction(event -> AgregarPlatillo());
        vBox=new VBox();
        vBox.getChildren().addAll(tbvPlatillo,btnNuevo);
        escena=new Scene(vBox,300,250);

    }

    private void CrearTabla() {
        TableColumn<PlatillosDAO,Integer> tbcIdPlatillo=new TableColumn<>("ID");
        tbcIdPlatillo.setPrefWidth(25);
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_platillo"));

        TableColumn<PlatillosDAO,String> tbcNomPlatillo =new TableColumn<>("Nombre Platillo");
        tbcNomPlatillo.setPrefWidth(150);
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nombre_platillo"));

        TableColumn<PlatillosDAO,Float> tbcPrecioPlatillo=new TableColumn<>("Precio");
        tbcPrecioPlatillo.setPrefWidth(70);
        tbcPrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbvPlatillo.getColumns().addAll(tbcIdPlatillo,tbcNomPlatillo,tbcPrecioPlatillo);
        tbvPlatillo.setItems(objPDAO.getAllPlatillo());

    }

}
