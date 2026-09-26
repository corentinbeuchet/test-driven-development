# Partie 2 — La double boucle : Cucumber + TDD

⏱️ Durée indicative : 3 h

Le kata testait une classe isolée. Dans une vraie application, on part d'un **besoin métier** : c'est la **double boucle**.

```text
 Boucle externe (acceptation)          Boucle interne (unitaire)
 ┌────────────────────────────┐        ┌───────────────────────┐
 │ 1. scénario Cucumber ROUGE │ ─────▶ │rouge → vert → refactor│ ◀─┐
 │                            │        └───────────┬───────────┘   │
 │ 3. scénario VERT → suivant │ ◀── 2. assez de code ? ── non ─────┘
 └────────────────────────────┘
```

1. Le scénario d'acceptation échoue (boucle externe rouge).
2. Vous construisez le code nécessaire en TDD, test unitaire par test unitaire (boucle interne).
3. Quand le scénario passe, vous passez au suivant.

## Le besoin

Le fichier `emprunts/src/test/resources/features/emprunter-un-livre.feature` décrit, en français, ce que la bibliothèque attend :

```gherkin
Scénario: un abonné emprunte un livre disponible
  Étant donné le livre "9780132350884" disponible
  Quand "alice" emprunte le livre "9780132350884"
  Alors l'emprunt est accepté
  Et le livre "9780132350884" n'est plus disponible
```

Ce fichier est écrit **avec** le métier : il sert à la fois de spécification, de documentation et de test.

## Déroulé

Travaillez sur une branche `emprunts`, module `emprunts`, package `fr.library.loans`.

### 1. Boucle externe rouge

- Retirez le tag `@wip` du **premier** scénario (un seul à la fois) et lancez `./gradlew :emprunts:test`.
- Cucumber indique que les étapes ne sont pas définies et **propose leur squelette**. Copiez-les dans `LoanSteps.java`.
- Écrivez les étapes :
  - `Étant donné` prépare l'état (ajouter un livre au catalogue, créer des emprunts existants) ;
  - `Quand` appelle l'API : `POST /loans` avec `{"isbn": "…", "member": "…"}`, grâce à `MockMvc` ;
  - `Alors` vérifie la réponse : `201` si l'emprunt est accepté ; `409` ou `404` sinon, avec le motif dans le champ `detail` (`ProblemDetail`).
- Commit : `test: scénario d'emprunt d'un livre disponible` : **la CI est rouge**, c'est attendu.

### 2. Boucle interne

Le scénario échoue parce que rien n'existe. Construisez en TDD, dans `src/test/java/fr/library/loans/LoanServiceTest.java`, un `LoanService` qui :

| Règle | Motif |
|---|---|
| emprunte un livre disponible | |
| refuse un livre déjà emprunté | `Livre déjà emprunté` |
| refuse un 4ᵉ emprunt en cours pour un même abonné | `Trop d'emprunts en cours` |
| refuse un livre absent du catalogue | `Livre inconnu` |

Un cycle rouge → vert → refactor par règle, avec ses commits. Pas besoin de base de données : une `Map` en mémoire suffit.

### 3. Boucle externe verte

Ajoutez le controller (`POST /loans`) et un `@RestControllerAdvice` qui transforme les exceptions en `409` / `404`. Relancez : le scénario passe, la CI redevient verte. Commit `feat: …`, puis retirez le `@wip` du scénario suivant.

💡 Le contexte Spring est partagé entre les scénarios : videz l'état en mémoire dans une méthode annotée `@io.cucumber.java.Before` (hook Cucumber : un `@BeforeEach` JUnit ne s'exécute pas dans les classes d'étapes) pour que chaque scénario reparte de zéro.

## ✅ Terminé quand…

- [ ] Les 4 scénarios passent, sans aucun tag `@wip`
- [ ] Chaque règle de `LoanService` a été écrite en TDD (tests unitaires rapides, sans Spring)
- [ ] L'historique montre la boucle externe rouge **avant** la boucle interne
- [ ] La CI est verte sur la Pull Request
