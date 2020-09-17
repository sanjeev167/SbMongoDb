/**
 * 
 */
package com.pon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * @author Sanjeev
 *
 */
@Configuration
public class AuthDataSourceConfiguration extends AbstractMongoClientConfiguration{
 
	 @Override
	public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
        // remove __class field from mongo
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));//Comment it to have class package name with record in db
        return super.mongoTemplate(databaseFactory, converter);
    }

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "user_db";
	}

}