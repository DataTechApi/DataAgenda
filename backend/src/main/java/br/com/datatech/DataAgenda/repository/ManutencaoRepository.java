package br.com.datatech.DataAgenda.repository;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.StatusManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    Long countByStatusManutencao(StatusManutencao status);

    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.dataAgendada = CURRENT_DATE")
    Long countScheduledToday();

    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.dataAgendada < CURRENT_DATE AND m.statusManutencao = 'PENDENTE'")
    Long countOverdue();

    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.statusManutencao = 'EXECUTADA' AND m.dataRealizada >= :dataLimite")
    Long countExecutedSince(@org.springframework.data.repository.query.Param("dataLimite") java.time.LocalDate dataLimite);

    @Query("SELECT m FROM Manutencao m WHERE m.statusManutencao = 'PENDENTE' ORDER BY m.dataAgendada ASC LIMIT 5")
    List<Manutencao> findTop5PendingOrderByDataAgendadaAsc();
}
