package br.com.clubelinkar.support.event

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by felipe on 3/13/16.
 */
interface TimelineEventRepository extends MongoRepository<TimelineEvent, String> {

}