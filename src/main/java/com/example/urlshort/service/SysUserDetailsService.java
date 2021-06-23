package com.example.urlshort.service;

import com.example.urlshort.entity.SysUser;
import com.example.urlshort.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s is not found", username)));

        return new User(sysUser.getUsername(), sysUser.getPassword(), Collections.emptyList());
    }

    public SysUser addUser(String username, String password) {
        return sysUserRepository.save(SysUser.builder().username(username).password(password).build());
    }
}
