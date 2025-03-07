package com.springboot.datamapping.Datamapping.Controllers;

import com.springboot.datamapping.Datamapping.entities.DepartmentEntity;
import com.springboot.datamapping.Datamapping.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController()
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }
    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId){
        return departmentService.assignManagerToDepartment(employeeId,departmentId);
    }
    @GetMapping(path = "/assignedDepartmentByManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        return departmentService.getAssignedDepartmentOfManager(employeeId);
    }
    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable Long employeeId) {
        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancers/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId,
                                                         @PathVariable Long employeeId) {
        return departmentService.assignFreelancerToDepartment(departmentId, employeeId);
    }
}
