package br.jus.tream.jwtsec.domain.DTO;

public class JwtDTO {
	private String id;
	private String nome;
	private String jwtToken;
	
	public JwtDTO(String id, String nome, String jwtToken) {
		this.id = id;
		this.nome = nome;
		this.jwtToken = jwtToken;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
