package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component 
public class BridiStoreChangeListeners {
	List<BridiStoreChangeListener> listeners = new ArrayList<>();
}