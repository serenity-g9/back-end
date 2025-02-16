package com.serenity.api.serenity.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    void softDeleteById(ID id);

    void undeleteById(ID id);

    List<T> findAllActive();

    Optional<T> findByIdActive(ID id);
}

