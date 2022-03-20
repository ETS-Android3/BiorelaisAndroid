<?php

class CommandeDAO
{
    public static function ToutesLesCommandesDuClient()
    {
        $param=array("token");

        $sql = "SELECT idVente,c.idUtilisateur,idCommande,date_,validee,remarque
                FROM commande AS c, jeton AS j, utilisateur AS u
                WHERE j.idUtilisateur = u.idUtilisateur
                AND j.jeton = :token
                AND u.codeStatut = 'CL' ";

        if(isset($_POST['valide'])) {              
            $sql .= "AND validee = :valide ";
            array_push($param, "valide");
        } else if(isset($_POST['date_'])) {
            $sql .= "AND date_ = :date_ ";
            array_push($param, "date_");
        }
        else if(isset($_POST['Signaler'])) {
            $sql .= "AND Remarque is not null ";
        }

        $sql.= "ORDER BY idCommande DESC";

        DBConnex::runFetchAll(
            "Tentative de récupération de toutes les commandes d'un client :), token = '" . $_POST['token'] . "'.",
            "Récup de toutes les commandes d'un client",
            "Echec de la récupération de toutes les commandes d'un client !",
            $sql,
            $param,
        );
    }

    public static function infosCommandeClient()
    {
        DBConnex::runFetchAll(
            "Tentative de récupération de toutes les infos d'une commande d'un client :), token = '" . $_POST['token'] . ".",
            "Récup infos commande réussite.",
            "Echec de la récupération de toutes les infos d'une commande d'un client !",
            "SELECT *
            FROM commande AS c, jeton AS j, utilisateur AS u,
            produit as p,ligneCommande as l,proposer as pr,unite as un,categorie as ca
            WHERE j.idUtilisateur = u.idUtilisateur
            AND j.jeton = :token
            AND l.idCommande=:id
            AND l.idProduit=p.idProduit
            AND pr.idProduit=p.idProduit
            AND u.codeStatut = 'CL'
            AND un.codeUnite= pr.codeUnite
            AND c.idCommande=l.idCommande
            AND ca.idCateg = p.idCateg ",
            array("token","id")
        );
    }

    public static function confirmerCommandeClient()
    {
        DBConnex::runQuery(
            "Tentative de confirmation d'une commande",
            "confirmation commande réussite.",
            "Echec de la Tentative de confirmation d'une commande !",
            "UPDATE lignecommande
            SET quantiteLivree = quantiteALivrer
            WHERE idCommande=:id;
            UPDATE commande
            SET validee = 1
            WHERE idCommande=:id",
            array("id")
        );
    }
    
    public static function SignalementCommandeRemarque()
    {
        DBConnex::runQuery(
            "Tentatve de signalement d'une commande pour une remarque",
            "Signalement remarque reussi",
            "Echec du signalement remarque",
            "UPDATE commande
            SET Remarque = :Remarque, validee=1
            WHERE idCommande=:id;",
            array("id","Remarque")
            );
    }

    public static function SignalementCommande()
    {
        DBConnex::runQuery(
            "Tentatve de signalement d'une commande",
            "Signalement reussi",
            "Echec du signalement",
            "UPDATE ligneCommande
            SET quantiteLivree = :qtte
            WHERE idCommande=:id
            AND idProduit=:idProduit",
            array("id","idProduit","qtte")
        );
    }
}