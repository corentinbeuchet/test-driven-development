# Partie 1 — Kata : les pénalités de retard

⏱️ Durée indicative : 2 h 30

Un **kata** est un petit exercice que l'on répète pour acquérir un geste. Ici, le geste est le cycle **rouge → vert → refactor**.

La bibliothèque veut calculer la pénalité d'un livre rendu en retard. Les règles vont arriver **une par une**, comme dans un vrai projet : ne lisez pas l'étape suivante avant d'avoir terminé la précédente.

## Mise en place

- Travaillez sur une branche `kata` et ouvrez une Pull Request vers `main` à la fin.
- Tout se passe dans le module `kata`, package `fr.library.fees`.
- Les montants sont en **centimes** (`int`), jamais en `double` : `0.1 + 0.2` ne vaut pas `0.3` en `double`.
- Lancer les tests : `./gradlew :kata:test`

## Les trois règles du TDD

1. N'écrivez **aucun code de production** sans un test qui échoue.
2. N'écrivez **que ce qu'il faut** de test pour échouer (une erreur de compilation est un échec).
3. N'écrivez **que ce qu'il faut** de code pour faire passer le test.

## Étape 1 — Rendu à temps

> Un livre rendu à la date prévue, ou avant, ne coûte rien.

Écrivez le premier test dans `kata/src/test/java/fr/library/fees/LateFeeCalculatorTest.java`. La classe `LateFeeCalculator` n'existe pas encore : c'est le test qui va la faire naître.

```java
@Test
void noFeeWhenReturnedOnTime() {
    LateFeeCalculator calculator = new LateFeeCalculator();

    int fee = calculator.feeInCents(LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 1));

    assertThat(fee).isZero();
}
```

- 🔴 Le test ne compile pas : `test: rendu à temps, pas de pénalité`
- 🟢 Créez la classe et la méthode. Le code le plus simple qui passe est… `return 0;`. Oui, vraiment. `feat: …`
- 🔵 Rien à nettoyer ? Passez directement à l'étape suivante.

## Étape 2 — Chaque jour de retard coûte 0,50 €

> 1 jour de retard → 50 centimes, 3 jours → 150 centimes.

Le `return 0;` ne tient plus : c'est le test qui vous force à écrire le vrai calcul (`ChronoUnit.DAYS.between`).

💡 Un test paramétré (`@ParameterizedTest` + `@CsvSource`) évite de copier-coller le même test pour 1, 2, 3 jours.

## Étape 3 — Plafond de 10 €

> Quel que soit le retard, la pénalité ne dépasse jamais 1 000 centimes.

Testez **la frontière** : 19 jours (950), 20 jours (1 000), 21 jours (toujours 1 000).

## Étape 4 — Les abonnés premium ont 3 jours de grâce

> Pour un abonné premium, les 3 premiers jours de retard sont gratuits : 4 jours de retard → 50 centimes.

Il faut maintenant savoir qui rend le livre : ajoutez un paramètre `MemberType memberType` (`STANDARD`, `PREMIUM`).

🔵 Refactor : vos anciens tests ne compilent plus. Mettez-les à jour (`MemberType.STANDARD`) : c'est le prix, et l'intérêt, d'une conception qui évolue.

## Étape 5 — Les nouveautés coûtent le double

> Un livre de la catégorie nouveauté coûte 1 € par jour de retard. Le plafond reste de 10 €, et les 3 jours de grâce des abonnés premium s'appliquent aussi aux nouveautés.

Ajoutez `BookCategory category` (`STANDARD`, `NEW_RELEASE`).

🔵 Refactor obligatoire : `feeInCents(dueDate, returnDate, memberType, category)` a trop de paramètres. Regroupez ce qui décrit l'emprunt dans un record :

```java
public record Loan(LocalDate dueDate, MemberType memberType, BookCategory category) { }

public int feeInCents(Loan loan, LocalDate returnDate)
```

Les tests doivent rester verts pendant **tout** le refactor.

## Étape 6 — Une date de retour absente est une erreur

> `feeInCents(loan, null)` lève une `IllegalArgumentException`.

- 💡 `assertThatThrownBy(() -> calculator.feeInCents(loan, null)).isInstanceOf(IllegalArgumentException.class).hasMessage("La date de retour est obligatoire")` : vérifiez aussi le message, il aide celui qui lira les logs.
- 💡 Et une date de retour **avant** l'échéance ? Ce n'est pas une erreur (livre rendu en avance) : vous l'avez déjà couvert à l'étape 1 ou 2 ? Si non, c'est le moment d'écrire ce test.

## ✅ Terminé quand…

- [ ] Les 6 règles sont couvertes par des tests, frontières comprises (0, 1, 20, 21 jours…)
- [ ] `git log --oneline` montre les cycles `test:` → `feat:` → `refactor:`
- [ ] Aucune ligne de `LateFeeCalculator` n'existe sans un test qui l'a exigée
- [ ] La CI est verte sur la Pull Request
