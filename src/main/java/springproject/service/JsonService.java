package springproject.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import springproject.entity.Feature;
import springproject.entity.FeatureValue;
import springproject.entity.Project;
import springproject.entity.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface JsonService {
    String requestToString(HttpServletRequest req) throws IOException;
    boolean isJson(String content);
    JSONObject parseRequest(String body) throws ParseException;

    void flushJSON(JSONObject json, HttpServletResponse res) throws IOException;
    void flushMessage(String message, HttpServletResponse res) throws IOException;

    void flushProjects(HttpServletResponse res, List<Project> projects) throws IOException;
    void flushProject(HttpServletResponse res, int projectId) throws IOException;

    void flushResources(HttpServletResponse res, List<Resource> resources) throws IOException;
    void flushResource(HttpServletResponse res, int resourceId) throws IOException;

    void flushFeatures(HttpServletResponse res, List<Feature> features) throws IOException;
    void flushFeature(HttpServletResponse res, int featureId) throws IOException;

    void flushFeatureValues(HttpServletResponse res, List<FeatureValue> featureValues) throws IOException;
    void flushFeatureValue(HttpServletResponse res, int featureValueId) throws IOException;

}
