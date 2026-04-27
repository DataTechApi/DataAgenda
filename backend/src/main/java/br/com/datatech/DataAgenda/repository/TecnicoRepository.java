package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.response.LoginDTOResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

   @Query("select count(t) from Tecnico t where t.isAtivo = true")
   public Long contarTecnicosAtivos();

  Tecnico findByEmail(String email);





}