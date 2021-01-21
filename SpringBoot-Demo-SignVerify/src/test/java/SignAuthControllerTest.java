
import com.alibaba.fastjson.JSON;
import com.xiao.SignDemoApplication;
import com.xiao.filter.HttpServletRequestFilter;
import com.xiao.pojo.UserInfo;
import com.xiao.utils.MapUtil;
import com.xiao.utils.SignUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SignAuth
 * @Description 签名验证控制层
 * @Date 2020/11/2
 * @Author KongX
 * @version: 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SignDemoApplication.class)
@WebAppConfiguration
public class SignAuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(new HttpServletRequestFilter()).build();
    }

    /**
     * 测试get请求携带params
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        //构建参数map
        UserInfo userInfo = new UserInfo("zhangsan", 18);
        HashMap<String, String> stringMap = MapUtil.getNamValMap(userInfo);
        Map<String, String> signMap = SignUtil.sign(stringMap);
        //调用接口，传入添加的用户参数
        mockMvc.perform(MockMvcRequestBuilders.get("/signAuth/signDemo01?" + SignUtil.createLinkString(signMap))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试post请求携带body
     *
     * @throws Exception
     */
    @Test
    public void testPost() throws Exception {
        //构建参数map
        UserInfo userInfo = new UserInfo("zhangsan", 18);
        HashMap<String, String> stringMap = MapUtil.getNamValMap(userInfo);
        Map<String, String> signMap = SignUtil.sign(stringMap);
        //调用接口，传入添加的用户参数
        mockMvc.perform(MockMvcRequestBuilders.post("/signAuth/signDemo02")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(signMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试post请求,同时携带params和body
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        //构建参数map
        UserInfo userInfo = new UserInfo("zhangsan", 18);
        HashMap<String, String> stringMap = MapUtil.getNamValMap(userInfo);
        stringMap.put("address", "shenzhen");
        Map<String, String> signMap = SignUtil.sign(stringMap);
        signMap.remove("address");
        //调用接口，传入添加的用户参数
        mockMvc.perform(MockMvcRequestBuilders.post("/signAuth/signDemo03?address=shenzhen")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(signMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }
}
