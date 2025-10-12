# Interface Homme-Machine pour Robot mobile

![Projet universitaire](https://img.shields.io/badge/Université%20de%20Lorraine-Projet-lightgrey?logo=googlescholar)
![MBSE](https://img.shields.io/badge/Ingénierie%20des%20Exigences-Système-orange.svg)
![Programmation](https://img.shields.io/badge/Java-Programmation-blue.svg)
![DataBase](https://img.shields.io/badge/MariaDB-DataBase-green.svg)

**💡 Contexte :** SPAM - Sous-système de communication   
**🎯 Objectif :** Développer une interface interactive pour contrôler et programmer un robot mobile  
**🛠 Technologies :** Java  • MariaDB • Cameo Systems Modeler   
**👥 Auteur :** Jules Terlez   
**🏫 Université de Lorraine** – Projet universitaire - Master Ingénierie des Systèmes Complexes  

---

## ✨ Aperçu rapide

💡 **SPAM (Système de Production Autonome de Moteurs)** : Système composé de 5 sous-systèmes.  
  Ce projet a été réalisé en groupe. Ma partie porte sur le **sous-système de communication**.
  <details>
  <summary>Voir l'architecture du SPAM</summary>
  <br>
  <img src="assets/images/Architecture_SPAM.jpg" alt="Architecture_SPAM" width="750">
</details>
  
🎯 Le SPAM a pour objectif de produire de façon autonome des moteurs.  
Le **système de communication** doit :  
1. Contrôler et programmer les tâches d'un robot mobile. 
2. Gérer une base de données (actions, tâches, stocks)
  <details>
  <summary>Voir les missions du système de communication</summary>
  <br>
  <img src="assets/images/Missions_système_communication.jpg" alt="Missions_système_communication" width="750">
</details>

🛠 **Conception** du système de communication :
  1. Ingénierie des exigences  
  2. Création de la base de données
  3. Codage en Java

📊 Résultats [ici](lien)  

---

## Présentation finale
Voir/Télécharger le Powerpoint :  
- /assets/Sous-système de communication.pptx

---

## Visuels
![Interface Homme-Machine du Robot](assets/images/IHM.jpg)
<details>
  <summary>Voir détails</summary>
  <p align="center">
  <br>
  <img src="assets/images/Planification_Ordres_Fabrication.jpg" alt="Planification des Ordres de Fabrication" width="750"> <br>
  <em>Figure 1 - Planification des Ordres de Fabrication</em>
    <br> <br>
  <img src="assets/images/Transport_Articles.jpg" alt="Transport des Articles" width="750"> <br>
  <em>Figure 2 - Transport des Articles</em>
    <br> <br>
  <img src="assets/images/BDD_Stats.jpg" alt="BDD et Statistiques d'utilisation" width="750"> <br>
  <em>Figure 3 - BDD et Statistiques d'utilisation</em>
    <br> <br>
  <img src="assets/images/Gestion_Stocks.jpg" alt="Gestion des Stocks" width="750"> <br>
  <em>Figure 4 - Gestion des Stocks</em>
    <br> <br>
  <img src="assets/images/Mode_Maintenance.jpg" alt="Mode Maintenance" width="750"> <br>
  <em>Figure 5 - Mode Maintenance</em>
  </p>
</details>

---

## Compétences utilisées
- **Langage** : Java, SQL
- **Outils** : JavaFX (pour l'interface graphique), MariaDB (base de données), Cameo System Modeler

---

## Documentation
<details>
  <summary>En savoir plus sur la conception de l'IHM</summary>
  <p align="center">
  <br>
  <img src="assets/images/PFMS.jpg" alt="PFMS" width="750"> <br>
  <em>Figure 1 - PFMS</em>
        <br> <br>
  <img src="assets/images/Exigences.jpg" alt="Exigences" width="750"> <br>
  <em>Figure 2 - Exigences</em>
 </p>
</details>

---

## Exécuter le code
1. Identifiez-vous sur un **ordinateur de l'[AIPL](https://aip-primeca.univ-lorraine.fr/)**
2. Démarrez le **serveur** (demandez au professeur/référent du cours)
3. Suivez ce tutoriel [ici](lien)

4. 
5. Lancez l'application : Espace commun pédagogique > aipl > partages > M1ISC > admin > Modelisation& POO > PIDRappSansLive
6. Téléchargez le **jar externe** (/assets/mysql-connector-j-9.0.0.jar) et placez-le à la racine "O:" (Espace personnel)  
7. Téléchargez le projet et dézipper-le dans le dossier de votre choix : **Mon_dossier** (par défaut C:\Users\nom_utilisateur\Downloads)  
8. Ouvrez un IDE, de préférence **Eclipse**  
9. Dans Eclipse, cliquez Fichiers > Open Files from Files System > Mon_Dossier > src > POO > Sélectionner le dossier  
10. Chemin du jar  
11. Connectez-vous à Kapss'Ul, puis à MariaDB
12. Modifiez
13. Créez une nouvelle base de données Projet_POO_S7
14. Créez 3 nouvelles tables : 
