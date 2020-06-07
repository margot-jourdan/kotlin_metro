# Projet Kotlin - Navigation RATP
##### Margot JOURDAN - EPF

## Introduction
Ce projet est une application mobile pour Android réalisée en langage Koltin. 
Elle permet d'accéder aux lignes, stations et horaires du métro parisien.

Les données proviennent de l'API Rest : https://api-ratp.pierre-grimaud.fr/v4/
## Démarrage
Si l'application est lancée depuis Android Studio, il est necessaire de synchroniser le build graddle. Ensuite il suffit de lancer l'application sur un téléphone ayant le mode developpeur activé pour l'installer.
## Fonctionnement
Lors de la première ouverture de l'application, elle récupère les lignes et stations de métro et les sauvegarde dans la base de données. 

#### Page d'accueil
##### Barre supérieure
L'icone loupe permet d'accéder à une barre de recherche pour trouver une station à partir de son nom (incomplet ou non).
L'icone appareil photo permet de scanner un QR code représentant une station. 
![](http://www.image-heberg.fr/files/159152301360179823.png)

La page d'accueil liste les stations mises en favoris. Cliquer sur une station renvoie aux horaires de celle-ci.
La liste des lignes de métro en dessous fonctionne avec le même principe et redirige vers la liste des stations de la ligne selectionnée.

#### Liste des stations
![](http://www.image-heberg.fr/files/159152293816546010.png)

Les stations avec une étoile jaune sont celles déja mises en favoris. En cliquant sur l'étoile on peut l'enlever ou l'ajouter des favoris.
Cliquer sur une station renvoie aux horaires de celle-ci.

#### Recherche de station
![](http://www.image-heberg.fr/files/1591523070164937084.png)

Pour indiquer la ligne correspondant à chaque station on affiche son icone.
Cliquer sur une station renvoie aux horaires de celle-ci.

#### Horaires
![horaires](http://www.image-heberg.fr/files/15915231271910399870.png "horaires")
Sur cette page sont affichés les horaires pour les trains des différentes directions. Il est encore une fois possible d'ajouter ou de retirer un favoris sur la station.
L'icone en haut à droite permet de réactualiser les horaires.


## Exemples de QRcodes
<a rel='nofollow' href='https://fr.qr-code-generator.com/
            ' border='0' style='cursor:default'><img src='https://chart.googleapis.com/chart?cht=qr&chl=hotel%2Bde%2Bville&chs=180x180&choe=UTF-8&chld=L|2' alt=''></a>
Hotel de ville 

<a rel='nofollow' href='https://fr.qr-code-generator.com/
            ' border='0' style='cursor:default'></a><img src='https://chart.googleapis.com/chart?cht=qr&chl=bercy&chs=180x180&choe=UTF-8&chld=L|2' alt=''>
Bercy
 
 <img src='https://chart.googleapis.com/chart?cht=qr&chl=denfert%2Brochereau&chs=180x180&choe=UTF-8&chld=L|2' alt='qr code'><a href='https://fr.qr-code-generator.com/
            ' border='0' style='cursor:default'  rel='nofollow'></a>
 Denfert Rochereau 
## Améliorations possibles
* Etat du trafic
* Localisation GPS et stations les plus proches
* Quand on met on favoris depuis la liste des stations, ne pas recharger la page.
* Pouvoir accéder depuis la page d'accueil directement (nouvel onglet ?) aux horaires des stations (dans 1 sens ?)
* Choix des stations sur une carte
* Mise en forme des horaires pour mieux différencier les directions
* Mode sombre et apparence en général
* Réglages

## Souces
* Données :  https://api-ratp.pierre-grimaud.fr/v4/
* Lecteur de QR code : https://github.com/dm77/barcodescanner
* Générateur de QRCode : https://fr.qr-code-generator.com/
* Retrofit : https://square.github.io/retrofit/
