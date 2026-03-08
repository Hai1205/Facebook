package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.dtos.Response;
import com.backend.exceptions.OurException;
import com.backend.services.apis.ChatApi;
import com.backend.services.instants.OnlineUserTracker;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    @Autowired
    private ChatApi chatApi;

    @Autowired
    private OnlineUserTracker onlineUserTracker;

    @GetMapping("/between/{userId}/{otherUserId}")
    public ResponseEntity<Response> getOrCreateConversation(
            @PathVariable String userId,
            @PathVariable String otherUserId) {

        Response response = chatApi.getOrCreateConversation(userId, otherUserId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/between/{userId}/{otherUserId}")
    public ResponseEntity<Response> createConversation(
            @PathVariable String userId,
            @PathVariable String otherUserId) {

        if (userId == null || otherUserId == null) {
            throw new OurException("userId and otherUserId are required");
        }

        Response response = chatApi.getOrCreateConversation(userId, otherUserId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Response> getUserConversations(
            @PathVariable String userId) {
        Response response = chatApi.getUserConversations(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{conversationId}/users/{userId}/messages")
    public ResponseEntity<Response> getMessages(
            @PathVariable String conversationId,
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Response response = chatApi.getMessages(conversationId, userId, page, size);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/groups")
    public ResponseEntity<Response> getGroupConversation() {
        Response response = chatApi.getGroupConversation();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{conversationId}/users/{userId}")
    public ResponseEntity<Response> getConversation(
            @PathVariable String conversationId,
            @PathVariable String userId) {
        Response response = chatApi.getConversation(conversationId, userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{userId}/participants")
    public ResponseEntity<Response> getUsersWithConversation(@PathVariable String userId) {
        Response response = chatApi.getUsersWithConversation(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/groups")
    public ResponseEntity<Response> createGroupConversation(@RequestBody Map<String, Object> requestBody) {
        String groupName = (String) requestBody.get("name");
        List<?> userIdsRaw = (List<?>) requestBody.get("userIds");
        List<String> userIds = userIdsRaw.stream()
                .map(id -> String.valueOf(id.toString()))
                .toList();

        Response response = chatApi.createGroupConversation(groupName, userIds);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/{conversationId}/users/{userId}")
    public ResponseEntity<Response> addUserToGroup(
            @PathVariable String conversationId,
            @PathVariable String userId) {
        Response response = chatApi.addUserToGroup(conversationId, userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{conversationId}/users/{userId}")
    public ResponseEntity<Response> deleteUserFromGroup(@PathVariable String conversationId,
            @PathVariable String userId) {
        Response response = chatApi.deleteUserFromGroup(conversationId, userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/online-users")
    public ResponseEntity<Response> getOnlineUsers() {
        Response response = new Response();
        try {
            Set<String> onlineUsers = onlineUserTracker.getOnlineUsers();
            response.setStatusCode(200);
            response.setMessage("Online users retrieved successfully");
            response.setData(Map.of("onlineUsers", onlineUsers, "count", onlineUsers.size()));

            org.slf4j.LoggerFactory.getLogger(ConversationController.class)
                    .info("Retrieved {} online users", onlineUsers.size());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving online users: " + e.getMessage());

            org.slf4j.LoggerFactory.getLogger(ConversationController.class)
                    .error("Error retrieving online users", e);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{userId}/online-status")
    public ResponseEntity<Response> isUserOnline(@PathVariable String userId) {
        Response response = new Response();
        try {
            if (userId == null || userId.trim().isEmpty()) {
                throw new IllegalArgumentException("User ID cannot be empty");
            }

            boolean isOnline = onlineUserTracker.isUserOnline(userId);
            response.setStatusCode(200);
            response.setMessage("User online status retrieved successfully");
            response.setData(Map.of("userId", userId, "online", isOnline));

            org.slf4j.LoggerFactory.getLogger(ConversationController.class)
                    .debug("User {} is online: {}", userId, isOnline);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving user online status: " + e.getMessage());

            org.slf4j.LoggerFactory.getLogger(ConversationController.class)
                    .error("Error checking online status for user {}: {}", userId, e.getMessage());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/ai")
    public ResponseEntity<Response> chatAI(@RequestBody String prompt) {
        Response response = new Response();
        try {
            // Xử lý prompt cho chatbot AI hoặc gọi dịch vụ bên ngoài
            String aiResponse = "Đây là phản hồi từ AI cho: " + prompt;
            response.setStatusCode(200);
            response.setMessage("AI response generated successfully");
            response.setData(Map.of("response", aiResponse));
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error generating AI response: " + e.getMessage());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}