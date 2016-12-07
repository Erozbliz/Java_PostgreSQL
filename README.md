## Présentation
- Generate a JSON nested (Recursive function) from PostgreSQL in JAVA and display data in a sunburst D3.js.
- Client side : HTML/JS/CSS/BOOTSTRAP/JQuery/Ajax/D3.js/HTML5 storage
- Server side : JAVA/PostgreSQL/HTTP REST (GET)/Recursive function/CORS(*)

- Génération d'un JSON depuis PostgreSQL en JAVA et affichage des données dans un sunburst D3.js

## Prérequis
- Eclipse
- PostgreSQL / PGADMIN
- BDD test

## Autres
- Utilisation de D3.JS
- HTML / CSS / JQUERY
- JMerise
- http-20070405.jar
- postgresql-9.4.1211.jre.jar

## Installation
- Importer bdd_vx_script.sql (dossier "database")
- Inserer ALL_INSERT_bddvx.sql (dossier "database")
etc
- Bien vérifier que "jdbc:postgresql://localhost:5432/test", user, password" dans "SqlRequest.java" que la base données correspond

## Backup
C:\Program Files\PostgreSQL\9.6\bin>pg_dump.exe --file "F:/backup1.sql" --host "localhost" --port "5432" --username "postgres" --no-password --verbose --format=p --encoding "UTF8" "test"

## Informations complémentaires
- Get : 127.0.0.1:9999/getSunburst?parent=0   (active la génération du JSON)
- IHM : indexDataSeparate.html (lancer un serveur)

![alt tag](https://raw.githubusercontent.com/Erozbliz/Java_PostgreSQL/master/capture/IHM.JPG)

- Lancer un serveur dans le dossier "web_service" si jamais il y a des problèmes au niveau de la récupération (exemple: "http-server" en cmd (installation via npm "npm install -g http-server"))

