package br.com.projetofinal.bibliotecaGama.services;

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

@Service
public class LocacaoService {

	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private LivroService livrosService;
	
	Locacao loc = new Locacao();
	
	List<LocacaoItem> locIt = new ArrayList<>();
	
	public Locacao salvar(LocacaoDto locDto) {
		
		buscarCadastro(locDto.getUsuario_id());
		buscarLivro(locDto.getLocacaoItem());
		
		return null;
	}

	private void buscarLivro(List<LocacaoItemDto> locacaoItem) {
		int index = 0;
		for(LocacaoItemDto liv : locacaoItem) {
			System.out.println("id do Livro "+ liv.getLivro_id());
			
			Optional<Livro> livro = livrosService.buscarPorId(liv.getLivro_id());
			
			System.out.println(index + " dados do livro " + livro.get().getTitulo());
			
			index++;
			
			
			
//			locIt.add(livro.get());
			
		}
		
//		Iterable<Livro> livro = livrosService.buscarPorId(locacaoItem);
//		List<Livro> livros = livrosService.buscarLivros(livros_id);
//		loc.setLocacaoItem(livros);
		
	}
	
//##########################################################################################
	
//	private void buscarLivro(List<LocacaoItemDto> locacaoItem) {
//		
//		for (LocacaoItemDto liv : locacaoItem) {
//			Livro livroRetorno = livrosService.buscarPorId2(liv.getLivro_id());
//			
//			locIt.add(e)
//			locIt.add(livroRetorno.getClass());
//		}
//	}
	
//##############################################################################################	
	

	private void buscarCadastro(int usuario) {
		
		Optional<Cadastro> cad = cadastroService.buscarPorId(usuario);
		
		loc.setCadastro(cad.get());
		
		
	}
}
