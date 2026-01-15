package ort._olen.course;

import org.springframework.boot.SpringApplication;

public class TestCourseApplication {

	public static void main(String[] args) {
		SpringApplication.from(CourseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
