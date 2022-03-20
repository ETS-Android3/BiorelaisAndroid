<?php

class daoProduit {

	// -------------------------------------------
    // Recupère la derniere vente
    public static function getNouveauProduit(){
        DBConnex::run(
            "Tentative de récupération du dernier produit ajouté.",
            "Récupération du dernier produit ajouté réussite.",
            "Echec de la récupération du dernier produit ajouté !",
            "SELECT *
            FROM produit AS p, proposer AS o, unite AS u
            WHERE p.idProduit = o.idProduit
            AND o.codeUnite = u.codeUnite
            ORDER BY p.idProduit
            LIMIT 1"
        );
    }
	// -------------------------------------------
    
}
