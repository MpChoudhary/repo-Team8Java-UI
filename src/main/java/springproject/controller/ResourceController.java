package springproject.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springproject.entity.FeatureValue;
import springproject.entity.Project;
import springproject.entity.Resource;
import springproject.entity.Action;
import springproject.service.JsonService;
import springproject.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ResourceController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/ResourceHandler", method = RequestMethod.GET)
    public String resourceGetHandler(HttpServletRequest req, HttpServletResponse res, Model theModel) {

        List<Project> projects = projectService.getProjects();
        List<Resource> resources = projectService.getResources();
        Project displaySelectedProject;

        String displaySelectedProjectId = req.getParameter("displaySelectedProject");
        if (displaySelectedProjectId == null) {
            displaySelectedProject = projectService.getProject(1);
        } else {
            displaySelectedProject = projectService.getProject(Integer.parseInt(displaySelectedProjectId));
        }

        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
        theModel.addAttribute("resources", resources);
        theModel.addAttribute("projects", projects);
        return "resource";
    }

    @RequestMapping(value = "/ResourceHandler", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public void resourcePostHandler(HttpServletRequest req, HttpServletResponse res, Model theModel)
            throws IOException, ParseException {
        String body = jsonService.requestToString(req);
        JSONObject jsonObject = jsonService.parseRequest(body);

        if(jsonService.isJson(body)) {
            String submitType = (String) jsonObject.get("submit");
            Action action = submitType(submitType);
            resourceActions(res, jsonObject, action);
        }
        //////*** FOR FRONT END TESTING - PASS TO JSP ***////////
//        String submitType = (String) jsonObject.get("submit");
//        Action action = submitType(submitType);
//        Project displaySelectedProject = selectedProject(jsonObject, action);
//
//        List<Resource> resources = projectService.getResources();
//        List<Project> projects = projectService.getProjects();
//
//        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
//        theModel.addAttribute("resources", resources);
//        theModel.addAttribute("projects", projects);
//        return "resource";
    }

    @SuppressWarnings("Duplicates")
    private Action submitType(String submit) {
        if(submit.equals("newResource")) {
            return Action.ADD;
        }
        if(submit.equals("displayResource")) {
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
        if(submit.startsWith("edit")){
            return Action.EDIT;
        }
        return Action.NOT_FOUND;
    }

    @SuppressWarnings("Duplicates")
    private void resourceActions(HttpServletResponse res, JSONObject jsonObject, Action action) throws IOException {
        int selectedProjectId;
        Project project;
        String projectId = "projectId";

        switch (action) {
            case ADD:
                selectedProjectId = getProjectId(jsonObject, projectId);
                project = projectService.getProject(selectedProjectId);
                projectService.saveResource(newResource(jsonObject, project));

                jsonService.flushMessage("Resource successfully added", res);
//                jsonService.flushResources(res, projectService.getResources());
                break;

            case DISPLAY:
//                jsonService.flushMessage("Display all resources", res);
                jsonService.flushResources(res, projectService.getResources());
                break;

            case DELETE:
                int deleteResourceId = Integer.parseInt(((String) jsonObject.get("submit")).substring(6));

                Resource resource = projectService.getResource(deleteResourceId);
                if(resource != null) {
                    if(resource.getFeatureValue() != null) {
                        projectService.deleteFeatureValue(resource.getFeatureValue().getId());
                    }
                    projectService.deleteResource(deleteResourceId);
                    jsonService.flushMessage("Resource successfully deleted", res);
//                    jsonService.flushResources(res, projectService.getResources());
                } else {
                    jsonService.flushMessage("Resource id not found!", res);
                }
                break;

            case DISPLAY_ALL:
//                jsonService.flushMessage("Display all", res);
                jsonService.flushProjects(res, projectService.getProjects());
                break;

            case FIND:
                int findResourceId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                jsonService.flushResource(res, findResourceId);
                break;
            case EDIT:
                int updateResourceId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                Resource resourceToUpdate = projectService.getResource(updateResourceId);
                if(resourceToUpdate != null) {
                    resourceToUpdate.setCode((String) jsonObject.get("code"));
                    resourceToUpdate.setName((String) jsonObject.get("name"));
                    projectService.saveResource(resourceToUpdate);
                    jsonService.flushMessage("Resource "+ updateResourceId + " saved successfully!", res);
                } else {
                    jsonService.flushMessage("Resource id not found!", res);
                }
                break;
            case NOT_FOUND:
            default:
                jsonService.flushMessage("Error", res);
        }
    }

    private int getProjectId(JSONObject jsonObject, String name) {
        return Integer.parseInt((String) jsonObject.get(name));
    }

    private Resource newResource(JSONObject jsonObject, Project project) {
        Resource resource = new Resource((String) jsonObject.get("code"), (String) jsonObject.get("name"));
        resource.setProject(project);
        return resource;
    }


////////****** For front end(jsp) - send back the processed Project object ******////////
//    private Project selectedProject(JSONObject jsonObject, Action action) {
//        int selectedProjectId;
//        Project project;
//        String projectName = "projectName";
//
//        switch (action) {
//            case ADD:
//                selectedProjectId = getProjectId(jsonObject, projectName);
//                project = projectService.getProject(selectedProjectId);
//                projectService.saveResource(newResource(jsonObject, project));
//                break;
//
//            case DISPLAY:
//                selectedProjectId = getProjectId(jsonObject, projectName);
//                project = projectService.getProject(selectedProjectId);
//                break;
//
//            case DELETE:
//                int deleteResourceId = Integer.parseInt(((String) jsonObject.get("submit")).substring(6));
//                Resource resource = projectService.getResource(deleteResourceId);
//                projectService.deleteFeatureValue(resource.getFeatureValue().getId());
//                projectService.deleteResource(deleteResourceId);
//                List<Project> projects = projectService.getProjects();
//                project = projectService.getProject(projects.get(0).getId());
//                break;
//
//            case NOT_FOUND:
//            default:
//                project = new Project(null);
//        }
//        return project;
//    }

}
