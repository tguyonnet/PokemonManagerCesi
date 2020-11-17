package fr.cesi.ihm;

import fr.cesi.bo.Attaque;
import fr.cesi.bo.Dresseur;
import fr.cesi.bo.Pokemon;
import fr.cesi.dal.AttaqueDAO;
import fr.cesi.dal.DresseurDAO;
import fr.cesi.dal.PokemonDAO;

public class Main {
    public static void main(String[] args) {
        System.out.println("## Pokemon Manager ##");
        PokemonDAO pdao = new PokemonDAO();
        DresseurDAO ddao = new DresseurDAO();
        AttaqueDAO adao = new AttaqueDAO();
        Attaque atq01 = new Attaque("Statik", 25);
        Attaque atq02 = new Attaque("ParaTonnerre", 55);
        Dresseur dresseur = new Dresseur("Caliendo", "Julien");
        Pokemon pikachu = new Pokemon(25, "Pikachu", 0.6f, 1.2f);

        pikachu.setAttaque01(adao.insert(atq01));
        pikachu.setAttaque02(adao.insert(atq02));
        pikachu.setDresseur(ddao.insert(dresseur));

        pdao.insert(pikachu);
        System.out.println(pdao.getAll());
    }
}
