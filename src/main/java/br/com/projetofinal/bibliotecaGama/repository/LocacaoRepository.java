package br.com.projetofinal.bibliotecaGama.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Locacao;
import br.com.projetofinal.bibliotecaGama.model.enums.LocacaoStatus;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {

//	Locacao findByDataRetiradaAndDataAgendamento(LocalDate dataFinalizacao, LocalDate dataAgendamento);

//	Locacao findByDataRetiradaAndDataAgendamentoAndCadastroId(LocalDate dataFinalizacao, LocalDate dataAgendamento,
//			int usuario_id);

	Optional<Locacao> findByDataRetiradaAndDataAgendamentoAndCadastroId(LocalDate dataFinalizacao,
			LocalDate dataAgendamento, int usuario_id);

//	List<Locacao> findByLocacaoStatus(String string);

//	@Query(value = "SELECT l FROM Locacao l" 
//			+ " JOIN Cadastro c " 
//			+ " WHERE l.dataAgendamento = :dataAgendamento "
//			+ " OR l.dataRetirada = :dataFinalizacao " 
//			+ " OR c.id = : idCadastro " 
//			+ " OR l.status = :status")
//	List<Locacao> buscarEspecifica(
//			@Param("dataAgendamento") LocalDate dataAgendamento,
//			@Param("dataFinalizacao") LocalDate dataFinalizacao, 
//			@Param("usuario") int usuario_id,
//			@Param("status") String status);

	
//	Locacao findByDataAgendamento(LocalDate dataAgendamento);
//
//	Locacao findByDataAgendamentoAndCadastro(LocalDate dataAgendamento, int usuario_id);
//
//	Locacao findByDataRetiradaAndDataAgendamentoAndCadastroIdAndLocacaoStatus(LocalDate dataAgendamento,LocalDate dataRetirada, int usuario_id, LocacaoStatus status);

//	List<Locacao> findByStatus(String string);
//
//	List<Locacao> findByLocacaoStatus(String string);

}
