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
import sample.components.ButtonCustomeTP;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class TipoPlatilloCRUD extends Stage {

    private VBox vBox;
    private TableView<TipoPlatilloDAO> tbvTipoPlatillo;
    private Button btnNuevo;
    private Scene escena;
    private TipoPlatilloDAO objTPDAO;

    public TipoPlatilloCRUD(){
        objTPDAO=new TipoPlatilloDAO();
        crearGUI();
        this.setTitle("Administracion de Tipos de Platillo");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        tbvTipoPlatillo=new TableView<>();
        CrearTabla();
        btnNuevo=new Button("Nuevo Tipo de Platillo");
        btnNuevo.setOnAction(event -> {new FrmTipoPlatillo(tbvTipoPlatillo,null);});
        vBox=new VBox();
        vBox.getChildren().addAll(tbvTipoPlatillo,btnNuevo);
        escena=new Scene(vBox,465,250);
        escena.getStylesheets().add("sample/assets/CSS´s/TipoPlatilloCRUD.css");

    }

    private void CrearTabla() {
        TableColumn<TipoPlatilloDAO,Integer> tbcIDtipoPlatillo=new TableColumn<>("ID");
        tbcIDtipoPlatillo.setPrefWidth(30);
        tbcIDtipoPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        TableColumn<TipoPlatilloDAO,String> tbcDescripcion=new TableColumn<>("Descripcion");
        tbcDescripcion.setPrefWidth(150);
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("dsc_tipo"));

        TableColumn<TipoPlatilloDAO,String> tbcEditar = new TableColumn<>("Editar" );
        tbcEditar. setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustomeTP(1);
                    }
                }
        );

        TableColumn<TipoPlatilloDAO,String> tbcBorrar = new TableColumn<>("Borrar" );
        tbcBorrar. setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustomeTP(2);
                    }
                }
        );


        tbvTipoPlatillo.getColumns().addAll(tbcIDtipoPlatillo,tbcDescripcion,tbcEditar,tbcBorrar);
        tbvTipoPlatillo.setItems(objTPDAO.getAllTipo());

    }

}
