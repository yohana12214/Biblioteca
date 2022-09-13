package com.Biblioteca.Prestamos.Entidades;

import javax.persistence.*;
import java.util.Set;

//creo estudiante como entidad crre una base datos y que va a ser una tabla
@Entity
@Table(name="Estudiante")
public class Estudiante {

    @Id
    @Column(unique=true,length=30)
   private String documento;
    @Column(nullable = false,length = 50)
   private String nombre;
    @Column(nullable = false,length = 50)
   private String Apellido;
    @Column(nullable = false,length = 500)
   private String email;
    @Column(nullable = false,length = 50)
   private String telefono;



    @OneToMany(mappedBy = "estudiante",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public Set<Prestamo> prestamos;//captura datos


   // Constructores
    public Estudiante() {
    }

    //atributos y anotaciones
    public Estudiante(String documento, String nombre, String apellido, String email, String telefono) {

        this.documento = documento;
        this.nombre = nombre;
        Apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }


    //gett y set

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
