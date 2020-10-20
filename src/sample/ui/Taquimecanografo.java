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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Taquimecanografo extends Stage {
    //Arrleglos para etiquetar los bonotes
    private String arLblBtn1[]={"ESC","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","SUPR"};
    private String arLblBtn2[]={"°|¬","1!","2","3#","4$","5%","6&","7/","8(","9)","10=","F11","'?","¿¡","BORRAR","INICIO"};
    private String arLblBtn3[]={"TAB","Q@","W","E","R","T","Y","U","I","O","P","´¨","*+~","ENTER","FIN"};
    private String arLblBtn4[]={"BM","A","S","D","F","G","H","J","K","L","Ñ","{[^","]}`","REPAG"};
    private String arLblBtn5[]={"SHIFT","<>","Z","X","C","V","B","N","M",",;",".:","-_","SHIFT","AVPAG"};
    private String arLblBtn6[]={"CTRL","ALT","ESPACIO","ALTCR","CTRL"};

    //Elememtos para el toolbar
    private ToolBar tlbMenu;
    private Button btnAbrir;

    //Elemenetos para la escritura
    private TextArea txtContenido, txtEscritura;

    //Elementos para construir el teclado
    private HBox[] arHboxTeclas=new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arBtnTeclado1=new Button[14];
    private Button[] arBtnTeclado2=new Button[16];
    private Button[] arBtnTeclado3=new Button[15];
    private Button[] arBtnTeclado4=new Button[14];
    private Button[] arBtnTeclado5=new Button[14];
    private Button[] arBtnTeclado6=new Button[5];


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
        vBoxPrincipal.getChildren().addAll(tlbMenu,txtContenido,txtEscritura,vBoxTeclado);
        vBoxPrincipal.setSpacing(10);
        vBoxPrincipal.setPadding(new Insets(10));
        escena=new Scene(vBoxPrincipal,800,500);
    }

    private void CrearTeclado() {
        vBoxTeclado=new VBox();
        vBoxTeclado.setSpacing(2);
        for (int i = 0; i <arHboxTeclas.length; i++) {
         arHboxTeclas[i]= new HBox();
         arHboxTeclas[i].setSpacing(2);
        }
        for (int i = 0; i <arBtnTeclado1.length ; i++) {
            arBtnTeclado1[i]=new Button(arLblBtn1[i]);
            arHboxTeclas[0].getChildren().add(arBtnTeclado1[i]);
        }
        for (int i = 0; i <arBtnTeclado2.length ; i++) {
            arBtnTeclado2[i]=new Button(arLblBtn2[i]);
            arHboxTeclas[1].getChildren().add(arBtnTeclado2[i]);
        }
        for (int i = 0; i <arBtnTeclado3.length ; i++) {
            arBtnTeclado3[i]=new Button(arLblBtn3[i]);
            arHboxTeclas[2].getChildren().add(arBtnTeclado3[i]);
        }
        for (int i = 0; i <arBtnTeclado4.length ; i++) {
            arBtnTeclado4[i]=new Button(arLblBtn4[i]);
            arHboxTeclas[3].getChildren().add(arBtnTeclado4[i]);
        }
        for (int i = 0; i <arBtnTeclado5.length ; i++) {
            arBtnTeclado5[i]=new Button(arLblBtn5[i]);
            arHboxTeclas[4].getChildren().add(arBtnTeclado5[i]);
        }
        for (int i = 0; i <arBtnTeclado6.length ; i++) {
            arBtnTeclado6[i]=new Button(arLblBtn6[i]);
            arHboxTeclas[5].getChildren().add(arBtnTeclado6[i]);
        }

        vBoxTeclado.getChildren().addAll(arHboxTeclas[0],arHboxTeclas[1],arHboxTeclas[2],arHboxTeclas[3],arHboxTeclas[4],arHboxTeclas[5]);
    }

    private void CrearEscritura() {
        txtContenido=new TextArea();
        txtContenido.setEditable(false);
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
                String uurl= String.valueOf(file);
                System.out.print(file);

                String codigo = new String(), path = uurl;
                File archivo = new File(path);

                FileReader fr = null;
                BufferedReader entrada = null;
                try {
                    fr = new FileReader(path);
                    entrada = new BufferedReader(fr);

                    while(entrada.ready()){
                        codigo += entrada.readLine();
                    }

                }catch(IOException e) {
                    e.printStackTrace();
                }finally{
                    try{
                        if(null != fr){
                            fr.close();
                        }
                    }catch (Exception e2){
                        e2.printStackTrace();
                    }
                }
                txtContenido.setText(codigo);
        }
    }
}
