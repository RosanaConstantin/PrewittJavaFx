package HomePage;

import javafx.scene.layout.Pane;

public class home {

    public static void  homeInitialize(Pane homePane, Pane helpPane){
        homePane.setVisible(true);
        homePane.toFront();
        helpPane.setVisible(false);
        return;
    }
}
