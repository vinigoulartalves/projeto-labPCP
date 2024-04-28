package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.InserirTurmaRequest;
import br.com.projeto.labpcp.controller.dto.response.DocenteResponse;
import br.com.projeto.labpcp.controller.dto.response.TurmaResponse;
import br.com.projeto.labpcp.datasource.entity.*;
import br.com.projeto.labpcp.datasource.repository.CursoRepository;
import br.com.projeto.labpcp.datasource.repository.DocenteRepository;
import br.com.projeto.labpcp.datasource.repository.TurmaRepository;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final DocenteRepository docenteRepository;
    private final CursoRepository cursoRepository;
    private final TurmaRepository turmaRepository;

    public TurmaResponse salvar(InserirTurmaRequest inserirTurmaRequest, String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");

        if (!Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        DocenteEntity professorDocente = docenteRepository.findById(inserirTurmaRequest.idDocente())
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        CursoEntity nomeCurso = cursoRepository.findById(inserirTurmaRequest.idCurso())
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        TurmaEntity turmaEntity = new TurmaEntity();
        turmaEntity.setIdDocente(professorDocente);
        turmaEntity.setIdCurso(nomeCurso);
        turmaEntity.setNome(inserirTurmaRequest.nome());

        TurmaEntity turmaSalva = turmaRepository.save(turmaEntity);


        List<AlunoEntity> alunoEntities = new ArrayList<>();


        return new TurmaResponse(turmaSalva.getId(),
                turmaSalva.getNome(),
                turmaSalva.getIdDocente().getId(),
                alunoEntities,
                turmaSalva.getIdCurso().getId()
        );

    }

    public List<TurmaEntity> buscarTodos() {
        return turmaRepository.findAll();
    }

    public TurmaEntity buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    public void ExcluirPorId(Long id) {
        turmaRepository.deleteById(id);
    }

    public TurmaEntity alterar(TurmaEntity turma) {
        return turmaRepository.save(turma);
    }

}
