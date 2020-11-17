package fr.cesi.ihm;

import fr.cesi.bo.Attaque;
import fr.cesi.dal.AttaqueDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("## Pokemon Manager ##");
        AttaqueDAO adao = new AttaqueDAO();
        List<Attaque> listeAttaque = adao.get();
        System.out.println(listeAttaque);
    }
}
