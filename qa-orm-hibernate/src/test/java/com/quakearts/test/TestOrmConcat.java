package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*; 
import org.junit.Test;

import com.quakearts.test.hibernate.TestEntity;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;

public class TestOrmConcat {

	private static final String TEST = "PnFJyaWSqE3UCX2dJeWt522aKUWW5syPDAusA85xkvcwRnKqqdgnT4WBM2LvjNW9k7QWfMvGQp3AFE9VHrFWJVT899f7GzQsTtYP7qm88cKjpc6dxBjdYFgtm57GjN7B";

	@Test
	public void testOrmConcat() throws Exception {
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
	}

}
