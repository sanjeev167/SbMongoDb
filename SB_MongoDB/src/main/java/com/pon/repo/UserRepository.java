/**
 * 
 */
package com.pon.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pon.model.User;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
