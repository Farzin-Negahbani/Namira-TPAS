package Controllers;

/**
 * Created by Farzin.negahbani on 5/26/2016.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class firstController {

    @FXML
    private Label label1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane firstAnchorPane;

    @FXML
    private Button worldCupButton;

    @FXML
    private Button leagueButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button cupButton;

    @FXML
    private Button tournamentButton;

    @FXML
    private Button friendlyMatchButton;

    @FXML
    void initialize() {
        assert firstAnchorPane != null : "fx:id=\"firstAnchorPane\" was not injected: check your FXML file 'First.fxml'.";
        assert worldCupButton != null : "fx:id=\"worldCupButton\" was not injected: check your FXML file 'First.fxml'.";
        assert leagueButton != null : "fx:id=\"leagueButton\" was not injected: check your FXML file 'First.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'First.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'First.fxml'.";
        assert cupButton != null : "fx:id=\"cupButton\" was not injected: check your FXML file 'First.fxml'.";
        assert tournamentButton != null : "fx:id=\"tournamentButton\" was not injected: check your FXML file 'First.fxml'.";
        assert friendlyMatchButton != null : "fx:id=\"friendlyMatchButton\" was not injected: check your FXML file 'First.fxml'.";

        cupButton.setOnAction((event -> {
            Stage stage1 = (Stage) cupButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/Cup.fxml"));
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


        worldCupButton.setOnAction((event -> {
            Stage stage1 = (Stage) cupButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/WorldCup.fxml"));
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

        tournamentButton.setOnAction((event -> {
            Stage stage1 = (Stage) leagueButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/Tournament.fxml"));
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

        friendlyMatchButton.setOnAction((event -> {
            Stage stage1 = (Stage) leagueButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/FriendlyMatch.fxml"));
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

        leagueButton.setOnAction((event -> {
            Stage stage1 = (Stage) leagueButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/League.fxml"));
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

        settingsButton.setOnAction((event -> {
            Stage stage1 = (Stage) leagueButton.getScene().getWindow();
            stage1.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/Settings.fxml"));
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

        exitButton.setOnAction((event -> {
            Stage stage1 = (Stage) cupButton.getScene().getWindow();
            stage1.close();
        }));


    }
}
