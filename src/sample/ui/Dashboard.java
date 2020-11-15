package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class Dashboard extends Stage {

    private Scene escena;
    private VBox vBox;
    private Button btnPlatilloCRUD,btnTipoPlatilloCRUD;

    public Dashboard(){
        CrearGUI();
        this.setTitle("Dashboard");
        this.setScene(escena);
        this.show();

    }

    private void CrearGUI() {
        vBox=new VBox();
        btnPlatilloCRUD=new Button("Platillo CRUD");
        btnPlatilloCRUD.setOnAction(event -> {new PlatilloCRUD();});
        btnTipoPlatilloCRUD=new Button("Tipo Platillo CRUD");
        btnTipoPlatilloCRUD.setOnAction(event -> {new TipoPlatilloCRUD();});



        vBox.getChildren().addAll(btnPlatilloCRUD,btnTipoPlatilloCRUD);
        escena=new Scene(vBox,200,100);
        escena.getStylesheets().add("sample/assets/CSS´s/Dashboard.css");
    }

}
