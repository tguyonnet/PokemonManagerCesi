package fr.cesi.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static Connection seConnecter() {
        String jdbc = "jdbc:sqlite:db.sqlite3";
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(jdbc);
        } catch (SQLException e) {
            System.err.println("Erreur SQL " + e.getMessage());
        }
        return cnx;
    }
}
