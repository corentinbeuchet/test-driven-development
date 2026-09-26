package fr.library.renewal;

/**
 * Partie 3 : ce code a été écrit SANS tests.
 * Ne le corrigez pas tout de suite : lisez d'abord PARTIE3_TDD_et_IA.md.
 */
public class RenewalPolicy {

    public boolean canRenew(int renewalsSoFar, boolean overdue, boolean reservedByAnotherMember) {
        if (reservedByAnotherMember) {
            return false;
        }
        return renewalsSoFar <= 2;
    }
}
