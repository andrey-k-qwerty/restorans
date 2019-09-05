package graduation.raitrest.web;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static graduation.raitrest.TestUtil.userAuth;
import static graduation.raitrest.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
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
   /* @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                assertMatch(actual, ADMIN, USER);
                            }
                        }
                ));
    }*/

}