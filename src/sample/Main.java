package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.ui.Memorama;
import sample.ui.Taquimecanografo;

public class Main extends Application implements EventHandler<WindowEvent>
{
    private VBox vPrincipal;
    private MenuBar mnbProyecto;
    private Menu menCompetencia1,menCompetencia2,menSalir;
    private MenuItem mitPractica1,mitPractica2,mitPractica3,mitAdios;
    private Scene escena;

    private BorderPane brpPrincipal;

    private ToolBar tlbMenu;
    private Button btnToolbar1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        CrearUI();
        primaryStage.setTitle("Practicas Topicos");
        primaryStage.setScene(escena);
        primaryStage.addEventHandler(WindowEvent.ANY,this);
        primaryStage.show();
        }
        private void CrearUI() {
        mnbProyecto= new MenuBar();

        menCompetencia1=new Menu("Primer Parcial");
        menCompetencia2=new Menu("Segundo Parcial(Vacio)");
        menSalir=new Menu("Salir");

        mnbProyecto.getMenus().addAll(menCompetencia1,menCompetencia2,menSalir);

        mitPractica1=new MenuItem("      Memorama");
        mitPractica1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
        mitPractica1.setOnAction(event -> OpcionMenu(1));

        mitPractica2=new MenuItem("Taquimecanografo");
        mitPractica2.setOnAction(event -> OpcionMenu(2));

        mitPractica3=new MenuItem("vacio");
        mitPractica3.setOnAction(event -> OpcionMenu(3));

        mitAdios=new MenuItem("Adios");
        mitAdios.setOnAction(event -> {System.exit(0);});

        menCompetencia1.getItems().addAll(mitPractica1,mitPractica2);
        menCompetencia2.getItems().addAll(mitPractica3);

        menSalir.getItems().add(mitAdios);

            // Crear una barra de herramientas
            tlbMenu = new ToolBar();
            btnToolbar1 = new Button();
            btnToolbar1.setOnAction(event -> OpcionMenu(1));
            btnToolbar1.setPrefSize(15,15);

            // Asignamos la imagen al boton dentro del toolbar
            Image img = new Image("sample/assets/game_control.png");
            ImageView imv = new ImageView(img);
            imv.setFitHeight(15);
            imv.setPreserveRatio(true);
            btnToolbar1.setGraphic(imv);

            tlbMenu.getItems().add(btnToolbar1);

            vPrincipal = new VBox();
            vPrincipal.getChildren().addAll(mnbProyecto,tlbMenu);
            escena = new Scene(vPrincipal,300,200);
            escena.getStylesheets().add("../assets/main_styles.css");

    }



    private void OpcionMenu(int i)
    {
        switch(i)
        {
            case 1:
                new Memorama();

                break;
            case 2:
                new Taquimecanografo();

                break;
            case 3:
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Dialogo Informativo");
                alert2.setHeaderText(null);
                alert2.setContentText("El Campo esta vacio por el momento");
                alert2.showAndWait();
                break;
            case 20:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(WindowEvent event) {
        System.out.println("mostrando la ventana");
    }
}
