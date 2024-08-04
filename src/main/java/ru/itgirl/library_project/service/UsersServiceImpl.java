package ru.itgirl.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.library_project.dto.UsersCreateDto;
import ru.itgirl.library_project.dto.UsersDto;
import ru.itgirl.library_project.dto.UsersUpdateDto;
import ru.itgirl.library_project.model.Users;
import ru.itgirl.library_project.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public UsersDto getUsersById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow();
        UsersDto usersDto = convertEntityToDto(users);
        return usersDto;
    }

    @Override
    public UsersDto createUsers(UsersCreateDto usersCreateDto) {
        Users users = usersRepository.save(convertDtoToEntity(usersCreateDto));
        UsersDto usersDto = convertEntityToDto(users);
        return usersDto;
    }

    @Override
    public UsersDto updateUsers(UsersUpdateDto usersUpdateDto) {
        Users users = usersRepository.findById(usersUpdateDto.getId()).orElseThrow();
        users.setLogin(usersUpdateDto.getLogin());
        users.setPassword(usersUpdateDto.getPassword());
        users.setRole(usersUpdateDto.getRole());
        Users savedUsers = usersRepository.save(users);
        UsersDto usersDto = convertEntityToDto(savedUsers);
        return usersDto;
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private Users convertDtoToEntity(UsersCreateDto usersCreateDto) {
        return Users.builder()
                .login(usersCreateDto.getLogin())
                .password(usersCreateDto.getPassword())
                .role(usersCreateDto.getRole())
                .build();
    }

    private UsersDto convertEntityToDto(Users users) {
        UsersDto usersDto = UsersDto.builder()
                .id(users.getId())
                .login(users.getLogin())
                .password(users.getPassword())
                .role(users.getRole())
                .build();
        return usersDto;
    }
}