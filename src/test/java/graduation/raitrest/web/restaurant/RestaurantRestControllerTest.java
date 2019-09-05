package graduation.raitrest.web.restaurant;

import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.service.RestaurantService;
import graduation.raitrest.web.AbstractControllerTest;
import graduation.raitrest.web.SecurityUtil;
import graduation.raitrest.web.json.JsonUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.TestUtil.*;
import static graduation.raitrest.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(service.getAll()));

    }

    @Test
    void getAllByManager() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "manager/" + MANAGER_ID).with(userHttpBasic(MANAGER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(service.getAll(MANAGER_ID)));

    }

    @Test
    void get() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID).with(userHttpBasic(MANAGER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT_STAR));

    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID).with(userHttpBasic(MANAGER)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(service.getAll(), RESTAURANT_PEARL, RESTAURANT_SUPER_STAR, RESTAURANT_BLACK_PEARL);
    }

    @Test
    void update() throws Exception {

        Restaurant updated = getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(MANAGER))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());


        assertMatch(service.get(RESTAURANT_ID, MANAGER_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {

        Restaurant newRestaurant = getCreated();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(MANAGER))
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant returned = readFromJson(action, Restaurant.class);
        newRestaurant.setId(returned.getId());

        assertMatch(returned, newRestaurant);
        assertMatch(service.getAll(MANAGER_ID), RESTAURANT_STAR, RESTAURANT_SUPER_STAR,newRestaurant);

    }
}