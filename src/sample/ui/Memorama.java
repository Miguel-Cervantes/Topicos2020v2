package sample.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Memorama extends Stage implements EventHandler {

    private String[] arImagenes={"uno1.png","dos2.png","tres3.png","cuatro4.png","cinco5.png","seis6.png","siete7.png","ocho8.png","nueve9.png","diez10.png"};
    private Label lblTarjetas,lblContador;
    private TextField txtNoTarjetas,txtcontador;
    private Button btnAceptar, btnRegresar,btnLimpiar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa;
    private Button[][] arTarjetas;
    private String[][] arAsignacion;

    private int noPares;
    private Scene escena;

    private ToolBar tlbMenu;
    int x=0, y=0, continents =0;  int c1x=0; int c1y=0; int c2x=0; int c2y=0;
    String car1;
    int pares=0;
    boolean bca =false;

    public Memorama() {

        CrearGUI();
        this.setTitle("Memorama :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Hora de Jugar!");
        alert1.setHeaderText("Bienvenido al Memorama de MIKE");
        alert1.setContentText("Este Memorama se juega con minimo 2 hasta 10 pares"+"\n"+"Diviertete mucho y has un record de menos intentos");
        alert1.showAndWait();
        System.out.print("arreglo de inicial de imagenes de numeros ordedenado");
        System.out.print("size: "+arImagenes.length+"\n");
        for (int ii = 0; ii <arImagenes.length ; ii++) {
            System.out.print(ii+".-"+" "+arImagenes[ii]+"\n");
        }System.out.print("\n");

        lblTarjetas =new Label("Número de Pares:");

        txtNoTarjetas = new TextField();
        txtNoTarjetas.setPrefWidth(40);

        btnAceptar = new Button("Jugar!!");
        btnRegresar=new Button("Menu");
        btnLimpiar=new Button("limpiar");

        lblContador=new Label("Intentos: ");

        txtcontador =new TextField();
        txtcontador.setPrefWidth(40);
        txtcontador.setEditable(false);
        txtcontador.setText(Integer.toString(continents));

        btnRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
        btnLimpiar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> vBox.getChildren().remove(gdpMesa));
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        /*btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventosMemorama());
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("tercer evento anonimo");
            }
        });*/

        hBox=new HBox();
        hBox.getChildren().addAll(lblTarjetas,txtNoTarjetas,btnAceptar,btnLimpiar,btnRegresar);
        hBox.setSpacing(3);

        gdpMesa=new GridPane();
        tlbMenu = new ToolBar();
        tlbMenu.getItems().addAll(lblContador,txtcontador);
        vBox=new VBox();
        vBox.getChildren().addAll(hBox,gdpMesa,tlbMenu);

        escena =new Scene(vBox,700,500);

    }
    @Override
    public void handle(Event event)
    {
        RevolverAreImagenes();

            noPares = Integer.parseInt(txtNoTarjetas.getText());
        if(noPares<2 || noPares>10){
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Número de Pares Invalido");
            alert2.setHeaderText(null);
            alert2.setContentText("El valor de pares que ingresaste no el valido para poder jugar"+"\n"+"Intenta otro valor");
            alert2.showAndWait();

        }else {
            //En construccion----(Matriz dinamica segun el valor de pares)------------
            if (noPares == 2) { x = 2; y = 2; } else {
                if (noPares==3){ x = 3;y = 2; }else{
                    if (noPares==4){ x = 2;y = 4; }else{
                        if (noPares==5){ x = 2;y = 5; }else{
                            if (noPares==6){ x = 3;y = 4; }else{
                                if (noPares==7){ x = 2;y = 7; }else{
                                    if (noPares==8){ x = 4;y = 4; }else{
                                        if (noPares==9){ x = 2;y = 9; }else{
                                            x = 4;y = 5;
                                        } } } } } } } }
            //---------------------------------
        }
        vBox.getChildren().remove(gdpMesa);
        gdpMesa=new GridPane();
        arAsignacion= new String[x][y];
        revolver();
        arTarjetas=new Button[(x)][y];
        for (int i = 0; i <x; i++) {
            for (int j = 0; j <y ; j++) {
                Image img=new Image("sample/assets/pokerReverso.jpg");
                ImageView imv=new ImageView(img);
                imv.setFitHeight(100);
                imv.setPreserveRatio(true);
                arTarjetas[i][j]= new Button();
                int finalI=i;
                int finalJ=j;
                arTarjetas[i][j].setOnAction(event1 -> verTarjeta(finalI,finalJ));
                arTarjetas[i][j].setGraphic(imv);
                //arTarjetas[i][j].setPrefSize(80,120);
                gdpMesa.add(arTarjetas[i][j],j,i);
            }
        }
        vBox.getChildren().add(gdpMesa);

    }

    private void verTarjeta(int finalI, int finalJ) {

        Image img=new Image("sample/assets/"+arAsignacion[finalI][finalJ]);
        ImageView imv=new ImageView(img);
        imv.setFitHeight(100);
        imv.setPreserveRatio(true);
        arTarjetas[finalI][finalJ].setGraphic(imv);
        CompararTarjetas( finalI, finalJ);
    }
    private void CompararTarjetas(int finalI, int finalJ) {
        String car2;

        if (!bca) {
            car1 = arAsignacion[finalI][finalJ];

            System.out.print("Car1: " + car1 + "\n");
            System.out.print("finalI: " + finalI + "    finalJ: " + finalJ + "\n");
            c1x = finalI;
            c1y = finalJ;
            System.out.print("c1x: " + c1x + "  c1y: " + c1y + "\n");
            System.out.print("" + arAsignacion[c1x][c1y] + "\n\n");

            bca = true;
        } else {
            bca = false;
            car2 = arAsignacion[finalI][finalJ];

            System.out.print("Car2: " + car2 + "\n");
            System.out.print("finalI: " + finalI + "    finalJ: " + finalJ + "\n");
            c2x = finalI;
            c2y = finalJ;
            System.out.print("c2x: " + c2x + "  c2y :" + c2y + "\n");

            if (car1.compareToIgnoreCase(car2) == 0) {
                this.continents++;
                System.out.print("contador: " + continents + "\n\n");
                pares = pares + 1;
                System.out.print("PARES ENCONTRADOS: " + pares + "\n");

                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Felcidades!!");
                alert3.setHeaderText(null);
                alert3.setContentText("Le atinaste a un par!!");
                alert3.showAndWait();

                if (pares == noPares) {
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Juego Terminado");
                    alert4.setHeaderText("Felicitaciones por encontar todos los pares!!");
                    alert4.setContentText("Tuviste " + continents + " intentos Vuelve y rompe tu record!!");
                    alert4.showAndWait();
                }
            } else {
                this.continents++;
                System.out.print("contador: " + continents + "\n\n");
                OcultarTarjetaU(c1x, c1y);
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                OcultarTarjetaD(c2x, c2y);
            }
        }

    }
    private void revolver() {

        for(int ii=0; ii<x; ii++)
            for(int jj=0; jj<y; jj++){
                arAsignacion[ii][jj] = "";
            }
        int posx, posy, cont = 0;
        for(int ii=0; ii<noPares;){
            posx = (int)(Math.random() * x);
            posy = (int)(Math.random() * y);
            if( arAsignacion[posx][posy].equals("") ){
                arAsignacion[posx][posy] = arImagenes[ii];
                cont++;
            }
            if(cont == 2){
                ii++;
                cont = 0;
            }
        }
    }

    private void RevolverAreImagenes(){
        Random r = new Random();
        for (int ii=0; ii<arImagenes.length; ii++) {
            int posAleatoria = r.nextInt(arImagenes.length);
            String temp = arImagenes[ii];
            arImagenes[ii] = arImagenes[posAleatoria];
            arImagenes[posAleatoria] = temp;
        }


        System.out.print("revolver el arreglode imagenes ");
        System.out.print("size: "+arImagenes.length+"\n");

        for (int ii = 0; ii <arImagenes.length ; ii++) {
            System.out.print(ii+".-"+" "+arImagenes[ii]+"\n");
        }System.out.print("\n");
    }
    private void OcultarTarjetaU( int c1x, int c1y) {

        Image imgOU=new Image("sample/assets/pokerReverso.jpg");
        ImageView imvOU=new ImageView(imgOU);
        imvOU.setFitHeight(100);
        imvOU.setPreserveRatio(true);
        arTarjetas[c1x][c1y].setGraphic(imvOU);
    }
    private void OcultarTarjetaD( int c2x, int c2y) {
        Espera(1);
        Image imgOD=new Image("sample/assets/pokerReverso.jpg");
        ImageView imvOD=new ImageView(imgOD);
        imvOD.setFitHeight(100);
        imvOD.setPreserveRatio(true);
        arTarjetas[c2x][c2y].setGraphic(imvOD);
    }

    void Espera(int seg){
        try {
            Thread.sleep(seg * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

