package br.com.projeto.labpcp.service;


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

        if (!Objects.equals(nomePapel, "PROFESSOR") && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DocenteEntity docenteEntity = new DocenteEntity();
        docenteEntity.setUsuarioEntity(usuario);
        docenteEntity.setNome(inserirDocenteRequest.nome());
        docenteEntity.setDataEntrada(inserirDocenteRequest.dataEntrada());
        docenteEntity.setUsuarioEntity(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou inválido"))
        );

        DocenteEntity docenteSalva = docenteRepository.save(docenteEntity);

        return new DocenteResponse(docenteSalva.getId(),
                docenteSalva.getNome(),
                docenteSalva.getDataEntrada(),
                docenteSalva.getUsuarioEntity().getId()
        );
    }

    //GET ALL
    public List<DocenteEntity> buscarTodos(String token) {
        String nomePapel = tokenService.buscaCampo(token, "scope");

        if (!Objects.equals(nomePapel, "PROFESSOR") && !Objects.equals(nomePapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        Long idUsuario = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );

        return docenteRepository.findAll();
    }

    //GET BY ID
    public DocenteEntity buscarPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    //PUT
    public DocenteEntity alterar(Long id, DocenteEntity docenteEntity) {
        buscarPorId(id);
        docenteEntity.setId(id);
        return docenteRepository.save(docenteEntity);
    }

    //DELETE
    public void excluirPorId(Long id) {
        DocenteEntity docenteEntity = buscarPorId(id);
        docenteRepository.delete(docenteEntity);
    }

}


