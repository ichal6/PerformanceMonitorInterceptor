package pl.lechowicz.performance_trace;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public int addEmployee(@RequestBody Employee employee) throws InterruptedException {
        return this.employeeService.createEmployee(employee);
    }

    @GetMapping("/fullname")
    public String getFullName(@RequestParam Integer id) throws InterruptedException {
        return this.employeeService.getFullName(id);
    }
}
