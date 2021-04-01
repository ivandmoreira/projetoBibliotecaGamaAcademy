package br.com.projetofinal.bibliotecaGama.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Login;
import br.com.projetofinal.bibliotecaGama.model.Sessao;
import br.com.projetofinal.bibliotecaGama.repository.CadastroRepository;
import br.com.projetofinal.bibliotecaGama.security.JWTConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	@Autowired
	private CadastroRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Sessao logar (Login login) {
		if (login == null || login.getUsuario()==null || login.getSenha()==null) {
			throw new RuntimeException("Login e senha são requeridos");
		}

		Cadastro cad =  repository.findByLoginUsuario(login.getUsuario());
			
		boolean senhaOk = encoder.matches(login.getSenha(),cad.getLogin().getSenha());

		if (!senhaOk) {
			throw new RuntimeException("Senha inválida para o login: " + login.getUsuario());
		}
		
		Sessao sessao = new Sessao();
		sessao.setDataInicio(new Date(System.currentTimeMillis()));
		sessao.setDataFim(new Date(System.currentTimeMillis() + JWTConstants.TOKEN_EXPIRATION));
		
		sessao.setLogin(cad.getLogin().getUsuario());

		sessao.setToken(JWTConstants.PREFIX + getJWTToken(sessao));

		return sessao;
	}
	//como vc gerenciaria a nivel de banco o role de um usuario ???
	private String getJWTToken(Sessao sessao) {
		String role = "ROLE_USER";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getDataInicio()).setExpiration(sessao.getDataFim())
				.signWith(SignatureAlgorithm.HS512, JWTConstants.KEY.getBytes()).compact();

		return token;
	}
}
