package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    @Value("${app.prop.upload-folder}")
    private String uploadFolder;

    //паказываем форму загрузки по url
    @RequestMapping("/transfer")
    public ModelAndView showUpload() {
        ModelAndView model = new ModelAndView("transfer");
        List<String> fileList =  getFileList();

        model.addObject("fileList", fileList);

        return model;
    }

    //загружаем постом файл
    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            return new ModelAndView("status", "message", "File is empty");
        }

        try {
            //пишем фаил в указанное хранилище
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //паказываем, что файл загружен
        return showUpload();
    }

    //загрузка файла из хранилища
    @RequestMapping(value = "/download/{fileName:.*}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        String filename = uploadFolder + fileName;
        InputStream inputImage = new FileInputStream(filename);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int l = inputImage.read(buffer);
        while(l >= 0) {
            outputStream.write(buffer, 0, l);
            l = inputImage.read(buffer);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type",  MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.set("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    //читаем файлы из хранилища
    private List<String> getFileList() {
        try {
            return Files.walk(Paths.get(uploadFolder))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(File::getName)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
