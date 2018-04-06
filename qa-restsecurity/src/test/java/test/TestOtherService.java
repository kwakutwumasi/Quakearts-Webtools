package test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TestOtherService {
	void processSecured(HttpServletRequest request, HttpServletResponse response) throws IOException;
	void processUnSecured(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
