package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.components.ButtonCustome;
import sample.models.PlatillosDAO;

import static jdk.nashorn.internal.objects.NativeFunction.call;

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
        btnNuevo.setOnAction(event -> {new FrmPlatillos(); });
        vBox=new VBox();
        vBox.getChildren().addAll(tbvPlatillo,btnNuevo);
        escena=new Scene(vBox,465,250);
        escena.getStylesheets().add("sample/assets/CSS´s/FrmPlatillos.css");

    }

    private void CrearTabla() {
        TableColumn<PlatillosDAO,Integer> tbcIdPlatillo=new TableColumn<>("ID");
        tbcIdPlatillo.setPrefWidth(30);
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_platillo"));

        TableColumn<PlatillosDAO,String> tbcNomPlatillo =new TableColumn<>("Nombre Platillo");
        tbcNomPlatillo.setPrefWidth(150);
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nombre_platillo"));

        TableColumn<PlatillosDAO,Float> tbcPrecioPlatillo=new TableColumn<>("Precio");
        tbcPrecioPlatillo.setPrefWidth(70);
        tbcPrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<PlatillosDAO,String> tbcEditar = new TableColumn<>("Editar" );
        tbcEditar. setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
            @Override
                public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                    return new ButtonCustome(1);
                }
            }
        );

        TableColumn<PlatillosDAO,String> tbcBorrar = new TableColumn<>("Borrar" );
        tbcBorrar. setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
            @Override
                public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                    return new ButtonCustome(2);
                }
            }
        );


        tbvPlatillo.getColumns().addAll(tbcIdPlatillo,tbcNomPlatillo,tbcPrecioPlatillo,tbcEditar,tbcBorrar);
        tbvPlatillo.setItems(objPDAO.getAllPlatillo());

    }

}
