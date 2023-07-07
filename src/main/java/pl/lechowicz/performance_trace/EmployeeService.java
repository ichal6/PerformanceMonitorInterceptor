package pl.lechowicz.performance_trace;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private final AtomicInteger lastEmpId = new AtomicInteger(0);

    public String getFullName(Integer employeeId) throws InterruptedException{
        //Adding 300ms sleep to see impact on execution
        Thread.sleep(300);
        Employee employee = employeeMap.get(employeeId);
        if(employee != null) {
            return employee.firstName() + " " + employee.lastName();
        }
        return "Employee Not found" ;
    }


    public int createEmployee(Employee employee) throws InterruptedException{
        //Adding 500ms delay
        Thread.sleep(500);
        employeeMap.put(lastEmpId.addAndGet(1), employee);
        return lastEmpId.get();
    }
}