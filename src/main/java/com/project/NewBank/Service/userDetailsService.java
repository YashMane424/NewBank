/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.NewBank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
class userDetailsService {
    @Override
    public static UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        @Autowired
        private UserRepository userRepo;

        User user = userRepo.findByEmail(email);

        if(user == null){
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
        }
        return null;
    }
}
