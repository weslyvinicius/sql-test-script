package com.academy.springsqlscript.test;

import com.academy.springsqlscript.entity.Student;
import com.academy.springsqlscript.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import java.util.List;

@SqlMergeMode( SqlMergeMode.MergeMode.MERGE)
@Sql(scripts = "/insert_data1.sql")
@SpringBootTest
public class TestScriptMerge2 {

	@Autowired
	private StudentRepository studentRepository;

	@Sql(statements = "insert into student(id, name) values (100, 'Shiva')")
	@Test
	public void fetchRows1() {
		List<Student> students = studentRepository.findAll();

		System.out.println("Result - Test1: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(3, students.size());
	}

	@SqlMergeMode( SqlMergeMode.MergeMode.MERGE)
	@Sql("/insert_more_data1.sql")
	@Test
	public void fetchRows2() {

		List<Student>  students = studentRepository.findAll();
		System.out.println("Result - Test2: ");
		students.forEach( System.out::println );
		Assertions.assertEquals(5, students.size());
	}

}
