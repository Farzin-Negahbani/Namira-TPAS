package Controllers;

/**
 * Created by Farzin.negahbani on 5/26/2016.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.Game;
import Classes.Main;
import Classes.StaticThings;
import Classes.Tuple;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
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

public class friendlyMatchController {
    public static int teamNumber1 = 0;
    public static int teamNumber2 = 0;
    @FXML
    private ObservableList<Tuple> data1 = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Tuple> data2 = FXCollections.observableArrayList();



    @FXML
    private TableView<Tuple> tableView2;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumn1;

    @FXML
    private TableColumn<Tuple, StringProperty> teamNameColumn1;

    @FXML
    private TableView<Tuple> tableView1;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumn2;

    @FXML
    private TableColumn<Tuple, StringProperty> teamNameColumn2;


    @FXML
    private Spinner<Integer> staminaSpinner;

    @FXML
    private CheckBox penaltyModeCheckBox;

    @FXML
    private CheckBox goldenGoalCheckBox;

    @FXML
    private CheckBox syncModeCheckBox;

    @FXML
    private CheckBox foulCheckBox;

    @FXML
    private Spinner<Integer> numberOfMatchSpinner;

    @FXML
    private CheckBox offsideCheckBox;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox extraHalfTimeCheckBox;

    @FXML
    private Spinner<Integer> extraHalfTimeSpinner;

    @FXML
    private Spinner<Integer> halfTimeSpinner;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox monitorCheckBox;

    @FXML // fx:id="addButton1"
    private Button addButton1; // Value injected by FXMLLoader

    @FXML // fx:id="removeButton1"
    private Button removeButton1; // Value injected by FXMLLoader

    @FXML // fx:id="removeButton2"
    private Button removeButton2; // Value injected by FXMLLoader


    @FXML // fx:id="addButton2"
    private Button addButton2; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        assert goldenGoalCheckBox != null : "fx:id=\"goldenGoalCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert saveMenuItem != null : "fx:id=\"saveMenuItem\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert syncModeCheckBox != null : "fx:id=\"syncModeCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert exitMenuItem != null : "fx:id=\"exitMenuItem\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert penaltyModeCheckBox != null : "fx:id=\"penaltyModeCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert loadMenuItem != null : "fx:id=\"loadMenuItem\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert tableView1 != null : "fx:id=\"leagueTableView1\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert tableView2 != null : "fx:id=\"leagueTableView2\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert extraHalfTimeCheckBox != null : "fx:id=\"extraHalfTimeCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert teamNameColumn2 != null : "fx:id=\"teamNameColumn2\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert teamNameColumn1 != null : "fx:id=\"teamNameColumn1\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert foulCheckBox != null : "fx:id=\"foulCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert removeButton2 != null : "fx:id=\"removeButton2\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert monitorCheckBox != null : "fx:id=\"monitorCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert offsideCheckBox != null : "fx:id=\"offsideCheckBox\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert teamNumberColumn2 != null : "fx:id=\"teamNumberColumn2\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert teamNumberColumn1 != null : "fx:id=\"teamNumberColumn1\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert addButton1 != null : "fx:id=\"addButton1\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert removeButton1 != null : "fx:id=\"removeButton1\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert addButton2 != null : "fx:id=\"addButton2\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";
        assert statusLabel !=null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'FriendlyMatch.fxml'.";

        teamNameColumn1.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        teamNumberColumn1.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));

        tableView1.setItems(data1);

        teamNameColumn2.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        teamNumberColumn2.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));

        tableView2.setItems(data2);


        halfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 60000, 3000, 1));
        numberOfMatchSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 1, 1));
        staminaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 800000, 130600, 1));
        extraHalfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3000,500,1));

        extraHalfTimeSpinner.setVisible(false);

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

        halfTimeSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                halfTimeSpinner.increment(0); // won't change value, but will commit editor
            }
        });

        //Game game =  new Game();


        exitMenuItem.setOnAction((event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }));


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

        addButton1.setOnAction((event1 -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Start Files", "*"));
            File file = fileChooser.showOpenDialog(StaticThings.stage);
            boolean flag = true;
            for (Tuple t:data1) {
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
            for (Tuple t:data2) {
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

            if(flag){
                data1.add(new Tuple(Main.myGet(file), file.getPath(), 1, ++ teamNumber1));
            }


        }));


        addButton2.setOnAction((event1 -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Start Files", "*"));
            File file = fileChooser.showOpenDialog(StaticThings.stage);
            boolean flag = true;
            for (Tuple t:data1) {
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
            for (Tuple t:data2) {
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

            if(flag){
                data2.add(new Tuple(Main.myGet(file), file.getPath(), 1, ++ teamNumber2));
            }


        }));

        startButton.setOnAction((event -> {
            ArrayList<String> teamList = new ArrayList<>();
            String temp = "";
            for(Tuple s: data1) {
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                temp = temp.substring(0,temp.length()-1);
                teamList.add(temp);
            }
            temp = "";
            for(Tuple s: data2){
                temp = temp.concat(s.getTeamBinaryLoc().concat(","));
            }
            if(!temp.equals("")){
                temp = temp.substring(0,temp.length()-1);
                teamList.add(temp);
            }

            Integer extraHalfTimeSpinnerValue = 0;

            if(extraHalfTimeCheckBox.isSelected()){
                extraHalfTimeSpinnerValue  = extraHalfTimeSpinner.getValue();
            }

            Game.set_Game_Settings(numberOfMatchSpinner.getValue()*data1.size()*data2.size(),monitorCheckBox.isSelected(), syncModeCheckBox.isSelected(), "friendly", "NAMIRA CUP", staminaSpinner.getValue(), numberOfMatchSpinner.getValue(),
                    foulCheckBox.isSelected(), offsideCheckBox.isSelected(), goldenGoalCheckBox.isSelected(), penaltyModeCheckBox.isSelected(), extraHalfTimeSpinnerValue/10, halfTimeSpinner.getValue()/10,teamList);

            System.out.println(Game.toJSON());

            Stage stage1 = (Stage) backButton.getScene().getWindow();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/ResultPage.fxml"));
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
            stage1.close();
        }));


        backButton.setOnAction((event -> {
            Stage stage1 = (Stage) backButton.getScene().getWindow();
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
            stage1.close();
        }));

    }
}
