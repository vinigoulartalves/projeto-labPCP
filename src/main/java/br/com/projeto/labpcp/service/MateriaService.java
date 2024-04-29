package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.AlterarMateriaRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirMateriaRequest;
import br.com.projeto.labpcp.controller.dto.response.MateriaResponse;
import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import br.com.projeto.labpcp.datasource.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final TokenService tokenService;


    public MateriaResponse salvar(InserirMateriaRequest inserirMateriaRequest, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        MateriaEntity materiaEntity = new MateriaEntity();
        materiaEntity.setNome(materiaEntity.getNome());
        materiaEntity.setIdCurso(materiaEntity.getIdCurso());

        MateriaEntity salvarMateria = materiaRepository.save(materiaEntity);

        return new MateriaResponse(salvarMateria.getId(), materiaEntity.getNome(), materiaEntity.getIdCurso().getId());
    }

    public List<MateriaEntity> buscarTodos(String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        return materiaRepository.findAll();
    }

    public MateriaEntity buscarPorId(Long id, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        return materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    public void excluirPorId(Long id, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        materiaRepository.deleteById(id);
    }

    public MateriaEntity alterar(Long id, AlterarMateriaRequest alterarMateriaRequest, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        materiaRepository.updateMateriaParcial(id, alterarMateriaRequest.nome(), alterarMateriaRequest.cursoId());
        return materiaRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }
}
