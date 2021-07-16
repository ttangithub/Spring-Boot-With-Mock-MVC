package com.javacodegeeks.web;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.javacodegeeks.domain.Student;
import com.javacodegeeks.service.StudentService;

@WebMvcTest(controllers = StudentRestController.class)
class RestControllerTest {

	MockMvc mockMvc;
	
	@MockBean
	StudentService service;

	@BeforeEach
	void setUp(WebApplicationContext wac) throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
						.alwaysExpect(status().isOk())
						.alwaysExpect(content().contentType(MediaType.APPLICATION_JSON))
						.alwaysDo(print())
						.build();
	}

	@Test
	void whenReadStudent_returnJsonContent() throws Exception {
		Student student = new Student("Bill", "Gates", "Freshman");
		student.setId(102L);
		when(service.findById(102L)).thenReturn(Optional.of(student));

		this.mockMvc.perform(get("/students/102"));
	}

	@Test
	void whenReadStudents_returnList() throws Exception {
		Student s1 = new Student("Jane", "Doe", "Junior");
		Student s2 = new Student("Martin", "Fowler", "Senior");
		Student s3 = new Student("Roy", "Fielding", "Freshman");
		List<Student> studentList = List.of(s1, s2, s3);

		when(service.findAll()).thenReturn(studentList);

		this.mockMvc.perform(get("/students"));
	}	
}