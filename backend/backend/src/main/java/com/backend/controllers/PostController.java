package com.backend.controllers;

import com.backend.dtos.Response;
import com.backend.services.apis.PostsApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostsApi postsApi;

    @GetMapping("/")
    public ResponseEntity<Response> getAllPost() {
        Response response = postsApi.getAllPost();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/stories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllStory() {
        Response response = postsApi.getAllStory();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/reports")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllReport() {
        Response response = postsApi.getAllReport();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable("postId") String postId) {
        Response response = postsApi.getPost(postId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("users/{userId}")
    public ResponseEntity<Response> createPost(
            @PathVariable("userId") String userId,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam("content") String content,
            @RequestParam("privacy") String privacy) {
        Response response = postsApi.createPost(userId, files, content, privacy);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Response> updatePost(
            @PathVariable("postId") String postId,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam("content") String content,
            @RequestParam("privacy") String privacy) {
        Response response = postsApi.updatePost(postId, files, content, privacy);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/stories")
    public ResponseEntity<Response> createStory(
            @RequestParam("userId") String userId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("privacy") String privacy) {
        Response response = postsApi.createStory(userId, file, privacy);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Response> deletePost(@PathVariable("postId") String postId) {
        Response response = postsApi.deletePost(postId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/stories/{storyId}")
    public ResponseEntity<Response> deleteStory(@PathVariable("storyId") String storyId) {
        Response response = postsApi.deleteStory(storyId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/{postId}/users/{userId}/likes")
    public ResponseEntity<Response> likePost(
            @PathVariable("postId") String postId,
            @PathVariable("userId") String userId) {
        Response response = postsApi.likePost(postId, userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/{postId}/users/{userId}/comments")
    public ResponseEntity<Response> commentPost(
            @PathVariable("postId") String postId,
            @PathVariable("userId") String userId,
            @RequestParam("text") String text) {
        Response response = postsApi.commentPost(postId, userId, text);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Response> deleteComment(
            @PathVariable("commentId") String commentId,
            @PathVariable("postId") String postId) {
        Response response = postsApi.deleteComment(commentId, postId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/{postId}/users/{userId}/shares")
    public ResponseEntity<Response> sharePost(
            @PathVariable("postId") String postId,
            @PathVariable("userId") String userId) {
        Response response = postsApi.sharePost(postId, userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{userId}/feed")
    public ResponseEntity<Response> getUserFeed(
            @PathVariable(value = "userId", required = false) String userId) {
        Response response = postsApi.getUserFeed(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{userId}/stories/feed")
    public ResponseEntity<Response> getUserStoryFeed(
            @PathVariable("userId") String userId) {
        Response response = postsApi.getUserStoryFeed(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchPosts(
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "privacy", required = false) String privacy) {
        Response response = postsApi.searchPosts(content, status, privacy);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/reports/search")
    public ResponseEntity<Response> searchReports(
            @RequestParam(name = "reason", required = false) String reason,
            @RequestParam(name = "contentType", required = false) String contentType,
            @RequestParam(name = "status", required = false) String status) {
        Response response = postsApi.searchReports(contentType, reason, status);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/reports/users/{userId}/contents/{contentId}")
    public ResponseEntity<Response> report(
            @PathVariable("userId") String userId,
            @PathVariable("contentId") String contentId,
            @RequestParam("contentType") String contentType,
            @RequestParam("reason") String reason,
            @RequestParam("additionalInfo") String additionalInfo) {
        Response response = postsApi.report(contentId, userId, contentType, reason, additionalInfo);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/reports/{contentId}")
    public ResponseEntity<Response> resolveReport(
            @PathVariable("contentId") String contentId,
            @RequestParam("status") String status) {
        Response response = postsApi.resolveReport(contentId, status);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<Response> deleteReport(@PathVariable("reportId") String reportId) {
        Response response = postsApi.deleteReport(reportId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}