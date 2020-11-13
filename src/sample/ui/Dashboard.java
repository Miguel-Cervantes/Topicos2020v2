package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class Dashboard extends Stage {

    private Scene escena;
    private VBox vBox;

    public Dashboard(){
        CrearGUI();
        this.setTitle("Dashboard");
        this.setScene(escena);
        this.show();

        new PlatilloCRUD();
    }

    private void CrearGUI() {
        vBox=new VBox();
        escena=new Scene(vBox,200,100);
    }

}
