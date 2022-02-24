package com.StudentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.StudentManagement.model.Student;
import com.StudentManagement.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	// INSERT - form
	@GetMapping("/saveStudentLayer")
	public String saveStudentLayer(Student student) {
		return "saveStudentLayer";
	}

	@PostMapping("/saveStudent")
	public String create(@ModelAttribute("student") Student s) {
		studentService.saveStudent(s);
		return "redirect:/";
	}

	// DISPLAY - just one page required
	@GetMapping("/getAllStudents")
	public String getAllStudents(Model m) {
		m.addAttribute("students", studentService.displayAllStudent());
		return "display";
	}

	@GetMapping("/getStudentbyIDLayer")
	public String getStudentbyID() {
		return "getStudentbyIDLayer";
	}

	// DISPLAY - just one page required
	@PostMapping("/getStudentbyID")
	public String getStudentbyID(@RequestParam("id") Long id, Model m) {
		m.addAttribute("students", studentService.displayById(id));
		return "display";
	}

	// update - form
	@GetMapping("/updateStudentByIDLayer")
	public String updateStudentByIDLayer() {
		return "updateStudentByIDLayer";
	}

	@PostMapping("/updateStudentByID")
	public String updateStudentByID(@RequestParam("id") Long id, Model m) {
		Student existingStudent = studentService.updateStudent(id);
		m.addAttribute("student", existingStudent);
		return "updateStudentForm";
	}

	@GetMapping("/deleteAllStudent")
	public String delete() {
		studentService.deleteStudent();
		return "redirect:/";
	}

	// delete - form
	@GetMapping("/deleteStudentByIDLayer")
	public String deleteStudentByIDLayer() {
		return "deleteStudentByIDLayer";
	}

	@PostMapping("/deleteStudentByID")
	public String deleteStudentByID(@RequestParam("id") Long id) {
		studentService.deleteStudentbyId(id);
		return "redirect:/";
	}

}
