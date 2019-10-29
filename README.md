
--------------------------Notice d'utilisation : utilisateur----------------------------------

Bienvenue dans la notice d'utilisation du programme de découverte de combinaison.
Pour utiliser le programme, veuillez suivre les instructions ci-dessous :
1: Installez Maven sur votre ordinateur.
2: Créez un dossier où décompresser l'archive contenant le programme escape-polymorph-1.0-SNAPSHOT.jar
le programme se trouve dans le dossier Target, de l'archive.
3: Ouvrez un programme type terminal ( gitBash ou la console windows par exemple)
4: Rendez vous dans le dossier que vous avez créer à l'aide des commandes de navigation dans l'arborescence (cd, cd.., tab etc)
5: Tapez la commande maven : mvn clean package
Maven va "nettoyer" autour du package pour pouvoir le démarrer 
6: Tapez la commande suivante, en étant dans le dossier où se trouve le fichier : java -jar escape-polymorph-1.0-SNAPSHOT.JAR , puis entrée.

6:vous êtes dans le programme, amusez-vous !

une brève description des modes de jeu :

1. Mode Challenger : L'utilisateur cherche à découvrir la combinaison générée par l'ordinateur.
2. Mode défenseur : L'utilisateur choisi une combinaison que l'ordinateur tentera de découvrir, 
			        en fonction des informations données par l'utilisateur ( au dessus / en dessous / égale).
3.Mode Duel : Chacun leur tour, l'utilisateur et l'ordinateur feront une proposition pour tenter de découvrir
			  la combinaison de l'adversaire. Le premier à le faire est le vainqueur.
			  
Les modes 1 & 2 ont un nombre limite de tentatives ( par défaut = 8).
la longueur de combinaison est définie en début de mode de jeu ( par défaut = 4).
le mode développeur (cheatcode) peut-etre activé pour faire apparaître la combinaison à deviner (par defaut =off).			 


----------------------------Notice d'utilisation : développeur-------------------------------

bienvenue dans la notice d'utilisation du programme de découverte de combinaison, en tant que développeur.

1:rendez-vous sur le lien du repository contenant le programme ici pour le forker :
2:https://github.com/peasch/newpolymorph

3:cliquez sur "clone or download". copiez le lien proposé.

4:rendez-vous dans votre logiciel de programmation java (inteliJ, Eclipse, etc)
5:créez un nouveau projet
6:utilisez la fonction correspondant dans votre programme pour récupérer dans un controleur de version (checkout version control)
7:suivez les intructions du logiciel et collez le lien du repository forké
8:le logiciel va charger le projet.
9:lancez le programme en cliquant sur "run main"
10: le programme va se lancer, amusez-vous!

les fichiers de configuration par défaut, et de configuration log4j, se trouvent dans le dossier "ressources" du projet java.
le fichier log4j application, contient tous les critères de log qui sont enregistrés au cours de l'utilisation du jeu.
le fichier config contient les valeurs par défaut que l'on veut installer dans le jeu, taille de combinaison, 
nombre d'essai, ou mode developpeur On/Off.