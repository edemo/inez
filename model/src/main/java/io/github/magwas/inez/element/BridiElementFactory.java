package io.github.magwas.inez.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

@Service
public class BridiElementFactory {

	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;

	public BridiElement apply(String id) {
		BridiElement element = new BridiElement(id);
		autowireCapableBeanFactory.autowireBean(element);
		element.fixParent();
		return element;
	}

}
