package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.TipoPlatilloDAO;
import sample.ui.FrmTipoPlatillo;

import java.util.Optional;

public class ButtonCustomeTP extends TableCell<TipoPlatilloDAO,String> {

    private Button btnCeldaTP;
    private TipoPlatilloDAO objTPlatillo;

    public ButtonCustomeTP(int opc){
        if (opc == 1){
            btnCeldaTP = new Button("Editar");
            btnCeldaTP.setOnAction(event -> {
                objTPlatillo=ButtonCustomeTP.this.getTableView().getItems().get(ButtonCustomeTP.this.getIndex());
                new FrmTipoPlatillo(ButtonCustomeTP.this.getTableView(),objTPlatillo);
            });

        }else{
            btnCeldaTP = new Button("Borrar");
            btnCeldaTP.setOnAction(event -> {

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("MENSAJE DEL SISTEMA :)");
                alerta.setHeaderText("Confirmacion de Acción");
                alerta.setContentText("Realmente quieres borrar el registro?");
                Optional<ButtonType> result = alerta.showAndWait();
                if(result.get() == ButtonType.OK){
                    objTPlatillo=ButtonCustomeTP.this.getTableView().getItems().get(ButtonCustomeTP.this.getIndex());
                    objTPlatillo.delTipo();

                    ButtonCustomeTP.this.getTableView().setItems(objTPlatillo.getAllTipo());
                    ButtonCustomeTP.this.getTableView().refresh();
                }

            });
        }
    }


    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            setGraphic(btnCeldaTP);
        }
    }
}
