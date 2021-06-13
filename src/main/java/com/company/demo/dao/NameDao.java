package com.company.demo.dao;

import com.company.demo.model.Name;

public interface NameDao {

    Name getName(int id);

    Name addName(Name name);

}
