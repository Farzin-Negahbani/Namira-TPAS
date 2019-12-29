package Controllers;
/**
 * Sample Skeleton for 'ResultPage.fxml' Controller Class
 */

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import Classes.Game;
import Classes.Main;
import Classes.Tuple;
import Classes.Tuple2;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class resultPageController {



    public Timer timer = new Timer();

    public String temp;

    public Thread thread;

    public Integer gamesdone = 0;


    @FXML
    private ObservableList<String> listViewData = FXCollections.observableArrayList();


    @FXML
    private ObservableList<Tuple2> tableData = FXCollections.observableArrayList();



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="resualtTable"
    private TableView<Tuple2> resualtTable; // Value injected by FXMLLoader

    @FXML // fx:id="resualtPositionColumn"
    private TableColumn<Tuple2, Integer> resualtPositionColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtTeamColumn"
    private TableColumn<Tuple2, SimpleStringProperty> resualtTeamColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtPlayedColumn"
    private TableColumn<Tuple2, Integer> resualtPlayedColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtWonColumn"
    private TableColumn<Tuple2, Integer> resualtWonColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtDrawnColumn"
    private TableColumn<Tuple2, Integer> resualtDrawnColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtLostColumn"
    private TableColumn<Tuple2, Integer> resualtLostColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtPointsColumn"
    private TableColumn<Tuple2, Integer> resualtPointsColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtGoalForColumn"
    private TableColumn<Tuple2, Integer> resualtGoalForColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtGoalAgainstColumn"
    private TableColumn<Tuple2, Integer> resualtGoalAgainstColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resualtGoalDifColumn"
    private TableColumn<Tuple2, Integer> resualtGoalDifColumn; // Value injected by FXMLLoader

    @FXML // fx:id="fixturesListView"
    private ListView<String> fixturesListView  = new ListView<>(); // Value injected by FXMLLoader

    @FXML // fx:id="progressBar"
    private ProgressBar progressBar; // Value injected by FXMLLoader

    @FXML // fx:id="AselectedButton"
    private Button AselectedButton; // Value injected by FXMLLoader

    @FXML // fx:id="currentMatchLabel"
    private Label currentMatchLabel; // Value injected by FXMLLoader

    @FXML // fx:id="AallButton"
    private Button AallButton; // Value injected by FXMLLoader

    @FXML // fx:id="completedPercantageLabel"
    private Label completedPercantageLabel; // Value injected by FXMLLoader

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resualtLostColumn != null : "fx:id=\"resualtLostColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtGoalDifColumn != null : "fx:id=\"resualtGoalDifColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert AselectedButton != null : "fx:id=\"AselectedButton\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtWonColumn != null : "fx:id=\"resualtWonColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert AallButton != null : "fx:id=\"AallButton\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtTeamColumn != null : "fx:id=\"resualtTeamColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtPointsColumn != null : "fx:id=\"resualtPointsColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert currentMatchLabel != null : "fx:id=\"currentMatchLabel\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert fixturesListView != null : "fx:id=\"fixturesListView\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtPlayedColumn != null : "fx:id=\"resualtPlayedColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtPositionColumn != null : "fx:id=\"resualtPositionColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtGoalForColumn != null : "fx:id=\"resualtGoalForColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtGoalAgainstColumn != null : "fx:id=\"resualtGoalAgainstColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtTable != null : "fx:id=\"resualtTable\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert resualtDrawnColumn != null : "fx:id=\"resualtDrawnColumn\" was not injected: check your FXML file 'ResultPage.fxml'.";
        assert completedPercantageLabel != null : "fx:id=\"completedPercantageLabel\" was not injected: check your FXML file 'ResultPage.fxml'.";

        resualtPositionColumn.setCellValueFactory(new PropertyValueFactory<>("Position"));
        resualtTeamColumn.setCellValueFactory(new PropertyValueFactory<>("Team"));
        resualtPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("Played"));
        resualtWonColumn.setCellValueFactory(new PropertyValueFactory<>("Won"));
        resualtDrawnColumn.setCellValueFactory(new PropertyValueFactory<>("Drawn"));
        resualtLostColumn.setCellValueFactory(new PropertyValueFactory<>("Lost"));
        resualtPointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));
        resualtGoalForColumn.setCellValueFactory(new PropertyValueFactory<>("GF"));
        resualtGoalAgainstColumn.setCellValueFactory(new PropertyValueFactory<>("GA"));
        resualtGoalDifColumn.setCellValueFactory(new PropertyValueFactory<>("GD"));

        fixturesListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        // change the label text value to the newly selected
                        // item.
                        System.out.println("You Selected " + newValue);
                        if(!newValue.equals("")){
                            try {

                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/matchFacts.fxml"));
                                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/ResultPage.fxml"));
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.setTitle("Analysed Results");
                                stage.setScene(new Scene(root1));
                                stage.show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

//        tableData.add(new Tuple2(1,"HELIOS2017",2,2,0,0,6,9,2,7));
//        tableData.add(new Tuple2(2,"MT2017",1,2,0,0,1,1,0,1));

        resualtTable.setItems(tableData);
        fixturesListView.setItems(listViewData);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runserver("127.0.0.85", 6969);
    }

    public void runserver(String serverName, int port){

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println("Connecting to " + serverName + " on port " + port);
                    Socket client = new Socket(serverName, port);

                    System.out.println("Just connected to " + client.getRemoteSocketAddress());
                    OutputStream outToServer = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);

                    out.writeUTF(Game.toJSON().toJSONString()+'\n');
                    System.out.println("Game JSON sent.");
                    out.flush();

                    BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream())); // Bufferreader from socket
                    //DataInputStream in = new DataInputStream(client.getInputStream());

                    System.out.println("Ready to receive...");

                    while (true) {
                        temp = in.readLine();
                        System.out.println("Server said: " + temp);

                        if (temp.contains("END_OF_THE_GAME")) {
                            System.out.println("Completed");
                            break;
                        }
                        else if(temp.contains("current")){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    currentMatchLabel.setText(temp.substring(8));
                                }
                            });
                        }
                        else{
                            gamesdone += 1;
                            String Stemp = temp;

                            ///

                            //JSONParser parser = new JSONParser();
                            //JSONObject json = (JSONObject) parser.parse(temp);
                            //System.out.println(json);
                            //System.out.println(json.get("game"));
                            //String Stemp = json.get("game").toString();
                            ///
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    listViewData.add(Stemp);
                                    progressBar.setProgress(gamesdone * 1.0 / Game.totalMatches);
                                    completedPercantageLabel.setText(Integer.toString(gamesdone * 100/ Game.totalMatches).concat("%"));
                                }
                            });
                        }
                    }
                    client.close();

                    AallButton.setDisable(false);
                    AselectedButton.setDisable(false);

                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
            }

        });
        thread.start();

    }
}
