package com.cursojava.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Column(name = "id")
    @Getter @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    @Column(name = "apellido")
    @Getter @Setter
    private String apellidos;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "telefono")
    @Getter @Setter
    private String telefono;

    @Column(name = "password")
    @Getter @Setter
    private String password;


    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
/* 2) Creación del Entity

   6) Se agregarán unas anotaciones para indicar a este usuario que tabla debe utilizar

   $ @Table: Se utiliza para especificar los detalles de la tabla que se usará para persitir en la BD, permite a demás
     crear índices, lo cual es util cuando le dices a que cree tablas por ti y aunque la anotación es opcional, si no la
     proporcionamos JPA asumirá que el nombre de la clase es la misma de la tabla de la BD
   $ @Entity: Una entidad que hará referencia a la BD y la cual requerirá tener una @anotación de ID de persitencia

   Adicionalmente, sustituiremos los Getter and Setter con la siguiente anotación:
   $ @Getter @Setter: asi ya no tendremos que crear dichas funciones
   $ @Column(name= "nom_atributo_BD"): No basta con indicar la tabla que debera gestionar, sino que también debemos indicar
     que columna es según el nombre, asi que se lo tenemos que decir de manera manual mediante esta anotación.
   $ @GeneratedValue: para cuando el dato/id es auto incremental
 */
