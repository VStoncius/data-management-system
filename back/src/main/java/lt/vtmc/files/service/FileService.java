package lt.vtmc.files.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lt.vtmc.documents.dao.DocumentRepository;
import lt.vtmc.documents.model.Document;
import lt.vtmc.documents.service.DocumentService;
import lt.vtmc.files.dao.FilesRepository;
import lt.vtmc.files.dto.FileDetailsDTO;
import lt.vtmc.files.model.File4DB;
import lt.vtmc.user.controller.UserController;
import lt.vtmc.user.dao.UserRepository;
import lt.vtmc.user.model.User;

/**
 * Service layer for uploading and downloading files. Note that files that are
 * being uploaded are saved on database as BLOBs.
 * 
 * @author pra-va
 *
 */
@Service
public class FileService {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private FilesRepository filesRepository;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DocumentService docService;
	/**
	 * This method saves single files to a database.
	 * 
	 * @param file
	 * @throws Exception
	 */
	@Transactional
	public void saveFile(MultipartFile file, Document doc) throws Exception {
		LOG.info("saving file to db");
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		String fileType = file.getContentType();
		File4DB file4db = new File4DB(fileName, fileType, bytes, docService.generateUID());
		file4db.setDocument(doc);
		List<File4DB> tmplist = doc.getFileList();
		tmplist.add(file4db);
		doc.setFileList(tmplist);
		filesRepository.save(file4db);
		documentRepository.save(doc);
	}

	/**
	 * This method handles single file download from database. It looks for specific
	 * file name and returns this file as response entity.
	 * 
	 * @param fileName
	 * @return
	 */
	@Transactional
	public ResponseEntity<Resource> downloadFileByUID(String fileUID) {
		File4DB file4db = filesRepository.findFile4dbByUID(fileUID);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file4db.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file4db.getFileName() + "\"")
				.body(new ByteArrayResource(file4db.getData()));
	}

	public List<FileDetailsDTO> findAllFileDetailsByUsername(String username) {
		List<Document> tmpList = docService.findAllDocumentsByUsername(username);
		Set<File4DB> listToReturn = new HashSet<File4DB>();
		for (Document document : tmpList) {
			List<File4DB> tmp = document.getFileList();
			listToReturn.addAll(tmp);
		}
		List<FileDetailsDTO> returnList = new ArrayList<FileDetailsDTO>();
		for (File4DB file4db : listToReturn) {
			returnList.add(new FileDetailsDTO(file4db));
		}
		return returnList;
	}
	
	public List<FileDetailsDTO> findAllFileDetailsByDocument(String docName){
		Document tmpDoc = docService.findDocumentByName(docName);
		Set<File4DB> listToReturn = new HashSet<File4DB>();
		listToReturn.addAll(tmpDoc.getFileList());
		List<FileDetailsDTO> returnList = new ArrayList<FileDetailsDTO>();
		for (File4DB file4db : listToReturn) {
			returnList.add(new FileDetailsDTO(file4db));
		}
		return returnList;
	}
}
