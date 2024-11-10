package com.app.shop.controller;

import com.app.shop.dto.request.CategoryReq;
import com.app.shop.entity.Category;
import com.app.shop.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ICategoryService categoryService;
    private CategoryReq categoryReq;
    private Category category;

    @BeforeEach
    void initData() {
        categoryReq = CategoryReq.builder()
                .name("Category")
                .build();
        category = Category.builder()
                .id(1L)
                .name("Category")
                .build();
    }

//    @Test
//    void createCategory_validReq_success() throws Exception {
//        // GIVEN
//        ObjectMapper objectMapper = new ObjectMapper();
//        String content = objectMapper.writeValueAsString(categoryDTO);
//        Mockito.when(categoryService.createCategory(ArgumentMatchers.any()))
//                .thenReturn(category);
//
//        // WHEN
//        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(content)
//                );
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("code").value(2000));
//        // THEN
//    }
}
