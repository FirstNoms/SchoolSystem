package com.ileiwe.data.repository;

import com.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts=("/db/insert.sql"))
class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test

    void saveInstructorAsLearningPartyTest(){
        //create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor =Instructor.builder().firstName("john").lastName("Alao").learningParty(user).build();
        //map instructor with learning party
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("instructor after saving -> {}", instructor);
    }

    @Test
    void havingInstructorWithNullValuesTest(){
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor =Instructor.builder().firstName(null).lastName(null).learningParty(user).build();

        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void instructorWithEmptyStringValuesTest() {
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor =Instructor.builder().firstName(" ").lastName("").learningParty(user).build();
        assertThrows(DataIntegrityViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorWithUniqueEmailsTest() {
        LearningParty user1 = new LearningParty("trainer@ileiwe.com", "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor =Instructor.builder().firstName("Lapel").lastName("Lapol").learningParty(user1).build();

        //save to db
        instructorRepository.save(instructor);
       // assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getEmail()).isEqualTo("trainer@ileiwe.com");
        assertThat(instructor.getLearningParty().getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_INSTRUCTOR);
        assertThat(instructor.getLearningParty().getAuthorities().get(0).getId()).isNotNull();

        //create another instructor with same email
        LearningParty user2 = new LearningParty("trainer@ileiwe.com", "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor2 =Instructor.builder().firstName("Lapel").lastName("Lapol").learningParty(user2).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor2));
    }

    @Test
    void updateInstructorTest(){
        LearningParty user = new LearningParty("trainer5@ileiwe.com", "124pass", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor =Instructor.builder().firstName("Happy").lastName("Happier").learningParty(user).build();

        //???????????????
        log.info("Instructor before saving --> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving --> {}", instructor);

        //update Instructor's information by adding a bio. ???????
        instructor.setBio("Teaching is from within, giving knowledge is like giving life");
        instructor.setSpecialization("Soft ware Engineer of life");
        instructor.setGender(Gender.FEMALE);

        instructorRepository.save(instructor);
        //????????????
        Optional<Instructor> foundInstructor = instructorRepository.findById(instructor.getId());

        assertThat(instructor.getBio()).isEqualTo(foundInstructor.get().getBio());
        assertThat(instructor.getSpecialization()).isEqualTo(foundInstructor.get().getSpecialization());
        assertThat(instructor.getGender()).isEqualTo(foundInstructor.get().getGender());

    }


//    @Test
//    void updateInstructorAfterCreationTest(){
//        LearningParty user = new LearningParty("trainer5@ileiwe.com", "124pass", new Authority(Role.ROLE_INSTRUCTOR));
//        Instructor instructor =Instructor.builder().firstName("Happy").lastName("Happier").learningParty(user).build();
//
//        log.info("Instructor after saving ->{}", instructor);
//
//        Instructor savedInstructor =instructorRepository.findById(instructor.getId()).orElse(null);
//
//    }

    @AfterEach
    void tearDown() {
    }
}