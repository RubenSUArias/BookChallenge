package com.alura.challengeBook.challengeBook.model;


import jakarta.persistence.*;
//import jakarta.persistence.Id;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "libros")
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;

    private Double numeroDescargas;
    private String autor;
    private String idiomas;
    private Integer fechaNacimiento;
    private Integer fechaDeceso;



    public Libro(){}
    public Libro(DatosLibro datos){
        if (datos.resultados() != null && !datos.resultados().isEmpty()) {
            InfoLibro info = datos.resultados().get(0);  // Se toma el primer resultado

            this.titulo = info.titulo();
            this.numeroDescargas = info.numeroDescargas();


            if (info.autor() != null && !info.autor().isEmpty()) {
                DatosAutor autorInfo = info.autor().get(0);  // Solo una vez
                System.out.println(autorInfo);

                this.autor = autorInfo.autor();

                // Manejo si año de nacimiento o muerte es null
                this.fechaNacimiento = (autorInfo.nacimiento() != null) ? autorInfo.nacimiento() : null;
                this.fechaDeceso = (autorInfo.muerte() != null) ? autorInfo.muerte() : null;

            } else {
                this.autor = "Desconocido";
                this.fechaNacimiento = null;
                this.fechaDeceso = null;
            }



            if (info.idiomas() != null && !info.idiomas().isEmpty()) {
                this.idiomas = String.join(", ", info.idiomas());
            } else {
                this.idiomas = "No especificado";
            }
        } else {
            this.titulo = "Sin título";
            this.numeroDescargas = 0.0;
            this.autor = "Desconocido";
            this.idiomas = "No especificado";

        }
    }
    @Override
    public String toString() {
        return "********Libro************"+ '\n' +
                "titulo='" + titulo + '\n' +
                ", totalDescargas=" + numeroDescargas +'\n' +
                ", autor=" + autor +'\n' +
                ", idioma='" + idiomas +'\n' +
                "*************************";

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }
    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaDeceso() {
        return fechaDeceso;
    }

    public void setFechaDeceso(Integer fechaDeceso) {
        this.fechaDeceso = fechaDeceso;
    }

}