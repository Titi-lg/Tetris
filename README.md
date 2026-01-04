# 🎮 Tetris - Projet Java

Un jeu Tetris complet développé en Java avec Swing, offrant plusieurs modes de jeu et fonctionnalités avancées.

## 📋 Description

Ce projet est une implémentation complète du jeu classique Tetris avec une architecture orientée objet. Le jeu propose plusieurs modes de jeu : solo, versus (2 joueurs), coopératif, et un mode "Grand" avec deux joueurs sur une grille partagée.

## ✨ Fonctionnalités

### Modes de jeu
- **Mode Solo** : Jouez seul contre le temps
- **Mode Versus** : Affrontez un autre joueur en temps réel
- **Mode Coopératif** : Jouez en équipe avec un score combiné
- **Mode Grand** : Deux joueurs sur une grande grille partagée (25x20)

### Fonctionnalités principales
- 🎵 Musique de fond et effets sonores
- 📊 Système de score avec bonus pour les lignes multiples
- ⏸️ Pause du jeu
- 🎯 Aperçu de la prochaine pièce
- ⚡ Augmentation progressive de la vitesse selon le score
- 🎨 Interface graphique moderne avec Swing

### Types de pièces
Le jeu inclut les 7 pièces classiques du Tetris :
- **I** : Pièce droite (4 blocs)
- **J** : Pièce en L inversé
- **L** : Pièce en L
- **O** : Pièce carrée
- **S** : Pièce en S
- **T** : Pièce en T
- **Z** : Pièce en Z

## 🏗️ Structure du projet

```
TetrisProjet/
├── src/
│   ├── Controller/
│   │   └── KeyPadController.java      # Gestion des entrées clavier
│   ├── model/
│   │   ├── Board.java                 # Grille de jeu principale
│   │   ├── BoardGrand.java            # Grille pour le mode Grand
│   │   ├── TabOperation.java          # Opérations sur les matrices
│   │   └── bricks/
│   │       ├── Brick.java              # Classe abstraite des pièces
│   │       ├── BrickManager.java      # Gestionnaire de pièces
│   │       ├── IBrick.java            # Pièce I
│   │       ├── JBrick.java            # Pièce J
│   │       ├── LBrick.java            # Pièce L
│   │       ├── SBrick.java            # Pièce S
│   │       ├── SquareBrick.java       # Pièce O
│   │       ├── TBrick.java            # Pièce T
│   │       └── ZBrick.java            # Pièce Z
│   ├── View/
│   │   ├── TetrisFrame.java           # Fenêtre principale
│   │   ├── GrandTetrisFrame.java     # Fenêtre mode Grand
│   │   ├── TetrisCanva.java           # Canvas de rendu
│   │   ├── GrandCanva.java            # Canvas mode Grand
│   │   ├── NextPieceCanva.java       # Aperçu prochaine pièce
│   │   ├── ScorePanel.java            # Panneau de score
│   │   └── Themes.java                # Gestion audio
│   ├── assets/
│   │   ├── maintheme.wav              # Musique principale
│   │   ├── gameover.wav               # Son game over
│   │   └── Pause.jpg                  # Image pause
│   └── Test.java                      # Point d'entrée
└── out/                               # Fichiers compilés
```

## 📦 Prérequis

- **Java JDK** : Version 8 ou supérieure
- **IDE** : IntelliJ IDEA (recommandé) ou tout autre IDE Java
- **Système d'exploitation** : Windows, macOS, ou Linux

## 🎮 Utilisation

### Lancement
Exécutez la classe `Test.java` pour démarrer le jeu. La fenêtre principale s'ouvrira avec le mode solo par défaut.

### Contrôles

#### Joueur 1 (Mode Solo/Versus/Coop)
- **←** (Flèche gauche) : Déplacer la pièce vers la gauche
- **→** (Flèche droite) : Déplacer la pièce vers la droite
- **↓** (Flèche bas) : Faire descendre la pièce rapidement
- **↑** ou **Espace** : Rotationner la pièce
- **B** : Mettre en pause
- **P** : Activer le mode Versus
- **C** : Activer le mode Coopératif
- **G** : Activer le mode Grand
- **Échap** : Retour au mode solo

