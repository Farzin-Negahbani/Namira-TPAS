package Controllers;

import Classes.Main;
import Classes.StaticThings;
import Classes.Tuple;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Classes.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class cupController {

    public static int teamNumber = 0;

    public int index;

    @FXML
    private MenuBar menuBar;

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
    private Spinner<Integer> extraHalfTimeSpinner ;

    @FXML
    private Spinner<Integer> staminaSpinner ;

    @FXML
    private CheckBox penaltyModeCheckBox;

    @FXML
    private CheckBox goldenGoalCheckBox;

    @FXML
    private CheckBox foulCheckBox ;

    @FXML
    private Spinner<Integer> halfTimeSpinner ;

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
    private Spinner<Integer> numberOfTeamsSpinner;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    @FXML
    private  ObservableList<Tuple> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Tuple> cupTableView = new TableView<>();

    @FXML
    private TableColumn<Tuple, Integer> teamNumberColumn;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> teamNameColumn;

    @FXML
    private TableColumn<Tuple, SimpleStringProperty> binaryLocationColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox monitorCheckBox;
    @FXML
    private CheckBox syncModeCheckBox;
    @FXML
    void initialize() {

        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'Cup.fxml'.";
        assert saveMenuItem != null : "fx:id=\"saveMenuItem\" was not injected: check your FXML file 'Cup.fxml'.";
        assert loadMenuItem != null : "fx:id=\"loadMenuItem\" was not injected: check your FXML file 'Cup.fxml'.";
        assert exitMenuItem != null : "fx:id=\"exitMenuItem\" was not injected: check your FXML file 'Cup.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'Cup.fxml'.";
        assert extraHalfTimeCheckBox != null : "fx:id=\"extraHalfTimeCheckBox\" was not injected: check your FXML file 'Cup.fxml'.";
        assert extraHalfTimeSpinner != null : "fx:id=\"extraHalfTimeSpinner\" was not injected: check your FXML file 'Cup.fxml'.";
        assert staminaSpinner != null : "fx:id=\"staminaSpinner\" was not injected: check your FXML file 'Cup.fxml'.";
        assert penaltyModeCheckBox != null : "fx:id=\"penaltyModeCheckBox\" was not injected: check your FXML file 'Cup.fxml'.";
        assert goldenGoalCheckBox != null : "fx:id=\"goldenGoalCheckBox\" was not injected: check your FXML file 'Cup.fxml'.";
        assert foulCheckBox != null : "fx:id=\"foulCheckBox\" was not injected: check your FXML file 'Cup.fxml'.";
        assert halfTimeSpinner != null : "fx:id=\"halfTimeSpinner\" was not injected: check your FXML file 'Cup.fxml'.";
        assert numberOfMatchSpinner != null : "fx:id=\"numberOfMatchSpinner\" was not injected: check your FXML file 'Cup.fxml'.";
        assert offsideCheckBox != null : "fx:id=\"offsideCheckBox\" was not injected: check your FXML file 'Cup.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'Cup.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'Cup.fxml'.";
        assert shuffleButton != null : "fx:id=\"shuffleButton\" was not injected: check your FXML file 'Cup.fxml'.";
        assert numberOfTeamsSpinner != null : "fx:id=\"numberOfTeamsSpinner\" was not injected: check your FXML file 'Cup.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'Cup.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'Cup.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Cup.fxml'.";
        assert cupTableView != null : "fx:id=\"cupTableView\" was not injected: check your FXML file 'Cup.fxml'.";
        assert teamNumberColumn != null : "fx:id=\"teamNumberColumn\" was not injected: check your FXML file 'Cup.fxml'.";
        assert teamNameColumn != null : "fx:id=\"teamNameColumn\" was not injected: check your FXML file 'Cup.fxml'.";
        assert binaryLocationColumn != null : "fx:id=\"binaryLocationColumn\" was not injected: check your FXML file 'Cup.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'Cup.fxml'.";



        //data.add(new Tuple("HELIOS2016","F:\\PSS\\HELIOS2016.sh", 8, 1));
        //data.add(new Tuple("ITAndroids2016","F:\\PSS\\ITAndroids2016.sh" , 8, 2));
        //data.add(new Tuple("Ziziphus2016","F:\\PSS\\Ziziphus2016.sh" , 8, 3));
        //data.add(new Tuple("Shiraz2016","F:\\PSS\\Shiraz2016.sh" , 8, 4));
        //data.add(new Tuple("OXSY2016","F:\\PSS\\OXSY2016.sh" , 8, 5));
        //data.add(new Tuple("MT2016","F:\\PSS\\MT2016.sh" , 8,  6));
        //data.add(new Tuple("Sirus2016","F:\\PSS\\OXSY2016.sh" , 8, 5));
        //data.add(new Tuple("MT2016","F:\\PSS\\MT2016.sh" , 8,  6));


        cupTableView.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = cupTableView.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                index = ((TablePosition) selectedCells.get(0)).getRow();
            }
        });

        teamNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));
        binaryLocationColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamBinaryLoc"));

        teamNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamNumber"));

        cupTableView.setItems(data);

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
                        new FileChooser.ExtensionFilter("Start Files", "*.sh"));
                File file = fileChooser.showOpenDialog(StaticThings.stage);
                boolean flag = true;
                for (Tuple t:data) {
                    if( file.getPath().equals(t.getTeamBinaryLoc()) ){
                        flag = false;
                        statusLabel.setText("This Binary is Already choosed!");
                        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ae -> { statusLabel.setText(""); }));
                        timeline.play();
                    }
                }
                if(file !=null && flag){
                    data.add(new Tuple(Main.myGet(file), file.getPath(), 8, ++ teamNumber));
                }
            }
            else{
                statusLabel.setText("Cannot Add more than Capacity!");
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ae -> { statusLabel.setText(""); }));
                timeline.play();
            }
        });

        exitMenuItem.setOnAction((event -> {
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
        }));

        startButton.setOnAction((event -> {
            Integer extraHalfTimeSpinnerValue = 0;
            ArrayList<String> teamList = new ArrayList<>();

            for(Tuple s: data) {
                teamList.add(s.getTeamBinaryLoc().toString());
            }

            if(extraHalfTimeCheckBox.isSelected()){
                extraHalfTimeSpinnerValue  = extraHalfTimeSpinner.getValue();
            }

            String name = "Namira CUP";
            if (!nameTextField.getText().equals("")){
                name = nameTextField.getText();
            }

            Game.set_Game_Settings(numberOfMatchSpinner.getValue()*data.size(),monitorCheckBox.isSelected(), syncModeCheckBox.isSelected(), "cup", name, staminaSpinner.getValue(), numberOfMatchSpinner.getValue(),
                    foulCheckBox.isSelected(), offsideCheckBox.isSelected(), goldenGoalCheckBox.isSelected(), penaltyModeCheckBox.isSelected(), extraHalfTimeSpinnerValue/10, halfTimeSpinner.getValue()/10,teamList);

            Stage stage1 = (Stage) addButton.getScene().getWindow();

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

            Stage stage1 = (Stage) addButton.getScene().getWindow();

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
