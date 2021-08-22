//package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

import java.text.DecimalFormat;

/**
 *
 * @author soumilverma
 *
 * Source:
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
 * https://www.tutorialspoint.com/how-to-retrieve-the-contents-of-a-text-field-in-javafx
 * https://stackoverflow.com/questions/20205145/javafx-how-to-show-read-only-text
 * https://www.tutorialspoint.com/javafx/layout_gridpane.htm
 * https://stackoverflow.com/questions/57540770/how-to-align-labels-and-textfields-in-javafx
 * https://www.javatpoint.com/javafx-textfield
 * https://www.geeksforgeeks.org/javafx-label/
 * https://stackoverflow.com/questions/23521958/how-to-add-mouse-event-for-a-button
 *
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        DecimalFormat formatter = new DecimalFormat("#.##");

        //creating text fields
        Text interestRateLabel = new Text("Annual Interest Rate: ");
        Text numOfYearsLabel = new Text("Number of Years: ");
        Text loanAmountLabel = new Text("Loan Amount: ");
        Text monthlyPaymentLabel = new Text("Loan Amount: ");
        Text totalPaymentLabel = new Text("Total Payment: ");

        //Creating TextField
        TextField interestRateField = new TextField();
        TextField numOfYearsField = new TextField();
        TextField loanAmountField = new TextField();
        TextField monthlyPaymentField = new TextField();
        TextField totalPaymentField = new TextField();

        //fields set to read-only to view output
        monthlyPaymentField.setDisable(true);
        totalPaymentField.setDisable(true);

        //Creating Buttons
        Button button = new Button("Calculate");

        //variable define for calculation

        //button action
        try {
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Calculation calculate = new Calculation();

                    calculate.setInterestRate(Double.parseDouble(interestRateField.getText()));
                    calculate.setNumOfYears(Integer.parseInt(numOfYearsField.getText()));
                    calculate.setLoanAmount(Double.parseDouble(loanAmountField.getText()));

                    monthlyPaymentField.setText(String.valueOf(formatter.format(calculate.monthlyCompound())));
                    totalPaymentField.setText(String.valueOf(formatter.format(calculate.totalPayment())));
                }
            });
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(350, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(interestRateLabel, 0, 0);
        gridPane.add(numOfYearsLabel, 0, 1);
        gridPane.add(loanAmountLabel, 0, 2);
        gridPane.add(monthlyPaymentLabel, 0, 3);
        gridPane.add(totalPaymentLabel, 0, 4);

        gridPane.add(interestRateField, 1, 0);
        gridPane.add(numOfYearsField, 1, 1);
        gridPane.add(loanAmountField, 1, 2);
        gridPane.add(monthlyPaymentField, 1, 3);
        gridPane.add(totalPaymentField, 1, 4);

        gridPane.add(button, 1, 5);

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Loan Calculator");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    //main
    public static void main(String[] args) {
        launch(args);
    }
}
