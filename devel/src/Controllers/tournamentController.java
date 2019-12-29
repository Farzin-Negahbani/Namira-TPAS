package Controllers;

/**
 * Created by Farzin.negahbani on 5/26/2016.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Classes.Game;
import Classes.Main;
import Classes.StaticThings;
import Classes.Tuple;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class tournamentController {


    public static int teamNumber = 0;

    public static int teamNumberA = 0;
    public static int teamNumberB = 0;
    public static int teamNumberC = 0;
    public static int teamNumberD = 0;
    public static int teamNumberE = 0;
    public static int teamNumberF= 0;
    public static int teamNumberG = 0;
    public static int teamNumberH = 0;


    @FXML
    private TabPane tabPane;

    @FXML
    private ObservableList<Tuple> dataA = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewA;

    @FXML
    private  ObservableList<Tuple> dataB = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewB;


    @FXML
    private  ObservableList<Tuple> dataC = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewC;


    @FXML
    private  ObservableList<Tuple> dataD = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewD;


    @FXML
    private  ObservableList<Tuple> dataE = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewE;

    @FXML
    private  ObservableList<Tuple> dataF = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewF;

    @FXML
    private  ObservableList<Tuple> dataG = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewG;

    @FXML
    private  ObservableList<Tuple> dataH = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuple> tableViewH;

    @FXML
    private Label statusLabel;

    @FXML
    private Spinner<Integer> staminaSpinner;

    @FXML
    private CheckBox penaltyCheckBox;

    @FXML
    private CheckBox goldenGoalCheckBox;

    @FXML
    private CheckBox foulCheckBox;

    @FXML
    private Spinner<Integer> numberOfMatchSpinner;

    @FXML
    private CheckBox offsideCheckBox;

    @FXML
    private Spinner<Integer> numberOfTeamsSpinner;

    @FXML
    private TextField tournamentNameTextField;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    @FXML
    private Tab   GroupA;

    @FXML
    private Button addGroupA;

    @FXML
    private Button removeGroupA;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnA;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnA;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnA;

    @FXML
    private Tab   GroupB;

    @FXML
    private Button addGroupB;

    @FXML
    private Button removeGroupB;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnB;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnB;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnB;

    @FXML
    private Tab   GroupC;

    @FXML
    private Button addGroupC;

    @FXML
    private Button removeGroupC;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnC;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnC;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnC;

    @FXML
    private Tab   GroupD;

    @FXML
    private Button addGroupD;

    @FXML
    private Button removeGroupD;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnD;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnD;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnD;

    @FXML
    private Tab   GroupE;

    @FXML
    private Button addGroupE;

    @FXML
    private Button removeGroupE;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnE;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnE;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnE;

    @FXML
    private Tab   GroupF;

    @FXML
    private Button addGroupF;

    @FXML
    private Button removeGroupF;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnF;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnF;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnF;

    @FXML
    private Tab   GroupG;

    @FXML
    private Button addGroupG;

    @FXML
    private Button removeGroupG;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnG;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnG;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnG;

    @FXML
    private Tab   GroupH;

    @FXML
    private Button addGroupH;

    @FXML
    private Button removeGroupH;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumnH;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumnH;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumnH;

    @FXML
    private Button shuffleButton;

    @FXML
    private Spinner<Integer> numberOfGroupsSpinner;

    @FXML
    private CheckBox extraHalftTimeCheckBox;

    @FXML
    private Spinner<Integer> extraHalfTimeSpinner;

    @FXML
    private Spinner<Integer> halfeTimeSpinner;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private CheckBox monitorCheckBox;
    @FXML
    private CheckBox syncModeCheckBox;


    @FXML
    void initialize() {
        assert staminaSpinner != null : "fx:id=\"staminaSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert penaltyCheckBox != null : "fx:id=\"penaltyCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert goldenGoalCheckBox != null : "fx:id=\"goldenGoalCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert foulCheckBox != null : "fx:id=\"foulCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert numberOfMatchSpinner != null : "fx:id=\"numberOfMatchSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert offsideCheckBox != null : "fx:id=\"offsideCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert numberOfTeamsSpinner != null : "fx:id=\"numberOfTeamsSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert tournamentNameTextField != null : "fx:id=\"tournamentNameTextField\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupA != null : "fx:id=\"addGroupA\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupA != null : "fx:id=\"removeGroupA\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnA != null : "fx:id=\"teamNumberColumnA\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnA != null : "fx:id=\"teamNameColumnA\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnA != null : "fx:id=\"binaryLocationColumnA\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupB != null : "fx:id=\"addGroupB\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupB != null : "fx:id=\"removeGroupB\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnB != null : "fx:id=\"teamNumberColumnB\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnB != null : "fx:id=\"teamNameColumnB\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnB != null : "fx:id=\"binaryLocationColumnB\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupC != null : "fx:id=\"addGroupC\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupC != null : "fx:id=\"removeGroupC\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnC != null : "fx:id=\"teamNumberColumnC\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnC != null : "fx:id=\"teamNameColumnC\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnC != null : "fx:id=\"binaryLocationColumnC\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupD != null : "fx:id=\"addGroupD\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupD != null : "fx:id=\"removeGroupD\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnD != null : "fx:id=\"teamNumberColumnD\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnD != null : "fx:id=\"teamNameColumnD\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnD != null : "fx:id=\"binaryLocationColumnD\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupE != null : "fx:id=\"addGroupE\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupE != null : "fx:id=\"removeGroupE\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnE != null : "fx:id=\"teamNumberColumnE\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnE != null : "fx:id=\"teamNameColumnE\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnE != null : "fx:id=\"binaryLocationColumnE\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupF != null : "fx:id=\"addGroupF\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupF != null : "fx:id=\"removeGroupF\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnF != null : "fx:id=\"teamNumberColumnF\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnF != null : "fx:id=\"teamNameColumnF\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnF != null : "fx:id=\"binaryLocationColumnF\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupG != null : "fx:id=\"addGroupG\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupG != null : "fx:id=\"removeGroupG\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnG != null : "fx:id=\"teamNumberColumnG\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnG != null : "fx:id=\"teamNameColumnG\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnG != null : "fx:id=\"binaryLocationColumnG\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert addGroupH != null : "fx:id=\"addGroupH\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert removeGroupH != null : "fx:id=\"removeGroupH\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNumberColumnH != null : "fx:id=\"teamNumberColumnH\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert teamNameColumnH != null : "fx:id=\"teamNameColumnH\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert binaryLocationColumnH != null : "fx:id=\"binaryLocationColumnH\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert shuffleButton != null : "fx:id=\"shuffleButton\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert numberOfGroupsSpinner != null : "fx:id=\"numberOfGroupsSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert extraHalftTimeCheckBox != null : "fx:id=\"extraHalftTimeCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert extraHalfTimeSpinner != null : "fx:id=\"extraHalfTimeSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert halfeTimeSpinner != null : "fx:id=\"halfeTimeSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";


        teamNameColumnA.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnA.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnA.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewA.setItems(dataA);

        teamNameColumnB.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnB.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnB.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewB.setItems(dataB);

        teamNameColumnC.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnC.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnC.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewC.setItems(dataC);

        teamNameColumnD.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnD.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnD.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewD.setItems(dataD);

        teamNameColumnE.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnE.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnE.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewE.setItems(dataE);

        teamNameColumnF.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnF.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnF.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewF.setItems(dataF);

        teamNameColumnG.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnG.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnG.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewG.setItems(dataG);

        teamNameColumnH.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumnH.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));
        teamNumberColumnH.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));
        tableViewH.setItems(dataH);

        GroupA.setDisable(false);
        GroupB.setDisable(false);
        GroupC.setDisable(true);
        GroupD.setDisable(true);
        GroupE.setDisable(true);
        GroupF.setDisable(true);
        GroupG.setDisable(true);
        GroupH.setDisable(true);

        extraHalfTimeSpinner.setVisible(false);
        //Setting Spinners
        halfeTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 60000, 6000, 1));
        numberOfMatchSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 1, 1));
        staminaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 800000, 130600, 1));
        numberOfTeamsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 32, 8, 1));
        extraHalfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3000,500,1));
        numberOfGroupsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8,2,1));
        numberOfGroupsSpinner.setEditable(false);
        ///
       /* foulCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                System.out.print(new_val);
            }
        });*/

        extraHalftTimeCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if(new_val){
                    extraHalfTimeSpinner.setVisible(true);
                }
                else{
                    extraHalfTimeSpinner.setVisible(false);
                }
            }
        });

        numberOfGroupsSpinner.setOnMouseClicked((event1 -> {

            switch (numberOfGroupsSpinner.getValue()){
                case 1:
                    GroupA.setDisable(false);
                    GroupB.setDisable(true);
                    GroupC.setDisable(true);
                    GroupD.setDisable(true);
                    GroupE.setDisable(true);
                    GroupF.setDisable(true);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 2:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(true);
                    GroupD.setDisable(true);
                    GroupE.setDisable(true);
                    GroupF.setDisable(true);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 3:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(true);
                    GroupE.setDisable(true);
                    GroupF.setDisable(true);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 4:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(false);
                    GroupE.setDisable(true);
                    GroupF.setDisable(true);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 5:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(false);
                    GroupE.setDisable(false);
                    GroupF.setDisable(true);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 6:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(false);
                    GroupE.setDisable(false);
                    GroupF.setDisable(false);
                    GroupG.setDisable(true);
                    GroupH.setDisable(true);
                    break;
                case 7:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(false);
                    GroupE.setDisable(false);
                    GroupF.setDisable(false);
                    GroupG.setDisable(false);
                    GroupH.setDisable(true);
                    break;
                case 8:
                    GroupA.setDisable(false);
                    GroupB.setDisable(false);
                    GroupC.setDisable(false);
                    GroupD.setDisable(false);
                    GroupE.setDisable(false);
                    GroupF.setDisable(false);
                    GroupG.setDisable(false);
                    GroupH.setDisable(false);
                    break;
            }

        }));


        numberOfMatchSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                numberOfMatchSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        extraHalfTimeSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                extraHalfTimeSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        staminaSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                staminaSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        halfeTimeSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                halfeTimeSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        addGroupA.setOnAction((event)->{

            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataA) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataA.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberA));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        addGroupB.setOnAction((event)->{

            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataB) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataB.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberB));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        addGroupC.setOnAction((event)->{
            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataC) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataC.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberC));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        addGroupD.setOnAction((event)->{
            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataD) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataD.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberD));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        addGroupE.setOnAction((event)->{

            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataE) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataE.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberE));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });


        addGroupF.setOnAction((event)->{
            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataF) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataF.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberF));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });


        addGroupG.setOnAction((event)->{
            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataG) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataG.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberG));
                    ++ teamNumber;
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        addGroupH.setOnAction((event)->{

            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:dataH) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000), ae -> {
                            statusLabel.setText("");
                        }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    dataH.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumberH));
                    ++ teamNumber;
                }

            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2000), ae -> {
                    statusLabel.setText("");
                }));
                timeline.play();
            }
        });

        exitMenuItem.setOnAction((event -> {
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.close();
        }));

        startButton.setOnAction((event -> {
            ArrayList<String> teamList = new ArrayList<>();

            Integer extraHalfTimeSpinnerValue = 0;

            if(extraHalftTimeCheckBox.isSelected()){
                extraHalfTimeSpinnerValue  = extraHalfTimeSpinner.getValue();
            }

            String name = "Namira Tournament";
            if (!tournamentNameTextField.getText().equals("")){
                name = tournamentNameTextField.getText();
            }

            String temp = "";

            for(Tuple s: dataA) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataB) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataC) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataD) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataE) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataF) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataG) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: dataH) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                teamList.add(temp);
            }


            //////////////FIX number of total match
            Game.set_Game_Settings(numberOfMatchSpinner.getValue()*dataA.size(),monitorCheckBox.isSelected(), syncModeCheckBox.isSelected(), "league", name, staminaSpinner.getValue(), numberOfMatchSpinner.getValue(),
                    foulCheckBox.isSelected(), offsideCheckBox.isSelected(), goldenGoalCheckBox.isSelected(), penaltyCheckBox.isSelected(), extraHalfTimeSpinnerValue/10, halfeTimeSpinner.getValue()/10,teamList);

            System.out.println(Game.toJSON());

        }));


        backButton.setOnAction((event -> {
            Stage stage1 = (Stage) startButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/First.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }));
    }
}
