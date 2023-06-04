package com.academy.springsqlscript.test;

import com.academy.springsqlscript.entity.Student;
import com.academy.springsqlscript.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Sql(scripts = "/insert_data1.sql", statements = "insert into student(id, name) values (100, 'Shiva')")
@SpringBootTest
public class TestScript1 {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void fetchRows1() {
		List<Student> students = studentRepository.findAll();

		System.out.println("Result - Test1: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(3, students.size());
	}

	@Sql("/insert_more_data1.sql")
	@Test
	public void fetchRows2() {

		List<Student>  students = studentRepository.findAll();
		System.out.println("Result - Test2: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(5, students.size());
	}

}
