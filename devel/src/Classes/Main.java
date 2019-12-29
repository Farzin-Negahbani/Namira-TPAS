package Classes; /**
 * Created by Farzin.negahbani on 5/26/2016.
 */



import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/First.fxml"));
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML/ResultPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("NAMIRA TPAS");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Application.launch(args);
    }

    public static String myGet(File start){
        String res = null, temp;
        try {
            Scanner scanner = new Scanner(start);
            while (scanner.hasNext()) {
                temp = scanner.nextLine();
                if (temp.contains("teamname=")){
                    res = temp.split("\"")[1];
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(res == null){
            try {
                String s   = start.getPath();
                s = s.replace(s.split("/")[s.split("/").length-1],"");
                s = s.concat("start");
                File file = new File(s);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    temp = scanner.nextLine();
                    if (temp.contains("teamname=")){
                        res = temp.split("\"")[1];
                        break;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
        return res;
    }


}