package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tdm.CustomerTM;

import java.net.URL;
import java.util.HashMap;
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
        //Add Jasper Libraries to the project
        //Add Created Report to the Project Area.
        try {

            //File Type - jrxml (Not Compiled)
            /*//Catch The Row Report
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/HelloJasper.jrxml"));

            //Compile the Report
            JasperReport compileReport = JasperCompileManager.compileReport(load);

            //Fill the information which report needed
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource(1));

            //Then the report is ready.. let's view it
            JasperViewer.viewReport(jasperPrint,false);

            //if you have a printer. you can directly print without viewing it
            //JasperPrintManager.printReport(jasperPrint,false);*/


            //No Compilation, Load and view
            //We can also skip the compilation process of the report by submitting compile version
            //Catch The Compiled Report
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/HelloJasper.jasper"));

            //Fill the information which report needed
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource(1));

            //Then the report is ready.. let's view it
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }


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
        double customerSalary = Double.parseDouble(txtCusSalary.getText());

        /*Fill those values to the Table Model before adding to the table*/
        CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, customerSalary);

        /*Get the table and add the table model*/
        tblCustomer.getItems().add(customerTM);


    }

    public void beanArrayEvent(MouseEvent event) {

        //Bean Arrays
        CustomerTM[] allCustomers= new CustomerTM[3];
        allCustomers[0]= new CustomerTM("C001","Dasun","Galle",100.00);
        allCustomers[1]= new CustomerTM("C002","Kamal","Panadura",200.00);
        allCustomers[2]= new CustomerTM("C003","Ranuka","kaluthara",300.00);


        try {
            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/BeanArrayReport.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, new JRBeanArrayDataSource(allCustomers));
            JasperViewer.viewReport(jasperPrint, false);


        } catch (JRException e) {
            e.printStackTrace();
        }


    }

    public void reportWithParam(MouseEvent event) {

        //We should gather information to send to the report
        String customerID = txtCusID.getText();
        String customerName = txtCusName.getText();
        String customerAddress = txtCusAddress.getText();
        double customerSalary = Double.parseDouble(txtCusSalary.getText());

        HashMap paramMap = new HashMap();
        paramMap.put("id", customerID);// id = report param name // customerID = UI typed value
        paramMap.put("name", customerName);
        paramMap.put("address", customerAddress);
        paramMap.put("salary", customerSalary);

        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/ParameterReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, paramMap, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }


    }

    public void sqlAndParamEvent(MouseEvent event) {


    }
}
