package com.quakearts.workflowapp.jbpm.util;

import com.quakearts.workflowapp.jbpm.util.UtilityMethods;
import com.quakearts.workflowapp.jbpm.data.JbpmTaskDatasource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.infinispan.manager.CacheContainer;
import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class QuakeArtsjBPMFunctions {
	private static final Logger log= Logger.getLogger(QuakeArtsjBPMFunctions.class);
	private static final JbpmConfiguration config = JbpmConfiguration.getInstance();
	private static Map<String, Object> cache;
	private static Class<?> datasourceclass;
	
	public QuakeArtsjBPMFunctions() {
	}

	public static TaskInstance getPreviousTask(ProcessInstance pi){
		if(pi==null)
			return null;

		JbpmContext jc = config.createJbpmContext();
		if(jc==null){
			log.error("JbpmContext is null");
			return null;
		}
		try{
	
			Session session = jc.getSession();
			Query query;
			query = session.createQuery("select max(ti.id) from org.jbpm.taskmgmt.exe.TaskInstance ti join ti.processInstance pi where pi.id = :pid and ti.end !=null");
			query.setLong("pid",pi.getId());
			Object obj = query.uniqueResult();
			if(obj !=null){
				if(log.isTraceEnabled())
					log.trace("Last task id "+obj+". Type: "+obj.getClass().getCanonicalName());
	
				query = session.createQuery("select ti from org.jbpm.taskmgmt.exe.TaskInstance ti where ti.id = :tid");
				if(obj instanceof Long)
					query.setLong("tid",((Long)obj).longValue());
				else 
					if (obj instanceof Integer)
						query.setInteger("tid",((Integer)obj).intValue());
					else
						query.setString("tid",obj.toString());
	
				obj = query.uniqueResult();
				if(log.isTraceEnabled()){
					if(obj!=null)
						log.trace("Query returned Object: "+obj+". Type: "+obj.getClass().getCanonicalName());                        
					else
						log.error("Query returned null.");
				}
				if(obj instanceof TaskInstance){
					return (TaskInstance) obj;
				}
			}
		} finally {
			jc.close();
		}
		return null;
	}

	public static String getFullName(String userName){
		String fullName;
		fullName = (String) retrieve(userName,String.class);
		if(fullName!=null)
			return fullName;

		fullName = "";
		
		JbpmTaskDatasource datasource = getDatasource();
		datasource.setQueryName("FULLNAMEQUERY");
		datasource.setDetail("USERNAME",userName);
		
		if(datasource.search()){
			fullName = (String) datasource.getDetail("FULLNAME");
			store(userName,fullName);
		} 
		
		return fullName;
	}

	public static String getUserPrincipal(String principalClass) {
		try {
			return getUserPrincipal(Thread.currentThread().getContextClassLoader().loadClass(principalClass));
		} catch (ClassNotFoundException e) {
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown while calling getUserPrincipal(). Message is " + e.getMessage());
			return "";
		}
	}

	@SuppressWarnings("rawtypes")
	public static String getUserPrincipal(Class principalClass) {
		String principal = null;
		Subject subject;
		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown while calling getUserPrincipal(). Message is " + e.getMessage());
			return "";
		}
		log.trace("Extracting user profile info....");
		Set principals = subject.getPrincipals(Group.class);
		for(Iterator i = principals.iterator();i.hasNext();){
			Group roles = (Group) i.next();
			if(roles.getName().equals("Roles"))
			{
				log.trace("Found Roles");
				Enumeration enumer = roles.members();
				while(enumer.hasMoreElements()){
					Object obj = enumer.nextElement();
					if((principalClass).isInstance(obj)){
						principal =((Principal)obj).getName();
						if(log.isTraceEnabled())
							log.trace("Found "+principalClass+" .getName() value "+principal);
						break;
					}
				}
			}
		}
		return principal;
	}
	
	private static Properties getProperties(String filename){
		if(filename==null)
			return null;

		Object obj = retrieve(filename, Properties.class);
		if(obj!=null)
			return (Properties) obj;

		Properties props = new Properties();

		File propFile = new File(filename);
		if(!propFile.exists()){
			filename = JbpmConfiguration.Configs.getString(filename);
			if(filename == null)
				return null;

			propFile = new File(filename);

			if(!propFile.exists())
				return null;
		}

		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			props.load(fis);
			fis.close();
			return props;
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	public static String getProperty(String name, String file){
		if(file=="jbpm")
			return JbpmConfiguration.Configs.getString(name);

		Properties props = getProperties(file);
		if(props!=null)
			return props.getProperty(name);

		return null;
	}

	public static ArrayList<SelectItem> getListFromProperties(String listName){
		ArrayList<SelectItem> list = new ArrayList<SelectItem>();
		Properties props = getProperties(JbpmConfiguration.Configs.getString("com.quakearts.jbpm.properties"));
		if(props !=null){
			String listString = props.getProperty(listName);
			if(listString !=null){
				StringTokenizer tokenizer = new StringTokenizer(listString,";",false);
				while(tokenizer.hasMoreTokens()){
					String token=tokenizer.nextToken();
					int i=token.lastIndexOf(':');
					String value= i==-1||i==token.length()-1?token:token.substring(0,i);
					String label= i==-1||i==token.length()-1?token:token.substring(i+1,token.length());
					list.add(new SelectItem(value,label));
				}
			}
		}
		return list;
	}

	public static JbpmTaskDatasource getDatasource(){
		try {
			if(datasourceclass==null)
				datasourceclass = Class.forName(JbpmConfiguration.Configs.getString("com.quakearts.jbpm.datasource"));
			
			Object datasourceobj;
			datasourceobj = datasourceclass.newInstance();
			if(datasourceobj instanceof JbpmTaskDatasource){
				JbpmTaskDatasource datasource = (JbpmTaskDatasource) datasourceobj;
				return datasource;
			}
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		} catch (InstantiationException e) {
			log.error(e);
		}
		return null;
	}

	public static String getInitiator(ProcessInstance pi){
		log.trace("Getting task initiator...");
		String initiator =(String) retrieve("Initiator-id:"+pi.getId(),String.class);
		if(initiator!=null)
			return initiator;

		if(pi!=null){
			TaskInstance ti = getInitialTask(pi);
			if(ti!=null){
				initiator = ti.getActorId()==null?"":ti.getActorId();
				store("Initiator-id:"+pi.getId(),initiator);
				return initiator;
			}
		}
		return "";
	}

	public static TaskInstance getInitialTask(ProcessInstance pi){
		log.trace("Finding initial task...");
		TaskInstance ti = (TaskInstance) retrieve("InitialTask-id:"+pi.getId(),TaskInstance.class);
		if(ti!=null)
			return ti;
		return getTask(pi,false);
	}

	public static TaskInstance getCurrentTask(ProcessInstance pi){
		log.trace("Finding current task...");        
		return getTask(pi,true);        
	}

	public static TaskInstance getTask(ProcessInstance pi, boolean max){
		JbpmContext jc = config.createJbpmContext();
		if(jc==null)
			return null;
			
		try{
			if(pi!=null){
				Session session = jc.getSession();
				Query query;
				if(max)
					query = session.createQuery("select max(ti.id) from org.jbpm.taskmgmt.exe.TaskInstance ti join ti.processInstance pi where pi.id = :pid");
				else
					query = session.createQuery("select min(ti.id) from org.jbpm.taskmgmt.exe.TaskInstance ti join ti.processInstance pi where pi.id = :pid");
	
				query.setLong("pid",pi.getId());
				Object obj = query.uniqueResult();
				if(obj !=null){
					if(log.isTraceEnabled())
						log.trace((max)?"Max id ":"Min id "+obj+". Type: "+obj.getClass().getCanonicalName());
	
					query = session.createQuery("select ti from org.jbpm.taskmgmt.exe.TaskInstance ti where ti.id = :tid");
					if(obj instanceof Long)
						query.setLong("tid",((Long)obj).longValue());
					else 
						if (obj instanceof Integer)
							query.setInteger("tid",((Integer)obj).intValue());
						else
							query.setString("tid",obj.toString());
	
					obj = query.uniqueResult();
					if(log.isTraceEnabled()){
						if(obj!=null)
							log.trace("Query returned Object: "+obj+". Type: "+obj.getClass().getCanonicalName());                        
						else
							log.error("Query returned null.");
					}
					if(obj instanceof TaskInstance){
						return (TaskInstance) obj;
					}
				}                               
				else
					log.error("Query returned null.");
	
			}else{
				log.error("JbpmConext was null.");           
			}
		}finally{
			jc.close();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static int length(Collection col){
		return col!=null?col.size():0;
	}

	public static String toHexString(byte[] bites){
		StringBuffer hexString;
		hexString = new StringBuffer();
		String plainText;

		for (int i = 0; i < bites.length; i++) {
			plainText = Integer.toHexString(0xFF & bites[i]);
			if (plainText.length() < 2) {
				plainText = "0" + plainText;
			}
			hexString.append(plainText);
		}

		return hexString.toString();

	}
	
	private static Map<String, Object> getCache() {
		if(cache==null){
			try {
				String cacheJndiName = JbpmConfiguration.Configs.getString("com.quakearts.jbpm.cachecontainer");
				if(cacheJndiName==null || cacheJndiName.trim().isEmpty())
					cacheJndiName="java:infinispan/jbpm-main";
				
				CacheContainer container =(CacheContainer) UtilityMethods.getInitialContext().lookup(cacheJndiName);
				cache = container.getCache();
			} catch (NamingException e) {
				log.warn("Warning: CacheContainer could not be loaded. Using a regular ConcurrentHashMap.");
				cache= new ConcurrentHashMap<String,Object>();
			}
		}
		return cache;
	}

	@SuppressWarnings("rawtypes")
	public static Object retrieve(String key, Class clazz){
		if(key==null)
			return null;
			
		Object obj = getCache().get(key);
		if(obj!=null && clazz.isInstance(obj)){
			return obj;
		}
		return null;
	}

	public static void store(String key, Object value){
		if(key==null)
			return;
		getCache().put(key,value);
	}

	public static String getClientId(String componentId) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();
		UIComponent c = findComponent(root, componentId);
		return c.getClientId(context);
	}

	private static UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	public static String getHashText(String plainText, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest mdAlgorithm = MessageDigest.getInstance(algorithm);

		mdAlgorithm.update(plainText.getBytes());

		byte[] digest = mdAlgorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < digest.length; i++) {
			plainText = Integer.toHexString(0xFF & digest[i]);

			if (plainText.length() < 2) {
				plainText = "0" + plainText;
			}

			hexString.append(plainText);
		}
		return hexString.toString();
	}
	
}
