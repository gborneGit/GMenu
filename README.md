<p align="center">
<img src="https://github.com/gborneGit/gborneGit/blob/main/EcoCraft_logo_transparent.png" width="100"/>
</p>

# Plugin Central pour EcoCraft Minecraft - Menu, téléportation animée et gestion de homes

## Description :

Le Plugin Central est un ajout crucial pour le serveur EcoCraft Minecraft, qui permet aux joueurs de profiter d'un menu convivial pour gérer leurs téléportations, homes et plus encore. Ce plugin inclut des fonctionnalités avancées pour améliorer l'expérience de jeu des joueurs et leur permettre de se concentrer sur l'exploration et la création, tout en limitant l'utilisation de commandes.

Le fichier de configuration comprend plusieurs options clés pour personnaliser le plugin selon les besoins du serveur, notamment :

* Désactivation de l'ouverture de la boussole pour les mondes spécifiques
* Définition de points de spawn pour chaque monde
* Délai de téléportation en secondes pour chaque téléportation
* Définition de mondes sans délai de téléportation
* Configuration des options de home, y compris le prix et le monde par défaut
* Le plugin offre également des téléportations animées pour une expérience plus immersive, et des messages personnalisés pour informer les joueurs des restrictions de téléportation. Avec le Plugin Central, les joueurs peuvent facilement naviguer entre les différents mondes du serveur, en utilisant un système convivial et intuitif qui ne nécessite pas de commandes complexes.

# Configuration :

* `disable_worlds` : Cette option permet de désactiver l'ouverture de la boussole pour les mondes spécifiques. Dans cet exemple, la boussole est désactivée pour le monde de l'arène.

* `spawn` : Cette option permet de définir un point de spawn pour chaque monde. Dans cet exemple, le point de spawn pour le monde safe est défini à 2,5 de coordonnée X, 107 de coordonnée Y et 56,5 de coordonnée Z avec une rotation de 180 et une inclinaison de 0.

* `survie`, `nether`, `ender` : Ces options permettent de définir des points de téléportation pour les différents mondes, avec une permission spécifique pour chaque monde. Dans cet exemple, un joueur doit avoir le groupe "fer1" pour être autorisé à se téléporter vers le monde nether, et doit être dans le groupe "or1" pour se téléporter vers le monde ender.

* `teleportation_delay` : Cette option permet de définir un délai de téléportation en secondes pour chaque téléportation.

* `worlds_without_delay` : Cette option permet de définir des mondes sans délai de téléportation.* 

* `home` : Cette option permet de configurer les options de home, y compris le prix et le monde par défaut. Dans cet exemple, le prix pour définir un home est de 50 et le monde par défaut pour les homes est le monde normal.
