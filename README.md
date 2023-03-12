# GeoHunt

## Description
Un projet d'application mobile de type chasse au trésor (scavenger hunt) fait dans le cadre d'un cours d'application internet.

## Architecture
### Serveur
NodeJs et ExpressJs sont utilisés pour le serveur. Trois modèles d’objets sont utilisés. 
Le premier est la classe User composée d’un nom d’utilisateur et d’un score. 
La deuxième classe est Repere qui correspond aux coordonnées géographiques et qui sont composées d’un identifiant unique, d’une latitude, d’une longitude et d’un objet Texte. 
La troisième classe est la classe Texte qui est composée d’un texte et d’une réponse. 
Les données sont conservées dans des fichiers json qui se trouvent le répertoire json. User.json contient les informations des utilisateurs, et reperes.json contient les informations sur les points. 
Les routes disponibles sont les suivantes : 
- /user 
	o Méthode : POST 
	o Message JSON : 
		 { username : nom de l’utilisateur } 
	o Description : Permet à l’utilisateur de s’authentifier. Dans le cas où le nom d’utilisateur n’existe pas, le compte est créé avec un score à 0. 
- /user/ :user 
	o Méthode : GET 
	o Description : Retourne le score de l’utilisateur spécifié. 
- /user/ :user 
	o Méthode : PUT 
	o Message JSON : { score : nouveau score } 
	o Description : Met à jour le score de l’utilisateur spécifié. 
- /repere 
	o Méthode : GET 
	o Description : Retourne la liste des coordonnées où il y a une énigme. 
- /repere 
	o Méthode : POST 
	o Message JSON : { latitude : point, longitude : point, texte : { texte : text, reponse : reponse } } 
	o Description : Permet de créer un nouveau point et y associer une question et une réponse. 
- /repere/ :id 
	o Méthode : GET 
	o Description : Permet de récupérer la question d’un point 
- /repere/ :id 
	o Méthode : POST 
	o Message JSON : { reponse : reponse } 
	o Description : Permet d’envoyer la réponse associée au point spécifié.

### Client
Le client Android utilise retrofit pour communiquer par requête avec le serveur. Des modèles sont utilisés pour récupérer les informations des coordonnées (CacheList), les informations d’un point (Cache), les informations d’un texte (Texte) et les informations de l’utilisateur (User). 
Le client est composé de 4 activités. 
La première est LoginActivity qui correspond à la page de connexion où il est possible de saisir un nom d’utilisateur. 
MainActivity correspond à la page principale de l’application où il est possible de voir la carte et les points disponibles via l’API de Google. 
À partir de cette activité, CachesActivity est accessible. Cette activité permet de récupérer les informations d’un point près de nous et d’y envoyer notre réponse. 
L’activity UserActivity est accessible à partir de MainActivity et correspond à la page de profile de l’utilisateur où son score est 
affiché. 

