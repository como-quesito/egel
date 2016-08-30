package org.unitec.emulador;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by campitos on 30/08/16.
 */
@SpringComponent
@UIScope
public interface RepositorioReactivos extends MongoRepository<Reactivo,String> {

  List<Reactivo> findByTema(String tema);
}
