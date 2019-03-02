package ru.mborisov.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Comment {
    private final String author;
    private final String message;

    @JsonCreator
    public Comment(
            @JsonProperty("author") String author,
            @JsonProperty("message") String message
    ) {
        this.author = author;
        this.message = message;
    }
}
