package com.danielesteban.music_app.service.common;

import java.io.Serializable;
import java.util.Set;

public interface SimpleService<E extends Serializable, ID> {
	
	 E findById(ID id);
	 Set<E> getAll();
	 E save(E model);
	 void delete(ID id);
	 E update(E model, ID id);

}
