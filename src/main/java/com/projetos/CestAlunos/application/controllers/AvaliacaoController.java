package com.projetos.CestAlunos.application.controllers;

import com.projetos.CestAlunos.domain.entity.Avaliacao;
import com.projetos.CestAlunos.domain.service.AvaliacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> salvar(@RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoSalva = avaliacaoService.salvar(avaliacao);
        return new ResponseEntity<>(avaliacaoSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizar(avaliacao);
        return new ResponseEntity<>(avaliacaoAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        avaliacaoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> buscarTodas() {
        List<Avaliacao> avaliacoes = avaliacaoService.buscarAvaliacoesPorAlunoId(null);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @PostMapping("/lancar")
    public ResponseEntity<?> lancarNotaEFrequencia(@RequestBody Avaliacao avaliacao) {
        try {
            Avaliacao avaliacaoSalva = avaliacaoService.registrarAvaliacao(avaliacao);
            return new ResponseEntity<>(avaliacaoSalva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Método adicional para alunos consultarem suas notas
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Avaliacao>> consultarNotasDoAluno(@PathVariable Long alunoId) {
        List<Avaliacao> avaliacoesDoAluno = avaliacaoService.buscarAvaliacoesPorAlunoId(alunoId);
        if (!avaliacoesDoAluno.isEmpty()) {
            return new ResponseEntity<>(avaliacoesDoAluno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Adicione outros métodos conforme necessário
}
