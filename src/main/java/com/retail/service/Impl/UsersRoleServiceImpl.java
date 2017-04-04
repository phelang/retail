package com.retail.service.Impl;

import com.retail.model.UserRole;
import com.retail.repository.UserRoleRepository;
import com.retail.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UsersRoleServiceImpl implements UserRoleService {

    @Inject
    private UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRole) {
        return this.userRoleRepository.save(userRole);
    }

    public UserRole findById(Integer id) {
        return this.userRoleRepository.findById(id);
    }

    public List<UserRole> findAll() {
        return this.userRoleRepository.findAll();
    }

    public UserRole update(UserRole userRole) {
        return this.userRoleRepository.update(userRole);
    }

    public UserRole delete(Integer id) {
        return this.userRoleRepository.delete(id);
    }
}
