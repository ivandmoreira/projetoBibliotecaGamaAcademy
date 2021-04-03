package br.com.projetofinal.bibliotecaGama.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public Locacao salvar(LocacaoDto locDto) {
		
		Locacao locacao = new Locacao();
				locacao.setStatus(LocacaoStatus.RESERVADA);
		
		Optional <Cadastro> cadastro = cadastroService.buscarPorId(locDto.getUsuario_id());
		
		List<LocacaoItem> locacaoItens = new ArrayList<LocacaoItem>();
		
		locacao.setCadastro(cadastro.isPresent()?cadastro.get():null);
		locacao.setLocacaoItem(locacaoItens);
		
		locacaoRepository.save(locacao);
		for (LocacaoItemDto locacaoItemDto : locDto.getLocacaoItem()) {
			
			LocacaoItem objetoLocacaoItem = new LocacaoItem();
			objetoLocacaoItem.setLocacao(locacao);
			objetoLocacaoItem.setDataPrevisaoEntrega(locacaoItemDto.getDataPrevisaoEntrega());
			Optional<Livro> livro = livrosService.buscarPorId(locacaoItemDto.getLivro_id());
			objetoLocacaoItem.setLivro(livro.isPresent()?livro.get():livro.get());
			locacaoItemRepository.save(objetoLocacaoItem);
			locacaoItens.add(objetoLocacaoItem);
		}


		
		return locacao;
	}

	
	
	

	

	

	



}
