package ProcessPage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import utils.PrewittHV;
import utils.PrewittHorizontal;
import utils.PrewittVertical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class process {

    public static class Details {
        public Details(String latime, String inaltime, String dimensiune) {
            this.latime = new SimpleStringProperty(latime);
            this.inaltime = new SimpleStringProperty(inaltime);
            this.dimensiune = new SimpleStringProperty(dimensiune);
        }

        private StringProperty latime;
        public void setLatime(String value) { latimeProperty().set(value); }
        public String getLatime() { return latimeProperty().get(); }
        public StringProperty latimeProperty() {
            return latime;
        }

        private StringProperty inaltime;
        public void setInaltime(String value) { inaltimeProperty().set(value); }
        public String getInaltime() { return inaltimeProperty().get(); }
        public StringProperty inaltimeProperty() {
            return inaltime;
        }

        private StringProperty dimensiune;
        public void setdimensiune(String value) { dimensiuneProperty().set(value); }
        public String getdimensiune() { return dimensiuneProperty().get(); }
        public StringProperty dimensiuneProperty() {
            return dimensiune;
        }
    }

    public static void  processInitialize(Pane parentPane, Pane homePane, Pane helpPane){
        parentPane.toFront();
        homePane.setVisible(false);
        helpPane.setVisible(false);
    }

    public void loadImage(Pane parentPane, ImageView imgOrg, ChoiceBox chooseBox, ProgressIndicator progressInd, Button processButton, TableView table, TableView tableModif) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecteaza imaginea");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("BMP files", "*.bmp"));
        File selectedImage = fileChooser.showOpenDialog(parentPane.getScene().getWindow());
        String originalPath = selectedImage.getPath();
        String imageName = selectedImage.getName();
        try {
            imgOrg.setImage(new Image( new FileInputStream(originalPath)));
            if(chooseBox.getItems().isEmpty()) {
                chooseBox.getItems().addAll("Vertical", "Horizontal", "Hor&Vert");
            }
            progressInd.setVisible(true);
            processButton.setVisible(true);
            progressInd.setProgress(Double.valueOf(0));
            getImageDetails(table, tableModif, imgOrg.getImage(), originalPath, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getImageDetails(TableView table, TableView tableModif, Image image, String path, boolean original) {
        String width = String.valueOf(image.getWidth());
        String height = String.valueOf(image.getHeight());
        double size = new Double(new File(path).length()) / 1024 / 1024;
        DecimalFormat df = new DecimalFormat("0.00");
        String dimensiune = String.valueOf(df.format(size));

        Details details1 = new Details(width, height, dimensiune);
        ObservableList<Details> details = FXCollections.observableArrayList();
        details.add(details1);

//        if(original) {
//            table.getColumns();
//            width.setCellValueFactory(new PropertyValueFactory<Details, String>("latime"));
//            height.setCellValueFactory(new PropertyValueFactory<Details, String>("inaltime"));
//            size.setCellValueFactory(new PropertyValueFactory<Details, String>("dimensiune"));
//            table.setItems(details);
//        } else {
//            widthModif.setCellValueFactory(new PropertyValueFactory<Details, String>("latime"));
//            heightModif.setCellValueFactory(new PropertyValueFactory<Details, String>("inaltime"));
//            sizeModif.setCellValueFactory(new PropertyValueFactory<Details, String>("dimensiune"));
//            tableModif.setItems(details);
//        }
        return;
    }

    public String getOrientation(ChoiceBox chooseBox) {
        String output =  chooseBox.getSelectionModel().getSelectedItem().toString().toLowerCase();
        return output;
    }

    public void saveToFile(Pane parentPane, ImageView img, String imageName, TableView table,  TableView tableModif) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Alegeti directorul");
        File selectedDirectory = directoryChooser.showDialog(parentPane.getScene().getWindow());
        String path = selectedDirectory.getPath() + "/" + imageName.substring(0, imageName.length() - 4) + ".bmp";
        File outputFile = new File(path);
        BufferedImage bImage = SwingFXUtils.fromFXImage(img.getImage(), null);
        try {
            ImageIO.write(bImage, "png", outputFile);
            getImageDetails(table, tableModif, img.getImage(), path, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processImage(Pane parentPane, ImageView imgOrg, ImageView img,ProgressIndicator progressInd, String imageName, TableView table, TableView tableModif, ChoiceBox chooseBox) {
        String orientation = getOrientation(chooseBox);
        long startTime = System.nanoTime();
        if(orientation.equals("vertical")){
            Image resultImage = PrewittVertical.PrewittVertical(imgOrg.getImage(), progressInd);
            img.setImage(resultImage);
            progressInd.setProgress(Double.valueOf(100));
        } else if (orientation.equals("horizontal")){
            Image resultImage = PrewittHorizontal.PrewittHorizontal(imgOrg.getImage(), progressInd);
            img.setImage(resultImage);
            progressInd.setProgress(Double.valueOf(100));
        } else if (orientation.equals("hor&vert")){
            Image resultImage = PrewittHV.PrewittHV( imgOrg.getImage(), progressInd);
            img.setImage(resultImage);
            progressInd.setProgress(Double.valueOf(100));
        }

        long elapsed = (System.nanoTime() - startTime); //timpul in nanosecunde
        System.out.println(elapsed);
        saveToFile(parentPane, img, imageName, table, tableModif);
        return;
    }

    public void changePhoto(ToggleButton toggle, Label imgOrgLabel, Label imgLabel, ImageView imgOrg, ImageView img, TableView table, TableView tableModif, Checkbox checkInfo ){

        boolean isChecked = checkInfo.getState();
        if(toggle.selectedProperty().getValue()) {
            imgOrgLabel.setVisible(false);
            imgOrg.setVisible(false);
            img.setVisible(true);
            imgLabel.setVisible(true);

            if(isChecked){
                tableModif.setVisible(true);
                table.setVisible(false);
            } else {
                tableModif.setVisible(false);
                table.setVisible(false);
            }
        } else {
            imgOrgLabel.setVisible(true);
            imgOrg.setVisible(true);
            img.setVisible(false);
            imgLabel.setVisible(false);

            if(isChecked){
                tableModif.setVisible(false);
                table.setVisible(true);
            } else {
                tableModif.setVisible(false);
                table.setVisible(false);
            }
        }
    }

    public void showInfo(Checkbox checkInfo, ToggleButton toggle, TableView table, TableView tableModif, TextField info) {
        boolean value = checkInfo.getState();
        if(value){
            info.setVisible(true);
            if(toggle.selectedProperty().getValue()) {
                tableModif.setVisible(true);
            } else {
                table.setVisible(true);
            }
        } else {
            info.setVisible(false);
            table.setVisible(false);
            tableModif.setVisible(false);
        }
        return;
    }
}
