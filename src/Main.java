import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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

        final Tab home = new Tab("Acasa");
        final Tab processing = new Tab("Procesare");
        processing.setId("processItem");
        final Tab help = new Tab("Ajutor");
        help.setId("helpItem");

        menu.getTabs().addAll(home, processing, help);

        ScrollBar sc = new ScrollBar();
        sc.setMin(0);
        sc.setMax(1000.0);
        sc.setValue(0);

        Pane homePane = new Pane();
        homePane.setId("homePane");
        homePane.setPrefSize(1100, 600);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText("\n" +
                "Operatorul Prewitt este utilizat în procesarea imaginilor, în special în cadrul algoritmilor de detectare a marginilor. Din punct de vedere tehnic, este un operator de diferențiere discret, calculând o aproximare a gradientului funcției de intensitate a imaginii.\n" +
                " În fiecare punct al imaginii, rezultatul operatorului Prewitt este fie vectorul gradient corespunzător, fie norma acestui vector. Operatorul Prewitt se bazează pe conveierea imaginii cu un filtru mic, separabil și cu valoare integrată în direcții orizontale și verticale și este, prin urmare, relativ ieftin în ceea ce privește calculele precum operatorii Sobel și Kayyali. Pe de altă parte, aproximarea gradientului pe care o produce este relativ brută, în special pentru variațiile de înaltă frecvență din imagine. \n" +
                "\n" +
                "Operatorul Prewitt a fost dezvoltat de Judith M. S. Prewitt. În termeni simpli, operatorul calculează gradientul intensității imaginii în fiecare punct, dând direcția celei mai mari creșteri posibile din lumină spre întuneric și rata de schimbare în acea direcție. Rezultatul, prin urmare, arată modul în care imaginea \"brusc\" sau \"fără probleme\" se schimbă la acel moment și, prin urmare, cât de probabil este ca o parte a imaginii să reprezinte o margine, precum și modul în care marginea respectivă este probabil orientată.\n" +
                "\n" +
                " În practică, calculul amplitudinii (probabilitatea unei margini) este mai fiabil și mai ușor de interpretat decât calculul direcției. În mod punctual, gradientul unei funcții cu două variabile (aici funcția de intensitate a imaginii) este la fiecare punct de imagine un vector 2D cu componentele date de derivați în direcțiile orizontale și verticale. La fiecare punct de imagine, vectorul de gradient indică în direcția celei mai mari creșteri a intensității posibile, iar lungimea vectorului de gradient corespunde ratei de schimbare în direcția respectivă. Aceasta implică faptul că rezultatul operatorului Prewitt la un punct de imagine care se află într-o regiune cu intensitate constantă a imaginii este un vector zero și la un punct pe o margine este un vector care indică peste margine, de la valori mai întunecate la cele mai strălucitoare.");

        ImageView imageView = new ImageView("./utils/images/seeMore.png");
        imageView.setId("homeIcon");
        imageView.setFitHeight(105);
        imageView.setFitWidth(172);

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText("https://en.wikipedia.org/wiki/Prewitt_operator");
        hyperlink.setAlignment(Pos.CENTER);
        hyperlink.setOnAction(e -> {
            try {
                openWebpage( "https://en.wikipedia.org/wiki/Prewitt_operator");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Label label =  new Label();
        label.setText("Vezi mai multe:");

        homePane.getChildren().addAll(textArea, hyperlink, imageView, label);

        Pane helpPane = new Pane();
        helpPane.setId("helpPane");
        helpPane.setPrefSize(1100, 600);
        TextArea textAreaHelp = new TextArea();
        textAreaHelp.setEditable(false);
        textAreaHelp.setText("\n"
                +"Prewitt Editor v7.1.0 \n \n"+
        "Autor: Rosana Constantin\n"+
        "Proiect:  https://github.com/RosanaConstantin/imageProcessing\n\n\n"+
        "Acest program este software liber; îl puteți redistribui și / sau modifica în conformitate cu termenii Licenței Publice Generale GNU, publicată de Fundația pentru Software Liber; fie versiunea 2 a Licenței, fie (la alegere) orice versiune ulterioară.\n"+
        "Acest program este distribuit în speranța că va fi util, dar FĂRĂ NICI O GARANȚIE; fără nici măcar garanția implicită de VANDABILITATE sau de UTILIZAREA PENTRU UN SCOP SPECIC. Pentru mai multe detalii, consultați Licența publică generală GNU.\n\n");
        ImageView imageViewHelp = new ImageView("./utils/images/matrix.png");
        imageViewHelp.setId("helpIcon");
        imageViewHelp.setFitHeight(150);
        imageViewHelp.setFitWidth(200);

        helpPane.getChildren().addAll(textAreaHelp, imageViewHelp);

        Hyperlink hyperlinkGit = new Hyperlink();
        hyperlinkGit.setText("https://github.com/RosanaConstantin/imageProcessing");
        hyperlinkGit.setAlignment(Pos.CENTER);
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


        //Tab menu actions

        home.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                HomePage.home.homeInitialize(homePane, helpPane);
            }
        });

        help.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                HelpPage.help.helpInitialize(helpPane, homePane);
            }
        });

        processing.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ProcessPage.process.processInitialize(parentPane, homePane, helpPane);
            }
        });


        //ChoiceBox
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Vertical", "Horizontal", "Vert&Horiz")
        );
        cb.setTooltip(new Tooltip("Selecteaza orientarea de procesare"));


        //Button load
        Button loadButton = new Button();
        loadButton.setText("Incarca imaginea");

        //Button process
        Button processButton = new Button();
        processButton.setText("Proceseaza imaginea");

        //DatePicker
        DatePicker dateHolder = new DatePicker();
        dateHolder.setEditable(false);

        //homeIcon
        ImageView homeIcon = new ImageView();

        //helpIcon
        ImageView helpIcon = new ImageView();


        parentPane.getChildren().addAll(menu, sc, labelGit, hyperlinkGit, cb, dateHolder);


        //Creare scena

        Scene scene = new Scene(parentPane,1200, 740);
        primaryStage.setTitle("Procesare imagine");
        primaryStage.setScene(scene);
        Initialize.initialize(dateHolder, homePane, helpPane, homeIcon, helpIcon);
        primaryStage.show();
    }


}