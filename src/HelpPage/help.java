package HelpPage;

import javafx.scene.layout.Pane;

public class help {
    public static void  helpInitialize(Pane helpPane, Pane homePane){
        helpPane.setVisible(true);
        helpPane.toFront();
        homePane.setVisible(false);

    }
}
