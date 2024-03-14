package com.example.lesson_thyemleaf;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

@SpringBootTest
class LessonThyemleafApplicationTests {
    @Autowired
    private ProfileRepository profileRepository;

    void testUpload() {
        ProfileEntity entity = new ProfileEntity();
        entity.setName("Ali");
        entity.setSurname("Aliyev");
        entity.setPhone("1234");
        entity.setPassword(MD5Util.getMd5("1"));
        entity.setRole("ROLE_ADMIN");
        profileRepository.save(entity);
    }

    @Test
    void contextLoads() {
    }

}
