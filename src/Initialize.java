import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Initialize {

    public static void initialize(DatePicker dateHolder, Pane homePane, Pane helpPane, ImageView homeIcon, ImageView helpIcon){
        dateHolder.setValue(LocalDate.now());
        homePane.setVisible(true);
        try {
            helpIcon.setImage(new Image( new FileInputStream(System.getProperty("user.dir") + "/PrewittJava/src/utils/images/matrix.png")));
            homeIcon.setImage(new Image( new FileInputStream(System.getProperty("user.dir") + "/PrewittJava/src/utils/images/seeMore.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
