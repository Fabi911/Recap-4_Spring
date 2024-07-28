package de.webdev.recap4_spring.ai;

/**
 * {
 * "role": "user",
 * "content": "Say this is a test!"
 *  }
 */
public record OpenAiAnswer(String role, String content) {
}
