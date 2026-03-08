package com.backend.services.apis;

import com.backend.dtos.*;
import com.backend.entities.*;
import com.backend.exceptions.OurException;
import com.backend.repositories.*;
import com.backend.utils.mappers.UserMapper;

import com.example.cloudinarycommon.CloudinaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class BioApi {
    private static final Logger log = LoggerFactory.getLogger(BioApi.class);

    @Autowired
    private BioRepository bioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Response updateUserBio(String userId, String bioText, String liveIn, String relationship, String workplace,
            String education, String phone, String hometown) {
        Response response = new Response();

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new OurException("User Not Found"));
            Bio bio = bioRepository.findById(user.getBio().getId())
                    .orElseThrow(() -> new OurException("User bio Not Found"));

            if (bioText != null && !bioText.isEmpty() && !bioText.equals(bio.getBioText())) {
                bio.setBioText(bioText);
            }

            if (liveIn != null && !liveIn.isEmpty() && !liveIn.equals(bio.getLiveIn())) {
                bio.setLiveIn(liveIn);
            }

            if (relationship != null && !relationship.isEmpty() && !relationship.equals(bio.getRelationship())) {
                bio.setRelationship(relationship);
            }

            if (workplace != null && !workplace.isEmpty() && !workplace.equals(bio.getWorkplace())) {
                bio.setWorkplace(workplace);
            }

            if (education != null && !education.isEmpty() && !education.equals(bio.getEducation())) {
                bio.setEducation(education);
            }

            if (phone != null && !phone.isEmpty() && !phone.equals(bio.getPhone())) {
                bio.setPhone(phone);
            }

            if (hometown != null && !hometown.isEmpty() && !hometown.equals(bio.getHometown())) {
                bio.setHometown(hometown);
            }

            bioRepository.save(bio);
            UserDTO userDTO = UserMapper.mapEntityToDTOFull(user);

            response.setStatusCode(200);
            response.setMessage("Bio updated successfully");
            response.setUser(userDTO);
        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        return response;
    }

    public Response updateCoverPhoto(String userId, MultipartFile coverPhoto) {
        Response response = new Response();

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new OurException("User Not Found"));

            if (user.getCoverPhotoPublicId() != null && !user.getCoverPhotoPublicId().isEmpty()) {
                cloudinaryService.deleteFile(user.getCoverPhotoPublicId());
                log.debug("Deleted cover photo file for userId={}", userId);
            }

            if (coverPhoto != null && !coverPhoto.isEmpty()) {
                Map<String, Object> uploadResult = cloudinaryService.uploadFile(coverPhoto);
                if (uploadResult.containsKey("error")) {
                    log.error("File upload failed for userId={}: {}", userId, uploadResult.get("error"));
                    throw new OurException("Failed to upload cover photo: " + uploadResult.get("error"));
                }

                String coverPhotoUrl = (String) uploadResult.get("url");
                String coverPhotoPublicId = (String) uploadResult.get("publicId");

                user.setCoverPhotoPublicId(coverPhotoPublicId);
                user.setCoverPhotoUrl(coverPhotoUrl);
            }

            User savedUser = userRepository.save(user);
            UserDTO userDTO = UserMapper.mapEntityToDTOFull(savedUser);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);
        } catch (BadCredentialsException e) {
            response.setStatusCode(401);
            response.setMessage(e.getMessage());
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        return response;
    }

    public Response updateAvatarPhoto(String userId, MultipartFile avatarPhoto) {
        Response response = new Response();

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new OurException("User Not Found"));

            if (user.getAvatarPhotoPublicId() != null && !user.getAvatarPhotoPublicId().isEmpty()) {
                cloudinaryService.deleteFile(user.getAvatarPhotoPublicId());
                log.debug("Deleted avatar file for userId={}", userId);
            }

            if (avatarPhoto != null && !avatarPhoto.isEmpty()) {
                Map<String, Object> uploadResult = cloudinaryService.uploadFile(avatarPhoto);
                if (uploadResult.containsKey("error")) {
                    log.error("File upload failed for userId={}: {}", userId, uploadResult.get("error"));
                    throw new OurException("Failed to upload avatar photo: " + uploadResult.get("error"));
                }

                String avatarPhotoUrl = (String) uploadResult.get("url");
                String avatarPhotoPublicId = (String) uploadResult.get("publicId");

                user.setAvatarPhotoPublicId(avatarPhotoPublicId);
                user.setAvatarPhotoUrl(avatarPhotoUrl);
            }

            User savedUser = userRepository.save(user);
            UserDTO userDTO = UserMapper.mapEntityToDTOFull(savedUser);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);
        } catch (BadCredentialsException e) {
            response.setStatusCode(401);
            response.setMessage(e.getMessage());
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        return response;
    }
}
