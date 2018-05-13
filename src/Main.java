import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

/**
 * Created by Rosana-Constantin on 11-May-18.
 */
public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    public void openWebpage(String urlString) throws Exception {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        URI url = new URI(urlString);
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane parentPane = new Pane();
        parentPane.setPrefSize(1200, 740.0);

        TabPane menu = new TabPane();

        menu.setTabMaxWidth(100.0);
        menu.setTabMinWidth(100.0);
        menu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        menu.setId("menuBar");
        menu.setSide(Side.TOP);
        menu.setLayoutX(190.2);
        menu.setLayoutY(21.0);
        menu.prefHeight(32.0);
        menu.prefWidth(242.0);

        final Tab home = new Tab("Acasa");
        final Tab processing = new Tab("Procesare");
        final Tab help = new Tab("Ajutor");

        menu.getTabs().addAll(home, processing, help);

        Pane homePane = new Pane();
       // homePane.setPrefSize(1100, 600);
        homePane.setLayoutX(50.0);
        homePane.setLayoutY(70.0);
        homePane.setVisible(false);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText("\n" +
                "Operatorul Prewitt este utilizat în procesarea imaginilor, în special în cadrul algoritmilor de detectare a marginilor. Din punct de vedere tehnic, este un operator de diferențiere discret, calculând o aproximare a gradientului funcției de intensitate a imaginii.\n" +
                " În fiecare punct al imaginii, rezultatul operatorului Prewitt este fie vectorul gradient corespunzător, fie norma acestui vector. Operatorul Prewitt se bazează pe conveierea imaginii cu un filtru mic, separabil și cu valoare integrată în direcții orizontale și verticale și este, prin urmare, relativ ieftin în ceea ce privește calculele precum operatorii Sobel și Kayyali. Pe de altă parte, aproximarea gradientului pe care o produce este relativ brută, în special pentru variațiile de înaltă frecvență din imagine. \n" +
                "\n" +
                "Operatorul Prewitt a fost dezvoltat de Judith M. S. Prewitt. În termeni simpli, operatorul calculează gradientul intensității imaginii în fiecare punct, dând direcția celei mai mari creșteri posibile din lumină spre întuneric și rata de schimbare în acea direcție. Rezultatul, prin urmare, arată modul în care imaginea \"brusc\" sau \"fără probleme\" se schimbă la acel moment și, prin urmare, cât de probabil este ca o parte a imaginii să reprezinte o margine, precum și modul în care marginea respectivă este probabil orientată.\n" +
                "\n" +
                " În practică, calculul amplitudinii (probabilitatea unei margini) este mai fiabil și mai ușor de interpretat decât calculul direcției. În mod punctual, gradientul unei funcții cu două variabile (aici funcția de intensitate a imaginii) este la fiecare punct de imagine un vector 2D cu componentele date de derivați în direcțiile orizontale și verticale. La fiecare punct de imagine, vectorul de gradient indică în direcția celei mai mari creșteri a intensității posibile, iar lungimea vectorului de gradient corespunde ratei de schimbare în direcția respectivă. Aceasta implică faptul că rezultatul operatorului Prewitt la un punct de imagine care se află într-o regiune cu intensitate constantă a imaginii este un vector zero și la un punct pe o margine este un vector care indică peste margine, de la valori mai întunecate la cele mai strălucitoare.");

        textArea.setLayoutX(20.0);
        textArea.setLayoutY(11.0);
        textArea.prefHeight(590.0);
        textArea.prefWidth(1054.0);
        textArea.setWrapText(true);
        textArea.setFont(javafx.scene.text.Font.font("Arial Italic"));
        textArea.setFont(javafx.scene.text.Font.font(16.0));


        ImageView imageView = new ImageView("./utils/images/seeMore.png");
        imageView.setId("homeIcon");
        imageView.setFitHeight(105);
        imageView.setFitWidth(172);

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText("https://en.wikipedia.org/wiki/Prewitt_operator");
        hyperlink.setAlignment(Pos.CENTER);
        hyperlink.setLayoutX(372.0);
        hyperlink.setLayoutY(500.0);
        hyperlink.prefHeight(29.0);
        hyperlink.prefWidth(430.0);

        hyperlink.setOnAction(e -> {
            try {
                openWebpage( "https://en.wikipedia.org/wiki/Prewitt_operator");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Label label =  new Label();
        label.setText("Vezi mai multe:");
        label.setLayoutX(260.0);
        label.setLayoutY(505.0);

        homePane.getChildren().addAll(textArea, hyperlink, imageView, label);

        Pane helpPane = new Pane();
        helpPane.prefHeight( 600);
        helpPane.prefWidth( 1100);
        helpPane.setVisible(false);
        helpPane.setLayoutX(50.0);
        helpPane.setLayoutY(70.0);

        TextArea textAreaHelp = new TextArea();
        textAreaHelp.setEditable(false);
        textAreaHelp.setText("\n"
                +"Prewitt Editor v7.1.0 \n \n"+
        "Autor: Rosana Constantin\n"+
        "Proiect:  https://github.com/RosanaConstantin/imageProcessing\n\n\n"+
        "Acest program este software liber; îl puteți redistribui și / sau modifica în conformitate cu termenii Licenței Publice Generale GNU, publicată de Fundația pentru Software Liber; fie versiunea 2 a Licenței, fie (la alegere) orice versiune ulterioară.\n"+
        "Acest program este distribuit în speranța că va fi util, dar FĂRĂ NICI O GARANȚIE; fără nici măcar garanția implicită de VANDABILITATE sau de UTILIZAREA PENTRU UN SCOP SPECIC. Pentru mai multe detalii, consultați Licența publică generală GNU.\n\n");
        textAreaHelp.setLayoutX(29.0);
        textAreaHelp.setLayoutY(11.0);
        textAreaHelp.prefWidth(1054.0);
        textAreaHelp.prefHeight(590.0);

        textAreaHelp.setWrapText(true);
        textAreaHelp.setFont(javafx.scene.text.Font.font("Arial Narrow Italic"));
        textAreaHelp.setFont(javafx.scene.text.Font.font(17.0));


        ImageView imageViewHelp = new ImageView("./utils/images/matrix.png");
        imageViewHelp.setId("helpIcon");
        imageViewHelp.setFitHeight(150);
        imageViewHelp.setFitWidth(200);
        imageViewHelp.setLayoutX(400.0);
        imageViewHelp.setLayoutY(350.0);

        helpPane.getChildren().addAll(textAreaHelp, imageViewHelp);

        Hyperlink hyperlinkGit = new Hyperlink();
        hyperlinkGit.setText("https://github.com/RosanaConstantin/PrewittJavaFx.git");
        hyperlinkGit.setLayoutX(540.0);
        hyperlinkGit.setLayoutY(680.0);
        hyperlinkGit.setPrefHeight(29.0);
        hyperlinkGit.setPrefWidth(430.0);


        hyperlinkGit.setOnAction(e -> {
            try {
                openWebpage( "https://github.com/RosanaConstantin/PrewittJavaFx.git");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Label labelGit =  new Label();
        labelGit.setId("textGit");
        labelGit.setText("Gaseste proiectul pe Git, la adresa:");
        labelGit.setLayoutX(291.0);
        labelGit.setLayoutY(684.0);

        //ChoiceBox
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Vertical", "Horizontal", "Vert&Horiz")
        );
        cb.setTooltip(new Tooltip("Selecteaza orientarea de procesare"));
        cb.setLayoutX(921.0);
        cb.setLayoutY(99.0);
        cb.prefWidth(150.0);


        //Button load
        Button loadButton = new Button();
        loadButton.setText("Incarca imaginea");
        loadButton.setLayoutX(233.0);
        loadButton.setLayoutY(99.0);
        loadButton.prefHeight(27.0);
        loadButton.prefWidth(172.0);

        //Button process
        Button processButton = new Button();
        processButton.setText("Proceseaza imaginea");
        processButton.setLayoutX(794.0);
        processButton.setLayoutY(160.0);
        processButton.prefHeight(31.0);
        processButton.prefWidth(181.0);

        //DatePicker
        DatePicker dateHolder = new DatePicker();
        dateHolder.setEditable(false);
        dateHolder.setLayoutX(715.0);
        dateHolder.setLayoutY(23.0);
        dateHolder.prefHeight(27.0);
        dateHolder.prefWidth(260.0);

        //homeIcon
        ImageView homeIcon = new ImageView();
        homeIcon.setLayoutX(100.0);
        homeIcon.setLayoutY(460.0);
        homeIcon.setFitWidth(172.0);
        homeIcon.setFitHeight(105.0);

        //helpIcon
        ImageView helpIcon = new ImageView();

        //Label
        Label typeLabel = new Label();
        typeLabel.setText("Alege tipul de transformare:");
        typeLabel.setLayoutX(677.0);
        typeLabel.setLayoutY(101.0);
        typeLabel.prefHeight(27.0);
        typeLabel.prefWidth(188.0);

        //CheckBox

        CheckBox checkInfo = new CheckBox();
        checkInfo.setText("      Afiseaza informatiile despre imagine" );
        checkInfo.setLayoutX(737.0);
        checkInfo.setLayoutY(371.0);

        //img
        ImageView img = new ImageView();
        img.setFitWidth(500.0);
        img.setFitHeight(400.0);
        img.setLayoutX(100.0);
        img.setLayoutY(160.0);
        img.pickOnBoundsProperty();
        img.preserveRatioProperty();
        img.setVisible(false);


        //imgOrg
        ImageView imgOrg = new ImageView();
        imgOrg.setFitWidth(500.0);
        imgOrg.setFitHeight(400.0);
        imgOrg.setLayoutX(100.0);
        imgOrg.setLayoutY(160.0);
        imgOrg.pickOnBoundsProperty();
        imgOrg.preserveRatioProperty();
        imgOrg.setVisible(false);

        //Progress Indicator
        ProgressIndicator progressInd = new ProgressIndicator();
        progressInd.setLayoutX(849.0);
        progressInd.setLayoutY(286.0);
        progressInd.prefHeight(55.0);
        progressInd.prefWidth(66.0);
        progressInd.setVisible(false);
        progressInd.setProgress(0.0);

        //textField
        TextField textInfo = new TextField();
        textInfo.setText("Informatii imagine");
        textInfo.setLayoutX(808.0);
        textInfo.setLayoutY(467.0);
        textInfo.prefHeight(27.0);
        textInfo.prefWidth(172.0);
        textInfo.setVisible(false);

        //Label img
        Label labelImg = new Label();
        labelImg.setText("Imaginea procesata");
        labelImg.setLayoutX(78.0);
        labelImg.setLayoutY(635.0);
        labelImg.prefHeight(21.0);
        labelImg.prefWidth(142.0);
        labelImg.setVisible(false);

        //Label imgOrg
        Label labelImgOrg = new Label();
        labelImgOrg.setText("Imaginea originala");
        labelImgOrg.setLayoutX(121.0);
        labelImgOrg.setLayoutY(635.0);
        labelImgOrg.prefHeight(21.0);
        labelImgOrg.prefWidth(142.0);


        //Toggle button
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setText("Afisare imaginea procesata");
        toggleButton.setLayoutX(285.0);
        toggleButton.setLayoutY(632.0);
        toggleButton.prefHeight(31.0);
        toggleButton.prefWidth(260.0);


        //Tabel
        TableView table = new TableView();
        table.setEditable(false);
        table.setFixedCellSize(30.0);
        table.setLayoutX(677.0);
        table.setLayoutY(515.0);
        table.prefHeight(151.0);
        table.prefWidth(430.0);
        table.setVisible(false);

        TableColumn width = new TableColumn("Latime");
        width.setPrefWidth(75.0);
        TableColumn height = new TableColumn("Inaltime");
        height.setPrefWidth(75.0);
        TableColumn size = new TableColumn("Dimensiune");
        size.setPrefWidth(75.0);
        table.getColumns().addAll(width, height, size);

        TableView tableModif = new TableView();
        tableModif.setEditable(false);
        tableModif.setFixedCellSize(30.0);
        tableModif.setLayoutX(677.0);
        tableModif.setLayoutY(515.0);
        tableModif.prefHeight(151.0);
        tableModif.prefWidth(430.0);
        tableModif.setVisible(false);

        TableColumn widthModif = new TableColumn("Latime");
        widthModif.setPrefWidth(75.0);
        TableColumn heightModif = new TableColumn("Inaltime");
        heightModif.setPrefWidth(75.0);
        TableColumn sizeModif = new TableColumn("Dimensiune");
        sizeModif.setPrefWidth(75.0);
        tableModif.getColumns().addAll(widthModif, heightModif, sizeModif);

        //Actions buttons
        loadButton.setOnAction(e -> {
            try {
                ProcessPage.process.loadImage(parentPane, imgOrg, cb, progressInd, processButton, table, tableModif);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        processButton.setOnAction(e -> {
            try {
                ProcessPage.process.processImage(parentPane, imgOrg, img, progressInd, table, tableModif, cb);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Toggle button Action
        toggleButton.setOnAction(e-> {
            ProcessPage.process.changePhoto(toggleButton, labelImgOrg, labelImg, imgOrg, img, table, tableModif, checkInfo);
        });

        //CheckBox action
        checkInfo.setOnAction(e-> {
            ProcessPage.process.showInfo(checkInfo, toggleButton, table, tableModif, textInfo);
        });

        //Tab actions
        home.setOnSelectionChanged(e-> {
            if(home.isSelected())
                HomePage.home.homeInitialize(homePane, helpPane);

        });

        help.setOnSelectionChanged(e-> {
            if (help.isSelected())
                HelpPage.help.helpInitialize(helpPane, homePane);

        });

        processing.setOnSelectionChanged(e-> {
            if (processing.isSelected())
                ProcessPage.process.processInitialize(parentPane, homePane, helpPane);
        });


        parentPane.getChildren().addAll(homePane, helpPane, menu, table, tableModif, labelGit, hyperlinkGit, cb, dateHolder, toggleButton, loadButton, processButton, typeLabel, checkInfo, img, imgOrg, progressInd, textInfo, labelImg, labelImgOrg);
        //Creare scena

        Scene scene = new Scene(parentPane,1200, 740);
        primaryStage.setTitle("Procesare imagine");
        primaryStage.setScene(scene);
        Initialize.initialize(dateHolder, homePane, helpPane, homeIcon, helpIcon);
        primaryStage.show();
        return;
    }
}