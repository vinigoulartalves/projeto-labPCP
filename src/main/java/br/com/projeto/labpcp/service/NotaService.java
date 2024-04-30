package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.AlterarNotaRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirNotaRequest;
import br.com.projeto.labpcp.controller.dto.response.NotaResponse;
import br.com.projeto.labpcp.datasource.entity.NotaEntity;
import br.com.projeto.labpcp.datasource.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final TokenService tokenService;


    public NotaResponse salvar(InserirNotaRequest inserirNotaRequest, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        NotaEntity novaNota = new NotaEntity();
        novaNota.setNota(novaNota.getNota());
        novaNota.setIdAluno(novaNota.getIdAluno());
        novaNota.setIdMateria(novaNota.getIdMateria());
        novaNota.setIdDocente(novaNota.getIdDocente());
        novaNota.setData(novaNota.getData());

        NotaEntity salvarNota = notaRepository.save(novaNota);

        return new NotaResponse(salvarNota.getId(), salvarNota.getIdAluno().getId(),
                salvarNota.getIdDocente().getId(), novaNota.getIdMateria().getId(),
                salvarNota.getNota(), salvarNota.getData());
    }

    public List<NotaEntity> buscarTodos(String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }
        return notaRepository.findAll();
    }


    public NotaEntity buscarPorId(Long id, String token) {
        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }


    public NotaEntity alterar(Long id, AlterarNotaRequest alterarNotaRequest, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN") && !Objects.equals(tokenPapel, "PEDAGAGICO")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }

        notaRepository.updateParcialNota(id, alterarNotaRequest.nota(), alterarNotaRequest.dataNota());
        return notaRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

    public void excluir(Long id, String token) {

        String tokenPapel = tokenService.buscaCampo(token, "scope");
        if (!Objects.equals(tokenPapel, "ADMIN")) {
            throw new RuntimeException("Usuário não tem acesso a essa funcionalidade");
        }
        notaRepository.deleteById(id);
    }

    public BigDecimal buscarMediaFinal(List<NotaEntity> notas) {

        if (notas == null || notas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal somaTotal = notas.stream().map(NotaEntity::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal mediaFinal = somaTotal.divide(BigDecimal.valueOf(notas.size()),
                2, RoundingMode.HALF_UP);
        return mediaFinal.multiply(BigDecimal.TEN);
    }


}
