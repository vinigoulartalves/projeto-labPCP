package br.com.projeto.labpcp.service;


import br.com.projeto.labpcp.controller.dto.request.AlterarDocenteRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirDocenteRequest;
import br.com.projeto.labpcp.controller.dto.response.DocenteResponse;
import br.com.projeto.labpcp.datasource.entity.DocenteEntity;
import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import br.com.projeto.labpcp.datasource.repository.DocenteRepository;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;


    //CREATE
    public DocenteResponse salvar(InserirDocenteRequest inserirDocenteRequest, String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");

        if (!Objects.equals(nomePapel, "PROFESSOR")
                && !Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "RECRUTADOR")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        UsuarioEntity usuarioDocente = usuarioRepository.findById(inserirDocenteRequest.id())
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        DocenteEntity docenteEntity = new DocenteEntity();
        docenteEntity.setUsuario(usuarioDocente);
        docenteEntity.setNome(inserirDocenteRequest.nome());
        docenteEntity.setDataEntrada(inserirDocenteRequest.dataEntrada());

        DocenteEntity docenteSalva = docenteRepository.save(docenteEntity);

        return new DocenteResponse(docenteSalva.getId(),
                docenteSalva.getNome(),
                docenteSalva.getDataEntrada(),
                docenteSalva.getUsuario().getId()
        );
    }

    //GET ALL
    public List<DocenteEntity> buscarTodos(String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");

        if (!Objects.equals(nomePapel, "PROFESSOR")
                && !Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "RECRUTADOR")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        return docenteRepository.findAll();
    }

    //GET BY ID
    public DocenteEntity buscarPorId(Long id, String token) {

        String nomePapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(nomePapel, "PROFESSOR")
                && !Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "RECRUTADOR")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }


        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    public DocenteEntity buscarPorId(Long id) {


        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    //PUT
    public DocenteEntity alterar(Long id, AlterarDocenteRequest alterarDocenteRequest, String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(nomePapel, "PROFESSOR")
                && !Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "RECRUTADOR")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }
        docenteRepository.updateDocenteParcial(id, alterarDocenteRequest.nome());
        return docenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível recuperar o usuário"));
    }

    //DELETE
    public void excluirPorId(Long id, String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(nomePapel, "PROFESSOR")
                && !Objects.equals(nomePapel, "PEDAGAGICO")
                && !Objects.equals(nomePapel, "RECRUTADOR")
                && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        DocenteEntity docenteEntity = buscarPorId(id);
        docenteRepository.delete(docenteEntity);
    }

}


