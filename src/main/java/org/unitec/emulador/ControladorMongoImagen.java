package org.unitec.emulador;

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

/**
 * Created by rapid on 26/07/2016.
 */
@Controller
public class ControladorMongoImagen {
    @Autowired
    GridFsTemplate grid;

    @RequestMapping(value="/leer-imagen/{nombre:.+}", method= RequestMethod.GET)
    public @ResponseBody
    @CrossOrigin
    byte[] culera2(HttpServletResponse response, @PathVariable String nombre)throws IOException {
        GridFSDBFile filesito=grid.findOne(new Query(Criteria.where("filename").is(nombre)));
        File imageFile=new File(nombre);
        filesito.writeTo(imageFile);
        byte[] bytes= FileCopyUtils.copyToByteArray(imageFile);
        System.out.println("Recobrando correctamente con este metodo del todo nuevo");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
        response.setContentLength(bytes.length);
        response.setContentType("image/png");
        return bytes;
    }

    @RequestMapping("/hola-mundo")
    String holaMundo(){
        return "inicio";
    }
}
