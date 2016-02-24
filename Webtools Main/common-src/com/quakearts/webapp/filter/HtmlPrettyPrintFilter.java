package com.quakearts.webapp.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class HtmlPrettyPrintFilter implements Filter {
	protected FilterConfig config;
	protected final static Logger log = Logger.getLogger(HtmlPrettyPrintFilter.class.getName());
	
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		ServletResponse newResponse = response;
		if ((request instanceof HttpServletRequest)) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			newResponse = new CharResponseWrapper(httpResponse);

			chain.doFilter(request, newResponse);
			try {
				if (((newResponse instanceof CharResponseWrapper))
						&& (((CharResponseWrapper) newResponse).isGetWriterCalled())) {
					String text = newResponse.toString();
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					dbf.setValidating(false);
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					Document doc = db.parse(new ByteArrayInputStream(text.getBytes()));
					Transformer tf = TransformerFactory.newInstance().newTransformer();
					tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					tf.setOutputProperty(OutputKeys.INDENT, "yes");
					tf.transform(new DOMSource(doc), new StreamResult(response.getWriter()));
				}				
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles pretty printing.");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
