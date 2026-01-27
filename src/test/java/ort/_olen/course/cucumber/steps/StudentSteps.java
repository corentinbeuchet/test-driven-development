package ort._olen.course.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import ort._olen.course.configuration.CucumberSpringConfiguration;
import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentSteps extends CucumberSpringConfiguration {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<StudentDTO> lastResponse;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setupSql() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("datasets/repository/student-before.sql"));
        populator.execute(dataSource);
    }

    @After
    public void tearDownSql() {
        ResourceDatabasePopulator cleaner = new ResourceDatabasePopulator();
        cleaner.addScript(new ClassPathResource("datasets/repository/student-after.sql"));
        cleaner.execute(dataSource);
    }

    @When("I create a student with name {string} and surname {string}")
    public void iCreateAStudent(String name, String surname) {
        StudentSaveDTO newStudent = new StudentSaveDTO(name, surname, LocalDate.of(2000, 1, 1));
        lastResponse = restTemplate.postForEntity("/api/students", newStudent, StudentDTO.class);
    }

    @Then("the student should be successfully created")
    public void studentShouldBeSuccessfullyCreated() {
        assertThat(lastResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(lastResponse.getBody()).isNotNull();
        assertThat(lastResponse.getBody().id()).isNotNull();
    }

    @Then("I should be able to retrieve the student with name {string} and surname {string} by its ID")
    public void iShouldBeAbleToRetrieveTheStudent(String expectedName, String expectedSurname) {
        Long id = lastResponse.getBody().id();
        ResponseEntity<StudentDTO> response = restTemplate.getForEntity("/api/students/" + id, StudentDTO.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().name()).isEqualTo(expectedName);
        assertThat(response.getBody().surname()).isEqualTo(expectedSurname);
    }

    @Given("there are {int} students in the system")
    public void thereAreStudentsInTheSystem(int expectedCount) {
        Set<?> students = restTemplate.getForObject("/api/students", Set.class);
        assertThat(students).hasSize(expectedCount);
    }

    @When("I delete the student with ID {int}")
    public void iDeleteTheStudentWithID(int id) {
        restTemplate.delete("/api/students/" + id);
    }

    @Then("there should be {int} student remaining")
    @Then("there should be {int} students remaining")
    public void thereShouldBeStudentRemaining(int expectedCount) {
        Set<?> students = restTemplate.getForObject("/api/students", Set.class);
        assertThat(students).hasSize(expectedCount);
    }
}
