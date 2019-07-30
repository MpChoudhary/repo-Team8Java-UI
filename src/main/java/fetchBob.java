import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name="/fetchBob")
public class fetchBob extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String  username = req.getParameter("username"),
				password = req.getParameter("password");
		
		// Parse request(JSON) into string
		String body = req.getReader().lines().collect(Collectors.joining());
		JSONObject json = new JSONObject();
		
		// New student setter
		StudentTest newStudent = new StudentTest();
		newStudent.setPassword(body.split(",")[1].split(":")[1].replaceAll("\"", ""));
		newStudent.setName(body.split(",")[0].split(":")[1].replaceAll("\"", ""));
		
		// Put as json object 
		json.put("message", "request success");
		json.put("username", newStudent.getName());
		json.put("password", newStudent.getPassword());
		
		// Flush the data back to postman
        PrintWriter pw = res.getWriter();
        pw.println(json.toString());
	}
}