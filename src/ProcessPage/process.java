package ProcessPage;

import javafx.scene.layout.Pane;

public class process {

    public void  processInitialize(Pane parentPane, Pane homePane, Pane helpPane){
        parentPane.toFront();
        homePane.setVisible(false);
        helpPane.setVisible(false);
    }
}
