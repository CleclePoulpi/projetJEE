# Rapport technique

## Spring

Pour la mise en place de notre projet, nous avons choisi d'utiliser le framework Spring, et plus précisément Spring Boot. En effet, ce framework est particuliérement adaptée à la mise en place du application web utilisant une API REST. 

Le principe de Spring Boot est de séparer les méthodes entre diverses classes : 
  - Les controlleurs qui vont recevoir les requetes et appeler les fonctions correspondantes.
  - Les services qui vont posséder des méthodes visant a répondre aux demandes des controlleurs
  - Les entitées qui représente toutes les classes qui vont être la base de l'application
  - Les repository qui contiennent toutes les instances de chaque entitées. Elle communiquent directement avec la base de donnée grâce a Hibernate.

La principale force de Spring Boot est l'automatisation des liens entre les controlleurs, les services et les repositorys, permettant de grandement simplifier la mise en place du serveur.

Hibernate s'occupe de générer dans la base MySql les tables dont nous avons besoins, il génère également une table seq pour chacune, permettant de générer automatiquement les IDs des entitées qui se trouvent dans la base.


![schema](Fig-2-Architecture-flow-of-spring-boot-Applications-Spring-boot-uses-all-the-features.png)
