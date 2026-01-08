//package com.application.booker.serviceImpl;
//
//import com.application.booker.entity.Users;
//import com.application.booker.repository.UsersRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdminUserInitializer {
//
//    @Bean
//    public CommandLineRunner createAdminUser(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
//        return args -> {
//            if(usersRepository.findByUsername("admin").isEmpty()){
//                Users adminUser = new Users();
//                adminUser.setUsername("admin");
//                adminUser.setRole("ROLE_ADMIN");
//                adminUser.setPassword(passwordEncoder.encode("admin123"));
//                usersRepository.save(adminUser);
//                System.out.println("Default Admin User Created");
//            }
//            if(usersRepository.findByUsername("user").isEmpty()){
//                Users adminUser = new Users();
//                adminUser.setUsername("user");
//                adminUser.setRole("ROLE_USER");
//                adminUser.setPassword(passwordEncoder.encode("user123"));
//                usersRepository.save(adminUser);
//                System.out.println("Default User Created");
//            }
//        };
//    }
//}
