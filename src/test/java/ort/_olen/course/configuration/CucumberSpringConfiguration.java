package ort._olen.course.configuration;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ort._olen.course.CourseApplication;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = CourseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:config/application.yml")
@ActiveProfiles("test")
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}