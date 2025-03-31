# 🌟 StarfleetReservation - Système de Gestion Spatiale

## 📖 Introduction

Salut ! 👋 Je suis ravi de te présenter **StarfleetReservation**, un programme que j'ai développé en Java pour gérer une flotte spatiale. Imagine que tu commandes ta propre flotte de vaisseaux Star Trek - c'est exactement ce que ce programme te permet de faire !

## 🎯 A quoi ça sert ?

Ce programme te permet de :
- Gérer tes vaisseaux spatiaux 🚀 (ajouter, modifier, supprimer)
- Organiser ton équipage 👨‍🚀👩‍🚀 (officiers et passagers civils)
- Planifier des missions 🌍➡️🪐 (dates, destinations)
- Faire des réservations de voyage ✨

## 🛠️ Comment l'installer ?

### Ce dont tu as besoin :
1. **Java** installé sur ton ordinateur (version 11 ou plus)
2. Un terminal (cmd sur Windows, Terminal sur Mac/Linux)

### Étapes simples :
1. Télécharge tous les fichiers du projet
2. Ouvre ton terminal dans le dossier du projet
3. Compile avec :
   ```bash
   javac -d out src/strfleet/*.java src/strfleet/modele/**/*.java src/strfleet/systeme/*.java
   ```

## 🚀 Comment l'utiliser ?

### Version interactive (avec menus) :
```bash
java -cp out strfleet.InterfaceControl
```

Tu verras un menu simple avec des numéros. Tape le numéro de ce que tu veux faire et suis les instructions !

### Version de test automatique :
```bash
java -cp out strfleet.Main
```
Cette version fait tout toute seule pour te montrer comment ça marche.

## 💡 Fonctionnalités cool

1. **Pour les vaisseaux** :
   - Tu peux donner un nom cool à ton vaisseau (comme "USS Enterprise")
   - Lui donner une immatriculation ("NCC-1701")
   - Choisir combien de passagers il peut transporter

2. **Pour l'équipage** :
   - Enregistrer les officiers (avec leur rang et spécialité)
   - Ajouter des civils (tu peux noter leur planète d'origine)
   
3. **Pour les missions** :
   - Choisir la destination (Vulcain, Terre, Risa...)
   - Définir les dates de voyage
   - Associer un vaisseau à la mission

4. **Réservations** :
   - Réserver des places pour tes passagers
   - Annuler des réservations si besoin

## 🔄 Sauvegarde automatique

Toutes tes données sont sauvegardées dans un fichier `starfleet_data.dat`. Quand tu relances le programme, tout revient comme avant !

## 🧑‍💻 Pour les développeurs

Si tu veux comprendre comment c'est fait :
- Le code est bien organisé en différentes classes
- Il y a des commentaires pour t'aider
- Tu peux facilement ajouter de nouvelles fonctionnalités



Amuse-toi bien avec ton système de gestion spatiale ! 🚀👨‍🚀