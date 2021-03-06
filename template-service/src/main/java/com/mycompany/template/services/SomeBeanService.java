package com.mycompany.template.services;

import com.mycompany.template.aspects.Loggable;
import com.mycompany.template.beans.Pager;
import com.mycompany.template.beans.Parameter;
import com.mycompany.template.beans.SomeBean;
import com.mycompany.template.repositories.PropertyRepository;
import com.mycompany.template.repositories.SomeBeanRepository;
import com.mycompany.template.utils.PagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 7/1/13
 * Time: 6:01 PM
  */
@Service
@Loggable
public class SomeBeanService {

    @Autowired
    SomeBeanRepository someBeanRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    PagerUtils pagerUtils;

    /**
     * Geat a SomeBean by id
     * @param id
     * @return SomeBean
     */
    public SomeBean getSomeBena(String id){
        return someBeanRepository.findOne(id);
    }

    /**
     * Returns all SomeBeans from the dataBase
     * @return list of SomeBean
     */
    public List<SomeBean> getAllSomeBeans(){
        return (List<SomeBean>) someBeanRepository.findAll();
    }


    /**
     * Returns limited list of SomeBean
     * @param skip
     * @param limit
     * @param simple
     * @return list of SomeBean
     */
    public List<SomeBean> findLimited(int skip, int limit, boolean simple){
        if (simple){
            return someBeanRepository.findLimitedSimple(skip, limit);
        } else {
            return someBeanRepository.findLimited(skip, limit);
        }
    }


    /**
     * Returns limited list of SomeBean
     * @param skip
     * @param limit
     * @param title
     * @param createdAfter
     * @return list of SomeBean
     */
    public List<SomeBean> findFiltered(int skip, int limit, String title, long createdAfter){
        return someBeanRepository.findFiltered(skip, limit, title, createdAfter);
    }

    /**
     * Creates or updates SomeBean
     * @param someBean
     * @return SomeBean
     */
    public SomeBean saveSomeBean(SomeBean someBean){
        propertyRepository.save(someBean.getProperties());
        return someBeanRepository.save(someBean);
    }

    /**
     * Removes a SomeBean by bean
     * @param someBean
     */
    public void deleteSomeBean(SomeBean someBean){
        if (someBean != null){
            propertyRepository.delete(someBean.getProperties());
            someBeanRepository.delete(someBean);
        }
    }

    /**
     * Removes a SomeBean by id
     * @param id
     */
    public void deleteSomeBean(String id){
        deleteSomeBean(someBeanRepository.findOne(id));
    }

    public Pager getSomeBeansPager (int skip, int limit){
        return pagerUtils.getPager(someBeanRepository.count(), limit, skip, new ArrayList<Parameter>());
    }
}
