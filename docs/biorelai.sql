-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 18 mars 2020 à 22:27
-- Version du serveur :  10.1.35-MariaDB
-- Version de PHP :  7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `biorelai`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

CREATE TABLE `adherents` (
  `idAdherent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`idAdherent`) VALUES
(19),
(26),
(27),
(28);

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `idCategorie` int(11) NOT NULL,
  `nomCategorie` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`idCategorie`, `nomCategorie`) VALUES
(1, 'légumes'),
(2, 'fruits'),
(3, 'viandes'),
(4, 'boulangerie'),
(5, 'boissons'),
(6, 'crèmerie'),
(7, 'épicerie');

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `idCommande` int(11) NOT NULL,
  `dateCommande` date NOT NULL,
  `idVente` int(11) NOT NULL,
  `idAdherent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




-- --------------------------------------------------------

--
-- Structure de la table `lignescommande`
--

CREATE TABLE `lignescommande` (
  `idCommande` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `quantite` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `producteurs`
--

CREATE TABLE `producteurs` (
  `idProducteur` int(11) NOT NULL,
  `adresseProducteur` varchar(50) NOT NULL,
  `communeProducteur` varchar(40) NOT NULL,
  `codePostalProducteur` varchar(5) NOT NULL,
  `descriptifProducteur` longtext NOT NULL,
  `photoProducteur` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `producteurs`
--

INSERT INTO `producteurs` (`idProducteur`, `adresseProducteur`, `communeProducteur`, `codePostalProducteur`, `descriptifProducteur`, `photoProducteur`) VALUES
(1, '9 chemin du moulin', 'Lamothe-Montravel', '24230', 'Producteur de fruits et légumes en agriculture raisonnée.\r\nNous pratiquons la lutte biologique intégrale ( introduction de prédateurs contre les nuisibles), ce qui évite les insecticides.\r\nNous pratiquons un désherbage mécanique ou manuel, donc pas de désherbant anti-germinatif dans les cultures.\r\nTous nos produits sont choisit pour leur qualité gustative.', '1.png'),
(2, 'Route des pommiers', 'Lizac', '82200', 'Exploitation en agriculture raisonnée pour les fraises, cerises, abricots, pêches, pêches plates, brugnons, figues, prunes américaines et dérivé en jus de fruits, compotes, veloutés et vinaigre de pommes BIO\r\nexploitation en label BIO pour certaines variétés de pommes et pour des prunes.mise en conversion en 2017 pour les poires\r\nnous vous proposons en plus des légumes BIO comme les pommes de terre, les tomates,des courgettes rondes, les artichauts, les petits pois, les cornichons, concombres, aubergines, courges pour l\'hiver... ', '2.png');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `idProduit` int(11) NOT NULL,
  `nomProduit` varchar(50) NOT NULL,
  `descriptifProduit` longtext NOT NULL,
  `photoProduit` varchar(40) NOT NULL,
  `idProducteur` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`idProduit`, `nomProduit`, `descriptifProduit`, `photoProduit`, `idProducteur`, `idCategorie`) VALUES
(1, 'Carottes', 'carottes lavées, produites en agriculture raisonnées', '1.png', 1, 1),
(2, 'Céleri branche', 'Un pied de céleri branche (entre 800g et 1kg)', '2.png', 1, 1),
(3, 'Courges Buttercup', 'Très bonne qualité gustative (proche du potimarron). Bonne conservation ', '3.png', 1, 1),
(4, 'Pommes chantecler', 'Variété reinette jaune\r\nElle est croquante avec un arrière goût sucré/amer ', '4.png', 2, 2),
(5, 'Longues de Nice', 'Une sorte de courgette longue collée à  une courgette ronde ! Sa taille peut varier de 60 cm à  1 m La chair ferme a un goût musqué et sucré. L\'épiderme, d\'abord vert, vire à l\'ocre à  maturité. Ils sont utilisés pour faire des soupes,des tartes de la purée et des gâteaux.', '5.png', 2, 1),
(6, 'Pommes pitchounettes', 'Croquante, parfumÃ©e, juteuse, sucrÃ©e, douce...\r\nvariÃ©tÃ© de la famille des reinettes... calibre moyen', '6.png', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `proposer`
--

CREATE TABLE `proposer` (
  `idVente` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `unite` varchar(10) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `idUtilisateur` int(11) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `mdp` varchar(128) NOT NULL,
  `statut` varchar(15) NOT NULL,
  `nomUtilisateur` varchar(40) DEFAULT NULL,
  `prenomUtilisateur` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`idUtilisateur`, `mail`, `mdp`, `statut`, `nomUtilisateur`, `prenomUtilisateur`) VALUES
(1, 'prod1', '78fea5e9d15310d512c26b95c11e1ff1', 'producteur', 'Martin', 'Martine'),
(2, 'prod2', '2e1d9317b801e76029454d3bd0d1f0e9', 'producteur', 'Dupont', 'Albert'),
(4, 'bio', 'e5ba7590156e333ef9aa4b9616a55921', 'bioRelai', 'Lejeune', 'Conrad'),
(19, 'adhe1', 'b0fc89b6792215fcc07d8b8c64672bef', 'adherent', 'Cassali', 'Franck'),
(26, 'adhe2', 'a93125bd25604d875a2fda31a918a0c3', 'adherent', 'Hamel', 'Benedicte'),
(27, 'test', '098f6bcd4621d373cade4e832627b4f6', 'adherent', 'test', 'test'),
(28, 'aa', '4124bc0a9335c27f086f24ba207a4912', 'adherent', 'aa', 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `ventes`
--

CREATE TABLE `ventes` (
  `idVente` int(11) NOT NULL,
  `dateVente` date NOT NULL,
  `dateDebutProd` date NOT NULL,
  `dateFinProd` date NOT NULL,
  `dateDebutCli` date NOT NULL,
  `dateFinCli` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;






--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`idAdherent`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`idCategorie`);

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `idVente` (`idVente`),
  ADD KEY `idAdherent` (`idAdherent`);

--
-- Index pour la table `lignescommande`
--
ALTER TABLE `lignescommande`
  ADD PRIMARY KEY (`idCommande`,`idProduit`),
  ADD KEY `idProduit` (`idProduit`);

--
-- Index pour la table `producteurs`
--
ALTER TABLE `producteurs`
  ADD PRIMARY KEY (`idProducteur`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`idProduit`),
  ADD KEY `idProducteur` (`idProducteur`),
  ADD KEY `idCategorie` (`idCategorie`);

--
-- Index pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD PRIMARY KEY (`idVente`,`idProduit`),
  ADD KEY `idProduit` (`idProduit`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `uniqueMail` (`mail`);

--
-- Index pour la table `ventes`
--
ALTER TABLE `ventes`
  ADD PRIMARY KEY (`idVente`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `idCategorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `idProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `ventes`
--
ALTER TABLE `ventes`
  MODIFY `idVente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD CONSTRAINT `adherents_ibfk_1` FOREIGN KEY (`idAdherent`) REFERENCES `utilisateurs` (`idUtilisateur`);

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_2` FOREIGN KEY (`idAdherent`) REFERENCES `adherents` (`idAdherent`),
  ADD CONSTRAINT `commandes_ibfk_3` FOREIGN KEY (`idVente`) REFERENCES `ventes` (`idVente`);

--
-- Contraintes pour la table `lignescommande`
--
ALTER TABLE `lignescommande`
  ADD CONSTRAINT `lignescommande_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`),
  ADD CONSTRAINT `lignescommande_ibfk_2` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`);

--
-- Contraintes pour la table `producteurs`
--
ALTER TABLE `producteurs`
  ADD CONSTRAINT `producteurs_ibfk_1` FOREIGN KEY (`idProducteur`) REFERENCES `utilisateurs` (`idUtilisateur`);

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `produits_ibfk_2` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`),
  ADD CONSTRAINT `produits_ibfk_3` FOREIGN KEY (`idProducteur`) REFERENCES `producteurs` (`idProducteur`);

--
-- Contraintes pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD CONSTRAINT `proposer_ibfk_1` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`),
  ADD CONSTRAINT `proposer_ibfk_2` FOREIGN KEY (`idVente`) REFERENCES `ventes` (`idVente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
