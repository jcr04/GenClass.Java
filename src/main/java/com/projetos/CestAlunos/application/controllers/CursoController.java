package com.projetos.CestAlunos.application.controllers;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.domain.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.salvar(curso);
        return new ResponseEntity<>(cursoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.atualizar(curso);
        return new ResponseEntity<>(cursoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        if (curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> buscarTodos() {
        List<Curso> cursos = cursoService.buscarTodos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

}
