package com.ileiwe.security.user;

import com.ileiwe.data.repository.LearningPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class LearningPartyServiceImpl implements UserDetailsService {

    @Autowired
    private LearningPartyRepository learningPartyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
