package com.retail.service.Impl;

import com.retail.model.Users;
import com.retail.repository.UsersRepository;
import com.retail.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Inject
    private UsersRepository usersRepository;

    public void setUsersRepository(UsersRepository usersRepository){
        this.usersRepository= usersRepository;
    }

    @Transactional
    public Users save(Users users) {
        return this.usersRepository.save(users);
    }

    @Transactional
    public Users findById(Integer id) {
        return this.usersRepository.findById(id);
    }

    @Transactional
    public List<Users> findAll() {
        return this.usersRepository.findAll();
    }

    @Transactional
    public Users update(Users users) {
        return this.usersRepository.update(users);
    }

    @Transactional
    public Users delete(Integer id) {
        return this.usersRepository.delete(id);
    }
}
