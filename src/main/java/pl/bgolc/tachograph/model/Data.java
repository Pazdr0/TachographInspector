package pl.bgolc.tachograph.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="data")
public class Data implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
/*	https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/
 * 	http://zetcode.com/springboot/postgresql/
 * 	https://spring.io/guides/gs/accessing-data-jpa/
 * 	https://www.baeldung.com/spring-data-repositories
 * 	https://www.baeldung.com/stored-procedures-with-hibernate-tutorial
 * 	https://stackoverflow.com/questions/26122796/how-to-properly-call-postgresql-functions-stored-procedures-within-spring-hibe
 *	https://www.thoughts-on-java.org/hibernate-tips-call-postgresql-function/
*/	private String data;
	
	private Timestamp date;
}
