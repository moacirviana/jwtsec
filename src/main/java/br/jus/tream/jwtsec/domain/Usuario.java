package br.jus.tream.jwtsec.domain;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.tream.jwtsec.domain.enums.Perfil;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "VW_SRH_LOTACAO")
public class Usuario implements Serializable {

	@Id
	@Column(name = "num_tit_ele")
	private String id;

	@Column(name = "nom")
	private String nome;

	@Column(name = "mat_servidor")
	private String matricula;
	
	@Column(name = "cd_unidade")
	private Integer idUnidade;
	
	@Column(name = "sigla_unid_tse")
	private String siglaUnd;

	@JsonIgnore
	@Transient
	private String senha;
	
	@Transient
	private boolean isProducao;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Usuario() {
		addPerfil(Perfil.USER);
	}

	public Usuario(String id, String nome, String matricula, Integer idUnidade, String siglaUnd, String senha,
			Integer ativo, boolean isProducao) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.idUnidade = idUnidade;
		this.siglaUnd = siglaUnd;
		this.senha = senha;
		this.isProducao = isProducao;
		addPerfil(Perfil.USER);
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public void removeAllPerfil() {
		perfis.clear();
	}
	
	public void removePerfil(Perfil perfil) {
		this.perfis.remove(perfil.getCod());
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public String getFirstName() {
		String[] nameparts = nome.split(" ");
		return nameparts[0];
	}

	public String getId() {
		return id;
	}

	public boolean isProducao() {
		return isProducao;
	}

	public void setProducao(boolean isProducao) {
		this.isProducao = isProducao;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getSiglaUnd() {
		return siglaUnd;
	}

	public void setSiglaUnd(String siglaUnd) {
		this.siglaUnd = siglaUnd;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula +
				", perfis=" + perfis.toString() + "]";
	}

}