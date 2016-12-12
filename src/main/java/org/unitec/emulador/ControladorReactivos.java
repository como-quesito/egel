package org.unitec.emulador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by campitos on 12/12/16.
 */
@RestController
public class ControladorReactivos {
    @Autowired RepositorioReactivos repo;

    @CrossOrigin
    @RequestMapping("/reactivo")
    public List<Reactivo> leerTodos()throws Exception{


        return repo.findAll();
    }
}
