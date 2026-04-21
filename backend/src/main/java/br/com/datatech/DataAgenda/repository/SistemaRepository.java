package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.dto.response.SistemaDTOResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema,Long> {

    @Query("select count(s) from Sistema s")
    Long contarSistemas();


}
