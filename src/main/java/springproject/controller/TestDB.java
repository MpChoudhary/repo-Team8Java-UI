//package springproject.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import springproject.entity.Project;
//import springproject.entity.Resource;
//import springproject.service.ProjectService;
//
//@Controller
//public class TestDB {
//
//	@Autowired
//	private static ProjectService projectService;
//
//	public static void main(String[] args) {
//		Project p1 = new Project("Project 1");
//		Resource r1 = new Resource("010000", "General Requirements");
//		Resource r2 = new Resource("020000", "Existing Conditions");
//		p1.add(r1);
//		p1.add(r2);
//		projectService.saveProject(p1);
//	}
//}
