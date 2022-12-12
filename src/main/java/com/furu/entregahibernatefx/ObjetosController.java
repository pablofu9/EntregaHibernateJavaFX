package com.furu.entregahibernatefx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTreeTableView;
import entity.Objetos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.CRUD_Objetos;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ObjetosController implements Initializable {

    @FXML
    private JFXButton btnUser, btnObjecto, btnExit;



    private List<Objetos> obj;
    @FXML
    private void cambiarVista() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("vista-usuarios.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        //Para cerrar el login
        Stage loginStage = (Stage) this.btnUser.getScene().getWindow();
        loginStage.close();
    }
    @FXML
    private void salir(){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
}
