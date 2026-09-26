package com.example.demo.service;

import com.example.demo.DTO.StudentRequestDTO;
import com.example.demo.DTO.StudentResponseDTO;
import com.example.demo.UPDATE_DTO.updateStudentRequestDTO;
import com.example.demo.UPDATE_DTO.updateStudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//yaha buisness logic perform hoga we know normally pahle jaisa repository mein nhi dalenge

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {

        Student student = mapIntoStudent(studentRequestDTO); //Repository directly DTO nahi leta. Repository ko hamari Student Entity chahiye.yaha conversion hoga aur entry mein hi convert kr dengey then return ke time response mein
        if (rollExists(student)) {
            throw new DuplicateResourceException("Roll Number Already Exists: " + student.getRoll());
        }

        Student stud = studentRepository.save(student);
        return mapToResponse(stud);
    }


    public StudentResponseDTO getStud(Long id) {
        Student studResp = studentRepository
                .findById(id)
                .orElseThrow(() ->//clean architecuture milta toh thik nhi mila toh throw exception
                        new ResourceNotFoundException("Student with id Not" + id + " found"));

        return mapToResponse(studResp); //nhi toh map krdo DTO mein sidha
    }


    //  Optional<Student> stud = studentRepository.findById(id); // optional<Studnet> Yahan Student isliye hai kyunki Repository Entity ke saath kaam karta hai.
//        if (stud.isEmpty()) {
//            return null;
//        }
    //  return mapToResponse(stud.get());  //Matlab method ko StudentResponseDTO return karna hi padega.
// }


    public List<StudentResponseDTO> getAllStudent() {
        List<Student> stud = studentRepository.findAll();

        return stud.stream()
                .map(this::mapToResponse)
                .toList();  /////Student1 → mapToResponse() → ResponseDTO1
        /////Student2 → mapToResponse() → ResponseDTO2
        ////Student3 → mapToResponse() → ResponseDTO3
    }


    public updateStudentResponseDTO updateStudent(Long id, updateStudentRequestDTO updateStudentRequestDTO) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " Not Found"));

//        if (existing.isEmpty()){
//            return null;
//        }


        existing.setName(updateStudentRequestDTO.getName());
        existing.setAge(updateStudentRequestDTO.getAge());
        existing.setRoll(updateStudentRequestDTO.getRoll());

        Student savedStudent = studentRepository.save(existing);

        return mapToUpdateResponse(savedStudent);
    }


    public boolean deleteStudent(Long id) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " Not Found"));

        studentRepository.delete(student);

        return true;

//        if (!isStudent) {
//            return false;
//        }
//
//        studentRepository.deleteById(Id);
//
//        return true;
//    }

        //ye 3 custom methods abhi class mein define nahi hain. Unko neeche banana padega.
//    mapIntoStudent(studentRequestDTO)
//    mapToResponse(stud)
//    mapToUpdateResponse(savedStudent)
        //jo data aa raha hai woh StudentRequestDTO mein hai, lekin database mein save karne ke liye hume Student chahiye.

    }

    private Student mapIntoStudent(StudentRequestDTO studentReqDto) {

        Student student = new Student();

        student.setName(studentReqDto.getName());
        student.setAge(studentReqDto.getAge());
        student.setRoll(studentReqDto.getRoll());

        return student;
    }

    private StudentResponseDTO mapToResponse(Student student) {

        StudentResponseDTO responseDto = new StudentResponseDTO();

        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setRoll(student.getRoll());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        responseDto.setCreatedAt(student.getCreatedAt());

        return responseDto;
    }

    private boolean rollExists(Student student) {
        return studentRepository.existsByRoll(student.getRoll());
    }

    private updateStudentResponseDTO mapToUpdateResponse(Student student) {

        updateStudentResponseDTO responseDto = new updateStudentResponseDTO();

        responseDto.setAge(student.getAge());
        responseDto.setRoll(student.getRoll());

        responseDto.setUpdatedAt(student.getUpdatedAt().toLocalTime());

        return responseDto;
    }
}