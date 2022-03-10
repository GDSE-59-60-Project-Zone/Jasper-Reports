package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import view.tdm.CustomerTM;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class JasperReportsController implements Initializable {


    public TextField txtCusID;
    public TextField txtCusName;
    public TextField txtCusAddress;
    public TextField txtCusSalary;
    public TableView<CustomerTM> tblCustomer;
    public JFXButton btnSaveCustomer;
    public JFXButton btnHelloJasper;
    public JFXComboBox cmbCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    private void initTable() {
        /*initialize table column values*/
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        /*set two images to the last columns*/
        tblCustomer.getColumns().get(3).setCellValueFactory((param) -> {
            ImageView edit = new ImageView("/view/assets/icons/draw.png");
            ImageView delete = new ImageView("/view/assets/icons/trash.png");

            return new ReadOnlyObjectWrapper(new HBox(10, edit, delete));
        });
    }


    public void helloJasperEvent(MouseEvent event) {

    }


    public void generateSQLReport(MouseEvent event) {

    }

    public void sqlChartEvent(MouseEvent event) {

    }

    public void saveCustomerEvent(MouseEvent event) {
        /*Add Customer*/
        /*01. Gather Details*/
        String customerID = txtCusID.getText();
        String customerName = txtCusName.getText();
        String customerAddress = txtCusAddress.getText();
        String customerSalary = txtCusSalary.getText();

        /*Fill those values to the Table Model before adding to the table*/
        CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, new BigDecimal(customerSalary));

        /*Get the table and add the table model*/
        tblCustomer.getItems().add(customerTM);


    }

    public void beanArrayEvent(MouseEvent event) {

    }

    public void reportWithParam(MouseEvent event) {


    }

    public void sqlAndParamEvent(MouseEvent event) {


    }
}
