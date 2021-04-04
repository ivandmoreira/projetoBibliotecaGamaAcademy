package br.com.projetofinal.bibliotecaGama.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetofinal.bibliotecaGama.dto.LocacaoDto;
import br.com.projetofinal.bibliotecaGama.dto.LocacaoItemDto;
import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Livro;
import br.com.projetofinal.bibliotecaGama.model.Locacao;
import br.com.projetofinal.bibliotecaGama.model.LocacaoItem;
import br.com.projetofinal.bibliotecaGama.model.enums.LocacaoStatus;
import br.com.projetofinal.bibliotecaGama.repository.LivroRepository;
import br.com.projetofinal.bibliotecaGama.repository.LocacaoItemRepository;
import br.com.projetofinal.bibliotecaGama.repository.LocacaoRepository;

@Service
public class LocacaoService {
	@Autowired
	private CadastroService cadastroService;
	@Autowired
	private LivroService livrosService;
	@Autowired
	private LocacaoRepository locacaoRepository;
	@Autowired
	private LocacaoItemRepository locacaoItemRepository;
	@Autowired
	private LivroRepository livroRepository;

	public Locacao salvar(LocacaoDto locDto) {

		Locacao locacao = new Locacao();
		locacao.setDataAgendamento(LocalDate.now());
		locacao.setStatus(LocacaoStatus.RESERVADA);
		locacao.setValorTotal(0.0);

		Optional<Cadastro> cadastro = cadastroService.buscarPorId(locDto.getUsuario_id());

		List<LocacaoItem> locacaoItens = new ArrayList<LocacaoItem>();

		locacao.setCadastro(cadastro.isPresent() ? cadastro.get() : null);
		locacao.setLocacaoItem(locacaoItens);

		locacaoRepository.save(locacao);
		for (LocacaoItemDto locacaoItemDto : locDto.getLocacaoItem()) {

			LocacaoItem objetoLocacaoItem = new LocacaoItem();
			objetoLocacaoItem.setLocacao(locacao);
			objetoLocacaoItem.setDataPrevisaoEntrega(locacaoItemDto.getDataPrevisaoEntrega());
			Optional<Livro> livro = livroRepository.VerificaDisponibilidade(locacaoItemDto.getLivro_id());
			if (livro.isPresent()) {
				Livro livroDisponivel = livro.get();
				livrosService.decrementarExemplares(livroDisponivel);
				livrosService.incrementarReservados(livroDisponivel);
				objetoLocacaoItem.setLivro(livroDisponivel);

			}
			locacaoItemRepository.save(objetoLocacaoItem);
			locacaoItens.add(objetoLocacaoItem);
		}

		return locacao;
	}

	public Iterable<Locacao> buscarLocacao() {
		return locacaoRepository.findAll();
	}

	public Optional<Locacao> buscarPorId(int id) {
		return locacaoRepository.findById(id);
	}

	public void retirarLivro(int id) {
		Optional<Locacao> retirando = locacaoRepository.findById(id);

		retirando.get().setDataRetirada(dataAtual());
		retirando.get().setStatus(LocacaoStatus.EFETIVADA);

		locacaoRepository.save(retirando.get());

	}

	public void entregarLivro(int id) {
		Optional<Locacao> entregarLivro = locacaoRepository.findById(id);
		entregarLivro.get().setDataFinalizacao(dataAtual());
		entregarLivro.get().setStatus(LocacaoStatus.FINALIZADA);
		List <LocacaoItem>locacaoItens = entregarLivro.get().getLocacaoItem();
		for (LocacaoItem locacaoItem : locacaoItens) {
			Optional<Livro> livro = livroRepository.findById(locacaoItem.getLivro().getId());
			if(livro.isPresent()) {
				Livro livroRetornado = livro.get();
			livrosService.incrementarExemplares(livroRetornado);
			livrosService.decrementarReservados(livroRetornado);
			livroRepository.save(livroRetornado);
			}
		}
	
		locacaoRepository.save(entregarLivro.get());
	}

	private LocalDate dataAtual() {
		LocalDate dat = LocalDate.now();
		return dat;
	}

	public List<Locacao> buscarPorLocacaoEspecifica(LocacaoDto locacao) {
		
		List<Locacao> loc1 = locacaoRepository.findByDataRetiradaOrDataAgendamentoOrCadastroId(locacao.getDataFinalizacao(), locacao.getDataAgendamento(), locacao.getUsuario_id());
		
		return loc1;
	}
	

	
}