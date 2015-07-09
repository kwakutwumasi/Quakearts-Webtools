package com.quakearts.security.cryptography.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;

import com.quakearts.security.cryptography.IllegalCryptoActionException;

public class EncryptedBytesType extends MutableUserType 
{

    private static int[] TYPES = new int[]{ Types.BINARY };
	
	@Override
	public Object deepCopy(Object object) throws HibernateException {
		if(object ==null)
			return null;
		
		if(!(object instanceof byte[]))
			throw new HibernateException("Encrypted bytes must be mapped to objects of type byte[]");
		
		byte[] bites = (byte[]) object;	
		
		byte[] copy = new byte[bites.length];
		System.arraycopy(bites, 0, copy, 0, bites.length);
		
		return copy;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		
		byte[] encrypted = rs.getBytes(names[0]);
		
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
	public void nullSafeSet(PreparedStatement ps, Object value, int index)
			throws HibernateException, SQLException {
		
		if(value == null){
			ps.setNull(index, Types.VARCHAR);
		}else {
			try {
				byte[] encrypted = getCryptoResource().doEncrypt((byte[])value);
				ps.setBytes(index, encrypted);
			} catch (IllegalCryptoActionException e) {
				throw new HibernateException("Exception " + e.getClass().getName() + ". Message is "
						+ e.getMessage(),e);
			}
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return byte[].class;
	}

	@Override
	public int[] sqlTypes() {
		return TYPES;
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		if(object==null)
			return 0;
		
		byte[] bytes = (byte[])object;
				
		int hash = 0;
		for(int i=0; i<bytes.length;i++)
			hash = hash*17 + bytes[i];
		
		return hash;		
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		
		if(x==y)
			return true;
		
		if(x==null)
			return y==null;
		
		if(!(y instanceof byte[]))
			return false;
				
		byte[] bytes1 = (byte[]) x, bytes2 = (byte[]) y;
		
		if(bytes1.length != bytes2.length)
			return false;

		for(int i =0;i<bytes1.length;i++)
			if(bytes1[i]!=bytes2[2])
				return false;
		
		return true;
	}

}
