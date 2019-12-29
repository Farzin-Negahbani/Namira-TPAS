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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class leagueController {

    public int index = 0;
    public static int teamNumber = 0;
    @FXML
    private ObservableList<Tuple> data = FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> staminaSpinner;

    @FXML
    private CheckBox penaltyModeCheckBox;

    @FXML
    private CheckBox goldenGoalCheckBox;

    @FXML
    private CheckBox foulCheckBox;

    @FXML
    private Spinner<Integer> numberOfMatchSpinner;

    @FXML
    private CheckBox offsideCheckBox;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button shuffleButton;

    @FXML
    private TableView<Tuple> leagueTableView;

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumn;

    @FXML
    private TableColumn<Tuple, StringProperty> teamNameColumn;

    @FXML
    private TableColumn<Tuple, StringProperty> binaryLocationColumn;

    @FXML
    private Spinner<Integer> numberOfTeamsSpinner;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private CheckBox extraHalfTimeCheckBox;

    @FXML
    private Spinner<Integer> extraHalfTimeSpinner;

    @FXML
    private Spinner<Integer> halfTimeSpinner;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox monitorCheckBox;
    @FXML
    private CheckBox syncModeCheckBox;

    @FXML
    void initialize() {
        assert staminaSpinner != null : "fx:id=\"stamminaSpinner\" was not injected: check your FXML file 'League.fxml'.";
        assert penaltyModeCheckBox != null : "fx:id=\"penaltyModeCheckBox\" was not injected: check your FXML file 'League.fxml'.";
        assert goldenGoalCheckBox != null : "fx:id=\"goldenGoalCheckBox\" was not injected: check your FXML file 'League.fxml'.";
        assert foulCheckBox != null : "fx:id=\"foulCheckBox\" was not injected: check your FXML file 'League.fxml'.";
        assert numberOfMatchSpinner != null : "fx:id=\"numberOfMatchSpinner\" was not injected: check your FXML file 'League.fxml'.";
        assert offsideCheckBox != null : "fx:id=\"offsideCheckBox\" was not injected: check your FXML file 'League.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'League.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'League.fxml'.";
        assert shuffleButton != null : "fx:id=\"shuffleButton\" was not injected: check your FXML file 'League.fxml'.";
        assert leagueTableView != null : "fx:id=\"teamTable\" was not injected: check your FXML file 'League.fxml'.";
        assert teamNumberColumn != null : "fx:id=\"numberColumn\" was not injected: check your FXML file 'League.fxml'.";
        assert teamNameColumn != null : "fx:id=\"teamNameColumn\" was not injected: check your FXML file 'League.fxml'.";
        assert binaryLocationColumn != null : "fx:id=\"binaryLocationColumn\" was not injected: check your FXML file 'League.fxml'.";
        assert numberOfTeamsSpinner != null : "fx:id=\"numberOfTeams\" was not injected: check your FXML file 'League.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'League.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'League.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'League.fxml'.";
        assert saveMenuItem != null : "fx:id=\"saveMenuItem\" was not injected: check your FXML file 'League.fxml'.";
        assert loadMenuItem != null : "fx:id=\"loadMenuItem\" was not injected: check your FXML file 'League.fxml'.";
        assert exitMenuItem != null : "fx:id=\"exitMenuItem\" was not injected: check your FXML file 'League.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'League.fxml'.";
        assert extraHalfTimeCheckBox != null : "fx:id=\"extraHalftTimeCheckBox\" was not injected: check your FXML file 'League.fxml'.";
        assert extraHalfTimeSpinner != null : "fx:id=\"extraHalfTimeSpinner\" was not injected: check your FXML file 'League.fxml'.";
        assert halfTimeSpinner != null : "fx:id=\"halfeTimeSpinner\" was not injected: check your FXML file 'League.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'League.fxml'.";




        extraHalfTimeSpinner.setVisible(false);

        teamNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));

        teamNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));

        leagueTableView.setItems(data);
        extraHalfTimeSpinner.setVisible(false);

        //Setting Spinners
        halfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 60000, 6000, 1));
        numberOfMatchSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 1, 1));
        staminaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 800000, 130600, 1));
        numberOfTeamsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 32, 8, 1));
        extraHalfTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3000,500,1));

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

        leagueTableView.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = leagueTableView.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                index = ((TablePosition) selectedCells.get(0)).getRow();
            }
        });

        removeButton.setOnAction(event -> {
            data.remove(index);
            teamNumber --;
            int i = 1;
            for( Tuple t: data){
                System.out.println(t.getTeamNumber());
                t.setTeamNumber(i);
                i++;
            }
        });

        addButton.setOnAction((event)->{
            if(teamNumber < numberOfTeamsSpinner.getValue()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Start Files", "*.sh", "*"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:data) {
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
                    data.add(new Tuple(Main.myGet(file), file.getPath(), 1, ++ teamNumber));
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
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }));

        startButton.setOnAction((event -> {

            ArrayList<String> teamList = new ArrayList<>();

            Integer extraHalfTimeSpinnerValue = 0;

            if(extraHalfTimeCheckBox.isSelected()){
                extraHalfTimeSpinnerValue  = extraHalfTimeSpinner.getValue();
            }

            for(Tuple s: data) {
                teamList.add(s.getTeamBinaryLoc());
            }

            String name = "Namira League";
            if (!nameTextField.getText().equals("")){
                name = nameTextField.getText();
            }

            Game.set_Game_Settings(numberOfMatchSpinner.getValue()*data.size(),monitorCheckBox.isSelected(), syncModeCheckBox.isSelected(), "league", name, staminaSpinner.getValue(), numberOfMatchSpinner.getValue(),
                    foulCheckBox.isSelected(), offsideCheckBox.isSelected(), goldenGoalCheckBox.isSelected(), penaltyModeCheckBox.isSelected(), extraHalfTimeSpinnerValue/10, halfTimeSpinner.getValue()/10,teamList);

            System.out.println(Game.toJSON());


        }));



        backButton.setOnAction((event -> {

            Stage stage1 = (Stage) addButton.getScene().getWindow();
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
