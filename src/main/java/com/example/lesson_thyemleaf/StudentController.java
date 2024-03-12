package com.example.lesson_thyemleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {
    public List<StudentDTO> list = new LinkedList<>();

    public StudentController() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(UUID.randomUUID().toString());
        studentDTO.setName("Alish");
        studentDTO.setSurname("Aliyev");
        studentDTO.setAge(27);
        list.add(studentDTO);

        studentDTO = new StudentDTO();
        studentDTO.setId(UUID.randomUUID().toString());
        studentDTO.setName("Valish");
        studentDTO.setSurname("Valiyev");
        studentDTO.setAge(23);
        list.add(studentDTO);

        studentDTO = new StudentDTO();
        studentDTO.setId(UUID.randomUUID().toString());
        studentDTO.setName("Toshmat");
        studentDTO.setSurname("Toshmatov");
        studentDTO.setAge(25);
        list.add(studentDTO);
    }

    @GetMapping()
    public String test(Model model) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(UUID.randomUUID().toString());
        studentDTO.setName("Alish");
        studentDTO.setSurname("Aliyev");
        studentDTO.setAge(27);

        model.addAttribute("student", studentDTO);
        return "student/student-detail";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("studentList", list);
        return "student/student-list";
    }

    @GetMapping("/go-to-add")
    public String goToAdd(Model model) {
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("isEdit", false);
        return "student/student-add";
    }


    @PostMapping("/save")
    public String save(Model model, @ModelAttribute StudentDTO studentDTO) {
        studentDTO.setId(UUID.randomUUID().toString());
        list.add(studentDTO);
        return "redirect:/student/list";
    }

    @GetMapping("/go-to-edit/{studentId}")
    public String save(Model model, @PathVariable("studentId") String studentId) {
        Optional<StudentDTO> optional = list.stream().filter(dto -> dto.getId().equals(studentId)).findAny();
        if (optional.isEmpty()) {
            return "404";
        }
        model.addAttribute("student", optional.get());
        model.addAttribute("isEdit", true);
        return "student/student-add";
    }

    @PostMapping("/edit/{studentId}")
    public String edit(Model model, @PathVariable("studentId") String studentId,
                       @ModelAttribute StudentDTO studentDTO) {
        Optional<StudentDTO> optional = list.stream().filter(dto -> dto.getId().equals(studentId)).findAny();
        if (optional.isEmpty()) {
            return "404";
        }
        StudentDTO exist = optional.get();
        exist.setAge(studentDTO.getAge());
        exist.setName(studentDTO.getName());
        exist.setSurname(studentDTO.getSurname());
        return "redirect:/student/list";
    }

}
