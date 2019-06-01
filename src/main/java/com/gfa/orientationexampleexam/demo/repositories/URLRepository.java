package com.gfa.orientationexampleexam.demo.repositories;

import com.gfa.orientationexampleexam.demo.models.URLItem;
import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository <URLItem, String> {
}
