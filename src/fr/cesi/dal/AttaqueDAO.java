package fr.cesi.dal;

import fr.cesi.bo.Attaque;
import fr.cesi.bo.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttaqueDAO implements DAO<Attaque> {
    @Override
    public List<Attaque> get() {
        // Retourne la liste des Pokémons en base
        List<Attaque> listeDesAttaques = new ArrayList<Attaque>();
        try (Connection cnx = Connexion.seConnecter(); Statement stmt = cnx.createStatement()) {
            String sql = "SELECT id, nom, force FROM attaque";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Attaque attaqueCourante = new Attaque(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("force")
                );
                listeDesAttaques.add(attaqueCourante);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return listeDesAttaques;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void insert() {

    }

    // Update

    // Delete
}
