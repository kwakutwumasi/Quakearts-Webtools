package com.quakearts.security.cryptography.hibernate;

import java.io.Serializable;
import org.hibernate.HibernateException;

public abstract class ImmutableUserType extends EncryptedTypeBase {

	@Override
	public Object assemble(Serializable cached, Object object)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object object) throws HibernateException {
		return object;
	}

	@Override
	public Serializable disassemble(Object object) throws HibernateException {
		return (Serializable) object;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if(x==y)
			return true;
		
		if(x==null)
			return false;
		
		return x.equals(y);
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		if(object == null)
			return 0;
			
		return object.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}
}
