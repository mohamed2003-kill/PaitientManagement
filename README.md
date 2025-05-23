# Application de Gestion des Patients

Ce document décrit le **fonctionnement** de l’application web de gestion des patients, sans aborder l’installation ni le déploiement. Il couvre les deux premières parties :

1. Gestion des patients (affichage, pagination, recherche, suppression, création)
2. Template de page et validation des formulaires

---

## 1. Gestion des patients

L’application permet de gérer un annuaire de patients via une interface web intuitive basée sur Spring MVC, Thymeleaf et Spring Data JPA.

### 1.1 Affichage de la liste

* La page principale affiche tous les patients sous forme de tableau.
* Chaque entrée montre l’ID, le prénom, le nom et l’email.

### 1.2 Recherche de patients

* Un champ de recherche permet de filtrer la liste par prénom ou nom (contient, insensible à la casse).
* Appuyez sur la loupe ou la touche Entrée pour lancer la recherche.

![Recherche de patients](./images/filter patient.PNG)

### 1.3 Pagination

* La liste est paginée pour améliorer la lisibilité.
* Contrôles « Précédent » et « Suivant » ainsi que numéros de pages pour naviguer.

![Pagination](./images/pagination.PNG)

### 1.4 Ajout d’un patient

* Cliquez sur le bouton **Nouveau Patient** pour accéder au formulaire de création.

![Ajout d'un patient]("./images/add new patient.PNG")

### 1.5 Suppression d’un patient

* Chaque ligne de patient comporte un bouton **Supprimer**.
* Une boîte de confirmation s’affiche avant la suppression.

---

## 2. Template de page & validation des formulaires

### 2.1 Template Thymeleaf (fragments)

* Un **header** et un **footer** sont définis dans des fragments Thymeleaf réutilisables.
* Chaque page (liste, formulaire) inclut ces fragments pour l’en-tête et le pied de page.

### 2.2 Validation des formulaires

* Le formulaire utilise les annotations Bean Validation (`@NotBlank`, `@Email`).
* En cas d’erreur (champ vide, format d’email invalide), les messages sont affichés sous chaque champ.

![Validation du formulaire](./images/form validation.PNG)

---

> Cette architecture vous permet de gérer facilement les patients, d’assurer une cohérence de l’interface via des templates, et de garantir l’intégrité des données grâce à la validation serveur.
