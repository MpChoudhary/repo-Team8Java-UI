package springproject.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import springproject.entity.*;
import springproject.service.JsonService;
import springproject.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private JsonService jsonService;

	@RequestMapping(value = "/ProjectHandler", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String projectGetHandler(Model theModel) {
		List<Project> projects = projectService.getProjects();
		theModel.addAttribute("projects", projects);
		return "project";
	}

	@RequestMapping(value = "/ProjectHandler", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void projectPostHandler(HttpServletRequest req, HttpServletResponse res, Model theModel)
			throws IOException, ParseException {
		String body = jsonService.requestToString(req);
		JSONObject jsonObject = jsonService.parseRequest(body);

		if(jsonService.isJson(body)) {
			String submitType = (String) jsonObject.get("submit");
			Action action = submitType(submitType);
			projectActions(jsonObject, action, res);
		}

//		System.out.println(jsonObject.get("projectName"));
//		Project p1 = new Project((String)jsonObject.get("projectName"));
//		projectService.saveProject(p1);
//		List<Project> projects = projectService.getProjects();
//		theModel.addAttribute("projects", projects);
//		return "project";
	}

	@SuppressWarnings("Duplicates")
	private Action submitType(String submit) {
		if(submit.equals("newProject")) {
			return Action.ADD;
		}
		if(submit.equals("displayProjects")) {
			return Action.DISPLAY;
		}
		if(submit.equalsIgnoreCase("displayAll")) {
			return Action.DISPLAY_ALL;
		}
		if(submit.startsWith("find")) {
			return Action.FIND;
		}
		if(submit.startsWith("delete")) {
			return Action.DELETE;
		}
		return Action.NOT_FOUND;
	}

	private void projectActions(JSONObject jsonObject, Action action, HttpServletResponse res) throws IOException {
		Project project;

		switch (action) {
			case ADD:
				newProject(jsonObject);

				jsonService.flushMessage("Successfully added", res);
				jsonService.flushProjects(res, projectService.getProjects());
				break;

			case DELETE:
				int deleteProjectId = Integer.parseInt(((String) jsonObject.get("submit")).substring(6));
				project = projectService.getProject(deleteProjectId);

				if(project.getResources() != null) {
					if(project.getFeatures() != null) {
						if (project.getFeatureValues() != null) {
							for(FeatureValue featureValue : project.getFeatureValues()) {
								projectService.deleteFeatureValue(featureValue.getId());
							}
						}
						for(Feature feature :project.getFeatures()) {
							projectService.deleteFeature(feature.getId());
						}
					}
					for (Resource resource : project.getResources()) {
						projectService.deleteResource(resource.getId());
					}
				}

				projectService.deleteProject(deleteProjectId);

				jsonService.flushMessage("Successfully deleted", res);
				jsonService.flushProjects(res, projectService.getProjects());
				break;

			case DISPLAY:
			case DISPLAY_ALL:
				jsonService.flushMessage("Display all projects", res);
				jsonService.flushProjects(res, projectService.getProjects());
				break;

			case FIND:
				int findProjectId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
				jsonService.flushProject(res, projectService.getProjects(), findProjectId);
				break;

			case NOT_FOUND:
			default:
				jsonService.flushMessage("Error, Project not found", res);
		}
	}

	private void newProject(JSONObject jsonObject) {
		Project p1 = new Project((String)jsonObject.get("projectName"));
		projectService.saveProject(p1);
	}
}
