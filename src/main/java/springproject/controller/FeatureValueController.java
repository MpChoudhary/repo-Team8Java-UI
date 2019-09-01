package springproject.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springproject.entity.*;
import springproject.service.JsonService;
import springproject.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FeatureValueController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/FeatureValueHandler", method = RequestMethod.GET)
    public String featureValueGetHandler(HttpServletRequest req, HttpServletResponse res, Model theModel) {
        List<Project> projects = projectService.getProjects();
        List<Resource> resources = projectService.getResources();
        List<Feature> features = projectService.getFeatures();
        List<FeatureValue> featureValues = projectService.getFeatureValues();
        Project displaySelectedProject;

        String displaySelectedProjectId = req.getParameter("displaySelectedProject");

        displaySelectedProject = (displaySelectedProjectId == null)
                ? projectService.getProject(1) : projectService.getProject(Integer.parseInt(displaySelectedProjectId));

        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
        theModel.addAttribute("projects", projects);
        theModel.addAttribute("resources", resources);
        theModel.addAttribute("features", features);
        theModel.addAttribute("featureValues", featureValues);
        return "featureValue";
    }

    @RequestMapping(value = "/FeatureValueHandler", method = RequestMethod.POST)
    public void featureValuePostHandler(HttpServletRequest req, HttpServletResponse res, Model theModel)
            throws IOException, ParseException {
        String body = jsonService.requestToString(req);
        JSONObject jsonObject = jsonService.parseRequest(body);

//        Project displaySelectedProject;

        if(jsonService.isJson(body)) {
            String submitType = (String) jsonObject.get("submit");
            Action action = submitType(submitType);
            featureValueActions(jsonObject, action, res);
        }

//        if(jsonObject.get("submit").equals("newFeatureValue")) {
//            displaySelectedProject = projectService.getProject(Integer.parseInt((String) jsonObject.get("projectName")));
//            projectService.saveFeatureValue(newFeatureValue(jsonObject, displaySelectedProject));
//        } else {
//            displaySelectedProject = projectService.getProject(Integer.parseInt((String) jsonObject.get("displaySelectedProject")));
//        }
//
//        List<Project> projects = projectService.getProjects();
//        List<Resource> resources = projectService.getResources();
//        List<Feature> features = projectService.getFeatures();
//        List<FeatureValue> featureValues = projectService.getFeatureValues();
//
//        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
//        theModel.addAttribute("resources", resources);
//        theModel.addAttribute("projects", projects);
//        theModel.addAttribute("features", features);
//        theModel.addAttribute("featureValues", featureValues);
//
//        return "featureValue";
    }

    @SuppressWarnings("Duplicates")
    private Action submitType(String submit) {
        if(submit.equals("newFeatureValue")) {
            return Action.ADD;
        }
        if(submit.equals("displayFeatureValue")) {
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
    private void featureValueActions(JSONObject jsonObject, Action action, HttpServletResponse res) throws IOException {
        int selectedProjectId;
        Project project;
        String projectId = "projectId";

        switch (action) {
            case ADD:
                selectedProjectId = getProjectId(jsonObject, projectId);
                project = projectService.getProject(selectedProjectId);
                projectService.saveFeatureValue(newFeatureValue(jsonObject, project));

                jsonService.flushMessage("Successfully added", res);
//                jsonService.flushFeatureValues(res, projectService.getFeatureValues());?
                break;

            case DISPLAY:
//                jsonService.flushMessage("Display all feature values", res);
                jsonService.flushFeatureValues(res, projectService.getFeatureValues());
                break;

            case DELETE:
                int deleteFeatureValueId = Integer.parseInt(((String) jsonObject.get("submit")).substring(6));

                if(projectService.getFeatureValue(deleteFeatureValueId) != null) {
                    projectService.deleteFeatureValue(deleteFeatureValueId);
                    jsonService.flushMessage("Successfully deleted", res);
//                    jsonService.flushFeatureValues(res, projectService.getFeatureValues());
                } else {
                    jsonService.flushMessage("Feature value id not found!", res);
                }
                break;

            case DISPLAY_ALL:
//                jsonService.flushMessage("Display all", res);
                jsonService.flushProjects(res, projectService.getProjects());
                break;

            case FIND:
                int findFeatureValueId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                jsonService.flushFeatureValue(res, findFeatureValueId);
                break;

            case EDIT:
                int updateFVId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                FeatureValue featureValueToUpdate = projectService.getFeatureValue(updateFVId);
                if(featureValueToUpdate != null) {
                    featureValueToUpdate.setValue((String) jsonObject.get("value"));
                    projectService.saveFeatureValue(featureValueToUpdate);
                    jsonService.flushMessage("Feature Value " + updateFVId + " saved successfully!", res);
                } else {
                    jsonService.flushMessage("Feature value id not found!", res);
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

    private FeatureValue newFeatureValue(JSONObject jsonObject, Project project) {
        FeatureValue featureValue = new FeatureValue((String) jsonObject.get("value"));
        featureValue.setProject(project);
        featureValue.setFeature(projectService.getFeature(Integer.parseInt((String) jsonObject.get("featureId"))));
        featureValue.setResource(projectService.getResource(Integer.parseInt((String) jsonObject.get("resourceId"))));
        return featureValue;
    }
}
