<?php

class daoUtilisateur {

	// -------------------------------------------
    // Connexion login + mdp
    public static function authentificationByToken(){
        DBConnex::run(
            "Tentative de connexion par jeton, jeton = '" . $_POST['token'] . "'.",
            "Connexion par jeton réussite.",
            "Echec de connexion au compte par jeton !",
            "SELECT u.idUtilisateur, mail, nom, prenom, u.codeStatut, jeton, libelle, dateExpiration
            FROM utilisateur AS u, statut AS s, jeton AS j
            WHERE j.jeton = :token 
            AND u.codeStatut = s.codeStatut
            AND u.idUtilisateur = j.idUtilisateur",
            array("token")
        );
    }
	// -------------------------------------------



	// -------------------------------------------
    // Connexion login + mdp
    public static function authentification(){
        DBConnex::run(
            "Tentative de connexion, login = '" . $_POST['login'] .  "', mdp = '" . $_POST['mdp'] . "'.",
            "Connexion réussite.",
            "Echec de connexion au compte !",
            "SELECT u.idUtilisateur, nom, mail, prenom, u.codeStatut, libelle, jeton, dateExpiration
            FROM utilisateur AS u, statut AS s, jeton AS j
            WHERE u.mail = :login 
            AND u.mdp = :mdp
            AND u.codeStatut = s.codeStatut
            AND u.idUtilisateur = j.idUtilisateur",
            array("login", "mdp")
        );
    }
	// -------------------------------------------



	// -------------------------------------------
    // Connexion login + mdp
    public static function getSel(){
        DBConnex::run(
            "Tentative de récupération du sel = '" . $_POST['login'] . "'.",
            "Récupération réussite.",
            "Echec de requête de récupéraion du sel !",
            "SELECT sel
            FROM utilisateur AS u 
            WHERE mail = :login",
            array("login")
        );
    }
	// -------------------------------------------
    
}
