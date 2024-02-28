package com.simplon.servicemedia;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MediaService implements IMedia{
    private MediaRepository mediaRepository;
    private final Path fileStorageLocation;
    private MapperConfig mapperConfig;

    MediaService(MediaRepository mediaRepository1,MapperConfig mapperConfig,FileStorageProperties fileStorageProperties) throws FileStorageException {
        this.mediaRepository = mediaRepository1;
        this.mapperConfig = mapperConfig;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public MediaDTO addMedia(MultipartFile file,long postId) throws FileStorageException {
        String fileName = this.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/MediaMicroService/uploads/")
                .path(fileName)
                .toUriString();
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setName(fileDownloadUri);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        mediaDTO.setDateCreation(now.format(dateFormat));
        mediaDTO.setPostId(postId);
        System.out.println(file.getContentType());
        String fullContentType = file.getContentType();
        String[] parts = fullContentType.split("/");
        String type = parts[0];
        if(type.equals("image")){
            mediaDTO.setTypeFile(TypeFile.IMAGE);
        }else if(type.equals("video")){
            mediaDTO.setTypeFile(TypeFile.VIDEO);
        }else if(type.equals("audio")){
            mediaDTO.setTypeFile(TypeFile.AUDIO);
        }
        Media media = mediaRepository.save(this.mapperConfig.modelMapper().map(mediaDTO,Media.class));
        return this.mapperConfig.modelMapper().map(media,MediaDTO.class);
    }

    @Override
    public boolean deleteMedia(long id) {
        
        return false;
    }

    @Override
    public List<MediaDTO> listMedia() {
        return null;
    }

    public String storeFile(MultipartFile file) throws FileStorageException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException | FileStorageException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
