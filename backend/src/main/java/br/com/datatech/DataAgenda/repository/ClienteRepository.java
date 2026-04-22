package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Cliente;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("select count(c) from Cliente c")
    public Long contarClientes();

    @Modifying
    @Transactional
    @Query("update Cliente c set c.isAtivo = false where c.id = :id")
    public void deletarCliente(Long id);

    


}
