package br.com.projeto.labpcp.service;


import br.com.projeto.labpcp.controller.dto.request.InserirAlunoRequest;
import br.com.projeto.labpcp.controller.dto.response.AlunoResponse;
import br.com.projeto.labpcp.datasource.entity.AlunoEntity;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import br.com.projeto.labpcp.datasource.repository.AlunoRepository;
import br.com.projeto.labpcp.datasource.repository.TurmaRepository;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final TurmaRepository turmaRepository;


    public AlunoResponse salvar(InserirAlunoRequest inserirAlunoRequest, String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");

        if (!Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setUsuario(usuario);
        alunoEntity.setNome(inserirAlunoRequest.nome());
        alunoEntity.setDataNascimento(inserirAlunoRequest.dataNascimento());

        AlunoEntity alunoSalva = alunoRepository.save(alunoEntity);
        List<TurmaEntity> turmaEntities = new ArrayList<>();

        return new AlunoResponse(alunoSalva.getId(),
                alunoSalva.getNome(),
                alunoEntity.getDataNascimento(),
                alunoSalva.getUsuario().getId(),
                turmaEntities);

    }

    public AlunoEntity buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

    public List<AlunoEntity> buscarTodos() {
        return alunoRepository.findAll();
    }

    public void excluirPorId(Long id) {
        alunoRepository.deleteById(id);
    }
}