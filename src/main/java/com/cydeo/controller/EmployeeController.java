package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Employee;
import com.cydeo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
// USe dependency injection to USe Employee Service to Save data
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/register")
    public String createEmployee(Model model){//bring data to view
        model.addAttribute("employee",new Employee());
        model.addAttribute("stateList", DataGenerator.getAllStates());
        return "/employee/employee-create";
    }

    @PostMapping("/insert")
        public String insertEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        //Binding result should be placed right after the class object you check for validation. This Interface helps to connect validation with result
        if(bindingResult.hasErrors()){// if there is an error
            model.addAttribute("stateList", DataGenerator.getAllStates());
            // we again add model stateList so that our page stays the
            // same in case of error and there will be no need to input all data all over again.
            // If we do not add it, states are lost, because they are at different end point
            return "employee/employee-create";
        }
        //Can create custom validation if needed
        employeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/list")
        public String listEmployees( Model model){
            model.addAttribute("employeeList",employeeService.readAllEmployees());
            return  "/employee/employee-list";
        }
        /*Validation. How to prevent user from unacceptable inputs
    1 Step: pomxml add dependency validation:
           <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-validation</artifactId>
           </dependency>
    2  Step: decide what limitations will be required
       go to Class and add required annotations, such as
@NotNull // field cannot be null
@NotBlank // cannot be left blank -> "  " -> String str="  "
@NotEmpty // should contain data  -> ""   -> String str=""
@Size(min=   max=)

All feilds have their own validation annotation

Controller SHOULD KNOW about annotations and validations: You should @Valid in the method using the Class Onject, such as
@ModelAttribute("employee") @Valid Employee employee (inside postmapping, because we check info that the user sends us)

    */

}
