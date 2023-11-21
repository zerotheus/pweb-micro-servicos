package com.micro.consultas.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.micro.consultas.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

        @Query(nativeQuery = true, value = "Select * from consultas where horario >= ?2 and fk_paciente_id = ?1 and estado != 'Cancelada'"
                        + " and estado != 'Analise' and estado != 'ValidacaoDeDadosDoPacientePendente' and estado != 'ExcedeQuantidadeMaximaDeConsultas'"
                        + " and estado != 'AnaliseDeConfirmacaoMedicaPendente' and estado != 'Remarcar'")
        public List<Consulta> jaPossuiConsultaAgendada(@Param(value = "id") Long id, LocalDateTime diaMarcado);

        @Query(nativeQuery = true, value = "SELECT DISTINCT fk_medico_id FROM consultas WHERE horario > :horaMarcada AND horario < :termino"
                        + " and estado = 'Agendada'")
        public List<Long> medicosIndisponiveis(@Param("horaMarcada") LocalDateTime horaMarcada,
                        @Param("termino") LocalDateTime termino);

        default HashMap<Long, Long> medicosIndisponiveisAsMap(LocalDateTime horaMarcada, LocalDateTime termino) {
                List<Long> lista = medicosIndisponiveis(horaMarcada, termino);
                HashMap<Long, Long> hashmap = new HashMap<Long, Long>();
                for (int i = 0; i < lista.size(); i++) {
                        hashmap.put(lista.get(i), lista.get(i));
                }
                return hashmap;
        }

        @Query(nativeQuery = true, value = "SELECT * FROM consultas WHERE fk_medico_id = :medico and horario >= :horaMarcada AND horario <= :termino and estado != 'Cancelada'")
        public List<Consulta> medicoSelecionadoEstaDisponivel(@Param("medico") Long id,
                        @Param("horaMarcada") LocalDateTime horaMarcada,
                        @Param("termino") LocalDateTime termino);

}
