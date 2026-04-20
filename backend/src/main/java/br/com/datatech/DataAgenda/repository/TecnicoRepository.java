package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

   @Query("select count(t) from Tecnico t where t.isAtivo = true")
   public Long contarTecnicosAtivos();  


}