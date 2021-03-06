package springproject.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springproject.entity.Feature;
import springproject.entity.FeatureValue;
import springproject.entity.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import springproject.entity.Resource;

/**
 * Tan Choong Liang
 * JSON request handler
 */

@Component
@SuppressWarnings("unchecked")
public class JsonServiceImpl implements JsonService {

    @Autowired
    ProjectService projectService;

    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public String requestToString(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining()).trim();
    }

    @Override
    public boolean isJson(String content) {
        return content.startsWith("{") && content.endsWith("}");
    }

    @Override
    public JSONObject parseRequest(String body) throws ParseException {
        if (isJson(body)) {
            System.out.println("Received JSON request!");
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(body);
        }
        JSONObject jsonObject = new JSONObject();
        String[] lines = body.split("&");
        for (String line : lines) {
            String[]words = line.split("=");
            if(words.length > 1) {
                jsonObject.put(words[0], words[1]);
            } else {
                jsonObject.put(words[0], null);
            }
        }
        return jsonObject;
    }

    @Override
    public void flushJSON(JSONObject json, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        pw.println(json.toString());
    }

    @Override
    public void flushMessage(String message, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        JSONObject json = new JSONObject();
        json.put("Message", message);
        pw.println(json.toString());
    }

    @Override
    public void flushProjects(HttpServletResponse res, List<Project> projects) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (Project project : projects) {
            String json = objectWriter.writeValueAsString(projectService.getProject(project.getId()));
            System.out.println(json);
            strArr.add(json);
//            pw.println(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushProject(HttpServletResponse res, int projectId) throws IOException {
        PrintWriter pw = res.getWriter();
        Project project = projectService.getProject(projectId);
        if(project != null) {
            String json = objectWriter.writeValueAsString(project);
            System.out.println("Flushing project " + projectId);
            pw.println(json);
        } else {
            flushMessage("Project id not found!", res);
        }
    }

    @Override
    public void flushResources(HttpServletResponse res, List<Resource> resources) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (Resource resource : resources) {
            String json = objectWriter.writeValueAsString(projectService.getResource(resource.getId()));
            System.out.println(json);
            strArr.add(json);
//            pw.println(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushResource(HttpServletResponse res, int resourceId) throws IOException {
        PrintWriter pw = res.getWriter();
        Resource resource = projectService.getResource(resourceId);
        if(resource != null) {
            String json = objectWriter.writeValueAsString(resource);
            System.out.println("Flushing resource " + resourceId);
            pw.println(json);
        } else {
            flushMessage("Resource id not found!", res);
        }

    }

    @Override
    public void flushFeatures(HttpServletResponse res, List<Feature> features) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (Feature feature : features) {
            String json = objectWriter.writeValueAsString(projectService.getFeature(feature.getId()));
            System.out.println(json);
            strArr.add(json);
//            pw.println(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushFeature(HttpServletResponse res, int featureId) throws IOException {
        PrintWriter pw = res.getWriter();
        Feature feature = projectService.getFeature(featureId);
        if(feature != null) {
            String json = objectWriter.writeValueAsString(feature);
            System.out.println("Flushing feature " + featureId);
            pw.println(json);
        } else {
            flushMessage("Feature id not found!", res);
        }
    }

    @Override
    public void flushFeatureValues(HttpServletResponse res, List<FeatureValue> featureValues) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (FeatureValue featureValue : featureValues) {
            String json = objectWriter.writeValueAsString(projectService.getFeatureValue(featureValue.getId()));
            System.out.println(json);
            strArr.add(json);
//            pw.println(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushFeatureValue(HttpServletResponse res, int featureValueId) throws IOException {
        PrintWriter pw = res.getWriter();
        FeatureValue featureValue = projectService.getFeatureValue(featureValueId);
        if(featureValue != null) {
            String json = objectWriter.writeValueAsString(featureValue);
            System.out.println("Flushing feature value " + featureValueId);
            pw.println(json);
        } else {
            flushMessage("Feature value not found!", res);
        }
    }
}
