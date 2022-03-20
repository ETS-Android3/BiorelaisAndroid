-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 22 août 2021 à 10:24
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biorelaisandroid`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherent`
--

DROP TABLE IF EXISTS `adherent`;
CREATE TABLE IF NOT EXISTS `adherent` (
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherent`
--

INSERT INTO `adherent` (`idUtilisateur`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCateg` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(50) NOT NULL,
  PRIMARY KEY (`idCateg`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idCateg`, `nomCategorie`) VALUES
(1, 'Fruit'),
(2, 'Legume');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idVente` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  `date_` date NOT NULL,
  `validee` tinyint(1) NOT NULL,
  `remarque` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idVente`,`idUtilisateur`,`idCommande`),
  KEY `COMMANDE_ADHERENT0_FK` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idVente`, `idUtilisateur`, `idCommande`, `date_`, `validee`, `remarque`) VALUES
(1, 2, 1, '2021-03-14', 0, ''),
(2, 2, 2, '2021-03-17', 1, NULL),
(3, 2, 3, '2021-04-23', 1, NULL),
(4, 2, 4, '2021-04-26', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `jeton`
--

DROP TABLE IF EXISTS `jeton`;
CREATE TABLE IF NOT EXISTS `jeton` (
  `idUtilisateur` int(11) NOT NULL,
  `jeton` varchar(300) NOT NULL,
  `dateExpiration` date NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jeton`
--

INSERT INTO `jeton` (`idUtilisateur`, `jeton`, `dateExpiration`) VALUES
(1, 'bubu', '2021-03-24'),
(2, 'yoan', '2021-03-26'),
(3, 'nat', '2021-03-03');

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `idVente` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `quantiteALivrer` double NOT NULL,
  `quantiteLivree` double NOT NULL,
  `quantiteRecuperee` tinyint(1) NOT NULL,
  PRIMARY KEY (`idVente`,`idUtilisateur`,`idCommande`,`idProduit`),
  KEY `LIGNECOMMANDE_PRODUIT0_FK` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lignecommande`
--

INSERT INTO `lignecommande` (`idVente`, `idUtilisateur`, `idCommande`, `idProduit`, `quantiteALivrer`, `quantiteLivree`, `quantiteRecuperee`) VALUES
(1, 2, 1, 3, 2, 1, 2),
(1, 2, 1, 7, 1, 1, 1),
(2, 2, 2, 19, 2, 2, 1),
(3, 2, 3, 5, 1, 1, 1),
(3, 2, 3, 9, 3, 3, 3),
(3, 2, 3, 10, 2, 2, 2),
(3, 2, 3, 12, 5, 5, 5),
(4, 2, 4, 14, 10, 10, 0),
(4, 2, 4, 16, 3, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `producteur`
--

DROP TABLE IF EXISTS `producteur`;
CREATE TABLE IF NOT EXISTS `producteur` (
  `idUtilisateur` int(11) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `commune` varchar(50) NOT NULL,
  `codePostal` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `photo` varchar(50) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `producteur`
--

INSERT INTO `producteur` (`idUtilisateur`, `adresse`, `commune`, `codePostal`, `description`, `photo`) VALUES
(1, '15 place de la victoire', 'bordeaux', '33800', 'Producteur de nos régions.', '');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `nomProduit` varchar(50) NOT NULL,
  `descriptif` longtext NOT NULL,
  `idCateg` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `PRODUIT_CATEGORIE_FK` (`idCateg`),
  KEY `PRODUIT_PRODUCTEUR0_FK` (`idUtilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `nomProduit`, `descriptif`, `idCateg`, `idUtilisateur`) VALUES
(1, 'pomme', '', 1, 1),
(2, 'orange', '', 1, 1),
(3, 'cerise', '', 1, 1),
(4, 'kiwi', '', 1, 1),
(5, 'pamplemousse', '', 1, 1),
(6, 'raisins', '', 1, 1),
(7, 'abricot', '', 1, 1),
(8, 'poires', '', 1, 1),
(9, 'bananes', '', 1, 1),
(10, 'framboises', '', 1, 1),
(11, 'aubergine', '', 2, 1),
(12, 'navet', '', 2, 1),
(13, 'choux', '', 2, 1),
(14, 'oignon', '', 2, 1),
(15, 'courgette', '', 2, 1),
(16, 'salade', '', 2, 1),
(17, 'patate', '', 2, 1),
(18, 'haricot', '', 2, 1),
(19, 'mais', '', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `proposer`
--

DROP TABLE IF EXISTS `proposer`;
CREATE TABLE IF NOT EXISTS `proposer` (
  `idProduit` int(11) NOT NULL,
  `idVente` int(11) NOT NULL,
  `codeUnite` varchar(50) NOT NULL,
  `quantite` double NOT NULL,
  `prix` double NOT NULL,
  PRIMARY KEY (`idProduit`,`idVente`,`codeUnite`),
  KEY `PROPOSER_VENTE0_FK` (`idVente`),
  KEY `PROPOSER_UNITE1_FK` (`codeUnite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `proposer`
--

INSERT INTO `proposer` (`idProduit`, `idVente`, `codeUnite`, `quantite`, `prix`) VALUES
(3, 1, 'KG', 1, 3),
(5, 3, 'KG', 10, 2),
(7, 1, 'KG', 2, 15),
(9, 3, 'KG', 20, 1),
(10, 3, 'KG', 30, 17),
(12, 3, 'BT', 10, 3),
(14, 4, 'KG', 50, 1),
(16, 4, 'KG', 12, 14),
(19, 2, 'KG', 10, 10);

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

DROP TABLE IF EXISTS `statut`;
CREATE TABLE IF NOT EXISTS `statut` (
  `codeStatut` varchar(5) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`codeStatut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `statut`
--

INSERT INTO `statut` (`codeStatut`, `libelle`) VALUES
('CL', 'Client'),
('Gest', 'Gestionnaire'),
('Prod', 'Producteur');

-- --------------------------------------------------------

--
-- Structure de la table `unite`
--

DROP TABLE IF EXISTS `unite`;
CREATE TABLE IF NOT EXISTS `unite` (
  `codeUnite` varchar(50) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`codeUnite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `unite`
--

INSERT INTO `unite` (`codeUnite`, `libelle`) VALUES
('BT', 'bottes'),
('KG', 'Kilogramme');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(50) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `sel` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `codeStatut` varchar(5) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  KEY `UTILISATEUR_STATUT_FK` (`codeStatut`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `mail`, `mdp`, `sel`, `nom`, `prenom`, `codeStatut`) VALUES
(1, 'thibault.bustos1234@gmail.com', '5291cefe14868f6853703e4c7d68e2145347909c18b187ac9aa170ad997ebb9b', 'AAAA', 'Bustos', 'Thibault', 'Prod'),
(2, 'yoan.laurain0@gmail.com', '5291cefe14868f6853703e4c7d68e2145347909c18b187ac9aa170ad997ebb9b', 'AAAA', 'Laurain', 'Yoan', 'CL'),
(3, 'nat', '5291cefe14868f6853703e4c7d68e2145347909c18b187ac9aa170ad997ebb9b', 'AAAA', 'Lesourd', 'Nathan', 'Gest');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `idVente` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `dateDebutProd` date NOT NULL,
  `dateFinProd` date NOT NULL,
  `dateDebutCli` date NOT NULL,
  `dateFinCli` date NOT NULL,
  PRIMARY KEY (`idVente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`idVente`, `date`, `dateDebutProd`, `dateFinProd`, `dateDebutCli`, `dateFinCli`) VALUES
(1, '2021-03-09', '2021-03-09', '2021-03-11', '2021-03-12', '2021-03-14'),
(2, '2021-03-17', '2021-03-16', '2021-03-17', '2021-03-17', '2021-03-18'),
(3, '2021-04-22', '2021-04-22', '2021-04-23', '2021-04-23', '2021-04-24'),
(4, '2021-04-26', '2021-04-23', '2021-04-25', '2021-04-25', '2021-04-26');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adherent`
--
ALTER TABLE `adherent`
  ADD CONSTRAINT `ADHERENT_UTILISATEUR_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `COMMANDE_ADHERENT0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `adherent` (`idUtilisateur`),
  ADD CONSTRAINT `COMMANDE_VENTE_FK` FOREIGN KEY (`idVente`) REFERENCES `vente` (`idVente`);

--
-- Contraintes pour la table `jeton`
--
ALTER TABLE `jeton`
  ADD CONSTRAINT `JETON_UTILISATEUR_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `LIGNECOMMANDE_COMMANDE_FK` FOREIGN KEY (`idVente`,`idUtilisateur`,`idCommande`) REFERENCES `commande` (`idVente`, `idUtilisateur`, `idCommande`),
  ADD CONSTRAINT `LIGNECOMMANDE_PRODUIT0_FK` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`);

--
-- Contraintes pour la table `producteur`
--
ALTER TABLE `producteur`
  ADD CONSTRAINT `PRODUCTEUR_UTILISATEUR_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `PRODUIT_CATEGORIE_FK` FOREIGN KEY (`idCateg`) REFERENCES `categorie` (`idCateg`),
  ADD CONSTRAINT `PRODUIT_PRODUCTEUR0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `producteur` (`idUtilisateur`);

--
-- Contraintes pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD CONSTRAINT `PROPOSER_PRODUIT_FK` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`),
  ADD CONSTRAINT `PROPOSER_UNITE1_FK` FOREIGN KEY (`codeUnite`) REFERENCES `unite` (`codeUnite`),
  ADD CONSTRAINT `PROPOSER_VENTE0_FK` FOREIGN KEY (`idVente`) REFERENCES `vente` (`idVente`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `UTILISATEUR_STATUT_FK` FOREIGN KEY (`codeStatut`) REFERENCES `statut` (`codeStatut`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
