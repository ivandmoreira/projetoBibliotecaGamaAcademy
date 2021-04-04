package br.com.projetofinal.bibliotecaGama.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Locacao;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {


	Optional<Locacao> findByDataRetiradaAndDataAgendamentoAndCadastroId(LocalDate dataFinalizacao,
			LocalDate dataAgendamento, int usuario_id);



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

	


}
