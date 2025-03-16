package persistentie;

import domein.Speler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO G02.speler (gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld)"
            + "VALUES (?, ?, ?, ?)";
    private static final String GEEF_SPELER = "SELECT * FROM G02.speler WHERE gebruikersnaam = ?";
    private static final String UPDATE_SPELER = "UPDATE G02.speler"
            + " SET geboortejaar = ?, aantalGewonnen = ?, aantalGespeeld = ?"
            + " WHERE gebruikersnaam = ?";
    private static final String GEEF_ALLE_SPELERS = "SELECT * FROM G02.speler";

    public void voegToe(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getGebruikersnaam());
            query.setInt(2, speler.getGeboortejaar());
            query.setInt(3, speler.getAantalOverwinningen());
            query.setInt(4, speler.getAantalGespeeld());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    int geboortejaar = rs.getInt("geboortejaar");
                    int aantalGewonnen = rs.getInt("aantalGewonnen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");
                    speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return speler;
    }

    public void updateSpeler(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement updateQuery = conn.prepareStatement(UPDATE_SPELER)) {
            updateQuery.setInt(1, speler.getGeboortejaar());
            updateQuery.setInt(2, speler.getAantalOverwinningen());
            updateQuery.setInt(3, speler.getAantalGespeeld());
            updateQuery.setString(4, speler.getGebruikersnaam());
            updateQuery.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Speler> geefAlleSpelers() {
        List<Speler> spelers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(GEEF_ALLE_SPELERS);
             ResultSet rs = query.executeQuery()) {
            while (rs.next()) {
                String gebruikersnaam = rs.getString("gebruikersnaam");
                int geboortejaar = rs.getInt("geboortejaar");
                int aantalGewonnen = rs.getInt("aantalGewonnen");
                int aantalGespeeld = rs.getInt("aantalGespeeld");
                Speler speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld);
                spelers.add(speler);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelers;
    }
}