package io.github.magwas.inez;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.storage.BridiStoreChangeListener;

@Component
public class InezFactory  implements ApplicationContextAware{
	    private static ApplicationContext _appCtx;

	    @Override
	    public void setApplicationContext(ApplicationContext ctx){
	         _appCtx = ctx;
	    }

	    public static InezFactory getInstance(){
	        return _appCtx.getBean(InezFactory.class);
	    } 
	    
	    public List<Bridi> getChildren(Bridi parent) {
	    	return null;
	    }

	    public List<Bridi> getElements(Bridi parent) {
	    	return null;
	    }

	    public Bridi getParent(Bridi parent) {
	    	return null;
	    }

	    public boolean hasChildren(Bridi parent) {
	    	return false;
	    }

	    public  void registerListener(BridiStoreChangeListener listener) {
	    	
	    }

	    public void unregisterListener(BridiStoreChangeListener listener) {
	    	
	    }
}

