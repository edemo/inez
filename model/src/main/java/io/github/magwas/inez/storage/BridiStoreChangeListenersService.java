package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.github.magwas.inez.BridiStoreChangeListener;
import lombok.Data;

@Component
@Data
public class BridiStoreChangeListenersService {
	final List<BridiStoreChangeListener> listeners = new ArrayList<>();
}