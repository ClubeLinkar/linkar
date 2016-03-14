package br.com.clubelinkar.support.file

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin

/**
 * Created by lennonjesus on 1/22/16.
 */
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

@CrossOrigin
@RestController
public class FileController {

//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) {
//
//        FileUpload fileUpload = fileUploadService.findByFilename(filename)
//
//        // No file found based on the supplied filename
//        if (fileUpload == null) {
//            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND)
//        }
//
//        // Generate the http headers with the file properties
//        HttpHeaders headers = new HttpHeaders()
//        headers.add("content-disposition", "attachment filename=" + fileUpload.getFilename())
//
//        // Split the mimeType into primary and sub types
//        String primaryType, subType
//        try {
//            primaryType = fileUpload.getMimeType().split("/")[0]
//            subType = fileUpload.getMimeType().split("/")[1]
//        }
//        catch (IndexOutOfBoundsException | NullPointerException ex) {
//            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//
//        headers.setContentType( new MediaType(primaryType, subType) )
//
//        return new ResponseEntity<>(fileUpload.getFile(), headers, HttpStatus.OK)
//    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {


        String randomId = UUID.randomUUID().toString()

        String resultFileName = null

        try {
            Iterator<String> itr = request.getFileNames()

            while (itr.hasNext()) {
                String uploadedFile = itr.next()
                MultipartFile file = request.getFile(uploadedFile)
                String mimeType = file.getContentType()
                String filename = file.getOriginalFilename()
                byte[] bytes = file.getBytes()

                resultFileName = randomId + "_" + filename

                File savedFile = new File("/Users/lennonjesus/Projects/linkar/linkar/upload/" + resultFileName)

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile))

                stream.write(bytes)
                stream.close()

            }
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return new ResponseEntity<>("{ \"filePath\" : \"" + resultFileName + "\" }", HttpStatus.OK)
    }

}
