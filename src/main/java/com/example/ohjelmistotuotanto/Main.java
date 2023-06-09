package com.example.ohjelmistotuotanto;

import com.example.ohjelmistotuotanto.NakymaHallinta.Alue.AlueHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.AsiakasHallinta.AsiakasHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.AsiakasHallinta.AsiakashallintaView;
import com.example.ohjelmistotuotanto.NakymaHallinta.LaskuHallinta.LaskuHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.Majoitus.MajoitusHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.Majoitus.MajoitusMokkiController;
import com.example.ohjelmistotuotanto.NakymaHallinta.Majoitus.MajoitusvarausHallintaView;
import com.example.ohjelmistotuotanto.NakymaHallinta.MokkiHallinta.MokkiHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.Palvelu.PalveluHallintaController;
import com.example.ohjelmistotuotanto.NakymaHallinta.Posti.PostiController;
import com.example.ohjelmistotuotanto.NakymaHallinta.VarauksenPalvelut.VarauksenPalvelutController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private BorderPane root;
    private AlueHallintaController alueHallintaView;
    private PalveluHallintaController palveluHallintaView;
    private MajoitusvarausHallintaView majoitusvarausHallintaView;
    private AsiakashallintaView asiakashallintaView;
    private LaskuHallintaController laskuHallintaController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        palveluHallintaView = new PalveluHallintaController();
        majoitusvarausHallintaView = new MajoitusvarausHallintaView();
        asiakashallintaView = new AsiakashallintaView();
        laskuHallintaController = new LaskuHallintaController();

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        Scene scene = new Scene(root, 1400, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Varausjärjestelmä");
        root.setCenter(new MajoitusHallintaController());
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu MokkiMenu = new Menu("Mökki");
        MenuItem MokkiMenuItem2 = new MenuItem("Hallinta");
        MokkiHallintaController mokkiHallintaView = new MokkiHallintaController();

        MokkiMenuItem2.setOnAction(e -> {
            root.setCenter(mokkiHallintaView);
        });


        MokkiMenu.getItems().addAll(MokkiMenuItem2);

        Menu AlueMenu = new Menu("Alue");
        MenuItem AlueMenuItem = new MenuItem("Hallinta");
        AlueHallintaController alueHallintaController = new AlueHallintaController();
        AlueMenuItem.setOnAction(e -> root.setCenter(alueHallintaController));
        AlueMenu.getItems().add(AlueMenuItem);

        Menu palveluMenu = new Menu("Palvelu");
        MenuItem palveluMenuItem = new MenuItem("Hallinta");
        PalveluHallintaController palveluHallintaController = new PalveluHallintaController();
        palveluMenuItem.setOnAction(e -> root.setCenter(palveluHallintaController));
        palveluMenu.getItems().add(palveluMenuItem);

        Menu majoitusvarausMenu = new Menu("Majoitusvaraus");
        MenuItem majoitusvarausMenuItem = new MenuItem("Hallinta");
        MenuItem majoitusvarausSeurantaMenuitem = new MenuItem("Majoitus seuranta");
        MajoitusHallintaController majoitusHallintaController = new MajoitusHallintaController();
        MajoitusMokkiController majoitusMokkiController = new MajoitusMokkiController();
        majoitusvarausMenuItem.setOnAction(e -> root.setCenter(majoitusHallintaController));
        majoitusvarausSeurantaMenuitem.setOnAction(e -> root.setCenter(majoitusMokkiController));
        majoitusvarausMenu.getItems().addAll(majoitusvarausMenuItem, majoitusvarausSeurantaMenuitem);

        Menu asiakasMenu = new Menu("Asiakashallinta");
        MenuItem asiakasMenuItem = new MenuItem("Hallinta");
        AsiakasHallintaController asiakasHallintaControlle = new AsiakasHallintaController();
        asiakasMenuItem.setOnAction(e -> root.setCenter(asiakasHallintaControlle));
        asiakasMenu.getItems().add(asiakasMenuItem);

        Menu laskumenu = new Menu("Laskuhallinta");
        MenuItem laskuMenuItem = new MenuItem("Hallinta");
        LaskuHallintaController laskuHallintaController = new LaskuHallintaController();
        laskuMenuItem.setOnAction(e -> root.setCenter(laskuHallintaController));
        laskumenu.getItems().add(laskuMenuItem);

        Menu postimenu = new Menu("postihallinta");
        MenuItem postiMenuItem = new MenuItem("Hallinta");
        PostiController postiController = new PostiController();
        postiMenuItem.setOnAction(e -> root.setCenter(postiController));
        postimenu.getItems().add(postiMenuItem);

        Menu varauksenpalvelutmenu = new Menu("Varauksen Palveluiden Hallinta");
        MenuItem varauksenpalveluItem = new MenuItem("Hallinta");
        VarauksenPalvelutController varauksenPalvelutController = new VarauksenPalvelutController();
        varauksenpalveluItem.setOnAction(e -> root.setCenter(varauksenPalvelutController));
        varauksenpalvelutmenu.getItems().add(varauksenpalveluItem);

        menuBar.getMenus().addAll(MokkiMenu, AlueMenu, palveluMenu, majoitusvarausMenu, asiakasMenu, laskumenu, postimenu, varauksenpalvelutmenu);

        return menuBar;
    }
}
