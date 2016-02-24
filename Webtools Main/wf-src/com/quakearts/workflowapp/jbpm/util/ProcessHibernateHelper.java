package com.quakearts.workflowapp.jbpm.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;


public class ProcessHibernateHelper {
	private static final Map<Long, SessionFactory> factories = new HashMap<Long, SessionFactory>();
	
	private ProcessHibernateHelper(){
	}
	
	public static SessionFactory getSessionFactory(ProcessDefinition pd) throws Exception {
		SessionFactory factory;
		if((factory = factories.get(pd.getId())) == null){
			final StringBuffer buffer = new StringBuffer();
            buffer.append(pd.getId());
            buffer.append("/");
            buffer.append("process.cfg.xml");
			factory = new Configuration().configure(new URL("par", "", 0, buffer.toString(), new FileDefinitionURLStreamHandler(pd.getFileDefinition(), "process.cfg.xml"))).buildSessionFactory();
			factories.put(pd.getId(), factory);
		}
		return factory;
	}
	
    private static final class FileDefinitionURLStreamHandler extends URLStreamHandler {
        private final FileDefinition fileDefinition;
        private final String src;

        public FileDefinitionURLStreamHandler(final FileDefinition fileDefinition, final String src) {
            this.fileDefinition = fileDefinition;
            this.src = src;
        }

        protected URLConnection openConnection(URL url) {
            return new ProcessHibernateHelper.FileDefinitionURLConnection(url, fileDefinition, src);
        }
    }

    private static final class FileDefinitionURLConnection extends URLConnection {
        private final FileDefinition fileDefinition;
        private final String src;

        protected FileDefinitionURLConnection(final URL url, final FileDefinition fileDefinition, final String src) {
            super(url);
            this.fileDefinition = fileDefinition;
            this.src = src;
        }

        public void connect() {
        }

        public InputStream getInputStream() throws FileNotFoundException {
            final InputStream inputStream = fileDefinition.getInputStream(src);
            if (inputStream == null) {
                throw new FileNotFoundException("File '" + src + "' not found in process file definition");
            }
            return inputStream;
        }
    }
	
}