#### Joueur 2 (Mode Versus/Coop/Grand)
- **Q** : Déplacer vers la gauche
- **D** : Déplacer vers la droite
- **S** : Faire descendre rapidement
- **Z** : Rotationner la pièce

## 🎯 Règles du jeu

### Objectif
Empilez les pièces qui tombent pour former des lignes horizontales complètes. Chaque ligne complète est supprimée et vous rapporte des points.

### Système de score
- **Ligne simple** : 10 points
- **Lignes multiples** : Bonus multiplicatif
  - 2 lignes : 20 × 2 = 40 points
  - 3 lignes : 30 × 3 = 90 points
  - 4 lignes (Tetris) : 40 × 4 = 160 points

### Augmentation de la vitesse
La vitesse augmente progressivement selon votre score :
- Score < 50 : 500ms par descente
- Score ≥ 50 : 400ms
- Score ≥ 100 : 300ms
- Score ≥ 150 : 250ms
- Score ≥ 200 : 200ms
- Score ≥ 500 : 150ms

### Fin de partie
Le jeu se termine lorsque les pièces atteignent le haut de la grille et qu'une nouvelle pièce ne peut pas être placée.

## 🎲 Modes de jeu détaillés

### Mode Solo
- Grille standard : 20 lignes × 10 colonnes
- Un seul joueur
- Objectif : Obtenir le meilleur score

### Mode Versus
- Deux grilles côte à côte
- Chaque joueur a sa propre grille
- Le premier à perdre est le perdant
- Contrôles séparés pour chaque joueur

### Mode Coopératif
- Deux grilles côte à côte
- Score combiné des deux joueurs
- La partie se termine si l'un des deux joueurs perd
- Objectif : Maximiser le score total

### Mode Grand
- Une seule grande grille : 25 lignes × 20 colonnes
- Deux joueurs jouent simultanément
- Joueur 1 à droite, Joueur 2 à gauche
- Partie terminée si l'un des joueurs ne peut plus placer de pièce

## 🏛️ Architecture

Le projet suit une architecture MVC (Model-View-Controller) :

- **Model** : 
  - `Board` / `BoardGrand` : Logique métier du jeu
  - `Brick` et sous-classes : Représentation des pièces
  - `BrickManager` : Gestion de la génération des pièces
  - `TabOperation` : Opérations sur les matrices

- **View** :
  - `TetrisFrame` / `GrandTetrisFrame` : Fenêtres principales
  - `TetrisCanva` / `GrandCanva` : Rendu graphique
  - `ScorePanel` : Affichage du score
  - `Themes` : Gestion audio

- **Controller** :
  - `KeyPadController` : Gestion des entrées utilisateur

Le projet utilise le pattern Observer via `PropertyChangeSupport` pour la communication entre les composants.

## 🐛 Dépannage

### Le jeu ne démarre pas
- Vérifiez que Java JDK est installé : `java -version`
- Assurez-vous que les fichiers audio sont présents dans `src/assets/`

### Les sons ne fonctionnent pas
- Vérifiez que les fichiers `.wav` sont présents dans `src/assets/`
- Vérifiez que le chemin d'accès aux ressources est correct

### Problèmes de compilation
- Nettoyez le projet : Build → Clean Project
- Reconstruisez : Build → Rebuild Project

## 📝 Notes de développement

- Le projet utilise des threads pour la descente automatique des pièces
- La synchronisation est gérée via `synchronized` pour éviter les conditions de course
- Le pattern Observer permet une communication découplée entre les composants

## 👥 Auteur

Projet développé dans le cadre d'un projet académique.

## 📄 Licence

Ce projet est fourni à des fins éducatives.

---

**Amusez-vous bien ! 🎮**

