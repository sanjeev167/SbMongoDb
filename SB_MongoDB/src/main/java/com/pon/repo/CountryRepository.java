/**
 * 
 */
package com.pon.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.stereotype.Repository;

import com.pon.model.Country;

import java.util.List;

/**
 * @author Sanjeev
 *
 */

// Use this instead if you don't want to extend Spring Data interfaces
// @RepositoryDefinition(domainClass=Country.class, idClass=String.class)
@Repository
public interface CountryRepository extends MongoRepository<Country, String> {

	// ------------------------------------------- equality

	public Country findByCountry(String countryName);

	@Query("{name : ?0}")
	public Country findByCountryQuery(String countryName);

	// ------------------------------------------- not equal

	public List<Country> findByCountryNot(String countryName);

	@Query("{name : {$ne : ?0}}")
	public List<Country> findByCountryNotQuery(String countryName);

	// ------------------------------------------- like / regex

	public List<Country> findByCountryLike(String countryName);

	public List<Country> readByCountryRegex(String countryName);

	@Query("{name : {$regex : ?0}}")
	public List<Country> getByCountryRegexQuery(String countryName);

	// ------------------------------------------- nested

	//public List<Country> findByContinentName(String continentName);

	//@Query("{'continent.name' : ?0}")
	//public List<Country> findByContinentNameQuery(String continentName);

	// ------------------------------------------- null / not null

	public List<Country> findByPopulationIsNull();

	@Query("{'population' : null}")
	public List<Country> findByPopulationIsNullQuery();

	public List<Country> findByPopulationIsNotNull();

	@Query("{'population' : {$ne : null}}")
	public List<Country> findByPopulationIsNotNullQuery();

	// ------------------------------------------- less than / greater than

	//public List<Country> findByAreaInSquareMilesLessThan(int area);

	//@Query("{'area' : {$lt : ?0}}")
	//public List<Country> findByAreaInSquareMilesLessThanQuery(int area);

	public List<Country> findByPopulationGreaterThan(int population);

	@Query("{'population' : {$gt : ?0}}")
	public List<Country> findByPopulationGreaterThanQuery(int population);

	// ------------------------------------------- between

	public List<Country> findByPopulationBetween(int start, int end);

	@Query("{'population' : {$gt : ?0, $lt : ?1}}")
	public List<Country> findByPopulationBetweenQuery(int start, int end);

	// ------------------------------------------- and

	//public List<Country> findByContinentNameAndPopulationLessThan(String continentName, int pop);

	//@Query("{'continent.name' : ?0, population : {$lt : ?1}}")
	//public List<Country> findByContinentNameAndPopulationLessThanQuery(String continentName, int pop);

	// ------------------------------------------- or

	//public List<Country> findByPopulationLessThanOrAreaInSquareMilesLessThan(int pop, int area);

	//@Query("{'$or' : [{'population' : {$lt : ?0}}, {'area' : {$lt : ?1}}]}")
	//public List<Country> findByPopulationLessThanOrAreaInSquareMilesLessThanQuery(int pop, int area);

	// ------------------------------------------- order by

	//public List<Country> findByContinentNameOrderByPopulationDesc(String continentName);

	// ------------------------------------------- fields

	//@Query(value = "{'continent.name' : ?0}", fields = "{_id : 0, name : 1}")
	//public List<Country> findByContinentNameJustReturnNameQuery(String continentName);

}

/**
 * Following example can be used while fetching data from MongoDb
 * 
 * [1] Use of is
		 Query query = new Query();
		query.addCriteria(Criteria.where("name").is("Eric"));
		List<User> users = mongoTemplate.find(query, User.class);

 * [2] Use of Regex
        Starts with A
       Query query = new Query();
       query.addCriteria(Criteria.where("name").regex("^A"));
       List<User> users = mongoTemplate.find(query,User.class);
       Ends with c
 	   Query query = new Query();
		query.addCriteria(Criteria.where("name").regex("c$"));
		List<User> users = mongoTemplate.find(query, User.class);

 * [3] Use of Lt and gt
      Query query = new Query();
	  query.addCriteria(Criteria.where("age").lt(50).gt(20));
	  List<User> users = mongoTemplate.find(query,User.class);

 * [4] Use of Sort
 
    Query query = new Query();
	query.with(Sort.by(Sort.Direction.ASC, "age"));
	List<User> users = mongoTemplate.find(query,User.class);

 * [5] Use of Pageable
 * 
 *  	final Pageable pageableRequest = PageRequest.of(0, 2);
		Query query = new Query();
		query.with(pageableRequest);
 * Generated Query
 * 
 * public interface UserRepository 
  extends MongoRepository<User, String>, QueryDslPredicateExecutor<User> {
    ...
}
 * [1] FindByX
 * 
 * List<User> users = userRepository.findByName("Eric");
   [2.] StartingWith and endingWith
   
    List<User> findByNameStartingWith(String regexp);
	List<User> findByNameEndingWith(String regexp);
	List<User> users = userRepository.findByNameStartingWith("A");
	List<User> users = userRepository.findByNameEndingWith("c");

 * [3] Use of Between
 * 
 * List<User> findByAgeBetween(int ageGT, int ageLT);
   List<User> users = userRepository.findByAgeBetween(20, 50);

 * [4] Like and OrderBy
 * List<User> users = userRepository.findByNameLikeOrderByAgeAsc("A");

  * JSON Query Methods
  * [1] @Query("{ 'name' : ?0 }")
		List<User> findUsersByName(String name);
		List<User> users = userRepository.findUsersByName("Eric");


  [2] $regex
      @Query("{ 'name' : { $regex: ?0 } }")
      List<User> findUsersByRegexpName(String regexp);
      List<User> users = userRepository.findUsersByRegexpName("^A");
      List<User> users = userRepository.findUsersByRegexpName("c$");

  [3] $lt and $gt
  
     @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<User> findUsersByAgeBetween(int ageGT, int ageLT);
    List<User> users = userRepository.findUsersByAgeBetween(20, 50);
    
    

 * **/
 