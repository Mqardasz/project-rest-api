package com.project.service;

import com.project.model.Student;
import com.project.model.Projekt;
import com.project.repository.StudentRepository;
import com.project.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProjektRepository projektRepository;

    @Override
    public Student setStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> getStudentByNrIndeksu(String nrIndeksu) {
        return studentRepository.findByNrIndeksu(nrIndeksu);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Integer id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setImie(updatedStudent.getImie());
                    student.setNazwisko(updatedStudent.getNazwisko());
                    student.setNrIndeksu(updatedStudent.getNrIndeksu());
                    student.setEmail(updatedStudent.getEmail());
                    student.setStacjonarny(updatedStudent.getStacjonarny());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("nie znaleziono studenta o ID: " + id));
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findStudentsByNazwisko(String nazwisko, Pageable pageable) {
        return studentRepository.findByNazwiskoStartsWithIgnoreCase(nazwisko, pageable);
    }

    @Override
    public void assignProjektToStudent(Integer studentId, Integer projektId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("nie znaleziono studenta o ID: " + studentId));
        Projekt projekt = projektRepository.findById(projektId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono projektu o ID:  " + projektId));

        student.getProjekty().add(projekt);
        studentRepository.save(student);
    }
}