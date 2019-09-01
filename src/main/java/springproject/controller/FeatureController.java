package springproject.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class FeatureController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/FeatureHandler", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String featureGetHandler(HttpServletRequest req, HttpServletResponse res, Model theModel) {
        List<Project> projects = projectService.getProjects();
        List<Feature> features = projectService.getFeatures();
        Project displaySelectedProject;

        String displaySelectedProjectId = req.getParameter("displaySelectedProject");

        displaySelectedProject = (displaySelectedProjectId == null)
                ? projectService.getProject(1) : projectService.getProject(Integer.parseInt(displaySelectedProjectId));

        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
        theModel.addAttribute("projects", projects);
        theModel.addAttribute("features", features);
        return "feature";
    }

    @RequestMapping(value = "/FeatureHandler", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void featurePostHandler(HttpServletRequest req, HttpServletResponse res, Model theModel)
            throws IOException, ParseException {
        String body = jsonService.requestToString(req);
        JSONObject jsonObject = jsonService.parseRequest(body);

//        Project displaySelectedProject;

        if(jsonService.isJson(body)) {
            String submitType = (String) jsonObject.get("submit");
            Action action = submitType(submitType);
            featureActions(jsonObject, action, res);
        }
//        if(jsonObject.get("submit").equals("newFeature")) {
//            displaySelectedProject = projectService.getProject(Integer.parseInt((String) jsonObject.get("projectName")));
//            projectService.saveFeature(newFeature(jsonObject, displaySelectedProject));
//        } else {
//            displaySelectedProject = projectService.getProject(Integer.parseInt((String) jsonObject.get("displaySelectedProject")));
//        }
//
//        List<Project> projects = projectService.getProjects();
//        List<Feature> features = projectService.getFeatures();
//
//        theModel.addAttribute("displaySelectedProject", displaySelectedProject);
//        theModel.addAttribute("projects", projects);
//        theModel.addAttribute("features", features);
//
//        return "feature";
    }

    @SuppressWarnings("Duplicates")
    private Action submitType(String submit) {
        if(submit.equals("newFeature")) {
            return Action.ADD;
        }
        if(submit.equals("displayFeature")) {
            return Action.DISPLAY;
        }
        if(submit.equalsIgnoreCase("displayAll")) {
            return Action.DISPLAY_ALL;
        }
        if (submit.startsWith("find")) {
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
    private void featureActions(JSONObject jsonObject, Action action, HttpServletResponse res) throws IOException {
        int selectedProjectId;
        Project project;
        String projectId = "projectId";

        switch (action) {
            case ADD:
                selectedProjectId = getProjectId(jsonObject, projectId);
                project = projectService.getProject(selectedProjectId);
                projectService.saveFeature(newFeature(jsonObject, project));

                jsonService.flushMessage("Successfully added", res);
//                jsonService.flushFeatures(res, projectService.getFeatures());
                break;

            case DISPLAY:
//                jsonService.flushMessage("Display all features", res);
                jsonService.flushFeatures(res, projectService.getFeatures());
                break;

            case DELETE:
                int deleteFeatureId = Integer.parseInt(((String) jsonObject.get("submit")).substring(6));
                Feature feature = projectService.getFeature(deleteFeatureId);

                if(feature != null) {
                    if(feature.getFeatureValues() != null) {
                        for (FeatureValue fv : feature.getFeatureValues()) {
                            projectService.deleteFeatureValue(fv.getId());
                        }
                    }
                    projectService.deleteFeature(deleteFeatureId);

                    jsonService.flushMessage("Successfully deleted", res);
//                    jsonService.flushFeatures(res, projectService.getFeatures());
                } else {
                    jsonService.flushMessage("Feature id not found!", res);
                }
                break;

            case DISPLAY_ALL:
//                jsonService.flushMessage("Display all", res);
                jsonService.flushProjects(res, projectService.getProjects());
                break;
            case EDIT:
                int updateFeatureId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                Feature featureToUpdate = projectService.getFeature(updateFeatureId);
                if(featureToUpdate != null) {
                    featureToUpdate.setName((String) jsonObject.get("name"));
                    featureToUpdate.setType((String) jsonObject.get("type"));
                    if(verifyFormulaContent(jsonObject)) {
                        featureToUpdate.setContent((String) jsonObject.get("content"));
                    } else {
                        featureToUpdate.setContent(null);
                    }
                    projectService.saveFeature(featureToUpdate);
                    jsonService.flushMessage("Feature " + updateFeatureId + " saved successfully!", res);
                } else {
                    jsonService.flushMessage("Feature id not found!", res);
                }
                break;

            case FIND:
                int findFeatureId = Integer.parseInt(((String) jsonObject.get("submit")).substring(4));
                jsonService.flushFeature(res, findFeatureId);
                break;

            case NOT_FOUND:
            default:
                jsonService.flushMessage("Error", res);
        }
    }

    private int getProjectId(JSONObject jsonObject, String name) {
        return Integer.parseInt((String) jsonObject.get(name));
    }

    private Feature newFeature(JSONObject jsonObject, Project project) {
        Feature feature;
        if(verifyFormulaContent(jsonObject)) {
            feature = new Feature((String) jsonObject.get("name"), (String) jsonObject.get("type"),
                    (String) jsonObject.get("content"));
        } else {
            feature = new Feature((String) jsonObject.get("name"), (String) jsonObject.get("type"), null);
        }
        feature.setProject(project);
        return feature;
    }

    private boolean verifyFormulaContent(JSONObject jsonObject) {
        if(((String) jsonObject.get("type")).equalsIgnoreCase("formula")) {
            return jsonObject.get("content") != null && !((((String) jsonObject.get("content")).trim()).equals(""));
        }
        return false;
    }
}
