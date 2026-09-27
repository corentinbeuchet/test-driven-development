package fr.library.reservation;

import java.util.List;
import java.util.Optional;

/**
 * Partie 3 : les réservations de livres.
 * Les règles métier sont dans PARTIE3_TDD_et_IA.md (le « ticket »).
 * L'implémentation attendue s'appelle InMemoryReservationService,
 * dans ce package, avec un constructeur sans paramètre.
 */
public interface ReservationService {

    /** Le système d'emprunt signale qu'un abonné vient d'emprunter un livre. */
    void markBorrowed(String isbn, String member);

    /** Le livre est rendu à la bibliothèque. */
    void markReturned(String isbn);

    /** L'abonné qui a actuellement le livre, s'il est emprunté. */
    Optional<String> borrowerOf(String isbn);

    /** Réserve un livre. Lève ReservationRefusedException si une règle l'interdit. */
    void reserve(String isbn, String member);

    /** Annule une réservation. Lève ReservationRefusedException si elle n'existe pas. */
    void cancel(String isbn, String member);

    /** La file d'attente du livre, dans l'ordre des réservations. */
    List<String> queue(String isbn);
}
