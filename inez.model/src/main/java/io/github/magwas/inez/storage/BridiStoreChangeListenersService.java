package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.github.magwas.inez.BridiStoreChangeListener;

@Component
public class BridiStoreChangeListenersService {
	public final List<BridiStoreChangeListener> listeners = new ArrayList<>();
}
