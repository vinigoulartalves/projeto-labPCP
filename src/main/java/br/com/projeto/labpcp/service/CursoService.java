package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.AlterarCursoRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirCursoRequest;
import br.com.projeto.labpcp.controller.dto.response.CursoResponse;
import br.com.projeto.labpcp.datasource.entity.CursoEntity;
import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.datasource.repository.CursoRepository;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;


    public CursoResponse salvar(InserirCursoRequest inserirCursoRequest, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        CursoEntity novoCurso = new CursoEntity();
        novoCurso.setNome(inserirCursoRequest.nome());

        CursoEntity salvarCurso = cursoRepository.save(novoCurso);

        List<TurmaEntity> turmasCadastradas = new ArrayList<>();
        List<MateriaEntity> materiasCadastradas = new ArrayList<>();

        return new CursoResponse(salvarCurso.getId(), salvarCurso.getNome(), turmasCadastradas, materiasCadastradas);

    }

    public CursoEntity buscarPorId(Long id, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

    public List<CursoEntity> buscarTodos(String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("ID não encontrado");
        }

        return cursoRepository.findAll();
    }

    public void excluirPorId(Long id, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN")) {
            throw new RuntimeException("ID não encontrado");
        }

        cursoRepository.deleteById(id);
    }

    public CursoEntity alterar(Long id, AlterarCursoRequest alterarCursoRequest, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        cursoRepository.updateCursoParcial(id, alterarCursoRequest.nome());
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

}