package com.gfa.oeexam.orientation.repositories;

import com.gfa.oeexam.orientation.models.UrlAlias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends CrudRepository <UrlAlias, String> {

    @Query(value = "SELECT * FROM lovely_alias a WHERE a.alias = ?1", nativeQuery = true)
    Optional<UrlAlias> getItemByAlias(String alias);
}
