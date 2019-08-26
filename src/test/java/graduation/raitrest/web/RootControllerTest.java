package graduation.raitrest.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(view().name("users"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
//                .andExpect(model().attribute("users",
//                        new AssertionMatcher<List<User>>() {
//                            @Override
//                            public void assertion(List<User> actual) throws AssertionError {
//                                assertMatch(actual, ADMIN, USER);
//                            }
//                        }
//                ));
    }
}