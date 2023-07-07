package pl.lechowicz.performance_trace.employee;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public int addEmployee(@RequestBody Employee employee,
                           @RequestParam(name = "d", required = false, defaultValue = "500") int delay)
            throws InterruptedException {
        return this.employeeService.createEmployee(employee, delay);
    }

    @GetMapping("/fullname")
    public String getFullName(@RequestParam Integer id,
                              @RequestParam(name = "d", required = false, defaultValue = "300") int delay)
            throws InterruptedException {
        return this.employeeService.getFullName(id, delay);
    }
}
