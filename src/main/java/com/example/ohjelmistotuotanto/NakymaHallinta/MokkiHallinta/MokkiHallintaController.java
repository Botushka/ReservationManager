package com.example.ohjelmistotuotanto.NakymaHallinta.MokkiHallinta;

import com.example.ohjelmistotuotanto.DatabaseManager;
import com.example.ohjelmistotuotanto.NakymaHallinta.Alue.AlueOlio;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MokkiHallintaController extends BorderPane {

    @FXML
    public TableView<Mokki> mokkiTable;

    @FXML
    public TableColumn<Mokki, Integer> mokkiIdColumn;

    @FXML
    public TableColumn<Mokki, Integer> alueIdColumn;

    @FXML
    public TableColumn<Mokki, String> postinroColumn;

    @FXML
    public TableColumn<Mokki, String> nimiColumn;

    @FXML
    public TableColumn<Mokki, String> katuosoiteColumn;

    @FXML
    public TableColumn<Mokki, Double> hintaColumn;

    @FXML
    public TableColumn<Mokki, Integer> henkilomaaraColumn;

    @FXML
    public TableColumn<Mokki, String> varusteluColumn;

    @FXML
    public TableColumn<Mokki, String> kuvausColumn;

    @FXML
    public Button lisaanappula;
    @FXML
    public Button muokkaanappula;
    public TextField haemokki;
    public TextField kuvauskentta;
    public TextField varustelukentta;
    public TextField hmaarakentta;
    public TextField osoitekentta;
    public TextField nimikentta;
    public TextField postikentta;
    public TextField aluekentta;
    public TextField mokkikentta;
    public TextField hintakentta;
    private List<Mokki> mokit = new ArrayList<>();


    private void naytaMokit(List<Mokki> mokit) {
        ObservableList<Mokki> mokkiData = FXCollections.observableArrayList(mokit);
        mokkiTable.setItems(mokkiData);
    }

    @FXML
    public void initialize() {
        // Alusta taulukon sarakkeet
        mokkiIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getMokkiId())).asObject());
        mokkiIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        mokkiIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, Integer>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, Integer> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setMokkiId(String.valueOf(event.getNewValue()));
            }
        });

        alueIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAlueId()).asObject());
        alueIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        alueIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, Integer>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, Integer> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setAlueId(event.getNewValue());
            }
        });


        postinroColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPostinro()).asObject().asString());
        postinroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        postinroColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, String> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setPostinro(Integer.parseInt(event.getNewValue()));
            }
        });

        nimiColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNimi()));
        nimiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nimiColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, String> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setNimi(event.getNewValue());
            }
        });

        katuosoiteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKatuosoite()));
        katuosoiteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        katuosoiteColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, String> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setKatuosoite(event.getNewValue());
            }
        });

        hintaColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHinta()).asObject());
        hintaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        hintaColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, Double>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, Double> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setHinta(event.getNewValue());
            }
        });

        henkilomaaraColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHenkilomaara()).asObject());
        henkilomaaraColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        henkilomaaraColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, Integer>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, Integer> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setHenkilomaara(event.getNewValue());
            }
        });

        varusteluColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVarustelu()));
        varusteluColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        varusteluColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, String> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setVarustelu(event.getNewValue());
            }
        });

        kuvausColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKuvaus()));
        kuvausColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        kuvausColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mokki, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Mokki, String> event)
            {
                Mokki mokki = event.getRowValue();
                mokki.setKuvaus(event.getNewValue());
            }
        });

        ObservableList<Mokki> mokkiData = FXCollections.observableArrayList(haeMokitTietokannasta());
        mokkiTable.setItems(mokkiData);
        mokkiTable.setEditable(true);

        naytaMokit(mokkiData);
        // Hae mokit tietokannasta ja lisää ne taulukkoon

        haemokki.textProperty().addListener((observable, oldValue, newValue) -> {
            String hakusana = newValue.toLowerCase();
            if (hakusana.trim().isEmpty()) {
                mokkiTable.setItems(mokkiData);
            } else {
                List<Mokki> hakutulokset = new ArrayList<>();
                for (Mokki mokki : mokkiData) {
                    if (mokki.getNimi().toLowerCase().contains(hakusana) ||
                            mokki.getMokkiId().toLowerCase().contains(hakusana) ||
                            String.valueOf(mokki.getAlueId()).toLowerCase().contains(hakusana) ||
                            String.valueOf(mokki.getPostinro()).toLowerCase().contains(hakusana) ||
                            mokki.getKatuosoite().toLowerCase().contains(hakusana) ||
                            String.valueOf(mokki.getHinta()).toLowerCase().contains(hakusana) ||
                            String.valueOf(mokki.getHenkilomaara()).toLowerCase().contains(hakusana) ||
                            mokki.getVarustelu().toLowerCase().contains(hakusana) ||
                            mokki.getKuvaus().toLowerCase().contains(hakusana)) {
                        hakutulokset.add(mokki);
                    }
                }
                mokkiTable.setItems(FXCollections.observableArrayList(hakutulokset));
            }
        });
    }

    private List<Mokki> haeMokitTietokannasta() {
        // Tämä on vain esimerkki, voit korvata tämän oikealla tietokannan käyttöä hoitavalla koodilla
        List<Mokki> mokit = new ArrayList<>();
        mokit.add(new Mokki("1", 1, 12345, "Mökki1", "Katu1", 100.0, 2, "Hyvä", "Luksusmökki järven rannalla"));
        mokit.add(new Mokki("2", 2, 54321, "Mökki2", "Katu2", 200.0, 3, "Hyvä", "Kaunis mökki metsän keskellä"));
        return mokit;

    }


    public MokkiHallintaController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ohjelmistotuotanto/mokki.fxml"));
        loader.setController(this);
        try {
            setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void lisaatieto(ActionEvent event) throws SQLException {
        Mokki newMokki = new Mokki(mokkikentta.getText(), Integer.parseInt(aluekentta.getText()), Integer.parseInt(postikentta.getText()),
                nimikentta.getText(), osoitekentta.getText(), Double.parseDouble(hintakentta.getText()),
                Integer.parseInt(hmaarakentta.getText()), varustelukentta.getText(), kuvauskentta.getText());

        ObservableList<Mokki> mokitData = mokkiTable.getItems();
        mokitData.add(newMokki);

        updateMokki(newMokki);

        // Clear the input fields
        mokkikentta.clear();
        aluekentta.clear();
        postikentta.clear();
        nimikentta.clear();
        osoitekentta.clear();
        hintakentta.clear();
        hmaarakentta.clear();
        varustelukentta.clear();
        kuvauskentta.clear();

        naytaMokit(mokitData);
    }

    private EventHandler<ActionEvent> alkuperaisetMuokkausKasittelija;
    @FXML
    private void muokkaaMokki() {

    }

    private void muokkaaMokki(ActionEvent event) {
    }



    @FXML
    public void poistatieto(ActionEvent event) {
        Mokki selectedMokki = mokkiTable.getSelectionModel().getSelectedItem();
        if (selectedMokki != null) {
            TableView.TableViewSelectionModel<Mokki> selectionModel = mokkiTable.getSelectionModel();
            ObservableList<Mokki> tableItems = mokkiTable.getItems();
            int selectedIndex = selectionModel.getSelectedIndex();
            tableItems.remove(selectedIndex);
            mokkiTable.setItems(tableItems);
            mokkiTable.refresh();
        }
    }

    String url = "jdbc:mysql://localhost:3306/vn";
    String user = "root";
    String password = "";

    Connection connection = null;
    {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // Prepare your SQL statement and execute it here
    }
    private void updateMokki(Mokki mokki) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String insertQuery = "INSERT INTO Mokki (alue_id, postinro, mokkinimi, katuosoite, hinta, henkilomaara, varustelu, kuvaus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setInt(1, mokki.getAlueId());
            preparedStatement.setInt(2, mokki.getPostinro());
            preparedStatement.setString(3, mokki.getNimi());
            preparedStatement.setString(4, mokki.getKatuosoite());
            preparedStatement.setDouble(5, mokki.getHinta());
            preparedStatement.setInt(6, mokki.getHenkilomaara());
            preparedStatement.setString(7, mokki.getVarustelu());
            preparedStatement.setString(8, mokki.getKuvaus());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Jokin meni pieleen tiedon tallentamisessa tietokantaan");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
