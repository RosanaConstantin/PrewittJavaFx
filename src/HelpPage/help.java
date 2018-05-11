package HelpPage;

import javafx.scene.layout.Pane;

public class help {
    public void  helpInitialize(Pane helpPane, Pane homePane){
        helpPane.setVisible(true);
        helpPane.toFront();
        homePane.setVisible(false);

    }
}
