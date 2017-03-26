package com.quakearts.security.cryptography.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.quakearts.security.cryptography.IllegalCryptoActionException;

public class EncryptedStringType extends ImmutableUserType {

	private static final int[] TYPES = new int[]{Types.VARCHAR};

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {		
		
		String encrypted = rs.getString(names[0]);
		
		if(rs.wasNull()){
			return null;
		}else{
			try {
				return getCryptoResource().doDecrypt(encrypted);
			} catch (IllegalCryptoActionException e) {
				throw new HibernateException("Exception " + e.getClass().getName() + ". Message is "
						+ e.getMessage(),e);
			}
		}		
	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {	
		
		if(value == null){
			ps.setNull(index, Types.VARCHAR);
		}else {
			try {
				String encrypted = getCryptoResource().doEncrypt(value.toString());
				ps.setString(index, encrypted);
			} catch (IllegalCryptoActionException e) {
				throw new HibernateException("Exception " + e.getClass().getName() + ". Message is "
						+ e.getMessage(),e);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return String.class;
	}

	@Override
	public int[] sqlTypes() {
		return TYPES;
	}
}
