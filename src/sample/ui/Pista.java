package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.components.HiloCorredor;

public class Pista extends Stage {

    private String[] arNombres={"Sonic","Flash","Meteoro","Super Man","Quick Silver"};
    private Scene escena;
    private VBox vBox;
    private ProgressBar[] pgbCarril =new ProgressBar[arNombres.length];
    private HiloCorredor[] arHilos =new HiloCorredor[arNombres.length];

    public Pista() {
        CrearGUI();
        this.setTitle("Pista de carreras");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vBox=new VBox();
        for (int i = 0; i <arNombres.length ; i++) {
            pgbCarril[i]=new ProgressBar(0);
            arHilos[i]=new HiloCorredor(pgbCarril[0]);
            arHilos[i].start();
            vBox.getChildren().add(pgbCarril[i]);
        }
        escena=new Scene(vBox,250,250);

    }
}
