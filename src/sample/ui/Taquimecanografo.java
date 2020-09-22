package sample.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage {

    //Elememtos para el toolbar
    private ToolBar tlbMenu;
    private Button btnAbrir;

    //Elemenetos para la escritura
    private TextArea txtContenido, txtEscritura;

    //Elementos para construir el teclado
    private HBox[] arHboxTeclas=new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arTeclado1=new Button[14];

    //Elementos de agrupacion global
    private VBox vBoxPrincipal;
    Scene escena;


    public Taquimecanografo(){
        CrearGUI();
        this.setTitle("Tutor taquimecanografo");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(){
        CrearToolbar();
        CrearEscritura();
        CrearTeclado();
        vBoxPrincipal=new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu,txtContenido,txtEscritura);
        vBoxPrincipal.setSpacing(10);
        vBoxPrincipal.setPadding(new Insets(10));
        escena=new Scene(vBoxPrincipal,800,500);
    }

    private void CrearTeclado() {

    }

    private void CrearEscritura() {
        txtContenido=new TextArea();
        txtContenido.setPrefRowCount(6);
        txtEscritura=new TextArea();
        txtEscritura.setPrefRowCount(6);
    }

    private void CrearToolbar() {
        tlbMenu=new ToolBar();
        btnAbrir=new Button();
        btnAbrir.setOnAction(event -> eventoTaqui(1));
        btnAbrir.setPrefSize(15,15);
        //Asignamos la imagen al boton abrir
        Image img=new Image("sample/assets/iconOpen.png");

        ImageView imv=new ImageView(img);
        imv.setFitHeight(14);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);
        tlbMenu.getItems().addAll(btnAbrir);
    }

    private void eventoTaqui(int opc) {
        switch(opc){
            case 1:
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("abrir Archivo");
                File file  =fileChooser.showOpenDialog(this);

        }
    }
}
