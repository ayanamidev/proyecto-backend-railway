package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
//Esta clase es un controlador REST.
// Va a manejar peticiones HTTP como GET, POST, PUT, DELETE... y va a devolver datos (JSON).
@RestController
//Todas las rutas de esta clase van a empezar por /user
@RequestMapping("/user")
public class UserController {

    //Instancia de UserService en esta clase.
    /*UserController no debería trabajar directamente con la base de datos.
        En lugar de eso, le dice a su ayudante userService:
            “Oye, tráeme todos los usuarios”
            “Oye, guarda este nuevo usuario”
            “Oye, borra este id”
      Así el código queda más limpio y organizado.*/

    /*¿Por qué hay que usar @Autowired si ya tengo la clase creada en mi proyecto?
        La respuesta corta es:
            Porque no queremos crear las instancias nosotros manualmente.
            Queremos que Spring Boot se encargue de eso por nosotros.*/
    @Autowired
    private UserService userService;

    //Obtener todos los usuarios
    @GetMapping
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    //Crear un nuevo usuario
    //1. Spring boot recibe el JSON
    //2. Ve que el método tiene @RequestBody UserModel request
    //3. Convierte automáticamente ese JSON a un objeto Java de tipo UserModal
    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    //Obtener los datos de un usuario por su id
    //1. Spring ve que en la URL pusiste /user/2
    //2. Busca el valor después de /user/ que coincide con {id}
    //3. Toma ese 2 y lo mete dentro de la variable Long id
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return  this.userService.getById(id);
    }

    //Actualizar un usuario por su id
    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
        return this.userService.updateById(request, id);
    }


    //Eliminar un usuario por su id
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);

        if (ok){
            return  "User with id " +id+" deleted";
        } else{
           return "Error, we have a problem and can´t delete user with id "+id;
        }
    }
}
