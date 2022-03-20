<?php

require_once 'dao/param.php';
require_once 'dao/dBConnex.php';

require_once 'lib/log.php';
require_once 'lib/json.php';

require_once 'dao/daoUtilisateur.php';
require_once 'dao/commandeDAO.php';

/*
$_POST['command'] = "infosCommandeClient";
$_POST['token'] = "yoan";
$_POST['login'] = "yoan.laurain0@gmail.com";
$_POST['mdp'] = "5291cefe14868f6853703e4c7d68e2145347909c18b187ac9aa170ad997ebb9b";
$_POST['id']=1;
*/

// -------------------------------------------
// Redirige vers la bonne commande
if (isset($_POST['command'])) {
	// Verifi si la commande existe
	$files = scandir("dao");
	$max = count($files);
	$count = 0;
	$found = false;
	$class = "";
	while (!$found && $count < $max) {
		$dao = str_replace(".php", "", $files[$count]);
		if (method_exists($dao, $_POST['command'])) {
			$found = true;
			$class = $dao;
		} else {
			$count++;
		}
	}
	if ($found) {
		call_user_func($dao . "::" . $_POST['command']);
	} else {
		log::put("Commande : '" . $_POST['command'] . "' inconnue !", log::$LEVEL_ERROR);
	}	
} else {
	log::put("Aucune commande !", log::$LEVEL_ERROR);
}
// -------------------------------------------
