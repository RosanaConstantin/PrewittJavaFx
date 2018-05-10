import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Rosana-Constantin on 11-May-18.
 */
public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        root.setId("parentPane");
        root.setPrefSize(1200, 740.0);

        TabPane menu = new TabPane();
        menu.setTabMaxWidth(100.0);
        menu.setTabMinWidth(100.0);
        menu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        menu.setId("menuBar");
        menu.setSide(Side.TOP);
        final Tab home = new Tab("Acasa");
        home.setId("homeItem");
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

        Label label =  new Label();
        label.setText("Vezi mai multe:");

        homePane.getChildren().addAll(textArea, hyperlink, imageView, label);
        root.getChildren().addAll(menu, sc);
        Scene scene = new Scene(root,1200, 740);
        primaryStage.setTitle("Procesare imagine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}