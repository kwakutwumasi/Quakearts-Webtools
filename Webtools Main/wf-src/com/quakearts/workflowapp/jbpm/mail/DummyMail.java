package com.quakearts.workflowapp.jbpm.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.jbpm.JbpmConfiguration;

public class DummyMail extends ServerMail {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	
	@Override
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
	
	@SuppressWarnings("rawtypes")
	public static void send(Properties mailServerProperties, String fromAddress, List recipients, String subject, String text) {
		if(recipients.size()<1)
			return;
		
		String tempLocationString = JbpmConfiguration.Configs.getString("com.quakearts.file.temp.location");
		File dummyMail = new File(tempLocationString+File.separator+"dummy_mail");
		if(!dummyMail.exists())
			dummyMail.mkdirs();
		
		StringBuffer mailBuffer = new StringBuffer().append("Subject: ").append(subject).append("\n")
		.append("From: ").append(fromAddress).append("\n").append("To: ").append(recipients).append("\n")
		.append("<body>\n").append(text).append("</body>");
		
		try {
			File dummyMailFile = new File(dummyMail, "Mail-"+dateFormat.format(new Date())+".txt");
			dummyMailFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(dummyMailFile);
			fos.write(mailBuffer.toString().getBytes());
			fos.close();
		} catch (IOException e) {
			System.out.println("Failed to save dummy mail. "+e.getMessage()+". Mail is \n"+mailBuffer);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2350138264733442441L;

}
