package com.example.demo.dao;

import com.example.demo.MyData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {

    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;

    public MyDataDaoImpl() {
        super();
    }

    public MyDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MyData> getAll() {
        Query query = entityManager.createQuery("from MyData");
        List<MyData> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public MyData findById(long id) {
        // ID를 검색하는 것이기 때문에 단 하나의 Entity만 나오므로 SingleResult 사용
        return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
    }

    @Override
    public List<MyData> findByName(String name) {
        // 동일한 이름이 있을 수 있기 때문에 ResultList 사용
        return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
    }

    @Override
    public List<MyData> find(String fstr) {
        List<MyData> list = null;
        Long fid = 0L;
        try {
            fid = Long.parseLong(fstr);
        } catch (NumberFormatException e) {

        }
        Query query = entityManager.createNamedQuery("findWithName")
                .setParameter("fname", "%" + fstr + "%");
        list = query.getResultList();
        return list;
    }

    @Override
    public List<MyData> findByAge(int min, int max) {
        return (List<MyData>)entityManager
                .createNamedQuery("findByAge")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
}