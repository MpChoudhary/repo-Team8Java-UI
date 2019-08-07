//package springproject.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import springproject.entity.Project;
//import springproject.entity.Resource;
//import springproject.service.ProjectService;
//
//@Controller
//public class TestingController {
//
//    @Autowired
//    private ProjectService projectService;
//
//    @GetMapping("/testing")
//    public String list(Model theModel) {
//        Project p1 = new Project("Project 1");
//        Resource r1 = new Resource("010000", "GeneralRequirements");
//        Resource r2 = new Resource("020000", "ExistingConditions");
//        p1.add(r1);
//        p1.add(r2);
//        projectService.saveProject(p1);
//        List<Project> projects = projectService.getProjects();
//        List<Resource> resources = projectService.getResources();
//        theModel.addAttribute("projects", projects);
//        theModel.addAttribute("resources", resources);
//        return "testing";
//    }
//}