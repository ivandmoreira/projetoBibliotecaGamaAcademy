package br.com.projetofinal.bibliotecaGama.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Locacao;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {


	List<Locacao> findByDataRetiradaAndDataAgendamentoAndCadastroLoginUsuarioIgnoreCase(LocalDate dataFinalizacao,
			LocalDate dataAgendamento, String usuario_id);



//	@Query(value = "SELECT l FROM Locacao l" 
//			+ " JOIN l.cadastro c " 
//			+ " WHERE (:dataAgendamento is not null or l.dataAgendamento = :dataAgendamento)"
//			+ " :dataRetirada IS NOT NULL(AND l.dataRetirada = : dataRetirada)"
//			)
//	List<Locacao> buscarEspecifica(
//			@Param("dataAgendamento") LocalDate dataAgendamento
//			,@Param("dataRetirada") LocalDate dataRetirada
//			);

	


}
