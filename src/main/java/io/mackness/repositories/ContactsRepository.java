package io.mackness.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import models.Contact;

@Repository
public class ContactsRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public ContactsRepository (JdbcTemplate jdbc){
		this.jdbc = jdbc;
	}

	public List<Contact> findAll() {
		return jdbc.query(
			"select id, firstName, lastName, phoneNumber, emailAddress " +
			"from contacts order by lastName",
			new RowMapper<Contact>(){
				public Contact mapRow(ResultSet resultSet, int rowNumber)
					throws SQLException{
					Contact contact = new Contact();
					contact.setId(resultSet.getLong(1));
					contact.setFirstName(resultSet.getString(2));
					contact.setLastName(resultSet.getString(3));
					contact.setPhoneNumber(resultSet.getString(4));
					contact.setEmailAddress(resultSet.getString(5));
					return contact;
				}
			}
		);
	}

	public void save(Contact contact) {
		jdbc.update(
			"insert into contacts " +
			"(firstName, lastName, phoneNumber, emailAddress)" +
			"values (?, ?, ?, ?)",
			contact.getFirstName(), contact.getLastName(),
			contact.getPhoneNumber(),contact.getEmailAddress()
		);
		
	}

}
