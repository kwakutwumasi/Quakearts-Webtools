package ${package};

import javax.inject.Inject;


public class TestInit
{
	@Inject 
	private AppInit appInit;
	
    public void init() {
    	appInit.init();
    }
}
