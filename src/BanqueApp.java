import banque.Compte;
import banque.CompteCourant;
import banque.CompteEpargne;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BanqueApp {
    private static Map<String, Compte> comptes = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireChoix();
            try {
                switch (choix) {
                    case 1:
                        creerCompte();
                        break;
                    case 2:
                        effectuerVersement();
                        break;
                    case 3:
                        effectuerRetrait();
                        break;
                    case 4:
                        effectuerVirement();
                        break;
                    case 5:
                        consulterSolde();
                        break;
                    case 6:
                        consulterOperations();
                        break;
                    case 0:
                        continuer = false;
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Option invalide !");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }

            System.out.println("\nAppuyez sur Entrée pour continuer...");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n===== MENU BANQUE =====");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter solde");
        System.out.println("6. Consulter opérations");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static int lireChoix() {
        try {
            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer
            return choix;
        } catch (Exception e) {
            scanner.nextLine(); // Vider le buffer en cas d'erreur
            return -1;
        }
    }

    private static void creerCompte() {
        System.out.println("\n=== CRÉATION DE COMPTE ===");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        System.out.print("Type de compte : ");

        int type = lireChoix();

        System.out.print("Solde initial : ");
        double solde = scanner.nextDouble();
        scanner.nextLine(); // Vider le buffer

        try {
            Compte compte;
            if (type == 1) {
                compte = new CompteCourant("CPT-12345", solde);
                System.out.println("Compte courant créé avec succès !");
            } else if (type == 2) {
                compte = new CompteEpargne("CPT-54321", solde);
                System.out.println("Compte épargne créé avec succès !");
            } else {
                System.out.println("Type de compte invalide !");
                return;
            }

            // Utiliser le code généré automatiquement pour stocker le compte
            comptes.put(compte.getCode(), compte);
            System.out.println("Numéro de compte attribué : " + compte.getCode());
            compte.afficherDetails();

        } catch (Exception e) {
            System.out.println("Erreur lors de la création : " + e.getMessage());
        }
    }

    private static void effectuerVersement() {
        System.out.println("\n=== VERSEMENT ===");
        Compte compte = rechercherCompte();

        if (compte != null) {
            System.out.print("Montant : ");
            double montant = scanner.nextDouble();
            scanner.nextLine(); // Vider le buffer

            System.out.print("Source (ex: Salaire, Dépôt) : ");
            String source = scanner.nextLine();

            try {
                compte.verser(montant, source);
                System.out.println("Versement effectué avec succès !");
                compte.afficherDetails();
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void effectuerRetrait() {
        System.out.println("\n=== RETRAIT ===");
        Compte compte = rechercherCompte();

        if (compte != null) {
            System.out.print("Montant : ");
            double montant = scanner.nextDouble();
            scanner.nextLine(); // Vider le buffer

            System.out.print("Destination (ex: ATM, Chèque) : ");
            String destination = scanner.nextLine();

            try {
                compte.retirer(montant, destination);
                System.out.println("Retrait effectué avec succès !");
                compte.afficherDetails();
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void effectuerVirement() {
        System.out.println("\n=== VIREMENT ===");
        System.out.println("Compte source :");
        Compte source = rechercherCompte();

        if (source == null) return;

        System.out.println("Compte destination :");
        Compte destination = rechercherCompte();

        if (destination == null) return;

        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        scanner.nextLine(); // Vider le buffer

        try {
            source.retirer(montant, "Virement vers " + destination.getCode());
            destination.verser(montant, "Virement depuis " + source.getCode());
            System.out.println("Virement effectué avec succès !");
            source.afficherDetails();
            destination.afficherDetails();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void consulterSolde() {
        System.out.println("\n=== CONSULTATION SOLDE ===");
        Compte compte = rechercherCompte();

        if (compte != null) {
            compte.afficherDetails();
            System.out.println("Intérêts calculés : " + compte.calculerInteret());
        }
    }

    private static void consulterOperations() {
        System.out.println("\n=== CONSULTATION OPÉRATIONS ===");
        Compte compte = rechercherCompte();

        if (compte != null && !compte.getListeOperations().isEmpty()) {
            System.out.println("Opérations du compte " + compte.getCode() + " :");
            compte.getListeOperations().forEach(op -> {
                String type = op instanceof operations.Versement ? "Versement" : "Retrait";
                System.out.println(type + " - Date: " + op.getDate() +
                        ", Montant: " + op.getMontant());
            });
        } else if (compte != null) {
            System.out.println("Aucune opération sur ce compte.");
        }
    }

    private static Compte rechercherCompte() {
        System.out.print("Code du compte : ");
        String code = scanner.nextLine();

        Compte compte = comptes.get(code);
        if (compte == null) {
            System.out.println("Compte non trouvé !");
        }
        return compte;
    }
}
