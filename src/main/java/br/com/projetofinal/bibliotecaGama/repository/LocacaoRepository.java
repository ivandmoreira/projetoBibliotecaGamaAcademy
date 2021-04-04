package br.com.projetofinal.bibliotecaGama.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Locacao;
import br.com.projetofinal.bibliotecaGama.model.enums.LocacaoStatus;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {

//	List<Locacao> findByDataAgendamentoAndDataRetiradaAndCadastroLoginUsuarioIgnoreCaseAndStatus(
//			LocalDate dataAgendamento, LocalDate dataFinalizacao, String usuario_name, LocacaoStatus num);


	@Query(value = "SELECT l FROM Locacao l" 
			+ " JOIN l.cadastro c "
			+ " JOIN c.login lg "
			+ " WHERE "
			+ " (:dataAgendamento IS NULL OR l.dataAgendamento = :dataAgendamento)"
			+ " AND "
			+ " (:dataRetirada IS NULL OR l.dataRetirada = :dataRetirada)"
			+ " AND"
			+ " (:usuario IS NULL OR lg.usuario =:usuario)"
			+ " AND "
			+ "(:status IS NULL OR l.status =:status)"
			)
	List<Locacao> buscarEspecifica(
			@Param("dataAgendamento") LocalDate dataAgendamento
			,@Param("dataRetirada") LocalDate dataRetirada
			,@Param("usuario") String usuario
			,@Param("status") LocacaoStatus status
			);

	


}
