# test-driven-development

Dans ce TP, vous n'allez pas seulement écrire des tests : vous allez les écrire **avant** le code, et laisser les tests guider la conception. C'est le **TDD** (*Test Driven Development*).

```text
   ┌──────────┐      ┌──────────┐      ┌────────────┐
   │  ROUGE   │ ───▶ │   VERT   │ ───▶ │  REFACTOR  │ ──┐
   │ un test  │      │ le code  │      │  nettoyer  │   │
   │qui échoue│      │ minimal  │      │ tests verts│   │
   └──────────┘      └──────────┘      └────────────┘   │
        ▲                                               │
        └───────────────────────────────────────────────┘
```

## Ce que vous devez comprendre et savoir faire

- Enchaîner des cycles **rouge → vert → refactor** courts, sans jamais écrire de code qu'aucun test n'exige
- Laisser les tests faire émerger la conception (paramètres, objets, noms)
- Écrire un scénario d'acceptation (Cucumber) **avant** la fonctionnalité, puis la construire en TDD : la **double boucle**
- Prendre du recul : que devient le TDD quand une IA écrit le code, les tests… ou tout, en vibe coding ?

## Prérequis

| Outil | Version |
|---|---|
| Java (JDK) | 25 (LTS), à installer : Gradle ne le télécharge pas pour vous |
| Gradle | 9.8.0, fourni par le wrapper `./gradlew` |
| Spring Boot | 4.1.1 (partie 2, déjà configuré) |
| JUnit / AssertJ / Cucumber | 6.1.3 (kata ; le module Spring Boot utilise la version qu'il gère) / 3.27.7 / 7.34.6 |
| Un assistant IA | celui de votre choix (partie 3) |

## Démarrer

1. Créez un dépôt **vide** sur votre compte GitHub, puis :

```bash
git clone https://github.com/corentinbeuchet/test-driven-development.git
cd test-driven-development
git remote set-url origin https://github.com/<votre-compte>/test-driven-development.git
git push -u origin main
./gradlew test        # sous Windows : gradlew.bat test
```

2. Protégez `main` : Pull Request obligatoire et check `test` requis (le nom du job de la CI).

## Contenu

| Partie | Sujet | Durée indicative |
|---|---|---|
| [Partie 1](PARTIE1_Kata.md) | Kata : les pénalités de retard, cycle par cycle | 2 h 30 |
| [Partie 2](PARTIE2_Double_boucle.md) | Double boucle : Cucumber + TDD avec Spring Boot | 3 h |
| [Partie 3](PARTIE3_TDD_et_IA.md) | TDD et IA : vibe coding, tests d'abord, tests après | 2 h 30 |

## Comment votre travail est évalué

Le TDD se voit dans l'**historique Git**. Pour chaque cycle, faites un commit par étape :

| Étape | Préfixe du commit | État de la CI |
|---|---|---|
| Rouge | `test: …` | rouge (c'est normal !) |
| Vert | `feat: …` | vert |
| Refactor | `refactor: …` (seulement s'il y a quelque chose à nettoyer) | vert |

Un `git log --oneline` qui alterne `test:` / `feat:` / `refactor:` montre que vous avez vraiment travaillé en TDD. Un seul gros commit final ne le montre pas.

## Structure du projet

```text
.
├── kata/        # parties 1 et 3 : Java pur (fees, reservation, renewal)
└── emprunts/    # partie 2 : application Spring Boot + Cucumber
```
