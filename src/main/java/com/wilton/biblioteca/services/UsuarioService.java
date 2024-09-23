package com.wilton.biblioteca.services;

import com.wilton.biblioteca.dtos.*;
import com.wilton.biblioteca.exception.ExceptionPersonalizada;
import com.wilton.biblioteca.mappers.LivroMapper;
import com.wilton.biblioteca.mappers.UsuarioMapper;
import com.wilton.biblioteca.model.Emprestimo;
import com.wilton.biblioteca.model.Livro;
import com.wilton.biblioteca.model.Usuario;
import com.wilton.biblioteca.repositorys.EmprestimoRepository;
import com.wilton.biblioteca.repositorys.LivroRepository;
import com.wilton.biblioteca.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private LivroMapper livroMapper;

    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto requestDto) {
        verificarEmailExistente(requestDto.getEmail());
        return mapper.toUsuarioResponseDto(repository.save(mapper.toUsuario(requestDto)));
    }

/*
    public List<Object> listarUsuarios() {
        return mapper.toListUsuarioResponseDto(verificarListaSeEstaVazia());
    }

    public UsuarioResponseDto obterUsuario(long id) {
        return mapper.toUsuarioResponseDto(verificarUsuarioExiste(id));
    }
*/

    public EmprestimoResponseDto pegarLivroEmprestado(EmprestimoRequestDto requestDto) {
        EmprestimoListRequestDto emprestimoListRequestDto = new EmprestimoListRequestDto();
        emprestimoListRequestDto.setIdUsuario(requestDto.getIdUsuario());
        verificarSeUsuarioPodePegarMaisLivros(emprestimoListRequestDto, requestDto.getIsbn());
        Emprestimo emprestimo = new Emprestimo(verificarDisponibilidadeLivroNaBilbioteca(requestDto.getIsbn(), 1),
                verificarUsuarioExiste(requestDto.getIdUsuario()), LocalDate.now());
        return mapper.toEmprestimoResponseDto(emprestimoRepository.save(emprestimo));

    }

    public DevolucaoResponseDto devolverLivro(DevolucaoRequestDto requestDto) {
        DevolucaoResponseDto responseDto = new DevolucaoResponseDto();
        Usuario usuario = verificarUsuarioExiste(requestDto.getIdUsuario());
        Emprestimo emprestimo = verificarExistenciaEmprestimo(usuario.getEmprestimos(), requestDto.getIdEmprestimo());
        Livro livro = verificarDisponibilidadeLivroNaBilbioteca(emprestimo.getLivro().getIsbn(), 2);
        responseDto.setIsbn(livro.getIsbn());
        responseDto.setIdUsuario(usuario.getId());
        responseDto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        responseDto.setDataDevolucao(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimoRepository.save(emprestimo);
        return responseDto;

    }


    public Object listDeEmprestimosDoUsuario(EmprestimoListRequestDto requestDto) {
        Usuario usuario = verificarUsuarioExiste(requestDto.getIdUsuario());
        List<EmprestimoResponseDto> list = mapper.toListEmprestimoResponseDto(criarLIstaEmprestimo(usuario.getEmprestimos()));
        if (list.isEmpty()) {
            throw new ExceptionPersonalizada("esse usuario não tem emprestimos ativos", 404);
        }
        return new EmprestimoListResponseDto(list, usuario.getId());

    }

    private void verificarSeUsuarioPodePegarMaisLivros(EmprestimoListRequestDto requestDto, long isbn) {
        Usuario usuario = verificarUsuarioExiste(requestDto.getIdUsuario());
        List<Emprestimo> emprestimosAtivos = criarLIstaEmprestimo(usuario.getEmprestimos());
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getLivro().getIsbn() == isbn) {
                throw new ExceptionPersonalizada("não permitido usuario fazer emprestimo repetido de uma mesma copia", 409);
            }
        }

        if (emprestimosAtivos.size() == 3) {
            throw new ExceptionPersonalizada("O usuario não pode obter mais de 3 emprestimos simutaneos", 409);
        }
    }


    private List<Emprestimo> criarLIstaEmprestimo(List<Emprestimo> list) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo e : list) {
            if (e.getDataDevolucao() == null) {
                emprestimosAtivos.add(e);
            }
        }
        return emprestimosAtivos;
    }


    private Livro verificarDisponibilidadeLivroNaBilbioteca(long isbn, int acao) {
        //ação 1 é pegar emprestado e ação 2 é devolver
        Livro livro = livroRepository.findById(isbn).orElseThrow(() ->
                new ExceptionPersonalizada("isbn não existente", 404));

        if (acao == 1) {
            if (livro.getQuantidade() < 1) {
                throw new ExceptionPersonalizada("livro em falta", 404);
            }
            livro.setQuantidade(livro.getQuantidade() - 1);
        } else {
            livro.setQuantidade(livro.getQuantidade() + 1);
        }
        return livroRepository.save(livro);

    }

    private Usuario verificarUsuarioExiste(long id) {
        return repository.findById(id).orElseThrow(() -> new ExceptionPersonalizada("usuario não existente", 404));
    }

    private void verificarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ExceptionPersonalizada("Email já cadastrado", 409);
        }
    }

/*
    private List<Usuario> verificarListaSeEstaVazia() {
        List<Usuario> list = repository.findAll();
        if (list.isEmpty()) {
            throw new ExceptionPersonalizada("Lista de Usuario vazia", 404);
        }
        return list;
    }
*/

    private Emprestimo verificarExistenciaEmprestimo(List<Emprestimo> list, long id) {
        for (Emprestimo e : list) {
            if (e.getIdEmprestimo() == id) {
                return e;
            }
        }
        throw new ExceptionPersonalizada("Emprestimo inexistente", 404);
    }

}
