/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author doyin
 */
public class StockFinder extends Application {

    public static ApplicationContext ac;

    public static void main(String[] args) {
        Application.launch(StockFinder.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "dataAccessContext-local.xml"});
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("个人专用软件");
        Scene sc = new Scene(root);

        sc.getStylesheets().add(StockFinder.class.getResource("/com/sai/javafx/calendar/styles/calendar_styles.css").toExternalForm());
        stage.setScene(sc);

        stage.show();


    }
}
