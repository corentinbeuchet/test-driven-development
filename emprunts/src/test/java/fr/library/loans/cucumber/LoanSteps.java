package fr.library.loans.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

// Partie 2 : écrivez ici les étapes (steps) du fichier emprunter-un-livre.feature.
// Lancez ./gradlew :emprunts:test après avoir retiré @wip : Cucumber vous propose le squelette de chaque étape.
public class LoanSteps {

    @Autowired
    private MockMvc mockMvc;
}
