package br.jus.tream.jwtsec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.tream.jwtsec.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	List<Usuario> findBySiglaUnd(String siglaUnd);
	
	Usuario findByMatricula(String matricula);
	
	@Query(value="SELECT * from VW_SRH_LOTACAO WHERE num_tit_ele IN (SELECT usuario_num_tit_ele FROM tpl_perfis) ORDER BY NOM", nativeQuery = true)
	List<Usuario> findAllByPerfil();
	
	@Query(value="SELECT * from VW_SRH_LOTACAO WHERE num_tit_ele NOT IN (SELECT usuario_num_tit_ele FROM tpl_perfis) ORDER BY NOM", nativeQuery = true)
    List<Usuario> findAllParaDistribuir();
	
	// DELETA SOMENTE O PERFIL
	@Query(value="DELETE FROM perfis WHERE usuario_num_tit_ele=:numTitulo", nativeQuery = true)
	void removePerfil(@Param("numTitulo") String numTitulo);
}
