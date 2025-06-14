package com.alura.challengeBook.challengeBook.principal;
import com.alura.challengeBook.challengeBook.model.InfoLibro;
import com.alura.challengeBook.challengeBook.model.Libro;
import com.alura.challengeBook.challengeBook.repository.LibroRepository;
import com.alura.challengeBook.challengeBook.service.ConsumoAPI;
import com.alura.challengeBook.challengeBook.service.ConvierteDatos;
import com.alura.challengeBook.challengeBook.model.DatosLibro;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.List;

public class Principal {
    private final Scanner teclado= new Scanner(System.in);
    private final ConsumoAPI consumoApi= new ConsumoAPI();
    private final String URL_BASE="https://gutendex.com/books/";

    private final ConvierteDatos conversor =new ConvierteDatos();
    private LibroRepository repositorio;


    public Principal(LibroRepository repository) {
        this.repositorio=repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = "1 - Buscar libro por titulo\n" +
                       "2 - Buscar libros registrados\n" +
                       "3.- Listar autores registrados\n" +
                       "4 - Autores vivos en un determinado año\n" +
                       "5.- Listar libros por idioma\n" +
                       "\n" +
                       "\n" +
                       "0 - Salir\n";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivosEnAnio();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void autoresVivosEnAnio() {
        System.out.println("Dime el año que quieres buscar:");
        Integer anio = Integer.valueOf(teclado.nextLine());
        List<String> autoresEnAnio =repositorio.findAutoresVivosEnAnio(anio);
        System.out.println("******************************** \n "+ "Autores vivos en "+ anio);
        autoresEnAnio.forEach(System.out::println);
        System.out.println("\n" +"\n"+"******************************** ");
    }

    private void librosPorIdioma() {
        System.out.println("******************************** \n "+ "Idiomas con Registros");
        for (String s : repositorio.findIdiomasUnicos()) {
            System.out.println(s);
        }
        System.out.println("\n" +"Elige uno"+"******************************** ");

        String idiom = teclado.nextLine();

        List<Libro> librosEnIdioma = repositorio.findByIdioma(idiom);
        librosEnIdioma.forEach(System.out::println);
    }

    private void autoresRegistrados() {
        List<Libro> libros = repositorio.findAll();
        //libros.forEach(System.out::println);
        System.out.println("******************************** \n "+ "Autores");
        libros.forEach(libro -> System.out.println(libro.getAutor()));
        System.out.println("\n" +"\n"+"******************************** ");
    }

    private void buscarLibrosRegistrados() {
        List<Libro> libros = repositorio.findAll();
        libros.forEach(System.out::println);
        //libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        System.out.println(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var json = consumoApi.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        System.out.println(json);
        System.out.println("getDatosLibro");
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        Optional<InfoLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
        return datos;

    }
    private void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        Optional<Libro> existente = repositorio.findByTitulo(libro.getTitulo());
        if (existente.isEmpty()) {
            repositorio.save(libro);
            System.out.println("Libro guardado: " + libro);
        } else {
            System.out.println("El libro ya existe en la base de datos: " + libro.getTitulo());
        }
        //datosSeries.add(datos);
        //System.out.println(libro);
    }



}
