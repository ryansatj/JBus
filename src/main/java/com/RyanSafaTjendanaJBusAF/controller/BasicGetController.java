package com.RyanSafaTjendanaJBusAF.controller;

import com.RyanSafaTjendanaJBusAF.Algorithm;
import com.RyanSafaTjendanaJBusAF.dbjson.JsonTable;
import com.RyanSafaTjendanaJBusAF.dbjson.Serializable;
import org.apache.coyote.RequestInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.RyanSafaTjendanaJBusAF.Algorithm.find;
import static com.RyanSafaTjendanaJBusAF.Algorithm.paginate;

@RequestMapping("/page")
@RestController
public interface BasicGetController<T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pagesize
    ){
        return Algorithm.paginate(getJsonTable(), page, pagesize, t->true);
    }

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id)
    {
        return Algorithm.<T>find(getJsonTable(), e->e.id == id);
    }
}

