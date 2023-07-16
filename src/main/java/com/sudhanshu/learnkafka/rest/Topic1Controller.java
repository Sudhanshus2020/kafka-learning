/**
 * 
 */
package com.sudhanshu.learnkafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sudhanshu.learnkafka.producer.Topic1Producer;

/**
 * @author SudhanshuS
 *
 */
@RestController
@RequestMapping("topic1")
public class Topic1Controller {

	@Autowired
	Topic1Producer topic1Producer;

	@PostMapping(value = "/{contactId}")
	public @ResponseBody ResponseEntity<?> send(@PathVariable Integer contactId, @RequestBody String contactName) {

		topic1Producer.sendMessage(contactId, contactName);
		return ResponseEntity.status(HttpStatus.CREATED).body(contactId);
	}

}
