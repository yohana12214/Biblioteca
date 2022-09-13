package com.Biblioteca.Prestamos.Entidades;

//esta clase tiene un id autoincrementable, llave foránea y

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="Prestamo")
public class Prestamo {
    @Id
    @Column(unique=true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)//Generar el id autoincrementable
    private int id_prestamo;

    @ManyToOne(fetch=FetchType.LAZY,optional=false) //asociacion muchos a uno. Prestamo a Estudiante
    @JoinColumn(name="documento",referencedColumnName = "documento",nullable=false)//unir columna, exactamente con la columna documento de Estudiante
    @JsonIgnore
    private Estudiante estudiante; //establezco con que tabla hago ese tipo de relcio

    @ManyToOne(fetch=FetchType.LAZY,optional=false) //recorrer lo que este asociado a ese estudiante
    @JoinColumn(name="isbn",referencedColumnName = "isbn",nullable=false)//unir columna, exactamente con la columna isbn de Libro, reafirma asociación
    @JsonIgnore//no lo toma
    private Libro libro;

    @Temporal(TemporalType.DATE)//para que solo con fecha sin hora
    @Column(nullable=false)//no puede ser nula esta columna
    private Date fecha;

    //fecha automátaca
    @PrePersist
    public void prePersist(){ //al momento de insertar un prestamo se actualiza automaticamente la fecha
        this.fecha=new Date();
    }


    //Constructores
    public Prestamo() {
    }

    public Prestamo(int id_prestamo, Estudiante estudiante, Libro libro, Date fecha) {
        this.id_prestamo = id_prestamo;
        this.estudiante = estudiante;
        this.libro = libro;
        this.fecha = fecha;
    }

    //getter y setter

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    //metodo toString


    @Override
    public String toString() {
        return "Prestamos{" +
                "id_prestamo=" + id_prestamo +
                ", estudiante=" + estudiante +
                ", libro=" + libro +
                ", fecha=" + fecha +
                '}';
    }
}
