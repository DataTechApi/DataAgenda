package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query(value="select count(*) from cliente",nativeQuery=true)
    public Long contarClientes();

    


}
