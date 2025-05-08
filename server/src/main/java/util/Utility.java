package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.web.multipart.MultipartFile;

import requestDto.FileUploadPathDto;

public class Utility {

	public static String getEncodeData(String decryptData) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encode = encoder.encodeToString(decryptData.getBytes());
		return encode;
	}

	public static String getDecryptData(String encryptData) throws Exception {
		byte[] decode = Base64.getDecoder().decode(encryptData);
		String decryptCode = new String(decode);
		return decryptCode;
	}

	public static String uploadFile(MultipartFile file, String fileName) throws Exception {
		String result = null;
		System.out.println("inside uploadFile method");

		try {
			System.out.println(file.getContentType());
			if (!file.isEmpty()) {

				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				System.out.println("bytes : " + bytes);
				double rand = Math.random();
				String random = String.valueOf(rand);
				random = random.substring(random.indexOf(".") + 1);

				// For Local
				// Path path =
				// Paths.get("C:\\dspUploadFile\\"+fileName+"_"+random+"_"+file.getOriginalFilename());

				// For Rooftop
               // Path path = Paths
               // .get("D:\\amstiko frontend\\am-ecommerce-project\\src\\assets\\Mabs\\" + fileName + "_" + random + "_" + file.getOriginalFilename());

                 // Path path = Paths
                // .get("D:\\Ecommerce-pdf-upload\\" + fileName + "_" + random + "_" + file.getOriginalFilename());
				
//				Path path = Paths
//						.get("D:\\amsitko frontend\\am-ecommerce-project\\src\\assets\\" + fileName + "_" + random + "_" + file.getOriginalFilename());
//				
                //for server 				
//				Path path = Paths
//						.get("/home/uploads/" + fileName + "_" + random + "_" + file.getOriginalFilename());
				
                  //	for local test			
				Path path = Paths
						.get("D:\\angular 16 pro\\am-ecommerce-project\\src\\assets\\local-practice\\" + fileName + "_" + random + "_" + file.getOriginalFilename());
				
			
				System.out.println("path " + path);
				Path filePath = Files.write(path, bytes);
				FileUploadPathDto filePathDto = new FileUploadPathDto();
				filePathDto.setFilePath(filePath.toString());

				System.out.println("filePathDto : " + filePathDto);
				result = path.toString();
			}

		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public static String getRandomNumber() {
		String localDateTime = LocalDateTime.now().toString();// 2023-07-11T10:31:28.279
		String localDateTimeReplace = localDateTime.replaceAll("\\-|\\:|[a-zA-z]|\\.", "");

		return localDateTimeReplace;
	}

}
