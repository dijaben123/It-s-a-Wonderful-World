# It's a Wonderful World

_`Début du projet le 25/09/2023`_

🎲
Reproduction du jeu de plateau It's a Wonderful World en Java dans le cadre d'un projet de développement tutoré
en L3 MIASH à l'Université Nice Côte d'azure.

## Installation

Télecharger le projet dans sa dernière version

## Démarrage
Pour lancer le projet executer les commandes suivantes à la racine du projet :

``` sh
cd iww5
mvn clean install
```

### Sans arguments :
``` sh
 mvn exec:java
 ```

### Avec arguments :

[^1]:(remplacer le nombre **3** par le nombre de parties que vous voulez lancer)

Choix du nombre de joueurs:[^1]
``` sh
 mvn exec:java -Dexec.args=3
 ```

Choix du nombre de joueurs et du nombre de parties:
``` sh
 mvn exec:java -Dexec.args="3 4"
 ```
## Statistiques

A chaque fin d'exécution du programme un fichier Excel se nommant Resultat avec les statistiques de la ou des parties lancées est crées et stocké à la racine du fichier iww5.

## Affichage

### Une partie
l'affichage entier de la partie se fera dans la console.

### Plusieurs parties
Seule le nombre de victoire de chaque joueur sera affiché dans la console.

## Fonctionnalités

Les stats de tout les joueurs sont enregistrées dans un fichier excel **Resultat.xlsx**.

Tout les détails de la derniere partie lancée sont enregistrées dans un fichier **logsPartie.txt**

## Tests
Commande pour lancer les tests
 ``` sh
mvn test
 ```


## Documentation
Commande pour générer la documentation
Générer ou mettre à jour java doc
 ``` sh
mvn javadoc:javadoc
 ```

Le client à fait une demande d'évolution qui à été prise en compte de le découpage et dans le fichier prisesEnCompteDesChangements.md

Le lien vers la documention se trouve dans iww5\target\site\apidocs\index.html

## Dépendances utilisées

- [Maven](https://maven.apache.org/) - Dependency Management
- [Junit](https://junit.org/junit5/) - Framework de test
- [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core) - Librairie Json



![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)
