package com.example.demo.controller;

import com.example.demo.DTO.StudentRequestDTO;
import com.example.demo.DTO.StudentResponseDTO;
import com.example.demo.UPDATE_DTO.updateStudentRequestDTO;
import com.example.demo.UPDATE_DTO.updateStudentResponseDTO;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;
//sabse pahle api ko sudhara alag alag end points likhne ki jrurat nhi hai
@RestController
@RequestMapping("/api/students")
public class StudentController {
    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping// yeh create hai na eska mtlb  request kr rha toh request dto mein
    public ResponseEntity<StudentResponseDTO> createStudent // student response dto mein na final result save hogaa
            (@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO createStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStud(@PathVariable Long id) {
        StudentResponseDTO studentResp = studentService.getStud(id);
        // yeha tak kab ayega pahle controller se service then handler sab shi rhega tabhi ayega na return yak toh happy code hai
        //yeh jo if-else statement hai controller ka kam nhi hai yeh esse htate jayenge sara direct return krenge wohi same kyuki shi rhega toh hi return krega na
        // aur hmesa happy code likhenge ok wala else yeh sab nhi
        return ResponseEntity.ok(studentResp);
    }

    //eski jrurat nhi hai yeh if else exception  handler se ja rha hai
//        if (studentResp == null) {
//            return status(HttpStatus.NOT_FOUND)
//                    .body(null);
//        }
//        return ResponseEntity
//        .status(HttpStatus.OK)
//                .body(studentResp);
  //  }
        @GetMapping// yaha kuch nhi mtlb hua na get All
        public ResponseEntity<List<StudentResponseDTO>> getAllStudent () {
            List<StudentResponseDTO> studentList = studentService.getAllStudent();
            return ResponseEntity.ok(studentList);
//yeh kr chuke hai khi aur file mein
//            if (studentList == null) {
//                return status(HttpStatus.NOT_FOUND)
//                        .body(null);
//            }
//
//            return status(HttpStatus.OK)
//                    .body(studentList);
        }
        @PutMapping
        public ResponseEntity<updateStudentResponseDTO> studentUpdate (@PathVariable Long id, @RequestBody updateStudentRequestDTO updateStudentRequestDTO) {

            updateStudentResponseDTO studentResp = studentService.updateStudent(id, updateStudentRequestDTO); // left side wala final result haina woh wohi save hoga response mein na
            return ResponseEntity.ok(studentResp);
        }
//            if (studentResp == null) {
//                return ResponseEntity
//                        .status(HttpStatus.NOT_FOUND)
//                        .body(null);
//            }
//
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(studentResp);
//        }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

                studentService.deleteStudent(id);
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT).build();
    }
//        if (!isDeleted) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok("Record Deleted");
//    }
}