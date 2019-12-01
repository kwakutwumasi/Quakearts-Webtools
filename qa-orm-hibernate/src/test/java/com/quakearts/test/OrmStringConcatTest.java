package com.quakearts.test;

import static org.junit.Assert.*;

import org.junit.Rule;

import static org.hamcrest.core.Is.*; 
import static org.hamcrest.core.IsNull.*; 
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.test.hibernate.Brand;
import com.quakearts.test.hibernate.TestEntity;
import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.stringconcat.ConcatenationListener;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;

public class OrmStringConcatTest {

	private static final String TEST = "PnFJyaWSqE3UCX2dJeWt522aKUWW5syPDAusA85xkvcwRnKqqdgnT4WBM2LvjNW9k7QWfMvGQp3AFE9VHrFWJVT899f7GzQsTtYP7qm88cKjpc6dxBjdYFgtm57GjN7B";

	private final Object[] args = new Object[4];
	
	private ConcatenationListener listener = (beanClass,instance,value,trimmed) -> {
		args[0]=beanClass;
		args[1]=instance;
		args[2]=value;
		args[3]=trimmed;
	};
	
	@Test
	public void testOrmConcat() throws Exception {
		OrmStringConcatUtil.addConcatenationListener(listener);
		TestEntity entity = new TestEntity();
		entity.setId(1);
		//length 10
		entity.setStringColumn1(TEST);
		//length 20
		entity.setStringColumn2(TEST);
		//length 30
		entity.setStringColumn3(TEST);
		//length 255
		entity.setStringColumn4(TEST);
		//Ignore
		entity.setStringColumn5(TEST);
		//Ignore
		entity.setStringColumn6(TEST);
		//Length 40
		entity.setStringColumn7(TEST);
		//Ignore
		entity.setStringColumn8(TEST);
		//Ignore
		entity.setStringColumn9(TEST);
		//Length 10
		entity.setStringColumn10(TEST);
		//Length Integer Max
		entity.setStringColumn11(TEST);
		//Ignore
		entity.setStringColumn12(TEST);
		//Length 10
		entity.setStringColumn13(TEST);
		//Length 10
		entity.setStringColumn14(TEST);
		//Length 10
		entity.setStringColumn15(TEST);
		//Length 10
		entity.setStringColumn16(TEST);
		
		try {
			OrmStringConcatUtil.trimStrings(entity);
			assertThat(entity.getStringColumn1().length(), is(10));
			assertThat(entity.getStringColumn1(), is("PnFJyaWSqE"));
			assertThat(entity.getStringColumn2().length(), is(20));
			assertThat(entity.getStringColumn2(), is("PnFJyaWSqE3UCX2dJeWt"));
			assertThat(entity.getStringColumn3().length(), is(30));
			assertThat(entity.getStringColumn3(), is("PnFJyaWSqE3UCX2dJeWt522aKUWW5s"));
			assertThat(entity.getStringColumn4().length(), is(128));
			assertThat(entity.getStringColumn4(), is(TEST));
			assertThat(entity.getStringColumn5().length(), is(128));
			assertThat(entity.getStringColumn5(), is(TEST));
			assertThat(entity.getStringColumn6().length(), is(128));
			assertThat(entity.getStringColumn6(), is(TEST));
			assertThat(entity.getStringColumn7().length(), is(40));
			assertThat(entity.getStringColumn7(), is("PnFJyaWSqE3UCX2dJeWt522aKUWW5syPDAusA85x"));
			assertThat(entity.getStringColumn8().length(), is(128));
			assertThat(entity.getStringColumn8(), is(TEST));
			assertThat(entity.getStringColumn9().length(), is(128));
			assertThat(entity.getStringColumn9(), is(TEST));
			assertThat(entity.getStringColumn10().length(), is(10));
			assertThat(entity.getStringColumn10(), is("PnFJyaWSqE"));
			assertThat(entity.getStringColumn11().length(), is(128));
			assertThat(entity.getStringColumn11(), is(TEST));
			assertThat(entity.getStringColumn12(), is(TEST));
			assertThat(entity.getStringColumn13().length(), is(10));
			assertThat(entity.getStringColumn13(), is("PnFJyaWSqE"));
			assertThat(entity.getStringColumn14().length(), is(10));
			assertThat(entity.getStringColumn14(), is("PnFJyaWSqE"));
			assertThat(entity.getStringColumn15().length(), is(10));
			assertThat(entity.getStringColumn15(), is("PnFJyaWSqE"));
			assertThat(entity.getStringColumn16().length(), is(10));
			assertThat(entity.getStringColumn16(), is("PnFJyaWSqE"));
			assertTrue(args[0] == TestEntity.class);
			assertThat(args[1], is(entity));
			assertThat(args[2], is(TEST));
			assertThat(args[3], is("PnFJyaWSqE3UCX2dJeWt"));
		} finally {
			OrmStringConcatUtil.removeConcatenationListener(listener);
			args[0]=null;
			args[1]=null;
			args[2]=null;
			args[3]=null;
		}
		entity = new TestEntity();
		entity.setId(1);
		
		OrmStringConcatUtil.trimStrings(entity);
		assertThat(args[0], is(nullValue()));
		assertThat(args[1], is(nullValue()));
		assertThat(args[2], is(nullValue()));
		assertThat(args[3], is(nullValue()));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testConcatNoConcat() throws Exception {
		Brand brand = new Brand();
		brand.setName(TEST+TEST+TEST);
		
		try {
			DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
			dataStore.save(brand);
			dataStore.flushBuffers();
			
			assertThat(brand.getName(), is((TEST+TEST+TEST).substring(0,255)));
		} finally {
			CurrentSessionContextHelper.closeOpenSessions();
		}

		expectedException.expect(DataStoreException.class);
		brand.setName(TEST+TEST+TEST);
		try {
			DataStore dataStore = DataStoreFactory.getInstance()
				.getDataStore("testdomain");
			dataStore.save(brand);
			dataStore.flushBuffers();
		} finally {
			CurrentSessionContextHelper.closeOpenSessions();
		}
	}
	
}
