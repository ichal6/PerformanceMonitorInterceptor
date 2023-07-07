package pl.lechowicz.performance_trace.employee;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private final AtomicInteger lastEmpId = new AtomicInteger(0);

    public String getFullName(Integer employeeId, int delay) throws InterruptedException{
        //Adding sleep to see impact on execution
        Thread.sleep(delay);
        Employee employee = employeeMap.get(employeeId);
        if(employee != null) {
            return employee.firstName() + " " + employee.lastName();
        }
        return "Employee Not found" ;
    }


    public int createEmployee(Employee employee, int delay) throws InterruptedException{
        //Adding delay
        Thread.sleep(delay);
        employeeMap.put(lastEmpId.addAndGet(1), employee);
        return lastEmpId.get();
    }
}