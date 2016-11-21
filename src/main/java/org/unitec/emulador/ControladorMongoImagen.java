package org.unitec.emulador;

import com.mongodb.gridfs.GridFSDBFile;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    /*
GUARDAR IMAGEN ELN MONGODB
 */
    @RequestMapping(value="/cargar-mongo1", method=RequestMethod.POST, headers={"Accept=text/html"})
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file)throws Exception{
        String nombre=file.getOriginalFilename();
        String prefijo="";
        DateTime date=new DateTime();
        ;
        int dia=  date.getDayOfYear();
        int segundo=  date.getSecondOfDay();
        long tamano= file.getSize();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (file.getSize() > 0) {
            inputStream = file.getInputStream();


            String contenido=  file.getContentType();
            int punto=nombre.indexOf(".");
            String nombreSolo=nombre.substring(0, punto);
            prefijo="dia"+dia+"segundo"+segundo;
            //   System.out.println("nombre de archivo:"+fileName);
            //Guardamos imagen, si es que hay
        // gridFsTemplate.store(inputStream,prefijo+nombre,file.getContentType());
              //Lo impresionante aqui es que el repositorio tambien tiene la opcion para
            //guardar un archivo!!!
             // grid.store(inputStream,prefijo+nombre,file.getContentType());
        }
        System.out.println("El nombre de archivaldo es:" + nombre + " el tamaño del archivo esta:" + tamano + " se guardo como: " + prefijo + nombre);

        return  prefijo+nombre;

    }
}
