package springproject.service;

import com.sun.org.apache.regexp.internal.RE;
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
    void flushProject(HttpServletResponse res, List<Project> projects, int projectId) throws IOException;

    void flushResources(HttpServletResponse res, List<Resource> resources) throws IOException;
    void flushResource(HttpServletResponse res, List<Resource> resources, int projectId) throws IOException;

    void flushFeatures(HttpServletResponse res, List<Feature> features) throws IOException;
    void flushFeature(HttpServletResponse res, List<Feature> features, int projectId) throws IOException;

    void flushFeatureValues(HttpServletResponse res, List<FeatureValue> featureValues) throws IOException;
    void flushFeatureValue(HttpServletResponse res, List<FeatureValue> featureValues, int projectId) throws IOException;

}
