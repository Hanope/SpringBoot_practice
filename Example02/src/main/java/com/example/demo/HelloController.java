package com.example.demo;

import com.example.demo.dao.MyDataDao;
import com.example.demo.dao.MyDataDaoImpl;
import com.example.demo.repository.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private MyDataRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    private MyDataDaoImpl dao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData myData, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content.");
        Iterable<MyData> list = repository.findAllOrderByName();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData myData,
                             BindingResult result, ModelAndView mav) {
        ModelAndView res = null;
        if(!result.hasErrors()) {
            repository.saveAndFlush(myData);
            res = new ModelAndView("redirect:/");
        }
        else {
            mav.setViewName("index");
            mav.addObject("msg", "sorry, error is occured...");
            Iterable<MyData> list = repository.findAll();
            mav.addObject("datalist", list);
            res = mav;
        }

        return res;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav) {
        mav.setViewName("find");
        mav.addObject("title", "Find Page");
        mav.addObject("msg", "MyData의 예제입니다.");
        mav.addObject("value", "");
        Iterable<MyData> list = dao.getAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("find");
        String param = request.getParameter("fstr");
        if(param == "") {
            mav = new ModelAndView("redirect:/find");
        }
        else {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "[" + param + "] 의 검색 결과");
            mav.addObject("value", param);
            List<MyData> list = dao.find(param);
            mav.addObject("datalist", list);
        }

        return mav;
    }

    @PostConstruct
    public void init() {
        dao = new MyDataDaoImpl(entityManager);
        MyData d1 = new MyData();
        d1.setName("kim");
        d1.setAge(123);
        d1.setMail("a@a.com");
        d1.setMemo("090-999-999");
        repository.saveAndFlush(d1);
        MyData d2 = new MyData();
        d2.setName("lee");
        d2.setAge(15);
        d2.setMail("b@a.com");
        d2.setMemo("080-888-888");
        repository.saveAndFlush(d2);
        MyData d3 = new MyData();
        d3.setName("choi");
        d3.setAge(37);
        d3.setMail("c@a.com");
        d3.setMemo("070-777-777");
        repository.saveAndFlush(d3);
    }
}