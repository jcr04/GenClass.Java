package com.projetos.CestAlunos.application.controllers;

import com.projetos.CestAlunos.domain.entity.Professor;
import com.projetos.CestAlunos.domain.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor, @RequestParam Set<Long> cursoIds) {
        Professor professorSalvo = professorService.salvar(professor, cursoIds);
        return ResponseEntity.ok(professorSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professor, @RequestParam Set<Long> cursoIds) {
        professor.setId(id);
        Professor professorAtualizado = professorService.atualizar(professor, cursoIds);
        return ResponseEntity.ok(professorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        professorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Professor>> buscarTodos() {
        List<Professor> professores = professorService.buscarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Professor>> buscarPorNome(@RequestParam String nome) {
        List<Professor> professores = professorService.buscarProfessoresPorNome(nome);
        return professores.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(professores);
    }

    // Outros métodos conforme necessário
}
