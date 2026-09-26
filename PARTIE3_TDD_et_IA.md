# Partie 3 — TDD et IA : qui écrit les tests ?

⏱️ Durée indicative : 1 h 30

Le TDD est une bonne pratique reconnue. Mais aujourd'hui, un assistant IA écrit du code **et** des tests en quelques secondes. On risque de ne plus écrire les tests, seulement de les **lire** (vite), et de valider ce qui passe au vert.

Cette partie vous fait vivre les deux usages, puis vous demande votre avis argumenté. Il n'y a pas de « bonne réponse » attendue, mais une réponse **argumentée par ce que vous avez observé**.

## Expérience A : vos tests, le code de l'IA

Nouvelle règle du kata :

> Un abonné étudiant (`MemberType.STUDENT`) paie la moitié de la pénalité, plafond compris : 3 jours de retard → 75 centimes, 30 jours → 500 centimes.

1. Écrivez **vous-même** les tests de cette règle (rouge). Commit `test: …`.
2. Donnez **uniquement vos tests** à l'IA et demandez-lui le code qui les fait passer. Commit `feat: …` en indiquant dans le message que le code vient de l'IA.
3. Relisez le code produit. Est-il correct ? Plus compliqué que nécessaire ? Vos tests suffisaient-ils à le guider ?

## Expérience B : votre code, les tests de l'IA

Le module `kata` contient `fr.library.renewal.RenewalPolicy`, écrit **sans tests**. La règle métier est la suivante :

> Un emprunt peut être prolongé si **toutes** ces conditions sont vraies : le livre n'est **pas en retard**, il a été prolongé **moins de 2 fois**, et **aucun autre abonné** ne l'a réservé.

1. **Sans lire la règle ci-dessus**, donnez le code de `RenewalPolicy` à l'IA et demandez-lui d'écrire les tests. Ajoutez-les, lancez-les. Commit `test: tests générés par l'IA`.
2. Les tests passent-ils ? Maintenant, comparez-les à la règle métier. Que remarquez-vous ?
3. Écrivez vous-même les tests qui découlent de la règle, corrigez le code (cycles rouge → vert).

## À rendre : `REPONSES_IA.md` dans votre Pull Request

Répondez en quelques lignes, en vous appuyant sur ce que vous avez observé :

1. Dans l'expérience B, les tests générés ont-ils trouvé les bugs ? Pourquoi ?
2. Dans chaque expérience, **qui** a écrit la spécification : vous, l'IA, ou personne ?
3. Qu'avez-vous réellement **lu** dans les tests de l'IA ? Auriez-vous vu le problème sans la règle écrite ?
4. Votre avis : avec l'IA, le TDD est-il **plus** utile (les tests deviennent la spécification que l'on donne à l'IA), **moins** utile (on lit au lieu d'écrire), ou en train de changer de forme ? Argumentez.

## ✅ Terminé quand…

- [ ] La règle étudiant est testée par vos tests et implémentée
- [ ] `RenewalPolicy` respecte la règle métier, prouvée par vos tests
- [ ] `REPONSES_IA.md` répond aux 4 questions
