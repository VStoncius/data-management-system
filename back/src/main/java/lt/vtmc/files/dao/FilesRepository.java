package lt.vtmc.files.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.vtmc.files.model.File4DB;

/**
 * DAO for files. Files are saved as byte array in repository.
 * 
 * @author pra-va
 *
 */
public interface FilesRepository extends JpaRepository<File4DB, Integer> {

	/**
	 * Finds a file by UID
	 * 
	 * @param UID
	 */

	File4DB findFile4dbByUID(String UID);

	/**
	 * Deletes a file from the system
	 * 
	 * @param UID
	 */

	void deleteFileByUID(String UID);

}
