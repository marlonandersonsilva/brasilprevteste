package br.com.edsonajeje.cadatroAPI.model.repositories;

import br.com.edsonajeje.cadatroAPI.model.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query("select c from Client c where c.cpf like ?1")
    Optional<Client> findClientByCpf(String cpf);

    @Query("select c from Client c where c.email like ?1")
    Optional<Client> findClientByEmail(String email);
}