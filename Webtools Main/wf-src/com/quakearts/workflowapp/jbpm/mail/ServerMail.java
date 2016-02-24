package com.quakearts.workflowapp.jbpm.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmException;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.mail.AddressResolver;
import org.jbpm.mail.Mail;
import org.jbpm.util.ClassLoaderUtil;
import org.jbpm.util.XmlUtil;
import com.quakearts.workflowapp.jbpm.util.UtilityMethods;

public class ServerMail extends Mail {
    private static Logger log = Logger.getLogger(com.quakearts.workflowapp.jbpm.mail.ServerMail.class);
    private static final long serialVersionUID = 1L;
    private MailVariableEvaluator evaluator = new MailVariableEvaluator();

    String template = null;
    String actors = null;
    String to = null;
    String subject = null;
    String text = null;
    
    ExecutionContext executionContext = null;

    public ServerMail() {
        log.trace("Instantiated...");
    }
    
    public ServerMail(String template, String actors, String to, String subject, String text) {
        super(template,actors,to,subject,text);
        log.trace("Instantiated...\ntemplate="+template+"\nactors="+actors+"\nsubject="+subject+"\ntext="+text);
    }   
    @SuppressWarnings("rawtypes")
	public static void send(Properties mailServerProperties, String fromAddress, List recipients, String subject, String text) {
        Session session;
        if ( (recipients==null) || (recipients.isEmpty())) {
            log.trace("Skipping mail because there are no recipients");
            return;
        }
        log.trace("Sending email to '"+recipients+"' about '"+subject+"'");
        InitialContext icx;
        try {
            icx = UtilityMethods.getInitialContext();
            session = (Session) icx.lookup("java:/Mail");
        } catch (NamingException e) {
            throw new JbpmException("couldn't send email", e);
        }
        MimeMessage message = new MimeMessage(session);
        try {
            if (fromAddress!=null) {
                message.setFrom(new InternetAddress(fromAddress));
        }
        Iterator iter = recipients.iterator();
        while (iter.hasNext()) {
            InternetAddress recipient = new InternetAddress((String) iter.next());
            message.addRecipient(Message.RecipientType.TO, recipient);
        }
        if (subject!=null) {
            message.setSubject(subject);
        }
        if (text!=null) {
            message.setText(text);
        }
        message.setSentDate(new Date());
        
        Transport.send(message);
        } catch (Exception e) {
            String tempfilelocation = JbpmConfiguration.Configs.getString("com.quakearts.file.temp.location");
            log.trace("tempfilelocation ='"+tempfilelocation+"'");
            String storelocation = tempfilelocation+"\\jpbm_email_error";
            File loc;
            loc = new File(storelocation);
            if(!loc.exists()){
                log.trace("Making directory "+loc.toURI());
                loc.mkdir();
            }
            
            FileOutputStream fos=null;
            try {
                fos = new FileOutputStream(storelocation+"\\Mail-"+new Date()+".log");
                fos.write("Exeption:".getBytes());                    
                fos.write(e.getMessage()!=null?e.getMessage().getBytes():"".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("----Mail Details----\r\nFrom:".getBytes());                    
                fos.write(fromAddress!=null?fromAddress.getBytes():"".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("To:".getBytes());                    
                fos.write(recipients!=null?recipients.toString().getBytes():"".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("Subject:".getBytes());                    
                fos.write(subject!=null?subject.getBytes():"".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("Text:".getBytes());                    
                fos.write(text!=null?text.getBytes():"".getBytes());
                fos.write("\r\n".getBytes());
                fos.close();
            } catch (FileNotFoundException f) {
                log.error("Couldn't log failed email ",e);
            } catch (IOException f) {
                log.error("Couldn't log failed email ",e);
            } catch (Exception f){
                log.error("Couldn't log failed email ",e);                    
            }
        }
    }
            
    public void execute(ExecutionContext executionContext) {
        this.executionContext = executionContext;
        send();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRecipients() {
        List recipients = new ArrayList();
        if (actors!=null) {
          String evaluatedActors = evaluateCustom(actors);
          List tokenizedActors = tokenize(evaluatedActors);
          if (tokenizedActors!=null) {
            recipients.addAll(resolveAddresses(tokenizedActors));
          }
        }
        if (to!=null) {
          String resolvedTo = evaluateCustom(to);
          recipients.addAll(tokenize(resolvedTo));
        }
        return recipients;
    }
    
    public String getSubject() {
        if (subject==null) return null;
        return evaluateCustom(subject);
    }
    
    public String getText() {
        if (text==null) return null;
        return evaluateCustom(text);
    }
    
    public String getFromAddress() {
        if (JbpmConfiguration.Configs.hasObject("jbpm.mail.from.address")) {
          return JbpmConfiguration.Configs.getString("jbpm.mail.from.address");
        } 
        return "jbpm@noreply";
    }
    
    public void send() {
        if (template!=null) {
          Properties properties = getMailTemplatePropertiesCustom(template);
          if (actors==null) {
            actors = properties.getProperty("actors");
          }
          if (to==null) {
            to = properties.getProperty("to");
          }
          if (subject==null) {
            subject = properties.getProperty("subject");
          }
          if (text==null) {
            text = properties.getProperty("text");
          }
        }
        
        send(getMailServerPropertiesCustom(), 
                getFromAddress(), 
                getRecipients(), 
                getSubject(), 
                getText());
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected List tokenize(String text) {
        if (text==null) {
          return null;
        }
        List list = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(text, ",;:");
        while (tokenizer.hasMoreTokens()) {
          list.add(tokenizer.nextToken());
        }
        return list;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected Collection resolveAddresses(List actorIds) {
        List emailAddresses = new ArrayList();
        Iterator iter = actorIds.iterator();
        while (iter.hasNext()) {
          String actorId = (String) iter.next();
          AddressResolver addressResolver = (AddressResolver) JbpmConfiguration.Configs.getObject("jbpm.mail.address.resolver");
          Object resolvedAddresses = addressResolver.resolveAddress(actorId);
          if (resolvedAddresses!=null) {
            if (resolvedAddresses instanceof String) {
              emailAddresses.add((String)resolvedAddresses);
            } else if (resolvedAddresses instanceof Collection) {
              emailAddresses.addAll((Collection)resolvedAddresses);
            } else if (resolvedAddresses instanceof String[]) {
              emailAddresses.addAll(Arrays.asList((String[])resolvedAddresses));
            } else {
              throw new JbpmException("Address resolver '"+addressResolver+"' returned '"+resolvedAddresses.getClass().getName()+"' instead of a String, Collection or String-array: "+resolvedAddresses);
            }
          }
        }
        return emailAddresses;
    }
    
    Properties getMailServerPropertiesCustom() {
        Properties mailServerProperties = new Properties();
        
        if (JbpmConfiguration.Configs.hasObject("resource.mail.properties")) {
          String mailServerPropertiesResource = JbpmConfiguration.Configs.getString("resource.mail.properties");
          try {
            InputStream mailServerStream = ClassLoaderUtil.getStream(mailServerPropertiesResource);
            mailServerProperties.load(mailServerStream);
          } catch (Exception e) {
            throw new JbpmException("couldn't get configuration properties for jbpm mail server from resource '"+mailServerPropertiesResource+"'", e);
          }
        
        } else if (JbpmConfiguration.Configs.hasObject("jbpm.mail.smtp.host")) {
          String smtpServer = JbpmConfiguration.Configs.getString("jbpm.mail.smtp.host");
          mailServerProperties.put("mail.smtp.host", smtpServer);
          
        } else {
          
          log.error("couldn't get mail properties");
        }
        
        return mailServerProperties;
    }
    
    @SuppressWarnings("rawtypes")
	static Map templates = null;
    @SuppressWarnings("rawtypes")
	static Map templateVariables = null;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	synchronized Properties getMailTemplatePropertiesCustom(String templateName) {
        if (templates==null) {
          templates = new HashMap();
          String mailTemplatesResource = JbpmConfiguration.Configs.getString("resource.mail.templates");
          org.w3c.dom.Element mailTemplatesElement = XmlUtil.parseXmlResource(mailTemplatesResource,false).getDocumentElement();
          List mailTemplateElements = XmlUtil.elements(mailTemplatesElement, "mail-template");
          Iterator iter = mailTemplateElements.iterator();
          while (iter.hasNext()) {
            org.w3c.dom.Element mailTemplateElement = (org.w3c.dom.Element) iter.next();
        
            Properties templateProperties = new Properties();
            addTemplatePropertyCustom(mailTemplateElement, "actors", templateProperties);
            addTemplatePropertyCustom(mailTemplateElement, "pooledActors", templateProperties);
            addTemplatePropertyCustom(mailTemplateElement, "to", templateProperties);
            addTemplatePropertyCustom(mailTemplateElement, "subject", templateProperties);
            addTemplatePropertyCustom(mailTemplateElement, "text", templateProperties);
        
            templates.put(mailTemplateElement.getAttribute("name"), templateProperties);
          }
        
          templateVariables = new HashMap();
          List variableElements = XmlUtil.elements(mailTemplatesElement, "variable");
          iter = variableElements.iterator();
          while (iter.hasNext()) {
            org.w3c.dom.Element variableElement = (org.w3c.dom.Element) iter.next();
            templateVariables.put(variableElement.getAttribute("name"), variableElement.getAttribute("value"));
          }
        }
        return (Properties) templates.get(templateName);
    }
    
    void addTemplatePropertyCustom(org.w3c.dom.Element mailTemplateElement, String property, Properties templateProperties) {
        org.w3c.dom.Element element = XmlUtil.element(mailTemplateElement, property);
        if (element!=null) {
          templateProperties.put(property, XmlUtil.getContentText(element));
        }
    }
    
    String evaluateCustom(String expression) {
        log.trace("Evaluating: "+expression);
        expression = evaluator.evaluate(expression,executionContext);        
        if (expression==null) {
            return "";
        }
        VariableResolver variableResolver = JbpmExpressionEvaluator.getUsedVariableResolver();
        if (variableResolver!=null) {
            variableResolver = new MailVariableResolver(templateVariables, variableResolver);
        }
        Object obj = JbpmExpressionEvaluator.evaluate(expression, executionContext, variableResolver, null);
        log.trace("Evaluating: "+obj.getClass().getCanonicalName() +"; toString() = "+obj);
        if(obj instanceof String)
            return obj.toString();
        else
            return evaluator.toString(obj);
    }
    
    class MailVariableResolver implements VariableResolver, Serializable {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("rawtypes")
		Map templateVariables = null;
        VariableResolver variableResolver = null;
        
        @SuppressWarnings("rawtypes")
		public MailVariableResolver(Map templateVariables, VariableResolver variableResolver) {
          this.templateVariables = templateVariables;
          this.variableResolver = variableResolver;
        }
        
        public Object resolveVariable(String pName) throws ELException {
          if ( (templateVariables!=null)
               && (templateVariables.containsKey(pName))
             ){
            return templateVariables.get(pName);
          }
          return variableResolver.resolveVariable(pName);
        }
    }

}
