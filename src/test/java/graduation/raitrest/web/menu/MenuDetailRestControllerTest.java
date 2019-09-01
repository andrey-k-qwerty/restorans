package graduation.raitrest.web.menu;

import graduation.raitrest.RestoranTestData;
import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.to.MenuDetailTo;
import graduation.raitrest.service.MenuDetailsService;
import graduation.raitrest.web.AbstractControllerTest;
import graduation.raitrest.web.SecurityUtil;
import graduation.raitrest.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static graduation.raitrest.MenuDetailsTestData.*;
import static graduation.raitrest.RestoranTestData.RESTAURANT_ID;
import static graduation.raitrest.RestoranTestData.RESTAURANT_STAR;
import static graduation.raitrest.TestUtil.readFromJson;
import static graduation.raitrest.TestUtil.readFromJsonMvcResult;
import static graduation.raitrest.UserTestData.MANAGER_ID;
import static graduation.raitrest.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuDetailRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuDetailRestController.REST_URL + '/';

    @Autowired
    private MenuDetailsService service;

    @Test
    void get() throws Exception {
        SecurityUtil.setAuthUserId(MANAGER_ID);

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + MENU_DETAILS_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, MenuDetails.class), MENU_DETAILS_STAR_TODAY_1));
    }

    @Test
    void delete() throws Exception {
        SecurityUtil.setAuthUserId(MANAGER_ID);
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + MENU_DETAILS_ID))
                .andExpect(status().isNoContent());

        List<MenuDetails> menuAllManager = service.getFilterByDateByRestaurant(LocalDate.now(), LocalDate.now().plusDays(1L), RESTAURANT_ID);
        assertMatch(menuAllManager, MENU_DETAILS_STAR_TODAY_2, MENU_DETAILS_STAR_TODAY_3, MENU_DETAILS_STAR_TODAY_4);
    }

    @Test
    void getAll() throws Exception {

        List<MenuDetailTo> allMenu = service.getAll();

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonTo(allMenu));

    }

    @Test
    void getAllCurrentDay() throws Exception {
        List<MenuDetails> allMenu = service.getFilterByDate(LocalDate.now(), LocalDate.now().plusDays(1L));

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(allMenu));

    }

    @Test
    void createWithLocation() throws Exception {
        SecurityUtil.setAuthUserId(MANAGER_ID);
        MenuDetails newMenu = new MenuDetails(null,// RESTAURANT_STAR
                "Пятое блюдо", "Хлеб", "1 кусочек", new BigDecimal("0.10"), LocalDateTime.now());

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andDo(print());


        MenuDetails created =  readFromJson(action, MenuDetails.class);
        newMenu.setId(created.getId());

 //       newMenu.setRestaurant(created.getRestaurant());
        assertMatch(created, newMenu);
//        MenuDetails menuDetailsGet = service.get(created.getId());
//        assertMatch(menuDetailsGet, newMenu);


    }

    @Test
    void update() throws Exception {
        SecurityUtil.setAuthUserId(MANAGER_ID);
        MenuDetails updated = new MenuDetails(MENU_DETAILS_ID + 3, RESTAURANT_STAR,
                "Первое блюдо", "Супер Уха", "100 грамм", new BigDecimal("25.00"),
                LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + (MENU_DETAILS_ID + 3)).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MenuDetails actual = service.get(MENU_DETAILS_ID + 3, MANAGER_ID);
        assertMatch(actual, updated);

    }
}