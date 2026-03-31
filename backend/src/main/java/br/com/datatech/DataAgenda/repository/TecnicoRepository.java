package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
