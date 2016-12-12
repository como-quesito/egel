package org.unitec.emulador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapid on 26/07/2016.
 */
@Controller
public class ControladorMongoImagen {
    @Autowired
    GridFsTemplate grid;

    @RequestMapping(value = "/leer-imagen/{nombre:.+}", method = RequestMethod.GET)
    public
    @ResponseBody
    @CrossOrigin
    byte[] culera2(HttpServletResponse response, @PathVariable String nombre) throws IOException {
        GridFSDBFile filesito = grid.findOne(new Query(Criteria.where("filename").is(nombre)));
        File imageFile = new File(nombre);
        filesito.writeTo(imageFile);
        byte[] bytes = FileCopyUtils.copyToByteArray(imageFile);
        System.out.println("Recobrando correctamente con este metodo del todo nuevo");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
        response.setContentLength(bytes.length);
        response.setContentType("image/png");
        return bytes;
    }

    @RequestMapping("/hola-mundo")
    String holaMundo() {
        return "inicio";
    }

    @CrossOrigin
    @RequestMapping(value = "/mensaje",
            method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    String guardarRest(@RequestBody String json) throws Exception {
        Mensaje mm = new Mensaje();

        System.out.println("SE ACTIVO GUARDAR CON MONGO con el sig JSON:" + json);
        ObjectMapper maper = new ObjectMapper();
        Mensaje mensa = maper.readValue(json, Mensaje.class);
        String titulo = mensa.getTitulo();
        String cuerpo = mensa.getCuerpo();
        System.out.println("TITULO:" + mensa.getTitulo() + " CUERPO:" + mensa.getCuerpo());

        //Preparamos la respuesta
        Map map = new HashMap();
        map.put("success", true);
        //Si hubiera un error, aqui enviamos en el map en evz de tru false y agergamos el put de error con el
        //error que desees
        //map.put("errors", "File not found in the specified path.");
        // mm.setTitulo(titulo);
        //  mm.setCuerpo(cuerpo);
        /// servicio.agregarMensaje(mm);
        return maper.writeValueAsString(map);
    }


}