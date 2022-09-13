package com.Biblioteca.Prestamos.Servicios;


import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Repositorio.LibroRepositorio;
import org.apache.el.util.ReflectionUtil;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


@Service
public class libroServicio {

    private LibroRepositorio repositorio;//me va a importar metodos de jpa repository

    //constructor
    public libroServicio(LibroRepositorio repositorio) {
        this.repositorio = repositorio;
    }



    /*.........................LEER o buscar "GET"....................*/
    public ArrayList<Libro> listarLibros(){ //aquí retorno mis datos

        //utilizo metodos del jpa
        //findAll me trae todos los datos de la tabla
        return (ArrayList<Libro>) repositorio.findAll();
    }

    public Optional<Libro> buscarLibro(String isbn){//si existe retorna el libro y si no captura el dato para que quede nilo
        return repositorio.findById(isbn);//retorna datos que se estan buscando
    }

    public ArrayList<Libro> buscarAutor(String autor){
        return repositorio.findByAutor(autor); //tocó ir a LibroRepositorio a crear un arraylist con el findBy
        //luego voy alibroControlador

    }


    /*........................INSERTAR o agregar  "POST"....................*/

    //necesitamos que retorne si se registra exitosamente y que adema´s si existe ya, no salga una EXCEPCION
    public String agregarLibro (Libro libro){
        if(!buscarLibro(libro.getIsbn()).isPresent()){//llamo al metodp buscar libro por id y ispresent me dice si existe
        repositorio.save(libro);//esto solo para agregar sin tener en cuanta la excepcio
        return "Libro registrado exitosamente";
    }else{
            return "El libro ya existe";
        }
    }

    /*.........................MODIFICAR  o actualizar "PUT ".............................*/

    //tambien debo validar que al querer modificar si existe lo deje y si no pues diga que no existe el libro, otra excepcion
    public String actualizarLibro (Libro libro){
        if(buscarLibro(libro.getIsbn()).isPresent()) {
            repositorio.save(libro);
            return "Libro actualizado exitosamente";
        }else{
            return "el libro a modificar no existe";
        }
    }

    /*.........................MODIFICAR O actualizar " PATCH" solo una parte................................*/

    public String actualizarEditorial(String isbn, String editorial){
        if(buscarLibro(isbn).isPresent()){//preginto si el libro existe
            Libro libro1=repositorio.findById(isbn).get();//creo el libro para
                    libro1.setEditorial(editorial);//
                    repositorio.save(libro1);
                    return "Editorial Actualizada";
        }else{
            return "Libro a actiualizar no existe";
        }

    }


    /*.............2 opcion...........MODIFICAR O actualizar " PATCH" solo una parte.............................*/


    public Libro actualizarCampo(String isbn, Map<Object,Object> libroMap){
        Libro libro=repositorio.findById(isbn).get();

        libroMap.forEach((key,value)->{
            Field campo= ReflectionUtils.findField(Libro.class, (String) key);
            campo.setAccessible(true);
            ReflectionUtils.setField(campo, libro, value);
        });
        return repositorio.save(libro);
    }

    /*.........................ELIMINAR O borrar " DELETE" solo una parte.............................*/

    //pregunto si existe y llamo desde epo
    public String eliminarLibro(String isbn){
        if(buscarLibro(isbn).isPresent()){
            repositorio.deleteById(isbn);
            return"Libro Eliminado";
        }else{
            return "El libro a eliminar no existe";
        }

    }



}

