package com.alura.screenmatch.service;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ChatGptCalling {
    public static String translating(String text) {
        final String APIKEY = "";
        OpenAiService service = new OpenAiService(APIKEY);

        CompletionRequest requisition = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduce a español el siguiente texto:" + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(requisition);
        return response.getChoices().get(0).getText();
    }
}
