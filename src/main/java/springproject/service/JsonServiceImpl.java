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
        for (Project project : projects) {
            String json = objectWriter.writeValueAsString(projectService.getProject(project.getId()));
            System.out.println(json);
            pw.println(json);
        }
    }

    @Override
    public void flushProject(HttpServletResponse res, List<Project> projects, int projectId) throws IOException {
        PrintWriter pw = res.getWriter();
        for (Project project : projects) {
            if (project.getId() == projectId) {
                String json = objectWriter.writeValueAsString(projectService.getProject(project.getId()));
                System.out.println(json);
                pw.println(json);
                break;
            }
        }
    }

    @Override
    public void flushResources(HttpServletResponse res, List<Resource> resources) throws IOException {
        PrintWriter pw = res.getWriter();
        for (Resource resource : resources) {
            String json = objectWriter.writeValueAsString(projectService.getResource(resource.getId()));
            System.out.println(json);
            pw.println(json);
        }
    }

    @Override
    public void flushResource(HttpServletResponse res, List<Resource> resources, int id) throws IOException {
        PrintWriter pw = res.getWriter();
        for (Resource resource : resources) {
            if (resource.getId() == id) {
                String json = objectWriter.writeValueAsString(projectService.getResource(resource.getId()));
                System.out.println(json);
                pw.println(json);
                break;
            }
        }
    }

    @Override
    public void flushFeatures(HttpServletResponse res, List<Feature> features) throws IOException {
        PrintWriter pw = res.getWriter();
        for (Feature feature : features) {
            String json = objectWriter.writeValueAsString(projectService.getFeature(feature.getId()));
            System.out.println(json);
            pw.println(json);
        }
    }

    @Override
    public void flushFeature(HttpServletResponse res, List<Feature> features, int id) throws IOException {
        PrintWriter pw = res.getWriter();
        for (Feature feature : features) {
            if (feature.getId() == id) {
                String json = objectWriter.writeValueAsString(projectService.getFeature(feature.getId()));
                System.out.println(json);
                pw.println(json);
                break;
            }
        }
    }

    @Override
    public void flushFeatureValues(HttpServletResponse res, List<FeatureValue> featureValues) throws IOException {
        PrintWriter pw = res.getWriter();
        for (FeatureValue featureValue : featureValues) {
            String json = objectWriter.writeValueAsString(projectService.getFeatureValue(featureValue.getId()));
            System.out.println(json);
            pw.println(json);
        }
    }

    @Override
    public void flushFeatureValue(HttpServletResponse res, List<FeatureValue> featureValues, int id) throws IOException {
        PrintWriter pw = res.getWriter();
        for (FeatureValue featureValue : featureValues) {
            if (featureValue.getId() == id) {
                String json = objectWriter.writeValueAsString(projectService.getFeatureValue(featureValue.getId()));
                System.out.println(json);
                pw.println(json);
                break;
            }
        }
    }
}
