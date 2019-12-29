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

public class worldCupController {


    public static int teamNumber = 0;

    public static int teamNumberA = 0;
    public static int teamNumberB = 0;
    public static int teamNumberC = 0;
    public static int teamNumberD = 0;

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
    private CheckBox offsideCheckBox;

    @FXML
    private Spinner<Integer> numberOfTeamsSpinner;


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
    private CheckBox extraHalfTimeCheckBox;

    @FXML
    private Spinner<Integer> extraHalfTimeSpinner;

    @FXML
    private Spinner<Integer> halfeTimeSpinner;

    @FXML
    private Spinner<Integer> numberOfMatchSpinner;



    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private CheckBox monitorCheckBox;
    @FXML
    private CheckBox syncModeCheckBox;


    @FXML
    void initialize() {
        assert staminaSpinner != null : "fx:id=\"staminaSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
//        assert penaltyCheckBox != null : "fx:id=\"penaltyCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
//        assert goldenGoalCheckBox != null : "fx:id=\"goldenGoalCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
//        assert foulCheckBox != null : "fx:id=\"foulCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
//        assert offsideCheckBox != null : "fx:id=\"offsideCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
        assert numberOfTeamsSpinner != null : "fx:id=\"numberOfTeamsSpinner\" was not injected: check your FXML file 'Tournament.fxml'.";
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
        assert extraHalfTimeCheckBox != null : "fx:id=\"extraHalftTimeCheckBox\" was not injected: check your FXML file 'Tournament.fxml'.";
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

        GroupA.setDisable(false);
        GroupB.setDisable(false);
        GroupC.setDisable(false);
        GroupD.setDisable(false);

        extraHalfTimeSpinner.setVisible(false);
        //Setting Spinners
        halfeTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 60000, 6000, 1));
        numberOfMatchSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 1, 1));
        staminaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 800000, 130600, 1));
        numberOfTeamsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 32, 8, 1));
        extraHalfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3000,500,1));

        extraHalfTimeCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if(new_val){
                    extraHalfTimeSpinner.setVisible(true);
                }
                else{
                    extraHalfTimeSpinner.setVisible(false);
                }
            }
        });

        extraHalfTimeSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                extraHalfTimeSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        numberOfMatchSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                numberOfMatchSpinner.increment(0); // won't change value, but will commit editor
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

        exitMenuItem.setOnAction((event -> {
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.close();
        }));

        startButton.setOnAction((event -> {
            ArrayList<String> teamList = new ArrayList<>();

            Integer extraHalfTimeSpinnerValue = 0;

            if(extraHalfTimeCheckBox.isSelected()){
                extraHalfTimeSpinnerValue  = extraHalfTimeSpinner.getValue();
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


            //////////////FIX number of total match
            Game.set_Game_Settings(1, monitorCheckBox.isSelected(), syncModeCheckBox.isSelected(), "World Cup", "World Cup", staminaSpinner.getValue(), numberOfMatchSpinner.getValue(), foulCheckBox.isSelected(), offsideCheckBox.isSelected(), goldenGoalCheckBox.isSelected(), penaltyCheckBox.isSelected(), extraHalfTimeSpinnerValue/10, halfeTimeSpinner.getValue()/10,teamList);
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
