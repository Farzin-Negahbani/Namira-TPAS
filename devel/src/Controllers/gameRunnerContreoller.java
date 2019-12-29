package Controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Classes.Game;
import Classes.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class gameRunnerContreoller {

    public Timer timer = new Timer();

    public String temp;

    public Thread thread;

    public Integer gamesdone = 0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML
    private ObservableList<String> data = FXCollections.observableArrayList();

    @FXML // fx:id="playedGamesListView"
    private ListView<String> playedGamesListView = new ListView<>(); // Value injected by FXMLLoader

    @FXML // fx:id="progressBar"
    private ProgressBar progressBar; // Value injected by FXMLLoader

    @FXML // fx:id="stopButton"
    private Button stopButton; // Value injected by FXMLLoader

    @FXML // fx:id="currentMatchLabel"
    private Label currentMatchLabel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert playedGamesListView != null : "fx:id=\"playedGamesListView\" was not injected: check your FXML file 'gameRunner.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'gameRunner.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'gameRunner.fxml'.";
        assert currentMatchLabel != null : "fx:id=\"currentMatchLabel\" was not injected: check your FXML file 'gameRunner.fxml'.";

        playedGamesListView.setItems(data);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runserver("127.0.0.85", 6969);

        }


        public void runserver(String serverName, int port){

            System.out.println("Male main");
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        System.out.println("Connecting to " + serverName + " on port " + port);
                        Socket client = new Socket(serverName, port);

                        System.out.println("Just connected to " + client.getRemoteSocketAddress());
                        OutputStream outToServer = client.getOutputStream();
                        DataOutputStream out = new DataOutputStream(outToServer);

                        //out.writeUTF("Hello from Farzin" + client.getLocalSocketAddress()+'\n');
                        out.writeUTF(Game.toJSON().toJSONString()+'\n');
                        System.out.println("after Send");
                        out.flush();

                        BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream())); // Bufferreader from socket
                        //DataInputStream in = new DataInputStream(client.getInputStream());

                        System.out.println("Before while");

                        while (true) {
                            System.out.println("While");
                            temp = in.readLine();
                            System.out.println("Server says " + temp);

                            if (temp.contains("ENDOFGAME")) {

                                break;
                            }
                            else {
                                gamesdone += 1;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Add to game list");
                                        progressBar.setProgress(gamesdone * 1.0 / Game.matchNumber);
                                        data.add(temp);
                                    }
                                });
                            }
                        }
                        client.close();
                        System.out.println("Completed");
                        //////
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage stage1 = (Stage) stopButton.getScene().getWindow();
                                stage1.close();
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
                            }
                        });

                    } catch (Exception e) {//Catch exception if any
                        System.err.println("Error: " + e.getMessage());
                    }
                }

            });
            thread.start();

    }





}
