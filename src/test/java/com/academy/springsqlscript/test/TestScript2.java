package com.academy.springsqlscript.test;

import com.academy.springsqlscript.entity.Student;
import com.academy.springsqlscript.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

@SqlConfig(commentPrefix = "#")
@Sql(scripts = "/insert_data_prefix_#.sql" )
@SpringBootTest
public class TestScript2 {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void fetchRows1() {
		List<Student> students = studentRepository.findAll();

		System.out.println("Result - Test1: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(2, students.size());
	}

	@Sql(scripts = "/insert_more_data_prefix_~.sql",  config= @SqlConfig(commentPrefix = "~"))
	@Test
	public void fetchRows2() {

		List<Student>  students = studentRepository.findAll();
		System.out.println("Result - Test2: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(4, students.size());
	}

}
