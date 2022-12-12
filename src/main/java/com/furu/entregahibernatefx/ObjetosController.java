package com.furu.entregahibernatefx;

import alertas.Alerts;
import com.jfoenix.controls.*;
import entity.Objetos;
import entity.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.CRUD_Objetos;
import modelo.CRUD_Usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ObjetosController implements Initializable {

    @FXML
    private JFXButton btnUser, btnObjecto, btnExit, btnLimpiar;


    @FXML
    private JFXTextField txtId, txtNombre, txtPrecio,txtUser;

    @FXML
    private JFXComboBox cmbTipo, cmbEstado, cmbUser;
    @FXML
    private JFXCheckBox checkReservado;
    @FXML
    private TableView<Objetos> tablaObjetos;

    @FXML
    private TableColumn<Objetos, Integer> colId;
    @FXML
    private TableColumn<Objetos, Integer> colUser;
    @FXML
    private TableColumn<Objetos, String> colNombre;
    @FXML
    private TableColumn<Objetos, String> colTipo;
    @FXML
    private TableColumn<Objetos, String> colEstado;
    @FXML
    private TableColumn<Objetos, Integer> colPrecio;
    @FXML
    private TableColumn<Objetos, Boolean> colReservado;

    @FXML
    private StackPane stack;


    @FXML
    private void cargarTabla(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id_objeto"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_objeto"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_objeto"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colReservado.setCellValueFactory(new PropertyValueFactory<>("reservado"));

        List<Objetos> objetos = CRUD_Objetos.llenarTabla();
        tablaObjetos.setItems(FXCollections.observableList(objetos));
    }
    @FXML //Menu de arriba, para volver a la pagina de usuarios
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
    @FXML //Menu de arriba para salir de la aplicacion
    private void salir(){
        System.exit(0);
    }

    @FXML
    private void llenarComboTipo(){
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Informatica");
        tipos.add("Ropa");
        tipos.add("Mobiliario");
        tipos.add("Electrodomesticos");

        cmbTipo.setItems(tipos);
    }

    @FXML
    private void llenarComboEstado(){
        ObservableList<String> estados = FXCollections.observableArrayList();
        estados.add("Nuevo");
        estados.add("Como nuevo");
        estados.add("Usado");
        estados.add("Mucho uso");

        cmbEstado.setItems(estados);
    }


    @FXML//Mandamos la zona seleccionada en la tabla a los campos de texto
    public void objetoCargado(Objetos obj){
        txtId.setText(String.valueOf(obj.getId_objeto()));
        txtNombre.setText(obj.getNombre_objeto());
        txtPrecio.setText(String.valueOf(obj.getPrecio())+" €");
        cmbEstado.setValue(obj.getEstado());
        cmbTipo.setValue(obj.getTipo_objeto());
        txtUser.setText(String.valueOf(obj.getUsuario().getId_user()));
        if(obj.isReservado()){
            checkReservado.setSelected(true);

        }

        txtId.setDisable(true);
        txtUser.setDisable(true);
    }

    @FXML //Usamos el metodo de arriba para rellenar los textfields
    private void objetoSelected(){
        /*Lo que hacemos primero es comprobar si la tabla esta vacia para que no salte una excepcion de
        null point
         */
        if(tablaObjetos.getItems().isEmpty()){
            Alerts.crearAlertaError(stack, "La tabla esta vacia");
        }else{
            Objetos obj = (Objetos) tablaObjetos.getSelectionModel().getSelectedItem();
            objetoCargado(obj);
        }

    }

    @FXML
    private void reservarObjeto(){
        /*Reservamos un objeto si no el objeto no esta ya seleccionado
        Si no lo que hara sera sacar un mensaje de error, de que ese objeto ya esta reservado
         */
        if(!checkReservado.isSelected()){
            CRUD_Objetos.reservar(Integer.parseInt(txtId.getText()));

            cargarTabla();
            checkReservado.setSelected(true);
        }else {
            Alerts.crearAlertaError(stack, "El objeto ya esta reservado");
        }
        }

    @FXML
    private void addObjeto(){
        /*Lo que hacemos es buscar el usuario, segun el id que se muestre en el textfield
        para asi poder añadir un nuevo objeto
         */
        boolean reserved=false;
        try{
            //Buscamos si el usuario existe
            Usuarios u1 = CRUD_Usuarios.buscarUser(Integer.parseInt(txtUser.getText()));

            if(u1 == null){
                //Si no lo encuentra saldra un mensaje de error
                Alerts.crearAlertaError(stack, "Usuario no encontrado");
            }else{
                //Si lo encuentra añadira el objeto
                if(checkReservado.isSelected()){
                    reserved=true;
                }
                Objetos objetoNuevo = new Objetos(u1, txtNombre.getText(),
                        cmbTipo.getValue().toString(), cmbEstado.getValue().toString(),
                        Integer.parseInt(txtPrecio.getText()), reserved);
                CRUD_Objetos.insertarObjeto(objetoNuevo);
                Alerts.crearAlertaInfo(stack, "Objeto insertado");
                cargarTabla();
                limpiar();
            }

        }catch (Exception e){
            Alerts.crearAlertaError(stack, "Error al añadir");
        }

    }

    @FXML //Metodo para borrar un objeto, si hay alguno seleccionado
    private void deleteObject(){
        if(txtId.getText().isEmpty()){
            Alerts.crearAlertaError(stack,"Selecciona un objeto");
        }else{
            CRUD_Objetos.eliminarObjeto(Integer.parseInt(txtId.getText()));
            Alerts.crearAlertaInfo(stack,"Objeto eliminado correctamente");
            cargarTabla();
            limpiar();
        }
    }

    @FXML //Limpiamos los campos de texto y los combobox
    private void limpiar(){
        txtUser.setDisable(false);
        txtUser.setText("");
        txtId.setText("");
        txtPrecio.setText("");
        txtNombre.setText("");
        cmbEstado.setValue("Estado");
        cmbTipo.setValue("Tipo");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        llenarComboEstado();
        llenarComboTipo();
    }
}
