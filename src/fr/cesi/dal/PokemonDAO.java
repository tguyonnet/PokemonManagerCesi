package fr.cesi.dal;

import fr.cesi.bo.Attaque;
import fr.cesi.bo.Dresseur;
import fr.cesi.bo.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO implements DAO<Pokemon> {

    final static String SQL_INSERT = "INSERT INTO pokemon (identifiant, nom, poids, taille, design, attaque01, attaque02, dresseur) values(?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_SELECT_ALL = "SELECT id, identifiant, nom, poids, taille, design, attaque01, attaque02, dresseur FROM pokemon";
    final static String SQL_SELECT_1 = "SELECT id, identifiant, nom, poids, taille, design, attaque01, attaque02, dresseur FROM pokemon WHERE id=?";
    final static String SQL_DELETE = "DELETE FROM pokemon WHERE id=?";
    final static String SQL_UPDATE = "UPDATE pokemon SET identifiant=?, nom=?, poids=?, taille=?, design=?, attaque01=?, attaque02=?, dresseur=? WHERE id=?";

    @Override
    public Pokemon get(int id) {
        AttaqueDAO adao = new AttaqueDAO();
        DresseurDAO ddao = new DresseurDAO();
        Pokemon p = new Pokemon();
        try (Connection cnx = Connexion.seConnecter(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_1)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setId(rs.getInt("id"));
                    p.setIdentifiant(rs.getInt("identifiant"));
                    p.setNom(rs.getString("nom"));
                    p.setPoids(rs.getFloat("poids"));
                    p.setTaille(rs.getFloat("taille"));
                    p.setDesign(rs.getString("design"));
                    p.setAttaque01(adao.get(rs.getInt("attaque01")));
                    p.setAttaque02(adao.get(rs.getInt("attaque02")));
                    p.setDresseur(ddao.get(rs.getInt("dresseur")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return p;
    }

    @Override
    public List<Pokemon> getAll() {
        // Retourne la liste des Pokémons en base
        List<Pokemon> listeDesPokemons = new ArrayList<Pokemon>();
        AttaqueDAO adao = new AttaqueDAO();
        DresseurDAO ddao = new DresseurDAO();
        try (Connection cnx = Connexion.seConnecter(); Statement stmt = cnx.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                Attaque atq01 = adao.get(rs.getInt("attaque01"));
                Attaque atq02 = adao.get(rs.getInt("attaque02"));
                Dresseur dresseur = ddao.get(rs.getInt("dresseur"));
                Pokemon pokemonCourant = new Pokemon(
                        rs.getInt("id"),
                        rs.getInt("identifiant"),
                        rs.getString("nom"),
                        rs.getFloat("poids"),
                        rs.getFloat("taille"),
                        rs.getString("design"),
                        atq01, atq02, dresseur
                );
                listeDesPokemons.add(pokemonCourant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return listeDesPokemons;
    }

    @Override
    public void update(Pokemon pokemon) {
        try (Connection cnx = Connexion.seConnecter(); PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE)) {
            pstmt.setInt(1, pokemon.getIdentifiant());
            pstmt.setString(2, pokemon.getNom());
            pstmt.setFloat(3, pokemon.getPoids());
            pstmt.setFloat(4, pokemon.getTaille());
            pstmt.setString(5, pokemon.getDesign());
            pstmt.setInt(6, pokemon.getAttaque01().getId());
            pstmt.setInt(7, pokemon.getAttaque02().getId());
            pstmt.setInt(8, pokemon.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection cnx = Connexion.seConnecter();
             PreparedStatement pstmt = cnx.prepareStatement(SQL_DELETE)
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    @Override
    public Pokemon insert(Pokemon pokemon) {
        int idGenere = 0;
        try (Connection cnx = Connexion.seConnecter();
             PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, pokemon.getIdentifiant());
            pstmt.setString(2, pokemon.getNom());
            pstmt.setFloat(3, pokemon.getPoids());
            pstmt.setFloat(4, pokemon.getTaille());
            pstmt.setString(5, pokemon.getDesign());
            pstmt.setInt(6, pokemon.getAttaque01().getId());
            pstmt.setInt(7, pokemon.getAttaque02().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenere = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        pokemon.setId(idGenere);
        return pokemon;
    }
}
