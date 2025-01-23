package org.treinamento.service.impl;

import org.springframework.stereotype.Service;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.model.Aluno;
import org.treinamento.repository.AlunoRepository;
import org.treinamento.service.CadastroService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CadastroServiceImpl implements CadastroService {

    private AlunoRepository alunoRepository;


    public CadastroServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public ResponseDTO buscarAluno(String matricula) throws Exception {
        try{
            List alunos = new ArrayList<Aluno>();
            Aluno aluno = alunoRepository.findByMatricula(matricula);

            if(aluno == null){
                throw new Exception("Não foi possível localizar um aluno com esta matrícula");
            }
            alunos.add(aluno);

            return new ResponseDTO("1","Retorno sucesso.", alunos);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseDTO buscarAlunos(int page, int pageSize) {
        List<Aluno> alunos = alunoRepository.findAll();

        int start = Math.min(page * pageSize, alunos.size());
        int end = Math.min((page + 1) * pageSize, alunos.size());

        List<Aluno> alunosPage = alunos.stream()
                .collect(Collectors.toList()).subList(start, end);
        return new ResponseDTO("1","Retorno sucesso.", alunosPage);
    }

    @Override
    public ResponseDTO salvarAluno(Aluno aluno) throws Exception {
        try{
            Aluno novoAluno = alunoRepository.save(aluno);

            if(novoAluno == null){
                throw new Exception();
            }

            List alunos = new ArrayList<Aluno>();

            alunos.add(novoAluno);

            return new ResponseDTO("1","Aluno salvo com sucesso.", alunos);
        }catch (Exception e){
            throw new Exception("Não foi possível salvar o aluno, verifique os dados e tente novamente.");
        }
    }
}