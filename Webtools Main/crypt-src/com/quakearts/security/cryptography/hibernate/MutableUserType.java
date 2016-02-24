package com.quakearts.security.cryptography.hibernate;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.type.SerializationException;

public abstract class MutableUserType extends EncryptedTypeBase {
	
	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return deepCopy(owner);
	}

	@Override
	public Serializable disassemble(Object object) throws HibernateException {
		Object deepCopy = deepCopy(object);

        if (!(deepCopy instanceof Serializable)) {
            throw new SerializationException(
                    String.format("deepCopy of %s is not serializable", object), null);
        }
        
        return (Serializable) deepCopy;
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return deepCopy(original);
	}


}
