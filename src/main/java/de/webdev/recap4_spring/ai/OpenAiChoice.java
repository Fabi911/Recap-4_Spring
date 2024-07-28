package de.webdev.recap4_spring.ai;
/**
 * {
 *  *             "message": {
 *  *                 "role": "assistant",
 *  *                 "content": "\n\nThis is a test!"
 *  *             }
 *  *         }
 */
public record OpenAiChoice(OpenAiAnswer message) {
}
