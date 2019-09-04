package com.example.demo;
import com.example.demo.entity.*;
import com.example.demo.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewSystemTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository reviewRepository;

    Status status = new Status();
    CarData carData = new CarData();
    CarType carType = new CarType();
    CarColor carColor = new CarColor();
    Review review = new Review();

    private Validator validator;
    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        carData.setcC("500");
        carData.setModel("CITY 2020");
        entityManager.persistAndFlush(carData);

        carColor.setColor("Titanium");
        entityManager.persistAndFlush(carColor);

        carType.setCarType("4-door");
        entityManager.persistAndFlush(carType);

        status.setStatus("Done");
        entityManager.persistAndFlush(status);

        review.setCarData(entityManager.persistFlushFind(carData));
        review.setCarColor(entityManager.persistFlushFind(carColor));
        review.setCarType(entityManager.persistFlushFind(carType));
        review.setStatus(entityManager.persistFlushFind(status));
        entityManager.persistAndFlush(review);

        /*carData;
        carColor;
        carType;
        status;
        comment;*/
    }
    @Test
    public void testCommentSizeCannotLowerThanMin() {
        Review review = new Review();
        review.setCarData(carData);
        review.setCarColor(carColor);
        review.setCarType(carType);
        review.setStatus(status);
        review.setComment("ก");
        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentSizeCannotLowerThanMin**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testCommentSizeCannotHigherThanMax() {
        Review review = new Review();
        review.setCarData(carData);
        review.setCarColor(carColor);
        review.setCarType(carType);
        review.setStatus(status);
        review.setComment("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentSizeCannotHigherThanMax**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testCommentCannotMatchPattern() {
        Review review = new Review();
        review.setCarData(carData);
        review.setCarColor(carColor);
        review.setCarType(carType);
        review.setStatus(status);
        review.setComment("Aกกกกกกกกกกกกกกกกกกก");
        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentCannotMatchPattern**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testReviewPass() {
        Review review = new Review();
        review.setCarData(carData);
        review.setCarColor(carColor);
        review.setCarType(carType);
        review.setStatus(status);
        review.setComment("ทดสอบ");
        try {
            entityManager.persist(review);
            entityManager.flush();
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testReviewPass**********************");
            System.out.println(e.getConstraintViolations());
        }
    }



}
