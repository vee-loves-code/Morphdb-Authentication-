package com.example.morphdb.inftrastructure.persistence.daoimpl;

import com.example.morphdb.domain.dao.CrudDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudDaoImpl<T, ID> implements CrudDao<T, ID> {

    private final JpaRepository<T, ID> repository;

    public CrudDaoImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T getRecordById(ID id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found. Entity with id: " + id));
    }

    @Override
    public T saveRecord(T record) {
        return repository.saveAndFlush(record);
    }

    @Override
    public List<T> saveAllRecord(List<T> record) {
        return repository.saveAll(record);
    }

    @Override
    public void deleteRecord(T record) {
        repository.delete(record);
    }

    @Override
    public List<T> getAllRecords() {
        return repository.findAll();
    }
}
