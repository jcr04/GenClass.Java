package com.projetos.CestAlunos.application.controllers;

import com.projetos.CestAlunos.domain.entity.Aluno;
import com.projetos.CestAlunos.domain.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.salvar(aluno);
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.atualizar(aluno);
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alunoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        if (aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> buscarTodos() {
        List<Aluno> alunos = alunoService.buscarTodos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

}
