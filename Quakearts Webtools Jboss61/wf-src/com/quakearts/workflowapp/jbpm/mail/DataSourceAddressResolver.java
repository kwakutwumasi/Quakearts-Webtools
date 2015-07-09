package com.quakearts.workflowapp.jbpm.mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.mail.AddressResolver;
import com.quakearts.workflowapp.jbpm.util.UtilityMethods;

public class DataSourceAddressResolver implements AddressResolver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5224796507940835082L;
	private static final Logger log = Logger.getLogger(DataSourceAddressResolver.class);
	
	@Override
	public Object resolveAddress(String actorId) {
		
		String dsJndi = JbpmConfiguration.Configs.getString("address.resolver.ds"),
		query = JbpmConfiguration.Configs.getString("address.resolver.query"),
		coloum = JbpmConfiguration.Configs.getString("address.resolver.column");
		
		InitialContext icx = UtilityMethods.getInitialContext();
		
		try {
			DataSource ds = (DataSource) icx.lookup(dsJndi);
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, actorId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String emails = rs.getString(coloum);
				return emails.split(";");
			}else{
				throw new NullPointerException("No email for "+actorId);
			}
		} catch (Exception e) {
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage());
			return new String[0];
		}
	}

}
