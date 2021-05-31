package br.com.serratec.cafeteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.cafeteria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findById(Long idCliente);


}
