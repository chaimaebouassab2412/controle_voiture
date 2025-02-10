-- Ajout de données dans la table des utilisateurs
INSERT INTO utilisateur (id_utilisateur, nom, email, mot_de_passe)
VALUES
(1, 'Youssef', 'youssef@example.com', 'password123'),
(2, 'Amina', 'amina@example.com', 'securepassword');


-- Ajout de données dans la table des véhicules
-- La colonne `proprietaire_id` fait référence à `id_utilisateur` dans la table `utilisateur`
INSERT INTO vehicule (id, marque, modele, numero_chassis, type_carburant, nombre_places, poids_a_vide, capacite_reservoir, puissance, date_achat, kilometrage, couleur, statut, proprietaire_id)
VALUES
(1, 'Toyota', 'Corolla', '123456', 'Essence', 5, 1200, 50, 150, '2023-01-01', 10000, 'Noir', 'Actif', 1), -- Youssef
(2, 'BMW', 'X5', '789012', 'Diesel', 5, 2000, 70, 300, '2022-06-15', 20000, 'Blanc', 'Inactif', 2);     -- Amina


-- Ajout de données dans la table des assurances
-- Vous pouvez également ajouter une relation entre `assurance` et `vehicule` si nécessaire
INSERT INTO assurance (id, compagnie, date_debut, date_fin, montant, check_validity)
VALUES
(1, 'AXA', '2023-01-01', '2024-01-01', 1500.00, true),
(2, 'Wafa Assurance', '2023-05-01', '2024-05-01', 2000.00, true);
