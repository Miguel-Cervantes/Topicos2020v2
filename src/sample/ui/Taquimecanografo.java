package sample.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import static java.lang.String.*;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    //Bandera para detectar cambios
    boolean banColor;

    //Arreglos para etiquetar los bonotes
    private String[] arLblBtn1 ={"ESC","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","IMP PNT","SUPR"};
    private String[] arLblBtn2 ={"°|¬","1!","2","3#","4$","5%","6&","7/","8(","9)","0=","'?","¿¡","BORRAR","IN"};
    private String[] arLblBtn3 ={"TAB","Q","W","E","R","T","Y","U","I","O","P","´¨","*+~","ENTER","FIN"};
    private String[] arLblBtn4 ={"BM","A","S","D","F","G","H","J","K","L","Ñ","{[^","]}`","REPAG"};
    private String[] arLblBtn5 ={"SHIFT","<>","Z","X","C","V","B","N","M",",;",".:","-_","SHIFT","^","AVPAG"};
    private String[] arLblBtn6 ={"CTRL","ALT","ESPACIO","MENU","CTRL","<","DOWN",">"};

    //Elememtos para el toolbar
    private ToolBar tlbMenu;

    //Elemenetos para la escritura
    private TextArea txtContenido, txtEscritura;

    //Elementos para construir el teclado
    private HBox[] arHboxTeclas=new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arBtnTeclado1=new Button[15];
    private Button[] arBtnTeclado2=new Button[15];
    private Button[] arBtnTeclado3=new Button[15];
    private Button[] arBtnTeclado4=new Button[14];
    private Button[] arBtnTeclado5=new Button[15];
    private Button[] arBtnTeclado6=new Button[8];

    //Informacion del texto
    private HBox HBoxInfo= new HBox();
    private Label lblPalabras=new Label("Palabras Totales: ");
    private TextField txtPalabras=new TextField();
    private Label lblErrores=new Label(" Errores: ");
    private TextField txtErrores=new TextField();
    private Label lblPalabrasE=new Label(" Palabras Escritas ");
    private TextField txtPalabrasE=new TextField();

    //Errores
    int error=0,escritas=0,palabrasT=0;
    Scene escena;

    public Taquimecanografo(){
        CrearGUI();
        this.setTitle("Tutor taquimecanografo");
        this.setScene(escena);
        this.show();
        banColor = false;
    }

    private void CrearGUI(){
        CrearToolbar();
        CrearHBoxInfo();
        CrearEscritura();
        CrearTeclado();
        //Elementos de agrupacion global
        VBox vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu,HBoxInfo,txtContenido,txtEscritura,vBoxTeclado);
        vBoxPrincipal.setSpacing(3);
        vBoxPrincipal.setPadding(new Insets(5));
        escena=new Scene(vBoxPrincipal,565,675);
        escena.getStylesheets().add("sample/assets/Taqui_Style.css");
    }

    private void CrearHBoxInfo() {

        txtPalabras.setPrefWidth(35);
        txtPalabras.setEditable(false);
        txtPalabras.setText(Integer.toString(palabrasT));
        txtErrores.setPrefWidth(35);
        txtErrores.setEditable(false);
        txtErrores.setText(Integer.toString(error));
        txtPalabrasE.setPrefWidth(35);
        txtPalabrasE.setEditable(false);
        txtPalabrasE.setText(Integer.toString(escritas));
        HBoxInfo.getChildren().addAll(lblPalabras,txtPalabras,lblErrores,txtErrores,lblPalabrasE,txtPalabrasE);

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
            arBtnTeclado1[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[0].getChildren().add(arBtnTeclado1[i]);
        }arBtnTeclado1[0].setId("ButtonWidthESC");
        for (int i = 0; i <arBtnTeclado2.length ; i++) {
            arBtnTeclado2[i]=new Button(arLblBtn2[i]);
            arBtnTeclado2[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[1].getChildren().add(arBtnTeclado2[i]);
        }arBtnTeclado2[0].setId("ButtonWidth5");arBtnTeclado2[1].setId("ButtonWidth5");
        arBtnTeclado2[2].setId("ButtonWidth5");arBtnTeclado2[3].setId("ButtonWidth5");
        arBtnTeclado2[4].setId("ButtonWidth5");arBtnTeclado2[5].setId("ButtonWidth5");
        arBtnTeclado2[6].setId("ButtonWidth5");arBtnTeclado2[7].setId("ButtonWidth5");
        arBtnTeclado2[8].setId("ButtonWidth5");arBtnTeclado2[9].setId("ButtonWidth5");
        arBtnTeclado2[10].setId("ButtonWidth5");arBtnTeclado2[11].setId("ButtonWidth5");
        arBtnTeclado2[12].setId("ButtonWidth5");arBtnTeclado2[13].setId("ButtonWidthBorrar");

        for (int i = 0; i <arBtnTeclado3.length ; i++) {
            arBtnTeclado3[i]=new Button(arLblBtn3[i]);
            arBtnTeclado3[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[2].getChildren().add(arBtnTeclado3[i]);
        }arBtnTeclado3[0].setId("ButtonWidthShift");arBtnTeclado3[1].setId("ButtonWidthTecla3");
        arBtnTeclado3[2].setId("ButtonWidthTecla3");arBtnTeclado3[3].setId("ButtonWidthTecla3");
        arBtnTeclado3[4].setId("ButtonWidthTecla3");arBtnTeclado3[5].setId("ButtonWidthTecla3");
        arBtnTeclado3[6].setId("ButtonWidthTecla3");arBtnTeclado3[7].setId("ButtonWidthTecla3");
        arBtnTeclado3[8].setId("ButtonWidthTecla3");arBtnTeclado3[9].setId("ButtonWidthTecla3");
        arBtnTeclado3[10].setId("ButtonWidthTecla3");arBtnTeclado3[11].setId("ButtonWidth2");
        arBtnTeclado3[12].setId("ButtonWidth3");arBtnTeclado3[13].setId("ButtonWidthEnter");

        for (int i = 0; i <arBtnTeclado4.length ; i++) {
            arBtnTeclado4[i]=new Button(arLblBtn4[i]);
            arBtnTeclado4[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[3].getChildren().add(arBtnTeclado4[i]);
        }arBtnTeclado4[0].setId("ButtonWidthBM");
        arBtnTeclado4[1].setId("ButtonWidthTecla4");arBtnTeclado4[2].setId("ButtonWidthTecla4");
        arBtnTeclado4[3].setId("ButtonWidthTecla4");arBtnTeclado4[4].setId("ButtonWidthTecla4");
        arBtnTeclado4[5].setId("ButtonWidthTecla4");arBtnTeclado4[6].setId("ButtonWidthTecla4");
        arBtnTeclado4[7].setId("ButtonWidthTecla4");arBtnTeclado4[8].setId("ButtonWidthTecla4");
        arBtnTeclado4[9].setId("ButtonWidthTecla4");arBtnTeclado4[10].setId("ButtonWidthTecla4");
        arBtnTeclado4[11].setId("ButtonWidthTecla4");arBtnTeclado4[12].setId("ButtonWidthTecla4");
        arBtnTeclado4[13].setId("ButtonWidthTeclaRePag");
        for (int i = 0; i <arBtnTeclado5.length ; i++) {
            arBtnTeclado5[i]=new Button(arLblBtn5[i]);
            arBtnTeclado5[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[4].getChildren().add(arBtnTeclado5[i]);
        }arBtnTeclado5[0].setId("ButtonWidthShift");arBtnTeclado5[1].setId("ButtonWidth1");
         arBtnTeclado5[2].setId("ButtonWidthTecla");arBtnTeclado5[3].setId("ButtonWidthTecla");
         arBtnTeclado5[4].setId("ButtonWidthTecla");arBtnTeclado5[5].setId("ButtonWidthTecla");
         arBtnTeclado5[6].setId("ButtonWidthTecla");arBtnTeclado5[7].setId("ButtonWidthTecla");
         arBtnTeclado5[8].setId("ButtonWidthTecla");arBtnTeclado5[9].setId("ButtonWidthTecla");
         arBtnTeclado5[10].setId("ButtonWidthTecla");arBtnTeclado5[11].setId("ButtonWidthTecla");
         arBtnTeclado5[12].setId("ButtonWidthShift");
        for (int i = 0; i <arBtnTeclado6.length ; i++) {
            arBtnTeclado6[i]=new Button(arLblBtn6[i]);
            arBtnTeclado6[i].setStyle("-fx-background-color: #666666;");
            arHboxTeclas[5].getChildren().add(arBtnTeclado6[i]);
        }arBtnTeclado6[0].setId("ButtonWidthCtrl");arBtnTeclado6[2].setId("ButtonWidthSpace");arBtnTeclado6[4].setId("ButtonWidthCtrl");

        vBoxTeclado.getChildren().addAll(arHboxTeclas[0],arHboxTeclas[1],arHboxTeclas[2],arHboxTeclas[3],arHboxTeclas[4],arHboxTeclas[5]);
    }

    private void CrearEscritura() {
        txtContenido=new TextArea();
        txtContenido.setEditable(false);
        txtContenido.setPrefRowCount(6);
        txtEscritura=new TextArea();
        txtEscritura.setPrefRowCount(6);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
    }

    private void CrearToolbar() {
        tlbMenu=new ToolBar();
        Button btnAbrir = new Button();
        btnAbrir.setOnAction(event -> eventoTaqui());
        btnAbrir.setPrefSize(25,25);

        //Asignamos la imagen al boton abrir
        Image img=new Image("sample/assets/iconOpen.png");
        ImageView imv=new ImageView(img);
        imv.setFitHeight(25);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);
        tlbMenu.getItems().addAll(btnAbrir);
    }

    private void eventoTaqui() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("abrir Archivo");
        File file = fileChooser.showOpenDialog(this);

        String uurl=null;
                uurl = valueOf(file);
        //System.out.print(file+"\n");

        StringBuilder codigo = new StringBuilder();
        //StringBuilder codigoLine = new StringBuilder();
        new File(uurl);

        FileReader fr = null;
        BufferedReader entrada;
        if(!uurl.equals("null")){
            palabrasT=0;error=0;escritas=0;
            txtPalabras.setText(Integer.toString(palabrasT));
            txtErrores.setText(Integer.toString(error));
            txtPalabrasE.setText(Integer.toString(escritas));

            try {
                fr = new FileReader(uurl);
                entrada = new BufferedReader(fr);
                while (entrada.ready()) {
                    codigo.append(entrada.readLine()).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            txtContenido.setText(valueOf(codigo));
            System.out.println("codigo: " + "\n" + codigo);
            String codline = valueOf(codigo);
            String cadEnter = "",cadTamaño="";

            String[] cadenalineArray = codline.split("\n");

            for (int i = 0; i < cadenalineArray.length; i++) {
                cadEnter = cadEnter + cadenalineArray[i];
            }

            String a="\n";
            int intIndexa = cadEnter.indexOf(a);
            if(intIndexa != - 1){
                System.out.println("palabra encontrada: "+a);
                cadEnter = cadEnter.replaceAll(a, "");
            }else{
                System.out.println("palabra no encontrada" + intIndexa+" : "+a);
            }

            String b=".";
            int intIndexb = cadEnter.indexOf(b);
            if(intIndexb != - 1){
                System.out.println("palabra encontrada: "+b);
                cadEnter = cadEnter.replaceAll(b, "");
            }else{
                System.out.println("palabra no encontrada" + intIndexb+" : "+b);
            }

            String c=",";
            int intIndexc = cadEnter.indexOf(c);
            if(intIndexc != - 1){
                System.out.println("palabra encontrada: "+c);
                cadEnter = cadEnter.replaceAll(c,"");
            }else{
                System.out.println("palabra no encontrada" + intIndexc+" : "+c);
            }

            String d="-";
            int intIndexd = cadEnter.indexOf(d);
            if(intIndexd != - 1){
                System.out.println("palabra encontrada: "+d);
                cadEnter = cadEnter.replaceAll(d,"");
            }else{
                System.out.println("palabra no encontrada" + intIndexd+" : "+d);
            }

            String e=" ";
            int intIndexe = cadEnter.indexOf(e);
            if(intIndexe != - 1){
                System.out.println("palabra encontrada: "+e);
                cadEnter = cadEnter.replaceAll(e,"¬");
            }else{
                System.out.println("palabra no encontrada" + intIndexe+" : "+e+"\n");
            }

            String[] cadTamañoArray = codline.split("\n");

            for (int i = 0; i < cadenalineArray.length; i++) {
                cadTamaño = cadTamaño + cadTamañoArray[i];
            }

            System.out.print("CadEnter: " + cadEnter + "\n");
            //System.out.print("Codigo: " + codigo + "\n");
            //System.out.print("CodigoLine: " + codline + "\n");

            txtPalabras.setText(Integer.toString(cadTamañoArray.length));
        }
    }


    @Override
    public void handle(KeyEvent event)
    {
        //System.out.print(event.getCode().getName()+"\n");
        //System.out.print(event.getCode()+"\n\n");

        switch (event.getCode().toString()){
            //Primer Renglon de teclas
            case "ESCAPE": if(!banColor) { arBtnTeclado1[0].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado1[0].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "PRINTSCREEN": if(!banColor) { arBtnTeclado1[13].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado1[13].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DELETE": if(!banColor) { arBtnTeclado1[14].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado1[14].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            //Segundo renglon de teclas
            case "DIGIT1": if(!banColor) { arBtnTeclado2[1].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[1].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT2": if(!banColor) { arBtnTeclado2[2].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[2].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT3": if(!banColor) { arBtnTeclado2[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT4": if(!banColor) { arBtnTeclado2[4].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[4].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT5": if(!banColor) { arBtnTeclado2[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT6": if(!banColor) { arBtnTeclado2[6].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[6].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT7": if(!banColor) { arBtnTeclado2[7].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[7].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT8": if(!banColor) { arBtnTeclado2[8].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[8].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT9": if(!banColor) { arBtnTeclado2[9].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[9].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DIGIT0": if(!banColor) { arBtnTeclado2[10].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[10].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "QUOTE": if(!banColor) { arBtnTeclado2[11].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[11].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            /*case "UNDEFINED": if(banColor==false) { arBtnTeclado2[14].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[14].setStyle("-fx-background-color: #666666;"); banColor=false; }break;*/
            case "BACK_SPACE": if(!banColor) { arBtnTeclado2[13].setStyle("-fx-background-color: #0D1526;"); banColor=true; error++; txtErrores.setText(Integer.toString(error));
                }else { arBtnTeclado2[13].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "HOME": if(!banColor) { arBtnTeclado2[14].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado2[14].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            //tercer renglon de teclas
            case "TAB": if(!banColor) { arBtnTeclado3[0].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[0].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "Q": if(!banColor) { arBtnTeclado3[1].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[1].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "W": if(!banColor) { arBtnTeclado3[2].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[2].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "E": if(!banColor) { arBtnTeclado3[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "R": if(!banColor) { arBtnTeclado3[4].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[4].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "T": if(!banColor) { arBtnTeclado3[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "Y": if(!banColor) { arBtnTeclado3[6].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[6].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "U": if(!banColor) { arBtnTeclado3[7].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[7].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "I": if(!banColor) { arBtnTeclado3[8].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[8].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "O": if(!banColor) { arBtnTeclado3[9].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[9].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "P": if(!banColor) { arBtnTeclado3[10].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[10].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DEAD_ACUTE": if(!banColor) { arBtnTeclado3[11].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[11].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "PLUS": if(!banColor) { arBtnTeclado3[12].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[12].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "ENTER": if(!banColor) { arBtnTeclado3[13].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[13].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "END": if(!banColor) { arBtnTeclado3[14].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado3[14].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            //Cuarta linea de teclas
            case "CAPS": if(!banColor) { arBtnTeclado4[0].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[0].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "A": if(!banColor) { arBtnTeclado4[1].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[1].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "S": if(!banColor) { arBtnTeclado4[2].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[2].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "D": if(!banColor) { arBtnTeclado4[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "F": if(!banColor) { arBtnTeclado4[4].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[4].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "G": if(!banColor) { arBtnTeclado4[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "H": if(!banColor) { arBtnTeclado4[6].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[6].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "J": if(!banColor) { arBtnTeclado4[7].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[7].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "K": if(!banColor) { arBtnTeclado4[8].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[8].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "L": if(!banColor) { arBtnTeclado4[9].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[9].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            /*case "UNDEFINED":
                if(banColor==false) { arBtnTeclado4[10].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[10].setStyle("-fx-background-color: #666666;"); banColor=false; }break;*/
            case "BRACELEFT": if(!banColor) { arBtnTeclado4[11].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[11].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "BRACERIGHT": if(!banColor) { arBtnTeclado4[12].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[12].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "PAGE_UP": if(!banColor) { arBtnTeclado4[13].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado4[13].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            //Quinta linea de teclas
            case "SHIFT": if(!banColor) { arBtnTeclado5[0].setStyle("-fx-background-color: #0D1526;"); arBtnTeclado5[12].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[0].setStyle("-fx-background-color: #666666;"); arBtnTeclado5[12].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "LESS": if(!banColor) { arBtnTeclado5[1].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[1].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "Z": if(!banColor) { arBtnTeclado5[2].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[2].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "X": if(!banColor) { arBtnTeclado5[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "C": if(!banColor) { arBtnTeclado5[4].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[4].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "V": if(!banColor) { arBtnTeclado5[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "B": if(!banColor) { arBtnTeclado5[6].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[6].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "N": if(!banColor) { arBtnTeclado5[7].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[7].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "M": if(!banColor) { arBtnTeclado5[8].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[8].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "COMMA": if(!banColor) { arBtnTeclado5[9].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[9].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "PERIOD": if(!banColor) { arBtnTeclado5[10].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[10].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "MINUS": if(!banColor) { arBtnTeclado5[11].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[11].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            /*case "SHIFT": if(banColor==false) { arBtnTeclado5[12].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[12].setStyle("-fx-background-color: #666666;"); banColor=false; }break;*/
            case "UP": if(!banColor) { arBtnTeclado5[13].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[13].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "PAGE_DOWN": if(!banColor) { arBtnTeclado5[14].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado5[14].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            //Sexta linea de teclas
            case "CONTROL": if(!banColor) { arBtnTeclado6[0].setStyle("-fx-background-color: #0D1526;"); arBtnTeclado6[4].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[0].setStyle("-fx-background-color: #666666;"); arBtnTeclado6[4].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "ALT": if(!banColor) { arBtnTeclado6[1].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[1].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "SPACE": if(!banColor) { arBtnTeclado6[2].setStyle("-fx-background-color: #0D1526;"); banColor=true; escritas++; txtPalabrasE.setText(Integer.toString(escritas));
                }else { arBtnTeclado6[2].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            /*case "CONTROL ALT_GRAPH": if(banColor==false) { arBtnTeclado6[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;*/
            case "CONTEXT_MENU": if(!banColor) { arBtnTeclado6[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            /*case "CONTROL": if(banColor==false) { arBtnTeclado6[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;*/
            case "LEFT": if(!banColor) { arBtnTeclado6[5].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[5].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "DOWN": if(!banColor) { arBtnTeclado6[6].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[6].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
            case "RIGHT": if(!banColor) { arBtnTeclado6[7].setStyle("-fx-background-color: #0D1526;"); banColor=true;
                }else { arBtnTeclado6[7].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
        }
        /*switch (event.getCode().getName().toString()){
            case "Alt Graph": if(banColor==false) { arBtnTeclado6[3].setStyle("-fx-background-color: #0D1526;"); banColor=true;
            }else { arBtnTeclado6[3].setStyle("-fx-background-color: #666666;"); banColor=false; }break;
        }*/

        //System.out.print(event.getCode().getName()+"\n\n");

    }
    private void Time(){

    }
}
