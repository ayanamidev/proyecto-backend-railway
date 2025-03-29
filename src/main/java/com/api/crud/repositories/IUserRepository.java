package com.api.crud.repositories;

import com.api.crud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*Es una interfaz, no una clase.
  Tú defines qué tipo de entidad va a manejar (UserModel) y qué tipo de ID usa (Long).*/
//Quiero que esta interfaz herede todos los métodos de JpaRepository para trabajar con la tabla user
@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
//Es un repositorio, o sea, la pieza que conecta con la base de datos.
//Pero lo mejor es que no necesitas programar nada ahí porque Spring Boot lo hace todo por ti.

    /*@Repository
    Le dice a Spring:
        “Esta interfaz es un componente de acceso a datos.”
         Spring la detecta, la implementa automáticamente y la puede inyectar con @Autowired.*/
}
