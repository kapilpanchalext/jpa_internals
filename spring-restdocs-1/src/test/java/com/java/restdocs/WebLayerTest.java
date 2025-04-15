package com.java.restdocs;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.restdocs.controller.HomeController;

import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World!"))).andDo(document("home"));
	}

	@Test
	public void shouldDocumentGreetingEndpoint() throws Exception {
		this.mockMvc.perform(get("/greet/{name}", "John").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Hello, John"))
				.andDo(document("greet",
						pathParameters(
								parameterWithName("name").description("The name of the person to greet")),
						responseFields(fieldWithPath("message").description("The greeting message returned"))));
	}

  @Test
  void postContent_shouldReturnMap() throws Exception {
      String requestBody = "\"Sample Value\""; // Sending plain JSON string (not an object)

      this.mockMvc.perform(post("/postVideoContents")
          .queryParam("userId", "nmscemuadmin01")
          .contentType(MediaType.APPLICATION_JSON)
          .content(new ObjectMapper().writeValueAsString("videoContent")))
      .andExpect(status().isOk())
      .andDo(document("post-video-content",
              queryParameters(
                  parameterWithName("userId").description("ID of the user uploading video content")
              ),
              requestFields(
                  fieldWithPath("title").description("Title of the video"),
                  fieldWithPath("duration").description("Duration in minutes")
                  // describe all fields in your VideoContentAcadsBean
              )
      ));
  }
	
}
