package com.example.homework0502.config;

import com.example.homework0502.bean.Klass;
import com.example.homework0502.bean.School;
import com.example.homework0502.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AutoConfiguration {

    @Bean(name = "student001")
    public Student getStudent001() {
        return new Student(001, "jack001");
    }

    @Bean(name = "student002")
    public Student getStudent002() {
        return new Student(002, "jack002");
    }

    @Bean(name = "class1")
    public Klass getKlass() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(getStudent001());
        students.add(getStudent002());

        Klass k1 = new Klass();
        k1.setStudents(students);

        return k1;
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}
