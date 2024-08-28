package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação do serviço de usuários.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCep(userRequestDto.getCep());
        user.setCpf(userRequestDto.getCpf());
        user.setIs_active(Boolean.TRUE);


        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUser_id(savedUser.getUser_id());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setCep(savedUser.getPassword());
        userResponseDto.setCpf(savedUser.getCpf());
        userResponseDto.setIs_active(savedUser.getIs_active());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User updateUser = userRepository.findById(id).orElseThrow();

        updateUser.setName(userRequestDto.getName());
        updateUser.setEmail(userRequestDto.getEmail());
        updateUser.setPassword(userRequestDto.getPassword());
        updateUser.setCep(userRequestDto.getCep());
        updateUser.setCpf(userRequestDto.getCpf());

        userRepository.save(updateUser);

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUser_id(updateUser.getUser_id());
        userResponseDto.setName(updateUser.getName());
        userResponseDto.setEmail(updateUser.getEmail());
        userResponseDto.setPassword(updateUser.getPassword());
        userResponseDto.setCep(updateUser.getCep());
        userResponseDto.setCpf(updateUser.getCpf());
        userResponseDto.setIs_active(updateUser.getIs_active());

        return userResponseDto;
    }

    @Override
    public UserResponseDto getId(Long id) {
        User userById = userRepository.findById(id).orElseThrow();

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUser_id(userById.getUser_id());
        userResponseDto.setName(userById.getName());
        userResponseDto.setEmail(userById.getEmail());
        userResponseDto.setPassword(userById.getPassword());
        userResponseDto.setCep(userById.getCep());
        userResponseDto.setCpf(userById.getCpf());
        userResponseDto.setIs_active(userById.getIs_active());

        return userResponseDto;
    }

    @Override
    public UserResponseDto setActive(long id, UserRequestDto is_active) {
       User setActiveUser = userRepository.findById(id).orElseThrow();

       setActiveUser.setIs_active(is_active.getIs_active());

       userRepository.save(setActiveUser);

       UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUser_id(setActiveUser.getUser_id());
        userResponseDto.setName(setActiveUser.getName());
        userResponseDto.setEmail(setActiveUser.getEmail());
        userResponseDto.setPassword(setActiveUser.getPassword());
        userResponseDto.setCep(setActiveUser.getCep());
        userResponseDto.setCpf(setActiveUser.getCpf());
        userResponseDto.setIs_active(setActiveUser.getIs_active());

       return userResponseDto;
    }
}
