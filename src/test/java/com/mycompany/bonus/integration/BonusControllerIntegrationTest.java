package com.mycompany.bonus.integration;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.mycompany.bonus.BonusApplication;
import com.mycompany.bonus.builders.BonusBuilder;
import com.mycompany.bonus.entity.Bonus;
import com.mycompany.bonus.service.BonusRepository;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BonusApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class BonusControllerIntegrationTest {
    private static final String BONUS = "/bonus";
    private static final String UPDATE_BONUS = "/bonus/{id}";
    private static final String FIRST_BONUS_DESCRIPTION = "bonus1";
    private static final String SECOND_BONUS_DESCRIPTION = "bonus2";
    private static final String THIRD_BONUS_DESCRIPTION = "bonus3";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";
    private static final String ACTIVE_BONUS_URL = "/bonus/active";
    private static final String INACTIVE_BONUS_URL = "/bonus/inactive";
    private static final Bonus INACTIVE_BONUS = new BonusBuilder().setStatus(false).
            setName("History").setComments("First Bonus").setDescription(FIRST_BONUS_DESCRIPTION).
            setCreatedDate(new Date()).build();
    private static final Bonus ACTIVE_BONUS = new BonusBuilder().setStatus(true).
            setName("Active").setComments("Second Bonus").setDescription(SECOND_BONUS_DESCRIPTION)
            .setCreatedDate(new Date()).build();
    private static final Bonus NEW_BONUS = new BonusBuilder().setStatus(true).
            setName("New").setComments("Third Bonus").setDescription(THIRD_BONUS_DESCRIPTION)
            .setCreatedDate(new Date()).build();

    @Autowired
    BonusRepository bonusRepository;

    @Value("${local.server.port}")
    private int serverport;

    @Before
    public void setup() {
        bonusRepository.deleteAll();;
        bonusRepository.saveAndFlush(ACTIVE_BONUS);
        bonusRepository.saveAndFlush(INACTIVE_BONUS);
        RestAssured.port = serverport;
    }

    @Test
    public void shouldGetAllBonuses() {
        when().get(BONUS).then().statusCode(HttpStatus.SC_OK).body(DESCRIPTION , hasItems(FIRST_BONUS_DESCRIPTION,
                SECOND_BONUS_DESCRIPTION)).body(STATUS, hasItems(true, false));
    }

    @Test
    public void shouldGetAllActiveBonuses() {
        when().get(ACTIVE_BONUS_URL).then().statusCode(HttpStatus.SC_OK).body(DESCRIPTION ,
                hasItems(SECOND_BONUS_DESCRIPTION)).body(STATUS, hasItems(true));
    }

    @Test
    public void shouldGetAllInActiveBonuses() {
        when().get(INACTIVE_BONUS_URL).then().statusCode(HttpStatus.SC_OK).body(DESCRIPTION ,
                hasItems(FIRST_BONUS_DESCRIPTION)).body(STATUS, hasItems(false));
    }

    @Test
    public void shouldSaveNewBonus() {
        given().body(NEW_BONUS).contentType(ContentType.JSON).when().post(BONUS)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnBadRequestWithEmptyBody() {
        given().contentType(ContentType.JSON).when().post(BONUS).then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public  void shouldReturnNotSupportedWithNoContentType() {
        given().body(NEW_BONUS).when().post(BONUS).then().statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

}
