package sample.ui;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class Dashboard extends Stage {

    public Dashboard(){
        CrearGUI();
        this.setTitle("Dashboard");
        this.show();

        new PlatilloCRUD();
    }

    private void CrearGUI() {

    }

}
