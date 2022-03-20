<?php

class daoVente {

	// -------------------------------------------
    // Recupère la derniere vente
    public static function getProchaineVente(){
        DBConnex::run(
            "Tentative de récupération de la prochaine vente.",
            "Récupération de la prochaine vente réussite.",
            "Echec de la récupération de la prochaine vente !",
            "SELECT *
            FROM vente
            ORDER BY idVente
            LIMIT 1"
        );
    }
	// -------------------------------------------
    
}
