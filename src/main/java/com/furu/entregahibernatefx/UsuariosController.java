package com.furu.entregahibernatefx;

import alertas.Alerts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTogglePane;
import entity.Usuarios;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.CRUD_Usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class UsuariosController implements Initializable {

    @FXML //Panel para las alertas
    private StackPane stack;
    @FXML
    private JFXButton btnUser, btnObject, btnExit, btnAddUser, btnRemoveUser, btnEditUser;

    @FXML
    private JFXListView listaUsuarios;

    @FXML
    private JFXTextField txtIdUser, txtNameUser, txtEmailUser;

    @FXML //Metodo para añadir usuarios
    private void addUser(){
        /*El metodo comprueba que los campos esten llenos, solo añade si estan llenos pero el de id no lo esta, es decir
        El usuario que vamos a añadir no estaba en la tabla anteriormente
         */
        Usuarios u = new Usuarios(txtNameUser.getText(), txtEmailUser.getText());
        if(txtNameUser.getText().isEmpty() || txtEmailUser.getText().isEmpty()){
            Alerts.crearAlertaError(stack, "Rellena los campos");
        }else{
            if(txtIdUser.getText().isEmpty()){
                Alerts.crearAlertaInfo(stack,"Usuario añadido");
                CRUD_Usuarios.insertarUser(u);
                cargarLista();
                limpiar();
            }else{
                Alerts.crearAlertaError(stack, "Atencion!! Usuario duplicado");
            }

        }

    }

    @FXML //Metodo para cargar la tabla
    private void cargarLista(){
        try{
            List<Usuarios> user = CRUD_Usuarios.llenarTabla();
            listaUsuarios.setItems(FXCollections.observableList(user));
        }catch (Exception e){
            Alerts.crearAlertaError(stack, "No se pudo cargar la lista");
        }
    }


    @FXML//Mandamos la zona seleccionada en la tabla a los campos de texto
    public void userCargado(Usuarios u){
        /* El try-catch lo usamos para que pinchas en la tabla pero fuera de los usuarios, salte un men
        saje por pantalla
         */
        try{
            txtIdUser.setText(String.valueOf(u.getId_user()));
            txtNameUser.setText(u.getNombre_usuario());
            txtEmailUser.setText(u.getEmail_usuario());
        }catch (Exception e){
            Alerts.crearAlertaError(stack, "No estas seleccionando ningun usuario");
        }

    }

    @FXML //Usamos el metodo de arriba para rellenar los textfields
    private void userSelected(){
        Usuarios userSel = (Usuarios) listaUsuarios.getSelectionModel().getSelectedItem();
        userCargado(userSel);
    }

    @FXML //Metodo del boton para limpiar los campos de texto
    private void limpiar(){
        txtIdUser.setText("");
        txtNameUser.setText("");
        txtEmailUser.setText("");
    }
    @FXML //Metodo para modificar
    private void modificar(){

        try{
            Usuarios user = new Usuarios(Integer.parseInt(txtIdUser.getText()), txtNameUser.getText(),txtEmailUser.getText());
            CRUD_Usuarios.modificarUser(Integer.parseInt(txtIdUser.getText()), txtNameUser.getText(),txtEmailUser.getText());
            Alerts.crearAlertaInfo(stack,"Usuario modificado");
            cargarLista();
            limpiar();
        }catch(Exception e){
            Alerts.crearAlertaError(stack,"Selecciona un usuario");
        }

    }

    @FXML //Metodo para borrar la tabla
    private void borrar(){
        /*Este metodo funciona igual que el de abajo de borrar, solo borrara o modificara un usuario cuando haya alguno seleccionad*/
        try{
            Usuarios user = new Usuarios(Integer.parseInt(txtIdUser.getText()), txtNameUser.getText(),txtEmailUser.getText());
            CRUD_Usuarios.eliminarUser(user);
            Alerts.crearAlertaInfo(stack,"Usuario eliminado");
            cargarLista();
            limpiar();
        }catch (Exception e){
            Alerts.crearAlertaError(stack,"Selecciona un usuario");
        }

    }

    @FXML //Menu de arriba, para cambiar las pantallas
    private void cambiarVista() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("vista-objetos.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

        Stage loginStage = (Stage) this.btnObject.getScene().getWindow();
        loginStage.close();
    }
    @FXML //Boton para salir de la aplicacion
    private void salir(){
        System.exit(0);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLista();

    }
}
